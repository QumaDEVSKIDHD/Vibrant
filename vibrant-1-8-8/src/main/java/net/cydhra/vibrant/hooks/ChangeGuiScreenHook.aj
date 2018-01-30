package net.cydhra.vibrant.hooks;

import net.cydhra.eventsystem.EventManager;
import net.cydhra.vibrant.api.gui.VibrantGuiController;
import net.cydhra.vibrant.events.minecraft.GuiScreenChangeEvent;
import net.minecraft.client.gui.GuiScreen;

/**
 *
 */
public aspect ChangeGuiScreenHook {
    
    pointcut dispayGuiScreen(GuiScreen guiScreen):
            call(void net.minecraft.client.Minecraft.displayGuiScreen(GuiScreen))
                    && args(guiScreen);
    
    void around(GuiScreen guiScreen): dispayGuiScreen(guiScreen) {
        GuiScreenChangeEvent event = new GuiScreenChangeEvent((VibrantGuiController) guiScreen);
        EventManager.callEvent(event);
        
        if (!event.isCancelled())
            proceed((GuiScreen) event.getScreen());
    }
}
