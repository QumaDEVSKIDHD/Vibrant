package net.cydhra.vibrant.api.util

/**
 *
 */
interface VibrantVec3 {

    val xCoord: Double
    val yCoord: Double
    val zCoord: Double

    fun lengthVector(): Double

    fun addVector(x: Double, y: Double, z: Double): VibrantVec3
}