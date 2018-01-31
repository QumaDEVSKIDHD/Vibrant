package net.cydhra.vibrant.organization.resources

import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.organization.GameResource
import net.cydhra.vibrant.organization.GameResourceState
import net.cydhra.vibrant.organization.channel.ChannelBuilder
import net.cydhra.vibrant.organization.channel.IResourceChannel
import net.cydhra.vibrant.organization.channel.ResourceChannel

object NoFallResource : GameResource<NoFallResource.NoFallResourceState>() {

    override fun register(): MutableMap<GameResource<*>, in IResourceChannel<*>>.() -> Unit = {
        this[NoFallResource] = ChannelBuilder
                .newBuilder(
                        { NoFallResourceState(VibrantClient.minecraft.thePlayer?.onGround ?: false) },
                        { side, state ->
                            if (side == ResourceChannel.Side.CLIENT) {
                                VibrantClient.minecraft.thePlayer?.onGround = state.onGround
                                println("${state.onGround} ${VibrantClient.minecraft.thePlayer?.onGround}")
                            } else {
                                //TODO
                            }
                        })
                .create()
    }

    data class NoFallResourceState(val onGround: Boolean = false) : GameResourceState()
}