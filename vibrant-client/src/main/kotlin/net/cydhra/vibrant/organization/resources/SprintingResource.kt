package net.cydhra.vibrant.organization.resources

import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.organization.GameResource

/**
 * This resource handles the player sprinting. This resource updates the sprint flag in client and on the server depending on modules'
 * demand.
 */
object SprintingResource : GameResource<SprintingResource.SprintResourceState>() {

    override fun register(): MutableMap<GameResource<*>, in IResourceChannel<*>>.() -> Unit = {
        this[SprintingResource] = ChannelBuilder
                .newBuilder(
                        { SprintResourceState(VibrantClient.minecraft.thePlayer?.isSprinting ?: false) },
                        { side, state ->
                            if (side == ResourceChannel.Side.CLIENT) // ignore server side since it is the same
                                VibrantClient.minecraft.thePlayer?.isSprinting = state.isSprinting
                        })
                .convergent()
                .create()
    }

    data class SprintResourceState(val isSprinting: Boolean = false) : GameResourceState()
}