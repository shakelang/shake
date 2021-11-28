package io.github.shakelang.shake.parser.node.variables

import io.github.shakelang.shake.parser.node.ValuedNode
import io.github.shakelang.shake.parser.node.VariableType
import io.github.shakelang.shake.parser.node.AccessDescriber
import io.github.shakelang.parseutils.characters.position.PositionMap
import kotlin.jvm.JvmOverloads

class VariableDeclarationNode @JvmOverloads constructor(
    map: PositionMap,
    val name: String,
    val type: VariableType = VariableType.DYNAMIC,
    val assignment: VariableAssignmentNode? = null,
    val access: AccessDescriber = AccessDescriber.PACKAGE,
    val isInClass: Boolean = false,
    val isStatic: Boolean = false,
    val isFinal: Boolean = false
) : ValuedNode(map) {

    constructor(map: PositionMap, name: String, assignment: VariableAssignmentNode?) : this(
        map,
        name,
        VariableType.DYNAMIC,
        assignment
    )

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to "VariableDivAssignmentNode",
            "variable_name" to name,
            "type" to type.toString(),
            "access" to access.toString(),
            "assignment" to this.assignment?.toJson(),
            "is_in_class" to isInClass,
            "is_static" to isStatic,
            "is_final" to isFinal
        )

}