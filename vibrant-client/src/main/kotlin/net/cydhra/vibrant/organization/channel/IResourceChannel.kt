package net.cydhra.vibrant.organization.channel

import net.cydhra.vibrant.organization.priorities.ResourceRequestPriority

/**
 *
 */
interface IResourceChannel<R : Any> {

    fun appendState(state: R, priority: ResourceRequestPriority, side: ResourceChannel.Side)

    fun getCurrentState(side: ResourceChannel.Side): R
}