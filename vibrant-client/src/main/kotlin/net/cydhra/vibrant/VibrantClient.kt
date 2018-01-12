package net.cydhra.vibrant

import org.slf4j.LoggerFactory

/**
 *
 */
object VibrantClient {

    val logger = LoggerFactory.getLogger("Vibrant")!!

    fun init() {
        logger.info("Vibrant Client successfully hooked.")
    }
}