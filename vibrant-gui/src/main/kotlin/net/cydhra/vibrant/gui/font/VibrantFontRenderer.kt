package net.cydhra.vibrant.gui.font

import org.lwjgl.opengl.GL11.*
import org.newdawn.slick.SlickException
import org.newdawn.slick.UnicodeFont
import org.newdawn.slick.font.effects.ColorEffect
import java.awt.Color
import java.awt.Font
import java.util.*

class VibrantFontRenderer(awtFont: Font) {

    private var fontHeight: Int

    private var font: UnicodeFont = UnicodeFont(awtFont)

    init {
        font.addAsciiGlyphs()
        font.effects.add(ColorEffect(Color.WHITE))
        try {
            font.loadGlyphs()
        } catch (exception: SlickException) {
            throw RuntimeException(exception)
        }

        fontHeight = font.getHeight("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789") / 2
    }

    fun drawString(string: String?, x: Int, y: Int, color: Int): Int {
        var x = x
        var y = y
        if (string == null) {
            return 0
        }

        glPushMatrix()
        glScaled(0.5, 0.5, 0.5)

        val blend = glIsEnabled(GL_BLEND)
        val lighting = glIsEnabled(GL_LIGHTING)
        val texture = glIsEnabled(GL_TEXTURE_2D)

        if (!blend) {
            glEnable(GL_BLEND)
        }
        if (lighting) {
            glDisable(GL_LIGHTING)
        }
        if (texture) {
            glDisable(GL_TEXTURE_2D)
        }
        x *= 2
        y *= 2

        font.drawString(x.toFloat(), y.toFloat(), string, org.newdawn.slick.Color(color))

        if (texture) {
            glEnable(GL_TEXTURE_2D)
        }
        if (lighting) {
            glEnable(GL_LIGHTING)
        }
        if (!blend) {
            glDisable(GL_BLEND)
        }
        glPopMatrix()
        return x
    }

    fun drawStringWithShadow(string: String, x: Float, y: Float, color: Int): Int {
        return drawString(string, x.toInt(), y.toInt(), color)
    }

    fun getCharWidth(c: Char): Int {
        return getStringWidth(Character.toString(c))
    }

    fun getStringWidth(string: String): Int {
        return font.getWidth(string) / 2
    }

    fun getStringHeight(string: String): Int {
        return font.getHeight(string) / 2
    }

    fun drawStringWithShadow(string: String, x: Int, y: Int, color: Int): Int {
        return this.drawStringWithShadow(string, x.toFloat(), y.toFloat(), color)
    }

    fun drawSplitString(str: String, x: Int, y: Int, wrapWidth: Int, textColor: Int) {
        var y = y
        val lines = ArrayList<StringBuilder>()
        lines.add(StringBuilder())

        var line = 0
        for (chr in str.toCharArray()) {
            if (this.getStringWidth(lines[line].toString()) + this.getCharWidth(chr) <= wrapWidth) {
                lines[line].append(chr)
            } else {
                lines[line].append(chr)
                lines.add(StringBuilder())
                line++
            }
        }

        for (builder in lines) {
            this.drawString(builder.toString(), x, y, textColor)

            y += fontHeight
        }
    }
}