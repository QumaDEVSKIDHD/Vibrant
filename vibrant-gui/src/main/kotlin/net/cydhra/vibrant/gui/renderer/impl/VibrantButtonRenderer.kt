package net.cydhra.vibrant.gui.renderer.impl

import net.cydhra.vibrant.gui.GuiManager
import net.cydhra.vibrant.gui.component.IButton
import net.cydhra.vibrant.gui.renderer.ComponentRenderer
import net.cydhra.vibrant.gui.theme.Theme
import net.cydhra.vibrant.gui.util.RenderUtil
import org.lwjgl.opengl.GL11
import java.awt.Color

/**
 *
 */
class VibrantButtonRenderer : ComponentRenderer<IButton> {

    override fun renderComponent(component: IButton, theme: Theme) {
        val innerColor = if (component.isMouseOver) {
            theme.highlightColor(theme.primaryColor)
        } else {
            theme.primaryColor
        }
                .let {
                    if (component.rendererState == null) {
                        component.rendererState = 20
                    }

                    if (!component.isMouseOver && component.rendererState as Int >= 20) {
                        component.rendererState = Math.max(20, component.rendererState as Int - 10)
                    } else if ((component.rendererState as Int) < it.alpha) {
                        component.rendererState = Math.min(it.alpha, (component.rendererState as Int) + 10)
                    }

                    Color(it.red, it.green, it.blue, component.rendererState as Int)
                }

        val outerColor = if (component.isMouseOver) {
            theme.highlightColor(theme.secondaryColor)
        } else {
            theme.secondaryColor
        }

        GuiManager.glStateManager.pushMatrix()
        GL11.glTranslated(component.posX + component.height / 2, component.posY + component.height / 2, 0.0)
        val edges = theme.getThemeProperty("edges", 6)

        // fill the rect and the circle likes
        RenderUtil.fillRect(
                0, (-component.height / 2).toInt(), (component.width - component.height).toInt(), component.height.toInt(), innerColor)
        RenderUtil.fillPartialCircleLike(
                0, 0, component.height / 2, edges, edges / 2, edges, innerColor)
        RenderUtil.fillPartialCircleLike(
                (component.width - component.height).toInt(), 0, component.height / 2, edges, 0, edges / 2, innerColor)

        // draw top and bottom line of button
        RenderUtil.drawLine(
                0, (-component.height / 2).toInt(), (component.width - component.height).toInt(), (-component.height / 2).toInt(),
                outerColor, 0.05f)
        RenderUtil.drawLine(
                0, (component.height - component.height / 2).toInt(),
                (component.width - component.height).toInt(), (component.height - component.height / 2).toInt(), outerColor)

        // outline the half circle-like
        RenderUtil.drawPartialCircleLike(
                0, 0, component.height / 2, edges, edges / 2, edges, outerColor, 0.05f)
        RenderUtil.drawPartialCircleLike(
                (component.width - component.height).toInt(), 0, component.height / 2, edges, 0, edges / 2, outerColor)

        GuiManager.glStateManager.popMatrix()

        GuiManager.fontRenderer.drawString(component.text,
                (component.posX + (component.width - GuiManager.fontRenderer.getStringWidth(component.text)) / 2).toFloat(),
                (component.posY + (component.height - GuiManager.fontRenderer.getStringHeight(component.text)) / 2 - 1).toFloat(),
                theme.primaryTextColor.rgb)
    }
}