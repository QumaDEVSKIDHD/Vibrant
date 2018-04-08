package net.cydhra.vibrant.interfaces.inventory;

import net.cydhra.vibrant.api.item.VibrantItemStack;
import net.minecraft.entity.player.InventoryPlayer;

import java.util.Arrays;

public aspect InventoryPlayerInterface {
    declare parents:net.minecraft.entity.player.InventoryPlayer implements net.cydhra.vibrant.api.inventory.VibrantPlayerInventory;

    public VibrantItemStack[] InventoryPlayer.getArmorInventory() {
        return Arrays.asList(this.armorInventory).toArray(new VibrantItemStack[this.armorInventory.length]);
    }
}
