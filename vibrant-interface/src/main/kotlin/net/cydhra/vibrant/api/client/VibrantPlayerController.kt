package net.cydhra.vibrant.api.client

import net.cydhra.vibrant.api.entity.VibrantEntity
import net.cydhra.vibrant.api.network.VibrantNetHandler

interface VibrantPlayerController {

    val netHandler: VibrantNetHandler

    fun attackEntity(entity: VibrantEntity)

}