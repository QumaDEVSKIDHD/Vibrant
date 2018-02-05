package net.cydhra.vibrant.gui.component

/**
 *
 */
interface IComponent {

    var positionX: Double
    var positionY: Double

    var width: Double
    var height: Double

    var text: String

    /**
     * Returns [positionX] as an integer. Cannot be changed, use [positionX] instead.
     */
    val posX: Int
        get() = positionX.toInt()

    /**
     * Returns [positionY] as an integer. Cannot be changed, use [positionY] instead.
     */
    val posY: Int
        get() = positionY.toInt()

    var isMouseOver: Boolean

    /**
     * Get a list of all child components of this component
     */
    fun getChildComponents(): List<IComponent>

    /**
     * Add a child component
     */
    fun addChildComponent(component: IComponent)

    /**
     * Clear child components
     */
    fun clearChildComponents()

    /**
     * Update or determine the hovering state of this component. This method returns true, if this component or any child-component is
     * hovered with the given mouse coordinates and can optionally update the hovering state of all components while doing that.
     *
     * @param mouseX mouse position x relative to component's origin
     * @param mouseY mouse position y relative to component's origin
     * @param shallUpdate whether the hovered state shall actually be updated
     *
     * @return true, if this component or any of its child-components is hovered
     */
    fun updateHovering(mouseX: Int, mouseY: Int, shallUpdate: Boolean): Boolean

    /**
     * Called, iff the mouse clicked this component. The component must check its sub-components whether they were clicked and if not,
     * handle the click itself.
     *
     * @param mouseX mouse position x relative to component's origin
     * @param mouseY mouse position y relative to component's origin
     * @param mouseButton pressed mouse button
     */
    fun onClick(mouseX: Int, mouseY: Int, mouseButton: Int)

    /**
     * Called when the component is dragged
     *
     * @param mouseX mouse position x relative to component's origin
     * @param mouseY mouse position y relative to component's origin
     * @param mouseButton pressed mouse button
     */
    fun onDrag(mouseX: Int, mouseY: Int, mouseButton: Int)

    /**
     * Called when the component is no longer dragged
     *
     * @param mouseX mouse position x relative to component's origin
     * @param mouseY mouse position y relative to component's origin
     * @param mouseButton pressed mouse button
     */
    fun onDragReleased(mouseX: Int, mouseY: Int, mouseButton: Int)

    /**
     * Called when this component is focused and a key on the keyboard is typed
     *
     * @param typedChar the character code for the typed key (if any)
     * @param keyCode the code for the typed key
     */
    fun onKeyTyped(typedChar: Char, keyCode: Int)

    /**
     * @param mouseX mouse position x relative to component's origin
     * @param mouseY mouse position y relative to component's origin
     *
     * @return true, if the mouse is considered inside this component. By default: whether it is inside the rectangle defined by the
     * components position and size, but this can be overridden in order to be changed
     */
    fun isMouseInsideComponent(mouseX: Int, mouseY: Int): Boolean {
        return mouseX > 0 && this.width > mouseX && mouseY > 0 && this.height > mouseY
    }

    /**
     * Draw this and all of its child components
     */
    fun drawComponent()
}