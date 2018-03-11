package net.cydhra.vibrant.util.shader

import org.lwjgl.opengl.ARBFragmentShader
import org.lwjgl.opengl.ARBShaderObjects
import org.lwjgl.opengl.ARBVertexShader
import org.lwjgl.opengl.GL11

/**
 * A wrapper for OpenGL shader code that is loaded into and compiled in the graphics card. The wrapper is able to reload and recompile
 * the source code. This is especially useful for debugging purpose.
 */
class VibrantShader(private val pathToCode: String, private val shaderType: ShaderType) {

    private var sourceCode: String = ""

    var shaderObjectId: Int = GL11.GL_NONE
        private set

    /**
     * Load or reload the shader source code from a resource specified by [pathToCode]
     */
    fun loadSourceCode() {
        sourceCode = this.javaClass.getResourceAsStream(pathToCode).reader().readText()
    }

    /**
     * Compile the shader with previously loaded source code
     */
    fun compile() {
        if (sourceCode.isEmpty()) {
            throw IllegalStateException("No shader source code loaded")
        }

        shaderObjectId = ARBShaderObjects.glCreateShaderObjectARB(shaderType.GL_SHADER_TYPE)
        ARBShaderObjects.glShaderSourceARB(shaderObjectId, sourceCode)
        ARBShaderObjects.glCompileShaderARB(shaderObjectId)

        if (ARBShaderObjects.glGetObjectParameteriARB(shaderObjectId, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE) {
            throw ShaderCompileException(shaderType, ARBShaderObjects.glGetInfoLogARB(shaderObjectId,
                    ARBShaderObjects.glGetObjectParameteriARB(shaderObjectId, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB)))
        }
    }

    enum class ShaderType(val GL_SHADER_TYPE: Int) {
        VERTEX_SHADER(ARBVertexShader.GL_VERTEX_SHADER_ARB),
        FRAGMENT_SHADER(ARBFragmentShader.GL_FRAGMENT_SHADER_ARB)
    }
}