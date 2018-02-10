package net.cydhra.vibrant.util.shader

import java.util.*

internal class ShaderUniformException(location: String, error: String, vararg values: Any) : RuntimeException("Location: " + location + "; Value: " + (if (values.size > 1) Arrays.toString(values) else values[0].toString()) + "; Error: " + error)
