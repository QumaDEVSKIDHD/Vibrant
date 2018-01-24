package net.cydhra.vibrant.gui.client

import net.cydhra.vibrant.gui.component.impl.*
import net.cydhra.vibrant.modulesystem.Module

/**
 *
 */
class VibrantClickGui(private val module: Module) : AbstractVibrantGuiScreen() {

    override fun initGuiFirstTime() {
        clickGuiScreen.addComponent(VibrantButton(100.0, 100.0, 200.0, 20.0, "test").apply {
            this.clickHandlers.add { println("!") }
        })
        clickGuiScreen.addComponent(VibrantButton(150.0, 110.0, 200.0, 20.0, "test2").apply {
            this.clickHandlers.add { println("?") }
        })
        clickGuiScreen.addComponent(VibrantCheckbox(30.0, 30.0, 80.0, 20.0, "check this"))

        clickGuiScreen.addComponent(VibrantCombobox(50.0, 60.0, 80.0, 20.0, "select this", "test1",
                mutableListOf("test1", "test2", "test3")).apply {
            this.selectionHandlers.add { println(it) }
        })

        clickGuiScreen.addComponent(VibrantDraggablePanel(0.0, 0.0, 50.0, 50.0, ""))

        clickGuiScreen.addComponent(VibrantSlider(0.2, 50.0, 100.0, 50.0, 10.0, "Slippery"))

        clickGuiScreen.addComponent(VibrantTextbox(200.0, 200.0, 100.0, 20.0, "Hallo Welt"))
    }

    override fun onGuiClosed() {
        module.isEnabled = false
    }
}