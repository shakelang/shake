package com.shakelang.shake.util.shason.elements

import com.shakelang.shake.util.shason.JSON

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
    override fun toString(): String = com.shakelang.shake.util.shason.JSON.stringify(value)
}
