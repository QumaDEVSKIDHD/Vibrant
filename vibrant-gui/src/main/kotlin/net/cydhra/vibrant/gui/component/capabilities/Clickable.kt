package net.cydhra.vibrant.gui.component.capabilities

import net.cydhra.vibrant.gui.component.IComponent

/**
 * A clickable component
 */
interface Clickable {

    /**
     * All registered click handlers of this component
     */
    val clickHandlers: MutableCollection<(IComponent) -> Unit>

    /**
     * Register a function as a handler for clicks on this component
     */
    fun registerClickHandler(callback: (IComponent) -> Unit) {
        clickHandlers.add(callback)
    }
}