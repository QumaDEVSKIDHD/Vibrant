package net.cydhra.vibrant.modules.inventory

import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.api.inventory.MAX_HOTBAR_INVENTORY_SLOT
import net.cydhra.vibrant.api.inventory.VibrantPlayerInventory
import net.cydhra.vibrant.api.item.VibrantArmorType
import net.cydhra.vibrant.api.item.VibrantItemArmor
import net.cydhra.vibrant.api.item.VibrantItemStack
import net.cydhra.vibrant.api.network.ClickType
import net.cydhra.vibrant.events.minecraft.MinecraftTickEvent
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import org.lwjgl.input.Keyboard

const val PROTOCOL_MAX_ARMOR_SLOT = 8

class AutoArmorModule : Module("AutoArmor", DefaultCategories.INVENTORY, Keyboard.KEY_P) {

    @EventHandler
    fun onTick(e: MinecraftTickEvent) {
        if (mc.thePlayer == null)
            return

        for (armorType in VibrantArmorType.values()) {
            val currentReductionByType =
                    getItemDamageReductionMultiplier(mc.thePlayer!!.playerInventory.getArmorInventory()[armorType.slotIndex])
            val bestIndexInInventory = getBestArmorForType(armorType, currentReductionByType, mc.thePlayer!!.playerInventory)

            if (bestIndexInInventory < 0) continue

            if (bestIndexInInventory <= MAX_HOTBAR_INVENTORY_SLOT) {
                swapItems(PROTOCOL_MAX_ARMOR_SLOT - armorType.slotIndex, bestIndexInInventory)
            } else {
                swapItems(bestIndexInInventory, 8)
                swapItems(PROTOCOL_MAX_ARMOR_SLOT - armorType.slotIndex, 8)
                swapItems(bestIndexInInventory, 8)
            }

            if (bestIndexInInventory >= 0)
                println("better index for $armorType is $bestIndexInInventory")
        }
    }

    /**
     * Search an inventory for armor that is better than a given threshold.
     *
     * @param type type of armor to search for
     * @param currentReduction damage reduction factor of currently equipped armor
     * @param inventory the inventory that shall be searched
     *
     * @return the index of the best item in given inventory for the given armor type. -1 if no item has a better value than the current equipped one.
     */
    private fun getBestArmorForType(type: VibrantArmorType, currentReduction: Double, inventory: VibrantPlayerInventory): Int {
        var bestDamageMultiplier = currentReduction
        var bestIndex = -1

        for ((index, stack) in inventory) {
            val item = stack?.getItem() as? VibrantItemArmor ?: continue
            if (item.type != type) continue

            val currentDamageMultiplier = getItemDamageReductionMultiplier(stack)

            if (bestDamageMultiplier - currentDamageMultiplier > 1E-8) {
                bestDamageMultiplier = currentDamageMultiplier
                bestIndex = index
            }
        }

        return bestIndex
    }

    /**
     * Calculate the damage reduction of a given item. (If [stack] is null, -1.0 is returned)
     *
     * @param stack the item stack containing the armor item
     *
     * @return the damage reduction multiplier that a piece of armor will apply if equipped. The value is between 0 and
     * 1; when the given stack is null it is 1.
     */
    private fun getItemDamageReductionMultiplier(stack: VibrantItemStack?): Double {
        val item = stack?.getItem() as? VibrantItemArmor ?: return 1.0
        var baseValue = 25.0

        baseValue -= item.material.getDamageReductionAmount(item.type.ordinal).toFloat()
        baseValue -= Math.min(mc.thePlayer!!.playerInventory
                .getEnchantmentModifier(arrayOf(stack), mc.thePlayer!!.getDamageSource()), 20)

        return baseValue / 25.0
    }

    private fun swapItems(slotId: Int, hotbarId: Int) {
        mc.thePlayer!!.openContainer.clickSlot(
                slotId = slotId,
                clickedButton = hotbarId,
                mode = ClickType.SWAP,
                player = mc.thePlayer!!
        )
        mc.playerController!!.netHandler.sendPacket(factory.newWindowClickPacket(
                windowId = 0,
                slotId = slotId,
                clickType = ClickType.SWAP,
                mouseButton = hotbarId,
                actionNumber = mc.thePlayer!!.openContainer.nextTransactionID(mc.thePlayer!!.playerInventory),
                clickedItem = null
        ))
    }
}