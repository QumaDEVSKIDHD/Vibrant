package net.cydhra.vibrant.interfaces.inventory;

import net.cydhra.vibrant.api.entity.VibrantPlayer;
import net.cydhra.vibrant.api.inventory.VibrantPlayerInventory;
import net.cydhra.vibrant.api.item.VibrantItemStack;
import net.cydhra.vibrant.api.network.ClickType;
import net.minecraft.inventory.Container;

public aspect ContainerInterface {

    declare parents:Container implements net.cydhra.vibrant.api.inventory.VibrantContainer;

    public int Container.getWindowId() {
        return this.windowId;
    }

    public short Container.nextTransactionID(VibrantPlayerInventory inventory) {
        return this.getNextTransactionID((net.minecraft.entity.player.InventoryPlayer) inventory);
    }

    public VibrantItemStack Container.clickSlot(int slotId, int clickedButton, ClickType mode, VibrantPlayer player) {
        return (VibrantItemStack) this.slotClick(slotId, clickedButton, mode.ordinal(),
                (net.minecraft.entity.player.EntityPlayer) player);
    }
}
