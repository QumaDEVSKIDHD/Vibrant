package net.cydhra.vibrant.modules.system

import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.api.render.VibrantTextureManager
import net.cydhra.vibrant.events.render.RenderOverlayEvent
import net.cydhra.vibrant.gui.font.VibrantFontRenderer
import net.cydhra.vibrant.gui.util.GlStateManager
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.modulesystem.ModuleManager
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11.*
import java.awt.Font

class HudModule : Module("Hud", DefaultCategories.SYSTEM, Keyboard.KEY_H) {

    private val font = Font("Arial", Font.BOLD, 24)

    private val watermarkFontRenderer = VibrantFontRenderer(font)
    private val fontRenderer = VibrantFontRenderer(font.deriveFont(18F))

    init {
        isEnabled = true
    }

    @EventHandler
    fun onRenderOverlay(e: RenderOverlayEvent) {
        val watermark = "Vibrant " + VibrantClient.VERSION
        watermarkFontRenderer.drawString(watermark, 3F, 2.2F, -1)

        var underLineOffset = 2
        val underLineWidth = 4 + watermarkFontRenderer.getStringWidth(watermark)

        GlStateManager.disableTexture2D()

        glLineWidth(2F)

        glBegin(GL11.GL_LINES)
        glVertex2i(2, underLineOffset)
        glVertex2i(underLineWidth, underLineOffset)
        glEnd()

        underLineOffset += watermarkFontRenderer.fontHeight + 2

        glBegin(GL11.GL_LINES)
        glVertex2i(2, underLineOffset)
        glVertex2i(4 + watermarkFontRenderer.getStringWidth(watermark), underLineOffset)
        glEnd()

        glBegin(GL11.GL_LINE_STRIP)
        var offset = 0F
        for (module in ModuleManager.modules) {
            if (module.isEnabled && module.category != DefaultCategories.SYSTEM) {
                val incrementY = fontRenderer.getStringHeight(module.displayName) + 4F
                val x = e.scaledResolution.getScaledWidth() - fontRenderer.getStringWidth(module.displayName) - 2F
                val y = offset

                offset += incrementY

                glVertex2f(x - 1, y - 1)
                glVertex2f(x - 1, y + incrementY - 1)
            }
        }
        glVertex2f(e.scaledResolution.getScaledWidth().toFloat(), offset - 1)
        glEnd()

        GlStateManager.enableTexture2D()

        offset = 0F
        for (module in ModuleManager.modules) {
            if (module.isEnabled && module.category != DefaultCategories.SYSTEM) {
                fontRenderer.drawString(module.displayName, e.scaledResolution.getScaledWidth() - fontRenderer.getStringWidth(module.displayName) - 1F, offset, -1)
                offset += fontRenderer.fontHeight + 3
            }
        }
    }
}
