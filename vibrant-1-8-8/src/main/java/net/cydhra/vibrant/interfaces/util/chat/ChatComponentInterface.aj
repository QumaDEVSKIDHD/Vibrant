package net.cydhra.vibrant.interfaces.util.chat;

import net.cydhra.vibrant.api.util.chat.VibrantChatComponent;
import net.cydhra.vibrant.api.util.chat.VibrantChatStyle;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;

import java.util.Arrays;
import java.util.List;

public aspect ChatComponentInterface {

    declare parents:IChatComponent extends VibrantChatComponent;

    public VibrantChatComponent IChatComponent.setStyle(VibrantChatStyle style) {
        return (VibrantChatComponent) this.setChatStyle((ChatStyle) style);
    }

    public VibrantChatComponent IChatComponent.appendSiblingComponent(VibrantChatComponent component) {
        return (VibrantChatComponent) this.appendSibling((IChatComponent) component);
    }

    public List<VibrantChatComponent> IChatComponent.getComponentSiblings() {
        //noinspection SuspiciousToArrayCall
        return Arrays.asList(this.getSiblings().toArray(new VibrantChatComponent[0]));
    }
}
