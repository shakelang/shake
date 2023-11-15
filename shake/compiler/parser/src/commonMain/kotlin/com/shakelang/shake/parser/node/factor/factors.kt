package com.shakelang.shake.parser.node.factor

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.ShakeValuedNodeImpl
import com.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeLogicalTrueNode(map: PositionMap) : ShakeValuedNodeImpl(map)
class ShakeLogicalFalseNode(map: PositionMap) : ShakeValuedNodeImpl(map)

class ShakeCharacterNode(map: PositionMap, val value: Char) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to nodeName, "value" to "$value")
}

class ShakeDoubleNode(map: PositionMap, val number: Double) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to nodeName, "value" to number)
}

class ShakeIntegerNode(map: PositionMap, val number: Int) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to nodeName, "value" to number)
}

class ShakePriorityNode(map: PositionMap, val value: ShakeValuedNode) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to nodeName, "value" to value.json)
}

class ShakeStringNode(map: PositionMap, val value: String) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to nodeName, "value" to value)
}

class ShakeNullNode(map: PositionMap) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to nodeName)
}