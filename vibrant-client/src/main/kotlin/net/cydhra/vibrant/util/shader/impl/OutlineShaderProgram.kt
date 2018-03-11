package net.cydhra.vibrant.util.shader.impl

import net.cydhra.vibrant.util.shader.*
import org.lwjgl.opengl.Display
import org.lwjgl.util.vector.Vector2f
import java.awt.Color

/**
 *
 */
class OutlineShaderProgram : VibrantShaderProgram() {

    private val outlineVertexShader = VibrantShader("/glsl/Tex0VertexShader.glsl", VibrantShader.ShaderType.VERTEX_SHADER)
    private val outlineFragmentShader = VibrantShader("/glsl/OutlineShader.glsl", VibrantShader.ShaderType.FRAGMENT_SHADER)

    var lineWidth by uniform(this, "lineWidth", 1F)
    var averageDivisor by uniform(this, "avgDivisor", 60f)
    var sampleRadius by uniform(this, "sampleRadius", 1)
    var maxSampleRadius by uniform(this, "maxSampleRadius", 1)
    val diffuseSampler by uniform(this, "diffuseSampler", 0)
    val color by uniform(this, "s_color", Color.WHITE)
    val texelSize by uniform(this, "texelSize", Vector2f(1 / Display.getWidth().toFloat(), 1 / Display.getHeight().toFloat()))

    init {
        try {
            outlineVertexShader.loadSourceCode()
            outlineVertexShader.compile()

            outlineFragmentShader.loadSourceCode()
            outlineFragmentShader.compile()

            this.appendShader(outlineVertexShader)
            this.appendShader(outlineFragmentShader)
            this.link()
        } catch (e: ShaderCompileException) {
            e.printStackTrace()
        } catch (e: ShaderLinkException) {
            e.printStackTrace()
        }
    }
}