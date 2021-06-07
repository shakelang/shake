package com.github.shakelang.shake.parser.node.expression

import com.github.shakelang.shake.parser.node.ValuedNode
import com.github.shakelang.shake.util.characterinput.position.PositionMap

@Suppress("unused")
abstract class ExpressionNode(
    map: PositionMap,
    val left: ValuedNode,
    val right: ValuedNode,
    val operatorPosition: Int
) : ValuedNode(map) {
    abstract val operator: Char
}