package net.cydhra.vibrant.organization.resources

import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.organization.GameResource
import net.cydhra.vibrant.organization.channel.ChannelBuilder
import net.cydhra.vibrant.organization.channel.ResourceChannel
import net.cydhra.vibrant.organization.locks.Side

/**
 *
 */
object FlyingResource : GameResource<FlyingResource.FlyingResourceState>() {

    override val channel: ResourceChannel<GameResource<FlyingResourceState>, FlyingResourceState> =
            ChannelBuilder<GameResource<FlyingResourceState>, FlyingResourceState>(this,
                    {
                        FlyingResourceState(VibrantClient.minecraft.thePlayer!!.isFlying,
                                VibrantClient.minecraft.thePlayer!!.isAllowedFlying)
                    })
                    .symmetric()
                    .create()

    override fun onUpdateState(side: Side, state: FlyingResourceState) {
        mc.thePlayer!!.isAllowedFlying = state.isAllowedFlying
        mc.thePlayer!!.isFlying = state.isFlying
    }

    class FlyingResourceState(isFlying: Boolean? = null, isAllowedFlying: Boolean? = null) : GameResourceState() {
        val isFlying by Partial(isFlying)
        val isAllowedFlying by Partial(isAllowedFlying)

        override fun generateEmptyState(): GameResourceState = FlyingResourceState()
    }
}