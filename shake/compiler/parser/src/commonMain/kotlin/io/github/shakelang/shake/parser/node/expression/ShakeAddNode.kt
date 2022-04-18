package io.github.shakelang.shake.parser.node.expression

import io.github.shakelang.shake.parser.node.factor.ShakeDoubleNode
import io.github.shakelang.shake.parser.node.factor.ShakeIntegerNode
import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.parser.node.ShakeValuedNode

@Suppress("unused")
class ShakeAddNode(map: PositionMap, left: ShakeValuedNode, right: ShakeValuedNode, operatorPosition: Int) :
    ShakeExpressionNode(map, left, right, operatorPosition) {
    constructor(map: PositionMap, left: Int, right: ShakeValuedNode, operatorPosition: Int) : this(
        map,
        ShakeIntegerNode(map, left),
        right,
        operatorPosition
    )

    constructor(map: PositionMap, left: ShakeValuedNode, right: Int, operatorPosition: Int) : this(
        map,
        left,
        ShakeIntegerNode(map, right),
        operatorPosition
    )

    constructor(map: PositionMap, left: Int, right: Int, operatorPosition: Int) : this(
        map,
        ShakeIntegerNode(map, left),
        ShakeIntegerNode(map, right),
        operatorPosition
    )

    constructor(map: PositionMap, left: Double, right: ShakeValuedNode, operatorPosition: Int) : this(
        map,
        ShakeDoubleNode(map, left),
        right,
        operatorPosition
    )

    constructor(map: PositionMap, left: ShakeValuedNode, right: Double, operatorPosition: Int) : this(
        map,
        left,
        ShakeDoubleNode(map, right),
        operatorPosition
    )

    constructor(map: PositionMap, left: Double, right: Double, operatorPosition: Int) : this(
        map,
        ShakeDoubleNode(map, left),
        ShakeDoubleNode(map, right),
        operatorPosition
    )

    override val operator: Char
        get() = '+'

    override fun toJson(): Map<String, *> = mapOf("name" to "AddNode", "left" to left, "right" to right)
}