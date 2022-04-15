package io.github.shakelang.shake.parser.node.variables

import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.shake.parser.node.ShakeVariableType
import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.parseutils.characters.position.PositionMap
import kotlin.jvm.JvmOverloads

class ShakeVariableDeclarationNode @JvmOverloads constructor(
    map: PositionMap,
    val name: String,
    val type: ShakeVariableType = ShakeVariableType.DYNAMIC,
    val assignment: ShakeVariableAssignmentNode? = null,
    val access: ShakeAccessDescriber = ShakeAccessDescriber.PACKAGE,
    val isInClass: Boolean = false,
    val isStatic: Boolean = false,
    val isFinal: Boolean = false
) : ShakeValuedNode(map) {

    constructor(map: PositionMap, name: String, assignment: ShakeVariableAssignmentNode?) : this(
        map,
        name,
        ShakeVariableType.DYNAMIC,
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