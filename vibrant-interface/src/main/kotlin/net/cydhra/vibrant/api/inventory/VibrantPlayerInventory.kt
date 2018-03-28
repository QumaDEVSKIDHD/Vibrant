package net.cydhra.vibrant.api.inventory

import net.cydhra.vibrant.api.item.VibrantItemStack


interface VibrantPlayerInventory : VibrantInventory {

    fun getArmorInventory(): Array<VibrantItemStack>
}