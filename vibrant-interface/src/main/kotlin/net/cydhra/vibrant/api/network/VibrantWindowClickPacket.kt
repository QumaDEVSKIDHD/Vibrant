package net.cydhra.vibrant.api.network

import net.cydhra.vibrant.api.item.VibrantItemStack

interface VibrantWindowClickPacket : VibrantPacket {
    var windowId: Int
    var slotId: Int
    var mouseButton: UsedMouseButton
    var actionNumber: Short
    var clickedItem: VibrantItemStack?
    var clickType: ClickType
}

enum class ClickType {
    PICKUP,
    QUICK_MOVE,
    SWAP,
    CLONE,
    THROW,
    QUICK_CRAFT,
    PICKUP_ALL
}

enum class UsedMouseButton {
    LEFT,
    RIGHT,
    MIDDLE
}