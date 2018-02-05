package net.cydhra.vibrant.gui.client

import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.api.client.VibrantMinecraft
import net.cydhra.vibrant.api.gui.VibrantGuiController
import net.cydhra.vibrant.api.gui.VibrantGuiScreen
import net.cydhra.vibrant.gui.screen.VibrantScreen
import net.cydhra.vibrant.gui.util.GlStateManager
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.GL11

/**
 * This class extends [VibrantGuiScreen] to override all the properties that must be implemented but are initialized by the adapter class.
 */
abstract class AbstractVibrantGuiScreen(private val controller: VibrantGuiController) : VibrantGuiScreen {

    protected open val clickGuiScreen = VibrantScreen()

    override var height: Int = -1
    override var width: Int = -1

    private var isDragging = false
    protected var isInitialized = false

    protected val mc: VibrantMinecraft
        get() = VibrantClient.minecraft

    private var wallpapers: Array<String> = arrayOf(
            "textures/wp1.jpg",
            "textures/wp2.jpg",
            "textures/wp3.png",
            "textures/wp4.jpg")

    constructor() : this(VibrantClient.factory.newGuiController())


    override fun initGui() {
        if (!isInitialized) {
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

    fun drawDefaultBackground() {
        if (mc.theWorld != null) {

        } else {
            // render a wallpaper of the array and switch it every minute (60000 ms)
            mc.getTextureManager().bindTexture(wallpapers[(System.currentTimeMillis() / 60000 % wallpapers.size).toInt()])
            controller.drawRectWithCustomSizedTexture(0, 0, 0f, 0f, this.width, this.height, this.width.toFloat(), this.height.toFloat())

            // if 55 seconds of the minute are over, blend the next image smoothly above the current
            if (System.currentTimeMillis() / 1000 % 60 > 54) {
                mc.getTextureManager().bindTexture(wallpapers[((System.currentTimeMillis() / 60000 + 1) % wallpapers.size).toInt()])
                GL11.glEnable(GL11.GL_BLEND)
//                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)

                // alpha value depends on current millisecond
                GL11.glColor4f(1f, 1f, 1f, 1 - (60000 - System.currentTimeMillis() % 60000) / 5000f)
                controller.drawRectWithCustomSizedTexture(0, 0, 0f, 0f, this.width, this.height, this.width.toFloat(), this.height.toFloat())
                GL11.glDisable(GL11.GL_BLEND)
            }
        }
    }
}