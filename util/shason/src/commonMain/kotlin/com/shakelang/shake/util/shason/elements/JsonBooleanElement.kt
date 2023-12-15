package com.shakelang.shake.util.shason.elements

import com.shakelang.shake.util.shason.JSON

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
    override val value: Boolean

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
    FALSE(false);

    /**
     * Override toString to generate via [JSON.stringify]
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun toString(): String = com.shakelang.shake.util.shason.JSON.stringify(value)

    companion object {

        /**
         * Creates a [JsonBooleanElement] from a boolean
         * @since 0.1.0
         * @version 0.1.0
         */
        fun from(b: Boolean) = if (b) TRUE else FALSE
    }
}
