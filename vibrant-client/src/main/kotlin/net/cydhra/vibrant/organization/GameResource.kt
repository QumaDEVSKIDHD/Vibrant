package net.cydhra.vibrant.organization

import net.cydhra.vibrant.organization.resources.GameResourceState

/**
 *
 */
abstract class GameResource<out S : GameResourceState> {

    /**
     * Register a [ResourceChannel] in a map of game resources with this instance as key. The channel shall not be exposed and therefore,
     * this function returns a lambda with the map as receiver instead of the channel.
     */
    abstract fun register(): MutableMap<GameResource<*>, in IResourceChannel<*>>.() -> Unit
}