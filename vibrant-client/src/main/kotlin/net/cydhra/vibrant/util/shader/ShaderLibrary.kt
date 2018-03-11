package net.cydhra.vibrant.util.shader

import net.cydhra.vibrant.util.shader.impl.OutlineShaderProgram

/**
 *
 */
object ShaderLibrary {

    lateinit var outlineShaderProgramProgram: OutlineShaderProgram


    init {
        loadShaders()
    }

    private fun reloadShaders() {
        outlineShaderProgramProgram.delete()

        loadShaders()
    }

    private fun loadShaders() {
        outlineShaderProgramProgram = OutlineShaderProgram()
    }
}