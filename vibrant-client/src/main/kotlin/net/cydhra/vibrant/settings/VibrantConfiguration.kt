package net.cydhra.vibrant.settings

import com.google.gson.GsonBuilder
import java.io.File

/**
 *
 */
object VibrantConfiguration {

    private val gson = GsonBuilder()
            .setPrettyPrinting()
            .create()
    private val file = File("vibrant/settings.json")

    private val settings = mutableMapOf<String, VibrantSettingDelegate<*>>()
    private lateinit var values: MutableMap<String, Any?>

    internal var scheduledThread: Thread? = null

    @Suppress("UNCHECKED_CAST")
    fun <T> registerSettingDelegate(delegate: VibrantSettingDelegate<T>) {
        if (settings.containsKey(delegate.uniqueId))
            throw IllegalArgumentException("Setting with id ${delegate.uniqueId} is already defined.")

        settings[delegate.uniqueId] = delegate

        if (values.containsKey(delegate.uniqueId)) {
            delegate.value = values[delegate.uniqueId] as T
        } else {
            values[delegate.uniqueId] = delegate.value
        }

        delegate.observer = {
            values[delegate.uniqueId] = it
            scheduleSave()
        }
    }

    fun load() {
        this.values = gson.fromJson(file
                .apply { parentFile.mkdir() }
                .apply {
                    if (createNewFile()) {
                        writeText("{}")
                    }
                }
                .readText(),
                mutableMapOf<String, Any?>()::class.java)
    }

    fun scheduleSave() {
        synchronized(VibrantConfiguration) {
            scheduledThread = SaveScheduler()
            scheduledThread!!.start()
        }
    }

    fun save() {
        file
                .apply { parentFile.mkdir() }
                .apply {
                    if (createNewFile()) {
                        writeText("{}")
                    }
                }
                .writeText(
                        gson.toJson(this.values)
                )
    }

    class SaveScheduler : Thread() {
        override fun run() {
            Thread.sleep(1000L)

            synchronized(VibrantConfiguration) {
                if (VibrantConfiguration.scheduledThread == this) {
                    VibrantConfiguration.scheduledThread = null
                    VibrantConfiguration.save()
                }
            }
        }
    }
}