package net.cydhra.vibrant.gui.renderer.impl

import net.cydhra.vibrant.gui.component.impl.VibrantCheckbox
import net.cydhra.vibrant.gui.renderer.ComponentRenderer
import net.cydhra.vibrant.gui.theme.Theme
import net.cydhra.vibrant.gui.util.GlStateManager
import net.cydhra.vibrant.gui.util.RenderUtil
import org.lwjgl.opengl.GL11

/**
 * Default renderer for [VibrantCheckbox]
 */
class VibrantCheckboxRenderer : ComponentRenderer<VibrantCheckbox> {

    override fun renderComponent(component: VibrantCheckbox, theme: Theme) {
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

        RenderUtil.fillCircleLike(0, 0, component.height / 2, edges, innerColor)
        RenderUtil.drawCircleLike(0, 0, component.height / 2, edges, outerColor)

        if (component.isChecked) {
            RenderUtil.fillCircleLike(0, 0, component.height / 4, edges, theme.tertiaryColor)
            RenderUtil.drawCircleLike(0, 0, component.height / 4, edges, outerColor)
        }
        GlStateManager.popMatrix()
    }

}