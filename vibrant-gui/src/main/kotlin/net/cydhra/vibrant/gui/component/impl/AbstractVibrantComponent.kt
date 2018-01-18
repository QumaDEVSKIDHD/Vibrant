package net.cydhra.vibrant.gui.component.impl

import net.cydhra.vibrant.gui.GuiManager
import net.cydhra.vibrant.gui.component.IComponent

/**
 * A basic implementation of the features all components have. Everything not implemented by this class must be implemented by its
 * subclasses, which will be all component implementations within this library. Other implementations do not need to extend this class,
 * just [IComponent] must be implemented.
 */
abstract class AbstractVibrantComponent(
        override var positionX: Double,
        override var positionY: Double,
        override var width: Double,
        override var height: Double,
        override var text: String) : IComponent {

    private val children: MutableList<AbstractVibrantComponent> = mutableListOf()

    override var isMouseOver: Boolean = false

    override fun updateHovering(mouseX: Int, mouseY: Int, shallUpdate: Boolean): Boolean {
        isMouseOver = false

        var foundHoveredComponent = false
        children.forEach {
            foundHoveredComponent = it.updateHovering(mouseX - it.posX, mouseY - it.posY, shallUpdate && !foundHoveredComponent)
        }

        if (foundHoveredComponent)
            return true

        if (isMouseInsideComponent(mouseX, mouseY)) {
            isMouseOver = shallUpdate
            return true
        }

        return false
    }

    override fun getChildComponents(): List<IComponent> {
        return this.children
    }

    override fun addChildComponent(component: IComponent) {
        if (component !is AbstractVibrantComponent)
            throw IllegalArgumentException("${component.javaClass} is not compatible with ${this.javaClass}")

        this.children.add(component)
    }

    override fun drawComponent() {
        GuiManager.getRenderer(this.javaClass)?.renderComponent(this, GuiManager.theme) ?:
                IllegalStateException("No renderer for ${this.javaClass} was set in the GuiManager")
        this.children.forEach(IComponent::drawComponent)
    }

    override fun onClick(mouseX: Int, mouseY: Int, mouseButton: Int) {
        children.firstOrNull { it.updateHovering(mouseX - it.posX, mouseY - it.posY, false) }
                ?.apply { this.onClick(mouseX - this.posX, mouseY - this.posY, mouseButton) }
                ?: this.onClickAction(mouseX, mouseY, mouseButton)
    }

    /**
     * Click handler that is only executed when the component was actually clicked (and not one of its sub-components)
     *
     * @param mouseX mouse position x relative to component's origin
     * @param mouseY mouse position y relative to component's origin
     * @param mouseButton clicked mouse button
     */
    abstract protected fun onClickAction(mouseX: Int, mouseY: Int, mouseButton: Int)
}