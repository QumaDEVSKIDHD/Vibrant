package net.cydhra.vibrant.util.math

import net.cydhra.vibrant.api.entity.VibrantEntityAlike

/**
 * An immutable vector in cartesian 3D space.
 */
class Vector(val xCoord: Double, val yCoord: Double, val zCoord: Double) {

    companion object {
        fun between(entity1: VibrantEntityAlike, entity2: VibrantEntityAlike): Vector {
            return Vector(entity2.posX - entity1.posX, entity2.posY - entity1.posY, entity2.posZ - entity1.posZ)
        }
    }

    /**
     * @return the length of this vector
     */
    fun length(): Double {
        return Math.sqrt(xCoord * xCoord + yCoord * yCoord + zCoord * zCoord)
    }

    /**
     * @return the length of this vector in the x-z-plane
     */
    fun horizontalLength(): Double {
        return Math.sqrt(xCoord * xCoord + zCoord * zCoord)
    }

    /**
     * @return a normalized version of this vector
     */
    fun normalize(): Vector {
        val length = length()
        return Vector(xCoord / length, yCoord / length, zCoord / length)
    }
}