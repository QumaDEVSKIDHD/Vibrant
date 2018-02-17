package net.cydhra.vibrant.util.shader

import org.lwjgl.opengl.ARBFragmentShader
import org.lwjgl.opengl.ARBShaderObjects.*
import org.lwjgl.opengl.ARBVertexShader
import org.lwjgl.opengl.GL11

/**
 * A GL shader wrapper class
 *
 * @author Flaflo
 * @author Cydhra
 */
class GLShader {

    val programId: Int = glCreateProgramObjectARB()
    private var state = State.CONSTRUCTION

    /**
     * @return true if the shader is bound
     */
    var isBound: Boolean = false
        private set
    /**
     * @return true if the shader is deleted
     */
    var isDeleted: Boolean = false
        private set

    /**
     * Compiles and attaches a shader from source code to this shader object
     * wrapper
     *
     * @param type             shader type
     * @param shaderSourceCode shader source code
     * @see ShaderType
     */
    fun compileAndAppendShader(type: ShaderType, shaderSourceCode: String): GLShader {
        if (this.state == State.LINKED) {
            throw IllegalStateException("The shader is already linked. No more shaders can be attached.")
        }

        val shaderObject = glCreateShaderObjectARB(type.GL_SHADER_TYPE)
        glShaderSourceARB(shaderObject, shaderSourceCode)
        glCompileShaderARB(shaderObject)

        // on error
        if (glGetObjectParameteriARB(shaderObject, GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE) {
            throw ShaderCompileException(type, glGetInfoLogARB(shaderObject,
                    glGetObjectParameteriARB(shaderObject, GL_OBJECT_INFO_LOG_LENGTH_ARB)))
        }

        // else attach shader to this shader object
        glAttachObjectARB(this.programId, shaderObject)
        return this
    }

    /**
     * Links the shader and finishes it. No more shaders can be attached
     */
    fun link(): GLShader {
        if (this.state == State.LINKED) {
            return this
        }

        glLinkProgramARB(this.programId)
        if (glGetObjectParameteriARB(this.programId,
                        GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_FALSE) {
            throw ShaderLinkException(glGetInfoLogARB(this.programId,
                    glGetObjectParameteriARB(this.programId, GL_OBJECT_INFO_LOG_LENGTH_ARB)))
        }

        // validate shader
        glValidateProgramARB(this.programId)
        if (glGetObjectParameteriARB(this.programId,
                        GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_FALSE) {
            throw ShaderLinkException(glGetInfoLogARB(this.programId,
                    glGetObjectParameteriARB(this.programId, GL_OBJECT_INFO_LOG_LENGTH_ARB)))
        }

        this.state = State.LINKED

        return this
    }

    /**
     * Loads a shader to the GPU and enables it for current render context
     */
    fun bind() {
        glUseProgramObjectARB(this.programId)
        this.isBound = true
    }

    /**
     * Disable the shader for current render context
     */
    fun unbind() {
        glUseProgramObjectARB(0)
        this.isBound = false
    }

    /**
     * Deletes the shader
     */
    fun delete() {
        glDeleteObjectARB(this.programId)
        this.isDeleted = true
    }

    /**
     * Get a uniform location by its name
     *
     * @param variableName the name of uniform variable
     * @return the location of the uniform
     */
    fun getUniformLocation(variableName: String): Int {
        return glGetUniformLocationARB(this.programId, variableName)
    }

    fun uniform(location: String, i: Int): GLShader {
        this.checkBeforeUniform(location, i)
        glUniform1iARB(this.getUniformLocation(location), i)

        return this
    }

    fun uniform(location: String, i1: Int, i2: Int): GLShader {
        this.checkBeforeUniform(location, i1, i2)
        glUniform2iARB(this.getUniformLocation(location), i1, i2)

        return this
    }

    fun uniform(location: String, i1: Int, i2: Int, i3: Int): GLShader {
        this.checkBeforeUniform(location, i1, i2, i3)
        glUniform3iARB(this.getUniformLocation(location), i1, i2, i3)

        return this
    }

    fun uniform(location: String, f: Float): GLShader {
        this.checkBeforeUniform(location, f)
        glUniform1fARB(this.getUniformLocation(location), f)

        return this
    }

    fun uniform(location: String, f1: Float, f2: Float): GLShader {
        this.checkBeforeUniform(location, f1, f2)
        glUniform2fARB(this.getUniformLocation(location), f1, f2)

        return this
    }

    fun uniform(location: String, f1: Float, f2: Float, f3: Float): GLShader {
        this.checkBeforeUniform(location, f1, f2, f3)
        glUniform3fARB(this.getUniformLocation(location), f1, f2, f3)
        return this
    }

    private fun checkBeforeUniform(location: String, vararg values: Any) {
        if (!this.canUniform()) {
            throw ShaderUniformException(location, "Shader is not ready to perform a uniform", *values)
        }
    }

    /**
     * @return true if a uniform can be executed
     */
    fun canUniform(): Boolean {
        return this.isBound && !this.isDeleted
    }

    /**
     * Type of a shader
     */
    enum class ShaderType private constructor(val GL_SHADER_TYPE: Int) {
        VERTEX_SHADER(ARBVertexShader.GL_VERTEX_SHADER_ARB),
        FRAGMENT_SHADER(ARBFragmentShader.GL_FRAGMENT_SHADER_ARB)
    }

    private enum class State {
        CONSTRUCTION, LINKED
    }
}