package net.cydhra.vibrant.api.entity

import net.cydhra.vibrant.api.util.VibrantDamageSource
import net.cydhra.vibrant.api.util.VibrantVec3

/**
 *
 */
interface VibrantEntityLiving : VibrantEntity {

    val isCollidedHorizontally: Boolean
    val isCollidedVertically: Boolean

    fun setPosition(posX: Double, posY: Double, posZ: Double)

    fun setPositionAndRotation(posX: Double, posY: Double, posZ: Double, yaw: Float, pitch: Float)

    fun getLookVec(): VibrantVec3

    fun getPositionVector(): VibrantVec3

    fun getEyeHeight(): Float

    fun getDamageSource(): VibrantDamageSource
}