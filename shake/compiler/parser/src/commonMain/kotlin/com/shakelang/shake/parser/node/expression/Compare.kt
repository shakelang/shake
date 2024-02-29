package com.shakelang.shake.parser.node.expression

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.util.parseutils.characters.position.PositionMap

/**
 * Node for a comparison expression
 * @param map The position of the node in the code
 * @param left The left node
 * @param right The right node
 * @param operatorToken The operator token
 */
class ShakeEqualNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = "=="

    override fun toJson(): Map<String, *> =
        mapOf("name" to "ShakeEqualNode", "left" to left.json, "right" to right.json)
}

/**
 * Node for a comparison expression
 * @param map The position of the node in the code
 * @param left The left node
 * @param right The right node
 * @param operatorToken The operator token
 */
class ShakeNotEqualNode(
    map: PositionMap,
    left: ShakeValuedNode,
    right: ShakeValuedNode,
    operatorToken: ShakeToken,
) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = "!="

    override fun toJson(): Map<String, *> =
        mapOf("name" to "ShakeNotEqualNode", "left" to left.json, "right" to right.json)
}

/**
 * Node for a comparison expression
 * @param map The position of the node in the code
 * @param left The left node
 * @param right The right node
 * @param operatorToken The operator token
 */
class ShakeGreaterThanOrEqualNode(
    map: PositionMap,
    left: ShakeValuedNode,
    right: ShakeValuedNode,
    operatorToken: ShakeToken,
) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = ">="
}

/**
 * Node for a comparison expression
 * @param map The position of the node in the code
 * @param left The left node
 * @param right The right node
 * @param operatorToken The operator token
 */
class ShakeGreaterThanNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = ">"
}

/**
 * Node for a comparison expression
 * @param map The position of the node in the code
 * @param left The left node
 * @param right The right node
 * @param operatorToken The operator token
 */
class ShakeLessThanOrEqualNode(
    map: PositionMap,
    left: ShakeValuedNode,
    right: ShakeValuedNode,
    operatorToken: ShakeToken,
) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = "<="
}

/**
 * Node for a comparison expression
 * @param map The position of the node in the code
 * @param left The left node
 * @param right The right node
 * @param operatorToken The operator token
 */
class ShakeLessThanNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = "<"
}
