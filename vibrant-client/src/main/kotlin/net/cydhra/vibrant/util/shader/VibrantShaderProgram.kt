package net.cydhra.vibrant.util.shader

import org.lwjgl.opengl.ARBShaderObjects
import org.lwjgl.opengl.GL11

/**
 *
 */
abstract class VibrantShaderProgram {

    private var programId = ARBShaderObjects.glCreateProgramObjectARB()
    private var linked = false

    protected val uniforms = mutableListOf<ShaderUniform<Any>>()

    /**
     * Register a uniform variable as input for the shader pipeline
     */
    internal fun registerUniform(uniform: ShaderUniform<Any>) {
        uniforms.add(uniform)
    }

    fun appendShader(shader: VibrantShader) {
        ARBShaderObjects.glAttachObjectARB(this.programId, shader.shaderObjectId)
    }

    fun link() {
        if (linked)
            throw IllegalStateException("Already linked")

        // link and check
        ARBShaderObjects.glLinkProgramARB(this.programId)
        if (ARBShaderObjects.glGetObjectParameteriARB(this.programId, ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_FALSE) {
            throw ShaderLinkException(ARBShaderObjects.glGetInfoLogARB(this.programId,
                    ARBShaderObjects.glGetObjectParameteriARB(this.programId, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB)))
        }

        // validate shader
        ARBShaderObjects.glValidateProgramARB(this.programId)
        if (ARBShaderObjects.glGetObjectParameteriARB(this.programId, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_FALSE) {
            throw ShaderLinkException(ARBShaderObjects.glGetInfoLogARB(this.programId,
                    ARBShaderObjects.glGetObjectParameteriARB(this.programId, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB)))
        }

        linked = true
    }

    fun bind() {
        if (!linked)
            throw IllegalStateException("Cannot bind unlinked shader")

        ARBShaderObjects.glUseProgramObjectARB(this.programId)

        this.uniforms.forEach { uni ->
            uni.uniforming(ARBShaderObjects.glGetUniformLocationARB(this.programId, uni.name), uni.value)
        }
    }

    fun unbind() {
        ARBShaderObjects.glUseProgramObjectARB(0)
    }

    fun delete() {

    }
}