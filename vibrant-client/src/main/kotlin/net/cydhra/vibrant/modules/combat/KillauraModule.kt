package net.cydhra.vibrant.modules.combat

import net.cydhra.vibrant.api.entity.VibrantEntity
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.util.enemy.EnemyTracker
import org.lwjgl.input.Keyboard

class Killaura : Module("Killaura", DefaultCategories.COMBAT, Keyboard.KEY_R) {

    override fun doRequestResources() {
        val target = EnemyTracker.getClosestEntity(mc.thePlayer!!, VibrantEntity::class.java)

        if (target != null && target.entity is VibrantEntity && (target.entity as VibrantEntity).getDistanceSq(mc.thePlayer!!.posX, mc.thePlayer!!.posY, mc.thePlayer!!.posZ) <= (3.8 * 3.8)) {
            mc.thePlayer!!.swing()
            mc.playerController!!.attackEntity(target.entity as VibrantEntity)
        }
    }
}