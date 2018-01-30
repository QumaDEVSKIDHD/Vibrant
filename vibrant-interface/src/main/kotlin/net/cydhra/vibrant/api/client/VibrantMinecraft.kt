package net.cydhra.vibrant.api.client

import net.cydhra.vibrant.api.entity.VibrantPlayerSP
import net.cydhra.vibrant.api.gui.VibrantGuiScreen
import net.cydhra.vibrant.api.render.VibrantEntityRenderer
import net.cydhra.vibrant.api.render.VibrantFramebuffer
import net.cydhra.vibrant.api.render.VibrantRenderGlobal
import net.cydhra.vibrant.api.render.VibrantTextureManager
import net.cydhra.vibrant.api.world.VibrantWorld

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

    fun getTextureManager(): VibrantTextureManager

    fun displayGuiScreen(screen: VibrantGuiScreen?)
}