package net.cydhra.vibrant.gui.screen

import net.cydhra.vibrant.gui.component.IComponent

/**
 *
 */
open class VibrantScreen {

    private val components: MutableList<IComponent> = mutableListOf()

    fun addComponent(component: IComponent) {
        this.components.add(component)
    }
}