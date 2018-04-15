package net.cydhra.vibrant.organization.resources

import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.api.network.VibrantPlayerLookPacket
import net.cydhra.vibrant.api.network.VibrantPlayerPacket
import net.cydhra.vibrant.api.network.VibrantPlayerPosLookPacket
import net.cydhra.vibrant.organization.GameResource
import net.cydhra.vibrant.organization.channel.ChannelBuilder
import net.cydhra.vibrant.organization.channel.ResourceChannel
import net.cydhra.vibrant.organization.locks.Side
import net.cydhra.vibrant.organization.network.NetworkManager
import net.cydhra.vibrant.organization.network.PacketManipulation

/**
 *
 */
object RotationResource : GameResource<RotationResource.RotationState>() {

    override val channel: ResourceChannel<GameResource<RotationState>, RotationState> =
            ChannelBuilder<GameResource<RotationState>, RotationState>(this,
                    {
                        RotationState(VibrantClient.minecraft.thePlayer!!.rotationYaw,
                                VibrantClient.minecraft.thePlayer!!.rotationPitch
                        )
                    })
                    .create()

    private val packetManipulation = RotationPacketManipulation()

    init {
        NetworkManager.registerPacketManipulation(packetManipulation)
    }

    override fun onUpdateState(side: Side, state: RotationState) {
        if (side == Side.CLIENT) {
            mc.thePlayer!!.setPositionAndRotation(
                    mc.thePlayer!!.posX, mc.thePlayer!!.posY, mc.thePlayer!!.posZ, state.yaw, state.pitch
            )
        } else if (side == Side.SERVER) {
            packetManipulation.rotationState = state
            packetManipulation.isDirty = true
        }
    }

    /**
     * Player's head rotation in polar coordinates
     */
    class RotationState(yaw: Float? = null, pitch: Float? = null) : GameResourceState() {
        val yaw by Partial(yaw)
        val pitch by Partial(pitch)

        override fun generateEmptyState(): GameResourceState = RotationState()
    }

    /**
     * Manipulates packets in the [NetworkManager] in order to keep the correct rotation state on the server
     */
    class RotationPacketManipulation : PacketManipulation<VibrantPlayerPacket>(VibrantPlayerPacket::class) {

        internal var rotationState: RotationState? = null

        // if the rotation state changed lately, we must force update it on the server. Otherwise, we only need to alter rotation packets
        internal var isDirty: Boolean = false

        override fun manipulate(packet: VibrantPlayerPacket): VibrantPlayerPacket {
            if (rotationState == null)
                return packet

            try {
                when {
                    packet is VibrantPlayerLookPacket -> {
                        packet.yaw = rotationState!!.yaw
                        packet.pitch = rotationState!!.pitch
                        return packet
                    }
                    packet is VibrantPlayerPosLookPacket -> {
                        packet.yaw = rotationState!!.yaw
                        packet.pitch = rotationState!!.pitch
                        return packet
                    }
                    this.isDirty -> {
                        return VibrantClient.factory.newPlayerLookPacket(rotationState!!.yaw, rotationState!!.pitch, packet.onGround)
                    }
                    else -> return packet
                }
            } finally {
                this.isDirty = false
            }
        }
    }
}