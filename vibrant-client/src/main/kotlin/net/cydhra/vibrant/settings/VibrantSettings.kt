package net.cydhra.vibrant.settings

import com.google.gson.GsonBuilder
import java.io.File

/**
 *
 */
object VibrantSettings {

    private const val filename = "settings.json"

    private val gson = GsonBuilder()
            .setPrettyPrinting()
            .create()

    private var registry: MutableMap<String, Any?> = mutableMapOf()

    /**
     * Load the config
     */
    fun load() {
        this.registry = File(filename).takeIf { !it.createNewFile() }
                ?.readText()
                ?.let { gson.fromJson(it, mutableMapOf<String, Any?>()::class.java) }
                ?: mutableMapOf()
    }

    fun save() {
        File(filename).apply { createNewFile() }.writeText(gson.toJson(this.registry))
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> registerConfig(config: VibrantConfig<T>): VibrantConfig<T> {
        val currentValue = registry.putIfAbsent(config.name, config.value)
        if (currentValue != null) {
            return config.apply { this.value = currentValue as T }
        }

        return config
    }
}