package net.cydhra.vibrant.organization.channel

import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.organization.GameResourceState
import net.cydhra.vibrant.organization.priorities.ResourceRequestPriority

/**
 *
 */
interface IResourceChannel<R : GameResourceState> {

    fun appendState(state: R, priority: ResourceRequestPriority, side: ResourceChannel.Side)

    fun getCurrentState(side: ResourceChannel.Side): R

    /**
     * Determine the new state of the channel of all appended states and the previous state
     */
    fun evaluateNewState()

    /**
     * Add a lock on a resource state with given priority on given side. If this module already holds a lock, it will be overriden.
     *
     * @see appendState
     */
    fun addLock(state: () -> R, module: Module, priority: ResourceRequestPriority, side: ResourceChannel.Side)

    /**
     * Remove the lock of this module. This method does nothing, if the module does not hold any lock on this channel.
     */
    fun removeLock(module: Module)

    /**
     * Update a lock with new state and out of bands update the channel, if the lock is currently active
     */
    fun updateLockedStateOutOfBands(state: R, module: Module)
}