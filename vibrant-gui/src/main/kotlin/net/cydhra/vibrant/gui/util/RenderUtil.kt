@file:Suppress("NOTHING_TO_INLINE")

package net.cydhra.vibrant.gui.util

import net.cydhra.vibrant.gui.GuiManager
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.Display
import org.lwjgl.opengl.GL11.*
import org.lwjgl.util.glu.GLU.gluProject
import org.lwjgl.util.vector.Vector2f
import org.lwjgl.util.vector.Vector3f
import java.awt.Color
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


/**
 *
 */
object RenderUtil {

    private var gluProjectModelViewMatrix = BufferUtils.createFloatBuffer(16)
    private var gluProjectProjectionMatrix = BufferUtils.createFloatBuffer(16)
    private var gluProjectModelViewportMatrix = BufferUtils.createIntBuffer(16)

    private var gluProjectPosition = BufferUtils.createFloatBuffer(3)

    /**
     * Set the OpenGL rendering color
     *
     * @param color [Color] instance
     */
    fun setColor(color: Color) {
        GuiManager.glStateManager.color(color)
    }

    private fun setOptions(color: Color? = null, lineWidth: Float? = null) {
        if (color != null)
            this.setColor(color)

        if (lineWidth != null)
            glLineWidth(lineWidth)

    }

    fun drawLine(startVertexX: Int, startVertexY: Int, endVertexX: Int, endVertexY: Int, color: Color? = null, lineWidth: Float? = null) {
        setOptions(color, lineWidth)

        glBegin(GL_LINE_STRIP)
        glVertex2d(startVertexX.toDouble(), startVertexY.toDouble())
        glVertex2d(endVertexX.toDouble(), endVertexY.toDouble())
        glEnd()
    }

    fun fillRect(posX: Int, posY: Int, width: Int, height: Int, color: Color? = null) {
        this.setOptions(color)

        glBegin(GL_QUADS)
        glVertex2d(posX.toDouble(), posY.toDouble())
        glVertex2d(posX.toDouble(), (posY + height).toDouble())
        glVertex2d((posX + width).toDouble(), (posY + height).toDouble())
        glVertex2d((posX + width).toDouble(), posY.toDouble())
        glEnd()
    }

    fun fillRect(posX: Float, posY: Float, width: Float, height: Float, color: Color? = null) {
        this.setOptions(color)

        glBegin(GL_QUADS)
        glVertex2f(posX, posY)
        glVertex2f(posX, (posY + height))
        glVertex2f((posX + width), (posY + height))
        glVertex2f((posX + width), posY)
        glEnd()
    }

    fun drawRect(posX: Int, posY: Int, width: Int, height: Int, color: Color? = null, lineWidth: Float? = null) {
        this.setOptions(color, lineWidth)

        glBegin(GL_LINE_STRIP)
        glVertex2d(posX.toDouble(), posY.toDouble())
        glVertex2d(posX.toDouble(), (posY + height).toDouble())

        glVertex2d(posX.toDouble(), (posY + height).toDouble())
        glVertex2d((posX + width).toDouble(), (posY + height).toDouble())

        glVertex2d((posX + width).toDouble(), (posY + height).toDouble())
        glVertex2d((posX + width).toDouble(), (posY).toDouble())

        glVertex2d((posX + width).toDouble(), (posY).toDouble())
        glVertex2d(posX.toDouble(), posY.toDouble())

        glEnd()
    }

    fun drawRect(posX: Float, posY: Float, width: Float, height: Float, color: Color? = null, lineWidth: Float? = null) {
        this.setOptions(color, lineWidth)

        glBegin(GL_LINE_STRIP)
        glVertex2f(posX, posY)
        glVertex2f(posX, posY + height)

        glVertex2f(posX, posY + height)
        glVertex2f(posX + width, posY + height)

        glVertex2f(posX + width, posY + height)
        glVertex2f(posX + width, posY)

        glVertex2f(posX + width, posY)
        glVertex2f(posX, posY)

        glEnd()
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

        glBegin(GL_POLYGON)
        for (i in (beginVertex..endVertex)) {
            glVertex2d(posX + sin(i / edges.toDouble() * 2 * PI) * radius, posY + cos(i / edges.toDouble() * 2 * PI) * radius)
        }
        glEnd()
    }

    fun drawPartialCircleLike(posX: Int, posY: Int, radius: Double, edges: Int, beginVertex: Int, endVertex: Int, color: Color? = null,
                              lineWidth: Float? = null) {
        this.setOptions(color, lineWidth)

        glBegin(GL_LINE_STRIP)
        for (i in beginVertex until endVertex) {
            glVertex2d(posX + sin(i / edges.toDouble() * 2 * PI) * radius, posY + cos(i / edges.toDouble() * 2 * PI) * radius)
            glVertex2d(posX + sin((i + 1 % edges) / edges.toDouble() * 2 * PI) * radius,
                    posY + cos((i + 1 % edges) / edges.toDouble() * 2 * PI) * radius)
        }

        glEnd()
    }

    fun drawLine3d(sourcePosX: Double, sourcePosY: Double, sourcePosZ: Double, targetPosX: Double, targetPosY: Double, targetPosZ: Double,
                   color: Color? = null, lineWidth: Float? = null) {
        this.setOptions(color, lineWidth)

        glBegin(GL_LINE_STRIP)
        glVertex3d(sourcePosX, sourcePosY, sourcePosZ)
        glVertex3d(targetPosX, targetPosY, targetPosZ)
        glEnd()
    }

    fun outlineCube(minX: Double, minY: Double, minZ: Double, maxX: Double, maxY: Double, maxZ: Double, color: Color? = null, lineWidth: Float? = null) {
        setOptions(color, lineWidth)

        glBegin(GL_LINE_STRIP)
        glVertex3d(minX, minY, minZ)
        glVertex3d(maxX, minY, minZ)
        glVertex3d(maxX, minY, maxZ)
        glVertex3d(minX, minY, maxZ)
        glVertex3d(minX, minY, minZ)
        glEnd()

        glBegin(GL_LINE_STRIP)
        glVertex3d(minX, maxY, minZ)
        glVertex3d(maxX, maxY, minZ)
        glVertex3d(maxX, maxY, maxZ)
        glVertex3d(minX, maxY, maxZ)
        glVertex3d(minX, maxY, minZ)
        glEnd()

        glBegin(GL_LINES)
        glVertex3d(minX, minY, minZ)
        glVertex3d(minX, maxY, minZ)
        glVertex3d(maxX, minY, minZ)
        glVertex3d(maxX, maxY, minZ)
        glVertex3d(maxX, minY, maxZ)
        glVertex3d(maxX, maxY, maxZ)
        glVertex3d(minX, minY, maxZ)
        glVertex3d(minX, maxY, maxZ)
        glEnd()
    }

    fun interpolate(now: Double, then: Double, ticks: Float): Double {
        return then + (now - then) * ticks
    }

    fun project2d(x: Float, y: Float, z: Float, scaleFactor: Int): Vector2f {
        val projected3d = project3d(x, y, z, scaleFactor)

        return Vector2f(projected3d.x, projected3d.y)
    }

    fun project3d(x: Float, y: Float, z: Float, scaleFactor: Int): Vector3f {
        //Clear buffers
        gluProjectPosition.clear()

        gluProjectModelViewMatrix.clear()
        gluProjectProjectionMatrix.clear()
        gluProjectModelViewportMatrix.clear()

        //Refill buffers
        glGetFloat(GL_MODELVIEW_MATRIX, gluProjectModelViewMatrix)
        glGetFloat(GL_PROJECTION_MATRIX, gluProjectProjectionMatrix)
        glGetInteger(GL_VIEWPORT, gluProjectModelViewportMatrix)

        //Project and return position buffer as new vector3f
        return if (gluProject(x, y, z, gluProjectModelViewMatrix, gluProjectProjectionMatrix, gluProjectModelViewportMatrix, gluProjectPosition))
            Vector3f(gluProjectPosition[0] / scaleFactor, (Display.getHeight() - gluProjectPosition[1]) / scaleFactor, gluProjectPosition[2] / scaleFactor)
        else
            Vector3f(0F, 0F, 0F)
    }
}