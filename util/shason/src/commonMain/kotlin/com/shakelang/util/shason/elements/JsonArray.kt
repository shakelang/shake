package com.shakelang.util.shason.elements

import com.shakelang.util.shason.JSON
import com.shakelang.util.shason.collection.ListBase
import com.shakelang.util.shason.collection.ListType
import com.shakelang.util.shason.collection.MutableListBase
import com.shakelang.util.shason.collection.MutableListType
import kotlin.jvm.JvmName

/**
 * An array in json
 * @since 0.1.0
 * @version 0.1.0
 */
@Suppress("unused")
interface JsonArray : JsonElement, ListType<JsonElement, JsonArray, MutableJsonArray> {

    // Override the methods for optimization
    override fun isJsonNull(): Boolean = false
    override fun isJsonBoolean(): Boolean = false
    override fun isJsonInteger(): Boolean = false
    override fun isJsonDouble(): Boolean = false
    override fun isJsonString(): Boolean = false
    override fun isJsonArray(): Boolean = true
    override fun isJsonObject(): Boolean = false
    override fun isJsonPrimitive(): Boolean = false

    /**
     * Implementation of [JsonArray]
     * @since 0.1.0
     * @version 0.1.0
     */
    class JsonArrayImplementation(value: List<JsonElement>) : JsonArray,
        ListBase<JsonElement, JsonArray, MutableJsonArray>(value) {

        /**
         * Get the value of the [JsonArray]
         * @since 0.1.0
         * @version 0.1.0
         *
         */
        override val value get() = list

        /**
         * Creates a new [JsonArray] from the [JsonArray]
         * @since 0.1.0
         * @version 0.1.0
         *
         */
        override fun toCollection(): JsonArray = of(value)

        /**
         * Creates a new [MutableJsonArray] from the [JsonArray]
         * @since 0.1.0
         * @version 0.1.0
         *
         */
        override fun toMutableCollection(): MutableJsonArray = MutableJsonArray.of(value)

        /**
         * Override toString to generate via [JSON.stringify]
         * @since 0.1.0
         * @version 0.1.0
         *
         */
        override fun toString(): String = JSON.stringify(value)
    }

    companion object {

        /**
         * Create a [JsonArray] out of a [Collection] of [JsonElement]s
         * @since 0.1.0
         * @version 0.1.0
         */
        @JvmName("ofElements")
        fun of(value: Collection<JsonElement>): JsonArray = JsonArrayImplementation(value.toList())

        /**
         * Create a [JsonArray] out of [JsonElement]s
         * @since 0.1.0
         * @version 0.1.0
         */
        @JvmName("ofElements")
        fun of(vararg values: JsonElement): JsonArray = JsonArrayImplementation(values.toList())

        /**
         * Create an empty [JsonArray]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun of(): JsonArray = JsonArrayImplementation(listOf())

        /**
         * Create a [JsonArray] out of a [Collection] of anonymous values
         * @since 0.1.0
         * @version 0.1.0
         */
        fun of(value: Collection<Any?>): JsonArray = JsonArrayImplementation(value.map { JsonElement.from(it) })

        /**
         * Create a [JsonArray] out of anonymous values
         * @since 0.1.0
         * @version 0.1.0
         */
        fun of(vararg values: Any?): JsonArray = JsonArrayImplementation(values.map { JsonElement.from(it) })
    }
}

/**
 * A mutable array in json
 * @since 0.1.0
 * @version 0.1.0
 */
@Suppress("unused")
interface MutableJsonArray : JsonArray, MutableListType<JsonElement, JsonArray, MutableJsonArray> {

    /**
     * Implementation of [MutableJsonArrayImplementation]
     * @since 0.1.0
     * @version 0.1.0
     */
    class MutableJsonArrayImplementation(value: MutableList<JsonElement>) : MutableJsonArray,
        MutableListBase<JsonElement, JsonArray, MutableJsonArray>(value) {

        /**
         * Get the value of the [MutableJsonArray]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val value get() = list

        /**
         * Creates a new [JsonArray] from the [MutableJsonArray]
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toCollection(): JsonArray = JsonArray.of(value)

        /**
         * Creates a new [MutableJsonArray] from the [MutableJsonArray]
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toMutableCollection(): MutableJsonArray = of(value)

        /**
         * Override toString to generate via [JSON.stringify]
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString(): String = JSON.stringify(value)
    }

    companion object {

        /**
         * Create a [MutableJsonArray] out of a [Collection] of [JsonElement]s
         * @since 0.1.0
         * @version 0.1.0
         */
        @JvmName("ofElements")
        fun of(value: Collection<JsonElement>) = MutableJsonArrayImplementation(value.toMutableList())

        /**
         * Create a [MutableJsonArray] out of [JsonElement]s
         * @since 0.1.0
         * @version 0.1.0
         */
        @JvmName("ofElements")
        fun of(vararg values: JsonElement) = MutableJsonArrayImplementation(values.toMutableList())

        /**
         * Create an empty [MutableJsonArray]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun of() = MutableJsonArrayImplementation(mutableListOf())

        /**
         * Create a [MutableJsonArray] out of a [Collection] of anonymous values
         * @since 0.1.0
         * @version 0.1.0
         */
        fun of(value: Collection<Any?>) =
            MutableJsonArrayImplementation(value.map { JsonElement.from(it) }.toMutableList())

        /**
         * Create a [MutableJsonArray] out of anonymous values
         * @since 0.1.0
         * @version 0.1.0
         */
        fun of(vararg values: Any?) =
            MutableJsonArrayImplementation(values.map { JsonElement.from(it) }.toMutableList())
    }
}

/**
 * Create a [MutableJsonArray] out of a [Collection] of [JsonElement]s
 * @since 0.1.0
 * @version 0.1.0
 */
@Suppress("unused")
@JvmName("mutableJsonArrayOfElements")
fun mutableJsonArrayOf(value: Collection<JsonElement>) = MutableJsonArray.of(value)

/**
 * Create a [MutableJsonArray] out of [JsonElement]s
 * @since 0.1.0
 * @version 0.1.0
 */
@Suppress("unused")
@JvmName("mutableJsonArrayOfElements")
fun mutableJsonArrayOf(vararg values: JsonElement) = MutableJsonArray.of(*values)

/**
 * Create a [MutableJsonArray] out of a [Collection] of anonymous values
 * @since 0.1.0
 * @version 0.1.0
 */
@Suppress("unused")
fun mutableJsonArrayOf(value: Collection<Any?>) = MutableJsonArray.of(value)

/**
 * Create a [MutableJsonArray] out of anonymous values
 * @since 0.1.0
 * @version 0.1.0
 */
@Suppress("unused")
fun mutableJsonArrayOf(vararg values: Any?) = MutableJsonArray.of(*values)

/**
 * Create an empty [MutableJsonArray]
 * @since 0.1.0
 * @version 0.1.0
 */
@Suppress("unused")
fun mutableJsonArrayOf() = MutableJsonArray.of()

/**
 * Create a [JsonArray] out of a [Collection] of [JsonElement]s
 * @since 0.1.0
 * @version 0.1.0
 */
@Suppress("unused")
@JvmName("jsonArrayOfElements")
fun jsonArrayOf(value: Collection<JsonElement>) = JsonArray.of(value)

/**
 * Create a [JsonArray] out of [JsonElement]s
 * @since 0.1.0
 * @version 0.1.0
 */
@Suppress("unused")
@JvmName("jsonArrayOfElements")
fun jsonArrayOf(vararg values: JsonElement) = JsonArray.of(*values)

/**
 * Create a [JsonArray] out of a [Collection] of anonymous values
 * @since 0.1.0
 * @version 0.1.0
 */
@Suppress("unused")
fun jsonArrayOf(value: Collection<Any?>) = JsonArray.of(value)

/**
 * Create a [JsonArray] out of anonymous values
 * @since 0.1.0
 * @version 0.1.0
 */
@Suppress("unused")
fun jsonArrayOf(vararg values: Any?) = JsonArray.of(*values)

/**
 * Create an empty [JsonArray]
 * @since 0.1.0
 * @version 0.1.0
 */
@Suppress("unused")
fun jsonArrayOf() = MutableJsonArray.of()
