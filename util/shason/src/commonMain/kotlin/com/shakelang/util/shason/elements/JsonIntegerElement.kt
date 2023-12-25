package com.shakelang.util.shason.elements

import com.shakelang.util.shason.JSON

/**
 * A json representation of integer values
 * @since 0.1.0
 * @version 0.1.0
 */
class JsonIntegerElement(

    /**
     * The value of the [JsonIntegerElement]
     * @since 0.1.0
     * @version 0.1.0
     */
    override val value: Long

) : JsonPrimitive {

    /**
     * Override toString to generate via [JSON.stringify]
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun toString(): String = JSON.stringify(value)

    override fun isNull(): Boolean = false
    override fun isBoolean(): Boolean = false
    override fun isString(): Boolean = false
    override fun isDouble(): Boolean = true
    override fun isInt(): Boolean = true
    override fun isJsonPrimitive(): Boolean = true
    override fun isJsonArray(): Boolean = false
    override fun isJsonObject(): Boolean = false
    override fun isJsonDouble(): Boolean = true
    override fun isJsonString(): Boolean = false
    override fun isJsonInteger(): Boolean = true
    override fun isJsonNull(): Boolean = false
    override fun isJsonBoolean(): Boolean = false

    /**
     * Convert this [JsonIntegerElement] to a [JsonDoubleElement].
     * @since 0.3.0
     * @version 0.3.0
     */
    override fun toDouble(): JsonDoubleElement {
        return JsonDoubleElement(value.toDouble())
    }

    /**
     * Convert this [JsonIntegerElement] to a [JsonDoubleElement].
     * @since 0.3.0
     * @version 0.3.0
     */
    override fun toJsonDouble(): JsonDoubleElement {
        return JsonDoubleElement(value.toDouble())
    }

    /**
     * Convert this [JsonIntegerElement] to a [JsonIntegerElement].
     * @since 0.3.0
     * @version 0.3.0
     */
    override fun toInt(): JsonIntegerElement {
        return this
    }
}
