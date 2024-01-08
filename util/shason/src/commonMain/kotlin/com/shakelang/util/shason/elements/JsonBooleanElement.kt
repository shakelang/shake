package com.shakelang.util.shason.elements

import com.shakelang.util.shason.JSON

/**
 * A json representation of a boolean value
 * @since 0.1.0
 * @version 0.1.0
 */
enum class JsonBooleanElement(

    /**
     * The value if the [JsonBooleanElement]
     * @since 0.1.0
     * @version 0.1.0
     */
    override val value: Boolean,

) : JsonPrimitive {

    /**
     * Boolean true value
     * @since 0.1.0
     * @version 0.1.0
     */
    TRUE(true),

    /**
     * Boolean false value
     * @since 0.1.0
     * @version 0.1.0
     */
    FALSE(false),
    ;

    /**
     * Override toString to generate via [JSON.stringify]
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun toString(): String = JSON.stringify(value)

    override fun isBoolean(): Boolean = true
    override fun isString(): Boolean = false
    override fun isDouble(): Boolean = false
    override fun isInt(): Boolean = false
    override fun isJsonPrimitive(): Boolean = true
    override fun isJsonArray(): Boolean = false
    override fun isJsonObject(): Boolean = false
    override fun isJsonDouble(): Boolean = false
    override fun isJsonString(): Boolean = false
    override fun isJsonInteger(): Boolean = false
    override fun isJsonNull(): Boolean = false
    override fun isJsonBoolean(): Boolean = true

    companion object {

        /**
         * Creates a [JsonBooleanElement] from a boolean
         * @since 0.1.0
         * @version 0.1.0
         */
        fun from(b: Boolean) = if (b) TRUE else FALSE
    }
}
