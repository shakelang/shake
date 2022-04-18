package io.github.shakelang.shake.generation

import io.github.shakelang.shake.parser.node.*
import io.github.shakelang.shake.parser.node.factor.ShakeIntegerNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableAddAssignmentNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableSubAssignmentNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableMulAssignmentNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDivAssignmentNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableModAssignmentNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariablePowAssignmentNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableIncreaseNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDecreaseNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableAssignmentNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableUsageNode
import io.github.shakelang.shake.parser.node.logical.ShakeLogicalEqEqualsNode
import io.github.shakelang.shake.parser.node.logical.ShakeLogicalBiggerEqualsNode
import io.github.shakelang.shake.parser.node.logical.ShakeLogicalSmallerEqualsNode
import io.github.shakelang.shake.parser.node.logical.ShakeLogicalBiggerNode
import io.github.shakelang.shake.parser.node.logical.ShakeLogicalSmallerNode
import io.github.shakelang.shake.parser.node.logical.ShakeLogicalAndNode
import io.github.shakelang.shake.parser.node.logical.ShakeLogicalOrNode
import io.github.shakelang.shake.parser.node.logical.ShakeLogicalXOrNode
import io.github.shakelang.shake.parser.node.loops.ShakeWhileNode
import io.github.shakelang.shake.parser.node.loops.ShakeDoWhileNode
import io.github.shakelang.shake.parser.node.loops.ShakeForNode
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassConstructionNode
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionCallNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import io.github.shakelang.shake.parser.node.logical.ShakeLogicalTrueNode
import io.github.shakelang.shake.parser.node.logical.ShakeLogicalFalseNode
import io.github.shakelang.shake.parser.node.expression.*
import io.github.shakelang.shake.parser.node.factor.ShakeDoubleNode

abstract class ShakeGenerator<T> : ShakeGeneratorBase {
    fun visit(n: ShakeNodeImpl): T {
        if (n is ShakeTree) return visitTree(n)
        if (n is ShakeDoubleNode) return visitDoubleNode(n)
        if (n is ShakeIntegerNode) return visitIntegerNode(n)
        if (n is ShakeAddNode) return visitAddNode(n)
        if (n is ShakeSubNode) return visitSubNode(n)
        if (n is ShakeMulNode) return visitMulNode(n)
        if (n is ShakeDivNode) return visitDivNode(n)
        if (n is ShakeModNode) return visitModNode(n)
        if (n is ShakePowNode) return visitPowNode(n)
        if (n is ShakeVariableDeclarationNode) return visitVariableDeclarationNode(n)
        if (n is ShakeVariableAddAssignmentNode) return visitVariableAddAssignmentNode(n)
        if (n is ShakeVariableSubAssignmentNode) return visitVariableSubAssignmentNode(n)
        if (n is ShakeVariableMulAssignmentNode) return visitVariableMulAssignmentNode(n)
        if (n is ShakeVariableDivAssignmentNode) return visitVariableDivAssignmentNode(n)
        if (n is ShakeVariableModAssignmentNode) return visitVariableModAssignmentNode(n)
        if (n is ShakeVariablePowAssignmentNode) return visitVariablePowAssignmentNode(n)
        if (n is ShakeVariableIncreaseNode) return visitVariableIncreaseNode(n)
        if (n is ShakeVariableDecreaseNode) return visitVariableDecreaseNode(n)
        if (n is ShakeVariableAssignmentNode) return visitVariableAssignmentNode(n)
        if (n is ShakeVariableUsageNode) return visitVariableUsageNode(n)
        if (n is ShakeLogicalEqEqualsNode) return visitEqEqualsNode(n)
        if (n is ShakeLogicalBiggerEqualsNode) return visitBiggerEqualsNode(n)
        if (n is ShakeLogicalSmallerEqualsNode) return visitSmallerEqualsNode(n)
        if (n is ShakeLogicalBiggerNode) return visitBiggerNode(n)
        if (n is ShakeLogicalSmallerNode) return visitSmallerNode(n)
        if (n is ShakeLogicalAndNode) return visitLogicalAndNode(n)
        if (n is ShakeLogicalOrNode) return visitLogicalOrNode(n)
        if (n is ShakeLogicalXOrNode) return visitLogicalXOrNode(n)
        if (n is ShakeWhileNode) return visitWhileNode(n)
        if (n is ShakeDoWhileNode) return visitDoWhileNode(n)
        if (n is ShakeForNode) return visitForNode(n)
        if (n is ShakeIfNode) return visitIfNode(n)
        if (n is ShakeFunctionDeclarationNode) return visitFunctionDeclarationNode(n)
        if (n is ShakeClassConstructionNode) return visitClassConstruction(n)
        if (n is ShakeFunctionCallNode) return visitFunctionCallNode(n)
        if (n is ShakeIdentifierNode) return visitIdentifierNode(n)
        if (n is ShakeClassDeclarationNode) return visitClassDeclarationNode(n)
        if (n is ShakeLogicalTrueNode) return visitLogicalTrueNode(n)
        if (n is ShakeLogicalFalseNode) return visitLogicalFalseNode(n)
        if (n is ShakeImportNode) return visitImportNode(n)
        if (n is ShakeCastNode) return visitCastNode(n)
        throw Error("It looks like that node is not implemented in the Interpreter: $n")
    }

    abstract fun visitTree(t: ShakeTree): T
    abstract fun visitDoubleNode(n: ShakeDoubleNode): T
    abstract fun visitIntegerNode(n: ShakeIntegerNode): T
    abstract fun visitAddNode(n: ShakeAddNode): T
    abstract fun visitSubNode(n: ShakeSubNode): T
    abstract fun visitMulNode(n: ShakeMulNode): T
    abstract fun visitDivNode(n: ShakeDivNode): T
    abstract fun visitModNode(n: ShakeModNode): T
    abstract fun visitPowNode(n: ShakePowNode): T
    abstract fun visitVariableDeclarationNode(n: ShakeVariableDeclarationNode): T
    abstract fun visitVariableAssignmentNode(n: ShakeVariableAssignmentNode): T
    abstract fun visitVariableAddAssignmentNode(n: ShakeVariableAddAssignmentNode): T
    abstract fun visitVariableSubAssignmentNode(n: ShakeVariableSubAssignmentNode): T
    abstract fun visitVariableMulAssignmentNode(n: ShakeVariableMulAssignmentNode): T
    abstract fun visitVariableDivAssignmentNode(n: ShakeVariableDivAssignmentNode): T
    abstract fun visitVariableModAssignmentNode(n: ShakeVariableModAssignmentNode): T
    abstract fun visitVariablePowAssignmentNode(n: ShakeVariablePowAssignmentNode): T
    abstract fun visitVariableIncreaseNode(n: ShakeVariableIncreaseNode): T
    abstract fun visitVariableDecreaseNode(n: ShakeVariableDecreaseNode): T
    abstract fun visitVariableUsageNode(n: ShakeVariableUsageNode): T
    abstract fun visitEqEqualsNode(n: ShakeLogicalEqEqualsNode): T
    abstract fun visitBiggerEqualsNode(n: ShakeLogicalBiggerEqualsNode): T
    abstract fun visitSmallerEqualsNode(n: ShakeLogicalSmallerEqualsNode): T
    abstract fun visitBiggerNode(n: ShakeLogicalBiggerNode): T
    abstract fun visitSmallerNode(n: ShakeLogicalSmallerNode): T
    abstract fun visitLogicalAndNode(n: ShakeLogicalAndNode): T
    abstract fun visitLogicalOrNode(n: ShakeLogicalOrNode): T
    abstract fun visitLogicalXOrNode(n: ShakeLogicalXOrNode): T
    abstract fun visitWhileNode(n: ShakeWhileNode): T
    abstract fun visitDoWhileNode(n: ShakeDoWhileNode): T
    abstract fun visitForNode(n: ShakeForNode): T
    abstract fun visitIfNode(n: ShakeIfNode): T
    abstract fun visitFunctionDeclarationNode(n: ShakeFunctionDeclarationNode): T
    abstract fun visitClassDeclarationNode(n: ShakeClassDeclarationNode): T
    abstract fun visitClassConstruction(n: ShakeClassConstructionNode): T
    abstract fun visitFunctionCallNode(n: ShakeFunctionCallNode): T
    abstract fun visitIdentifierNode(n: ShakeIdentifierNode): T
    abstract fun visitLogicalTrueNode(n: ShakeLogicalTrueNode): T
    abstract fun visitLogicalFalseNode(n: ShakeLogicalFalseNode): T
    abstract fun visitImportNode(n: ShakeImportNode): T
    abstract fun visitCastNode(n: ShakeCastNode): T
}