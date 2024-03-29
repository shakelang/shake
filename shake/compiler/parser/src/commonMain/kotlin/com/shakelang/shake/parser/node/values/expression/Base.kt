package com.shakelang.shake.parser.node.values.expression

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.ShakeValuedNodeImpl
import com.shakelang.util.parseutils.characters.position.PositionMap

/**
 * A node that represents an expression (e.g. 1 + 2)
 */
abstract class ShakeExpressionNode(
    map: PositionMap,

    /**
     * The left side of the expression
     */
    val left: ShakeValuedNode,

    /**
     * The right side of the expression
     */
    val right: ShakeValuedNode,

    /**
     * The token representing the operator
     */
    val operatorToken: ShakeToken,
) : ShakeValuedNodeImpl(map) {

    /**
     * The operator of the expression
     */
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
        if (operatorToken.start != other.operatorToken.start) return false
        if (operatorToken.end != other.operatorToken.end) return false
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
        result = 31 * result + operatorToken.start
        result = 31 * result + operatorToken.end
        result = 31 * result + operator.hashCode()
        return result
    }
}

/**
 * A node that represents an unary expression (e.g. -1)
 */
abstract class ShakeUnaryNode(
    map: PositionMap,

    /**
     * The value of the expression
     */
    val value: ShakeValuedNode,

    /**
     * The token representing the operator
     */
    val operatorToken: ShakeToken,
) : ShakeValuedNodeImpl(map) {

    /**
     * The operator of the expression
     */
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
        if (operatorToken.start != other.operatorToken.start) return false
        if (operatorToken.end != other.operatorToken.end) return false
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
        result = 31 * result + operatorToken.start
        result = 31 * result + operatorToken.end
        result = 31 * result + operator.hashCode()
        return result
    }
}
