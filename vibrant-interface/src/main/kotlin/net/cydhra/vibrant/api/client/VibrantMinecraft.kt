package net.cydhra.vibrant.api.client

import net.cydhra.vibrant.api.entity.VibrantEntity
import net.cydhra.vibrant.api.entity.VibrantPlayerSP
import net.cydhra.vibrant.api.gui.VibrantGuiScreen
import net.cydhra.vibrant.api.render.*
import net.cydhra.vibrant.api.world.VibrantWorld

/**
 *
 */
interface VibrantMinecraft {

    val thePlayer: VibrantPlayerSP?
    val theWorld: VibrantWorld?
    val renderGlobal: VibrantRenderGlobal
    val entityRenderer: VibrantEntityRenderer

    var theRenderViewEntity: VibrantEntity

    val framebuffer: VibrantFramebuffer?

    val displayWidth: Int
    val displayHeight: Int

    val isCurrentlyDisplayingScreen: Boolean

    val timer: VibrantTimer

    fun getTextureManager(): VibrantTextureManager

    fun getRenderManager(): VibrantRenderManager

    fun getRenderViewEntity(): VibrantEntity

    fun displayGuiScreen(screen: VibrantGuiScreen?)
}