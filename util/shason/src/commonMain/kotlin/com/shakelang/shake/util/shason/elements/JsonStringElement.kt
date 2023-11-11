package com.shakelang.shake.util.shason.elements

import com.shakelang.shake.util.shason.JSON

/**
 * A json representation of string values
 */
class JsonStringElement(

    /**
     * The value of the [JsonStringElement]
     */
    override val value: String

) : JsonPrimitive {

    /**
     * Override toString to generate via [JSON.stringify]
     */
    override fun toString(): String = com.shakelang.shake.util.shason.JSON.stringify(value)
}
