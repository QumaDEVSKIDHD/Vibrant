package net.cydhra.vibrant.interfaces.util;

public aspect DamageSourceInterface {

    declare parents:net.minecraft.util.DamageSource implements net.cydhra.vibrant.api.util.VibrantDamageSource;
}
