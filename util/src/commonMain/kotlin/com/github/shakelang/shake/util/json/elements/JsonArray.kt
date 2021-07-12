package com.github.shakelang.shake.util.json.elements

import com.github.shakelang.shake.util.json.*
import com.github.shakelang.shake.util.json.collection.CollectionBase
import com.github.shakelang.shake.util.json.collection.CollectionType
import com.github.shakelang.shake.util.json.collection.MutableCollectionBase
import com.github.shakelang.shake.util.json.collection.MutableCollectionType
import kotlin.jvm.JvmName


/**
 * An array in json
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
interface JsonArray : JsonElement, CollectionType<JsonElement, JsonArray, MutableJsonArray> {

    /**
     * Implementation of [JsonArray]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;]
     */
    class JsonArrayImplementation (value: Collection<JsonElement>) : JsonArray,
        CollectionBase<JsonElement, JsonArray, MutableJsonArray>(value) {

        /**
         * Get the value of the JsonArray
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override val value get() = collection

        /**
         * Creates a new [JsonArray] from the [JsonArray]
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override fun toCollection(): JsonArray = of(value)

        /**
         * Creates a new [MutableJsonArray] from the [JsonArray]
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override fun toMutableCollection(): MutableJsonArray = MutableJsonArray.of(value)

        /**
         * Override toString to generate via JSON.stringify
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override fun toString(): String = json.stringify(value)

    }

    companion object {

        /**
         * Create a [JsonArray] out of a [Collection] of [JsonElement]s
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        @JvmName("ofElements")
        fun of(value: Collection<JsonElement>) = JsonArrayImplementation(value.toList())

        /**
         * Create a [JsonArray] out of [JsonElement]s
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        @JvmName("ofElements")
        fun of(vararg values: JsonElement) = JsonArrayImplementation(values.toList())

        /**
         * Create an empty [JsonArray]
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        fun of() = JsonArrayImplementation(listOf())

        /**
         * Create a [JsonArray] out of a [Collection] of anonymous values
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        fun of(value: Collection<Any?>) = JsonArrayImplementation(value.map { JsonElement.from(it) })

        /**
         * Create a [JsonArray] out of anonymous values
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        fun of(vararg values: Any?) = JsonArrayImplementation(values.map { JsonElement.from(it) })

    }

}


/**
 * A mutable array in json
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
interface MutableJsonArray : JsonArray, MutableCollectionType<JsonElement, JsonArray, MutableJsonArray> {

    /**
     * Implementation of [MutableJsonArrayImplementation]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;]
     */
    class MutableJsonArrayImplementation (value: MutableCollection<JsonElement>) : MutableJsonArray,
        MutableCollectionBase<JsonElement, JsonArray, MutableJsonArray>(value) {

        /**
         * Get the value of the JsonArray
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override val value get() = collection

        /**
         * Creates a new [JsonArray] from the [JsonArray]
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override fun toCollection(): JsonArray = JsonArray.of(value)

        /**
         * Creates a new [MutableJsonArray] from the [JsonArray]
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override fun toMutableCollection(): MutableJsonArray = of(value)

        /**
         * Override toString to generate via JSON.stringify
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override fun toString(): String = JSON.stringify(value)
    }

    companion object {

        /**
         * Create a [MutableJsonArray] out of a [Collection] of [JsonElement]s
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        @JvmName("ofElements")
        fun of(value: Collection<JsonElement>) = MutableJsonArrayImplementation(value.toMutableList())

        /**
         * Create a [MutableJsonArray] out of [JsonElement]s
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        @JvmName("ofElements")
        fun of(vararg values: JsonElement) = MutableJsonArrayImplementation(values.toMutableList())

        /**
         * Create an empty [MutableJsonArray]
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        fun of() = MutableJsonArrayImplementation(mutableListOf())

        /**
         * Create a [MutableJsonArray] out of a [Collection] of anonymous values
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        fun of(value: Collection<Any?>)
                = MutableJsonArrayImplementation(value.map { JsonElement.from(it) }.toMutableList())

        /**
         * Create a [MutableJsonArray] out of anonymous values
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        fun of(vararg values: Any?)
                = MutableJsonArrayImplementation(values.map { JsonElement.from(it) }.toMutableList())

    }

}


/**
 * Create a [MutableJsonArray] out of a [Collection] of [JsonElement]s
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
@JvmName("mutableJsonArrayOfElements")
fun mutableJsonArrayOf(value: Collection<JsonElement>) = MutableJsonArray.of(value)

/**
 * Create a [MutableJsonArray] out of [JsonElement]s
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
@JvmName("mutableJsonArrayOfElements")
fun mutableJsonArrayOf(vararg values: JsonElement) = MutableJsonArray.of(*values)

/**
 * Create a [MutableJsonArray] out of a [Collection] of anonymous values
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
fun mutableJsonArrayOf(value: Collection<Any?>) = MutableJsonArray.of(value)

/**
 * Create a [MutableJsonArray] out of anonymous values
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
fun mutableJsonArrayOf(vararg values: Any?) = MutableJsonArray.of(*values)


/**
 * Create an empty [MutableJsonArray]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
fun mutableJsonArrayOf() = MutableJsonArray.of()



/**
 * Create a [JsonArray] out of a [Collection] of [JsonElement]s
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
@JvmName("jsonArrayOfElements")
fun jsonArrayOf(value: Collection<JsonElement>) = JsonArray.of(value)

/**
 * Create a [JsonArray] out of [JsonElement]s
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
@JvmName("jsonArrayOfElements")
fun jsonArrayOf(vararg values: JsonElement) = JsonArray.of(*values)

/**
 * Create a [JsonArray] out of a [Collection] of anonymous values
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
fun jsonArrayOf(value: Collection<Any?>) = JsonArray.of(value)

/**
 * Create a [JsonArray] out of anonymous values
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
fun jsonArrayOf(vararg values: Any?) = JsonArray.of(*values)


/**
 * Create an empty [JsonArray]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
fun jsonArrayOf() = MutableJsonArray.of()
