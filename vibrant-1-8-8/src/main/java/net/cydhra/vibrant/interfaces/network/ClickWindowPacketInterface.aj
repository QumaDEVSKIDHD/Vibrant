package net.cydhra.vibrant.interfaces.network;

import net.cydhra.vibrant.api.item.VibrantItemStack;
import net.cydhra.vibrant.api.network.ClickType;
import net.cydhra.vibrant.api.network.UsedMouseButton;
import net.minecraft.network.play.client.C0EPacketClickWindow;

public privileged aspect ClickWindowPacketInterface {

    declare parents:C0EPacketClickWindow implements net.cydhra.vibrant.api.network.VibrantWindowClickPacket;

    public void C0EPacketClickWindow.setWindowId(int id) {
        this.windowId = id;
    }

    public void C0EPacketClickWindow.setSlotId(int id) {
        this.slotId = id;
    }

    public UsedMouseButton C0EPacketClickWindow.getMouseButton() {
        switch (this.usedButton) {
            case 0:
                return UsedMouseButton.LEFT;
            case 1:
                return UsedMouseButton.RIGHT;
            case 2:
                return UsedMouseButton.MIDDLE;
            default:
                throw new AssertionError();
        }
    }

    public void C0EPacketClickWindow.setMouseButton(UsedMouseButton mouseButton) {
        this.usedButton = mouseButton.ordinal();
    }

    public void C0EPacketClickWindow.setActionNumber(short number) {
        this.actionNumber = number;
    }

    public void C0EPacketClickWindow.setClickedItem(VibrantItemStack stack) {
        this.clickedItem = (net.minecraft.item.ItemStack) stack;
    }

    public ClickType C0EPacketClickWindow.getClickType() {
        return ClickType.values()[this.mode];
    }

    public void C0EPacketClickWindow.setClickType(ClickType type) {
        this.mode = type.ordinal();

    }
}
