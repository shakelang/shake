package com.github.nsc.de.shake.parser.node.logical

import com.github.nsc.de.shake.parser.node.ValuedNode
import com.github.nsc.de.shake.util.characterinput.position.PositionMap

abstract class LogicalCompareNode(map: PositionMap, val left: ValuedNode, val right: ValuedNode) : LogicalNode(map) {
    override fun toString(): String = "LogicalCompareNode{left=$left, right=$right}"
}