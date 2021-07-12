package com.github.shakelang.shake.util.json.elements

import com.github.shakelang.shake.util.json.JSON

class JsonIntegerElement (

    override val value: Int

) : JsonPrimitive {

    override fun toString(): String = JSON.stringify(this)

}