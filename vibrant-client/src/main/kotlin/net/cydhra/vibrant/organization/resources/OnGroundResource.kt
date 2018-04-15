package net.cydhra.vibrant.organization.resources

import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.api.network.VibrantPlayerPacket
import net.cydhra.vibrant.organization.GameResource
import net.cydhra.vibrant.organization.channel.ChannelBuilder
import net.cydhra.vibrant.organization.channel.ResourceChannel
import net.cydhra.vibrant.organization.locks.Side
import net.cydhra.vibrant.organization.network.NetworkManager
import net.cydhra.vibrant.organization.network.PacketManipulation

object OnGroundResource : GameResource<OnGroundResource.OnGroundResourceState>() {

    override val channel: ResourceChannel<GameResource<OnGroundResourceState>, OnGroundResourceState> =
            ChannelBuilder<GameResource<OnGroundResourceState>, OnGroundResourceState>(this,
                    { OnGroundResourceState(VibrantClient.minecraft.thePlayer!!.onGround) })
                    .create()

    private val packetManipulation = OnGroundPacketManipulation()

    init {
        NetworkManager.registerPacketManipulation(packetManipulation)
    }

    override fun onUpdateState(side: Side, state: OnGroundResourceState) {
        if (side == Side.CLIENT) {
            VibrantClient.minecraft.thePlayer!!.onGround = state.onGround
        } else if (side == Side.SERVER) {
            packetManipulation.onGround = state.onGround
        }
    }

    class OnGroundResourceState(onGround: Boolean? = null) : GameResourceState() {
        val onGround by Partial(onGround)

        override fun generateEmptyState(): GameResourceState = OnGroundResourceState()
    }

    class OnGroundPacketManipulation : PacketManipulation<VibrantPlayerPacket>(VibrantPlayerPacket::class) {

        internal var onGround: Boolean? = null

        override fun manipulate(packet: VibrantPlayerPacket): VibrantPlayerPacket {
            if (onGround != null) {
                packet.onGround = onGround!!
            }

            return packet
        }
    }
}
