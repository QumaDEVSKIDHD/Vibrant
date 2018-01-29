package net.cydhra.vibrant.organization.channel

import net.cydhra.vibrant.organization.priorities.ResourceRequestPriority

/**
 *
 */
abstract class ResourceChannelDecorator<R : Any>(val channel: IResourceChannel<R>) : IResourceChannel<R> {

    override fun appendState(state: R, priority: ResourceRequestPriority, side: ResourceChannel.Side) {
        channel.appendState(state, priority, side)
    }

    override fun getCurrentState(side: ResourceChannel.Side): R {
        return channel.getCurrentState(side)
    }
}