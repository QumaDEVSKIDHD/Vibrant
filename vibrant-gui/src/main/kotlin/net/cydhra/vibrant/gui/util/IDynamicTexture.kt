package net.cydhra.vibrant.gui.util

interface IDynamicTexture {
    fun getGlTextureId(): Int

    fun deleteGlTexture()

    fun updateDynamicTexture()
}