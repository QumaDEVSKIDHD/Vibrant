package net.cydhra.vibrant

import net.cydhra.vibrant.api.client.VibrantMinecraft
import org.slf4j.LoggerFactory

/**
 *
 */
object VibrantClient {

    val logger = LoggerFactory.getLogger("Vibrant")!!

    lateinit var minecraft: VibrantMinecraft
        private set

    fun init(minecraft: VibrantMinecraft) {
        this.minecraft = minecraft

        logger.info("Vibrant Client successfully hooked.")
    }
}