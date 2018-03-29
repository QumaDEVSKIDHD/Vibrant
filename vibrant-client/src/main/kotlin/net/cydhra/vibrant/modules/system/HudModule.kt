package net.cydhra.vibrant.modules.system

import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.api.render.VibrantScaledResolution
import net.cydhra.vibrant.events.render.RenderOverlayEvent
import net.cydhra.vibrant.gui.font.VibrantFontRenderer
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.modulesystem.ModuleManager
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11.*
import java.awt.Color
import java.awt.Font

class HudModule : Module("Hud", DefaultCategories.SYSTEM, Keyboard.KEY_H) {

    private val font = Font("Arial", Font.BOLD, 24)

    private val watermarkFontRenderer = VibrantFontRenderer(font)
    private val fontRenderer = VibrantFontRenderer(font.deriveFont(18F))

    private val guiController = VibrantClient.factory.newGuiController()

    init {
        isEnabled = true
    }

    @EventHandler
    fun onRenderOverlay(e: RenderOverlayEvent) {
        drawActiveModules(e.scaledResolution)
        drawWatermarkIcon()
    }

    fun drawWatermarkIcon() {
        val scale = 0.1

        val width = 519F
        val height = 494F

        val borderOffset = 15

        val col = Color.getHSBColor(1F, 0F, 1F)

        val alpha = 40

        mc.glStateManager.pushMatrix()

        glPushAttrib(GL11.GL_ENABLE_BIT)

        glEnable(GL_BLEND)

        glScaled(scale, scale, 0.0)

        mc.glStateManager.color(Color(col.red, col.green, col.blue, alpha))

        mc.getTextureManager().bindTexture("textures/vibrant.png")
        guiController.drawRectWithCustomSizedTexture(borderOffset, borderOffset, 0F, 0F, width.toInt(), height.toInt(), width, height)

        glPopAttrib()

        mc.glStateManager.popMatrix()
    }

    fun drawWatermarkText() {
        val watermark = "Vibrant " + VibrantClient.VERSION
        watermarkFontRenderer.drawString(watermark, 3F, 2.2F, -1)

        var underLineOffset = 2
        val underLineWidth = 4 + watermarkFontRenderer.getStringWidth(watermark)

        mc.glStateManager.disableTexture2D()

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
    }

    fun drawActiveModules(scaledResolution: VibrantScaledResolution) {
        mc.glStateManager.disableTexture2D()

        glLineWidth(1.0F)
        glBegin(GL11.GL_LINE_STRIP)
        var offset = 0F
        for (module in ModuleManager.modules) {
            if (module.isEnabled && module.category != DefaultCategories.SYSTEM) {
                val incrementY = fontRenderer.getStringHeight(module.displayName) + 4F
                val x = scaledResolution.getScaledWidth() - fontRenderer.getStringWidth(module.displayName) - 2F
                val y = offset

                offset += incrementY

                glVertex2f(x - 1, y - 1)
                glVertex2f(x - 1, y + incrementY - 1)
            }
        }
        glVertex2f(scaledResolution.getScaledWidth().toFloat(), offset - 1)
        glEnd()

        mc.glStateManager.enableTexture2D()

        offset = 0F
        for (module in ModuleManager.modules) {
            if (module.isEnabled && module.category != DefaultCategories.SYSTEM) {
                fontRenderer.drawString(module.displayName, scaledResolution.getScaledWidth() - fontRenderer.getStringWidth(module.displayName) - 1F, offset, -1)
                offset += fontRenderer.fontHeight + 3
            }
        }
    }
}
