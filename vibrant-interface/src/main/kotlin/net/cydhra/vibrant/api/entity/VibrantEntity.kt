package net.cydhra.vibrant.api.entity

/**
 *
 */
interface VibrantEntity {
    var onGround: Boolean

    fun getDistanceSq(x: Double, y: Double, z: Double): Double
}