package io.github.shakelang.shake.parser.node.variables

import io.github.shakelang.shake.parser.node.*
import io.github.shakelang.parseutils.characters.position.PositionMap

class ShakeVariableDivAssignmentNode(
    map: PositionMap,
    val variable: ShakeValuedNode,
    val value: ShakeValuedNode,
    val operatorPosition: Int
) : ShakeValuedStatementNodeImpl(map) {

    override fun toJson(): Map<String, *> = mapOf(
        "name" to "VariableDivAssignmentNode",
        "variable" to variable.json,
        "value" to value.json
    )

}