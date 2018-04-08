package net.cydhra.vibrant.api.entity

import net.cydhra.vibrant.api.client.VibrantMovementInput
import net.cydhra.vibrant.api.inventory.VibrantContainer
import net.cydhra.vibrant.api.item.VibrantItemStack
import net.cydhra.vibrant.api.util.chat.VibrantChatComponent

/**
 *
 */
interface VibrantPlayerSP : VibrantPlayer {

    var isAllowedFlying: Boolean

    var isFlying: Boolean

    var isSprinting: Boolean

    val movementInput: VibrantMovementInput

    val openContainer: VibrantContainer

    fun getItemInUseCount(): Int

    fun getHeldItem(): VibrantItemStack?

    fun swing()

    fun sendChatMessageToServer(message: String)

    fun displayChatMessageOnClient(message: VibrantChatComponent)
}