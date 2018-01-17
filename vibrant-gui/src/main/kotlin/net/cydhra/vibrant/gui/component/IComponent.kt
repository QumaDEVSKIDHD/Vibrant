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
     * Called, iff the mouse clicked this component. The component must check its sub-components whether they were clicked and if not,
     * handle the click itself.
     *
     * @param mouseX mouse position x relative to component's origin
     * @param mouseY mouse position y relative to component's origin
     */
//    fun onClick(mouseX: Int, mouseY: Int)

    /**
     * Get a list of all child components of this component
     */
    fun getChildComponents(): List<IComponent>

    /**
     * Add a child component
     */
    fun addChildComponent(component: IComponent)

    /**
     * @param mouseX mouse position x relative to component's origin
     * @param mouseY mouse position y relative to component's origin
     *
     * @return
     */
    fun updateHovering(mouseX: Int, mouseY: Int, shallHighlight: Boolean): Boolean

    /**
     * Draw this and all of its child components
     */
    fun drawComponent()
}