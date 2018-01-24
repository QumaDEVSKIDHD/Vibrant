package net.cydhra.vibrant.gui.client

import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.api.gui.VibrantGuiScreen
import net.cydhra.vibrant.gui.screen.VibrantScreen
import net.cydhra.vibrant.gui.util.GlStateManager
import org.lwjgl.input.Keyboard

/**
 * This class extends [VibrantGuiScreen] to override all the properties that must be implemented but are initialized by the adapter class.
 */
abstract class AbstractVibrantGuiScreen : VibrantGuiScreen {

    protected open val clickGuiScreen = VibrantScreen()

    override var height: Int = -1
    override var width: Int = -1

    private var isDragging = false
    protected var isInitialized = false

    override fun initGui() {
        if(!isInitialized) {
            this.initGuiFirstTime()
            isInitialized = true
        }
    }

    /**
     * Called when the GUI is initialized (on open) for the first time
     */
    open fun initGuiFirstTime() {}

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        clickGuiScreen.updateMousePosition(mouseX, mouseY)

        GlStateManager.disableTexture2D()
        GlStateManager.enableColorBlending()
        clickGuiScreen.draw()
    }

    override fun tickScreen() {
        clickGuiScreen.tick()
    }

    override fun keyTyped(typedChar: Char, keyCode: Int) {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            VibrantClient.minecraft.displayGuiScreen(null)
        } else {
            clickGuiScreen.onKeyTyped(typedChar, keyCode)
        }
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        clickGuiScreen.onClick(mouseX, mouseY, mouseButton)
    }

    override fun mouseClickMove(mouseX: Int, mouseY: Int, clickedMouseButton: Int, timeSinceLastClick: Long) {
        isDragging = true
        clickGuiScreen.onDrag(mouseX, mouseY, clickedMouseButton)
    }

    override fun mouseReleased(mouseX: Int, mouseY: Int, state: Int) {
        if (isDragging) {
            clickGuiScreen.dragReleased(mouseX, mouseY, state)
            isDragging = false
        }
    }
}