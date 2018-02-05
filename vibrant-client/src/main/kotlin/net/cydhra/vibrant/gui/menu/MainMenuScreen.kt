package net.cydhra.vibrant.gui.menu

import net.cydhra.vibrant.api.gui.VibrantGuiMainMenu
import net.cydhra.vibrant.gui.client.AbstractVibrantGuiScreen
import net.cydhra.vibrant.gui.component.IComponent
import net.cydhra.vibrant.gui.component.impl.VibrantButton

/**
 *
 */
class MainMenuScreen(realMainMenu: VibrantGuiMainMenu) : AbstractVibrantGuiScreen(realMainMenu) {

    companion object {
        const val ACTION_SELECT_OPTIONS_SCREEN = 0
        const val ACTION_SELECT_WORLD_SCREEN = 1
        const val ACTION_MULTIPLAYER_SCREEN = 2
        const val ACTION_QUIT_GAME = 4
        const val ACTION_LANGUAGE_SCREEN = 5
    }

    override fun initGui() {
        super.initGui()

        this.clickGuiScreen.clearComponents()

        val addButton: (Int, String, (IComponent) -> Unit) -> Unit = { counter, name, clickhandler ->
            this.clickGuiScreen.addComponent(VibrantButton(10.0, this.height - 30.0 * counter, 200.0, 20.0, name).apply {
                registerClickHandler(clickhandler)
            })

        }
        var counter = 0

        addButton(++counter, "Quit") { this@MainMenuScreen.controller.actionPerformed(ACTION_QUIT_GAME) }
        addButton(++counter, "Language") { this@MainMenuScreen.controller.actionPerformed(ACTION_LANGUAGE_SCREEN) }
        addButton(++counter, "Options") { this@MainMenuScreen.controller.actionPerformed(ACTION_SELECT_OPTIONS_SCREEN) }
        addButton(++counter, "Multiplayer") { this@MainMenuScreen.controller.actionPerformed(ACTION_MULTIPLAYER_SCREEN) }
        addButton(++counter, "Singleplayer") { this@MainMenuScreen.controller.actionPerformed(ACTION_SELECT_WORLD_SCREEN) }
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        this.drawDefaultBackground()
        super.drawScreen(mouseX, mouseY, partialTicks)
    }
}