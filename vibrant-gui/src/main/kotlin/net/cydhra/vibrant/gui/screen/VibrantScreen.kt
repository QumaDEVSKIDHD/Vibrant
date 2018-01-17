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

    fun draw() {
        this.components.forEach(IComponent::drawComponent)
    }

    fun tick() {

    }
}