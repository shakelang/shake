package com.github.shakelang.shake.parser.node

import com.github.shakelang.shake.util.characterinput.position.PositionMap
import com.github.shakelang.shake.util.json.JSON

abstract class Node protected constructor(val map: PositionMap) {

    val json: Map<String, *>
        get() = toJson()

    abstract fun toJson(): Map<String, *>
    override fun toString(): String = JSON.stringify(this.json)

}