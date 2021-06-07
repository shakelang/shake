package com.github.shakelang.shake.parser.node.logical

import com.github.shakelang.shake.parser.node.ValuedNode
import com.github.shakelang.shake.parser.node.logical.LogicalConcatenationNode
import com.github.shakelang.shake.parser.node.logical.LogicalCompareNode
import com.github.shakelang.shake.parser.node.logical.LogicalNode
import com.github.shakelang.shake.util.characterinput.position.PositionMap

abstract class LogicalNode(map: PositionMap) : ValuedNode(map) {
    abstract val operator: String?
}