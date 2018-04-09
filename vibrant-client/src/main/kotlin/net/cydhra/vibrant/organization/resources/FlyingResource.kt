package net.cydhra.vibrant.organization.resources

import net.cydhra.vibrant.organization.GameResource
import net.cydhra.vibrant.organization.channel.ResourceChannel

/**
 *
 */
object FlyingResource : GameResource<FlyingResource.FlyingResourceState>() {

    override fun register():
            MutableMap<GameResource<*>, in ResourceChannel<*, FlyingResource.FlyingResourceState>>.() -> Unit = {
        TODO()
    }

    class FlyingResourceState(val isFlying: Partial<Boolean>, val isAllowedFlying: Partial<Boolean>) : GameResourceState()
}