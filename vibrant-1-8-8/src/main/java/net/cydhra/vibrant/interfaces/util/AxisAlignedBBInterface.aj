package net.cydhra.vibrant.interfaces.util;

import net.minecraft.util.AxisAlignedBB;

/**
 *
 */
public aspect AxisAlignedBBInterface {
    
    declare parents:(net.minecraft.util.AxisAlignedBB)implements net.cydhra.vibrant.api.util.VibrantBoundingBox;

    public double AxisAlignedBB.getMaxX() {
        return this.maxX;
    }

    public double AxisAlignedBB.getMaxY() {
        return this.maxY;
    }

    public double AxisAlignedBB.getMaxZ() {
        return this.maxZ;
    }

    public double AxisAlignedBB.getMinX() {
        return this.minX;
    }

    public double AxisAlignedBB.getMinY() {
        return this.minY;
    }

    public double AxisAlignedBB.getMinZ() {
        return this.minZ;
    }
}
