package net.cydhra.vibrant.gui.screen

import net.cydhra.vibrant.gui.component.IComponent
import java.util.*

/**
 *
 */
open class VibrantScreen {

    private val components: LinkedList<IComponent> = LinkedList()

    fun addComponent(component: IComponent) {
        this.components.add(component)
    }

    fun updateMousePosition(mouseX: Int, mouseY: Int) {
        var hoveredComponentIdentified = false
        this.components.forEach { comp -> hoveredComponentIdentified = comp.updateHovering(mouseX - comp.posX, mouseY - comp.posY, !hoveredComponentIdentified) }
    }

    fun onClick(mouseX: Int, mouseY: Int, mouseButton: Int) {
        this.components.firstOrNull { it.updateHovering(mouseX - it.posX, mouseY - it.posY, true) }?.apply {
            this.onClick(mouseX - this.posX, mouseY - this.posY, mouseButton)
        }
    }

    fun draw() {
        for (i in (0 until components.size).reversed()) {
            this.components[i].drawComponent()
        }
    }

    fun tick() {

    }
}