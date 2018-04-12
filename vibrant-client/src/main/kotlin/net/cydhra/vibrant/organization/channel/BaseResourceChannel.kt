package net.cydhra.vibrant.organization.channel

import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.organization.GameResource
import net.cydhra.vibrant.organization.locks.ResourceLock
import net.cydhra.vibrant.organization.locks.Side
import net.cydhra.vibrant.organization.resources.GameResourceState

class BaseResourceChannel<G : GameResource<S>, S : GameResourceState>(private val resource: G, internal val monitor: () -> S)
    : ResourceChannel<G, S> {

    private val registeredLocks: MutableList<ResourceLock<G, S>> = mutableListOf()

    override fun registerLock(lock: ResourceLock<G, S>) {
        registeredLocks.add(lock)
    }

    override fun releaseLock(lock: ResourceLock<G, S>) {
        registeredLocks.remove(lock)
    }

    override fun releaseAllLocks(module: Module) {
        registeredLocks.removeIf { it.module == module }
    }

    @Suppress("UNCHECKED_CAST")
    override fun evaluateState(side: Side) {
        if (side == Side.BOTH)
            throw IllegalArgumentException("Cannot evaluate both sides at the same time.")

        val monitorState = monitor()
        val currentBuiltState = monitorState.generateEmptyState() as? S ?: throw IllegalArgumentException(
                "${monitorState.javaClass.simpleName} does not return valid empty state")

        // ask the generators for the current priority and state of the lock
        val stateRequests = registeredLocks
                .filter { it.side == side }
                .map { Pair(it.priorityGenerator(), it.stateGenerator()) }
                .sortedBy { it.first }

        // build the currentBuiltState from all requests
        requests@ for (request in stateRequests) {
            val (_, currentRequestState) = request

            // test if current state diverges from request
            for ((index, partialState) in currentRequestState.partialStates.withIndex()) {
                if (currentBuiltState.partialStates[index].doesCare()
                        && currentBuiltState.partialStates[index] != partialState) {
                    // the currently built state diverges at partialStates[index] from the request, so it cannot be fulfilled
                    continue@requests
                }
            }

            // if current state can fit the request, apply it
            for ((index, partialState) in currentRequestState.partialStates.withIndex()) {
                if (!currentBuiltState.partialStates[index].doesCare()) {
                    if (partialState.doesCare()) {
                        // the state must be altered. Note that this is not type safe (it is type safe, but the type-system
                        // does not know that) and therefore the unsafe method must be used
                        currentBuiltState.partialStates[index].unsafeChangeState(partialState.state!!)
                    }
                }
            }
        }

        // fill in missing state from monitor
        for ((index, partialState) in currentBuiltState.partialStates.withIndex()) {
            if (!partialState.doesCare()) {
                assert(monitorState.partialStates.all { it.doesCare() })
                partialState.unsafeChangeState(monitorState.partialStates[index].state!!)
            }
        }

        resource.updateState(side, currentBuiltState)
    }
}