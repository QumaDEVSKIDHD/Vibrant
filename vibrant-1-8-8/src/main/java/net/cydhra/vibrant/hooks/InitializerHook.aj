package net.cydhra.vibrant.hooks;

import net.cydhra.vibrant.VibrantClient;
import net.minecraft.client.Minecraft;

/**
 * Hooks the initialize method ({@link Minecraft#startGame()}). Calls {@link VibrantClient#init()}
 */
public aspect InitializerHook {
    
    pointcut startGame():
            call(void net.minecraft.client.Minecraft.startGame());
    
    after(): startGame() {
        VibrantClient.INSTANCE.init();
    }
}
