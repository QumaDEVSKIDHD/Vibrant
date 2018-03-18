package net.cydhra.vibrant.api.render

import net.cydhra.vibrant.gui.util.IFramebuffer

/**
 *
 */
interface VibrantFramebuffer : IFramebuffer {

    val textureWidth: Int
    val textureHeight: Int

    val textureId: Int

    fun deleteFramebuffer()

    fun unbindFramebuffer()

    fun bindFramebuffer(setViewport: Boolean)

    fun bindFramebufferTexture()

    fun unbindFramebufferTexture()
}