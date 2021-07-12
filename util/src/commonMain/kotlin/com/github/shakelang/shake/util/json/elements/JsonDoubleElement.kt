package com.github.shakelang.shake.util.json.elements

import com.github.shakelang.shake.util.json.JSON

class JsonDoubleElement (

    override val value: Double

) : JsonPrimitive {

    override fun toString(): String = JSON.stringify(value)

}