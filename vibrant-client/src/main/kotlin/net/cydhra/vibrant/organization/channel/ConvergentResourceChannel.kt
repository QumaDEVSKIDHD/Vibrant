package net.cydhra.vibrant.organization.channel

import net.cydhra.vibrant.organization.priorities.ResourceRequestPriority

/**
 *
 */
class ConvergentResourceChannel<R : Any>(channel: IResourceChannel<R>) : ResourceChannelDecorator<R>(channel) {

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

    override fun updateState(side: ResourceChannel.Side, state: R) {
        if (side == ResourceChannel.Side.CLIENT)
            super.updateState(ResourceChannel.Side.BOTH, state)
        // else just ignore, because server sided updates do not change the state, since the client one already did.
    }
}