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

    fun setPosition(posX: Double, posY: Double, posZ: Double)

    fun setPositionAndRotation(posX: Double, posY: Double, posZ: Double, yaw: Float, pitch: Float)

    fun getLookVec(): VibrantVec3
}