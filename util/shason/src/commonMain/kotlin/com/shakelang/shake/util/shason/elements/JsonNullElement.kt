package com.shakelang.shake.util.shason.elements

import com.shakelang.shake.util.shason.JSON

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
}
