package com.github.shakelang.shake.util.json.elements

import com.github.shakelang.shake.util.json.JSON

enum class JsonNullElement : JsonPrimitive {

    INSTANCE;

    override val value: Nothing?
        get() = null

    override fun toString(): String = JSON.stringify(this)


}