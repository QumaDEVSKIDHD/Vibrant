package net.cydhra.vibrant.gui.component.capabilities

/**
 * A component that can be used to select something
 *
 * @param C type of the selected item
 */
interface Selectable<T> {

    /**
     * All registered selection handlers of this component
     */
    val selectionHandlers: MutableCollection<(T) -> Unit>

    /**
     * Register a function as a handler for clicks on this component
     */
    fun registerSelectionHandler(callback: (T) -> Unit) {
        selectionHandlers.add(callback)
    }
}