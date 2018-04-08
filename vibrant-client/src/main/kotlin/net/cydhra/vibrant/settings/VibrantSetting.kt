package net.cydhra.vibrant.settings

import kotlin.reflect.KProperty

/**
 * A function that takes a ConfigBuilder extension function to define a setting and then return the setting to be used as a delegate
 *
 * @param initialValue the initial value for the setting
 * @param initialize a function with a builder as receiver, that can be used to declare the setting's behaviour
 *
 * @return a [VibrantSettingDelegate] instance
 */
fun <T : Any> setting(name: String, initialValue: T, initialize: ConfigBuilder<T>.() -> Unit = {}):
        VibrantSettingDelegate<T> {
    return ConfigBuilder(name, initialValue)
            .apply(initialize)
            .build()
}

/**
 * Extension for [ConfigBuilders][ConfigBuilder] that have a numerical type parameter and can therefore be clamped between a minimum and
 * a maximum.
 *
 * @param min inclusive minimum for the setting
 * @param max inclusive maximum for the setting
 */
fun <T> ConfigBuilder<T>.clamped(min: T, max: T) where T : Number, T : Comparable<T> {
    if (this.min != null) throw IllegalStateException("setting is already clamped")

    this.constrained { it >= min }
    this.constrained { it <= max }
    this.min = min
    this.max = max
}

/**
 * A setting delegate that registers itself at [VibrantConfiguration] and syncs up with the Vibrant configuration. Furthermore it will
 * only update it's value, if all constraints are met.
 */
open class VibrantSettingDelegate<T>(
        val name: String, initialValue: T,
        private val constraints: List<(T) -> Boolean>,
        private val incrementingFunc: ((T) -> T)? = null,
        private val decrementingFunc: ((T) -> T)? = null,
        val uniqueId: String) {
    var value: T = initialValue

    lateinit var observer: (T) -> Unit

    init {
        @Suppress("LeakingThis")
        VibrantConfiguration.registerSettingDelegate(this)
    }

    operator fun getValue(thisRef: Any, property: KProperty<*>): T = value

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        if (this.value != value) {
            if (this.constraints.all { it(value) }) {
                this.value = value
            }
        }
    }

    operator fun inc(): VibrantSettingDelegate<T> {
        if (this.incrementingFunc != null)
            this.value = this.incrementingFunc.invoke(this.value)
        return this
    }

    operator fun dec(): VibrantSettingDelegate<T> {
        if (this.decrementingFunc != null)
            this.value = this.decrementingFunc.invoke(this.value)
        return this
    }
}

/**
 * A setting that is clamped between a minimum and a maximum (both inclusive)
 */
class VibrantClampedSettingDelegate<T>(name: String,
                                       initialValue: T,
                                       val min: T,
                                       val max: T,
                                       constraints: List<(T) -> Boolean>,
                                       incrementingFunc: ((T) -> T)? = null,
                                       decrementingFunc: ((T) -> T)? = null,
                                       uniqueId: String)
    : VibrantSettingDelegate<T>(name, initialValue, constraints, incrementingFunc, decrementingFunc, uniqueId)

/**
 * A builder for [VibrantSettingDelegates][VibrantSettingDelegate]
 */
class ConfigBuilder<T : Any>(private val name: String, private val initialValue: T) {
    private val constraints = mutableListOf<(T) -> Boolean>()

    val increment = object : Operator<T> {
        override infix fun applying(operatorFunc: (T) -> T) {
            this@ConfigBuilder.incrementingFunc = operatorFunc
        }
    }

    val decrement = object : Operator<T> {
        override infix fun applying(operatorFunc: (T) -> T) {
            this@ConfigBuilder.incrementingFunc = operatorFunc
        }
    }

    var min: T? = null
    var max: T? = null

    var incrementingFunc: ((T) -> T)? = null
    var decrementingFunc: ((T) -> T)? = null

    var uniqueId = name.replace(" ", "")
        set(value) {
            field = value.replace(" ", "")
        }

    fun constrained(constraint: (T) -> Boolean): ConfigBuilder<T> {
        this.constraints.add(constraint)
        return this
    }

    fun build(): VibrantSettingDelegate<T> {
        return when {
            this.min != null -> VibrantClampedSettingDelegate(name, initialValue, min!!, max!!, constraints, incrementingFunc,
                    decrementingFunc, uniqueId)
            else -> VibrantSettingDelegate(name, initialValue, constraints, incrementingFunc, decrementingFunc, uniqueId)
        }
    }
}

infix fun Operator<Int>.by(step: Int) = applying { it + step }
infix fun Operator<Double>.by(step: Double) = applying { it + step }
infix fun Operator<Float>.by(step: Float) = applying { it + step }
infix fun Operator<Long>.by(step: Long) = applying { it + step }

/**
 * Operator that cycles through enums
 */
infix fun <T : Enum<T>> Operator<T>.cycling(enumValues: Array<T>) {
    applying { cur: T -> enumValues.let { it[(it.indexOf(cur) + 1) % it.size] } }
}

/**
 * Define an arbitrary operation on settings that modifies the value. See [ConfigBuilder.increment] or [ConfigBuilder.decrement] for
 * implementation details of this interface
 */
interface Operator<T> {
    infix fun applying(operatorFunc: (T) -> T)
}
