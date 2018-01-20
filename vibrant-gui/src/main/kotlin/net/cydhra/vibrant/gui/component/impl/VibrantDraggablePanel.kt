package net.cydhra.vibrant.gui.component.impl

/**
 * A draggable panel component
 */
open class VibrantDraggablePanel(override var positionX: Double,
                            override var positionY: Double,
                            override var width: Double,
                            override var height: Double,
                            override var text: String) : VibrantPanel(positionX, positionY, width, height, text) {

    private var isDragging = false
    private var dragX = -1
    private var dragY = -1

    override fun onDragAction(mouseX: Int, mouseY: Int, mouseButton: Int) {
        if (!isDragging) {
            dragX = mouseX
            dragY = mouseY
            isDragging = true
        } else {
            this.positionX = (mouseX - dragX).toDouble()
            this.positionY = (mouseY - dragY).toDouble()
        }
    }

    override fun onDragReleasedAction(mouseX: Int, mouseY: Int, mouseButton: Int) {
        isDragging = false
    }
}