package net.cydhra.vibrant.api.render

/**
 *
 */
interface VibrantEntityRenderer {
    var frameCount: Int

    fun setupOverlayRendering()
}