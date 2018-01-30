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
object YawPitchResource : GameResource<YawPitchResource.YawPitchState>() {

    override fun register(): MutableMap<GameResource<*>, in IResourceChannel<*>>.() -> Unit = {
        val mc = VibrantClient.minecraft

        this[this@YawPitchResource] = ChannelBuilder.newBuilder(
                {
                    YawPitchState(mc.thePlayer?.rotationYaw ?: 0f,
                            mc.thePlayer?.rotationPitch ?: 0f)
                },
                { side, state ->
                    if (side == ResourceChannel.Side.CLIENT) {
                        mc.thePlayer?.setPositionAndRotation(
                                mc.thePlayer!!.posX, mc.thePlayer!!.posY, mc.thePlayer!!.posZ, state.yaw, state.pitch)
                    } else {
                        // TODO
                    }
                })
                .create()
    }


    data class YawPitchState(val yaw: Float, val pitch: Float) : GameResourceState()
}