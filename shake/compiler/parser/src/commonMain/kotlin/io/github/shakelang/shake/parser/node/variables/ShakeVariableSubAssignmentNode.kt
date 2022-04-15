package io.github.shakelang.shake.parser.node.variables

import io.github.shakelang.shake.parser.node.*
import io.github.shakelang.parseutils.characters.position.PositionMap

class ShakeVariableSubAssignmentNode(
    map: PositionMap,
    val variable: ShakeValuedNode,
    val value: ShakeNode,
    val operatorPosition: Int
) : ShakeValuedNode(map) {

    override fun toJson(): Map<String, *> = mapOf(
        "name" to "VariableSubAssignmentNode",
        "variable" to variable.json,
        "value" to value.json
    )

}