package net.cydhra.vibrant.modulesystem

/**
 * A category for modules (being used to classify them)
 */
data class ModuleCategory(val name: String, val hidden: Boolean = false)

/**
 * An enumeration of default [ModuleCategory]s for the internal modules
 */
object DefaultCategories {
    val SYSTEM = ModuleCategory("System", hidden = true)
    val PLAYER = ModuleCategory("Player")
    val INVENTORY = ModuleCategory("Inventory")
    val MOVEMENT = ModuleCategory("Movement")
    val MISCELLANEOUS = ModuleCategory("Miscellaneous")
    val AUTO = ModuleCategory("Automation");
    val COMBAT = ModuleCategory("Combat")
    val BUILD = ModuleCategory("Building")
    val VISUAL = ModuleCategory("Render")
}
