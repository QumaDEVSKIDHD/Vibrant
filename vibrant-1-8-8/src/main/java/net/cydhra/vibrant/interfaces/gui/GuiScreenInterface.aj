package net.cydhra.vibrant.interfaces.gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

/**
 *
 */
public privileged aspect GuiScreenInterface {
    declare parents:(net.minecraft.client.gui.GuiScreen)implements net.cydhra.vibrant.api.gui.VibrantGuiController;
    
    public void GuiScreen.drawRectWithCustomSizedTexture(
            int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight) {
        Gui.drawModalRectWithCustomSizedTexture(x, y, u, v, width, height, textureWidth, textureHeight);
    }
    
    public void GuiScreen.actionPerformed(int id) {
        try {
            this.actionPerformed(new GuiButton(id, 0, 0, ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
