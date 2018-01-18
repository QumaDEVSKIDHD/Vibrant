package net.cydhra.vibrant.gui.renderer.impl

import net.cydhra.vibrant.gui.component.IMenuItem
import net.cydhra.vibrant.gui.renderer.ComponentRenderer
import net.cydhra.vibrant.gui.theme.Theme
import net.cydhra.vibrant.gui.util.RenderUtil

/**
 *
 */
class VibrantMenuItemRenderer : ComponentRenderer<IMenuItem<*>> {

    override fun renderComponent(component: IMenuItem<*>, theme: Theme) {
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

        RenderUtil.fillRect(component.posX, component.posY, component.width.toInt(), component.height.toInt(), innerColor)
        RenderUtil.drawRect(component.posX, component.posY, component.width.toInt(), component.height.toInt(), outerColor)
    }
}