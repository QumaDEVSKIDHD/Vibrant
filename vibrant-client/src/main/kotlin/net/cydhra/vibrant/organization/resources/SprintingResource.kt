package net.cydhra.vibrant.organization.resources

import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.organization.GameResource
import net.cydhra.vibrant.organization.channel.ChannelBuilder
import net.cydhra.vibrant.organization.channel.ResourceChannel
import net.cydhra.vibrant.organization.locks.Side

/**
 * This resource handles the player sprinting. This resource updates the sprint flag in client and on the server depending on modules'
 * demand.
 */
object SprintingResource : GameResource<SprintingResource.SprintResourceState>() {

    override val channel: ResourceChannel<GameResource<SprintResourceState>, SprintResourceState> =
            ChannelBuilder<GameResource<SprintResourceState>, SprintResourceState>(this,
                    { SprintResourceState(VibrantClient.minecraft.thePlayer!!.isSprinting) })
                    .create()

    override fun onUpdateState(side: Side, state: SprintResourceState) {
        if (side == Side.CLIENT) {
            mc.thePlayer!!.isSprinting = state.isSprinting
        } else {
            TODO()
        }
    }

    class SprintResourceState(isSprinting: Boolean? = null) : GameResourceState() {
        val isSprinting by Partial(isSprinting)

        override fun generateEmptyState(): GameResourceState = SprintResourceState()
    }
}