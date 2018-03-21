package net.cydhra.vibrant.adapter;

import net.cydhra.vibrant.VibrantClient;
import net.cydhra.vibrant.api.client.VibrantMinecraft;
import net.cydhra.vibrant.api.gui.VibrantGuiScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

/**
 *
 */
public class VibrantGuiScreenAdapter extends GuiScreen {
    private final VibrantGuiScreen vScreen;

    public VibrantGuiScreenAdapter(VibrantGuiScreen vScreen) {
        this.vScreen = vScreen;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        VibrantClient.INSTANCE.getGlStateManager().pushAttrib();
        vScreen.drawScreen(mouseX, mouseY, partialTicks);
        VibrantClient.INSTANCE.getGlStateManager().popAttrib();
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
        // set the properties of the VibrantGuiScreen implementation.
        this.vScreen.setWidth(width);
        this.vScreen.setHeight(height);

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
        super.onResize(mcIn, screenSizeX, screenSizeY);
        vScreen.onResize((VibrantMinecraft) mcIn, screenSizeX, screenSizeY);
    }
}
