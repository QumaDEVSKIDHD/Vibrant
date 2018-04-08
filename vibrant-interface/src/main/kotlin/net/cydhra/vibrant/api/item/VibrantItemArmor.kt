package net.cydhra.vibrant.api.item

interface VibrantItemArmor : VibrantItem {

    val material: VibrantArmorMaterial
    val type: VibrantArmorType
}

enum class VibrantArmorMaterial(val maxDamageFactor: Int, val damageReductionAmountArray: Array<Int>, val enchantability: Int) {
    LEATHER(5, arrayOf(1, 2, 3, 1), 15),
    CHAIN(15, arrayOf(1, 4, 5, 2), 12),
    IRON(15, arrayOf(2, 5, 6, 2), 9),
    GOLD(7, arrayOf(1, 3, 5, 2), 25),
    DIAMOND(33, arrayOf(3, 6, 8, 3), 10);

    fun getDamageReductionAmount(armorType: Int): Int {
        return this.damageReductionAmountArray[armorType]
    }
}

enum class VibrantArmorType(val slotIndex: Int) {
    HEAD(3),
    CHEST(2),
    LEGS(1),
    BOOTS(0);
}