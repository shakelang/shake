package com.shakelang.shake.parser.node.variables

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.ShakeValuedStatementNodeImpl
import com.shakelang.shake.util.parseutils.characters.position.PositionMap

abstract class ShakeCommonVariableAssignmentNode(
    map: PositionMap,
    val variable: ShakeValuedNode,
    val value: ShakeValuedNode,
    val operatorPosition: Int
) : ShakeValuedStatementNodeImpl(map) {
    abstract val name: String

    override fun toJson(): Map<String, *> = mapOf(
        "name" to name,
        "variable" to variable.json,
        "value" to value.json
    )
}

class ShakeVariableAssignmentNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    value: ShakeValuedNode,
    operatorPosition: Int
) : ShakeCommonVariableAssignmentNode(map, variable, value, operatorPosition) {
    override val name: String = "VariableAssignmentNode"
}

class ShakeVariableAddAssignmentNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    value: ShakeValuedNode,
    operatorPosition: Int
) : ShakeCommonVariableAssignmentNode(map, variable, value, operatorPosition) {
    override val name: String = "VariableAddAssignmentNode"
}

class ShakeVariableSubAssignmentNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    value: ShakeValuedNode,
    operatorPosition: Int
) : ShakeCommonVariableAssignmentNode(map, variable, value, operatorPosition) {
    override val name: String = "VariableSubAssignmentNode"
}

class ShakeVariableMulAssignmentNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    value: ShakeValuedNode,
    operatorPosition: Int
) : ShakeCommonVariableAssignmentNode(map, variable, value, operatorPosition) {
    override val name: String = "VariableMulAssignmentNode"
}

class ShakeVariableDivAssignmentNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    value: ShakeValuedNode,
    operatorPosition: Int
) : ShakeCommonVariableAssignmentNode(map, variable, value, operatorPosition) {
    override val name: String = "VariableDivAssignmentNode"
}

class ShakeVariableModAssignmentNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    value: ShakeValuedNode,
    operatorPosition: Int
) : ShakeCommonVariableAssignmentNode(map, variable, value, operatorPosition) {
    override val name: String = "VariableModAssignmentNode"
}

class ShakeVariablePowAssignmentNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    value: ShakeValuedNode,
    operatorPosition: Int
) : ShakeCommonVariableAssignmentNode(map, variable, value, operatorPosition) {
    override val name: String = "VariablePowAssignmentNode"
}
