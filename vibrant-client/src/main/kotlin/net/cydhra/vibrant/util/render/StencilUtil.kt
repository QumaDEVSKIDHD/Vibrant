package net.cydhra.vibrant.util.render

import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.util.render.StencilUtil.StencilMode.CROP_INSIDE
import net.cydhra.vibrant.util.render.StencilUtil.StencilMode.CROP_OUTSIDE
import org.lwjgl.opengl.EXTFramebufferObject
import org.lwjgl.opengl.EXTPackedDepthStencil
import org.lwjgl.opengl.GL11

/**
 *
 */
object StencilUtil {

    /**
     * Enable the stencil buffer filling. Everything drawn from now on until a call of [enableStencil] will be drawn onto the stencil
     * buffer and will work as a stencil on every render call after [enableStencil]
     */
    fun setupStencil() {
        GL11.glPushMatrix()

        val fbo = VibrantClient.minecraft.framebuffer
        if (fbo != null) {
            if (fbo.depthBuffer > -1) {
                EXTFramebufferObject.glDeleteRenderbuffersEXT(fbo.depthBuffer)
                val stencil_depth_buffer_ID = EXTFramebufferObject.glGenRenderbuffersEXT()
                EXTFramebufferObject.glBindRenderbufferEXT(EXTFramebufferObject.GL_RENDERBUFFER_EXT, stencil_depth_buffer_ID)
                EXTFramebufferObject.glRenderbufferStorageEXT(EXTFramebufferObject.GL_RENDERBUFFER_EXT,
                        EXTPackedDepthStencil.GL_DEPTH_STENCIL_EXT, VibrantClient.minecraft.displayWidth, VibrantClient.minecraft.displayHeight)
                EXTFramebufferObject.glFramebufferRenderbufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT,
                        EXTFramebufferObject.GL_STENCIL_ATTACHMENT_EXT, EXTFramebufferObject.GL_RENDERBUFFER_EXT,
                        stencil_depth_buffer_ID)
                EXTFramebufferObject.glFramebufferRenderbufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT,
                        EXTFramebufferObject.GL_DEPTH_ATTACHMENT_EXT, EXTFramebufferObject.GL_RENDERBUFFER_EXT,
                        stencil_depth_buffer_ID)
            }

            fbo.depthBuffer = -1
        }

        GL11.glClearStencil(0)
        GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT)
        GL11.glEnable(GL11.GL_STENCIL_TEST)

        GL11.glStencilFunc(GL11.GL_ALWAYS, 1, -1)
        GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_REPLACE, GL11.GL_REPLACE)
    }

    /**
     * Apply the stencil buffer onto every following render call until a call to [endStencil]
     *
     * @param mode [StencilMode] to be applied
     */
    fun enableStencil(mode: StencilMode) {
        GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_KEEP)
        GL11.glStencilFunc(mode.maskBit, 0, -1)
    }

    fun endStencil() {
        GL11.glStencilFunc(GL11.GL_ALWAYS, 0, -1)
        GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_REPLACE, GL11.GL_REPLACE)
        GL11.glPopMatrix()
    }

    /**
     * Stencil mode defines whether to delete pixels inside the stencil buffer mask [CROP_INSIDE] or outside the mask [CROP_OUTSIDE]
     */
    enum class StencilMode(internal val maskBit: Int) {
        CROP_INSIDE(GL11.GL_EQUAL), CROP_OUTSIDE(GL11.GL_NOTEQUAL)
    }
}