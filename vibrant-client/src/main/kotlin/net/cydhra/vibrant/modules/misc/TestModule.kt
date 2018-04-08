package net.cydhra.vibrant.modules.misc

import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.api.entity.VibrantEntity
import net.cydhra.vibrant.events.render.RenderWorldEvent
import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.organization.GameResourceManager
import net.cydhra.vibrant.organization.channel.ResourceChannel
import net.cydhra.vibrant.organization.priorities.ResourceRequestPriority
import net.cydhra.vibrant.organization.resources.RotationResource
import net.cydhra.vibrant.util.enemy.EnemyTracker
import net.cydhra.vibrant.util.enemy.ITrackedEntity
import net.cydhra.vibrant.util.math.Angle
import net.cydhra.vibrant.util.math.AngleSmartMover
import org.lwjgl.input.Keyboard

/**
 *
 */
class TestModule : Module(name = "TestModule", keycode = Keyboard.KEY_GRAVE) {
    var target: ITrackedEntity? = null
    val smartMover = AngleSmartMover(Angle(0.0, 0.0))

    override fun doRequestResources() {
        target = EnemyTracker.getClosestEntity(mc.thePlayer!!, VibrantEntity::class.java)

        if (target != null) {
            GameResourceManager.lockGameResource(
                    RotationResource,
                    {
                        val angle = Angle(target!!.entity)
                        smartMover.update(angle)
                        RotationResource.RotationState(mc.thePlayer!!.rotationYaw, mc.thePlayer!!.rotationPitch)
                    },
                    this,
                    ResourceRequestPriority.combat,
                    ResourceChannel.Side.CLIENT
            )
        } else {
            target = null
            smartMover.update(Angle(mc.thePlayer!!.rotationYaw.toDouble(), mc.thePlayer!!.rotationPitch.toDouble()))
            GameResourceManager.removeLock(this, RotationResource)
        }
    }

    @EventHandler
    fun onRender(event: RenderWorldEvent) {
        if (target != null) {
            val (yaw, pitch) = smartMover.interpolate(event.partialTicks)
            GameResourceManager.outOfSyncUpdate(
                    RotationResource,
                    RotationResource.RotationState(yaw.toFloat(), pitch.toFloat()),
                    this)
        }
    }

}