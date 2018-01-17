package net.cydhra.vibrant.gui.theme

import java.awt.Color

/**
 *
 */
class DefaultTheme : Theme {
    override val primaryColor: Color = Color(200, 200, 255, 150)
    override val secondaryColor: Color = Color(170, 170, 220)
    override val tertiaryColor: Color = primaryColor.brighter()

    override val primaryTextColor: Color = Color.WHITE
    override val secondaryTextColor: Color = Color(255, 220, 190)

    override fun highlightColor(color: Color): Color {
        return color.brighter()
    }

    override fun disableColor(color: Color): Color {
        return Color(color.red, color.green, color.blue, color.alpha - 80)
    }
}