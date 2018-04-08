package net.cydhra.vibrant.api.inventory

import net.cydhra.vibrant.api.entity.VibrantPlayer
import net.cydhra.vibrant.api.item.VibrantItemStack
import net.cydhra.vibrant.api.network.ClickType

interface VibrantContainer {

    val windowId: Int

    fun nextTransactionID(inventory: VibrantPlayerInventory): Short

    fun clickSlot(slotId: Int, clickedButton: Int, mode: ClickType, player: VibrantPlayer): VibrantItemStack
}