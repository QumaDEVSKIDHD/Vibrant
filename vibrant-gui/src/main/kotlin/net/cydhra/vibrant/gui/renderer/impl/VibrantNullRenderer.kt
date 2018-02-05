package net.cydhra.vibrant.gui.renderer.impl

import net.cydhra.vibrant.gui.component.IComponent
import net.cydhra.vibrant.gui.renderer.ComponentRenderer
import net.cydhra.vibrant.gui.theme.Theme

/**
 *
 */
class VibrantNullRenderer<C : IComponent> : ComponentRenderer<C> {
    override fun renderComponent(component: C, theme: Theme) {
        // do absolutely nothing
    }

}