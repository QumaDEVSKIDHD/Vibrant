package net.cydhra.vibrant.interfaces.inventory;

public aspect InventoryPlayerInterface {
    declare parents:net.minecraft.entity.player.InventoryPlayer implements net.cydhra.vibrant.api.inventory.VibrantPlayerInventory;
}
