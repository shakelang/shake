package com.github.nsc.de.shake.parser.node.expression

import com.github.nsc.de.shake.parser.node.ValuedNode
import com.github.nsc.de.shake.parser.node.factor.DoubleNode
import com.github.nsc.de.shake.parser.node.factor.IntegerNode
import com.github.nsc.de.shake.util.characterinput.position.PositionMap

@Suppress("unused")
class SubNode(map: PositionMap, left: ValuedNode, right: ValuedNode, operatorPosition: Int) :
    ExpressionNode(map, left, right, operatorPosition) {
    constructor(map: PositionMap, left: Int, right: ValuedNode, operatorPosition: Int) : this(
        map,
        IntegerNode(map, left),
        right,
        operatorPosition
    )

    constructor(map: PositionMap, left: ValuedNode, right: Int, operatorPosition: Int) : this(
        map,
        left,
        IntegerNode(map, right),
        operatorPosition
    )

    constructor(map: PositionMap, left: Int, right: Int, operatorPosition: Int) : this(
        map,
        IntegerNode(map, left),
        IntegerNode(map, right),
        operatorPosition
    )

    constructor(map: PositionMap, left: Double, right: ValuedNode, operatorPosition: Int) : this(
        map,
        DoubleNode(map, left),
        right,
        operatorPosition
    )

    constructor(map: PositionMap, left: ValuedNode, right: Double, operatorPosition: Int) : this(
        map,
        left,
        DoubleNode(map, right),
        operatorPosition
    )

    constructor(map: PositionMap, left: Double, right: Double, operatorPosition: Int) : this(
        map,
        DoubleNode(map, left),
        DoubleNode(map, right),
        operatorPosition
    )

    override val operator: Char
        get() = '-'

    override fun toJson(): Map<String, *> = mapOf("name" to "SubNode", "left" to left, "right" to right)
}