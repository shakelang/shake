package com.shakelang.shake.util.shason.elements

import com.shakelang.shake.util.shason.JSON

/**
 * A json representation of double values
 */
class JsonDoubleElement(

    /**
     * The value of the [JsonDoubleElement]
     */
    override val value: Double

) : JsonPrimitive {

    /**
     * Override toString to generate via [JSON.stringify]
     */
    override fun toString(): String = com.shakelang.shake.util.shason.JSON.stringify(value)
}
