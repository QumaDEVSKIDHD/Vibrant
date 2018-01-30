package net.cydhra.vibrant.api.client

import net.cydhra.vibrant.api.entity.VibrantZombie
import net.cydhra.vibrant.api.render.VibrantDynamicTexture
import net.cydhra.vibrant.api.render.VibrantFrustum
import net.cydhra.vibrant.api.render.VibrantScaledResolution
import net.cydhra.vibrant.api.util.VibrantVec3
import net.cydhra.vibrant.api.world.VibrantWorld
import java.awt.image.BufferedImage

/**
 *
 */
interface VibrantFactory {
    fun newScaledResolution(): VibrantScaledResolution

    fun newFrustum(): VibrantFrustum

    fun newDynamicTexture(bufferedImage: BufferedImage): VibrantDynamicTexture

    fun newDynamicTexture(width: Int, height: Int): VibrantDynamicTexture

    fun newVec3(x: Double, y: Double, z: Double): VibrantVec3

    fun createZombie(world: VibrantWorld): VibrantZombie
}