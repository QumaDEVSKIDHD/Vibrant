package net.cydhra.vibrant.interfaces.client;

import net.minecraft.util.MovementInput;

/**
 *
 */
public aspect MovementInputInterface {
    
    declare parents: (MovementInput) implements net.cydhra.vibrant.api.client.VibrantMovementInput;
    
    public float MovementInput.getMoveStrafe() { return this.moveStrafe; }
    
    public void MovementInput.setMoveStrafe(float moveStrafe) { this.moveStrafe = moveStrafe; }
    
    public float MovementInput.getMoveForward() { return this.moveForward; }
    
    public void MovementInput.setMoveForward(float moveForward) { this.moveForward = moveForward; }
    
    public boolean MovementInput.getJump() { return this.jump; }
    
    public void MovementInput.setJump(boolean jump) { this.jump = jump; }
    
    public boolean MovementInput.getSneak() { return this.sneak; }
    
    public void MovementInput.setSneak(boolean sneak) { this.sneak = sneak; }
}
