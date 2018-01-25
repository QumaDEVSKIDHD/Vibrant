package net.cydhra.vibrant.organization.priorities

/**
 * The default client priority order is used.
 */
class DefaultPriorityComparator : Comparator<ResourceRequestPriority> {

    override fun compare(o1: ResourceRequestPriority, o2: ResourceRequestPriority): Int {
        val order: (ResourceRequestPriority) -> Int = { priority -> when(priority) {
                ResourceRequestPriority.optional -> 0
                ResourceRequestPriority.stealth -> 1
                ResourceRequestPriority.speed -> 2
                ResourceRequestPriority.movement -> 3
                ResourceRequestPriority.defense -> 4
                ResourceRequestPriority.combat -> 5
                ResourceRequestPriority.detectionPrevention -> 6
                else -> throw AssertionError("unknown priority")
            }
        }

        return Integer.compare(order(o1), order(o2))
    }
}