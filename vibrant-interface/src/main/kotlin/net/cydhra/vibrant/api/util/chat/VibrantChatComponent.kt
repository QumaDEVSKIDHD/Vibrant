package net.cydhra.vibrant.api.util.chat

interface VibrantChatComponent {
    fun setStyle(style: VibrantChatStyle): VibrantChatComponent

    fun getChatStyle(): VibrantChatStyle

    /**
     * Appends the given text to the end of this component.
     */
    fun appendText(text: String): VibrantChatComponent

    /**
     * Appends the given component to the end of this one.
     */
    fun appendSiblingComponent(component: VibrantChatComponent): VibrantChatComponent

    /**
     * Gets the text of this component, without any special formatting codes added, for chat.  TODO: why is this two
     * different methods?
     */
    fun getUnformattedTextForChat(): String

    /**
     * Get the text of this component, *and all child components*, with all special formatting codes removed.
     */
    fun getUnformattedText(): String

    /**
     * Gets the text of this component, with formatting codes added for rendering.
     */
    fun getFormattedText(): String

    fun getComponentSiblings(): List<VibrantChatComponent>

    /**
     * Creates a copy of this component.  Almost a deep copy, except the style is shallow-copied.
     */
    fun createCopy(): VibrantChatComponent
}