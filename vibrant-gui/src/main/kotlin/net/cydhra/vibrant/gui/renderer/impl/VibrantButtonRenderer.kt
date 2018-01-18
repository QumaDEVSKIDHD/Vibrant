package net.cydhra.vibrant.gui.renderer.impl

import net.cydhra.vibrant.gui.component.impl.VibrantButton
import net.cydhra.vibrant.gui.renderer.ComponentRenderer
import net.cydhra.vibrant.gui.theme.Theme
import net.cydhra.vibrant.gui.util.GlStateManager
import net.cydhra.vibrant.gui.util.RenderUtil
import org.lwjgl.opengl.GL11

/**
 *
 */
class VibrantButtonRenderer : ComponentRenderer<VibrantButton> {

    override fun renderComponent(component: VibrantButton, theme: Theme) {
        val innerColor = if (component.isMouseOver) {
            theme.highlightColor(theme.primaryColor)
        } else {
            theme.primaryColor
        }

        val outerColor = if (component.isMouseOver) {
            theme.highlightColor(theme.secondaryColor)
        } else {
            theme.secondaryColor
        }

        GlStateManager.pushMatrix()
        GL11.glTranslated(component.posX + component.height / 2, component.posY + component.height / 2, 0.0)
        val edges = theme.getThemeProperty("edges", 6)

        RenderUtil.fillRect(
                0, (-component.height / 2).toInt(), (component.width - component.height).toInt(), component.height.toInt(), innerColor)
        RenderUtil.fillPartialCircleLike(
                0, 0, component.height / 2, edges, edges / 2, edges, innerColor)
        RenderUtil.fillPartialCircleLike(
                (component.width - component.height).toInt(), 0, component.height / 2, edges, 0, edges / 2, innerColor)

        RenderUtil.drawLine(
                0, (-component.height / 2).toInt(), (component.width - component.height).toInt(), (-component.height / 2).toInt(), outerColor)
        RenderUtil.drawLine(
                0, (component.height - component.height / 2).toInt(),
                (component.width - component.height).toInt(), (component.height - component.height / 2).toInt(), outerColor)

        RenderUtil.drawPartialCircleLike(
                0, 0, component.height / 2, edges, edges / 2, edges, outerColor, 0.05f)
        RenderUtil.drawPartialCircleLike(
                (component.width - component.height).toInt(), 0, component.height / 2, edges, 0, edges / 2, outerColor, 0.05f)


//        RenderUtil.drawRect(component.posX, component.posY, component.width.toInt(), component.height.toInt(), outerColor, 0.05f)

        GlStateManager.popMatrix()
    }
}