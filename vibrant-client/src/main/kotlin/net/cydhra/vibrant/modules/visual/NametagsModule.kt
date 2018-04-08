package net.cydhra.vibrant.modules.visual

import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.api.entity.VibrantEntity
import net.cydhra.vibrant.api.entity.VibrantEntityLiving
import net.cydhra.vibrant.api.entity.VibrantPlayerSP
import net.cydhra.vibrant.events.render.RenderOverlayEvent
import net.cydhra.vibrant.events.render.RenderWorldEvent
import net.cydhra.vibrant.gui.font.VibrantFontRenderer
import net.cydhra.vibrant.gui.util.RenderUtil
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import org.lwjgl.input.Keyboard
import org.lwjgl.util.vector.Vector3f
import java.awt.Font
import java.util.*

class NametagsModule : Module("Nametags", DefaultCategories.VISUAL, Keyboard.KEY_J) {
    private val fontRenderer = VibrantFontRenderer(Font("Arial", Font.BOLD, 18))

    private val frustum = VibrantClient.factory.newFrustum()
    private val nametags = LinkedList<Pair<VibrantEntity, Vector3f>>()

    @EventHandler
    fun onRenderOverlay(e: RenderOverlayEvent) {
        nametags.forEach { fontRenderer.drawStringWithShadow(it.first.getName(), it.second.x - fontRenderer.getStringWidth(it.first.getName()) / 2, it.second.y, -1) }
    }

    @EventHandler
    fun onRenderWorld(e: RenderWorldEvent) {
        nametags.clear()

        for (en: VibrantEntityLiving in mc.theWorld!!.getEntityList().filterIsInstance<VibrantEntityLiving>().filterNot { it is VibrantPlayerSP }) {
            val px = RenderUtil.interpolate(mc.thePlayer!!.posX, mc.thePlayer!!.prevPosX, mc.timer.renderPartialTicks)
            val py = RenderUtil.interpolate(mc.thePlayer!!.posY, mc.thePlayer!!.prevPosY, mc.timer.renderPartialTicks)
            val pz = RenderUtil.interpolate(mc.thePlayer!!.posZ, mc.thePlayer!!.prevPosZ, mc.timer.renderPartialTicks)

            frustum.setPosition(px, py, pz)

            if (frustum.isBoundingBoxInsideFrustum(en.boundingBox)) {
                val x = (RenderUtil.interpolate(en.posX, en.prevPosX, mc.timer.renderPartialTicks) - mc.getRenderManager().renderPosX).toFloat()
                val y = (RenderUtil.interpolate(en.posY, en.prevPosY, mc.timer.renderPartialTicks) - mc.getRenderManager().renderPosY).toFloat()
                val z = (RenderUtil.interpolate(en.posZ, en.prevPosZ, mc.timer.renderPartialTicks) - mc.getRenderManager().renderPosZ).toFloat()

                nametags.add(Pair(en, RenderUtil.project3d(x, y + en.height + 0.5F, z, VibrantClient.factory.newScaledResolution().getScaleFactor())))
            }
        }
    }
}