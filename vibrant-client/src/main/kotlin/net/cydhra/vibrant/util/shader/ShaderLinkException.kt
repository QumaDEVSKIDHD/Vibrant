package net.cydhra.vibrant.util.shader

/**
 * @author FlaFlo
 * @author Cydhra
 * Called on error while shader linking
 */
class ShaderLinkException internal constructor(error: String) : RuntimeException(error)