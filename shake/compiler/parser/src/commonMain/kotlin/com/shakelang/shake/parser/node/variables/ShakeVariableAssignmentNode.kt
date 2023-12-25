package com.shakelang.shake.parser.node.variables

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.ShakeValuedStatementNodeImpl
import com.shakelang.util.parseutils.characters.position.PositionMap

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

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeCommonVariableAssignmentNode) return false
        if (other::class != this::class) return false
        if (variable != other.variable) return false
        if (value != other.value) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeCommonVariableAssignmentNode) return false
        if (other::class != this::class) return false
        if (variable != other.variable) return false
        if (value != other.value) return false
        if (map != other.map) return false
        return true
    }
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
