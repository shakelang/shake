package com.shakelang.shake.util.shason.elements

import com.shakelang.shake.util.shason.JSON

/**
 * A json representation of integer values
 */
class JsonIntegerElement(

    /**
     * The value of the [JsonIntegerElement]
     */
    override val value: Long

) : JsonPrimitive {

    /**
     * Override toString to generate via [JSON.stringify]
     */
    override fun toString(): String = com.shakelang.shake.util.shason.JSON.stringify(value)
}
