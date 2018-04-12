package net.cydhra.vibrant.organization.resources

import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.organization.GameResource
import net.cydhra.vibrant.organization.channel.ChannelBuilder
import net.cydhra.vibrant.organization.channel.ResourceChannel
import net.cydhra.vibrant.organization.locks.Side

/**
 *
 */
object RotationResource : GameResource<RotationResource.RotationState>() {

    override val channel: ResourceChannel<GameResource<RotationState>, RotationState> =
            ChannelBuilder<GameResource<RotationState>, RotationState>(this,
                    {
                        RotationState(VibrantClient.minecraft.thePlayer!!.rotationYaw,
                                VibrantClient.minecraft.thePlayer!!.rotationPitch)
                    })
                    .create()

    override fun onUpdateState(side: Side, state: RotationState) {
        if (side == Side.CLIENT) {
            mc.thePlayer!!.setPositionAndRotation(
                    mc.thePlayer!!.posX, mc.thePlayer!!.posY, mc.thePlayer!!.posZ, state.yaw, state.pitch)
        }
    }

    class RotationState(yaw: Float? = null, pitch: Float? = null) : GameResourceState() {
        val yaw by Partial(yaw)
        val pitch by Partial(pitch)

        override fun generateEmptyState(): GameResourceState = RotationState()
    }
}