package net.cydhra.vibrant.interfaces.item;

/**
 *
 */
public aspect ItemInterface {
    
    declare parents: (net.minecraft.item.Item) implements net.cydhra.vibrant.api.item.VibrantItem;
}
