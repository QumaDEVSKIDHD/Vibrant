package net.cydhra.vibrant.api.util

interface VibrantBoundingBox {

    fun offset(posX: Double, posY: Double, posZ: Double): VibrantBoundingBox

}