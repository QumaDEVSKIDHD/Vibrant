package net.cydhra.vibrant.util.math

import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.api.entity.VibrantEntityAlike
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.sin

/**
 * An angle in 3D space.
 */
class Angle(val yaw: Double, val pitch: Double) {
    val xCoord: Double = cos(yaw)
    val yCoord: Double = cos(pitch)
    val zCoord: Double = sin(yaw)

    /**
     * Angle defined by a direction vector coordinates in minecraft's cartesian coordinate system
     */
    constructor(xCoord: Double, yCoord: Double, zCoord: Double) : this(Vector(xCoord, yCoord, zCoord))

    /**
     * Angle defined by a direction vector in minecraft's cartesian coordinate system
     */
    constructor(cartesianDirection: Vector) : this(
            Math.toDegrees(asin(cartesianDirection.xCoord / cartesianDirection.horizontalLength()))
                    .let { if (cartesianDirection.zCoord > 0) 180 - it else it },
            Math.toDegrees(asin(cartesianDirection.yCoord / cartesianDirection.length())))

    /**
     * Angle of the player towards the given entity
     */
    constructor(entity: VibrantEntityAlike) : this(
            VibrantClient.minecraft.thePlayer!!.posX - entity.posX,
            VibrantClient.minecraft.thePlayer!!.posY - entity.posY,
            VibrantClient.minecraft.thePlayer!!.posZ - entity.posZ
    )
}