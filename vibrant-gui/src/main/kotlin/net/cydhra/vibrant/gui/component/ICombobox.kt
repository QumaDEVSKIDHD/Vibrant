package net.cydhra.vibrant.gui.component

/**
 * @param combobox item content type
 */
interface ICombobox<T> : IMenu<T> {
    var selectedItem: T
    val items: MutableCollection<T>
}