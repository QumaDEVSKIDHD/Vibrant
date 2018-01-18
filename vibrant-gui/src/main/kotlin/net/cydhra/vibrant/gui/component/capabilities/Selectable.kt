package net.cydhra.vibrant.gui.component.capabilities

import net.cydhra.vibrant.gui.component.IComponent

/**
 * A component that can be used to select something
 *
 * @param C type of the selected component
 */
interface Selectable<C : IComponent> {

    /**
     * All registered selection handlers of this component
     */
    val selectionHandlers: MutableCollection<(C) -> Unit>

    /**
     * Register a function as a handler for clicks on this component
     */
    fun registerSelectionHandler(callback: (C) -> Unit) {
        selectionHandlers.add(callback)
    }
}