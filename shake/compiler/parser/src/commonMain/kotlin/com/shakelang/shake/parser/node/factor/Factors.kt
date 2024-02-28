package com.shakelang.shake.parser.node.factor

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.ShakeValuedNodeImpl
import com.shakelang.util.parseutils.characters.position.PositionMap

abstract class ShakeLiteralTokenNode(
    map: PositionMap,
    val valueToken: ShakeToken,
) : ShakeValuedNodeImpl(map)

class ShakeLogicalTrueLiteralNode(
    map: PositionMap,
    valueToken: ShakeToken,
) : ShakeLiteralTokenNode(map, valueToken) {
    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeLogicalTrueLiteralNode) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeLogicalTrueLiteralNode) return false
        return true
    }

    override fun hashCode(): Int {
        return 0
    }
}

class ShakeLogicalFalseLiteralNode(map: PositionMap, valueToken: ShakeToken) : ShakeLiteralTokenNode(map, valueToken) {
    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeLogicalFalseLiteralNode) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeLogicalFalseLiteralNode) return false
        return true
    }

    override fun hashCode(): Int {
        return 0
    }
}

class ShakeCharacterLiteralNode(map: PositionMap, valueToken: ShakeToken) : ShakeLiteralTokenNode(map, valueToken) {

    val value: Char = (valueToken.value ?: throw Exception("Value of character token is null")).toCharArray()[0]
    override fun toJson(): Map<String, *> = mapOf("name" to nodeName, "value" to "$value")

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeCharacterLiteralNode) return false
        if (value != other.value) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeCharacterLiteralNode) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}

class ShakeDoubleLiteralNode(map: PositionMap, valueToken: ShakeToken) : ShakeLiteralTokenNode(map, valueToken) {

    val number: Double = valueToken.value?.toDouble() ?: throw Exception("Value of double token is null")

    override fun toJson(): Map<String, *> = mapOf("name" to nodeName, "value" to number)

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeDoubleLiteralNode) return false
        if (number != other.number) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeDoubleLiteralNode) return false
        if (number != other.number) return false
        return true
    }

    override fun hashCode(): Int {
        return number.hashCode()
    }
}

class ShakeIntegerLiteralNode(map: PositionMap, valueToken: ShakeToken) : ShakeLiteralTokenNode(map, valueToken) {

    val number: Int = valueToken.value?.toInt() ?: throw Exception("Value of integer token is null")

    override fun toJson(): Map<String, *> = mapOf("name" to nodeName, "value" to number)

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeIntegerLiteralNode) return false
        if (number != other.number) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeIntegerLiteralNode) return false
        if (number != other.number) return false
        return true
    }

    override fun hashCode(): Int {
        return number
    }
}

class ShakePriorityNode(
    map: PositionMap,
    val value: ShakeValuedNode,
    val lparenToken: ShakeToken,
    val rparenToken: ShakeToken,
) : ShakeValuedNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to nodeName, "value" to value.json)

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakePriorityNode) return false
        if (value != other.value) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakePriorityNode) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}

class ShakeStringLiteralNode(map: PositionMap, valueToken: ShakeToken) : ShakeLiteralTokenNode(map, valueToken) {

    val value: String = valueToken.value ?: throw Exception("Value of string token is null")

    override fun toJson(): Map<String, *> = mapOf("name" to nodeName, "value" to value)

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeStringLiteralNode) return false
        if (value != other.value) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeStringLiteralNode) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}

class ShakeNullLiteralNode(map: PositionMap, valueToken: ShakeToken) : ShakeLiteralTokenNode(map, valueToken) {
    override fun toJson(): Map<String, *> = mapOf("name" to nodeName)

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeNullLiteralNode) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeNullLiteralNode) return false
        return true
    }

    override fun hashCode(): Int {
        return 0
    }
}

class ShakeThisNode(map: PositionMap, valueToken: ShakeToken) : ShakeLiteralTokenNode(map, valueToken) {
    override fun toJson(): Map<String, *> = mapOf("name" to nodeName)

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeThisNode) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeThisNode) return false
        return true
    }

    override fun hashCode(): Int {
        return 0
    }
}

class ShakeSuperNode(map: PositionMap, valueToken: ShakeToken) : ShakeLiteralTokenNode(map, valueToken) {
    override fun toJson(): Map<String, *> = mapOf("name" to nodeName)

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeSuperNode) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeSuperNode) return false
        return true
    }

    override fun hashCode(): Int {
        return 0
    }
}
