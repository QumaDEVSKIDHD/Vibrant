package net.cydhra.vibrant.interfaces.item;

import net.cydhra.vibrant.api.item.VibrantArmorMaterial;
import net.cydhra.vibrant.api.item.VibrantArmorType;
import net.minecraft.item.ItemArmor;

public privileged aspect ItemArmorAspect {

    declare parents:ItemArmor implements net.cydhra.vibrant.api.item.VibrantItemArmor;

    public VibrantArmorMaterial ItemArmor.getMaterial() {
        switch (this.material) {
            case LEATHER:
                return VibrantArmorMaterial.LEATHER;
            case CHAIN:
                return VibrantArmorMaterial.CHAIN;
            case IRON:
                return VibrantArmorMaterial.IRON;
            case GOLD:
                return VibrantArmorMaterial.GOLD;
            case DIAMOND:
                return VibrantArmorMaterial.DIAMOND;
            default:
                throw new AssertionError();
        }
    }

    public VibrantArmorType ItemArmor.getType() {
        switch (this.armorType) {
            case 3:
                return VibrantArmorType.BOOTS;
            case 2:
                return VibrantArmorType.LEGS;
            case 1:
                return VibrantArmorType.CHEST;
            case 0:
                return VibrantArmorType.HEAD;
            default:
                throw new AssertionError();
        }
    }
}
