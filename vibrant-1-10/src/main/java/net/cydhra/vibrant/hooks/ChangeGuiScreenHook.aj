package net.cydhra.vibrant.hooks;

import net.cydhra.vibrant.api.gui.VibrantGuiController;
import net.cydhra.vibrant.aspects.AbstractChangeGuiScreenHook;

/**
 *
 */
public aspect ChangeGuiScreenHook extends AbstractChangeGuiScreenHook {

    @Override
    public pointcut dispayGuiScreen(VibrantGuiController guiScreen):
            call(void net.minecraft.client.Minecraft.displayGuiScreen(net.minecraft.client.gui.GuiScreen))
                    && args(guiScreen);
}
