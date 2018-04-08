package net.cydhra.vibrant.api.world

import net.cydhra.vibrant.api.entity.VibrantEntity
import net.cydhra.vibrant.api.tileentity.VibrantTileEntity

/**
 *
 */
interface VibrantWorld {

    fun getEntityList(): List<VibrantEntity>

    fun getTileEntityList(): List<VibrantTileEntity>

    fun getChunkFromBlockCoordinates(pos: VibrantBlockPosition): VibrantChunk
}