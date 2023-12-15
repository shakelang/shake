package com.shakelang.shake.util.shason.elements

import com.shakelang.shake.util.shason.JSON

/**
 * A json representation of string values
 * @since 0.1.0
 * @version 0.1.0
 */
class JsonStringElement(

    /**
     * The value of the [JsonStringElement]
     * @since 0.1.0
     * @version 0.1.0
     */
    override val value: String

) : JsonPrimitive {

    /**
     * Override toString to generate via [JSON.stringify]
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun toString(): String = JSON.stringify(value)

    override fun isNull(): Boolean = false
    override fun isBoolean(): Boolean = false
    override fun isString(): Boolean = true
    override fun isDouble(): Boolean = false
    override fun isInt(): Boolean = false
    override fun isJsonPrimitive(): Boolean = true
    override fun isJsonArray(): Boolean = false
    override fun isJsonObject(): Boolean = false
    override fun isJsonDouble(): Boolean = false
    override fun isJsonString(): Boolean = true
    override fun isJsonInteger(): Boolean = false
    override fun isJsonNull(): Boolean = false
    override fun isJsonBoolean(): Boolean = false
}
