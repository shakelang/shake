package com.github.shakelang.shake.util.json.elements

import com.github.shakelang.shake.util.json.JSON

/**
 * A json representation of integer values
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
class JsonIntegerElement (

    /**
     * The value of the [JsonIntegerElement]
     */
    override val value: Long

) : JsonPrimitive {

    /**
     * Override toString to generate via [JSON.stringify]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun toString(): String = JSON.stringify(value)

}