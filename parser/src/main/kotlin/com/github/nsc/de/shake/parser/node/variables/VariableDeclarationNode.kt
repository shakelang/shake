package com.github.nsc.de.shake.parser.node.variables

import com.github.nsc.de.shake.parser.node.ValuedNode
import com.github.nsc.de.shake.parser.node.VariableType
import com.github.nsc.de.shake.parser.node.AccessDescriber
import com.github.nsc.de.shake.util.characterinput.position.PositionMap

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

    override fun toString(): String = if (assignment != null) "$type $assignment" else "$type $name"
}