package net.cydhra.vibrant.interfaces.gui;

/**
 *
 */
public aspect GuiMainMenuInterface {
    declare parents:(net.minecraft.client.gui.GuiMainMenu)implements net.cydhra.vibrant.api.gui.VibrantGuiMainMenu;
}
