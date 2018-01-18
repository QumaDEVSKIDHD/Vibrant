package net.cydhra.vibrant.gui.component.impl

import net.cydhra.vibrant.gui.component.IMenu
import net.cydhra.vibrant.gui.component.IMenuItem

/**
 * A menu item that is derived from the button for simplicity
 */
class VibrantMenuItem<T>(override val parent: IMenu<T>,
                         override var positionX: Double,
                         override var positionY: Double,
                         override var width: Double,
                         override var height: Double,
                         override val content: T) : VibrantButton(positionX, positionY, width, height, content.toString()), IMenuItem<T>