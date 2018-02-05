package net.cydhra.vibrant.interfaces.gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

/**
 *
 */
public aspect GuiScreenInterface {
    declare parents:(net.minecraft.client.gui.GuiScreen)implements net.cydhra.vibrant.api.gui.VibrantGuiController;
    
    public void GuiScreen.drawRectWithCustomSizedTexture(
            int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight) {
        Gui.drawModalRectWithCustomSizedTexture(x, y, u, v, width, height, textureWidth, textureHeight);
    }
}
