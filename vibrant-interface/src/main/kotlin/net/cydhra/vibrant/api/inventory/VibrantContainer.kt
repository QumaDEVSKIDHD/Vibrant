package net.cydhra.vibrant.api.inventory

interface VibrantContainer {

    val windowId: Int

    fun nextTransactionID(inventory: VibrantPlayerInventory): Short
}