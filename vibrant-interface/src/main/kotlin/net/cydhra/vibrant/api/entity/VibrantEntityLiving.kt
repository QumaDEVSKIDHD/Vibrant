package net.cydhra.vibrant.api.entity

import net.cydhra.vibrant.api.util.VibrantVec3

/**
 *
 */
interface VibrantEntityLiving : VibrantEntity {

    val posX: Double
    val posY: Double
    val posZ: Double

    val rotationYaw: Float
    val rotationPitch: Float

    val isCollidedHorizontally: Boolean
    val isCollidedVertically: Boolean

    var motionX: Double
    var motionY: Double
    var motionZ: Double

    fun setPosition(posX: Double, posY: Double, posZ: Double)

    fun setPositionAndRotation(posX: Double, posY: Double, posZ: Double, yaw: Float, pitch: Float)

    fun getLookVec(): VibrantVec3

    fun getPositionVector(): VibrantVec3

    fun getEyeHeight(): Float
}