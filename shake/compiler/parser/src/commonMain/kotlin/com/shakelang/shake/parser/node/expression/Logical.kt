package com.shakelang.shake.parser.node.expression

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.util.parseutils.characters.position.PositionMap

/**
 * Node for a logical and expression
 * @param map The position of the node in the code
 * @param left The left node
 * @param right The right node
 * @param operatorToken The operator token
 */
class ShakeLogicalAndNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String get() = "&&"
}

/**
 * Node for a logical or expression
 * @param map The position of the node in the code
 * @param left The left node
 * @param right The right node
 * @param operatorToken The operator token
 */
class ShakeLogicalOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = "||"
}

/**
 * Node for a logical xor expression
 * @param map The position of the node in the code
 * @param left The left node
 * @param right The right node
 * @param operatorToken The operator token
 */
class ShakeLogicalXOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = "^^"
}

/**
 * Node for a logical not expression
 * @param map The position of the node in the code
 * @param node The node to negate
 * @param operatorToken The operator token
 */
class ShakeLogicalNotNode(map: PositionMap, node: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeUnaryNode(map, node, operatorToken) {
    override val operator: String
        get() = "!"
}
