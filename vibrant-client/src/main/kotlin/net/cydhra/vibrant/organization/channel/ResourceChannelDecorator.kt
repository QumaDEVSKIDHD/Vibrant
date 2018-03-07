package net.cydhra.vibrant.organization.channel

import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.organization.GameResourceState
import net.cydhra.vibrant.organization.priorities.ResourceRequestPriority

/**
 *
 */
abstract class ResourceChannelDecorator<R : GameResourceState>(val channel: IResourceChannel<R>) : IResourceChannel<R> {

    override fun appendState(state: R, priority: ResourceRequestPriority, side: ResourceChannel.Side) {
        this.channel.appendState(state, priority, side)
    }

    override fun getCurrentState(side: ResourceChannel.Side): R {
        return channel.getCurrentState(side)
    }

    override fun evaluateNewState() {
        this.channel.evaluateNewState()
    }

    override fun addLock(state: () -> R, module: Module, priority: ResourceRequestPriority, side: ResourceChannel.Side) {
        this.channel.addLock(state, module, priority, side)
    }

    override fun removeLock(module: Module) {
        this.channel.removeLock(module)
    }

    override fun updateLockedStateOutOfBands(state: R, module: Module) {
        this.channel.updateLockedStateOutOfBands(state, module)
    }
}