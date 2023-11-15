package com.shakelang.shake.parser.node.factor

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.ShakeValuedNodeImpl
import com.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeLogicalTrueNode(map: PositionMap) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "ShakeLogicalTrueNode")
}

class ShakeLogicalFalseNode(map: PositionMap) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "ShakeLogicalFalseNode")
}

class ShakeCharacterNode(map: PositionMap, val value: Char) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "ShakeCharacterNode", "value" to "$value")
}

class ShakeDoubleNode(map: PositionMap, val number: Double) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "ShakeDoubleNode", "value" to number)
}

class ShakeIntegerNode(map: PositionMap, val number: Int) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "ShakeIntegerNode", "value" to number)
}

class ShakePriorityNode(map: PositionMap, val value: ShakeValuedNode) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "ShakePriorityNode", "value" to value)
}

class ShakeStringNode(map: PositionMap, val value: String) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "ShakeStringNode", "value" to value)
}

class ShakeNullNode(map: PositionMap) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "ShakeNullNode")
}