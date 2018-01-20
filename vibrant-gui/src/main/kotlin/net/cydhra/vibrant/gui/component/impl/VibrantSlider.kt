package net.cydhra.vibrant.gui.component.impl

import net.cydhra.vibrant.gui.component.ISlider

/**
 * A simple slider implementation
 */
open class VibrantSlider(initialValue: Double,
                         override var positionX: Double,
                         override var positionY: Double,
                         override var width: Double,
                         override var height: Double,
                         override var text: String) : AbstractVibrantComponent(positionX, positionY, width, height, text), ISlider {

    override var value: Double = initialValue
    override val selectionHandlers: MutableCollection<(Double) -> Unit> = mutableListOf()

    override fun onDragAction(mouseX: Int, mouseY: Int, mouseButton: Int) {
        this.updateValue(mouseX)
    }

    override fun onDragReleasedAction(mouseX: Int, mouseY: Int, mouseButton: Int) {
        this.updateValue(mouseX)
        selectionHandlers.forEach { it.invoke(this.value) }
    }

    override fun onClickAction(mouseX: Int, mouseY: Int, mouseButton: Int) {
        this.updateValue(mouseX)
        selectionHandlers.forEach { it.invoke(this.value) }
    }

    /**
     * Update the value of the slider based on the current mouse position while dragging
     */
    private fun updateValue(mouseX: Int) {
        this.value = Math.min(Math.max(mouseX - this.positionX, 0.0), this.width) / this.width
    }
}