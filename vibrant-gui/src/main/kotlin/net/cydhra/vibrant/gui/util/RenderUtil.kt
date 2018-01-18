@file:Suppress("NOTHING_TO_INLINE")

package net.cydhra.vibrant.gui.util

import org.lwjgl.opengl.GL11
import java.awt.Color
import kotlin.math.PI
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
        GlStateManager.color(color)
    }

    private fun setOptions(color: Color? = null, lineWidth: Float? = null) {
        if (color != null)
            this.setColor(color)

        if (lineWidth != null)
            GL11.glLineWidth(lineWidth)

    }

    fun drawLine(startVertexX: Int, startVertexY: Int, endVertexX: Int, endVertexY: Int, color: Color? = null, lineWidth: Float? = null) {
        setOptions(color, lineWidth)

        GL11.glBegin(GL11.GL_LINE_STRIP)
        GL11.glVertex2d(startVertexX.toDouble(), startVertexY.toDouble())
        GL11.glVertex2d(endVertexX.toDouble(), endVertexY.toDouble())
        GL11.glEnd()
    }

    fun fillRect(posX: Int, posY: Int, width: Int, height: Int, color: Color? = null) {
        this.setOptions(color)

        GL11.glBegin(GL11.GL_QUADS)
        GL11.glVertex2d(posX.toDouble(), posY.toDouble())
        GL11.glVertex2d(posX.toDouble(), (posY + height).toDouble())
        GL11.glVertex2d((posX + width).toDouble(), (posY + height).toDouble())
        GL11.glVertex2d((posX + width).toDouble(), posY.toDouble())

        GL11.glEnd()
    }

    fun drawRect(posX: Int, posY: Int, width: Int, height: Int, color: Color? = null, lineWidth: Float? = null) {
        this.setOptions(color, lineWidth)

        GL11.glBegin(GL11.GL_LINE_STRIP)
        GL11.glVertex2d(posX.toDouble(), posY.toDouble())
        GL11.glVertex2d(posX.toDouble(), (posY + height).toDouble())

        GL11.glVertex2d(posX.toDouble(), (posY + height).toDouble())
        GL11.glVertex2d((posX + width).toDouble(), (posY + height).toDouble())

        GL11.glVertex2d((posX + width).toDouble(), (posY + height).toDouble())
        GL11.glVertex2d((posX + width).toDouble(), (posY).toDouble())

        GL11.glVertex2d((posX + width).toDouble(), (posY).toDouble())
        GL11.glVertex2d(posX.toDouble(), posY.toDouble())

        GL11.glEnd()
    }

    /**
     * Fill a circle at the given position with the given color.
     *
     * @param posX circle on-screen x position
     * @param posY circle on-screen y position
     * @param radius circle radius
     * @param color circle fill color
     */
    fun fillCircle(posX: Int, posY: Int, radius: Double, color: Color? = null) {
        this.fillCircleLike(posX, posY, radius, 360, color)
    }

    fun fillCircleLike(posX: Int, posY: Int, radius: Double, edges: Int, color: Color? = null) {
        fillPartialCircleLike(posX, posY, radius, edges, 0, edges - 1, color)
    }

    fun drawCircleLike(posX: Int, posY: Int, radius: Double, edges: Int, color: Color? = null, lineWidth: Float? = null) {
        drawPartialCircleLike(posX, posY, radius, edges, 0, edges, color, lineWidth)
    }

    fun fillPartialCircleLike(posX: Int, posY: Int, radius: Double, edges: Int, beginVertex: Int, endVertex: Int, color: Color? = null) {
        setOptions(color)

        GL11.glBegin(GL11.GL_POLYGON)
        for (i in (beginVertex .. endVertex)) {
            GL11.glVertex2d(posX + sin(i / edges.toDouble() * 2 * PI) * radius, posY + cos(i / edges.toDouble() * 2 * PI) * radius)
        }
        GL11.glEnd()
    }

    fun drawPartialCircleLike(posX: Int, posY: Int, radius: Double, edges: Int, beginVertex: Int, endVertex: Int, color: Color? = null,
                              lineWidth: Float? = null) {
        this.setOptions(color, lineWidth)

        GL11.glBegin(GL11.GL_LINE_STRIP)
        for (i in beginVertex until endVertex) {
            GL11.glVertex2d(posX + sin(i / edges.toDouble() * 2 * PI) * radius, posY + cos(i / edges.toDouble() * 2 * PI) * radius)
            GL11.glVertex2d(posX + sin((i + 1 % edges) / edges.toDouble() * 2 * PI) * radius,
                    posY + cos((i + 1 % edges) / edges.toDouble() * 2 * PI) * radius)
        }

        GL11.glEnd()
    }

    fun drawLine3d(sourcePosX: Double, sourcePosY: Double, sourcePosZ: Double, targetPosX: Double, targetPosY: Double, targetPosZ: Double,
                   color: Color? = null, lineWidth: Float? = null) {
        this.setOptions(color, lineWidth)

        GL11.glBegin(GL11.GL_LINE_STRIP)
        GL11.glVertex3d(sourcePosX, sourcePosY, sourcePosZ)
        GL11.glVertex3d(targetPosX, targetPosY, targetPosZ)
        GL11.glEnd()
    }
}