package io.github.shakelang.shake.util.shason.elements

/**
 * A json primitive (String ([JsonStringElement]), Integer ([JsonIntegerElement]), Double ([JsonDoubleElement])
 */
interface JsonPrimitive : JsonElement {

    /**
     * Is the [JsonPrimitive] a [JsonIntegerElement]?
     */
    fun isInt() = this is JsonIntegerElement

    /**
     * Cast the [JsonPrimitive] to a [JsonIntegerElement]
     */
    fun toInt() = if (this.isInt()) this as JsonIntegerElement else throw Error("Element is not a JsonIntegerElement")

    /**
     * Is the [JsonPrimitive] a [JsonDoubleElement]?
     */
    fun isDouble() = this is JsonDoubleElement

    /**
     * Cast the [JsonPrimitive] to a [JsonDoubleElement]
     */
    fun toDouble() =
        if (this.isDouble()) this as JsonDoubleElement else throw Error("Element is not a JsonDoubleElement")

    /**
     * Is the [JsonPrimitive] a [JsonStringElement]?
     */
    fun isString() = this is JsonStringElement

    /**
     * Cast the [JsonPrimitive] to a [JsonStringElement]
     */
    fun toStringElement() =
        if (this.isString()) this as JsonStringElement else throw Error("Element is not a JsonStringElement")

    /**
     * Is the [JsonPrimitive] a [JsonBooleanElement]?
     */
    fun isBoolean() = this is JsonBooleanElement

    /**
     * Cast the [JsonPrimitive] to a [JsonBooleanElement]
     */
    fun toBoolean() =
        if (this.isBoolean()) this as JsonBooleanElement else throw Error("Element is not a JsonBooleanElement")
}
