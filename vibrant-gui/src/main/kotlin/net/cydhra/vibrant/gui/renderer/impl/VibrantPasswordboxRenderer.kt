package net.cydhra.vibrant.gui.renderer.impl

import net.cydhra.vibrant.gui.GuiManager
import net.cydhra.vibrant.gui.component.impl.VibrantPasswordbox
import net.cydhra.vibrant.gui.component.impl.VibrantTextbox
import net.cydhra.vibrant.gui.theme.Theme

/**
 *
 */
class VibrantPasswordboxRenderer : VibrantTextboxRenderer<VibrantPasswordbox>() {

    override fun renderContent(component: VibrantPasswordbox, theme: Theme, posY: Float) {
        GuiManager.fontRenderer.drawString(
                String(Array(component.text.length, { component.passwordChar }).toCharArray()),
                component.posX.toFloat() + VibrantTextbox.MARGIN_LEFT,
                posY,
                theme.primaryTextColor.rgb)
    }

    override fun getContentWidth(component: VibrantPasswordbox, content: String): Int {
        return super.getContentWidth(component, String(Array(content.length, { component.passwordChar }).toCharArray()))
    }
}