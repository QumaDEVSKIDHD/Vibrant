package net.cydhra.vibrant.interfaces.render;

/**
 *
 */
public aspect ScaledResolutionInterface {
    
    declare parents: (net.minecraft.client.gui.ScaledResolution) implements net.cydhra.vibrant.api.render.VibrantScaledResolution;
}
