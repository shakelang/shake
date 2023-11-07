package io.github.shakelang.shake.parser.node.variables

import io.github.shakelang.shake.parser.node.ShakeValuedNode
import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap

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