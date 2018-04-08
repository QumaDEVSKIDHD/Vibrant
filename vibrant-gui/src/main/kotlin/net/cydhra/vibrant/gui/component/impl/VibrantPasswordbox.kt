package net.cydhra.vibrant.gui.component.impl

/**
 *
 */
class VibrantPasswordbox(
        positionX: Double,
        positionY: Double,
        width: Double,
        height: Double,
        text: String,
        val passwordChar: Char = '*') : VibrantTextbox(positionX, positionY, width, height, text)