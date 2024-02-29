package com.shakelang.shake.parser.node.values.factor

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeValuedNodeImpl
import com.shakelang.util.parseutils.characters.position.PositionMap

/**
 * Base node for a shake literal
 */
abstract class ShakeLiteralNode(
    map: PositionMap,

    /**
     * The token holding the literal's value
     */
    val valueToken: ShakeToken,
) : ShakeValuedNodeImpl(map)

/**
 * Node for a logical true literal
 */
class ShakeTrueLiteralNode(
    map: PositionMap,
    valueToken: ShakeToken,
) : ShakeLiteralNode(map, valueToken) {
    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeTrueLiteralNode) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeTrueLiteralNode) return false
        return true
    }

    override fun hashCode(): Int {
        return 0
    }
}

/**
 * Node for a logical false literal
 */
class ShakeFalseLiteralNode(map: PositionMap, valueToken: ShakeToken) : ShakeLiteralNode(map, valueToken) {
    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeFalseLiteralNode) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeFalseLiteralNode) return false
        return true
    }

    override fun hashCode(): Int {
        return 0
    }
}

/**
 * Node for a character literal
 */
class ShakeCharacterLiteralNode(map: PositionMap, valueToken: ShakeToken) : ShakeLiteralNode(map, valueToken) {

    /**
     * The value of the character literal
     */
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

/**
 * Node for a double literal
 */
class ShakeDoubleLiteralNode(map: PositionMap, valueToken: ShakeToken) : ShakeLiteralNode(map, valueToken) {

    /**
     * The value of the double literal
     */
    val value: Double = valueToken.value?.toDouble() ?: throw Exception("Value of double token is null")

    override fun toJson(): Map<String, *> = mapOf("name" to nodeName, "value" to value)

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeDoubleLiteralNode) return false
        if (value != other.value) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeDoubleLiteralNode) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}

/**
 * Node for an integer literal
 */
class ShakeIntegerLiteralNode(map: PositionMap, valueToken: ShakeToken) : ShakeLiteralNode(map, valueToken) {

    /**
     * The value of the integer literal
     */
    val value: Int = valueToken.value?.toInt() ?: throw Exception("Value of integer token is null")

    override fun toJson(): Map<String, *> = mapOf("name" to nodeName, "value" to value)

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeIntegerLiteralNode) return false
        if (value != other.value) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeIntegerLiteralNode) return false
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int {
        return value
    }
}

/**
 * Node for a string literal
 */
class ShakeStringLiteralNode(map: PositionMap, valueToken: ShakeToken) : ShakeLiteralNode(map, valueToken) {

    /**
     * The value of the string literal
     */
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

/**
 * Node for a `null` literal
 */
class ShakeNullLiteralNode(map: PositionMap, valueToken: ShakeToken) : ShakeLiteralNode(map, valueToken) {
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

/**
 * Node for a `this` literal
 */
class ShakeThisNode(map: PositionMap, valueToken: ShakeToken) : ShakeLiteralNode(map, valueToken) {
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

/**
 * Node for a `super` literal
 */
class ShakeSuperNode(map: PositionMap, valueToken: ShakeToken) : ShakeLiteralNode(map, valueToken) {
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
