package net.cydhra.vibrant.gui.component

import net.cydhra.vibrant.gui.component.capabilities.Selectable

/**
 *
 */
interface ISlider : IComponent, Selectable<Double> {
    var value: Double
}