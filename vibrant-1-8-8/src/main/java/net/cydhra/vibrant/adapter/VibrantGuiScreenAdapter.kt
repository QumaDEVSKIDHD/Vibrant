package net.cydhra.vibrant.adapter

import net.cydhra.vibrant.api.client.VibrantMinecraft
import net.cydhra.vibrant.api.gui.VibrantGuiScreen
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen

/**
 * An adapter implementation for the GuiScreen of Minecraft. It will delegate all important operations on the given [VibrantGuiScreen].
 *
 * @param vScreen [VibrantGuiScreen] implementation that gets the delegated calls
 */
class VibrantGuiScreenAdapter(private val vScreen: VibrantGuiScreen) : GuiScreen() {

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        vScreen.drawScreen(mouseX, mouseY, partialTicks)
    }

    override fun keyTyped(typedChar: Char, keyCode: Int) {
        vScreen.keyTyped(typedChar, keyCode)
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        vScreen.mouseClicked(mouseX, mouseY, mouseButton)
    }

    override fun mouseReleased(mouseX: Int, mouseY: Int, state: Int) {
        vScreen.mouseReleased(mouseX, mouseY, state)
    }

    override fun mouseClickMove(mouseX: Int, mouseY: Int, clickedMouseButton: Int, timeSinceLastClick: Long) {
        vScreen.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick)
    }

    override fun initGui() {
        vScreen.initGui()
    }

    override fun updateScreen() {
        vScreen.tickScreen()
    }

    override fun onGuiClosed() {
        vScreen.onGuiClosed()
    }

    override fun doesGuiPauseGame(): Boolean {
        return vScreen.doesGuiPauseGame()
    }

    override fun confirmClicked(result: Boolean, id: Int) {
        vScreen.confirmClicked(result, id)
    }

    override fun onResize(mcIn: Minecraft?, p_175273_2_: Int, p_175273_3_: Int) {
        vScreen.onResize(mcIn as VibrantMinecraft, p_175273_2_, p_175273_3_)
    }
}