package net.cydhra.vibrant.api.world

interface VibrantBlock {

    val lightOpacity: Int
    val lightValue: Int
    val useNeighborBrightness: Boolean
    val renderType: Int

    fun isNormalCube(): Boolean

    fun isVisuallyOpaque(): Boolean

    fun isFullCube(): Boolean

    fun isFullBlock(): Boolean

    fun isBlockNormalCube(): Boolean

    fun isTranslucent(): Boolean
}