package net.cydhra.vibrant.interfaces.tileentity;

import net.minecraft.tileentity.TileEntity;

/**
 *
 */
public privileged aspect TileEntityInterface {
    
    declare parents:(TileEntity)implements net.cydhra.vibrant.api.tileentity.VibrantTileEntity;
    
    public double TileEntity.getPosX() {
        return this.pos.getX();
    }
    
    public double TileEntity.getPosY() {
        return this.pos.getY();
    }
    
    public double TileEntity.getPosZ() {
        return this.pos.getZ();
    }
}
