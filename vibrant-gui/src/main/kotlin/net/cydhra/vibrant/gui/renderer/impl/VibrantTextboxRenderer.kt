package net.cydhra.vibrant.gui.renderer.impl

import net.cydhra.vibrant.gui.GuiManager
import net.cydhra.vibrant.gui.component.impl.VibrantTextbox
import net.cydhra.vibrant.gui.renderer.ComponentRenderer
import net.cydhra.vibrant.gui.theme.Theme
import net.cydhra.vibrant.gui.util.GlStateManager
import net.cydhra.vibrant.gui.util.RenderUtil
import java.awt.Color


/**
 *
 */
class VibrantTextboxRenderer : ComponentRenderer<VibrantTextbox> {

    override fun renderComponent(component: VibrantTextbox, theme: Theme) {
        // draw box
        RenderUtil.fillRect(component.posX, component.posY, component.width.toInt(), component.height.toInt(), theme.primaryColor)
        RenderUtil.drawRect(component.posX, component.posY, component.width.toInt(), component.height.toInt(), theme.secondaryColor)

        // draw text
        val textPosY = component.posY + (component.height - GuiManager.fontRenderer.getStringHeight(component.text)) / 2
        GuiManager.fontRenderer.drawString(component.text, component.posX + VibrantTextbox.MARGIN_LEFT, textPosY.toInt(),
                theme.primaryTextColor.rgb)

        // render marked area
        RenderUtil.fillRect(
                component.posX + VibrantTextbox.MARGIN_LEFT +
                        GuiManager.fontRenderer.getStringWidth(component.text.substring(0, component.cursorIndex)),
                (textPosY - 1).toInt(),
                GuiManager.fontRenderer.getStringWidth(component.text.substring(component.cursorIndex, (component.cursorIndex + component
                        .markerLength))),
                GuiManager.fontRenderer.getStringHeight(component.text) + 2,
                Color(theme.tertiaryColor.red, theme.tertiaryColor.green, theme.tertiaryColor.blue, 100))

        // draw cursor
        if (component.isMouseOver && System.currentTimeMillis() / 500 % 2 == 0L) {
            val cursorX = (component.posX + VibrantTextbox.MARGIN_LEFT +
                    GuiManager.fontRenderer.getStringWidth(component.text.substring(0, component.cursorIndex)) + 1)

            GlStateManager.disableLineSmoothing()
            RenderUtil.drawLine(cursorX, (textPosY - 1).toInt(), cursorX,
                    (textPosY + GuiManager.fontRenderer.getStringHeight(component.text) + 1).toInt(), theme.primaryTextColor)
            GlStateManager.enableLineSmoothing()
        }
    }

}