@file:Suppress("NOTHING_TO_INLINE")

package net.cydhra.vibrant.util.render

import org.lwjgl.opengl.GL11
import java.awt.Color
import kotlin.math.cos
import kotlin.math.sin

/**
 *
 */
object RenderUtil {

    /**
     * Set the OpenGL rendering color
     *
     * @param color [Color] instance
     */
    fun setColor(color: Color) {
        val alpha = color.alpha / 255.0f
        val red = color.red / 255.0f
        val green = color.green / 255.0f
        val blue = color.blue / 255.0f

        GL11.glColor4f(red, green, blue, alpha)
    }

    /**
     * Fill a circle at the given position with the given color.
     *
     * @param posX circle on-screen x position
     * @param posY circle on-screen y position
     * @param radius circle radius
     * @param color circle fill color
     */
    fun fillCircle(posX: Int, posY: Int, radius: Int, color: Color) {
        GL11.glPushMatrix()
        this.setColor(color)

        GL11.glBegin(GL11.GL_TRIANGLE_STRIP)
        for (i in (0..360)) {
            GL11.glVertex2d(posX.toDouble(), posY.toDouble())
            GL11.glVertex2d(posX + radius * cos(Math.toRadians(i.toDouble())),
                    posY + radius * sin(Math.toRadians(i.toDouble())))
            GL11.glVertex2d(posX + radius * cos(Math.toRadians((i + 1).toDouble())),
                    posY + radius * sin(Math.toRadians((i + 1).toDouble())))
        }
        GL11.glEnd()

        GL11.glPopMatrix()
    }

    fun drawLine3d(sourcePosX: Double, sourcePosY: Double, sourcePosZ: Double, targetPosX: Double, targetPosY: Double, targetPosZ: Double,
                   color: Color, lineWidth: Float) {
        setColor(color)
        GL11.glLineWidth(lineWidth)

        GL11.glBegin(GL11.GL_LINE_STRIP);
        GL11.glVertex3d(sourcePosX, sourcePosY, sourcePosZ)
        GL11.glVertex3d(targetPosX, targetPosY, targetPosZ)
        GL11.glEnd()
    }
}