package net.cydhra.vibrant.api.world

interface VibrantChunk {

    fun isAtLocation(x: Int, z: Int): Boolean

    fun getHeightValue(x: Int, z: Int): Int

    fun getBlock(x: Int, y: Int, z: Int): VibrantBlock
}