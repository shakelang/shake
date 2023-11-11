package com.shakelang.shake.parser.node.factor

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.ShakeValuedNodeImpl
import com.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeLogicalTrueNode(map: PositionMap) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalTrueNode")
}

class ShakeLogicalFalseNode(map: PositionMap) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "LogicalFalseNode")
}

class ShakeCharacterNode(map: PositionMap, val value: Char) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "CharacterNode", "value" to "$value")
}

class ShakeDoubleNode(map: PositionMap, val number: Double) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "DoubleNode", "value" to number)
}

class ShakeIntegerNode(map: PositionMap, val number: Int) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "IntegerNode", "value" to number)
}

class ShakePriorityNode(map: PositionMap, val value: ShakeValuedNode) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "PriorityNode", "value" to value)
}

class ShakeStringNode(map: PositionMap, val value: String) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to "StringNode", "number" to value)
}
