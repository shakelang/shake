package io.github.shakelang.shake.util.shason.elements

/**
 * A json primitive (String ([JsonStringElement]), Integer ([JsonIntegerElement]), Double ([JsonDoubleElement])
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
interface JsonPrimitive : JsonElement {

    /**
     * Is the [JsonPrimitive] a [JsonIntegerElement]?
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun isInt() = this is JsonIntegerElement

    /**
     * Cast the [JsonPrimitive] to a [JsonIntegerElement]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun toInt() = if (this.isInt()) this as JsonIntegerElement else throw Error("Element is not a JsonIntegerElement")


    /**
     * Is the [JsonPrimitive] a [JsonDoubleElement]?
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun isDouble() = this is JsonDoubleElement

    /**
     * Cast the [JsonPrimitive] to a [JsonDoubleElement]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun toDouble() =
        if (this.isDouble()) this as JsonDoubleElement else throw Error("Element is not a JsonDoubleElement")


    /**
     * Is the [JsonPrimitive] a [JsonStringElement]?
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun isString() = this is JsonStringElement

    /**
     * Cast the [JsonPrimitive] to a [JsonStringElement]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun toStringElement() =
        if (this.isString()) this as JsonStringElement else throw Error("Element is not a JsonStringElement")


    /**
     * Is the [JsonPrimitive] a [JsonBooleanElement]?
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun isBoolean() = this is JsonBooleanElement

    /**
     * Cast the [JsonPrimitive] to a [JsonBooleanElement]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun toBoolean() =
        if (this.isBoolean()) this as JsonBooleanElement else throw Error("Element is not a JsonBooleanElement")

}