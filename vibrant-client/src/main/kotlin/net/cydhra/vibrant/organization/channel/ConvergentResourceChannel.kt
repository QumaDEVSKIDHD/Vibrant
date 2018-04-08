package net.cydhra.vibrant.organization.channel

import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.organization.GameResourceState
import net.cydhra.vibrant.organization.priorities.ResourceRequestPriority

/**
 *
 */
class ConvergentResourceChannel<R : GameResourceState>(channel: IResourceChannel<R>) : ResourceChannelDecorator<R>(channel) {

    /**
     * Ignores the [side] argument and overrides it with [ResourceChannel.Side.BOTH]
     */
    override fun appendState(state: R, priority: ResourceRequestPriority, side: ResourceChannel.Side) {
        channel.appendState(state, priority, ResourceChannel.Side.BOTH)
    }

    /**
     * This cannot throw an exception because both sides are the same.
     *
     * @see [ResourceChannel.getCurrentState]
     */
    override fun getCurrentState(side: ResourceChannel.Side): R {
        return channel.getCurrentState(ResourceChannel.Side.CLIENT)
    }

    override fun addLock(state: () -> R, module: Module, priority: ResourceRequestPriority, side: ResourceChannel.Side) {
        channel.addLock(state, module, priority, ResourceChannel.Side.BOTH)
    }
}