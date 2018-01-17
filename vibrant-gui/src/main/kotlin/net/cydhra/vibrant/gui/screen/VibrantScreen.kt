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

    /**
     * Handle a click on the screen. The components are searched in order for the one clicked. If one is found, it is moved to the head
     * of the list, so it is now the focused one.
     *
     * @param mouseX mouse x coordinate on screen
     * @param mouseY mouse y coordinate on screen
     * @param mouseButton pressed mouse button
     */
    fun onClick(mouseX: Int, mouseY: Int, mouseButton: Int) {
        val iterator = this.components.iterator()
        var hoveredComponent: IComponent? = null

        while (iterator.hasNext()) {
            val component = iterator.next()
            if (component.updateHovering(mouseX - component.posX, mouseY - component.posY, false)) {
                component.onClick(mouseX - component.posX, mouseY - component.posY, mouseButton)
                hoveredComponent = component
                iterator.remove()
                break
            }
        }

        if (hoveredComponent != null) {
            this.components.add(0, hoveredComponent)
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