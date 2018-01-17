package net.cydhra.vibrant.gui.renderer.impl

import net.cydhra.vibrant.gui.component.impl.VibrantButton
import net.cydhra.vibrant.gui.renderer.ComponentRenderer
import net.cydhra.vibrant.gui.theme.Theme
import net.cydhra.vibrant.gui.util.RenderUtil

/**
 *
 */
class VibrantButtonRenderer : ComponentRenderer<VibrantButton> {

    override fun renderComponent(component: VibrantButton, theme: Theme) {
        RenderUtil.fillRect(component.posX, component.posY, component.width.toInt(), component.height.toInt(), theme.primaryColor)
        RenderUtil.drawRect(component.posX, component.posY, component.width.toInt(), component.height.toInt(), theme.secondaryColor, 0.05f)
    }
}