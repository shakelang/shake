package io.github.shakelang.shake.parser.node.variables

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.shake.parser.node.ShakeValuedStatementNodeImpl

class ShakeVariableAssignmentNode(map: PositionMap, val variable: ShakeValuedNode, val value: ShakeValuedNode, val operatorPosition: Int) :
    ShakeValuedStatementNodeImpl(map) {

    override fun toJson(): Map<String, *> = mapOf(
        "name" to "VariableAssignmentNode",
        "variable" to variable.json,
        "value" to value.json
    )
}