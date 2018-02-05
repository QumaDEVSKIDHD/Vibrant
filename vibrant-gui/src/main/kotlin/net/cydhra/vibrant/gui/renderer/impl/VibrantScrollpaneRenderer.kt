package net.cydhra.vibrant.gui.renderer.impl

import net.cydhra.vibrant.gui.component.impl.VibrantScrollpane
import net.cydhra.vibrant.gui.renderer.ComponentRenderer
import net.cydhra.vibrant.gui.theme.Theme
import net.cydhra.vibrant.gui.util.RenderUtil

/**
 *
 */
class VibrantScrollpaneRenderer : ComponentRenderer<VibrantScrollpane> {
    override fun renderComponent(component: VibrantScrollpane, theme: Theme) {
        RenderUtil.drawRect(component.posX, component.posY, component.width.toInt(), component.height.toInt(), theme.secondaryColor, 0.2f)
    }

}