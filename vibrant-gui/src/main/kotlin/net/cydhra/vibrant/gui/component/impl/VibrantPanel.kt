package net.cydhra.vibrant.gui.component.impl

import net.cydhra.vibrant.gui.component.IPanel

/**
 * Believe me, it really does nothing.
 */
open class VibrantPanel(override var positionX: Double,
                   override var positionY: Double,
                   override var width: Double,
                   override var height: Double,
                   override var text: String) : AbstractVibrantComponent(positionX, positionY, width, height, text), IPanel