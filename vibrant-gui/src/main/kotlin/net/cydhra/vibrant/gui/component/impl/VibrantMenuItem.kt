package net.cydhra.vibrant.gui.component.impl

import net.cydhra.vibrant.gui.component.IMenu
import net.cydhra.vibrant.gui.component.IMenuItem

/**
 * A menu item that is derived from the button for simplicity
 */
open class VibrantMenuItem<T>(override val parent: IMenu<T>,
                              positionX: Double,
                              positionY: Double,
                              width: Double,
                              height: Double,
                              override val content: T) : VibrantButton(positionX, positionY, width, height, content.toString()), IMenuItem<T>