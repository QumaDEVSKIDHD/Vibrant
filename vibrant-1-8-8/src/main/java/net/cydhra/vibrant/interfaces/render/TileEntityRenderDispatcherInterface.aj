package net.cydhra.vibrant.interfaces.render;

import net.cydhra.vibrant.api.tileentity.VibrantTileEntity;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;

/**
 *
 */
public aspect TileEntityRenderDispatcherInterface {
    
    declare parents:(TileEntityRendererDispatcher)implements net.cydhra.vibrant.api.render.VibrantTileEntityRendererDispatcher;
    
    public void TileEntityRendererDispatcher.doRenderTileEntity(VibrantTileEntity tileEntity, float partialTicks, int destroyStage) {
        this.renderTileEntity((TileEntity) tileEntity, partialTicks, destroyStage);
    }
}
