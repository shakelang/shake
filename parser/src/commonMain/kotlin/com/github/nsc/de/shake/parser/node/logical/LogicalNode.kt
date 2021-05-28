package com.github.nsc.de.shake.parser.node.logical

import com.github.nsc.de.shake.parser.node.ValuedNode
import com.github.nsc.de.shake.parser.node.logical.LogicalConcatenationNode
import com.github.nsc.de.shake.parser.node.logical.LogicalCompareNode
import com.github.nsc.de.shake.parser.node.logical.LogicalNode
import com.github.nsc.de.shake.util.characterinput.position.PositionMap

abstract class LogicalNode(map: PositionMap) : ValuedNode(map) {
    abstract val operator: String?
}