package net.cydhra.vibrant.api.entity

import net.cydhra.vibrant.api.client.VibrantMovementInput

/**
 *
 */
interface VibrantPlayerSP : VibrantEntityLiving {

    var isAllowedFlying: Boolean

    var isFlying: Boolean

    var isSprinting: Boolean

    val movementInput: VibrantMovementInput
}