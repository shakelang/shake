package com.shakelang.shake.parser.node.expression

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.ShakeValuedNodeImpl
import com.shakelang.util.parseutils.characters.position.PositionMap

abstract class ShakeExpressionNode(
    map: PositionMap,
    val left: ShakeValuedNode,
    val right: ShakeValuedNode,
    val operatorPosition: Int,
) : ShakeValuedNodeImpl(map) {
    abstract val operator: String
    override fun toJson(): Map<String, *> = mapOf(
        "name" to nodeName,
        "left" to left.json,
        "right" to right.json,
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeExpressionNode) return false

        if (other::class != this::class) return false
        if (left != other.left) return false
        if (right != other.right) return false
        if (operatorPosition != other.operatorPosition) return false
        if (operator != other.operator) return false

        return true
    }

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeExpressionNode) return false

        if (other::class != this::class) return false
        if (!left.equalsIgnorePosition(other.left)) return false
        if (!right.equalsIgnorePosition(other.right)) return false
        if (operator != other.operator) return false

        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        result = 31 * result + operatorPosition
        result = 31 * result + operator.hashCode()
        return result
    }
}

abstract class ShakeUnaryNode(
    map: PositionMap,
    val value: ShakeValuedNode,
    val operatorPosition: Int,
) : ShakeValuedNodeImpl(map) {
    abstract val operator: String
    override fun toJson(): Map<String, *> = mapOf(
        "name" to nodeName,
        "value" to value.json,
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeUnaryNode) return false

        if (other::class != this::class) return false
        if (value != other.value) return false
        if (operatorPosition != other.operatorPosition) return false
        if (operator != other.operator) return false

        return true
    }

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeUnaryNode) return false

        if (other::class != this::class) return false
        if (!value.equalsIgnorePosition(other.value)) return false
        if (operator != other.operator) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + operatorPosition
        result = 31 * result + operator.hashCode()
        return result
    }
}
