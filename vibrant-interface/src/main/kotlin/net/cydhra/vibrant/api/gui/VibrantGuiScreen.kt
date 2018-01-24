package net.cydhra.vibrant.api.gui

import net.cydhra.vibrant.api.client.VibrantMinecraft

/**
 *
 */
interface VibrantGuiScreen {

    var height: Int

    var width: Int

    fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {}

    fun keyTyped(typedChar: Char, keyCode: Int) {}

    fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {}

    fun mouseReleased(mouseX: Int, mouseY: Int, state: Int) {}

    fun mouseClickMove(mouseX: Int, mouseY: Int, clickedMouseButton: Int, timeSinceLastClick: Long) {}

    fun initGui() {}

    fun tickScreen() {}

    fun onGuiClosed() {}

    fun doesGuiPauseGame(): Boolean { return false }

    fun confirmClicked(result: Boolean, id: Int) {}

    fun onResize(mcIn: VibrantMinecraft, width: Int, height: Int) {}
}