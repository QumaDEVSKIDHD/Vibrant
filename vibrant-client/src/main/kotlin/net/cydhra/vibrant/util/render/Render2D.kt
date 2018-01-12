package net.cydhra.vibrant.util.render

import org.lwjgl.opengl.GL11
import java.awt.Color
import kotlin.math.cos
import kotlin.math.sin

/**
 *
 */
object Render2D {

    /**
     * Set the OpenGL rendering color
     *
     * @param color [Color] instance
     */
    fun setColor(color: Color) {
        val alpha = (color.alpha shr 24 and 255) / 255.0f
        val red = (color.red shr 16 and 255) / 255.0f
        val green = (color.green shr 8 and 255) / 255.0f
        val blue = (color.blue and 255) / 255.0f

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

        GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
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

    /**
     * Disable OpenGL depth test
     */
    fun disableDepthTest() {
        GL11.glDisable(GL11.GL_DEPTH_TEST)
    }

    /**
     * Enable OpenGL depth test
     */
    fun enableDepthTest() {
        GL11.glEnable(GL11.GL_DEPTH_TEST)
    }

}