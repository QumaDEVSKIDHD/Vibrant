package net.cydhra.vibrant.interfaces.entity;

import net.cydhra.vibrant.api.client.VibrantMovementInput;
import net.cydhra.vibrant.api.inventory.VibrantContainer;
import net.cydhra.vibrant.api.util.chat.VibrantChatComponent;
import net.minecraft.client.entity.EntityPlayerSP;

/**
 *
 */
public aspect EntityPlayerSpInterface {
    declare parents: (EntityPlayerSP) implements net.cydhra.vibrant.api.entity.VibrantPlayerSP;
    
    public boolean EntityPlayerSP.isAllowedFlying() {
        return this.capabilities.allowFlying;
    }
    
    public void EntityPlayerSP.setAllowedFlying(boolean allowedFlying) {
        this.capabilities.allowFlying = allowedFlying;
    }

    public void EntityPlayerSP.swing() {
        this.swingItem();
    }

    public boolean EntityPlayerSP.isFlying() {
        return this.capabilities.isFlying;
    }
    
    public void EntityPlayerSP.setFlying(boolean isFlying) {
        this.capabilities.isFlying = isFlying;
    }
    
    public VibrantMovementInput EntityPlayerSP.getMovementInput() { return (VibrantMovementInput) this.movementInput; }

    public VibrantContainer EntityPlayerSP.getOpenContainer() {
        return (VibrantContainer) this.openContainer;
    }

    public void EntityPlayerSP.sendChatMessageToServer(String message) {
        this.sendChatMessage(message);
    }

    public void EntityPlayerSP.displayChatMessageOnClient(VibrantChatComponent message) {
        this.addChatMessage((net.minecraft.util.IChatComponent) message);
    }
}
