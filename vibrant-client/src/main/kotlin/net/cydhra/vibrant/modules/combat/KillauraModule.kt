package net.cydhra.vibrant.modules.combat

import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.util.math.Angle
import org.lwjgl.input.Keyboard

class KillauraModule : Module("Killaura", DefaultCategories.COMBAT, Keyboard.KEY_R) {

    private var lastAngle = Angle(0.0, 0.0)

    fun doRequestResources() {
//        val target = EnemyTracker.getClosestEntity(mc.thePlayer!!, VibrantEntity::class.java)
//
//        if (target != null
//                && target.entity is VibrantEntity
//                && (target.entity as VibrantEntity).getDistanceSq(mc.thePlayer!!.posX, mc.thePlayer!!.posY, mc.thePlayer!!.posZ) <= (3.8 * 3.8)) {
//
//            val angle = Angle(target.entity)
//
//            val yawDiff = angle.yaw - lastAngle.yaw
//            val pitchDiff = angle.pitch - lastAngle.pitch
//
//            GameResourceManager.requestGameResource(
//                    RotationResource,
//                    RotationResource.RotationState((angle.yaw + yawDiff).toFloat(), (angle.pitch + pitchDiff).toFloat()),
//                    ResourceRequestPriority.combat,
//                    ResourceChannel.Side.SERVER
//            )
//
//            lastAngle = angle
//
//            mc.thePlayer!!.swing()
//            mc.playerController!!.attackEntity(target.entity as VibrantEntity)
//        }
    }
}