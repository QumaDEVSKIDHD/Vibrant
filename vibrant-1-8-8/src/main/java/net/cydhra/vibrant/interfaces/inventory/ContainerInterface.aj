package net.cydhra.vibrant.interfaces.inventory;

import net.cydhra.vibrant.api.inventory.VibrantPlayerInventory;
import net.minecraft.inventory.Container;

public aspect ContainerInterface {

    declare parents:Container implements net.cydhra.vibrant.api.inventory.VibrantContainer;

    public int Container.getWindowId() {
        return this.windowId;
    }

    public short Container.nextTransactionID(VibrantPlayerInventory inventory) {
        return this.getNextTransactionID((net.minecraft.entity.player.InventoryPlayer) inventory);
    }
}
