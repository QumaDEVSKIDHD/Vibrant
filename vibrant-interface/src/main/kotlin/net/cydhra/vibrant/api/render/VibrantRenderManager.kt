package net.cydhra.vibrant.api.render

import net.cydhra.vibrant.api.entity.VibrantEntity

interface VibrantRenderManager {
    var renderPosX: Double
    var renderPosY: Double
    var renderPosZ: Double

    fun renderEntitySimple(entity: VibrantEntity, partialTicks: Float, unused: Any?): Boolean

    fun <T : VibrantEntity> getEntityRenderObj(entity: T): VibrantRender<T>
}