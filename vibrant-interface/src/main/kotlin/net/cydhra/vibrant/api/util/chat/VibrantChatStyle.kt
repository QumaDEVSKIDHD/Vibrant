@file:Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package net.cydhra.vibrant.api.util.chat

interface VibrantChatStyle {
    fun setParentChatStyle(parentStyle: VibrantChatStyle?): VibrantChatStyle

    fun getStyleColor(): EnumChatFormatting
    fun setStyleColor(color: EnumChatFormatting): VibrantChatStyle

    fun getBold(): Boolean
    fun setBold(bold: java.lang.Boolean): VibrantChatStyle

    fun getItalic(): Boolean
    fun setItalic(italic: java.lang.Boolean): VibrantChatStyle

    fun getUnderlined(): Boolean
    fun setUnderlined(underlined: java.lang.Boolean): VibrantChatStyle

    fun getStrikethrough(): Boolean
    fun setStrikethrough(strikethrough: java.lang.Boolean): VibrantChatStyle

    fun getObfuscated(): Boolean
    fun setObfuscated(obfuscated: java.lang.Boolean): VibrantChatStyle
}

enum class EnumChatFormatting(val formatChar: Char, val colorIndex: Int = -1, val fancyStyle: Boolean = false) {
    BLACK(formatChar = '0', colorIndex = 0),
    DARK_BLUE(formatChar = '1', colorIndex = 1),
    DARK_GREEN(formatChar = '2', colorIndex = 2),
    DARK_AQUA(formatChar = '3', colorIndex = 3),
    DARK_RED(formatChar = '4', colorIndex = 4),
    DARK_PURPLE(formatChar = '5', colorIndex = 5),
    GOLD(formatChar = '6', colorIndex = 6),
    GRAY(formatChar = '7', colorIndex = 7),
    DARK_GRAY(formatChar = '8', colorIndex = 8),
    BLUE(formatChar = '9', colorIndex = 9),
    GREEN(formatChar = 'a', colorIndex = 10),
    AQUA(formatChar = 'b', colorIndex = 11),
    RED(formatChar = 'c', colorIndex = 12),
    LIGHT_PURPLE(formatChar = 'd', colorIndex = 13),
    YELLOW(formatChar = 'e', colorIndex = 14),
    WHITE(formatChar = 'f', colorIndex = 15),
    OBFUSCATED(formatChar = 'k', fancyStyle = true),
    BOLD(formatChar = 'l', fancyStyle = true),
    STRIKETHROUGH(formatChar = 'm', fancyStyle = true),
    UNDERLINE(formatChar = 'n', fancyStyle = true),
    ITALIC(formatChar = 'o', fancyStyle = true),
    RESET(formatChar = 'r')
}