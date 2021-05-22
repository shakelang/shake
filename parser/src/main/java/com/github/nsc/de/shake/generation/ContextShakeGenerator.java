package com.github.nsc.de.shake.generation;

import com.github.nsc.de.shake.parser.node.*;
import com.github.nsc.de.shake.parser.node.expression.*;
import com.github.nsc.de.shake.parser.node.factor.DoubleNode;
import com.github.nsc.de.shake.parser.node.factor.IntegerNode;
import com.github.nsc.de.shake.parser.node.functions.FunctionCallNode;
import com.github.nsc.de.shake.parser.node.functions.FunctionDeclarationNode;
import com.github.nsc.de.shake.parser.node.logical.*;
import com.github.nsc.de.shake.parser.node.loops.DoWhileNode;
import com.github.nsc.de.shake.parser.node.loops.ForNode;
import com.github.nsc.de.shake.parser.node.loops.WhileNode;
import com.github.nsc.de.shake.parser.node.objects.ClassConstructionNode;
import com.github.nsc.de.shake.parser.node.objects.ClassDeclarationNode;
import com.github.nsc.de.shake.parser.node.variables.*;

public abstract class ContextShakeGenerator<T, C> implements ShakeGeneratorBase {
    
    public T visit(Node n, C context) {

        if(n instanceof Tree) return visitTree((Tree) n, context);
        if(n instanceof DoubleNode) return visitDoubleNode((DoubleNode) n, context);
        if(n instanceof IntegerNode) return visitIntegerNode((IntegerNode) n, context);
        if(n instanceof AddNode) return visitAddNode((AddNode) n, context);
        if(n instanceof SubNode) return visitSubNode((SubNode) n, context);
        if(n instanceof MulNode) return visitMulNode((MulNode) n, context);
        if(n instanceof DivNode) return visitDivNode((DivNode) n, context);
        if(n instanceof ModNode) return visitModNode((ModNode) n, context);
        if(n instanceof PowNode) return visitPowNode((PowNode) n, context);
        if(n instanceof VariableDeclarationNode) return visitVariableDeclarationNode((VariableDeclarationNode) n, context);
        if(n instanceof VariableAddAssignmentNode) return visitVariableAddAssignmentNode((VariableAddAssignmentNode) n, context);
        if(n instanceof VariableSubAssignmentNode) return visitVariableSubAssignmentNode((VariableSubAssignmentNode) n, context);
        if(n instanceof VariableMulAssignmentNode) return visitVariableMulAssignmentNode((VariableMulAssignmentNode) n, context);
        if(n instanceof VariableDivAssignmentNode) return visitVariableDivAssignmentNode((VariableDivAssignmentNode) n, context);
        if(n instanceof VariableModAssignmentNode) return visitVariableModAssignmentNode((VariableModAssignmentNode) n, context);
        if(n instanceof VariablePowAssignmentNode) return visitVariablePowAssignmentNode((VariablePowAssignmentNode) n, context);
        if(n instanceof VariableIncreaseNode) return visitVariableIncreaseNode((VariableIncreaseNode) n, context);
        if(n instanceof VariableDecreaseNode) return visitVariableDecreaseNode((VariableDecreaseNode) n, context);
        if(n instanceof VariableAssignmentNode) return visitVariableAssignmentNode((VariableAssignmentNode) n, context);
        if(n instanceof VariableUsageNode) return visitVariableUsageNode((VariableUsageNode) n, context);
        if(n instanceof LogicalEqEqualsNode) return visitEqEqualsNode((LogicalEqEqualsNode) n, context);
        if(n instanceof LogicalBiggerEqualsNode) return visitBiggerEqualsNode((LogicalBiggerEqualsNode) n, context);
        if(n instanceof LogicalSmallerEqualsNode) return visitSmallerEqualsNode((LogicalSmallerEqualsNode) n, context);
        if(n instanceof LogicalBiggerNode) return visitBiggerNode((LogicalBiggerNode) n, context);
        if(n instanceof LogicalSmallerNode) return visitSmallerNode((LogicalSmallerNode) n, context);
        if(n instanceof LogicalAndNode) return visitLogicalAndNode((LogicalAndNode) n, context);
        if(n instanceof LogicalOrNode) return visitLogicalOrNode((LogicalOrNode) n, context);
        if(n instanceof LogicalXOrNode) return visitLogicalXOrNode((LogicalXOrNode) n, context);
        if(n instanceof WhileNode) return visitWhileNode((WhileNode) n, context);
        if(n instanceof DoWhileNode) return visitDoWhileNode((DoWhileNode) n, context);
        if(n instanceof ForNode) return visitForNode((ForNode) n, context);
        if(n instanceof IfNode) return visitIfNode((IfNode) n, context);
        if(n instanceof FunctionDeclarationNode) return visitFunctionDeclarationNode((FunctionDeclarationNode) n, context);
        if(n instanceof ClassConstructionNode) return visitClassConstruction((ClassConstructionNode) n, context);
        if(n instanceof FunctionCallNode) return visitFunctionCallNode((FunctionCallNode) n, context);
        if(n instanceof IdentifierNode) return visitIdentifierNode((IdentifierNode) n, context);
        if(n instanceof ClassDeclarationNode) return visitClassDeclarationNode((ClassDeclarationNode) n, context);
        if(n instanceof LogicalTrueNode) return visitLogicalTrueNode((LogicalTrueNode) n, context);
        if(n instanceof LogicalFalseNode) return visitLogicalFalseNode((LogicalFalseNode) n, context);
        if(n instanceof ImportNode) return visitImportNode((ImportNode) n, context);
        if(n instanceof CastNode) return visitCastNode((CastNode) n, context);
        throw new Error(String.format("It looks like that node is not implemented in the generator: %s",
                n.getClass().toString()));

    }

    public abstract T visitTree(Tree t, C context);
    public abstract T visitDoubleNode(DoubleNode n, C context);
    public abstract T visitIntegerNode(IntegerNode n, C context);
    public abstract T visitAddNode(AddNode n, C context);
    public abstract T visitSubNode(SubNode n, C context);
    public abstract T visitMulNode(MulNode n, C context);
    public abstract T visitDivNode(DivNode n, C context);
    public abstract T visitModNode(ModNode n, C context);
    public abstract T visitPowNode(PowNode n, C context);
    public abstract T visitVariableDeclarationNode(VariableDeclarationNode n, C context);
    public abstract T visitVariableAssignmentNode(VariableAssignmentNode n, C context);
    public abstract T visitVariableAddAssignmentNode(VariableAddAssignmentNode n, C context);
    public abstract T visitVariableSubAssignmentNode(VariableSubAssignmentNode n, C context);
    public abstract T visitVariableMulAssignmentNode(VariableMulAssignmentNode n, C context);
    public abstract T visitVariableDivAssignmentNode(VariableDivAssignmentNode n, C context);
    public abstract T visitVariableModAssignmentNode(VariableModAssignmentNode n, C context);
    public abstract T visitVariablePowAssignmentNode(VariablePowAssignmentNode n, C context);
    public abstract T visitVariableIncreaseNode(VariableIncreaseNode n, C context);
    public abstract T visitVariableDecreaseNode(VariableDecreaseNode n, C context);
    public abstract T visitVariableUsageNode(VariableUsageNode n, C context);
    public abstract T visitEqEqualsNode(LogicalEqEqualsNode n, C context);
    public abstract T visitBiggerEqualsNode(LogicalBiggerEqualsNode n, C context);
    public abstract T visitSmallerEqualsNode(LogicalSmallerEqualsNode n, C context);
    public abstract T visitBiggerNode(LogicalBiggerNode n, C context);
    public abstract T visitSmallerNode(LogicalSmallerNode n, C context);
    public abstract T visitLogicalAndNode(LogicalAndNode n, C context);
    public abstract T visitLogicalOrNode(LogicalOrNode n, C context);
    public abstract T visitLogicalXOrNode(LogicalXOrNode n, C context);
    public abstract T visitWhileNode(WhileNode n, C context);
    public abstract T visitDoWhileNode(DoWhileNode n, C context);
    public abstract T visitForNode(ForNode n, C context);
    public abstract T visitIfNode(IfNode n, C context);
    public abstract T visitFunctionDeclarationNode(FunctionDeclarationNode n, C context);
    public abstract T visitClassDeclarationNode(ClassDeclarationNode n, C context);
    public abstract T visitClassConstruction(ClassConstructionNode n, C context);
    public abstract T visitFunctionCallNode(FunctionCallNode n, C context);
    public abstract T visitIdentifierNode(IdentifierNode n, C context);
    public abstract T visitLogicalTrueNode(LogicalTrueNode n, C context);
    public abstract T visitLogicalFalseNode(LogicalFalseNode n, C context);
    public abstract T visitImportNode(ImportNode n, C context);
    public abstract T visitCastNode(CastNode n, C context);

}
