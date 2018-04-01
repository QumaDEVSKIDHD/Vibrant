package net.cydhra.vibrant.organization.resources

import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.api.network.VibrantPlayerPacket
import net.cydhra.vibrant.organization.GameResource
import net.cydhra.vibrant.organization.GameResourceManager
import net.cydhra.vibrant.organization.GameResourceState
import net.cydhra.vibrant.organization.channel.ChannelBuilder
import net.cydhra.vibrant.organization.channel.IResourceChannel
import net.cydhra.vibrant.organization.channel.ResourceChannel

object OnGroundResource : GameResource<OnGroundResource.OnGroundResourceState>() {

    override fun register(): MutableMap<GameResource<*>, in IResourceChannel<*>>.() -> Unit = {
        this[OnGroundResource] = ChannelBuilder
                .newBuilder(
                        { OnGroundResourceState(VibrantClient.minecraft.thePlayer?.onGround ?: false) },
                        { side, state ->
                            if (side == ResourceChannel.Side.CLIENT) {
                                VibrantClient.minecraft.thePlayer?.onGround = state.onGround
                            }
                        })
                .create()

        GameResourceManager.registerPacketManipulation(OnGroundResource) { it, state: OnGroundResourceState ->
            it.takeIf { it is VibrantPlayerPacket }?.apply { (it as VibrantPlayerPacket).onGround = state.onGround } ?: it
        }
    }

    data class OnGroundResourceState(val onGround: Boolean = false) : GameResourceState()
}