package net.cydhra.vibrant.gui.component.impl

import net.cydhra.vibrant.gui.component.ICombobox
import net.cydhra.vibrant.gui.component.IPanel

open class VibrantCombobox<T>(override var positionX: Double,
                         override var positionY: Double,
                         override var width: Double,
                         override var height: Double,
                         override var text: String,
                         selectedItem: T,
                         override val items: MutableCollection<T> = mutableListOf())
    : AbstractVibrantComponent(positionX, positionY, width, height, text), ICombobox<T> {

    override var selectedItem: T = selectedItem
        set(value) {
            this.selectionHandlers.forEach { it.invoke(value) }
            field = value
        }

    override var isExpanded: Boolean = false
        set(expanded) {
            if (field != expanded) {
                if (expanded) {
                    this.addChildComponent(this.buildListChild())
                } else {
                    this.children.removeIf { it is IPanel }
                }

                field = expanded
            }
        }

    init {
        this.addChildComponent(VibrantButton(this.width - this.height, 0.0, this.height, this.height, "").apply {
            clickHandlers.add { this@VibrantCombobox.toggleExpand() }
        })
    }

    override val selectionHandlers: MutableCollection<(T) -> Unit> = mutableListOf()

    /**
     * Toggle the expand state of the combobox
     */
    fun toggleExpand() {
        this.isExpanded = !this.isExpanded
    }

    /**
     * Build a child component that represents the selection of the combobox
     */
    private fun buildListChild(): VibrantPanel {
        val panel = VibrantPanel(this.height / 2, this.height, this.width - this.height, 1.0, "")

        this.items.forEach { item ->
            panel.addChildComponent(VibrantMenuItem<T>(this, 0.0, panel.height, panel.width, 20.0, item).apply {
                this.registerClickHandler {
                    this@VibrantCombobox.selectedItem = this.content
                    this@VibrantCombobox.isExpanded = false
                }
            })
            panel.height += 20
        }

        return panel
    }

}