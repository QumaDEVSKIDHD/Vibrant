package net.cydhra.vibrant.api.entity

import net.cydhra.vibrant.api.util.VibrantBoundingBox

/**
 *
 */
interface VibrantEntity : VibrantEntityAlike {
    val prevPosX: Double
    val prevPosY: Double
    val prevPosZ: Double

    var motionX: Double
    var motionY: Double
    var motionZ: Double

    val rotationYaw: Float
    val rotationPitch: Float

    var onGround: Boolean

    var width: Float
    var height: Float

    val boundingBox: VibrantBoundingBox

    fun getDistanceSq(x: Double, y: Double, z: Double): Double
}