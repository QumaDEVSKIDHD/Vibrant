package net.cydhra.vibrant.hooks;

import net.cydhra.vibrant.VibrantClient;
import net.cydhra.vibrant.api.client.VibrantFactory;
import net.cydhra.vibrant.api.client.VibrantMinecraft;
import net.cydhra.vibrant.aspects.AbstractInitializerHook;
import net.cydhra.vibrant.interfaces.client.VibrantFactoryImpl;
import net.minecraft.client.Minecraft;

/**
 * Hooks the initialize method ({@link Minecraft#startGame()}). Calls {@link VibrantClient#init(VibrantMinecraft, VibrantFactory)}
 */
public aspect InitializerHook extends AbstractInitializerHook {

    @Override
    public pointcut startGame():
            call(void net.minecraft.client.Minecraft.startGame());

    @Override
    public void startGame() {
        VibrantClient.INSTANCE.init((VibrantMinecraft) Minecraft.getMinecraft(), new VibrantFactoryImpl());
    }
}
