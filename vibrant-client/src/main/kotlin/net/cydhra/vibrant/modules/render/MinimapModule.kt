package net.cydhra.vibrant.modules.render

import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.api.entity.VibrantZombie
import net.cydhra.vibrant.api.render.VibrantRenderGlobal
import net.cydhra.vibrant.events.render.RenderOverlayEvent
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.settings.VibrantConfig
import net.cydhra.vibrant.settings.VibrantSettings
import net.cydhra.vibrant.util.render.RenderUtil
import net.cydhra.vibrant.util.render.StencilUtil
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.GL11
import java.awt.Color

/**
 *
 */
class MinimapModule : Module("Minimap", DefaultCategories.RENDER, Keyboard.KEY_NONE) {

    private val perspectiveAngle = VibrantSettings.registerConfig(VibrantConfig("Map Angle", 45, 40, 70, 1))
    private val mapRadius = VibrantSettings.registerConfig(VibrantConfig("Map Radius", 80, 50, 100, 1))
    private val showLines = VibrantSettings.registerConfig(VibrantConfig("Show Map Axis", true))
    private val lineWidth = VibrantSettings.registerConfig(VibrantConfig("Axis Strength", 0.2f, 0.1f, 1f, 0.05f))

    private var zombie: VibrantZombie? = null

    override fun onEnable() {
        this.zombie = mc.theWorld?.createZombie()
    }

    @EventHandler
    fun onRender2D(e: RenderOverlayEvent) {
        if (mc.thePlayer == null)
            return

        if (this.zombie == null)
            mc.theWorld!!.createZombie()

        mc.entityRenderer.setupOverlayRendering()

        RenderUtil.pushMatrix()
        val sc = mc.newScaledResolution()
        GL11.glTranslatef((sc.getScaledWidth() - mapRadius.value).toFloat(), (sc.getScaledHeight() - mapRadius.value).toFloat(), 0f)

        // prepare circular stencil
        StencilUtil.setupStencil()

        RenderUtil.disableDepthTest()
        RenderUtil.disableTexture2D()
        RenderUtil.enableColorBlending()
        RenderUtil.enableLineSmoothing()

        RenderUtil.fillCircle(0, 0, mapRadius.value, Color.BLACK)
        StencilUtil.enableStencil(StencilUtil.StencilMode.CROP_OUTSIDE)

        RenderUtil.enableTexture2D()
        RenderUtil.enableDepthTest()

        // flip and turn
        GL11.glScalef(1f, -1f, 1f)
        GL11.glRotatef(perspectiveAngle.value.toFloat(), 1f, 0f, 0f)
        GL11.glRotatef(mc.thePlayer!!.rotationYaw % 360 - 180, 0f, 1f, 0f)

        // prepare view frustum
        val frustum = mc.newFrustum()
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

        RenderUtil.disableTexture2D()
        RenderUtil.disableDepthTest()
        if (showLines.value) {
            RenderUtil.drawLine3d(-200.0, 1.0, 0.0, 200.0, 1.0, 0.0, Color.WHITE, lineWidth.value)
            RenderUtil.drawLine3d(0.0, 1.0, -200.0, 0.0, 1.0, 200.0, Color.WHITE, lineWidth.value)
        }
        RenderUtil.enableTexture2D()

        // end circular stencil
        StencilUtil.endStencil()

        RenderUtil.disableLineSmoothing()
        RenderUtil.enableDepthMask()
        RenderUtil.popMatrix()
    }
}