package net.cydhra.vibrant.gui.component.impl

import net.cydhra.vibrant.gui.component.IButton
import net.cydhra.vibrant.gui.component.IComponent

/**
 * A simple button implementation.
 */
open class VibrantButton(positionX: Double, positionY: Double, width: Double, height: Double, text: String)
    : AbstractVibrantComponent(positionX, positionY, width, height, text), IButton {

    override val clickHandlers: MutableCollection<(IComponent) -> Unit> = mutableListOf()
}