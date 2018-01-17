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

    override fun isHovered(mouseX: Int, mouseY: Int): Boolean {
        return this.children.any { isHovered(mouseX - this.posX, mouseY - this.posY) }
                || (this.posX < mouseX && this.posX + this.width > mouseX
                && this.posY < mouseY && this.posY + this.height > mouseY)
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
}