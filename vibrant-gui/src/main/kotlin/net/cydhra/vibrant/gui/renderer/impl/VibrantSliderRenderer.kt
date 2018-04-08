package net.cydhra.vibrant.gui.renderer.impl

import net.cydhra.vibrant.gui.GuiManager
import net.cydhra.vibrant.gui.component.impl.VibrantSlider
import net.cydhra.vibrant.gui.renderer.ComponentRenderer
import net.cydhra.vibrant.gui.theme.Theme
import net.cydhra.vibrant.gui.util.RenderUtil
import org.lwjgl.opengl.GL11

/**
 *
 */
class VibrantSliderRenderer : ComponentRenderer<VibrantSlider> {

    override fun renderComponent(component: VibrantSlider, theme: Theme) {
        GuiManager.glStateManager.pushMatrix()
        GL11.glTranslated(component.posX + component.height / 2, component.positionY + component.height / 2, 0.0)
        val edges = theme.getThemeProperty("edges", 6)

        // fill the rect and the circle likes
        RenderUtil.fillRect(
                0, (-component.height / 2).toInt(), (component.width - component.height).toInt(), component.height.toInt(), theme.primaryColor)
        RenderUtil.fillPartialCircleLike(
                0, 0, component.height / 2, edges, edges / 2, edges, theme.primaryColor)
        RenderUtil.fillPartialCircleLike(
                (component.width - component.height).toInt(), 0, component.height / 2, edges, 0, edges / 2, theme.primaryColor)

        // draw top and bottom line of slider
        RenderUtil.drawLine(
                0, (-component.height / 2).toInt(), (component.width - component.height).toInt(), (-component.height / 2).toInt(),
                theme.secondaryColor, 0.05f)
        RenderUtil.drawLine(
                0, (component.height - component.height / 2).toInt(),
                (component.width - component.height).toInt(), (component.height - component.height / 2).toInt(), theme.secondaryColor)

        // outline the half circle-like
        RenderUtil.drawPartialCircleLike(
                0, 0, component.height / 2, edges, edges / 2, edges, theme.secondaryColor, 0.05f)
        RenderUtil.drawPartialCircleLike(
                (component.width - component.height).toInt(), 0, component.height / 2, edges, 0, edges / 2, theme.secondaryColor)

        GuiManager.glStateManager.popMatrix()

        val sliderButtonColor = if (component.isMouseOver) {
            theme.highlightColor(theme.tertiaryColor)
        } else {
            theme.tertiaryColor
        }

        GuiManager.glStateManager.pushMatrix()
        GL11.glTranslated(component.positionX + component.height / 2 + component.value * (component.width - component.height),
                component.positionY + component.height / 2, 0.0)
        RenderUtil.fillCircleLike(0, 0, component.height / 2, edges, sliderButtonColor)
        RenderUtil.drawCircleLike(0, 0, component.height / 2, edges, theme.secondaryColor)
        GuiManager.glStateManager.popMatrix()
    }
}