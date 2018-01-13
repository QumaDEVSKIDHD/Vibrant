package net.cydhra.vibrant.settings

/**
 *
 */
data class VibrantConfig<T>(val name: String, var value: T, val min: T? = null, val max: T? = null, val step: T? = null)