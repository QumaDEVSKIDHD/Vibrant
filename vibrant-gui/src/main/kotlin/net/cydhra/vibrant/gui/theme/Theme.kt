package net.cydhra.vibrant.gui.theme

import java.awt.Color

/**
 *
 */
interface Theme {

    val primaryColor: Color
    val secondaryColor: Color
    val tertiaryColor: Color

    val primaryTextColor: Color
    val secondaryTextColor: Color

    /**
     * Create a highlighted version of the given color
     *
     * @param color any color to highlight (probably but not necessarily of this theme)
     *
     * @return a changed version of this color representing highlighting
     */
    fun highlightColor(color: Color): Color

    /**
     * Create a disabled version of the given color indicating a disabled GUI component
     *
     * @param color any color to highlight (probably but not necessarily of this theme)
     *
     * @return a changed version of this color representing disabling
     */
    fun disableColor(color: Color): Color
}