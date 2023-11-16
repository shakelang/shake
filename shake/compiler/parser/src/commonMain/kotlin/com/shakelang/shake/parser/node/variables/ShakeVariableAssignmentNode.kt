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
    override fun toJson(): Map<String, *> = mapOf(
        "name" to nodeName,
        "variable" to variable.json,
        "value" to value.json
    )
}

class ShakeVariableAssignmentNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    value: ShakeValuedNode,
    operatorPosition: Int
) : ShakeCommonVariableAssignmentNode(map, variable, value, operatorPosition)

class ShakeVariableAddAssignmentNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    value: ShakeValuedNode,
    operatorPosition: Int
) : ShakeCommonVariableAssignmentNode(map, variable, value, operatorPosition)

class ShakeVariableSubAssignmentNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    value: ShakeValuedNode,
    operatorPosition: Int
) : ShakeCommonVariableAssignmentNode(map, variable, value, operatorPosition)

class ShakeVariableMulAssignmentNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    value: ShakeValuedNode,
    operatorPosition: Int
) : ShakeCommonVariableAssignmentNode(map, variable, value, operatorPosition)
class ShakeVariableDivAssignmentNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    value: ShakeValuedNode,
    operatorPosition: Int
) : ShakeCommonVariableAssignmentNode(map, variable, value, operatorPosition)

class ShakeVariableModAssignmentNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    value: ShakeValuedNode,
    operatorPosition: Int
) : ShakeCommonVariableAssignmentNode(map, variable, value, operatorPosition)

class ShakeVariablePowAssignmentNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    value: ShakeValuedNode,
    operatorPosition: Int
) : ShakeCommonVariableAssignmentNode(map, variable, value, operatorPosition)
