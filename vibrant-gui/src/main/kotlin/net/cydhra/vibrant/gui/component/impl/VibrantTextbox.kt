package net.cydhra.vibrant.gui.component.impl

import net.cydhra.vibrant.gui.GuiManager
import net.cydhra.vibrant.gui.component.ITextBox
import org.lwjgl.input.Keyboard


/**
 *
 */
open class VibrantTextbox(
        positionX: Double,
        positionY: Double,
        width: Double,
        height: Double,
        text: String) : AbstractVibrantComponent(positionX, positionY, width, height, text), ITextBox {

    companion object {
        const val MARGIN_LEFT = 4
    }

    private var dragging: Boolean = false
    private var initialCursorIndex: Int = 0

    override var cursorIndex: Int = 0
        set(value) {
            if (value < 0)
                field = 0
            else if (value >= text.length)
                field = text.length
            else
                field = value
        }

    override var markerLength: Int = 0

    override fun onClickAction(mouseX: Int, mouseY: Int, mouseButton: Int) {
        if (mouseButton == 0) {
            this.cursorIndex = getCursorIndex(mouseX)
            this.initialCursorIndex = cursorIndex
            this.markerLength = 0
        }
    }

    override fun onDragAction(mouseX: Int, mouseY: Int, mouseButton: Int) {
        if (dragging) {
            val nextCursorIndex = getCursorIndex(mouseX)
            if (nextCursorIndex > initialCursorIndex) {
                // set marker length
                this.cursorIndex = initialCursorIndex
                this.markerLength = nextCursorIndex - initialCursorIndex
            } else if (nextCursorIndex < initialCursorIndex) {
                // set marker length and shift cursor
                this.markerLength = initialCursorIndex - nextCursorIndex
                this.cursorIndex = nextCursorIndex
            }
        } else {
            this.cursorIndex = getCursorIndex(mouseX)
            this.initialCursorIndex = cursorIndex
            this.markerLength = 0
            dragging = true
        }
    }

    override fun onDragReleasedAction(mouseX: Int, mouseY: Int, mouseButton: Int) {
        dragging = false
        initialCursorIndex = 0
    }

    /**
     * Calculate the cursor index of the current mouse position
     *
     * @param mouseX the current mouse x position
     *
     * @return the cursor index of the current mouse position
     */
    private fun getCursorIndex(mouseX: Int): Int {
        var textSnippet = ""

        for (i in 0 until this.text.length) {
            val currentChar = this.text.toCharArray()[i]

            if (mouseX < (MARGIN_LEFT + GuiManager.fontRenderer.getStringWidth(textSnippet)
                            + GuiManager.fontRenderer.getStringWidth("" + currentChar) / 1.5)) {
                return i
            }

            textSnippet += currentChar
        }

        return this.text.length
    }

    override fun onKeyAction(typedChar: Char, keyCode: Int) {
        val preCursorText = this.text.substring(0, this.cursorIndex)
        val markedText = this.text.substring(this.cursorIndex, this.cursorIndex + this.markerLength)
        val postCursorText = this.text.substring(this.cursorIndex + this.markerLength, this.text.length)

        when (keyCode) {
            Keyboard.KEY_BACK -> {
                this.text = (if (markedText.isEmpty())
                    if (!preCursorText.isEmpty()) preCursorText.substring(0, preCursorText.length - 1) else ""
                else
                    preCursorText) + postCursorText

                if (markedText.isEmpty() && !preCursorText.isEmpty()) this.cursorIndex = this.cursorIndex - 1
            }
            Keyboard.KEY_DELETE -> this.text = preCursorText + if (markedText.isEmpty())
                if (!postCursorText.isEmpty()) postCursorText.substring(1) else ""
            else
                postCursorText
            Keyboard.KEY_HOME -> this.cursorIndex = 0
            Keyboard.KEY_END -> this.cursorIndex = this.text.length
            Keyboard.KEY_LEFT -> this.cursorIndex = this.cursorIndex - 1
            Keyboard.KEY_RIGHT -> this.cursorIndex = this.cursorIndex + 1

            Keyboard.KEY_RETURN, Keyboard.KEY_TAB -> {
            }
            else -> if (typedChar.toInt() != 0 && typedChar.toInt() > 20 && typedChar.toInt() < 256) {
                this.text = preCursorText + typedChar + postCursorText
                this.cursorIndex = this.cursorIndex + 1
            }
        }

        this.markerLength = 0
    }
}