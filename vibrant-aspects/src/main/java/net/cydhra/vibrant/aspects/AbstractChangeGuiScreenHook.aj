package net.cydhra.vibrant.aspects;

import net.cydhra.eventsystem.EventManager;
import net.cydhra.vibrant.api.gui.VibrantGuiController;
import net.cydhra.vibrant.events.minecraft.GuiScreenChangeEvent;

public abstract aspect AbstractChangeGuiScreenHook {
    public abstract pointcut dispayGuiScreen(VibrantGuiController guiScreen);

    void around(VibrantGuiController guiScreen): dispayGuiScreen(guiScreen) {
        GuiScreenChangeEvent event = new GuiScreenChangeEvent(guiScreen);
        EventManager.callEvent(event);

        if (!event.isCancelled())
            proceed(event.getScreen());
    }
}
