package net.cydhra.vibrant.modules.combat

import net.cydhra.vibrant.api.entity.VibrantEntity
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.organization.GameResourceManager
import net.cydhra.vibrant.organization.locks.ResourceLock
import net.cydhra.vibrant.organization.locks.Side
import net.cydhra.vibrant.organization.priorities.ResourceRequestPriority
import net.cydhra.vibrant.organization.resources.RotationResource
import net.cydhra.vibrant.util.enemy.EnemyTracker
import net.cydhra.vibrant.util.enemy.ITrackedEntity
import net.cydhra.vibrant.util.math.Angle
import org.lwjgl.input.Keyboard

class KillauraModule : Module("Killaura", DefaultCategories.COMBAT, Keyboard.KEY_R) {

    private var lastAngle = Angle(0.0, 0.0)
    private var target: ITrackedEntity? = null

    override fun onEnable() {
        GameResourceManager.lockResource(
                ResourceLock(
                        this,
                        RotationResource,
                        { true },
                        { ResourceRequestPriority.combat },
                        {
                            target = EnemyTracker.getClosestEntity(mc.thePlayer!!, VibrantEntity::class.java)

                            if (target != null
                                    && target!!.entity is VibrantEntity && (target!!.entity as VibrantEntity)
                                            .getDistanceSq(mc.thePlayer!!.posX, mc.thePlayer!!.posY, mc.thePlayer!!.posZ) <= (3.8 * 3.8)) {

                                val angle = Angle(target!!.entity)

                                val yawDiff = angle.yaw - lastAngle.yaw
                                val pitchDiff = angle.pitch - lastAngle.pitch

                                lastAngle = angle

                                return@ResourceLock RotationResource
                                        .RotationState((angle.yaw + yawDiff).toFloat(), (angle.pitch + pitchDiff).toFloat())
                            } else {
                                return@ResourceLock RotationResource.RotationState()
                            }
                        },
                        Side.SERVER
                ).andThen {
                    mc.thePlayer!!.swing()
                    mc.playerController!!.attackEntity(target!!.entity as VibrantEntity)
                }
        )
    }
}