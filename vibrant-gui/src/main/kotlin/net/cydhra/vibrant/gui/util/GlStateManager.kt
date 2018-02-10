package net.cydhra.vibrant.gui.util

import org.lwjgl.opengl.GL11
import org.lwjgl.util.vector.Vector3f
import java.awt.Color
import java.nio.FloatBuffer

/**
 *
 */

object GlStateManager {

    private var argbState: Int = 0

    private val colorBuffer = AllocationHelper.createDirectFloatBuffer(16)
    private val LIGHT0_POS = Vector3f(0.2f, 1.0f, -0.7f).apply { normalise() }
    private val LIGHT1_POS = Vector3f(-0.2f, 1.0f, 0.7f).apply { normalise() }

    fun pushState() {
        GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT)
        GL11.glPushAttrib(GL11.GL_CURRENT_BIT)
        GL11.glPushAttrib(GL11.GL_DEPTH_BUFFER_BIT)
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT)
        GL11.glPushAttrib(GL11.GL_LIGHTING_BIT)
        GL11.glPushAttrib(GL11.GL_LINE_BIT)
        GL11.glPushAttrib(GL11.GL_POINT_BIT)
    }

    fun popState() {
        GL11.glPopAttrib()
        GL11.glPopAttrib()
        GL11.glPopAttrib()
        GL11.glPopAttrib()
        GL11.glPopAttrib()
        GL11.glPopAttrib()
        GL11.glPopAttrib()
    }

    fun disableDepthTest() = GL11.glDisable(GL11.GL_DEPTH_TEST)
    fun enableDepthTest() = GL11.glEnable(GL11.GL_DEPTH_TEST)

    fun enableTexture2D() = GL11.glEnable(GL11.GL_TEXTURE_2D)
    fun disableTexture2D() = GL11.glDisable(GL11.GL_TEXTURE_2D)

    fun disableColorBlending() = GL11.glDisable(GL11.GL_BLEND)
    fun enableColorBlending() = GL11.glEnable(GL11.GL_BLEND)

    fun enableLineSmoothing() = GL11.glEnable(GL11.GL_LINE_SMOOTH)
    fun disableLineSmoothing() = GL11.glDisable(GL11.GL_LINE_SMOOTH)

    fun enableDepthMask() = GL11.glDepthMask(true)
    fun disableDepthMask() = GL11.glDepthMask(false)

    fun enableCullFace() = GL11.glEnable(GL11.GL_CULL_FACE)
    fun disableCullFace() = GL11.glDisable(GL11.GL_CULL_FACE)

    fun pushMatrix() = GL11.glPushMatrix()
    fun popMatrix() = GL11.glPopMatrix()

    fun enableLighting() = GL11.glEnable(GL11.GL_LIGHTING)
    fun disableLighting() = GL11.glDisable(GL11.GL_LIGHTING)

    fun enableLight(number: Int) = GL11.glEnable(GL11.GL_LIGHT0 + number)
    fun disableLight(number: Int) = GL11.glDisable(GL11.GL_LIGHT0 + number)

    fun enableColorMaterial() = GL11.glEnable(GL11.GL_COLOR_MATERIAL)
    fun disableColorMaterial() = GL11.glDisable(GL11.GL_COLOR_MATERIAL)

    fun colorMaterial(face: Int, mode: Int) = GL11.glColorMaterial(face, mode)

    fun shadeModel(mode: Int) = GL11.glShadeModel(mode)

    fun clear(mask: Int) = GL11.glClear(mask)

    fun clearColor(red: Float, green: Float, blue: Float, alpha: Float) = GL11.glClearColor(red, green, blue, alpha)

    /**
     * Set the color attribute of OpenGL, if it is not already set on that value
     */
    fun color(color: Color) {
        val alpha = color.alpha / 255.0f
        val red = color.red / 255.0f
        val green = color.green / 255.0f
        val blue = color.blue / 255.0f

        GL11.glColor4f(red, green, blue, alpha)
        this.argbState = color.rgb
    }

    fun disableStandardItemLighting() {
        disableLighting()
        disableLight(0)
        disableLight(1)
        disableColorMaterial()
    }

    fun enableStandardItemLighting() {
        enableLighting()
        enableLight(0)
        enableLight(1)
        enableColorMaterial()
        colorMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_AMBIENT_AND_DIFFUSE)
        val f = 0.4f
        val f1 = 0.6f
        val f2 = 0.0f
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION,
                setupBuffer(LIGHT0_POS.x, LIGHT0_POS.y, LIGHT0_POS.z, 0f))
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, setupBuffer(f1, f1, f1, 1.0f))
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, setupBuffer(0.0f, 0.0f, 0.0f, 1.0f))
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, setupBuffer(f2, f2, f2, 1.0f))
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, setupBuffer(LIGHT1_POS.x, LIGHT1_POS.y, LIGHT1_POS.z, 0f))
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, setupBuffer(f1, f1, f1, 1.0f))
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_AMBIENT, setupBuffer(0.0f, 0.0f, 0.0f, 1.0f))
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_SPECULAR, setupBuffer(f2, f2, f2, 1.0f))
        shadeModel(GL11.GL_FLAT)
        GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, setupBuffer(f, f, f, 1.0f))
    }

    private fun setupBuffer(x: Float, y: Float, z: Float, alpha: Float): FloatBuffer {
        colorBuffer.clear()
        colorBuffer.put(x).put(y).put(z).put(alpha)
        colorBuffer.flip()
        return colorBuffer
    }

}

