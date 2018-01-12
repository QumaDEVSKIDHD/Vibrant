package net.cydhra.vibrant.api.client

import net.cydhra.vibrant.api.entity.VibrantPlayerSP
import net.cydhra.vibrant.api.render.VibrantScaledResolution

/**
 *
 */
interface VibrantMinecraft {

    val thePlayer: VibrantPlayerSP?

    fun newScaledResolution(): VibrantScaledResolution
}