package net.cydhra.vibrant.gui.component.impl

import net.cydhra.vibrant.gui.component.IListbox

/**
 *
 */
class VibrantListbox<T>(positionX: Double,
                        positionY: Double,
                        width: Double,
                        height: Double,
                        text: String) : AbstractVibrantComponent(positionX, positionY, width, height, text), IListbox<T> {
    override val selectionHandlers: MutableCollection<(T) -> Unit> = mutableListOf()
    override val items: MutableCollection<T> = mutableListOf()

    var itemHeight: Double = 15.0
        set(value) {
            field = itemHeight
            updateMenuItems()
        }

    var itemGap: Double = 2.0
        set(value) {
            field = itemHeight
            updateMenuItems()
        }

    private val scrollpane = VibrantScrollpane(0.0, 0.0, width, height, "")

    private var selectedItem: VibrantSelectableItem<T>? = null
        set(value) {
            if (value != null)
                this.selectionHandlers.forEach { it.invoke(value.content) }
            field = value
        }

    init {
        this.children.add(scrollpane)
    }

    override fun addItem(item: T) {
        items.add(item)
        updateMenuItems()
    }

    override fun removeItem(item: T) {
        items.remove(item)
        updateMenuItems()
    }

    override fun clear() {
        items.clear()
        updateMenuItems()
    }

    private fun updateMenuItems() {
        for ((index, item) in items.withIndex()) {
            scrollpane.addChildComponent(
                    VibrantSelectableItem(this, 0.0, itemHeight * index + itemGap * index, scrollpane.width, itemHeight, item).apply {
                        this.registerSelectionHandler {
                            this@VibrantListbox.selectedItem?.isChecked = false
                            this@VibrantListbox.selectedItem = this
                            this@VibrantListbox.selectedItem?.isChecked = true
                        }
                    })
        }
    }
}