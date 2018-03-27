package net.cydhra.vibrant.interfaces.inventory;

import net.cydhra.vibrant.api.entity.VibrantPlayer;
import net.cydhra.vibrant.api.item.VibrantItemStack;
import net.minecraft.inventory.IInventory;

public aspect InventoryInterface {

    declare parents:net.minecraft.inventory.IInventory extends net.cydhra.vibrant.api.inventory.VibrantInventory;

    public void IInventory.setInventorySlotContent(int index, VibrantItemStack stack) {
        this.setInventorySlotContents(index, (net.minecraft.item.ItemStack) stack);
    }

    public boolean IInventory.canBeUsedByPlayer(VibrantPlayer player) {
        return this.isUseableByPlayer((net.minecraft.entity.player.EntityPlayer) player);
    }

    public void IInventory.openInventoryToPlayer(VibrantPlayer player) {
        this.openInventory((net.minecraft.entity.player.EntityPlayer) player);
    }

    public void IInventory.closeCurrentInventory(VibrantPlayer player) {
        this.closeInventory((net.minecraft.entity.player.EntityPlayer) player);
    }

    public boolean IInventory.isItemValidInSlot(int index, VibrantItemStack stack) {
        return this.isItemValidForSlot(index, (net.minecraft.item.ItemStack) stack);
    }
}
