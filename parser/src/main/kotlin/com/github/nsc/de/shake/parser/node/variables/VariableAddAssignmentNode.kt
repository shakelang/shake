package com.github.nsc.de.shake.parser.node.variables

import com.github.nsc.de.shake.parser.node.*
import com.github.nsc.de.shake.util.characterinput.position.PositionMap

class VariableAddAssignmentNode(
    map: PositionMap,
    val variable: ValuedNode,
    val value: Node,
    val operatorPosition: Int
) : ValuedNode(map) {
    override fun toString(): String = "{$value+=$value}"
}