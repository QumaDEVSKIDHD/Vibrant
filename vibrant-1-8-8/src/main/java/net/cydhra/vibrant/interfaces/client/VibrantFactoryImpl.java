package net.cydhra.vibrant.interfaces.client;

import net.cydhra.vibrant.api.client.VibrantFactory;
import net.cydhra.vibrant.api.entity.VibrantZombie;
import net.cydhra.vibrant.api.render.VibrantDynamicTexture;
import net.cydhra.vibrant.api.render.VibrantFrustum;
import net.cydhra.vibrant.api.render.VibrantScaledResolution;
import net.cydhra.vibrant.api.util.VibrantVec3;
import net.cydhra.vibrant.api.world.VibrantWorld;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;

/**
 *
 */
public class VibrantFactoryImpl implements VibrantFactory {
    
    @NotNull
    @Override
    public VibrantDynamicTexture newDynamicTexture(@NotNull final BufferedImage bufferedImage) {
        return (VibrantDynamicTexture) new DynamicTexture(bufferedImage);
    }
    
    @NotNull
    @Override
    public VibrantDynamicTexture newDynamicTexture(final int width, final int height) {
        return (VibrantDynamicTexture) new DynamicTexture(width, height);
    }
    
    @NotNull
    @Override
    public VibrantScaledResolution newScaledResolution() {
        return (VibrantScaledResolution) new ScaledResolution(Minecraft.getMinecraft());
    }
    
    @NotNull
    @Override
    public VibrantFrustum newFrustum() {
        return (VibrantFrustum) new Frustum();
    }
    
    @NotNull
    @Override
    public VibrantVec3 newVec3(final double x, final double y, final double z) {
        return (VibrantVec3) new Vec3(x, y, z);
    }
    
    @NotNull
    @Override
    public VibrantZombie createZombie(VibrantWorld world) { return (VibrantZombie) new EntityZombie((World) world); }
}
