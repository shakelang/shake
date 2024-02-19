package com.shakelang.shake.parser.node.expression

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.util.parseutils.characters.position.PositionMap

class ShakeEqualNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = "=="

    override fun toJson(): Map<String, *> =
        mapOf("name" to "ShakeEqualNode", "left" to left.json, "right" to right.json)
}

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

class ShakeGreaterThanNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = ">"
}

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

class ShakeLessThanNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorToken: ShakeToken) :
    ShakeExpressionNode(map, left, right, operatorToken) {
    override val operator: String
        get() = "<"
}
