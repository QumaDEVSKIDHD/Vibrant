package net.cydhra.vibrant.modules.misc

import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.events.render.RenderWorldEvent
import net.cydhra.vibrant.modulesystem.Module
import org.lwjgl.input.Keyboard

/**
 *
 */
class TestModule : Module(name = "TestModule", keycode = Keyboard.KEY_GRAVE) {

    @EventHandler
    fun onRender(event: RenderWorldEvent) {

    }

}