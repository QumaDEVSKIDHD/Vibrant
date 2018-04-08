package net.cydhra.vibrant.api.client

import net.cydhra.nidhogg.data.Session
import net.cydhra.vibrant.api.entity.VibrantEntity
import net.cydhra.vibrant.api.entity.VibrantPlayerSP
import net.cydhra.vibrant.api.render.*
import net.cydhra.vibrant.api.world.VibrantWorld
import net.cydhra.vibrant.interfaces.VibrantGuiScreen

/**
 *
 */
interface VibrantMinecraft {

    val thePlayer: VibrantPlayerSP?
    val playerController: VibrantPlayerController?

    val theWorld: VibrantWorld?
    val renderGlobal: VibrantRenderGlobal
    val entityRenderer: VibrantEntityRenderer

    var theRenderViewEntity: VibrantEntity

    val framebuffer: VibrantFramebuffer?

    val displayWidth: Int
    val displayHeight: Int

    val isCurrentlyDisplayingScreen: Boolean

    val timer: VibrantTimer

    val gameSettings: VibrantGameSettings
    var minecraftSession: Session

    val glStateManager: VibrantGlStateManager
    val tessellator: VibrantTessellator

    fun getTextureManager(): VibrantTextureManager

    fun getRenderManager(): VibrantRenderManager

    fun getRenderViewEntity(): VibrantEntity

    fun getTileEntityRenderDispatcher(): VibrantTileEntityRendererDispatcher

    fun displayGuiScreen(screen: VibrantGuiScreen?)
}