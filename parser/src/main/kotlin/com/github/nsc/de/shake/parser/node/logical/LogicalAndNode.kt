package com.github.nsc.de.shake.parser.node.logical

import com.github.nsc.de.shake.parser.node.ValuedNode
import com.github.nsc.de.shake.util.characterinput.position.PositionMap

class LogicalAndNode(map: PositionMap, left: ValuedNode, right: ValuedNode) :
    LogicalConcatenationNode(map, left, right) {
    override val operator: String
        get() = "&&"
}