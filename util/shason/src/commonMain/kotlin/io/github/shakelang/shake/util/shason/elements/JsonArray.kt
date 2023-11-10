package io.github.shakelang.shake.util.shason.elements

import io.github.shakelang.shake.util.shason.JSON
import io.github.shakelang.shake.util.shason.collection.ListBase
import io.github.shakelang.shake.util.shason.collection.ListType
import io.github.shakelang.shake.util.shason.collection.MutableListBase
import io.github.shakelang.shake.util.shason.collection.MutableListType
import kotlin.jvm.JvmName

/**
 * An array in json
 */
@Suppress("unused")
interface JsonArray : JsonElement, ListType<JsonElement, JsonArray, MutableJsonArray> {

    /**
     * Implementation of [JsonArray]
     */
    class JsonArrayImplementation(value: List<JsonElement>) : JsonArray,
        ListBase<JsonElement, JsonArray, MutableJsonArray>(value) {

        /**
         * Get the value of the [JsonArray]
         *
         */
        override val value get() = list

        /**
         * Creates a new [JsonArray] from the [JsonArray]
         *
         */
        override fun toCollection(): JsonArray = of(value)

        /**
         * Creates a new [MutableJsonArray] from the [JsonArray]
         *
         */
        override fun toMutableCollection(): MutableJsonArray = MutableJsonArray.of(value)

        /**
         * Override toString to generate via [JSON.stringify]
         *
         */
        override fun toString(): String = io.github.shakelang.shake.util.shason.JSON.stringify(value)
    }

    companion object {

        /**
         * Create a [JsonArray] out of a [Collection] of [JsonElement]s
         *
         */
        @JvmName("ofElements")
        fun of(value: Collection<JsonElement>) = JsonArrayImplementation(value.toList())

        /**
         * Create a [JsonArray] out of [JsonElement]s
         *
         */
        @JvmName("ofElements")
        fun of(vararg values: JsonElement) = JsonArrayImplementation(values.toList())

        /**
         * Create an empty [JsonArray]
         *
         */
        fun of() = JsonArrayImplementation(listOf())

        /**
         * Create a [JsonArray] out of a [Collection] of anonymous values
         *
         */
        fun of(value: Collection<Any?>) = JsonArrayImplementation(value.map { JsonElement.from(it) })

        /**
         * Create a [JsonArray] out of anonymous values
         *
         */
        fun of(vararg values: Any?) = JsonArrayImplementation(values.map { JsonElement.from(it) })
    }
}

/**
 * A mutable array in json
 */
@Suppress("unused")
interface MutableJsonArray : JsonArray, MutableListType<JsonElement, JsonArray, MutableJsonArray> {

    /**
     * Implementation of [MutableJsonArrayImplementation]
     */
    class MutableJsonArrayImplementation(value: MutableList<JsonElement>) : MutableJsonArray,
        MutableListBase<JsonElement, JsonArray, MutableJsonArray>(value) {

        /**
         * Get the value of the [MutableJsonArray]
         *
         */
        override val value get() = list

        /**
         * Creates a new [JsonArray] from the [MutableJsonArray]
         *
         */
        override fun toCollection(): JsonArray = JsonArray.of(value)

        /**
         * Creates a new [MutableJsonArray] from the [MutableJsonArray]
         *
         */
        override fun toMutableCollection(): MutableJsonArray = of(value)

        /**
         * Override toString to generate via [JSON.stringify]
         *
         */
        override fun toString(): String = io.github.shakelang.shake.util.shason.JSON.stringify(value)
    }

    companion object {

        /**
         * Create a [MutableJsonArray] out of a [Collection] of [JsonElement]s
         *
         */
        @JvmName("ofElements")
        fun of(value: Collection<JsonElement>) = MutableJsonArrayImplementation(value.toMutableList())

        /**
         * Create a [MutableJsonArray] out of [JsonElement]s
         *
         */
        @JvmName("ofElements")
        fun of(vararg values: JsonElement) = MutableJsonArrayImplementation(values.toMutableList())

        /**
         * Create an empty [MutableJsonArray]
         *
         */
        fun of() = MutableJsonArrayImplementation(mutableListOf())

        /**
         * Create a [MutableJsonArray] out of a [Collection] of anonymous values
         *
         */
        fun of(value: Collection<Any?>) =
            MutableJsonArrayImplementation(value.map { JsonElement.from(it) }.toMutableList())

        /**
         * Create a [MutableJsonArray] out of anonymous values
         *
         */
        fun of(vararg values: Any?) =
            MutableJsonArrayImplementation(values.map { JsonElement.from(it) }.toMutableList())
    }
}

/**
 * Create a [MutableJsonArray] out of a [Collection] of [JsonElement]s
 */
@Suppress("unused")
@JvmName("mutableJsonArrayOfElements")
fun mutableJsonArrayOf(value: Collection<JsonElement>) = MutableJsonArray.of(value)

/**
 * Create a [MutableJsonArray] out of [JsonElement]s
 */
@Suppress("unused")
@JvmName("mutableJsonArrayOfElements")
fun mutableJsonArrayOf(vararg values: JsonElement) = MutableJsonArray.of(*values)

/**
 * Create a [MutableJsonArray] out of a [Collection] of anonymous values
 */
@Suppress("unused")
fun mutableJsonArrayOf(value: Collection<Any?>) = MutableJsonArray.of(value)

/**
 * Create a [MutableJsonArray] out of anonymous values
 */
@Suppress("unused")
fun mutableJsonArrayOf(vararg values: Any?) = MutableJsonArray.of(*values)

/**
 * Create an empty [MutableJsonArray]
 */
@Suppress("unused")
fun mutableJsonArrayOf() = MutableJsonArray.of()

/**
 * Create a [JsonArray] out of a [Collection] of [JsonElement]s
 */
@Suppress("unused")
@JvmName("jsonArrayOfElements")
fun jsonArrayOf(value: Collection<JsonElement>) = JsonArray.of(value)

/**
 * Create a [JsonArray] out of [JsonElement]s
 */
@Suppress("unused")
@JvmName("jsonArrayOfElements")
fun jsonArrayOf(vararg values: JsonElement) = JsonArray.of(*values)

/**
 * Create a [JsonArray] out of a [Collection] of anonymous values
 */
@Suppress("unused")
fun jsonArrayOf(value: Collection<Any?>) = JsonArray.of(value)

/**
 * Create a [JsonArray] out of anonymous values
 */
@Suppress("unused")
fun jsonArrayOf(vararg values: Any?) = JsonArray.of(*values)

/**
 * Create an empty [JsonArray]
 */
@Suppress("unused")
fun jsonArrayOf() = MutableJsonArray.of()
