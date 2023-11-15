package com.shakelang.shake.parser.node.expression

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.util.parseutils.characters.position.PositionMap

class ShakeEqualNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "=="

    override fun toJson(): Map<String, *> = mapOf("name" to "ShakeEqualNode", "left" to left.json, "right" to right.json)
}

class ShakeNotEqualNode(
    map: PositionMap,
    left: ShakeValuedNode,
    right: ShakeValuedNode,
    operatorPosition: Int
) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "!="

    override fun toJson(): Map<String, *> =
        mapOf("name" to "ShakeNotEqualNode", "left" to left.json, "right" to right.json)
}

class ShakeGreaterThanOrEqualNode(
    map: PositionMap,
    left: ShakeValuedNode,
    right: ShakeValuedNode,
    operatorPosition: Int
) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = ">="

    override fun toJson(): Map<String, *> =
        mapOf("name" to "ShakeGreaterThanOrEqualNode", "left" to left.json, "right" to right.json)
}

class ShakeGreaterThanNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = ">"

    override fun toJson(): Map<String, *> = mapOf("name" to "ShakeGreaterThanNode", "left" to left.json, "right" to right.json)
}

class ShakeLessThanOrEqualNode(
    map: PositionMap,
    left: ShakeValuedNode,
    right: ShakeValuedNode,
    operatorPosition: Int
) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "<="

    override fun toJson(): Map<String, *> =
        mapOf("name" to "ShakeLessThanOrEqualNode", "left" to left.json, "right" to right.json)
}

class ShakeLessThanNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    override val operator: String
        get() = "<"

    override fun toJson(): Map<String, *> = mapOf("name" to "ShakeLessThanNode", "left" to left.json, "right" to right.json)
}
