package net.cydhra.vibrant.api.gui

/**
 * This is the actual gui screen adapter.
 */
interface VibrantGuiController {
    fun drawRectWithCustomSizedTexture(x: Int, y: Int, u: Float, v: Float, width: Int, height: Int, textureWidth: Float, textureHeight: Float)

    fun actionPerformed(id: Int)
}