package net.cydhra.vibrant.interfaces.util;

/**
 *
 */
public aspect AxisAlignedBBInterface {
    
    declare parents:(net.minecraft.util.AxisAlignedBB)implements net.cydhra.vibrant.api.util.VibrantBoundingBox;
}
