package net.cydhra.vibrant.organization.resources

import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.organization.GameResource
import net.cydhra.vibrant.organization.GameResourceState
import net.cydhra.vibrant.organization.channel.ChannelBuilder
import net.cydhra.vibrant.organization.channel.IResourceChannel

/**
 * This resource handles the player sprinting. This resource updates the sprint flag in client and on the server depending on modules'
 * demand.
 */
object SprintingResource : GameResource<SprintingResource.SprintResourceState>() {

    override fun register(): MutableMap<GameResource<*>, in IResourceChannel<*>>.() -> Unit = {
        this[this@SprintingResource] = ChannelBuilder
                .newBuilder { SprintResourceState(VibrantClient.minecraft.thePlayer?.isSprinting ?: false) }
                .create()
    }

    data class SprintResourceState(val isSprinting: Boolean = false) : GameResourceState()
}