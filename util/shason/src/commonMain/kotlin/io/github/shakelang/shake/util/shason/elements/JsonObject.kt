package io.github.shakelang.shake.util.shason.elements

import io.github.shakelang.shake.util.shason.JSON
import io.github.shakelang.shake.util.shason.map.MapBase
import io.github.shakelang.shake.util.shason.map.MapType
import io.github.shakelang.shake.util.shason.map.MutableMapBase
import io.github.shakelang.shake.util.shason.map.MutableMapType
import kotlin.jvm.JvmName


/**
 * An object in json
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
interface JsonObject : JsonElement, MapType<String, JsonElement, JsonObject, MutableJsonObject> {

    /**
     * Implementation of [JsonObject]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;]
     */
    class JsonObjectImplementation(value: Map<String, JsonElement>) : JsonObject,
        MapBase<String, JsonElement, JsonObject, MutableJsonObject>(value) {

        /**
         * Get the value of the [JsonObject]
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override val value: Map<String, JsonElement> get() = this.map

        /**
         * Creates a new [JsonObject] from the [JsonObject]
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override fun toMap(): JsonObject = of(map)

        /**
         * Creates a new [MutableJsonObject] from the [JsonObject]
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override fun toMutableMap(): MutableJsonObject = MutableJsonObject.of(value)

        /**
         * Override toString to generate via [JSON.stringify]
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override fun toString(): String = io.github.shakelang.shake.util.shason.JSON.stringify(value)

    }

    companion object {

        /**
         * Create a [JsonObject] out of a [Map] of [JsonElement]s
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        @JvmName("ofElements")
        fun of(value: Map<String, JsonElement>) = JsonObjectImplementation(value.toMutableMap())

        /**
         * Create a [JsonObject] out of [JsonElement]s
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        @JvmName("ofElements")
        fun of(vararg values: Pair<String, JsonElement>) = JsonObjectImplementation(mutableMapOf(*values))

        /**
         * Create an empty [JsonObject]
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        fun of() = JsonObjectImplementation(mapOf())

        /**
         * Create a [JsonObject] out of a [Map] of anonymous values
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        fun of(value: Map<String, Any?>) = JsonObjectImplementation(value.mapValues { JsonElement.from(it.value) })

        /**
         * Create a [JsonObject] out of anonymous values
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        fun of(vararg values: Pair<String, Any?>) = of(mutableMapOf(*values))

    }

}


/**
 * A mutable object in json
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
interface MutableJsonObject : JsonObject, MutableMapType<String, JsonElement, JsonObject, MutableJsonObject> {

    /**
     * Implementation of [MutableJsonObject]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;]
     */
    class MutableJsonObjectImplementation(value: MutableMap<String, JsonElement>) : MutableJsonObject,
        MutableMapBase<String, JsonElement, JsonObject, MutableJsonObject>(value) {

        /**
         * Get the value of the [MutableJsonObject]
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override val value: MutableMap<String, JsonElement> get() = this.map

        /**
         * Creates a new [JsonObject] from the [MutableJsonObject]
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override fun toMap(): JsonObject = JsonObject.Companion.of(map)

        /**
         * Creates a new [MutableJsonObject] from the [MutableJsonObject]
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override fun toMutableMap(): MutableJsonObject = of(value)

        /**
         * Override toString to generate via [JSON.stringify]
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override fun toString(): String = io.github.shakelang.shake.util.shason.JSON.stringify(value)

    }

    companion object {

        /**
         * Create a [MutableJsonObject] out of a [Map] of [JsonElement]s
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        @JvmName("ofElements")
        fun of(value: Map<String, JsonElement>) = MutableJsonObjectImplementation(value.toMutableMap())

        /**
         * Create a [MutableJsonObject] out of [JsonElement]s
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        @JvmName("ofElements")
        fun of(vararg values: Pair<String, JsonElement>) = MutableJsonObjectImplementation(mutableMapOf(*values))

        /**
         * Create an empty [MutableJsonObject]
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        fun of() = MutableJsonObjectImplementation(mutableMapOf())

        /**
         * Create a [MutableJsonObject] out of a [Map] of anonymous values
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        fun of(value: Map<String, Any?>) =
            MutableJsonObjectImplementation(value.mapValues { JsonElement.from(it.value) }.toMutableMap())

        /**
         * Create a [MutableJsonObject] out of anonymous values
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        fun of(vararg values: Pair<String, Any?>) = of(mapOf(*values))

    }

}


/**
 * Create a [MutableJsonObject] out of a [Map] of [JsonElement]s
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
@JvmName("mutableJsonObjectOfElements")
fun mutableJsonObjectOf(value: Map<String, JsonElement>) = MutableJsonObject.of(value)

/**
 * Create a [MutableJsonObject] out of [JsonElement]s
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
@JvmName("mutableJsonObjectOfElements")
fun mutableJsonObjectOf(vararg values: Pair<String, JsonElement>) = MutableJsonObject.of(*values)

/**
 * Create a [JsonObject] out of a [Map] of anonymous values
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
fun mutableJsonObjectOf(value: Map<String, Any?>) = MutableJsonObject.of(value)

/**
 * Create a [MutableJsonObject] out of anonymous values
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
fun mutableJsonObjectOf(vararg values: Pair<String, Any?>) = MutableJsonObject.of(*values)

/**
 * Create an empty [MutableJsonObject]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
fun mutableJsonObjectOf() = MutableJsonObject.of()


/**
 * Create a [JsonObject] out of a [Map] of [JsonElement]s
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
@JvmName("jsonObjectOfElements")
fun jsonObjectOf(value: Map<String, JsonElement>) = JsonObject.of(value)

/**
 * Create a [JsonObject] out of [JsonElement]s
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
@JvmName("jsonObjectOfElements")
fun jsonObjectOf(vararg values: Pair<String, JsonElement>) = JsonObject.of(*values)


/**
 * Create a [JsonObject] out of a [Map] of anonymous values
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
fun jsonObjectOf(value: Map<String, Any?>) = JsonObject.of(value)

/**
 * Create a [JsonObject] out of anonymous values
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
fun jsonObjectOf(vararg values: Pair<String, Any?>) = JsonObject.of(*values)

/**
 * Create an empty [JsonObject]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
fun jsonObjectOf() = JsonObject.of()