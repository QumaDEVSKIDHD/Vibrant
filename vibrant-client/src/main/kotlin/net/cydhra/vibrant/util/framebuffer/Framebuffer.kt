package net.cydhra.vibrant.util.framebuffer

import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.api.render.VibrantFramebuffer
import org.lwjgl.opengl.GL11

/**
 *
 */
open class Framebuffer(
        width: Int = VibrantClient.minecraft.displayWidth,
        height: Int = VibrantClient.minecraft.displayHeight,
        private val isUsingDepth: Boolean) {

    var width = width
        private set

    var height = height
        private set

    var framebuffer: VibrantFramebuffer = VibrantClient.factory.newFramebuffer(width, height, isUsingDepth)
        private set

    /**
     * Update the framebuffer with new width and height. Does nothing when the framebuffer is already of the right size.
     *
     * @param width new framebuffer width
     * @param height new framebuffer height
     */
    fun update(width: Int, height: Int) {
        if (width != this.width || height != this.height) {
            this.framebuffer.deleteFramebuffer()
            this.framebuffer = VibrantClient.factory.newFramebuffer(width, height, this.isUsingDepth)

            this.width = width
            this.height = height
        }
    }

    open fun bind() {
        doBind()
    }

    /**
     * Unbind the framebuffer. Note: By default this automatically rebinds the minecraft framebuffer. Overwrite this and call [doUnbind]
     * to not rebind that.
     */
    open fun unbind() {
        doRebindMinecraftFramebuffer()
    }

    open fun drawOntoCurrentFramebuffer() {
        doDrawOntoCurrentFramebuffer()
    }

    protected fun doBind() {
        this.framebuffer.bindFramebuffer(false)
    }

    protected fun doUnbind() {
        this.framebuffer.unbindFramebuffer()
    }

    protected fun doRebindMinecraftFramebuffer() {
        VibrantClient.minecraft.framebuffer!!.bindFramebuffer(false)
    }

    fun delete() {
        this.framebuffer.deleteFramebuffer()
    }

    // TODO use a vbo or some other fancy shit for that
    protected fun doDrawOntoCurrentFramebuffer() {
        val scaledResolution = VibrantClient.factory.newScaledResolution()
        val scaledWidth = scaledResolution.getScaledWidth()
        val scaledHeight = scaledResolution.getScaledHeight()

        this.framebuffer.bindFramebufferTexture()
        GL11.glBegin(GL11.GL_QUADS)
        GL11.glTexCoord2d(0.0, 1.0)
        GL11.glVertex2d(0.0, 0.0)
        GL11.glTexCoord2d(0.0, 0.0)
        GL11.glVertex2d(0.0, scaledHeight.toDouble())
        GL11.glTexCoord2d(1.0, 0.0)
        GL11.glVertex2d(scaledWidth.toDouble(), scaledHeight.toDouble())
        GL11.glTexCoord2d(1.0, 1.0)
        GL11.glVertex2d(scaledWidth.toDouble(), 0.0)
        GL11.glEnd()
    }
}