package net.cydhra.vibrant.modules.visual

import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.api.entity.VibrantZombie
import net.cydhra.vibrant.api.render.VibrantRenderGlobal
import net.cydhra.vibrant.events.render.RenderOverlayEvent
import net.cydhra.vibrant.gui.util.GlStateManager
import net.cydhra.vibrant.gui.util.RenderUtil
import net.cydhra.vibrant.gui.util.StencilUtil
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.settings.by
import net.cydhra.vibrant.settings.clamped
import net.cydhra.vibrant.settings.setting
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.GL11
import java.awt.Color

/**
 *
 */
class MinimapModule : Module("Minimap", DefaultCategories.VISUAL, Keyboard.KEY_Z) {

    private val perspectiveAngle by setting("Map Angle", 45) {
        clamped(40, 70)
        increment by 1
        decrement by -1
    }

    private val mapRadius by setting("Map Radius", 80) {
        clamped(50, 100)
        increment by 5
        decrement by -5
    }

    private val showLines by setting("Show Map Axis", true)

    private val lineWidth by setting("Axis Strength", 0.2) {
        clamped(0.1, 1.0)
        increment by 0.05
        decrement by -0.05
    }

    private var zombie: VibrantZombie? = null

    @EventHandler
    fun onRender2D(e: RenderOverlayEvent) {
        if (mc.thePlayer == null)
            return

        if (this.zombie == null)
            zombie = factory.createZombie(mc.theWorld!!)

        mc.entityRenderer.setupOverlayRendering()

        GlStateManager.pushState()
        GlStateManager.pushMatrix()

        val sc = factory.newScaledResolution()
        GL11.glTranslatef((sc.getScaledWidth() - mapRadius).toFloat(), (sc.getScaledHeight() - mapRadius).toFloat(), 0f)

        // prepare circular stencil
        StencilUtil.setupStencil(mc.framebuffer, mc.displayWidth, mc.displayHeight)

        GlStateManager.disableDepthTest()
        GlStateManager.disableTexture2D()
        GlStateManager.enableColorBlending()
        GlStateManager.enableLineSmoothing()

        RenderUtil.fillCircle(0, 0, mapRadius.toDouble(), Color.BLACK)
        StencilUtil.enableStencil(StencilUtil.StencilMode.CROP_OUTSIDE)

        GlStateManager.enableTexture2D()
        GlStateManager.enableDepthTest()

        // flip and turn
        GL11.glScalef(1f, -1f, 1f)
        GL11.glRotatef(perspectiveAngle.toFloat(), 1f, 0f, 0f)
        GL11.glRotatef(mc.thePlayer!!.rotationYaw % 360 - 180, 0f, 1f, 0f)

        // prepare view frustum
        val frustum = factory.newFrustum()
        zombie!!.setPositionAndRotation(
                mc.thePlayer!!.posX - mc.thePlayer!!.getLookVec().xCoord * 8,
                mc.thePlayer!!.posY + 10,
                mc.thePlayer!!.posZ - mc.thePlayer!!.getLookVec().zCoord * 8,
                mc.thePlayer!!.rotationYaw,
                45f
        )
        frustum.setPosition(zombie!!.posX, zombie!!.posY, zombie!!.posZ)

        // setup terrain with new frustum
        mc.renderGlobal.setupTerrain(mc.thePlayer!!, e.partialTicks, frustum, mc.entityRenderer.frameCount++)

        // bind block textures
        mc.getTextureManager().bindTexture("textures/atlas/blocks.png")

        // render world layers
        mc.renderGlobal.renderBlockLayer(VibrantRenderGlobal.VibrantBlockLayerType.SOLID, zombie!!)
        mc.renderGlobal.renderBlockLayer(VibrantRenderGlobal.VibrantBlockLayerType.TRANSLUCENT, zombie!!)
        mc.renderGlobal.renderBlockLayer(VibrantRenderGlobal.VibrantBlockLayerType.CUTOUT_MIPPED, zombie!!)
        mc.renderGlobal.renderBlockLayer(VibrantRenderGlobal.VibrantBlockLayerType.CUTOUT, zombie!!)

        GlStateManager.disableTexture2D()
        GlStateManager.disableDepthTest()
        if (showLines) {
            RenderUtil.drawLine3d(-200.0, 1.0, 0.0, 200.0, 1.0, 0.0, Color.WHITE, lineWidth.toFloat())
            RenderUtil.drawLine3d(0.0, 1.0, -200.0, 0.0, 1.0, 200.0, Color.WHITE, lineWidth.toFloat())
        }
        GlStateManager.enableTexture2D()

        // end circular stencil
        StencilUtil.endStencil()

        GlStateManager.disableLineSmoothing()
        GlStateManager.enableDepthMask()
        GlStateManager.popMatrix()
        GlStateManager.popState()
    }
}