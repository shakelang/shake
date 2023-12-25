package com.shakelang.util.shason.elements

import com.shakelang.util.shason.JSON

/**
 * A json representation of a null value
 * @since 0.1.0
 * @version 0.1.0
 */
enum class JsonNullElement : JsonPrimitive {

    /**
     * The instance of the null value
     * @since 0.1.0
     * @version 0.1.0
     */
    INSTANCE;

    /**
     * The value of null is null, so as value we just return null.
     * @since 0.1.0
     * @version 0.1.0
     */
    override val value: Nothing?
        get() = null

    /**
     * Override toString to generate via [JSON.stringify]
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun toString(): String = JSON.stringify(value)

    override fun isNull(): Boolean = true
    override fun isBoolean(): Boolean = false
    override fun isString(): Boolean = false
    override fun isDouble(): Boolean = false
    override fun isInt(): Boolean = false
    override fun isJsonPrimitive(): Boolean = true
    override fun isJsonArray(): Boolean = false
    override fun isJsonObject(): Boolean = false
    override fun isJsonDouble(): Boolean = false
    override fun isJsonString(): Boolean = false
    override fun isJsonInteger(): Boolean = false
    override fun isJsonNull(): Boolean = true
    override fun isJsonBoolean(): Boolean = false
}
