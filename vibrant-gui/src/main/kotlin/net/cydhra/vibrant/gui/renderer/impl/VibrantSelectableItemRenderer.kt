package net.cydhra.vibrant.gui.renderer.impl

import net.cydhra.vibrant.gui.GuiManager
import net.cydhra.vibrant.gui.component.ISelectableItem
import net.cydhra.vibrant.gui.renderer.ComponentRenderer
import net.cydhra.vibrant.gui.theme.Theme
import net.cydhra.vibrant.gui.util.RenderUtil

/**
 *
 */
class VibrantSelectableItemRenderer : ComponentRenderer<ISelectableItem<*>> {

    override fun renderComponent(component: ISelectableItem<*>, theme: Theme) {
        val innerColor = (if (component.isMouseOver) {
            theme.highlightColor(theme.primaryColor)
        } else {
            theme.primaryColor
        })
                .let { if (component.isChecked) it.brighter() else it }

        val outerColor = if (component.isMouseOver) {
            theme.highlightColor(theme.secondaryColor)
        } else {
            theme.secondaryColor
        }

        RenderUtil.fillRect(component.posX, component.posY, component.width.toInt(), component.height.toInt(), innerColor)
        RenderUtil.drawRect(component.posX, component.posY, component.width.toInt(), component.height.toInt(), outerColor)

        GuiManager.fontRenderer.drawString(component.text, component.posX + 4F, (component.posY + (component.height -
                GuiManager.fontRenderer.getStringHeight(component.text)) / 2).toFloat(), theme.primaryTextColor.rgb)
    }
}