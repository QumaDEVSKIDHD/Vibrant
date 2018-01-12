package net.cydhra.vibrant.api.world

import net.cydhra.vibrant.api.entity.VibrantZombie

/**
 *
 */
interface VibrantWorld {

    fun createZombie(): VibrantZombie
}