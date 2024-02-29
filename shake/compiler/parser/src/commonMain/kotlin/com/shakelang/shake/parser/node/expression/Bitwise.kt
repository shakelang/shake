package com.shakelang.shake.parser.node.expression

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.util.parseutils.characters.position.PositionMap

/**
 * Node for a bitwise and expression
 * @param map The position of the node in the code
 * @param left The left node
 * @param right The right node
 * @param operatorToken The operator token
 */
class ShakeBitwiseAndNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String get() = "&"
}

/**
 * Node for a bitwise or expression
 * @param map The position of the node in the code
 * @param left The left node
 * @param right The right node
 * @param operatorToken The operator token
 */
class ShakeBitwiseOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = "|"
}

/**
 * Node for a bitwise xor expression
 * @param map The position of the node in the code
 * @param left The left node
 * @param right The right node
 * @param operatorToken The operator token
 */
class ShakeBitwiseXOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = "^"
}

/**
 * Node for a bitwise nand expression
 * @param map The position of the node in the code
 * @param left The left node
 * @param right The right node
 * @param operatorToken The operator token
 */
class ShakeBitwiseNAndNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = "~&"
}

/**
 * Node for a bitwise nor expression
 * @param map The position of the node in the code
 * @param left The left node
 * @param right The right node
 * @param operatorToken The operator token
 */
class ShakeBitwiseNOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = "~|"
}

/**
 * Node for a bitwise xnor expression
 * @param map The position of the node in the code
 * @param left The left node
 * @param right The right node
 * @param operatorToken The operator token
 */
class ShakeBitwiseXNOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = "~^"
}

/**
 * Node for a bitwise not expression
 * @param map The position of the node in the code
 * @param node The node to negate
 * @param operatorToken The operator token
 */
class ShakeBitwiseNotNode(map: PositionMap, node: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeUnaryNode(map, node, operatorToken) {
    override val operator: String
        get() = "~"
}

/**
 * Node for a bitwise shift left expression
 * @param map The position of the node in the code
 * @param left The left node
 * @param right The right node
 * @param operatorToken The operator token
 */
class ShakeBitwiseShiftLeftNode(
    map: PositionMap,
    left: ShakeValuedNode,
    right: ShakeValuedNode,
    operatorToken: ShakeToken,
) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = "<<"
}

/**
 * Node for a bitwise shift right expression
 * @param map The position of the node in the code
 * @param left The left node
 * @param right The right node
 * @param operatorToken The operator token
 */
class ShakeBitwiseShiftRightNode(
    map: PositionMap,
    left: ShakeValuedNode,
    right: ShakeValuedNode,
    operatorToken: ShakeToken,
) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = ">>"
}
