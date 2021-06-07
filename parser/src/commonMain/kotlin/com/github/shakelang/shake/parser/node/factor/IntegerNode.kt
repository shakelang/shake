package com.github.shakelang.shake.parser.node.factor

import com.github.shakelang.shake.parser.node.ValuedNode
import com.github.shakelang.shake.util.characterinput.position.PositionMap

class IntegerNode(map: PositionMap, val number: Int) : ValuedNode(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "IntegerNode", "value" to number)
}