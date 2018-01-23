package net.cydhra.vibrant.api.client

import net.cydhra.vibrant.api.entity.VibrantPlayerSP
import net.cydhra.vibrant.api.gui.VibrantGuiScreen
import net.cydhra.vibrant.api.render.*
import net.cydhra.vibrant.api.world.VibrantWorld
import java.awt.image.BufferedImage

/**
 *
 */
interface VibrantMinecraft {

    val thePlayer: VibrantPlayerSP?
    val theWorld: VibrantWorld?
    val renderGlobal: VibrantRenderGlobal
    val entityRenderer: VibrantEntityRenderer

    val framebuffer: VibrantFramebuffer?

    val displayWidth: Int
    val displayHeight: Int

    val isCurrentlyDisplayingScreen: Boolean

    fun newScaledResolution(): VibrantScaledResolution

    fun newFrustum(): VibrantFrustum

    fun newDynamicTexture(bufferedImage: BufferedImage): VibrantDynamicTexture

    fun newDynamicTexture(width: Int, height: Int): VibrantDynamicTexture

    fun getTextureManager(): VibrantTextureManager

    fun displayGuiScreen(screen: VibrantGuiScreen?)
}