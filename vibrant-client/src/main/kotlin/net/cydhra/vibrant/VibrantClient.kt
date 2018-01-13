package net.cydhra.vibrant

import net.cydhra.vibrant.api.client.VibrantMinecraft
import net.cydhra.vibrant.modulesystem.ModuleManager
import net.cydhra.vibrant.settings.VibrantSettings
import org.slf4j.LoggerFactory

/**
 *
 */
object VibrantClient {

    val logger = LoggerFactory.getLogger("Vibrant")!!

    lateinit var minecraft: VibrantMinecraft
        private set

    fun init(minecraft: VibrantMinecraft) {
        logger.info("Vibrant Client successfully hooked.")
        this.minecraft = minecraft

        VibrantSettings.load()

        logger.info("loading modules")
        ModuleManager.init()

        VibrantSettings.save()
    }
}