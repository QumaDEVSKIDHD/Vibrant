package net.cydhra.vibrant.interfaces;

import net.minecraft.util.Vec3;

/**
 *
 */
public aspect Vec3Interface {
    
    declare parents: (Vec3) implements net.cydhra.vibrant.api.util.VibrantVec3;
    
    public double Vec3.getXCoord() { return this.xCoord; }
    
    public double Vec3.getYCoord() { return this.yCoord; }
    
    public double Vec3.getZCoord() { return this.zCoord; }
}
