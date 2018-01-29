package net.cydhra.vibrant.organization.channel

import net.cydhra.vibrant.organization.priorities.ResourceRequestPriority

/**
 *
 */
open class ResourceChannel<R : Any>(protected val monitor: () -> R): IResourceChannel<R> {

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
        return when(side) {
            Side.CLIENT -> clientSideState
            Side.SERVER -> serverSideState
            Side.BOTH -> throw IllegalStateException("Cannot return both states")
        }
    }

    enum class Side {
        CLIENT, SERVER, BOTH
    }

    protected data class Request<R : Any>(val state: R, val priority: ResourceRequestPriority, val side: Side)
}