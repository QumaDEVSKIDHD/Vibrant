package net.cydhra.vibrant.util.shader

import net.cydhra.vibrant.VibrantClient
import org.lwjgl.opengl.ARBShaderObjects
import org.lwjgl.opengl.GL13
import org.lwjgl.util.vector.Vector2f
import java.awt.Color
import kotlin.reflect.KProperty

/**
 * Create a [ShaderUniform] delegate that takes one argument of type [T].
 */
fun <T : Any> uniform(program: VibrantShaderProgram, name: String, default: T, uniforming: (Int, T) -> Unit): ShaderUniform<T> {
    return ShaderUniform(name, default, uniforming).apply {
        @Suppress("UNCHECKED_CAST")
        program.registerUniform(this as ShaderUniform<Any>)
    }
}

fun uniform(program: VibrantShaderProgram, name: String, default: Int): ShaderUniform<Int> =
        uniform(program, name, default, ARBShaderObjects::glUniform1iARB)

fun uniform(program: VibrantShaderProgram, name: String, default: Float): ShaderUniform<Float> =
        uniform(program, name, default, ARBShaderObjects::glUniform1fARB)

fun uniform(program: VibrantShaderProgram, name: String, default: Color): ShaderUniform<Color> =
        uniform(program, name, default,
                { pos, color -> ARBShaderObjects.glUniform3fARB(pos, color.red / 255f, color.green / 255f, color.blue / 255f) })

fun uniform(program: VibrantShaderProgram, name: String, default: Vector2f): ShaderUniform<Vector2f> =
        uniform(program, name, default,
                { pos, color -> ARBShaderObjects.glUniform2fARB(pos, color.x, color.y) })

fun uniform(program: VibrantShaderProgram, name: String, default: Boolean): ShaderUniform<Boolean> =
        uniform(program, name, default, { pos, bool -> ARBShaderObjects.glUniform1iARB(pos, if (bool) 1 else 0) })

fun sampler(program: VibrantShaderProgram, name: String, default: Int, texture: Int): ShaderSamplerUniform {
    return ShaderSamplerUniform(name, default, texture).apply {
        program.registerSampler(this)
    }
}

/**
 * A uniform shader pipeline input value of type [T] with only one parameter
 *
 * @param name name of the uniform in the GLSL source code
 * @param default the default value for the uniform
 * @param uniforming the ARB function used for piping the value into the shader program
 * @param T input type
 */
open class ShaderUniform<T>(val name: String, default: T, val uniforming: (Int, T) -> Unit) {
    var value: T = default

    operator fun getValue(thisRef: Any, property: KProperty<*>) = value

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        this.value = value
    }
}

class ShaderSamplerUniform(name: String, default: Int, val texture: Int) : ShaderUniform<Int>(name, default, { pos, value ->
    VibrantClient.minecraft.glStateManager.setActiveTexture(GL13.GL_TEXTURE0 + texture)
    VibrantClient.minecraft.glStateManager.enableTexture2D()
    VibrantClient.minecraft.glStateManager.bindTexture(value)
    ARBShaderObjects.glUniform1iARB(pos, texture)
})