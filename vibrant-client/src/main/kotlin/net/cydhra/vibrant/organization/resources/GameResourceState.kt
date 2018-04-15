package net.cydhra.vibrant.organization.resources

import kotlin.reflect.KProperty

/**
 *
 */
abstract class GameResourceState() {

    private val mutablePartialStates: MutableList<Partial<*>> = mutableListOf()

    val partialStates: List<Partial<*>> = mutablePartialStates

    /**
     * Generate a version of this [GameResourceState] that consists only of don't cares
     */
    abstract fun generateEmptyState(): GameResourceState

    /**
     * A [GameResourceState] consists of multiple [PartialStates][PartialState] that can be instanced with [DONT_CARE], if
     * the [net.cydhra.vibrant.organization.locks.ResourceLock] does not care which state is active.
     *
     * @param state any state of type [T] or null, if the lock does not care
     */
    inner class Partial<T>(state: T?) {

        private var p_state: Any


        @Suppress("UNCHECKED_CAST")
        val state: T
            get() {
                if (doesCare())
                    return p_state as T
                else
                    throw IllegalStateException("Do not retrieve state if lock does not care")
            }


        init {
            this.p_state = state as? Any ?: DONT_CARE

            this@GameResourceState.mutablePartialStates += this
        }

        operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
            return this.state
        }

        /**
         * @return true, if the lock defines any state for this partial state
         */
        fun doesCare() = this.p_state != DONT_CARE

        /**
         * This may only be called by resource channels
         */
        internal fun unsafeChangeState(state: Any) {
            p_state = state
        }
    }
}

/**
 * Empty state, where the lock does not define any
 */
private object DONT_CARE