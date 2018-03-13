package net.cydhra.vibrant.interfaces.entity;

import net.cydhra.vibrant.api.util.VibrantBoundingBox;
import net.minecraft.entity.Entity;

/**
 *
 */
privileged public aspect EntityInterface {
    
    declare parents: (Entity) implements net.cydhra.vibrant.api.entity.VibrantEntity;

    public float Entity.getWidth() {
        return this.width;
    }

    public float Entity.getHeight() {
        return this.height;
    }
    
    public double Entity.getPosX() {
        return this.posX;
    }
    
    public double Entity.getPosY() {
        return this.posY;
    }
    
    public double Entity.getPosZ() {
        return this.posZ;
    }
    
    public double Entity.getPrevPosX() {
        return this.prevPosX;
    }
    
    public double Entity.getPrevPosY() {
        return this.prevPosY;
    }
    
    public double Entity.getPrevPosZ() {
        return this.prevPosZ;
    }
    
    public float Entity.getRotationYaw() { return this.rotationYaw; }
    
    public float Entity.getRotationPitch() { return this.rotationPitch; }
    
    public boolean Entity.getOnGround() {
        return onGround;
    }
    
    public void Entity.setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
    
    public double Entity.getMotionX() { return this.motionX; }
    
    public void Entity.setMotionX(double motionX) { this.motionX = motionX; }
    
    public double Entity.getMotionY() { return this.motionY; }
    
    public void Entity.setMotionY(double motionY) { this.motionY = motionY; }
    
    public double Entity.getMotionZ() { return this.motionZ; }
    
    public void Entity.setMotionZ(double motionZ) { this.motionZ = motionZ; }

    public void Entity.setWidth(float width) {
        this.width = width;
    }

    public void Entity.setHeight(float height) {
        this.height = height;
    }
    
    public VibrantBoundingBox Entity.getBoundingBox() { return (VibrantBoundingBox) this.getEntityBoundingBox(); }
    
    public float Entity.getFallDistance() { return this.fallDistance; }
    
    public void Entity.setFallDistance(float distance) { this.fallDistance = distance; }
}
