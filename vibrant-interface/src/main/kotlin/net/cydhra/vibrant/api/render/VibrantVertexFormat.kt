package net.cydhra.vibrant.api.render

interface VibrantVertexFormat {

    val normalOffset: Int
    val colorOffset: Int

    val nextOffset: Int
    val elementCount: Int

    fun clear()

    fun hasNormal(): Boolean

    fun hasColor(): Boolean

    fun hasUvOffset(id: Int): Boolean

    fun getUvOffsetById(id: Int): Int
}