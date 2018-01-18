package net.cydhra.vibrant.gui.component

import net.cydhra.vibrant.gui.component.capabilities.Selectable

/**
 *
 */
interface ICheckBox : IComponent, Selectable<ICheckBox> {
    var isChecked: Boolean
}