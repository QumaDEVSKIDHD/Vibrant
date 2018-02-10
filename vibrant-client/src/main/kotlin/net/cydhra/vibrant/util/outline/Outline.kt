package net.cydhra.vibrant.util.outline

import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.gui.util.GlStateManager
import net.cydhra.vibrant.util.shader.GLFramebuffer
import net.cydhra.vibrant.util.shader.GLShader
import org.lwjgl.opengl.Display
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11.*
import java.awt.Color

/**
 * Outline utility for 3D objects
 *
 * @author Flaflo
 */
open class Outline {

    /**
     * @return the drawn on framebuffer
     */
    var framebuffer: GLFramebuffer? = null
        private set
    private var shader: GLShader? = null

    private var lineWidth: Float = 0F
    private var avgDivisor: Float = 0F
    private var sampleRadius: Int = 0

    private var color: Color? = null

    /**
     * @return true if the outline is ready to render
     */
    val isReady: Boolean
        get() = this.shader != null && this.framebuffer != null && !this.shader!!.isDeleted && !this.framebuffer!!.isDeleted

    init {
        this.color = Color.WHITE
        this.lineWidth = 1f
        this.sampleRadius = 1
        this.avgDivisor = 60f
    }

    /**
     * This should be called to start rendering the outlines in 3D
     */
    open fun startOutline() {
        this.checkSetup()

        this.framebuffer!!.update(Display.getWidth(), Display.getHeight())
        this.framebuffer!!.bind()

        GlStateManager.clearColor(0F, 0F, 0F, 0F)
        GlStateManager.clear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
    }

    /**
     * This should be called after rendering in 3D
     */
    fun executeOutline() {
        this.checkSetup()

        VibrantClient.minecraft.framebuffer!!.bindFramebuffer(false)
    }

    /**
     * Draws the outlines and ends drawing sequence
     */
    open fun endOutline() {
        this.checkSetup()

        glDisable(GL11.GL_DEPTH_TEST)
        glEnable(GL11.GL_BLEND)
        glDisable(GL11.GL_TEXTURE_2D)
        glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
        glEnable(GL11.GL_LINE_SMOOTH)

        this.shader!!.bind()

        this.setDiffuseSampler()
        this.setSampleRadius(this.sampleRadius)
        this.setAverageDivisor(this.avgDivisor)
        this.setLineWidth(this.lineWidth)
        this.setColor(this.color)

        this.framebuffer!!.drawOntoMinecraftFramebuffer()
        this.shader!!.unbind()

        glEnable(GL11.GL_DEPTH_TEST)
        glEnable(GL11.GL_TEXTURE_2D)
    }

    /**
     * Checks the shader and framebuffer. The shader gets recompiled if needed
     */
    fun checkSetup() {
        val ready = this.isReady

        if (!ready) {
            this.framebuffer = GLFramebuffer(true, Display.getWidth(), Display.getHeight())
            this.shader = GLShader()
                    .compileAndAppendShader(GLShader.ShaderType.VERTEX_SHADER, GLShader.TEX0_VERTEX_SHADER)
                    .compileAndAppendShader(GLShader.ShaderType.FRAGMENT_SHADER, AVERAGE_OUTLINE_FRAGMENT_SHADER)
                    .link()
        }
    }

    /**
     * @return true if uniforming is possible
     */
    fun canUniform(): Boolean {
        return this.isReady && this.shader!!.isBound
    }

    /**
     */
    private fun setDiffuseSampler() {
        if (this.canUniform()) {
            this.shader!!.uniform("diffuseSampler", 0)
        }
    }

    /**
     * @param divisor the average divisor
     */
    fun setAverageDivisor(divisor: Float) {
        if (this.canUniform()) {
            this.shader!!.uniform("avgDivisor", divisor)
        }

        this.avgDivisor = divisor
    }

    /**
     * @param radius the radius
     */
    fun setSampleRadius(radius: Int) {
        if (this.canUniform()) {
            this.shader!!.uniform("sampleRadius", radius)
        }

        this.sampleRadius = radius
    }

    /**
     * @param color the color
     */
    fun setColor(color: Color?) {
        if (this.canUniform()) {
            this.shader!!.uniform("s_color", color!!.red / 255f, color.green / 255f, color.blue / 255f)
        }

        this.color = color
    }

    /**
     * @param lineWidth the line width
     */
    fun setLineWidth(lineWidth: Float) {
        if (this.canUniform()) {
            this.shader!!.uniform("texelSize", 1.0f / this.framebuffer!!.textureWidth * lineWidth, 1.0f / this.framebuffer!!.textureHeight * lineWidth)
        }

        this.lineWidth = lineWidth
    }

    /**
     * @return the color
     */
    fun getColor(): Color? {
        return color
    }

    companion object {

        /**
         * The average outline fragment shader
         */
        private val AVERAGE_OUTLINE_FRAGMENT_SHADER = ("#version 120\nuniform float avgDivisor; uniform sampler2D diffuseSampler;"
                + "uniform int sampleRadius; uniform vec2 texelSize; uniform vec3 s_color; void main(){ vec4 "
                + "centerCol = texture2D(diffuseSampler, gl_TexCoord[0].st); if(centerCol.a != 0) { gl_FragColor = "
                + "vec4(0, 0, 0, 0); return; } float colAvg = 0; for(int x = -sampleRadius; x <= sampleRadius; x++) { for(int y = -sampleRadius; y <= sampleRadius; y++) { vec4 "
                + "currCol = texture2D(diffuseSampler, gl_TexCoord[0].st + vec2(x * texelSize.x, y * texelSize.y));"
                + "if(currCol.a != 0) { colAvg += max(0,"
                + " (8.0F - sqrt(x * x + y * y)) / 2.0F);}}}colAvg /= avgDivisor; gl_FragColor = vec4(s_color, colAvg);}")

    }
}
