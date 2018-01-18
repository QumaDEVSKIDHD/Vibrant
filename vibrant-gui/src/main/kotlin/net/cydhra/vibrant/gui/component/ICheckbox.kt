package net.cydhra.vibrant.gui.component

import net.cydhra.vibrant.gui.component.capabilities.Selectable

/**
 *
 */
interface ICheckbox : IComponent, Selectable<ICheckbox> {
    var isChecked: Boolean
}