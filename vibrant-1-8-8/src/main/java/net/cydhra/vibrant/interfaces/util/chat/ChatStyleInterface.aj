package net.cydhra.vibrant.interfaces.util.chat;

import net.cydhra.vibrant.api.util.chat.VibrantChatStyle;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public aspect ChatStyleInterface {

    declare parents:ChatStyle extends net.cydhra.vibrant.api.util.chat.VibrantChatStyle;

    public VibrantChatStyle ChatStyle.setStyleColor(net.cydhra.vibrant.api.util.chat.EnumChatFormatting formatting) {
        return (VibrantChatStyle) this.setColor(EnumChatFormatting.values()[formatting.ordinal()]);
    }

    public net.cydhra.vibrant.api.util.chat.EnumChatFormatting ChatStyle.getStyleColor() {
        return net.cydhra.vibrant.api.util.chat.EnumChatFormatting.values()[this.getColor().ordinal()];
    }

    public VibrantChatStyle ChatStyle.setParentChatStyle(VibrantChatStyle style) {
        return (VibrantChatStyle) this.setParentStyle((ChatStyle) style);
    }
}
