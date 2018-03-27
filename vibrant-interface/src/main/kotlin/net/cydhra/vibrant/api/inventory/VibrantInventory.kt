package net.cydhra.vibrant.api.inventory

import net.cydhra.vibrant.api.entity.VibrantPlayer
import net.cydhra.vibrant.api.item.VibrantItemStack

interface VibrantInventory {

    fun getSizeInventory(): Int

    fun getStackInSlot(index: Int): VibrantItemStack?

    fun decrStackSize(index: Int, count: Int): VibrantItemStack?

    fun removeStackFromSlot(index: Int): VibrantItemStack?

    fun setInventorySlotContent(index: Int, stack: VibrantItemStack)

    fun getInventoryStackLimit(): Int

    fun markDirty()

    fun canBeUsedByPlayer(player: VibrantPlayer): Boolean

    fun openInventoryToPlayer(player: VibrantPlayer)

    fun closeCurrentInventory(player: VibrantPlayer)

    fun isItemValidInSlot(index: Int, stack: VibrantItemStack): Boolean

    fun getField(id: Int): Int

    fun setField(id: Int, value: Int)

    fun getFieldCount(): Int

    fun clear()
}