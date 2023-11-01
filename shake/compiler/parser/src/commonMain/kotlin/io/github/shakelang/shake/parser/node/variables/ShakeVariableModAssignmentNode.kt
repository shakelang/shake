package io.github.shakelang.shake.parser.node.variables

import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.shake.parser.node.ShakeValuedStatementNodeImpl

class ShakeVariableModAssignmentNode(
    map: PositionMap,
    val variable: ShakeValuedNode,
    val value: ShakeValuedNode,
    val operatorPosition: Int
) : ShakeValuedStatementNodeImpl(map) {

    override fun toJson(): Map<String, *> = mapOf(
        "name" to "VariableModAssignmentNode",
        "variable" to variable.json,
        "value" to value.json
    )

}