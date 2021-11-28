package io.github.shakelang.shake.parser.node

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.shason.JSON

abstract class Node protected constructor(val map: PositionMap) {

    val json: Map<String, *>
        get() = toJson()

    abstract fun toJson(): Map<String, *>
    override fun toString(): String = JSON.stringify(this.json)

}