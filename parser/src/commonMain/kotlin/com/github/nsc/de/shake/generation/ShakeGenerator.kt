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

abstract class ShakeGenerator<T> : ShakeGeneratorBase {
    fun visit(n: Node): T {
        if (n is Tree) return visitTree(n)
        if (n is DoubleNode) return visitDoubleNode(n)
        if (n is IntegerNode) return visitIntegerNode(n)
        if (n is AddNode) return visitAddNode(n)
        if (n is SubNode) return visitSubNode(n)
        if (n is MulNode) return visitMulNode(n)
        if (n is DivNode) return visitDivNode(n)
        if (n is ModNode) return visitModNode(n)
        if (n is PowNode) return visitPowNode(n)
        if (n is VariableDeclarationNode) return visitVariableDeclarationNode(n)
        if (n is VariableAddAssignmentNode) return visitVariableAddAssignmentNode(n)
        if (n is VariableSubAssignmentNode) return visitVariableSubAssignmentNode(n)
        if (n is VariableMulAssignmentNode) return visitVariableMulAssignmentNode(n)
        if (n is VariableDivAssignmentNode) return visitVariableDivAssignmentNode(n)
        if (n is VariableModAssignmentNode) return visitVariableModAssignmentNode(n)
        if (n is VariablePowAssignmentNode) return visitVariablePowAssignmentNode(n)
        if (n is VariableIncreaseNode) return visitVariableIncreaseNode(n)
        if (n is VariableDecreaseNode) return visitVariableDecreaseNode(n)
        if (n is VariableAssignmentNode) return visitVariableAssignmentNode(n)
        if (n is VariableUsageNode) return visitVariableUsageNode(n)
        if (n is LogicalEqEqualsNode) return visitEqEqualsNode(n)
        if (n is LogicalBiggerEqualsNode) return visitBiggerEqualsNode(n)
        if (n is LogicalSmallerEqualsNode) return visitSmallerEqualsNode(n)
        if (n is LogicalBiggerNode) return visitBiggerNode(n)
        if (n is LogicalSmallerNode) return visitSmallerNode(n)
        if (n is LogicalAndNode) return visitLogicalAndNode(n)
        if (n is LogicalOrNode) return visitLogicalOrNode(n)
        if (n is LogicalXOrNode) return visitLogicalXOrNode(n)
        if (n is WhileNode) return visitWhileNode(n)
        if (n is DoWhileNode) return visitDoWhileNode(n)
        if (n is ForNode) return visitForNode(n)
        if (n is IfNode) return visitIfNode(n)
        if (n is FunctionDeclarationNode) return visitFunctionDeclarationNode(n)
        if (n is ClassConstructionNode) return visitClassConstruction(n)
        if (n is FunctionCallNode) return visitFunctionCallNode(n)
        if (n is IdentifierNode) return visitIdentifierNode(n)
        if (n is ClassDeclarationNode) return visitClassDeclarationNode(n)
        if (n is LogicalTrueNode) return visitLogicalTrueNode(n)
        if (n is LogicalFalseNode) return visitLogicalFalseNode(n)
        if (n is ImportNode) return visitImportNode(n)
        if (n is CastNode) return visitCastNode(n)
        throw Error("It looks like that node is not implemented in the Interpreter: $n")
    }

    abstract fun visitTree(t: Tree?): T
    abstract fun visitDoubleNode(n: DoubleNode?): T
    abstract fun visitIntegerNode(n: IntegerNode?): T
    abstract fun visitAddNode(n: AddNode?): T
    abstract fun visitSubNode(n: SubNode?): T
    abstract fun visitMulNode(n: MulNode?): T
    abstract fun visitDivNode(n: DivNode?): T
    abstract fun visitModNode(n: ModNode?): T
    abstract fun visitPowNode(n: PowNode?): T
    abstract fun visitVariableDeclarationNode(n: VariableDeclarationNode?): T
    abstract fun visitVariableAssignmentNode(n: VariableAssignmentNode?): T
    abstract fun visitVariableAddAssignmentNode(n: VariableAddAssignmentNode?): T
    abstract fun visitVariableSubAssignmentNode(n: VariableSubAssignmentNode?): T
    abstract fun visitVariableMulAssignmentNode(n: VariableMulAssignmentNode?): T
    abstract fun visitVariableDivAssignmentNode(n: VariableDivAssignmentNode?): T
    abstract fun visitVariableModAssignmentNode(n: VariableModAssignmentNode?): T
    abstract fun visitVariablePowAssignmentNode(n: VariablePowAssignmentNode?): T
    abstract fun visitVariableIncreaseNode(n: VariableIncreaseNode?): T
    abstract fun visitVariableDecreaseNode(n: VariableDecreaseNode?): T
    abstract fun visitVariableUsageNode(n: VariableUsageNode?): T
    abstract fun visitEqEqualsNode(n: LogicalEqEqualsNode?): T
    abstract fun visitBiggerEqualsNode(n: LogicalBiggerEqualsNode?): T
    abstract fun visitSmallerEqualsNode(n: LogicalSmallerEqualsNode?): T
    abstract fun visitBiggerNode(n: LogicalBiggerNode?): T
    abstract fun visitSmallerNode(n: LogicalSmallerNode?): T
    abstract fun visitLogicalAndNode(n: LogicalAndNode?): T
    abstract fun visitLogicalOrNode(n: LogicalOrNode?): T
    abstract fun visitLogicalXOrNode(n: LogicalXOrNode?): T
    abstract fun visitWhileNode(n: WhileNode?): T
    abstract fun visitDoWhileNode(n: DoWhileNode?): T
    abstract fun visitForNode(n: ForNode?): T
    abstract fun visitIfNode(n: IfNode?): T
    abstract fun visitFunctionDeclarationNode(n: FunctionDeclarationNode?): T
    abstract fun visitClassDeclarationNode(n: ClassDeclarationNode?): T
    abstract fun visitClassConstruction(n: ClassConstructionNode?): T
    abstract fun visitFunctionCallNode(n: FunctionCallNode?): T
    abstract fun visitIdentifierNode(n: IdentifierNode?): T
    abstract fun visitLogicalTrueNode(n: LogicalTrueNode?): T
    abstract fun visitLogicalFalseNode(n: LogicalFalseNode?): T
    abstract fun visitImportNode(n: ImportNode?): T
    abstract fun visitCastNode(n: CastNode?): T
}