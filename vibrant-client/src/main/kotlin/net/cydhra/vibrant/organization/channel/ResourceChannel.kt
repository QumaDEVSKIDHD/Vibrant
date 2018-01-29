package net.cydhra.vibrant.organization.channel

import net.cydhra.vibrant.organization.priorities.ResourceRequestPriority

/**
 *
 */
open class ResourceChannel<R : Any>(protected val monitor: () -> R) : IResourceChannel<R> {

    protected lateinit var clientSideState: R
    protected lateinit var serverSideState: R

    protected val requests: MutableCollection<Request<R>> = mutableListOf()

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
    }

    override fun updateState(side: Side, state: R) {
        when (side) {
            Side.CLIENT -> clientSideState = state
            Side.SERVER -> serverSideState = state
            else -> {
                clientSideState = state
                serverSideState = state
            }
        }
    }

    enum class Side {
        CLIENT, SERVER, BOTH
    }

    protected data class Request<R : Any>(val state: R, val priority: ResourceRequestPriority, val side: Side)
}