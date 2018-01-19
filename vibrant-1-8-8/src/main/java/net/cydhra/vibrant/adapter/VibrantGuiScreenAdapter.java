package net.cydhra.vibrant.adapter;

import net.cydhra.vibrant.api.client.VibrantMinecraft;
import net.cydhra.vibrant.api.gui.VibrantGuiScreen;
import net.cydhra.vibrant.gui.util.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

/**
 *
 */
public class VibrantGuiScreenAdapter extends GuiScreen {
    private VibrantGuiScreen vScreen;
    
    public VibrantGuiScreenAdapter(VibrantGuiScreen vScreen) {
        this.vScreen = vScreen;
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.INSTANCE.pushState();
        vScreen.drawScreen(mouseX, mouseY, partialTicks);
        GlStateManager.INSTANCE.popState();
    }
    
    public void keyTyped(char typedChar, int keyCode) {
        vScreen.keyTyped(typedChar, keyCode);
    }
    
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        vScreen.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        vScreen.mouseReleased(mouseX, mouseY, mouseButton);
    }
    
    public void mouseClickMove(int mouseX, int mouseY, int mouseButton, long timeSinceLastClick) {
        vScreen.mouseClickMove(mouseX, mouseY, mouseButton, timeSinceLastClick);
    }
    
    public void initGui() {
        vScreen.initGui();
    }
    
    public void updateScreen() {
        vScreen.tickScreen();
    }
    
    public void onGuiClosed() {
        vScreen.onGuiClosed();
    }
    
    public boolean doesGuiPauseGame() {
        return vScreen.doesGuiPauseGame();
    }
    
    public void confirmClicked(boolean result, int id) {
        vScreen.confirmClicked(result, id);
    }
    
    public void onResize(Minecraft mcIn, int screenSizeX, int screenSizeY) {
        vScreen.onResize((VibrantMinecraft) mcIn, screenSizeX, screenSizeY);
    }
}
