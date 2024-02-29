package com.shakelang.shake.parser.node.variables

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.ShakeValuedStatementNodeImpl
import com.shakelang.util.parseutils.characters.position.PositionMap

abstract class ShakeCommonVariableAssignmentNode(
    map: PositionMap,
    val variable: ShakeValuedNode,
    val value: ShakeValuedNode,
    val operatorToken: ShakeToken,
) : ShakeValuedStatementNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf(
        "name" to nodeName,
        "variable" to variable.json,
        "value" to value.json,
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
    operatorToken: ShakeToken,
) : ShakeCommonVariableAssignmentNode(map, variable, value, operatorToken)

class ShakeVariableAddAssignmentNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    value: ShakeValuedNode,
    operatorToken: ShakeToken,
) : ShakeCommonVariableAssignmentNode(map, variable, value, operatorToken)

class ShakeVariableSubAssignmentNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    value: ShakeValuedNode,
    operatorToken: ShakeToken,
) : ShakeCommonVariableAssignmentNode(map, variable, value, operatorToken)

class ShakeVariableMulAssignmentNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    value: ShakeValuedNode,
    operatorToken: ShakeToken,
) : ShakeCommonVariableAssignmentNode(map, variable, value, operatorToken)

class ShakeVariableDivAssignmentNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    value: ShakeValuedNode,
    operatorToken: ShakeToken,
) : ShakeCommonVariableAssignmentNode(map, variable, value, operatorToken)

class ShakeVariableModAssignmentNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    value: ShakeValuedNode,
    operatorToken: ShakeToken,
) : ShakeCommonVariableAssignmentNode(map, variable, value, operatorToken)

class ShakeVariablePowAssignmentNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    value: ShakeValuedNode,
    operatorToken: ShakeToken,
) : ShakeCommonVariableAssignmentNode(map, variable, value, operatorToken)

abstract class ShakeCommonVariableIncreaseDecreaseNode(
    map: PositionMap,
    val variable: ShakeValuedNode,
    val operatorToken: ShakeToken,
) : ShakeValuedStatementNodeImpl(map) {
    override fun toJson(): Map<String, *> = mapOf("name" to nodeName, "variable" to variable.json)

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeCommonVariableIncreaseDecreaseNode) return false
        if (other::class != this::class) return false
        if (variable != other.variable) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeCommonVariableIncreaseDecreaseNode) return false
        if (other::class != this::class) return false
        if (variable != other.variable) return false
        if (map != other.map) return false
        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + variable.hashCode()
        result = 31 * result + operatorToken.hashCode()
        return result
    }
}

class ShakeVariableIncrementBeforeNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    operatorToken: ShakeToken,
) : ShakeCommonVariableIncreaseDecreaseNode(map, variable, operatorToken)

class ShakeVariableDecrementBeforeNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    operatorToken: ShakeToken,
) : ShakeCommonVariableIncreaseDecreaseNode(map, variable, operatorToken)

class ShakeVariableIncrementAfterNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    operatorToken: ShakeToken,
) : ShakeCommonVariableIncreaseDecreaseNode(map, variable, operatorToken)

class ShakeVariableDecrementAfterNode(
    map: PositionMap,
    variable: ShakeValuedNode,
    operatorToken: ShakeToken,
) : ShakeCommonVariableIncreaseDecreaseNode(map, variable, operatorToken)
