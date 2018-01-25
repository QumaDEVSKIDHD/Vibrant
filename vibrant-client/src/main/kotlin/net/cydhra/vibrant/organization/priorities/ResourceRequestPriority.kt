package net.cydhra.vibrant.organization.priorities

import net.cydhra.vibrant.organization.GameResourceManager

/**
 * Priority level used to allocate a resource for a module at the [GameResourceManager]. The order of priorities is dynamically
 * determined through the current [GameResourceManager.resourceRequestPriorityComparator]
 */
class ResourceRequestPriority private constructor(): Comparable<ResourceRequestPriority> {

    companion object {
        /**
         * This is probably always the lowest request priority. It should be used for resources that are completely optional for the
         * requesting module and rarely affect the module's functionality or player's (dis-) advantage when unavailable.
         */
        val optional = ResourceRequestPriority()

        /**
         * This is a rather low request priority. It should be used for requesting resources that are required for speeding up processes but
         * do also work without this resource. In other words, it is much like [optional] but could have a greater impact on player's
         * advantage and is a more narrow classifier for the purpose of the requested resource
         */
        val speed = ResourceRequestPriority()

        /**
         * Resources that are required for movement based hacks. Depending on current priority, this can be more or less important than [combat]
         */
        val movement = ResourceRequestPriority()

        /**
         * Resources that are required for combat based hacks. While usually being rather important, situation or preferences can rank
         * [movement] even higher.
         */
        val combat = ResourceRequestPriority()

        /**
         * Similar to [combat] resources, but meant for the player's ability to survive and withstand. Depending on situation or preferences,
         * this could be ranked higher or lower than [combat]
         */
        val defense = ResourceRequestPriority()

        /**
         * Resources required to keep the cheating actions of the player undetected. Depending on preferences, this could be rather important
         * or rather unimportant.
         */
        val stealth = ResourceRequestPriority()

        /**
         * This should always be the highest priority since it is meant for resources required to keep the player from being flagged, kicked
         * or autobanned by the anti-cheat system.
         */
        val detectionPrevention = ResourceRequestPriority()
    }

    /**
     * Compare the tow priority using the currently set [GameResourceManager.resourceRequestPriorityComparator]
     *
     * @see [Comparable.compareTo]
     */
    override operator fun compareTo(other: ResourceRequestPriority): Int {
        return GameResourceManager.resourceRequestPriorityComparator.compare(this, other)
    }
}