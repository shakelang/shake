package com.github.nsc.de.shake.generation

import com.github.nsc.de.shake.parser.node.*
import com.github.nsc.de.shake.parser.node.factor.IntegerNode
import com.github.nsc.de.shake.parser.node.variables.VariableDeclarationNode
import com.github.nsc.de.shake.parser.node.variables.VariableAddAssignmentNode
import com.github.nsc.de.shake.parser.node.variables.VariableSubAssignmentNode
import com.github.nsc.de.shake.parser.node.variables.VariableMulAssignmentNode
import com.github.nsc.de.shake.parser.node.variables.VariableDivAssignmentNode
import com.github.nsc.de.shake.parser.node.variables.VariableModAssignmentNode
import com.github.nsc.de.shake.parser.node.variables.VariablePowAssignmentNode
import com.github.nsc.de.shake.parser.node.variables.VariableIncreaseNode
import com.github.nsc.de.shake.parser.node.variables.VariableDecreaseNode
import com.github.nsc.de.shake.parser.node.variables.VariableAssignmentNode
import com.github.nsc.de.shake.parser.node.variables.VariableUsageNode
import com.github.nsc.de.shake.parser.node.logical.LogicalEqEqualsNode
import com.github.nsc.de.shake.parser.node.logical.LogicalBiggerEqualsNode
import com.github.nsc.de.shake.parser.node.logical.LogicalSmallerEqualsNode
import com.github.nsc.de.shake.parser.node.logical.LogicalBiggerNode
import com.github.nsc.de.shake.parser.node.logical.LogicalSmallerNode
import com.github.nsc.de.shake.parser.node.logical.LogicalAndNode
import com.github.nsc.de.shake.parser.node.logical.LogicalOrNode
import com.github.nsc.de.shake.parser.node.logical.LogicalXOrNode
import com.github.nsc.de.shake.parser.node.loops.WhileNode
import com.github.nsc.de.shake.parser.node.loops.DoWhileNode
import com.github.nsc.de.shake.parser.node.loops.ForNode
import com.github.nsc.de.shake.parser.node.functions.FunctionDeclarationNode
import com.github.nsc.de.shake.parser.node.objects.ClassConstructionNode
import com.github.nsc.de.shake.parser.node.functions.FunctionCallNode
import com.github.nsc.de.shake.parser.node.objects.ClassDeclarationNode
import com.github.nsc.de.shake.parser.node.logical.LogicalTrueNode
import com.github.nsc.de.shake.parser.node.logical.LogicalFalseNode
import com.github.nsc.de.shake.parser.node.expression.*
import com.github.nsc.de.shake.parser.node.factor.DoubleNode


@Suppress("unused")
abstract class ContextShakeGenerator<T, C> : ShakeGeneratorBase {
    fun visit(n: Node, context: C): T {
        if (n is Tree) return visitTree(n, context)
        if (n is DoubleNode) return visitDoubleNode(n, context)
        if (n is IntegerNode) return visitIntegerNode(n, context)
        if (n is AddNode) return visitAddNode(n, context)
        if (n is SubNode) return visitSubNode(n, context)
        if (n is MulNode) return visitMulNode(n, context)
        if (n is DivNode) return visitDivNode(n, context)
        if (n is ModNode) return visitModNode(n, context)
        if (n is PowNode) return visitPowNode(n, context)
        if (n is VariableDeclarationNode) return visitVariableDeclarationNode(n, context)
        if (n is VariableAddAssignmentNode) return visitVariableAddAssignmentNode(n, context)
        if (n is VariableSubAssignmentNode) return visitVariableSubAssignmentNode(n, context)
        if (n is VariableMulAssignmentNode) return visitVariableMulAssignmentNode(n, context)
        if (n is VariableDivAssignmentNode) return visitVariableDivAssignmentNode(n, context)
        if (n is VariableModAssignmentNode) return visitVariableModAssignmentNode(n, context)
        if (n is VariablePowAssignmentNode) return visitVariablePowAssignmentNode(n, context)
        if (n is VariableIncreaseNode) return visitVariableIncreaseNode(n, context)
        if (n is VariableDecreaseNode) return visitVariableDecreaseNode(n, context)
        if (n is VariableAssignmentNode) return visitVariableAssignmentNode(n, context)
        if (n is VariableUsageNode) return visitVariableUsageNode(n, context)
        if (n is LogicalEqEqualsNode) return visitEqEqualsNode(n, context)
        if (n is LogicalBiggerEqualsNode) return visitBiggerEqualsNode(n, context)
        if (n is LogicalSmallerEqualsNode) return visitSmallerEqualsNode(n, context)
        if (n is LogicalBiggerNode) return visitBiggerNode(n, context)
        if (n is LogicalSmallerNode) return visitSmallerNode(n, context)
        if (n is LogicalAndNode) return visitLogicalAndNode(n, context)
        if (n is LogicalOrNode) return visitLogicalOrNode(n, context)
        if (n is LogicalXOrNode) return visitLogicalXOrNode(n, context)
        if (n is WhileNode) return visitWhileNode(n, context)
        if (n is DoWhileNode) return visitDoWhileNode(n, context)
        if (n is ForNode) return visitForNode(n, context)
        if (n is IfNode) return visitIfNode(n, context)
        if (n is FunctionDeclarationNode) return visitFunctionDeclarationNode(n, context)
        if (n is ClassConstructionNode) return visitClassConstruction(n, context)
        if (n is FunctionCallNode) return visitFunctionCallNode(n, context)
        if (n is IdentifierNode) return visitIdentifierNode(n, context)
        if (n is ClassDeclarationNode) return visitClassDeclarationNode(n, context)
        if (n is LogicalTrueNode) return visitLogicalTrueNode(n, context)
        if (n is LogicalFalseNode) return visitLogicalFalseNode(n, context)
        if (n is ImportNode) return visitImportNode(n, context)
        if (n is CastNode) return visitCastNode(n, context)
        throw Error("It looks like that node is not implemented in the generator: $n")
    }

    abstract fun visitTree(t: Tree?, context: C): T
    abstract fun visitDoubleNode(n: DoubleNode?, context: C): T
    abstract fun visitIntegerNode(n: IntegerNode?, context: C): T
    abstract fun visitAddNode(n: AddNode?, context: C): T
    abstract fun visitSubNode(n: SubNode?, context: C): T
    abstract fun visitMulNode(n: MulNode?, context: C): T
    abstract fun visitDivNode(n: DivNode?, context: C): T
    abstract fun visitModNode(n: ModNode?, context: C): T
    abstract fun visitPowNode(n: PowNode?, context: C): T
    abstract fun visitVariableDeclarationNode(n: VariableDeclarationNode?, context: C): T
    abstract fun visitVariableAssignmentNode(n: VariableAssignmentNode?, context: C): T
    abstract fun visitVariableAddAssignmentNode(n: VariableAddAssignmentNode?, context: C): T
    abstract fun visitVariableSubAssignmentNode(n: VariableSubAssignmentNode?, context: C): T
    abstract fun visitVariableMulAssignmentNode(n: VariableMulAssignmentNode?, context: C): T
    abstract fun visitVariableDivAssignmentNode(n: VariableDivAssignmentNode?, context: C): T
    abstract fun visitVariableModAssignmentNode(n: VariableModAssignmentNode?, context: C): T
    abstract fun visitVariablePowAssignmentNode(n: VariablePowAssignmentNode?, context: C): T
    abstract fun visitVariableIncreaseNode(n: VariableIncreaseNode?, context: C): T
    abstract fun visitVariableDecreaseNode(n: VariableDecreaseNode?, context: C): T
    abstract fun visitVariableUsageNode(n: VariableUsageNode?, context: C): T
    abstract fun visitEqEqualsNode(n: LogicalEqEqualsNode?, context: C): T
    abstract fun visitBiggerEqualsNode(n: LogicalBiggerEqualsNode?, context: C): T
    abstract fun visitSmallerEqualsNode(n: LogicalSmallerEqualsNode?, context: C): T
    abstract fun visitBiggerNode(n: LogicalBiggerNode?, context: C): T
    abstract fun visitSmallerNode(n: LogicalSmallerNode?, context: C): T
    abstract fun visitLogicalAndNode(n: LogicalAndNode?, context: C): T
    abstract fun visitLogicalOrNode(n: LogicalOrNode?, context: C): T
    abstract fun visitLogicalXOrNode(n: LogicalXOrNode?, context: C): T
    abstract fun visitWhileNode(n: WhileNode?, context: C): T
    abstract fun visitDoWhileNode(n: DoWhileNode?, context: C): T
    abstract fun visitForNode(n: ForNode?, context: C): T
    abstract fun visitIfNode(n: IfNode?, context: C): T
    abstract fun visitFunctionDeclarationNode(n: FunctionDeclarationNode?, context: C): T
    abstract fun visitClassDeclarationNode(n: ClassDeclarationNode?, context: C): T
    abstract fun visitClassConstruction(n: ClassConstructionNode?, context: C): T
    abstract fun visitFunctionCallNode(n: FunctionCallNode?, context: C): T
    abstract fun visitIdentifierNode(n: IdentifierNode?, context: C): T
    abstract fun visitLogicalTrueNode(n: LogicalTrueNode?, context: C): T
    abstract fun visitLogicalFalseNode(n: LogicalFalseNode?, context: C): T
    abstract fun visitImportNode(n: ImportNode?, context: C): T
    abstract fun visitCastNode(n: CastNode?, context: C): T
}