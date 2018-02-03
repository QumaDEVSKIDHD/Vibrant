package net.cydhra.vibrant.modules.visual

import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.api.entity.VibrantEntityLiving
import net.cydhra.vibrant.events.render.RenderWorldEvent
import net.cydhra.vibrant.gui.util.GlStateManager
import net.cydhra.vibrant.gui.util.RenderUtil
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11.*
import java.awt.Color

class NametagsModule : Module("Nametags", DefaultCategories.VISUAL, Keyboard.KEY_J) {

    @EventHandler
    fun onRenderWorld(e: RenderWorldEvent) {
        for (e: VibrantEntityLiving in mc.theWorld!!.getEntityList().filterIsInstance<VibrantEntityLiving>()) {
            glPushMatrix()
            val scale = e.getDistanceSq(mc.getRenderManager().renderPosX, mc.getRenderManager().renderPosY, mc.getRenderManager().renderPosZ)
            GlStateManager.disableTexture2D()
            GlStateManager.disableDepthTest()
            glTranslated(-mc.getRenderManager().renderPosX, -mc.getRenderManager().renderPosY, -mc.getRenderManager().renderPosZ)
            glTranslated(e.posX, e.posY + e.getEyeHeight() + 0.5F, e.posZ)
            GL11.glNormal3f(0.0f, 1.0f, 0.0f)
            glRotatef(-mc.thePlayer!!.rotationYaw, 0.0f, 1.0f, 0.0f)
            glRotatef(mc.thePlayer!!.rotationPitch, 1.0f, 0.0f, 0.0f)
            RenderUtil.fillRect(0, 0, 10, 5, Color.RED)
            GlStateManager.enableTexture2D()
            GlStateManager.enableDepthTest()
            glPopMatrix()
        }
    }
}