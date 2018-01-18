package net.cydhra.vibrant.gui.component

/**
 * @param T menu item content type
 */
interface IMenuItem<T> : IButton {
    val parent: IMenu<T>
    val content: T
}