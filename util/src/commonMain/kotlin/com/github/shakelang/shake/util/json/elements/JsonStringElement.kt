package com.github.shakelang.shake.util.json.elements

import com.github.shakelang.shake.util.json.JSON

class JsonStringElement (

    override val value: String

) : JsonPrimitive {

    override fun toString(): String = JSON.stringify(this)

}