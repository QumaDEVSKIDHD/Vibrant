package net.cydhra.vibrant.interfaces.gui;

/**
 *
 */
public aspect GuiScreenInterface {
    declare parents:(net.minecraft.client.gui.GuiScreen)implements net.cydhra.vibrant.api.gui.VibrantGuiController;
}
