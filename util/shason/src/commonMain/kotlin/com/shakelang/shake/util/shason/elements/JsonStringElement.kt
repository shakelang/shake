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
}
