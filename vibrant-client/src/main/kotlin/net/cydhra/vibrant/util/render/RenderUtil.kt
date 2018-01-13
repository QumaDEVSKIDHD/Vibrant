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

    inline fun disableDepthTest() = GL11.glDisable(GL11.GL_DEPTH_TEST)
    inline fun enableDepthTest() = GL11.glEnable(GL11.GL_DEPTH_TEST)

    inline fun enableTexture2D() = GL11.glEnable(GL11.GL_TEXTURE_2D)
    inline fun disableTexture2D() = GL11.glDisable(GL11.GL_TEXTURE_2D)

    inline fun disableColorBlending() = GL11.glDisable(GL11.GL_BLEND)
    inline fun enableColorBlending() = GL11.glEnable(GL11.GL_BLEND)

    inline fun enableLineSmoothing() = GL11.glEnable(GL11.GL_LINE_SMOOTH)
    inline fun disableLineSmoothing() = GL11.glDisable(GL11.GL_LINE_SMOOTH)

    inline fun enableDepthMask() = GL11.glDepthMask(true)
    inline fun disableDepthMask() = GL11.glDepthMask(false)

    inline fun pushMatrix() = GL11.glPushMatrix()
    inline fun popMatrix() = GL11.glPopMatrix()
}