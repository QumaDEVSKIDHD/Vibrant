package net.cydhra.vibrant.organization.resources

import net.cydhra.vibrant.organization.GameResource
import net.cydhra.vibrant.organization.channel.ChannelBuilder
import net.cydhra.vibrant.organization.channel.ResourceChannel
import net.cydhra.vibrant.organization.locks.Side

object InventoryOpenResource : GameResource<InventoryOpenResource.InventoryOpenResourceState>() {

    override val channel: ResourceChannel<GameResource<InventoryOpenResourceState>, InventoryOpenResourceState> =
            ChannelBuilder<GameResource<InventoryOpenResource.InventoryOpenResourceState>, InventoryOpenResourceState>(this,
                    { TODO("not implemented") })
                    .create()

    override fun onUpdateState(side: Side, state: InventoryOpenResourceState) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class InventoryOpenResourceState(open: Boolean? = null) : GameResourceState() {
        val isOpen by Partial(open)

        override fun generateEmptyState(): GameResourceState = InventoryOpenResourceState()
    }
}