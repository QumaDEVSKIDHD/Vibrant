package net.cydhra.vibrant.organization

import net.cydhra.vibrant.organization.channel.IResourceChannel
import net.cydhra.vibrant.organization.channel.ResourceChannel

/**
 *
 */
abstract class GameResource<out S : GameResourceState> {

    /**
     * Register a [ResourceChannel] in a map of game resources with this instance as key. The channel shall not be exposed and therefore,
     * this function returns a lambda with the map as receiver instead of the channel.
     */
    abstract fun register(): MutableMap<GameResource<*>, in IResourceChannel<*>>.() -> Unit

    /**
     * @return the current state for this resource on the given side. Note that [ResourceChannel.Side.BOTH] may yield invalid results
     * thus throwing an Exception.
     */
    fun getCurrentState(side: ResourceChannel.Side): S {
        return GameResourceManager.getCurrentState(this, side)
    }
}