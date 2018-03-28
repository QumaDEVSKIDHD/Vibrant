@file:Suppress("UNUSED_PARAMETER")

package net.cydhra.vibrant.modules.visual

import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.api.entity.VibrantEntity
import net.cydhra.vibrant.api.entity.VibrantEntityLiving
import net.cydhra.vibrant.events.render.RenderOverlayEvent
import net.cydhra.vibrant.events.render.RenderWorldEvent
import net.cydhra.vibrant.gui.font.VibrantFontRenderer
import net.cydhra.vibrant.gui.util.RenderUtil
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.Display
import org.lwjgl.util.vector.Vector3f
import java.awt.Font
import java.util.*

class NametagsModule : Module("Nametags", DefaultCategories.VISUAL, Keyboard.KEY_J) {
    private val font = Font("Arial", Font.BOLD, 18)
    private val nametagFontRenderer = VibrantFontRenderer(font)

    private val frustum = VibrantClient.factory.newFrustum()
    private val nametags = LinkedList<Pair<VibrantEntity, Vector3f>>()

    @EventHandler
    fun onRenderOverlay(e: RenderOverlayEvent) {
        for (nametag in nametags) {
            val x = nametag.second.x / 2
            val y = (Display.getHeight() - nametag.second.y) / 2

            var name = nametag.first.toString().substring(nametag.first.toString().indexOf('\'') + 1, nametag.first.toString().length)
            name = name.substring(0, name.indexOf('\''))

            nametagFontRenderer.drawString(name, x - nametagFontRenderer.getStringWidth(name) / 2, y, -1)
        }
    }

    @EventHandler
    fun onRenderWorld(e: RenderWorldEvent) {
        nametags.clear()

        for (en: VibrantEntityLiving in mc.theWorld!!.getEntityList().filterIsInstance<VibrantEntityLiving>()) {
            val px = RenderUtil.interpolate(mc.thePlayer!!.posX, mc.thePlayer!!.prevPosX, mc.timer.renderPartialTicks)
            val py = RenderUtil.interpolate(mc.thePlayer!!.posY, mc.thePlayer!!.prevPosY, mc.timer.renderPartialTicks)
            val pz = RenderUtil.interpolate(mc.thePlayer!!.posZ, mc.thePlayer!!.prevPosZ, mc.timer.renderPartialTicks)

            frustum.setPosition(px, py, pz)

            if (frustum.isBoundingBoxInsideFrustum(en.boundingBox)) {
                val x = RenderUtil.interpolate(en.posX, en.prevPosX, mc.timer.renderPartialTicks) - mc.getRenderManager().renderPosX
                val y = RenderUtil.interpolate(en.posY, en.prevPosY, mc.timer.renderPartialTicks) - mc.getRenderManager().renderPosY
                val z = RenderUtil.interpolate(en.posZ, en.prevPosZ, mc.timer.renderPartialTicks) - mc.getRenderManager().renderPosZ

                nametags.add(Pair(en, RenderUtil.project3d(x.toFloat(), y.toFloat() + en.height + 0.5F, z.toFloat())))
            }
        }
    }
}