package net.cydhra.vibrant.gui

import net.cydhra.vibrant.gui.component.IComponent
import net.cydhra.vibrant.gui.component.impl.*
import net.cydhra.vibrant.gui.font.VibrantFontRenderer
import net.cydhra.vibrant.gui.renderer.ComponentRenderer
import net.cydhra.vibrant.gui.renderer.impl.*
import net.cydhra.vibrant.gui.theme.DefaultTheme
import net.cydhra.vibrant.gui.theme.Theme

/**
 *
 */
object GuiManager {

    private val renderers = mutableMapOf<Class<*>, ComponentRenderer<*>>()

    var theme: Theme = DefaultTheme()

    private var _fontObjHash: Int = theme.textFont.hashCode()
    var fontRenderer: VibrantFontRenderer = VibrantFontRenderer(theme.textFont)
        get() {
            if (theme.textFont.hashCode() != _fontObjHash) {
                field = VibrantFontRenderer(theme.textFont)
                _fontObjHash = theme.textFont.hashCode()
            }

            return field
        }
        private set

    init {
        this.setRenderer(VibrantButton::class.java, VibrantButtonRenderer())
        this.setRenderer(VibrantCheckbox::class.java, VibrantCheckboxRenderer())
        this.setRenderer(VibrantPanel::class.java, VibrantBoxRenderer())
        this.setRenderer(VibrantMenuItem::class.java, VibrantMenuItemRenderer())
        this.setRenderer(VibrantCombobox::class.java, VibrantComboboxRenderer())
        this.setRenderer(VibrantDraggablePanel::class.java, VibrantBoxRenderer())
        this.setRenderer(VibrantSlider::class.java, VibrantSliderRenderer())
        this.setRenderer(VibrantTextbox::class.java, VibrantTextboxRenderer())
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