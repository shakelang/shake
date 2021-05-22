package com.github.nsc.de.shake.parser.node.expression

import com.github.nsc.de.shake.parser.node.ValuedNode
import com.github.nsc.de.shake.util.characterinput.position.PositionMap

@Suppress("unused")
abstract class ExpressionNode(
    map: PositionMap,
    val left: ValuedNode,
    val right: ValuedNode,
    val operatorPosition: Int
) : ValuedNode(map) {
    abstract val operator: Char
    override fun toString(): String = "ExpressionNode{left=$left, right=$right, operator=$operator"
}