package net.cydhra.vibrant.api.client

import net.cydhra.vibrant.api.entity.VibrantPlayerSP
import net.cydhra.vibrant.api.render.VibrantFramebuffer
import net.cydhra.vibrant.api.render.VibrantFrustum
import net.cydhra.vibrant.api.render.VibrantScaledResolution

/**
 *
 */
interface VibrantMinecraft {

    val thePlayer: VibrantPlayerSP?

    val framebuffer: VibrantFramebuffer?

    val displayWidth: Int
    val displayHeight: Int

    fun newScaledResolution(): VibrantScaledResolution

    fun newFrustum(): VibrantFrustum


}