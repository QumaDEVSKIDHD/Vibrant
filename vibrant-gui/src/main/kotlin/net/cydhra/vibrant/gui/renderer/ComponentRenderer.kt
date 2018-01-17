package net.cydhra.vibrant.gui.renderer

import net.cydhra.vibrant.gui.component.IComponent
import net.cydhra.vibrant.gui.theme.Theme

/**
 * Renderer for components of type [C]
 *
 * @param C component type
 */
interface ComponentRenderer<in C : IComponent> {

    /**
     * Render the given component of type [C] using the given theme
     *
     * @param component any component instance of type [C]
     * @param theme any theme providing colors and shades for rendering. This does not mean, it must be used.
     */
    fun renderComponent(component: C, theme: Theme)
}