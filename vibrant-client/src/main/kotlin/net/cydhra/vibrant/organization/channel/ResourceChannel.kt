package net.cydhra.vibrant.organization.channel

import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.organization.GameResourceState
import net.cydhra.vibrant.organization.channel.ResourceChannel.Side
import net.cydhra.vibrant.organization.priorities.ResourceRequestPriority

/**
 * @param monitor a getter for the actual resource state in the client
 * @param updater a function taking a side (it wont receive [Side.BOTH]) and the new state for that side which will then update the
 * actual resource
 */
open class ResourceChannel<R : GameResourceState>(protected val monitor: () -> R, val updater: (Side, R) -> Unit) : IResourceChannel<R> {

    protected var clientSideState: R = monitor.invoke()
    protected var serverSideState: R = monitor.invoke()

    protected val requests: MutableCollection<Request<R>> = mutableListOf()
    protected val locks: MutableMap<Module, Request<R>> = mutableMapOf()
    /**
     * Append a resource state with priority and side that is requested. Whether the new state is accepted depends on the priority and
     * other requests.
     *
     * @param state requested state
     * @param priority request priority
     * @param side side the state shall be applied to
     */
    override fun appendState(state: R, priority: ResourceRequestPriority, side: Side) {
        requests.add(Request(state, priority, side))
    }

    override fun addLock(state: R, module: Module, priority: ResourceRequestPriority, side: Side) {
        locks[module] = Request(state, priority, side)
    }

    override fun removeLock(module: Module) {
        locks.remove(module)
    }

    /**
     * @param side the side the state is requested for. Note, that [Side.BOTH] may yield invalid results thus throwing an exception
     *
     * @return the state this channel holds currently for given side
     */
    override fun getCurrentState(side: Side): R {
        return when (side) {
            Side.CLIENT -> clientSideState
            Side.SERVER -> serverSideState
            Side.BOTH -> throw IllegalStateException("Cannot return both states")
        }
    }

    override fun evaluateNewState() {
        this.clientSideState = monitor.invoke()

        // add requests by locks
        this.requests.addAll(locks.values)

        val newClientSideState = this.requests
                .stream()
                .filter { it.side == Side.CLIENT || it.side == Side.BOTH }
                .reduce { acc, e -> if (acc.priority < e.priority) e else acc }
                .map { it.state }
                .orElse(monitor.invoke())

        val newServerSideState = this.requests
                .stream()
                .filter { it.side == Side.SERVER || it.side == Side.BOTH }
                .reduce { acc, e -> if (acc.priority < e.priority) e else acc }
                .map { it.state }
                .orElse(monitor.invoke())

        if (newClientSideState != this.clientSideState) {
            this.updateState(Side.CLIENT, newClientSideState)
        }

        if (newServerSideState != this.serverSideState) {
            this.updateState(Side.SERVER, newServerSideState)
        }

        this.requests.clear()
    }

    /**
     * Actually updates the side. This will invoke the [updater] with the actually updated side and the new state for that side
     */
    private fun updateState(side: Side, state: R) {
        if (side == Side.CLIENT || side == Side.BOTH) {
            clientSideState = state
            updater.invoke(Side.CLIENT, state)
        }

        if (side == Side.SERVER || side == Side.BOTH) {
            serverSideState = state
            updater.invoke(Side.SERVER, state)
        }
    }

    enum class Side {
        CLIENT, SERVER, BOTH
    }

    protected data class Request<R : GameResourceState>(val state: R, val priority: ResourceRequestPriority, val side: Side)
}