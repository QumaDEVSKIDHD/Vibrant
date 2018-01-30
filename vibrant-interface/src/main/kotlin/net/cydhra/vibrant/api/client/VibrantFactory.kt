package net.cydhra.vibrant.api.client

import net.cydhra.vibrant.api.render.VibrantDynamicTexture
import net.cydhra.vibrant.api.render.VibrantFrustum
import net.cydhra.vibrant.api.render.VibrantScaledResolution
import java.awt.image.BufferedImage

/**
 *
 */
interface VibrantFactory {
    fun newScaledResolution(): VibrantScaledResolution

    fun newFrustum(): VibrantFrustum

    fun newDynamicTexture(bufferedImage: BufferedImage): VibrantDynamicTexture

    fun newDynamicTexture(width: Int, height: Int): VibrantDynamicTexture
}