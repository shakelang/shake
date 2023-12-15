package com.shakelang.shake.util.shason.elements

import com.shakelang.shake.util.shason.JSON

/**
 * A json representation of double values
 * @since 0.1.0
 * @version 0.1.0
 */
class JsonDoubleElement(

    /**
     * The value of the [JsonDoubleElement]
     * @since 0.1.0
     * @version 0.1.0
     */
    override val value: Double

) : JsonPrimitive {

    /**
     * Override toString to generate via [JSON.stringify]
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun toString(): String = JSON.stringify(value)
}
