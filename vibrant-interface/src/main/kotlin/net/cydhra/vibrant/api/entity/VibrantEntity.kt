package net.cydhra.vibrant.api.entity

/**
 *
 */
interface VibrantEntity {
    val posX: Double
    val posY: Double
    val posZ: Double

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

    fun getDistanceSq(x: Double, y: Double, z: Double): Double
}