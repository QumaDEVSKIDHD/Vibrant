package net.cydhra.vibrant.util.framebuffer

import net.cydhra.vibrant.gui.util.GlStateManager
import net.cydhra.vibrant.util.shader.ShaderLibrary
import org.lwjgl.opengl.Display
import org.lwjgl.opengl.GL11

/**
 *
 */
open class OutlineFramebuffer(width: Int, height: Int) : Framebuffer(width, height, true) {

    override fun bind() {
        this.update(Display.getWidth(), Display.getHeight())
        doBind()

        GlStateManager.clearColor(0F, 0F, 0F, 0F)
        GlStateManager.clear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
    }

    override fun drawOntoCurrentFramebuffer() {
        GlStateManager.disableDepthTest()
        GlStateManager.enableColorBlending()
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
        GlStateManager.enableLineSmoothing()

        ShaderLibrary.outlineShaderProgramProgram.bind()
        this.doDrawOntoCurrentFramebuffer()
        ShaderLibrary.outlineShaderProgramProgram.unbind()

        GlStateManager.enableDepthMask()
    }
}