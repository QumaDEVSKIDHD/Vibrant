package net.cydhra.vibrant.gui.renderer.impl

import net.cydhra.vibrant.gui.GuiManager
import net.cydhra.vibrant.gui.component.impl.VibrantTextbox
import net.cydhra.vibrant.gui.renderer.ComponentRenderer
import net.cydhra.vibrant.gui.theme.Theme
import net.cydhra.vibrant.gui.util.RenderUtil
import java.awt.Color


/**
 *
 */
open class VibrantTextboxRenderer<in C : VibrantTextbox> : ComponentRenderer<C> {

    override fun renderComponent(component: C, theme: Theme) {
        // draw box
        RenderUtil.fillRect(component.posX, component.posY, component.width.toInt(), component.height.toInt(), theme.primaryColor)
        RenderUtil.drawRect(component.posX, component.posY, component.width.toInt(), component.height.toInt(), theme.secondaryColor)

        // draw text
        val textPosY = component.posY + (component.height - GuiManager.fontRenderer.getStringHeight(component.text)) / 2
        renderContent(component, theme, textPosY.toFloat())

        // render marked area
        RenderUtil.fillRect(
                component.posX + VibrantTextbox.MARGIN_LEFT + getContentWidth(component, component.text.substring(0, component.cursorIndex)),
                (textPosY - 1).toInt(),
                getContentWidth(component, component.text.substring(component.cursorIndex, (component.cursorIndex + component
                        .markerLength))),
                GuiManager.fontRenderer.getStringHeight(component.text) + 2,
                Color(theme.tertiaryColor.red, theme.tertiaryColor.green, theme.tertiaryColor.blue, 100))

        // draw cursor
        if (component.isMouseOver && System.currentTimeMillis() / 500 % 2 == 0L) {
            val cursorX = (component.posX + VibrantTextbox.MARGIN_LEFT +
                    getContentWidth(component, component.text.substring(0, component.cursorIndex)) + 1)

            GuiManager.glStateManager.disableLineSmooth()
            RenderUtil.drawLine(cursorX, (textPosY - 1).toInt(), cursorX,
                    (textPosY + GuiManager.fontRenderer.getStringHeight(component.text) + 1).toInt(), theme.primaryTextColor)
            GuiManager.glStateManager.enableLineSmooth()
        }
    }

    open fun getContentWidth(component: C, content: String): Int {
        return GuiManager.fontRenderer.getStringWidth(content)
    }

    open fun renderContent(component: C, theme: Theme, posY: Float) {
        GuiManager.fontRenderer.drawString(component.text, component.posX.toFloat() + VibrantTextbox.MARGIN_LEFT, posY,
                theme.primaryTextColor.rgb)
    }

}