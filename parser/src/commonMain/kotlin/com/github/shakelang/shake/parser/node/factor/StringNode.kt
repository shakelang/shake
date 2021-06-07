package com.github.shakelang.shake.parser.node.factor

import com.github.shakelang.shake.parser.node.ValuedNode
import com.github.shakelang.shake.util.characterinput.position.PositionMap

class StringNode(map: PositionMap, val value: String) : ValuedNode(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "StringNode", "number" to value)
}