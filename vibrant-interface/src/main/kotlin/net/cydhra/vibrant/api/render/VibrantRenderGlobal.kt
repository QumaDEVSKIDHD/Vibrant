package net.cydhra.vibrant.api.render

import net.cydhra.vibrant.api.entity.VibrantEntity

/**
 *
 */
interface VibrantRenderGlobal {

    fun setupTerrain(viewEntity: VibrantEntity, partialTicks: Float, frustum: VibrantFrustum, frameCount: Int)

    fun renderBlockLayer(type: VibrantBlockLayerType, viewEntity: VibrantEntity)

    enum class VibrantBlockLayerType {
        SOLID, TRANSLUCENT, CUTOUT, CUTOUT_MIPPED
    }
}