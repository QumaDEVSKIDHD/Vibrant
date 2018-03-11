package net.cydhra.vibrant.util.shader


/**
 * @author Flaflo
 * @author Cydhra
 *
 * Thrown when a shader compiler process fails
 */
class ShaderCompileException(type: VibrantShader.ShaderType, error: String) : RuntimeException("ShaderType: $type; Error: $error")