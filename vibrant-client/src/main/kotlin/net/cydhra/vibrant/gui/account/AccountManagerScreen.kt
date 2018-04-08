package net.cydhra.vibrant.gui.account

import net.cydhra.nidhogg.YggdrasilClient
import net.cydhra.nidhogg.data.AccountCredentials
import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.gui.client.AbstractVibrantGuiScreen
import net.cydhra.vibrant.gui.component.impl.VibrantButton
import net.cydhra.vibrant.gui.component.impl.VibrantListbox
import net.cydhra.vibrant.gui.component.impl.VibrantPasswordbox
import net.cydhra.vibrant.gui.component.impl.VibrantTextbox

/**
 *
 */
class AccountManagerScreen(private val parent: AbstractVibrantGuiScreen) : AbstractVibrantGuiScreen() {

    private lateinit var accountListbox: VibrantListbox<String>
    private lateinit var usernameTextbox: VibrantTextbox
    private lateinit var passwordTextbox: VibrantTextbox

    companion object {
        val authClient = YggdrasilClient("Vibrant")
    }

    override fun initGui() {
        super.initGui()

        this.clickGuiScreen.clearComponents()

        accountListbox = VibrantListbox(20.0, 20.0, this.width - 40.0, this.height - 100.0, "Accounts")
        usernameTextbox = VibrantTextbox(this.width / 2 - 205.0, this.height - 60.0, 200.0, 20.0, "username")
        passwordTextbox = VibrantPasswordbox(this.width / 2 + 5.0, this.height - 60.0, 200.0, 20.0, "password")

        this.clickGuiScreen.addComponent(accountListbox)
        this.clickGuiScreen.addComponent(usernameTextbox)
        this.clickGuiScreen.addComponent(passwordTextbox)

        this.clickGuiScreen.addComponent(VibrantButton(this.width / 2 - 105.0, this.height - 30.0, 100.0, 20.0, "Cancel").apply {
            registerClickHandler {
                this@AccountManagerScreen.escapeAction()
            }
        })

        this.clickGuiScreen.addComponent(VibrantButton(this.width / 2 + 5.0, this.height - 30.0, 100.0, 20.0, "Direct Login").apply {
            registerClickHandler {
                val session = authClient.login(AccountCredentials(usernameTextbox.text, passwordTextbox.text))
                VibrantClient.minecraft.minecraftSession = session
            }
        })


    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        this.drawDefaultBackground()
        super.drawScreen(mouseX, mouseY, partialTicks)


    }

    override fun escapeAction() {
        mc.displayGuiScreen(parent)
    }
}