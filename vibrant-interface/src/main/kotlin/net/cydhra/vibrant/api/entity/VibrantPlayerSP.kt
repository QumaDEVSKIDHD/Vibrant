package net.cydhra.vibrant.api.entity

import net.cydhra.vibrant.api.client.VibrantMovementInput
import net.cydhra.vibrant.api.item.VibrantItemStack

/**
 *
 */
interface VibrantPlayerSP : VibrantEntityLiving {

    var isAllowedFlying: Boolean

    var isFlying: Boolean

    var isSprinting: Boolean

    val movementInput: VibrantMovementInput

    fun getItemInUseCount(): Int

    fun getHeldItem(): VibrantItemStack?

    fun getEyeHeight(): Float
}