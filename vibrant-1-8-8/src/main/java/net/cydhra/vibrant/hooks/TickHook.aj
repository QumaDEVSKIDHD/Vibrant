package net.cydhra.vibrant.hooks;

import net.cydhra.vibrant.VibrantClient;
import net.cydhra.vibrant.events.minecraft.MinecraftTickEvent;
import net.minecraft.client.Minecraft;

/**
 * Hooks the {@link Minecraft#runTick()} method to call a {@link MinecraftTickEvent} after each game tick
 */
public aspect TickHook {
    
    pointcut tick():
            call(void net.minecraft.client.Minecraft.runTick());
    
    after(): tick() {
        VibrantClient.INSTANCE.tick();
    }
}
