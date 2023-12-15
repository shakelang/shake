package com.shakelang.shake.util.shason.elements

/**
 * A json primitive (String ([JsonStringElement]), Integer ([JsonIntegerElement]), Double ([JsonDoubleElement])
 * @since 0.1.0
 * @version 0.1.0
 */
interface JsonPrimitive : JsonElement {

    /**
     * Is the [JsonPrimitive] a [JsonNullElement]?
     * @since 0.3.0
     * @version 0.3.0
     */
    fun isNull() = this is JsonNullElement

    /**
     * Is the [JsonPrimitive] a [JsonNullElement]?
     * @since 0.3.0
     * @version 0.3.0
     */
    fun toNull() = if (this.isNull()) this as JsonNullElement else throw Error("Element is not a JsonNullElement")

    /**
     * Is the [JsonPrimitive] a [JsonIntegerElement]?
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isInt() = this is JsonIntegerElement

    /**
     * Cast the [JsonPrimitive] to a [JsonIntegerElement]
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toInt() = if (this.isInt()) this as JsonIntegerElement else throw Error("Element is not a JsonIntegerElement")

    /**
     * Is the [JsonPrimitive] a [JsonDoubleElement]?
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isDouble() = this is JsonDoubleElement

    /**
     * Cast the [JsonPrimitive] to a [JsonDoubleElement]
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toDouble() =
        if (this.isDouble()) this as JsonDoubleElement else throw Error("Element is not a JsonDoubleElement")

    /**
     * Is the [JsonPrimitive] a [JsonStringElement]?
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isString() = this is JsonStringElement

    /**
     * Cast the [JsonPrimitive] to a [JsonStringElement]
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toStringElement() =
        if (this.isString()) this as JsonStringElement else throw Error("Element is not a JsonStringElement")

    /**
     * Is the [JsonPrimitive] a [JsonBooleanElement]?
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isBoolean() = this is JsonBooleanElement

    /**
     * Cast the [JsonPrimitive] to a [JsonBooleanElement]
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toBoolean() =
        if (this.isBoolean()) this as JsonBooleanElement else throw Error("Element is not a JsonBooleanElement")

    // For optimization, override the following methods
    override fun isJsonArray() = false
    override fun isJsonObject() = false
    override fun isJsonPrimitive() = true
}
