package net.cydhra.vibrant.api.render

import net.cydhra.vibrant.api.entity.VibrantEntity

/**
 *
 */
interface VibrantRenderGlobal {

    fun setupTerrain(viewEntity: VibrantEntity, partialTicks: Float, frustum: VibrantFrustum, frameCount: Int)
}