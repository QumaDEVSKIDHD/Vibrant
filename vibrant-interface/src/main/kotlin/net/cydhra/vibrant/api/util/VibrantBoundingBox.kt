package net.cydhra.vibrant.api.util

interface VibrantBoundingBox {

    val minX: Double
    val maxX: Double

    val minY: Double
    val maxY: Double

    val minZ: Double
    val maxZ: Double

    fun offset(posX: Double, posY: Double, posZ: Double): VibrantBoundingBox

}