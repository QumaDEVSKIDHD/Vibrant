package net.cydhra.vibrant.interfaces.item;

/**
 *
 */
public aspect ItemStackInterface {
    
    declare parents: (net.minecraft.item.ItemStack) implements net.cydhra.vibrant.api.item.VibrantItemStack;
}
