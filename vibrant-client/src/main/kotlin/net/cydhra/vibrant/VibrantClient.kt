package net.cydhra.vibrant

import net.cydhra.vibrant.api.client.VibrantMinecraft
import net.cydhra.vibrant.modulesystem.ModuleManager
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

        logger.info("loading modules")
        ModuleManager.init()
    }
}