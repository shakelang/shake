package io.github.shakelang.shason.elements

import io.github.shakelang.shason.JSON

/**
 * A json representation of double values
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
class JsonDoubleElement(

    /**
     * The value of the [JsonDoubleElement]
     */
    override val value: Double

) : JsonPrimitive {

    /**
     * Override toString to generate via [JSON.stringify]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun toString(): String = JSON.stringify(value)

}