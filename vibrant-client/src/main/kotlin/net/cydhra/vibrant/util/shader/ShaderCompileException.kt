package net.cydhra.vibrant.util.shader


/**
 * @author FlaFlo
 * @author Cydhra
 *
 * Thrown when a shader compiler process fails
 */
class ShaderCompileException internal constructor(type: GLShader.ShaderType, error: String) : RuntimeException("ShaderType: $type; Error: $error")