package net.cydhra.vibrant.util.shader

import org.lwjgl.opengl.ARBShaderObjects
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
                { pos, color -> ARBShaderObjects.glUniform2fARB(pos, default.x, default.y) })


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