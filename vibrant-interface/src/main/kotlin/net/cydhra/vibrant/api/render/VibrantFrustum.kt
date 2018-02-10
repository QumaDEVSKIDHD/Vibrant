package net.cydhra.vibrant.api.render

import net.cydhra.vibrant.api.util.VibrantBoundingBox

/**
 *
 */
interface VibrantFrustum {

    fun setPosition(posX: Double, posY: Double, posZ: Double)

    fun isBoundingBoxInFrustum(bounds: VibrantBoundingBox): Boolean

}