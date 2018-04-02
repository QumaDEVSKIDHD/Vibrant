package net.cydhra.vibrant.adapter;

import net.cydhra.vibrant.api.client.VibrantFactory;
import net.cydhra.vibrant.api.entity.VibrantZombie;
import net.cydhra.vibrant.api.gui.VibrantGuiController;
import net.cydhra.vibrant.api.gui.VibrantGuiMainMenu;
import net.cydhra.vibrant.api.item.VibrantItemStack;
import net.cydhra.vibrant.api.network.*;
import net.cydhra.vibrant.api.render.VibrantDynamicTexture;
import net.cydhra.vibrant.api.render.VibrantFramebuffer;
import net.cydhra.vibrant.api.render.VibrantFrustum;
import net.cydhra.vibrant.api.render.VibrantScaledResolution;
import net.cydhra.vibrant.api.util.VibrantVec3;
import net.cydhra.vibrant.api.world.VibrantWorld;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
        return (VibrantVec3) new Vec3d(x, y, z);
    }

    @NotNull
    @Override
    public VibrantZombie createZombie(VibrantWorld world) {
        return (VibrantZombie) new EntityZombie((World) world);
    }

    @NotNull
    @Override
    public VibrantPlayerLookPacket newPlayerLookPacket(final float yaw, final float pitch, final boolean onGround) {
        return (VibrantPlayerLookPacket) new CPacketPlayer.Rotation(yaw, pitch, onGround);
    }

    @NotNull
    @Override
    public VibrantPlayerPacket newPlayerPacket(final boolean onGround) {
        return (VibrantPlayerPacket) new CPacketPlayer(onGround);
    }

    @NotNull
    @Override
    public VibrantPlayerPosLookPacket newPlayerPosLookPacket(final double posX, final double posY, final double posZ, final float yaw,
                                                             final float pitch,
                                                             final boolean onGround) {
        return (VibrantPlayerPosLookPacket) new CPacketPlayer.PositionRotation(posX, posY, posZ, yaw, pitch, onGround);
    }

    @NotNull
    @Override
    public VibrantPlayerPosPacket newPlayerPosPacket(final double posX, final double posY, final double posZ, final boolean onGround) {
        return (VibrantPlayerPosPacket) new CPacketPlayer.Position(posX, posY, posZ, onGround);
    }

    @NotNull
    @Override
    public VibrantGuiController newGuiController() {
        return (VibrantGuiController) new GuiGameOver(new TextComponentString(""));
    }

    @NotNull
    @Override
    public VibrantGuiMainMenu newGuiMainMenu() {
        GuiMainMenu mainMenu = new GuiMainMenu();
        ScaledResolution sc = new ScaledResolution(Minecraft.getMinecraft());
        mainMenu.setWorldAndResolution(Minecraft.getMinecraft(), sc.getScaledWidth(), sc.getScaledHeight());
        return (VibrantGuiMainMenu) mainMenu;
    }

    @NotNull
    @Override
    public VibrantFramebuffer newFramebuffer(int displayWidth, int displayHeight, boolean useDepth) {
        return (VibrantFramebuffer) new Framebuffer(displayWidth, displayHeight, useDepth);
    }

    @NotNull
    @Override
    public VibrantWindowClickPacket newWindowClickPacket(int windowId, int slotId, int mouseButton, short actionNumber, @Nullable VibrantItemStack clickedItem, @NotNull ClickType clickType) {
        return null;
    }
}
