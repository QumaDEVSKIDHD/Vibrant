package net.cydhra.vibrant.api.client

import net.cydhra.vibrant.api.entity.VibrantZombie
import net.cydhra.vibrant.api.gui.VibrantGuiController
import net.cydhra.vibrant.api.gui.VibrantGuiMainMenu
import net.cydhra.vibrant.api.item.VibrantItemStack
import net.cydhra.vibrant.api.network.*
import net.cydhra.vibrant.api.render.VibrantDynamicTexture
import net.cydhra.vibrant.api.render.VibrantFramebuffer
import net.cydhra.vibrant.api.render.VibrantFrustum
import net.cydhra.vibrant.api.render.VibrantScaledResolution
import net.cydhra.vibrant.api.util.VibrantVec3
import net.cydhra.vibrant.api.util.chat.VibrantChatComponent
import net.cydhra.vibrant.api.world.VibrantWorld
import java.awt.image.BufferedImage

/**
 *
 */
interface VibrantFactory {
    fun newScaledResolution(): VibrantScaledResolution

    fun newFrustum(): VibrantFrustum

    fun newDynamicTexture(bufferedImage: BufferedImage): VibrantDynamicTexture

    fun newDynamicTexture(width: Int, height: Int): VibrantDynamicTexture

    fun newVec3(x: Double, y: Double, z: Double): VibrantVec3

    fun createZombie(world: VibrantWorld): VibrantZombie

    fun newPlayerPosLookPacket(posX: Double, posY: Double, posZ: Double, yaw: Float, pitch: Float, onGround: Boolean): VibrantPlayerPosLookPacket

    fun newPlayerPosPacket(posX: Double, posY: Double, posZ: Double, onGround: Boolean): VibrantPlayerPosPacket

    fun newPlayerLookPacket(yaw: Float, pitch: Float, onGround: Boolean): VibrantPlayerLookPacket

    fun newPlayerPacket(onGround: Boolean): VibrantPlayerPacket

    fun newWindowClickPacket(windowId: Int, slotId: Int, mouseButton: Int, actionNumber: Short,
                             clickedItem: VibrantItemStack?, clickType: ClickType): VibrantWindowClickPacket

    fun newGuiController(): VibrantGuiController

    fun newGuiMainMenu(): VibrantGuiMainMenu

    fun newFramebuffer(displayWidth: Int, displayHeight: Int, useDepth: Boolean): VibrantFramebuffer

    fun newChatTextMessage(message: String): VibrantChatComponent
}