package net.cydhra.vibrant.organization.resources

/**
 *
 */
abstract class GameResourceState(vararg val partialStates: PartialState<*>) {

}

/**
 * Empty state, where the lock does not define any
 */
private object DONT_CARE

/**
 * A [GameResourceState] consists of multiple [PartialStates][PartialState] that can be instanced with [DONT_CARE], if
 * the [net.cydhra.vibrant.organization.locks.ResourceLock] does not care which state is active.
 *
 * @param state any state of type [T] or null, if the lock does not care
 */
class PartialState<T>(state: T?) {

    private val p_state: Any

    @Suppress("UNCHECKED_CAST")
    val state: T
        get() {
            if (doesCare())
                return p_state as T
            else
                throw IllegalStateException("Do not retrieve state if lock does not care")
        }

    init {
        this.p_state = state ?: DONT_CARE
    }

    /**
     * @return true, if the lock defines any state for this partial state
     */
    fun doesCare() = this.state != DONT_CARE
}