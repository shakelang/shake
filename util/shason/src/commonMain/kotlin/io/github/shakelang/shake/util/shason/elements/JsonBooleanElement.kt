package io.github.shakelang.shake.util.shason.elements

import io.github.shakelang.shake.util.shason.JSON

/**
 * A json representation of a boolean value
 */
enum class JsonBooleanElement(

    /**
     * The value if the [JsonBooleanElement]
     */
    override val value: Boolean

) : JsonPrimitive {

    /**
     * Boolean true value
     */
    TRUE(true),

    /**
     * Boolean false value
     */
    FALSE(false);

    /**
     * Override toString to generate via [JSON.stringify]
     */
    override fun toString(): String = io.github.shakelang.shake.util.shason.JSON.stringify(value)

    companion object {

        /**
         * Creates a [JsonBooleanElement] from a boolean
         *
         */
        fun from(b: Boolean) = if (b) TRUE else FALSE

    }

}