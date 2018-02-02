package net.cydhra.vibrant.api.world

import net.cydhra.vibrant.api.entity.VibrantEntity

/**
 *
 */
interface VibrantWorld {

    fun getEntityList(): List<VibrantEntity>
}