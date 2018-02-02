package net.cydhra.vibrant.interfaces.client;

import net.minecraft.util.Timer;

/**
 *
 */
public aspect TimerInterface {
    
    declare parents:(net.minecraft.util.Timer)implements net.cydhra.vibrant.api.client.VibrantTimer;
    
    public float Timer.getRenderPartialTicks() { return this.renderPartialTicks; }
}
