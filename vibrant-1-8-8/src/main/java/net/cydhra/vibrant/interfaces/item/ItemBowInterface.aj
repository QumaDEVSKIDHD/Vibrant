package net.cydhra.vibrant.interfaces.item;

/**
 *
 */
public aspect ItemBowInterface {
    
    declare parents: (net.minecraft.item.ItemBow) implements net.cydhra.vibrant.api.item.VibrantItemBow;
}
