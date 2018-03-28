package net.cydhra.vibrant.api.inventory

import net.cydhra.vibrant.api.entity.VibrantPlayer
import net.cydhra.vibrant.api.item.VibrantItemStack
import net.cydhra.vibrant.api.util.VibrantDamageSource

const val MIN_HOTBAR_INVENTORY_SLOT = 0
const val MAX_HOTBAR_INVENTORY_SLOT = 8
const val MIN_MAIN_INVENTORY_SLOT = 0
const val MAX_MAIN_INVENTORY_SLOT = 36

const val ARMOR_HELMET_ID = 103
const val ARMOR_CHEST_ID = 102
const val ARMOR_LEG_ID = 101
const val ARMOR_BOOTS_ID = 100

const val DROP_ITEM_SLOT_ID = -999

const val MIN_CRAFTING_SLOTS_ID = 80
const val MAX_CRAFTING_SLOTS_ID = 83

interface VibrantInventory : Iterable<Pair<Int, VibrantItemStack?>> {

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

    fun getEnchantmentModifier(armor: Array<VibrantItemStack>, source: VibrantDamageSource)
}

class InventoryIterator(private val inventory: VibrantInventory) : MutableIterator<Pair<Int, VibrantItemStack?>> {
    private var currentIndex: Int = 0

    override fun hasNext(): Boolean {
        return currentIndex < this.inventory.getSizeInventory()
    }

    override fun next(): Pair<Int, VibrantItemStack?> {
        return Pair(currentIndex, inventory.getStackInSlot(currentIndex)).apply { currentIndex++ }
    }

    override fun remove() {
        TODO("not implemented")
    }

}