package io.github.shakelang.shake.parser.node.logical

import io.github.shakelang.shake.parser.node.ValuedNode
import io.github.shakelang.shake.parser.node.logical.LogicalConcatenationNode
import io.github.shakelang.shake.parser.node.logical.LogicalCompareNode
import io.github.shakelang.shake.parser.node.logical.LogicalNode
import io.github.shakelang.parseutils.characters.position.PositionMap

abstract class LogicalNode(map: PositionMap) : ValuedNode(map) {
    abstract val operator: String?
}