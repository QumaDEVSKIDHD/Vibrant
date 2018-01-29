package net.cydhra.vibrant.organization.resources

import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.organization.GameResource
import net.cydhra.vibrant.organization.GameResourceState
import net.cydhra.vibrant.organization.channel.ChannelBuilder
import net.cydhra.vibrant.organization.channel.IResourceChannel
import net.cydhra.vibrant.organization.channel.ResourceChannel

/**
 *
 */
object FlyingResource : GameResource<FlyingResource.FlyingResourceState>() {

    override fun register(): MutableMap<GameResource<*>, in IResourceChannel<*>>.() -> Unit = {
        this[FlyingResource] = ChannelBuilder.newBuilder(
                {
                    FlyingResourceState(VibrantClient.minecraft.thePlayer?.isFlying ?: false,
                            VibrantClient.minecraft.thePlayer?.isAllowedFlying ?: false)
                },
                { side, state ->
                    if (side == ResourceChannel.Side.CLIENT) {
                        VibrantClient.minecraft.thePlayer?.isAllowedFlying = state.isAllowedFlying
                        VibrantClient.minecraft.thePlayer?.isFlying = state.isFlying
                    }
                })
                .convergent()
                .create()
    }

    data class FlyingResourceState(val isFlying: Boolean, val isAllowedFlying: Boolean) : GameResourceState()
}