package net.cydhra.vibrant.api.entity

import net.cydhra.vibrant.api.inventory.VibrantPlayerInventory

/**
 *
 */
interface VibrantPlayer : VibrantEntityLiving {

    val chasingPosX: Double
    val chasingPosY: Double
    val chasingPosZ: Double

    val playerInventory: VibrantPlayerInventory

}