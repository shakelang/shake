package com.shakelang.shake.generation

import com.shakelang.shake.parser.node.ShakeNodeImpl
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.misc.ShakeIdentifierNode
import com.shakelang.shake.parser.node.mixed.*
import com.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import com.shakelang.shake.parser.node.outer.ShakeImportNode
import com.shakelang.shake.parser.node.outer.ShakeMethodDeclarationNode
import com.shakelang.shake.parser.node.statements.*
import com.shakelang.shake.parser.node.values.ShakeCastNode
import com.shakelang.shake.parser.node.values.ShakeVariableUsageNode
import com.shakelang.shake.parser.node.values.expression.*
import com.shakelang.shake.parser.node.values.factor.ShakeFalseLiteralNode
import com.shakelang.shake.parser.node.values.factor.ShakeFloatLiteralNode
import com.shakelang.shake.parser.node.values.factor.ShakeIntegerLiteralNode
import com.shakelang.shake.parser.node.values.factor.ShakeTrueLiteralNode

abstract class ShakeGenerator<T> : com.shakelang.shake.generation.ShakeGeneratorBase {
    fun visit(n: ShakeNodeImpl): T {
        if (n is ShakeBlockNode) return visitTree(n)
        if (n is ShakeFloatLiteralNode) return visitDoubleNode(n)
        if (n is ShakeIntegerLiteralNode) return visitIntegerNode(n)
        if (n is ShakeAddNode) return visitAddNode(n)
        if (n is ShakeSubNode) return visitSubNode(n)
        if (n is ShakeMulNode) return visitMulNode(n)
        if (n is ShakeDivNode) return visitDivNode(n)
        if (n is ShakeModNode) return visitModNode(n)
        if (n is ShakePowNode) return visitPowNode(n)
        if (n is ShakeLocalDeclarationNode) return visitVariableDeclarationNode(n)
        if (n is ShakeVariableAddAssignmentNode) return visitVariableAddAssignmentNode(n)
        if (n is ShakeVariableSubAssignmentNode) return visitVariableSubAssignmentNode(n)
        if (n is ShakeVariableMulAssignmentNode) return visitVariableMulAssignmentNode(n)
        if (n is ShakeVariableDivAssignmentNode) return visitVariableDivAssignmentNode(n)
        if (n is ShakeVariableModAssignmentNode) return visitVariableModAssignmentNode(n)
        if (n is ShakeVariablePowAssignmentNode) return visitVariablePowAssignmentNode(n)
        if (n is ShakeValuedNode) return visitVariableAssignmentNode(n)
        if (n is ShakeVariableUsageNode) return visitVariableUsageNode(n)
        if (n is ShakeEqualNode) return visitEqEqualsNode(n)
        if (n is ShakeGreaterThanOrEqualNode) return visitBiggerEqualsNode(n)
        if (n is ShakeLessThanOrEqualNode) return visitSmallerEqualsNode(n)
        if (n is ShakeGreaterThanNode) return visitBiggerNode(n)
        if (n is ShakeLessThanNode) return visitSmallerNode(n)
        if (n is ShakeLogicalAndNode) return visitLogicalAndNode(n)
        if (n is ShakeLogicalOrNode) return visitLogicalOrNode(n)
        if (n is ShakeLogicalXOrNode) return visitLogicalXOrNode(n)
        if (n is ShakeWhileNode) return visitWhileNode(n)
        if (n is ShakeDoWhileNode) return visitDoWhileNode(n)
        if (n is ShakeForNode) return visitForNode(n)
        if (n is ShakeIfNode) return visitIfNode(n)
        if (n is ShakeMethodDeclarationNode) return visitFunctionDeclarationNode(n)
        if (n is ShakeInvocationNode) return visitFunctionCallNode(n)
        if (n is ShakeIdentifierNode) return visitIdentifierNode(n)
        if (n is ShakeClassDeclarationNode) return visitClassDeclarationNode(n)
        if (n is ShakeTrueLiteralNode) return visitLogicalTrueNode(n)
        if (n is ShakeFalseLiteralNode) return visitLogicalFalseNode(n)
        if (n is ShakeImportNode) return visitImportNode(n)
        if (n is ShakeCastNode) return visitCastNode(n)
        throw Error("It looks like that node is not implemented in the Interpreter: $n")
    }

    abstract fun visitTree(t: ShakeBlockNode): T
    abstract fun visitDoubleNode(n: ShakeFloatLiteralNode): T
    abstract fun visitIntegerNode(n: ShakeIntegerLiteralNode): T
    abstract fun visitAddNode(n: ShakeAddNode): T
    abstract fun visitSubNode(n: ShakeSubNode): T
    abstract fun visitMulNode(n: ShakeMulNode): T
    abstract fun visitDivNode(n: ShakeDivNode): T
    abstract fun visitModNode(n: ShakeModNode): T
    abstract fun visitPowNode(n: ShakePowNode): T
    abstract fun visitVariableDeclarationNode(n: ShakeLocalDeclarationNode): T
    abstract fun visitVariableAssignmentNode(n: ShakeValuedNode): T
    abstract fun visitVariableAddAssignmentNode(n: ShakeVariableAddAssignmentNode): T
    abstract fun visitVariableSubAssignmentNode(n: ShakeVariableSubAssignmentNode): T
    abstract fun visitVariableMulAssignmentNode(n: ShakeVariableMulAssignmentNode): T
    abstract fun visitVariableDivAssignmentNode(n: ShakeVariableDivAssignmentNode): T
    abstract fun visitVariableModAssignmentNode(n: ShakeVariableModAssignmentNode): T
    abstract fun visitVariablePowAssignmentNode(n: ShakeVariablePowAssignmentNode): T
    abstract fun visitVariableUsageNode(n: ShakeVariableUsageNode): T
    abstract fun visitEqEqualsNode(n: ShakeEqualNode): T
    abstract fun visitBiggerEqualsNode(n: ShakeGreaterThanOrEqualNode): T
    abstract fun visitSmallerEqualsNode(n: ShakeLessThanOrEqualNode): T
    abstract fun visitBiggerNode(n: ShakeGreaterThanNode): T
    abstract fun visitSmallerNode(n: ShakeLessThanNode): T
    abstract fun visitLogicalAndNode(n: ShakeLogicalAndNode): T
    abstract fun visitLogicalOrNode(n: ShakeLogicalOrNode): T
    abstract fun visitLogicalXOrNode(n: ShakeLogicalXOrNode): T
    abstract fun visitWhileNode(n: ShakeWhileNode): T
    abstract fun visitDoWhileNode(n: ShakeDoWhileNode): T
    abstract fun visitForNode(n: ShakeForNode): T
    abstract fun visitIfNode(n: ShakeIfNode): T
    abstract fun visitFunctionDeclarationNode(n: ShakeMethodDeclarationNode): T
    abstract fun visitClassDeclarationNode(n: ShakeClassDeclarationNode): T
    abstract fun visitFunctionCallNode(n: ShakeInvocationNode): T
    abstract fun visitIdentifierNode(n: ShakeIdentifierNode): T
    abstract fun visitLogicalTrueNode(n: ShakeTrueLiteralNode): T
    abstract fun visitLogicalFalseNode(n: ShakeFalseLiteralNode): T
    abstract fun visitImportNode(n: ShakeImportNode): T
    abstract fun visitCastNode(n: ShakeCastNode): T
}
