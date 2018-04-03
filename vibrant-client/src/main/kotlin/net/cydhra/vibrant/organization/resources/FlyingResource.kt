package net.cydhra.vibrant.organization.resources

import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.organization.GameResource

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

    class FlyingResourceState(val isFlying: Boolean, val isAllowedFlying: Boolean) : GameResourceState()
}