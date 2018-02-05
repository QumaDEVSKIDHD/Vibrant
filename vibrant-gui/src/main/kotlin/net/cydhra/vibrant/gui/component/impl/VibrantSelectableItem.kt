package net.cydhra.vibrant.gui.component.impl

import net.cydhra.vibrant.gui.component.ICheckbox
import net.cydhra.vibrant.gui.component.IMenu
import net.cydhra.vibrant.gui.component.ISelectableItem

/**
 *
 */
class VibrantSelectableItem<T>(parent: IMenu<T>,
                               positionX: Double,
                               positionY: Double,
                               width: Double,
                               height: Double,
                               content: T) : VibrantMenuItem<T>(parent, positionX, positionY, width, height, content), ISelectableItem<T> {
    override var isChecked: Boolean = false
    override val selectionHandlers: MutableCollection<(ICheckbox) -> Unit> = mutableListOf()

    init {
        this.clickHandlers.add {
            this.selectionHandlers.forEach {
                it.invoke(this@VibrantSelectableItem)
            }
        }
    }
}