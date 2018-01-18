package net.cydhra.vibrant.gui.component

import net.cydhra.vibrant.gui.component.capabilities.Selectable

/**
 * @param T menu item content type
 */
interface IMenu<T> : IComponent, Selectable<T> {

    /**
     * Whether the menu is expanded. This may throw an [UnsupportedOperationException] if the menu cannot be expanded or compressed
     */
    var isExpanded: Boolean
}