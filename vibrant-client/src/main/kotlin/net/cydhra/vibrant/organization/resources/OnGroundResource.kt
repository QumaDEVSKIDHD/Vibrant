package net.cydhra.vibrant.organization.resources

import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.organization.GameResource
import net.cydhra.vibrant.organization.channel.ChannelBuilder
import net.cydhra.vibrant.organization.channel.ResourceChannel
import net.cydhra.vibrant.organization.locks.Side

object OnGroundResource : GameResource<OnGroundResource.OnGroundResourceState>() {

    override val channel: ResourceChannel<GameResource<OnGroundResourceState>, OnGroundResourceState> =
            ChannelBuilder<GameResource<OnGroundResourceState>, OnGroundResourceState>(this,
                    { OnGroundResourceState(VibrantClient.minecraft.thePlayer!!.onGround) })
                    .create()

    override fun onUpdateState(side: Side, state: OnGroundResourceState) {
        if (side == Side.CLIENT) {
            VibrantClient.minecraft.thePlayer!!.onGround = state.onGround
        } else if (side == Side.SERVER) {
            TODO()
        }
    }

    class OnGroundResourceState(onGround: Boolean? = null) : GameResourceState() {
        val onGround by Partial(onGround)

        override fun generateEmptyState(): GameResourceState = OnGroundResourceState()
    }
}
