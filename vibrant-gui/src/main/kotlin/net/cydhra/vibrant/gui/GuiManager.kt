package net.cydhra.vibrant.gui

import net.cydhra.vibrant.gui.component.IComponent
import net.cydhra.vibrant.gui.component.impl.VibrantButton
import net.cydhra.vibrant.gui.renderer.ComponentRenderer
import net.cydhra.vibrant.gui.renderer.impl.VibrantButtonRenderer
import net.cydhra.vibrant.gui.theme.DefaultTheme
import net.cydhra.vibrant.gui.theme.Theme

/**
 *
 */
object GuiManager {

    private val renderers = mutableMapOf<Class<*>, ComponentRenderer<*>>()

    var theme: Theme = DefaultTheme()

    init {
        this.setRenderer(VibrantButton::class.java, VibrantButtonRenderer())
    }

    /**
     * Set the renderer of a given class
     */
    fun <C : IComponent> setRenderer(clazz: Class<C>, renderer: ComponentRenderer<C>) {
        this.renderers[clazz] = renderer
    }

    /**
     * @return the renderer for the given component class or null, if no renderer for this class was previously set.
     *
     * @see [setRenderer]
     */
    fun <C : IComponent> getRenderer(clazz: Class<C>): ComponentRenderer<C>? {
        @Suppress("UNCHECKED_CAST")
        return this.renderers[clazz] as? ComponentRenderer<C>
    }
}