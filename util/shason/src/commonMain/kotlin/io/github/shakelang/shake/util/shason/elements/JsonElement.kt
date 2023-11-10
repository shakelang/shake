package io.github.shakelang.shake.util.shason.elements

import kotlin.jvm.JvmName

/**
 * A [JsonElement] represents all elements of json
 *
 * @see JsonPrimitive
 * @see JsonArray
 * @see JsonObject
 * @see JsonIntegerElement
 * @see JsonDoubleElement
 * @see JsonBooleanElement
 * @see JsonNullElement
 */
interface JsonElement {

    /**
     * The value of the [JsonElement]
     */
    val value: Any?

    companion object {

        /**
         * Create a [JsonElement] from any anonymous value
         * (Will throw an exception if it can't process the value)
         *
         */
        fun from(value: Any?) = when (value) {
            null -> JsonNullElement.INSTANCE
            is String -> from(value)
            is Float -> from(value)
            is Double -> from(value)
            is Byte -> from(value)
            is Short -> from(value)
            is Int -> from(value)
            is Long -> from(value)
            is Boolean -> from(value)
            is JsonElement -> value
            else -> throw Error("Can't create JsonElement from $value")
        }

        /**
         * Create a [JsonStringElement] from a [String]
         *
         */
        fun from(value: String) = JsonStringElement(value)

        /**
         * Create a [JsonBooleanElement] from a [Boolean]
         *
         */
        fun from(value: Boolean) = JsonBooleanElement.from(value)

        /**
         * Create a [JsonDoubleElement] from a [Double]
         *
         */
        fun from(value: Double) = JsonDoubleElement(value)

        /**
         * Create a [JsonDoubleElement] from a [Float]
         *
         */
        fun from(value: Float) = from(value.toDouble())

        /**
         * Create a [JsonIntegerElement] from a [Long]
         *
         */
        fun from(value: Long) = JsonIntegerElement(value)

        /**
         * Create a [JsonIntegerElement] from a [Int]
         *
         */
        fun from(value: Int) = from(value.toLong())

        /**
         * Create a [JsonIntegerElement] from a [Short]
         *
         */
        fun from(value: Short) = from(value.toLong())

        /**
         * Create a [JsonIntegerElement] from a [Byte]
         *
         */
        fun from(value: Byte) = from(value.toLong())

        /**
         * Create a [JsonObject] from a [Map] of anonymous values
         *
         */
        fun from(value: Map<String, Any?>) = JsonObject.of(value)

        /**
         * Create a [JsonObject] from anonymous values
         *
         */
        fun from(vararg values: Pair<String, Any?>) = JsonObject.of(*values)

        /**
         * Create a [JsonObject] from a [Map] of [JsonElement]s
         *
         */
        @JvmName("fromElements")
        fun from(value: Map<String, JsonElement>) = JsonObject.of(value)

        /**
         * Create a [JsonObject] from [JsonElement]s
         *
         */
        @JvmName("fromElements")
        fun from(vararg values: Pair<String, JsonElement>) = JsonObject.of(*values)

        /**
         * Create a [JsonArray] from a [Collection] of anonymous values
         *
         */
        fun from(value: Collection<Any?>) = JsonArray.of(value)

        /**
         * Create a [JsonArray] from anonymous values
         *
         */
        fun from(vararg values: Any?) = JsonArray.of(*values)

        /**
         * Create a [JsonArray] from a [Collection] of [JsonElement]s
         *
         */
        @JvmName("fromElements")
        fun from(value: Collection<JsonElement>) = JsonArray.of(value)

        /**
         * Create a [JsonArray] from [JsonElement]s
         *
         */
        @JvmName("fromElements")
        fun from(vararg values: JsonElement) = JsonArray.of(*values)
    }

    /**
     * Is the [JsonElement] a [JsonPrimitive]?
     */
    fun isJsonPrimitive() = this is JsonPrimitive

    /**
     * Cast the [JsonElement] to a [JsonPrimitive]
     */
    fun toJsonPrimitive() =
        if (this.isJsonPrimitive()) this as JsonPrimitive else throw Error("Element is not a JsonPrimitive")

    /**
     * Is the [JsonElement] a [JsonObject]?
     */
    fun isJsonObject() = this is JsonObject

    /**
     * Cast the [JsonElement] to a [JsonObject]
     */
    fun toJsonObject() = if (this.isJsonObject()) this as JsonObject else throw Error("Element is not a JsonObject")

    /**
     * Is the [JsonElement] a [JsonArray]?
     */
    fun isJsonArray() = this is JsonArray

    /**
     * Cast the [JsonElement] to a [JsonArray]
     */
    fun toJsonArray() = if (this.isJsonArray()) this as JsonArray else throw Error("Element is not a JsonObject")
}
