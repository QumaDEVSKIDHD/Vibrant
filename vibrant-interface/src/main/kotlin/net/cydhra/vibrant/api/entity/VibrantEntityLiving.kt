package net.cydhra.vibrant.api.entity

/**
 *
 */
interface VibrantEntityLiving : VibrantEntity {

    val posX: Double
    val posY: Double
    val posZ: Double

    val rotationYaw: Float
    val rotationPitch: Float
}