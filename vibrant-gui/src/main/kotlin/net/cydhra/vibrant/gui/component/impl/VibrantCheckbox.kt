package net.cydhra.vibrant.gui.component.impl

import net.cydhra.vibrant.gui.component.ICheckbox

/**
 * A checkbox component
 */
class VibrantCheckbox(positionX: Double, positionY: Double, width: Double, height: Double, text: String, isSelected: Boolean = false) :
        AbstractVibrantComponent(positionX, positionY, width, height, text), ICheckbox {
    override var isChecked: Boolean = isSelected
        set(value) {
            if (field != value) {
                field = value
                selectionHandlers.forEach { it.invoke(this) }
            }
        }

    override val selectionHandlers: MutableCollection<(ICheckbox) -> Unit> = mutableListOf()

    /**
     * Toggle checkbox selection
     *
     * @see [AbstractVibrantComponent.onClickAction]
     */
    override fun onClickAction(mouseX: Int, mouseY: Int, mouseButton: Int) {
        this.isChecked = !this.isChecked
    }


}