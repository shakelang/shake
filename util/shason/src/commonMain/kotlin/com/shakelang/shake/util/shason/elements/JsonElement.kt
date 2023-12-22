package com.shakelang.shake.util.shason.elements

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
 * @since 0.1.0
 * @version 0.1.0
 */
interface JsonElement {

    /**
     * The value of the [JsonElement]
     * @since 0.1.0
     * @version 0.1.0
     */
    val value: Any?

    companion object {

        /**
         * Create a [JsonElement] from any anonymous value
         * (Will throw an exception if it can't process the value)
         * @since 0.1.0
         * @version 0.1.0
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
         * @since 0.1.0
         * @version 0.1.0
         */
        fun from(value: String) = JsonStringElement(value)

        /**
         * Create a [JsonBooleanElement] from a [Boolean]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun from(value: Boolean) = JsonBooleanElement.from(value)

        /**
         * Create a [JsonDoubleElement] from a [Double]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun from(value: Double) = JsonDoubleElement(value)

        /**
         * Create a [JsonDoubleElement] from a [Float]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun from(value: Float) = from(value.toDouble())

        /**
         * Create a [JsonIntegerElement] from a [Long]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun from(value: Long) = JsonIntegerElement(value)

        /**
         * Create a [JsonIntegerElement] from a [Int]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun from(value: Int) = from(value.toLong())

        /**
         * Create a [JsonIntegerElement] from a [Short]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun from(value: Short) = from(value.toLong())

        /**
         * Create a [JsonIntegerElement] from a [Byte]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun from(value: Byte) = from(value.toLong())

        /**
         * Create a [JsonObject] from a [Map] of anonymous values
         * @since 0.1.0
         * @version 0.1.0
         */
        fun from(value: Map<String, Any?>) = JsonObject.of(value)

        /**
         * Create a [JsonObject] from anonymous values
         * @since 0.1.0
         * @version 0.1.0
         */
        fun from(vararg values: Pair<String, Any?>) = JsonObject.of(*values)

        /**
         * Create a [JsonObject] from a [Map] of [JsonElement]s
         * @since 0.1.0
         * @version 0.1.0
         */
        @JvmName("fromElements")
        fun from(value: Map<String, JsonElement>) = JsonObject.of(value)

        /**
         * Create a [JsonObject] from [JsonElement]s
         * @since 0.1.0
         * @version 0.1.0
         */
        @JvmName("fromElements")
        fun from(vararg values: Pair<String, JsonElement>) = JsonObject.of(*values)

        /**
         * Create a [JsonArray] from a [Collection] of anonymous values
         * @since 0.1.0
         * @version 0.1.0
         */
        fun from(value: Collection<Any?>) = JsonArray.of(value)

        /**
         * Create a [JsonArray] from anonymous values
         * @since 0.1.0
         * @version 0.1.0
         */
        fun from(vararg values: Any?) = JsonArray.of(*values)

        /**
         * Create a [JsonArray] from a [Collection] of [JsonElement]s
         * @since 0.1.0
         * @version 0.1.0
         */
        @JvmName("fromElements")
        fun from(value: Collection<JsonElement>) = JsonArray.of(value)

        /**
         * Create a [JsonArray] from [JsonElement]s
         * @since 0.1.0
         * @version 0.1.0
         */
        @JvmName("fromElements")
        fun from(vararg values: JsonElement) = JsonArray.of(*values)
    }

    /**
     * Is the [JsonElement] a [JsonPrimitive]?
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isJsonPrimitive() = this is JsonPrimitive

    /**
     * Cast the [JsonElement] to a [JsonPrimitive]
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toJsonPrimitive() =
        if (this.isJsonPrimitive()) this as JsonPrimitive else throw Error("Element is not a JsonPrimitive")

    /**
     * Is the [JsonElement] a [JsonObject]?
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isJsonObject() = this is JsonObject

    /**
     * Cast the [JsonElement] to a [JsonObject]
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toJsonObject() = if (this.isJsonObject()) this as JsonObject else throw Error("Element is not a JsonObject")

    /**
     * Is the [JsonElement] a [JsonArray]?
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isJsonArray() = this is JsonArray

    /**
     * Cast the [JsonElement] to a [JsonArray]
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toJsonArray() = if (this.isJsonArray()) this as JsonArray else throw Error("Element is not a JsonObject")

    /**
     * Is the [JsonElement] a [JsonNullElement]?
     * @since 0.3.0
     * @version 0.3.0
     */
    fun isJsonNull() = this is JsonNullElement

    /**
     * Cast the [JsonElement] to a [JsonNullElement]
     * @since 0.3.0
     * @version 0.3.0
     */
    fun toJsonNull() =
        if (this.isJsonNull()) this as JsonNullElement else throw Error("Element is not a JsonNullElement")

    /**
     * Is the [JsonElement] a [JsonStringElement]?
     * @since 0.3.0
     * @version 0.3.0
     */
    fun isJsonString() = this is JsonStringElement

    /**
     * Cast the [JsonElement] to a [JsonStringElement]
     * @since 0.3.0
     * @version 0.3.0
     */
    fun toJsonString() =
        if (this.isJsonString()) this as JsonStringElement else throw Error("Element is not a JsonStringElement")

    /**
     * Is the [JsonElement] a [JsonIntegerElement]?
     * @since 0.3.0
     * @version 0.3.0
     */
    fun isJsonInteger() = this is JsonIntegerElement

    /**
     * Cast the [JsonElement] to a [JsonIntegerElement]
     * @since 0.3.0
     * @version 0.3.0
     */
    fun toJsonInteger() =
        if (this.isJsonInteger()) this as JsonIntegerElement else throw Error("Element is not a JsonIntegerElement")

    /**
     * Is the [JsonElement] a [JsonDoubleElement]?
     * @since 0.3.0
     * @version 0.3.0
     */
    fun isJsonDouble() = this is JsonDoubleElement

    /**
     * Cast the [JsonElement] to a [JsonDoubleElement]
     * @since 0.3.0
     * @version 0.3.0
     */
    fun toJsonDouble() =
        if (this.isJsonDouble()) this as JsonDoubleElement else throw Error("Element is not a JsonDoubleElement")

    /**
     * Is the [JsonElement] a [JsonBooleanElement]?
     * @since 0.3.0
     * @version 0.3.0
     */
    fun isJsonBoolean() = this is JsonBooleanElement

    /**
     * Cast the [JsonElement] to a [JsonBooleanElement]
     * @since 0.3.0
     * @version 0.3.0
     */
    fun toJsonBoolean() =
        if (this.isJsonBoolean()) this as JsonBooleanElement else throw Error("Element is not a JsonBooleanElement")
}
