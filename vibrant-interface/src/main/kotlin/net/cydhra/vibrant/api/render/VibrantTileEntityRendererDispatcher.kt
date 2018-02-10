package net.cydhra.vibrant.api.render

import net.cydhra.vibrant.api.tileentity.VibrantTileEntity

/**
 *
 */
interface VibrantTileEntityRendererDispatcher {

    fun doRenderTileEntity(tileEntity: VibrantTileEntity, partialTicks: Float, destroyStage: Int)
}