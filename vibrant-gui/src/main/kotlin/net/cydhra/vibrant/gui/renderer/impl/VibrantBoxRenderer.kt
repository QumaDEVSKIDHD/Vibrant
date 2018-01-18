package net.cydhra.vibrant.gui.renderer.impl

import net.cydhra.vibrant.gui.component.IComponent
import net.cydhra.vibrant.gui.renderer.ComponentRenderer
import net.cydhra.vibrant.gui.theme.Theme
import net.cydhra.vibrant.gui.util.RenderUtil

/**
 *
 */
class VibrantBoxRenderer<C : IComponent> : ComponentRenderer<C> {

    override fun renderComponent(component: C, theme: Theme) {
        RenderUtil.fillRect(component.posX, component.posY, component.width.toInt(), component.height.toInt(), theme.primaryColor)
        RenderUtil.drawRect(component.posX, component.posY, component.width.toInt(), component.height.toInt(), theme.secondaryColor)
    }
}