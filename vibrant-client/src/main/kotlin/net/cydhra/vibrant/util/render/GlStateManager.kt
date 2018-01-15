package net.cydhra.vibrant.util.render

import org.lwjgl.opengl.GL11

/**
 *
 */

object GlStateManager {

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

    fun pushMatrix() = GL11.glPushMatrix()
    fun popMatrix() = GL11.glPopMatrix()
}

