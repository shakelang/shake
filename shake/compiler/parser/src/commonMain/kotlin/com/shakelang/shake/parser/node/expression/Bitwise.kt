package com.shakelang.shake.parser.node.expression

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.util.parseutils.characters.position.PositionMap

class ShakeBitwiseAndNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String get() = "&"
}

class ShakeBitwiseOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = "|"
}

class ShakeBitwiseXOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = "^"
}

class ShakeBitwiseNAndNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = "~&"
}

class ShakeBitwiseNOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = "~|"
}

class ShakeBitwiseXNOrNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = "~^"
}

class ShakeBitwiseNotNode(map: PositionMap, node: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeUnaryNode(map, node, operatorToken) {
    override val operator: String
        get() = "~"
}

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
