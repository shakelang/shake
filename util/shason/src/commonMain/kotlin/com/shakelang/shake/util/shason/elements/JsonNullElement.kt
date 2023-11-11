package com.shakelang.shake.util.shason.elements

import com.shakelang.shake.util.shason.JSON

/**
 * A json representation of a null value
 */
enum class JsonNullElement : JsonPrimitive {

    /**
     * The instance of the null value
     */
    INSTANCE;

    /**
     * The value of null is null, so as value we just return null.
     */
    override val value: Nothing?
        get() = null

    /**
     * Override toString to generate via [JSON.stringify]
     */
    override fun toString(): String = com.shakelang.shake.util.shason.JSON.stringify(value)
}
