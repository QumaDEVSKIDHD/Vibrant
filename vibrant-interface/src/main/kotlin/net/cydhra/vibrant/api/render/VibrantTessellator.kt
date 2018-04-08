package net.cydhra.vibrant.api.render

interface VibrantTessellator {

    val worldRenderer: VibrantVertexBuffer

    fun draw()
}