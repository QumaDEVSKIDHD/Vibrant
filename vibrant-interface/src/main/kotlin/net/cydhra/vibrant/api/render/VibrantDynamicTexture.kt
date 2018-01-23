package net.cydhra.vibrant.api.render

interface VibrantDynamicTexture {

    fun getGlTextureId(): Int

    fun deleteGlTexture()

    fun updateDynamicTexture()
}
