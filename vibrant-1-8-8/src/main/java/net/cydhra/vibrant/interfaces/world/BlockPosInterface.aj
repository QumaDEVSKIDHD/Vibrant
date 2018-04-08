package net.cydhra.vibrant.interfaces.world;

import net.cydhra.vibrant.api.world.VibrantBlockFacing;
import net.cydhra.vibrant.api.world.VibrantBlockPosition;
import net.minecraft.util.BlockPos;

public aspect BlockPosInterface {

    declare parents: BlockPos implements net.cydhra.vibrant.api.world.VibrantBlockPosition;

    public int BlockPos.getPosX() {
        return this.getX();
    }

    public int BlockPos.getPosY() {
        return this.getY();
    }

    public int BlockPos.getPosZ() {
        return this.getZ();
    }

    public VibrantBlockPosition BlockPos.offsetSide(VibrantBlockFacing facing) {
        return (VibrantBlockPosition) this.offset(net.minecraft.util.EnumFacing.values()[facing.ordinal()]);
    }

    public VibrantBlockPosition BlockPos.offsetSide(VibrantBlockFacing facing, int count) {
        return (VibrantBlockPosition) this.offset(net.minecraft.util.EnumFacing.values()[facing.ordinal()], count);
    }
}
