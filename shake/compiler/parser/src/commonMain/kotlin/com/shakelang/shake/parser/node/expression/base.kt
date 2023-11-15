package com.shakelang.shake.parser.node.expression

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.ShakeValuedNodeImpl
import com.shakelang.shake.util.parseutils.characters.position.PositionMap

abstract class ShakeExpressionNode(
    map: PositionMap,
    val left: ShakeValuedNode,
    val right: ShakeValuedNode,
    val operatorPosition: Int
) : ShakeValuedNodeImpl(map) {
    abstract val operator: String
    override fun toJson(): Map<String, *> = mapOf(
        "name" to nodeName,
        "left" to left.json,
        "right" to right.json,
    )
}

abstract class ShakeUnaryNode(
    map: PositionMap,
    val value: ShakeValuedNode,
    val operatorPosition: Int
) : ShakeValuedNodeImpl(map) {
    abstract val operator: String
    override fun toJson(): Map<String, *> = mapOf(
        "name" to nodeName,
        "value" to value.json,
    )
}
