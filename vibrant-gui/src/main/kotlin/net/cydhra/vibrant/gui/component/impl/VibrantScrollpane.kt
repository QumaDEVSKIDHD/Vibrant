package net.cydhra.vibrant.gui.component.impl

import net.cydhra.vibrant.gui.GuiManager
import net.cydhra.vibrant.gui.component.IScrollpane
import net.cydhra.vibrant.gui.util.RenderUtil
import net.cydhra.vibrant.gui.util.StencilUtil
import org.lwjgl.opengl.GL11
import java.awt.Color

/**
 *
 */
class VibrantScrollpane(positionX: Double,
                        positionY: Double,
                        width: Double,
                        height: Double,
                        text: String) : AbstractVibrantComponent(positionX, positionY, width, height, text), IScrollpane {
    override var value: Double = 0.0

    override val selectionHandlers: MutableCollection<(Double) -> Unit> = mutableListOf()

    override fun drawComponent() {
        GuiManager.getRenderer(this.javaClass)?.renderComponent(this, GuiManager.theme)
                ?: IllegalStateException("No renderer for ${this.javaClass} was set in the GuiManager")

        val fb = GuiManager.framebuffer()
        StencilUtil.setupStencil(fb, fb.width, fb.height)
        RenderUtil.fillRect(this.posX, this.posY, this.width.toInt(), this.height.toInt(), Color.BLACK)
        StencilUtil.enableStencil(StencilUtil.StencilMode.CROP_OUTSIDE)

        GL11.glTranslated(this.positionX, this.positionY, 0.0)
        for (it in this.children) {
            it.drawComponent()
        }
        GL11.glTranslated(-this.positionX, -this.positionY, 0.0)

        StencilUtil.endStencil()
    }

    /**
     * Don't handle clicks on sub-components that are not visible
     */
    override fun onClick(mouseX: Int, mouseY: Int, mouseButton: Int) {
        if (isMouseInsideComponent(mouseX, mouseY)) {
            super.onClick(mouseX, mouseY, mouseButton)
        }
    }

    override fun updateHovering(mouseX: Int, mouseY: Int, shallUpdate: Boolean): Boolean {
        return super.updateHovering(mouseX, mouseY, shallUpdate && isMouseInsideComponent(mouseX, mouseY))
    }
}