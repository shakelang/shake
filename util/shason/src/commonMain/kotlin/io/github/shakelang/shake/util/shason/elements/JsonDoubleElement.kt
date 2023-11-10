package io.github.shakelang.shake.util.shason.elements

import io.github.shakelang.shake.util.shason.JSON

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
    override fun toString(): String = io.github.shakelang.shake.util.shason.JSON.stringify(value)
}
