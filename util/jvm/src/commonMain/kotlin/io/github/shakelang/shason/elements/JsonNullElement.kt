package io.github.shakelang.shason.elements

import io.github.shakelang.shason.JSON

/**
 * A json representation of a null value
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun toString(): String = JSON.stringify(value)


}