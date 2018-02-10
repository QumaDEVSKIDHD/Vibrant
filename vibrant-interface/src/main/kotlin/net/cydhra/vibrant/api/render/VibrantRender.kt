package net.cydhra.vibrant.api.render

import net.cydhra.vibrant.api.entity.VibrantEntity

/**
 *
 */
interface VibrantRender<in T : VibrantEntity> {

    fun render(entity: T, x: Double, y: Double, z: Double, entityYaw: Float, partialTicks: Float)
}