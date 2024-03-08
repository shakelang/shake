package com.shakelang.shake.parser.node.values.expression

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.util.parseutils.characters.position.PositionMap

/**
 * Node for a calculation expression
 * @param map The position of the node in the code
 * @param left The left node
 * @param right The right node
 * @param operatorToken The operator token
 */
class ShakeAddNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {

    override val operator: String
        get() = "+"
}

/**
 * Node for a calculation expression
 * @param map The position of the node in the code
 * @param left The left node
 * @param right The right node
 * @param operatorToken The operator token
 */
class ShakeSubNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {

    override val operator: String
        get() = "-"
}

/**
 * Node for a calculation expression
 * @param map The position of the node in the code
 * @param left The left node
 * @param right The right node
 * @param operatorToken The operator token
 */
class ShakeMulNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = "*"
}

/**
 * Node for a calculation expression
 * @param map The position of the node in the code
 * @param left The left node
 * @param right The right node
 * @param operatorToken The operator token
 */
class ShakeDivNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = "/"
}

/**
 * Node for a calculation expression
 * @param map The position of the node in the code
 * @param left The left node
 * @param right The right node
 * @param operatorToken The operator token
 */
class ShakeModNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = "%"
}

/**
 * Node for a calculation expression
 * @param map The position of the node in the code
 * @param left The left node
 * @param right The right node
 * @param operatorToken The operator token
 */
class ShakePowNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = "^"
}

/**
 * Node for a calculation expression
 * @param map The position of the node in the code
 * @param contents The node
 * @param operatorToken The operator token
 */
class ShakeUnaryPlusNode(
    map: PositionMap,
    contents: ShakeValuedNode,
    operatorToken: ShakeToken,
) : ShakeUnaryNode(map, contents, operatorToken) {
    override val operator: String get() = "+"
}

/**
 * Node for a calculation expression
 * @param map The position of the node in the code
 * @param contents The node
 * @param operatorToken The operator token
 */
class ShakeUnaryMinusNode(
    map: PositionMap,
    contents: ShakeValuedNode,
    operatorToken: ShakeToken,
) : ShakeUnaryNode(map, contents, operatorToken) {
    override val operator: String get() = "-"
}
