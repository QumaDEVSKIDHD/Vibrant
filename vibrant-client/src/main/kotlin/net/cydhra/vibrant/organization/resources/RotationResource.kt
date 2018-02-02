package net.cydhra.vibrant.organization.resources

import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.api.network.VibrantPlayerPacket
import net.cydhra.vibrant.api.network.VibrantPlayerPosLookPacket
import net.cydhra.vibrant.api.network.VibrantPlayerPosPacket
import net.cydhra.vibrant.organization.GameResource
import net.cydhra.vibrant.organization.GameResourceManager
import net.cydhra.vibrant.organization.GameResourceState
import net.cydhra.vibrant.organization.channel.ChannelBuilder
import net.cydhra.vibrant.organization.channel.IResourceChannel
import net.cydhra.vibrant.organization.channel.ResourceChannel

/**
 *
 */
object RotationResource : GameResource<RotationResource.RotationState>() {

    override fun register(): MutableMap<GameResource<*>, in IResourceChannel<*>>.() -> Unit = {
        val mc = VibrantClient.minecraft

        this[RotationResource] = ChannelBuilder.newBuilder(
                {
                    RotationState(mc.thePlayer?.rotationYaw ?: 0f,
                            mc.thePlayer?.rotationPitch ?: 0f)
                },
                { side, state ->
                    if (side == ResourceChannel.Side.CLIENT) {
                        mc.thePlayer?.setPositionAndRotation(
                                mc.thePlayer!!.posX, mc.thePlayer!!.posY, mc.thePlayer!!.posZ, state.yaw, state.pitch)
                    }
                })
                .create()

        GameResourceManager.registerPacketManipulation(RotationResource) { packet, state: RotationState ->
            if (packet is VibrantPlayerPosPacket) {
                VibrantClient.factory.newPlayerPosLookPacket(packet.posX, packet.posY, packet.posZ, state.yaw, state.pitch, packet.onGround)
            } else if (packet is VibrantPlayerPosLookPacket) {
                VibrantClient.factory.newPlayerPosLookPacket(packet.posX, packet.posY, packet.posZ, state.yaw, state.pitch, packet.onGround)
            } else if (packet is VibrantPlayerPacket) {
                VibrantClient.factory.newPlayerLookPacket(state.yaw, state.pitch, packet.onGround)
            } else {
                packet
            }
        }
    }

    data class RotationState(val yaw: Float, val pitch: Float) : GameResourceState()
}