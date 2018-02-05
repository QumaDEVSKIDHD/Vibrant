package net.cydhra.vibrant.modules.visual

import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.api.entity.VibrantEntityLiving
import net.cydhra.vibrant.api.entity.VibrantPlayerSP
import net.cydhra.vibrant.events.render.RenderWorldEvent
import net.cydhra.vibrant.gui.font.VibrantFontRenderer
import net.cydhra.vibrant.gui.util.GlStateManager
import net.cydhra.vibrant.gui.util.RenderUtil
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11.*
import java.awt.Color
import java.awt.Font

class NametagsModule : Module("Nametags", DefaultCategories.VISUAL, Keyboard.KEY_J) {
    private val font = Font("Arial", Font.BOLD, 40)
    private val nametagFontRenderer = VibrantFontRenderer(font)

    @EventHandler
    fun onRenderWorld(e: RenderWorldEvent) {
        for (en: VibrantEntityLiving in mc.theWorld!!.getEntityList().filterIsInstance<VibrantEntityLiving>()) {
            if (en is VibrantPlayerSP)
                continue

            val distance = en.getDistanceSq(mc.thePlayer!!.posX, mc.thePlayer!!.posY, mc.thePlayer!!.posZ)
            if (distance > 100 * 100)
                continue

            glPushMatrix()

            GlStateManager.disableTexture2D()
            GlStateManager.disableDepthTest()

            var scale = 0.0272

            val x = RenderUtil.interpolate(en.posX, en.prevPosX, mc.timer.renderPartialTicks) - mc.getRenderManager().renderPosX
            val y = RenderUtil.interpolate(en.posY, en.prevPosY, mc.timer.renderPartialTicks) - mc.getRenderManager().renderPosY
            val z = RenderUtil.interpolate(en.posZ, en.prevPosZ, mc.timer.renderPartialTicks) - mc.getRenderManager().renderPosZ

            glTranslated(x, y + en.getEyeHeight() + 0.5, z)
            GL11.glNormal3i(0, 1, 0)

            glRotatef(-mc.thePlayer!!.rotationYaw, 0F, 1F, 0F)
            glRotatef(mc.thePlayer!!.rotationPitch, 1F, 0F, 0F)

            glScaled(-scale, -scale, scale)

            glLineWidth(1F)

            var name = en.toString().substring(en.toString().indexOf('\'') + 1, en.toString().length)
            name = name.substring(0, name.indexOf('\''))

            val width = nametagFontRenderer.getStringWidth(name)

            glEnable(GL_BLEND)

            val maxDistance = 10.0

            scale = Math.min(Math.max((distance / (maxDistance * maxDistance)) % (maxDistance * maxDistance), 1.0), maxDistance)
            glScaled(scale, scale, 0.0)

            RenderUtil.fillRect(-width / 2 - 2, 0, width + 2, nametagFontRenderer.fontHeight / 2, Color(255, 255, 255, 100))

            glScaled(0.5, 0.5, 0.0)

            nametagFontRenderer.drawString(name, (-width / 2 - 2).toFloat(), -2F, -1)

            GlStateManager.enableTexture2D()
            GlStateManager.enableDepthTest()

            glPopMatrix()
        }
    }
}