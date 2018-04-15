package net.cydhra.vibrant.modules.combat

import net.cydhra.vibrant.api.entity.VibrantPlayer
import net.cydhra.vibrant.api.util.VibrantVec3
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import org.lwjgl.input.Keyboard

class BowAimbotModule : Module("BowAimbot", DefaultCategories.COMBAT, Keyboard.KEY_U) {

    private var entity: VibrantPlayer? = null

    fun doRequestResources() {
//        if (mc.thePlayer == null)
//            return
//
//        if (mc.thePlayer!!.getItemInUseCount() == 0) {
//            entity = null
//            return
//        }
//
//        if (mc.thePlayer!!.getHeldItem() != null && mc.thePlayer!!.getHeldItem()!!.getItem() is VibrantItemBow) {
//            entity = EnemyTracker.getClosestEntity(mc.thePlayer!!)?.entity as VibrantPlayer?
//
//            if (entity != null) {
//                var arrowVelocity = (72000 - mc.thePlayer!!.getItemInUseCount()) / 20.0
//                arrowVelocity = (arrowVelocity * arrowVelocity + arrowVelocity * 2.0f)
//
//                if (arrowVelocity < 0.3) {
//                    return
//                }
//
//                if (arrowVelocity > 3) {
//                    arrowVelocity = 3.0
//                }
//
//                val yaw = mc.thePlayer!!.rotationYaw
//
//                val enemyPosition = entity!!.getPositionVector().addVector(0.0, entity!!.getEyeHeight().toDouble(), 0.0)
//                val playerHeadPosition = factory.newVec3(mc.thePlayer!!.posX - Math.cos(Math.toRadians(yaw.toDouble())) * 0.16f,
//                        mc.thePlayer!!.posY + mc.thePlayer!!.getEyeHeight() - 0.1,
//                        mc.thePlayer!!.posZ - Math.sin(Math.toRadians(yaw.toDouble())) * 0.16f)
//
//
//                println(mc.timer.renderPartialTicks)
//                val motionX = entity!!.posX - (entity!!.chasingPosX + (entity!!.posX - entity!!.chasingPosX) * mc.timer.renderPartialTicks)
//                val motionZ = entity!!.posZ - (entity!!.chasingPosZ + (entity!!.posZ - entity!!.chasingPosZ) * mc.timer.renderPartialTicks)
//                val enemyVelocity = factory.newVec3(motionX, 0.0, motionZ)
//
//                val prediction = predictArrowDirection(enemyPosition, playerHeadPosition, enemyVelocity, arrowVelocity)
//
//                if (prediction != null) {
//                    val hypotenuse = Math.hypot(prediction.xCoord, prediction.zCoord)
//
//                    val yawAtan = Math.atan2(prediction.zCoord, prediction.xCoord).toFloat()
//                    val pitchAtan = Math.atan2(prediction.yCoord, hypotenuse).toFloat()
//                    val deg = (180 / Math.PI).toFloat()
//
//                    val predictedYaw = yawAtan * deg - 90f
//                    val predictedPitch = -(pitchAtan * deg)
//
//                    GameResourceManager.requestGameResource(
//                            RotationResource,
//                            RotationResource.RotationState(predictedYaw, predictedPitch),
//                            ResourceRequestPriority.combat,
//                            ResourceChannel.Side.SERVER)
//                }
//            }
//        } else {
//            entity = null
//        }
    }

    private fun getDirectionByTime(
            enemyPosition: VibrantVec3,
            playerHeadPosition: VibrantVec3,
            enemyVelocity: VibrantVec3,
            arrowVelocity: Double,
            time: Double): VibrantVec3 {
        return factory.newVec3(
                (enemyPosition.xCoord + enemyVelocity.xCoord * time - playerHeadPosition.xCoord) * (AIR_RESISTANCE_FACTOR - 1)
                        / (arrowVelocity * (Math.pow(AIR_RESISTANCE_FACTOR, time) - 1)),

                (enemyPosition.yCoord + enemyVelocity.yCoord * time - playerHeadPosition.yCoord) * (AIR_RESISTANCE_FACTOR - 1)
                        / (arrowVelocity * (Math.pow(AIR_RESISTANCE_FACTOR, time) - 1)) + GRAVITY * (Math.pow(AIR_RESISTANCE_FACTOR, time)
                        - AIR_RESISTANCE_FACTOR * time + time - 1)
                        / (arrowVelocity * (AIR_RESISTANCE_FACTOR - 1) * (Math.pow(AIR_RESISTANCE_FACTOR, time) - 1)),

                (enemyPosition.zCoord + enemyVelocity.zCoord * time - playerHeadPosition.zCoord) * (AIR_RESISTANCE_FACTOR - 1)
                        / (arrowVelocity * (Math.pow(AIR_RESISTANCE_FACTOR, time) - 1))
        )
    }

    // fixme Newton and/or bisect
    private fun predictArrowDirection(enemyPosition: VibrantVec3, playerHeadPosition: VibrantVec3, enemyVelocity: VibrantVec3, arrowVelocity: Double): VibrantVec3? {

        for (i in 1 until 180) {
            val newLimit = getDirectionByTime(enemyPosition, playerHeadPosition, enemyVelocity, arrowVelocity, i.toDouble())
            val newLimitLength = newLimit.lengthVector()

            if (Math.abs(newLimitLength - 1) < 1E-1) {
                return newLimit
            }

            // early escape if the length is already out of scope
            if (newLimitLength > 20 && i > 20) {
                break
            }
        }

        return null
    }

    companion object {
        private val GRAVITY = 0.05
        private val AIR_RESISTANCE_FACTOR = 0.99
    }
}
