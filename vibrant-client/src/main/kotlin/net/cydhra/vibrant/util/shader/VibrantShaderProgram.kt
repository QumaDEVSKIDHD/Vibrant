package net.cydhra.vibrant.util.shader

import net.cydhra.vibrant.VibrantClient
import org.lwjgl.opengl.ARBShaderObjects
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL13

/**
 *
 */
abstract class VibrantShaderProgram {

    private var programId = ARBShaderObjects.glCreateProgramObjectARB()
    private var linked = false

    protected val uniforms = mutableListOf<ShaderUniform<Any>>()
    protected val samplerUniforms = mutableListOf<ShaderSamplerUniform>()

    /**
     * Register a uniform variable as input for the shader pipeline
     */
    internal fun registerUniform(uniform: ShaderUniform<Any>) {
        uniforms.add(uniform)
    }

    internal fun registerSampler(uniform: ShaderSamplerUniform) {
        samplerUniforms.add(uniform)
        this.registerUniform(uniform as ShaderUniform<Any>)
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

        this.samplerUniforms.forEach { sampler ->
            VibrantClient.glStateManager.setActiveTexture(GL13.GL_TEXTURE0 + sampler.texture)
            VibrantClient.glStateManager.bindTexture(0)
        }

        VibrantClient.glStateManager.setActiveTexture(GL13.GL_TEXTURE0)
        VibrantClient.glStateManager.enableTexture2D()
        VibrantClient.glStateManager.bindTexture(0)
    }

    fun delete() {

    }
}