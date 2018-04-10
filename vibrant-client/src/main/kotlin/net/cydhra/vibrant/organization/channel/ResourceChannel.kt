package net.cydhra.vibrant.organization.channel

import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.organization.GameResource
import net.cydhra.vibrant.organization.locks.ResourceLock
import net.cydhra.vibrant.organization.resources.GameResourceState

/**
 * A resource channel is a utility that takes the different requests from [Modules][Module] to change the state of game
 * resources (like the player's onGround-state, look-vector or position) and decides which requests to fulfill.
 */
interface ResourceChannel<G : GameResource<S>, S : GameResourceState> {

    /**
     * Register a lock onto a game resource. A lock is not guaranteed to be fulfilled.
     * @param lock a [ResourceLock] containing lambdas providing state and priority for the lock and actions to
     * execute when the request is fulfilled
     */
    fun registerLock(lock: ResourceLock<G, S>)

    /**
     * Release a given [lock]
     */
    fun releaseLock(lock: ResourceLock<G, S>)

    /**
     * Release all locks of a [Module]
     *
     * @param module the module which may have locks to release
     */
    fun releaseAllLocks(module: Module)

    /**
     * Evaluate the state of the channel from all locks given to the method
     *
     * @param locks a list of [ResourceLocks][ResourceLock] that shall be taken into consideration by the evaluation.
     */
    fun evaluateState(locks: List<ResourceLock<G, S>>): S
}