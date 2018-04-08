package net.cydhra.vibrant.interfaces.world;

import net.cydhra.vibrant.api.entity.VibrantEntity;
import net.cydhra.vibrant.api.tileentity.VibrantTileEntity;
import net.cydhra.vibrant.api.world.VibrantBlockPosition;
import net.cydhra.vibrant.api.world.VibrantChunk;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 */
public aspect WorldInterface {
    
    declare parents:(WorldClient)implements net.cydhra.vibrant.api.world.VibrantWorld;
    
    public List<VibrantEntity> WorldClient.getEntityList() {
        return this.loadedEntityList.stream().map(
                new Function<Entity, VibrantEntity>() {
                    @Override
                    public VibrantEntity apply(final Entity entity) {
                        return (VibrantEntity) entity;
                    }
                }
        ).collect(Collectors.toList());
    }
    
    public List<VibrantTileEntity> WorldClient.getTileEntityList() {
        return this.loadedTileEntityList.stream().map(
                new Function<TileEntity, VibrantTileEntity>() {
                    @Override
                    public VibrantTileEntity apply(final TileEntity entity) {
                        return (VibrantTileEntity) entity;
                    }
                }
        ).collect(Collectors.toList());
    }

    public VibrantChunk WorldClient.getChunkFromBlockCoordinates(VibrantBlockPosition position) {
        return (VibrantChunk) this.getChunkFromBlockCoords((net.minecraft.util.BlockPos) position);
    }
}
