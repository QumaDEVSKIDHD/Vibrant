package net.cydhra.vibrant.gui.component

/**
 * A list box (a menu that cannot be collapsed and is intended to have a runtime-mutable collection of items
 */
interface IListbox<T> : IMenu<T> {
    override var isExpanded: Boolean
        get() = true
        set(value) = throw UnsupportedOperationException("Cannot collapse a list box")

    val items: MutableCollection<T>

    fun addItem(item: T)

    fun removeItem(item: T)

    fun clear()
}