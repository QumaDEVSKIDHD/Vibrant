package net.cydhra.vibrant.util.shader

import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.api.render.VibrantFramebuffer
import org.lwjgl.opengl.Display
import org.lwjgl.opengl.GL11

/**
 * A wrapper for minecraft framebuffer wrapper to add more functionality and
 * wrap unchanged stuff (width, height)
 *
 * @author Flaflo
 * @author Cydhra
 */
class GLFramebuffer
/**
 * Create a gl framebuffer to render extra stuff to it
 *
 * @param useDepth whether to use depth
 * @param width the width
 * @param height the height
 */
constructor(private val isUsingDepth: Boolean, width: Int = VibrantClient.minecraft.displayWidth, height: Int = VibrantClient.minecraft.displayHeight) {
    private var framebuffer: VibrantFramebuffer? = null

    var isBound: Boolean = false
        private set
    var isDeleted: Boolean = false
        private set

    val width: Float
        get() = framebuffer!!.width.toFloat()

    val height: Float
        get() = framebuffer!!.height.toFloat()

    val textureWidth: Float
        get() = framebuffer!!.textureWidth.toFloat()

    val textureHeight: Float
        get() = framebuffer!!.textureHeight.toFloat()

    init {
        this.framebuffer = VibrantClient.factory.newFramebuffer(width, height,
                isUsingDepth)
    }

    /**
     * Resize the framebuffer
     *
     * @param width the width
     * @param height the height
     */
    fun update(width: Int, height: Int) {
        this.framebuffer!!.deleteFramebuffer()
        this.framebuffer = VibrantClient.factory.newFramebuffer(width, height, this.isUsingDepth)
    }

    /**
     * Resize the framebuffer to the size of the display
     */
    fun update() {
        if (framebuffer!!.width != Display.getWidth() || framebuffer!!.height != Display.getHeight()) {
            framebuffer!!.deleteFramebuffer()
            framebuffer = VibrantClient.factory.newFramebuffer(Display.getWidth(), Display.getHeight(), this.isUsingDepth)
        }
    }

    fun updateAndBind(width: Int, height: Int) {
        this.bind()
        this.update(width, height)
    }

    /**
     * Destroy framebuffer
     */
    fun delete() {
        this.framebuffer!!.deleteFramebuffer()
        this.isDeleted = true
    }

    /**
     * Bind framebuffer to OpenGL
     */
    fun bind() {
        this.framebuffer!!.bindFramebuffer(false)
        this.isBound = true
    }

    /**
     * Unbind the framebuffer from OpenGL
     */
    fun unbind() {
        this.framebuffer!!.unbindFramebuffer()
        this.isBound = false
    }

    fun rebindMinecraftFramebuffer() {
        VibrantClient.minecraft.framebuffer?.bindFramebuffer(true)
        this.isBound = false
    }

    fun getFramebuffer(): VibrantFramebuffer? {
        return framebuffer
    }

    /**
     * Draw the framebuffer onto minecraft framebuffer as a 2D texture.
     */
    fun drawOntoMinecraftFramebuffer() {
        drawFramebuffer(this.framebuffer!!)
    }

    companion object {

        fun drawFramebuffer(framebuffer: VibrantFramebuffer) {
            val scaledResolution = VibrantClient.factory.newScaledResolution()
            val scaledWidth = scaledResolution.getScaledWidth()
            val scaledHeight = scaledResolution.getScaledHeight()

            framebuffer.bindFramebufferTexture()
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

}

