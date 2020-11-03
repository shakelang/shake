package com.github.nsc.de.compiler.generators;

import com.github.nsc.de.compiler.parser.node.*;
import com.github.nsc.de.compiler.parser.node.expression.*;
import com.github.nsc.de.compiler.parser.node.functions.FunctionCallNode;
import com.github.nsc.de.compiler.parser.node.functions.FunctionDeclarationNode;
import com.github.nsc.de.compiler.parser.node.logical.*;
import com.github.nsc.de.compiler.parser.node.loops.DoWhileNode;
import com.github.nsc.de.compiler.parser.node.loops.ForNode;
import com.github.nsc.de.compiler.parser.node.loops.WhileNode;
import com.github.nsc.de.compiler.parser.node.objects.ClassConstructionNode;
import com.github.nsc.de.compiler.parser.node.objects.ClassDeclarationNode;
import com.github.nsc.de.compiler.parser.node.variables.*;

public abstract class Generator<T> {
    
    public T visit(Node n) {

        if(n instanceof Tree) return visitTree((Tree) n);
        if(n instanceof NumberNode) return visitNumberNode((NumberNode) n);
        if(n instanceof AddNode) return visitAddNode((AddNode) n);
        if(n instanceof SubNode) return visitSubNode((SubNode) n);
        if(n instanceof MulNode) return visitMulNode((MulNode) n);
        if(n instanceof DivNode) return visitDivNode((DivNode) n);
        if(n instanceof PowNode) return visitPowNode((PowNode) n);
        if(n instanceof VariableDeclarationNode) return visitVariableDeclarationNode((VariableDeclarationNode) n);
        if(n instanceof VariableAddAssignmentNode) return visitVariableAddAssignmentNode((VariableAddAssignmentNode) n);
        if(n instanceof VariableSubAssignmentNode) return visitVariableSubAssignmentNode((VariableSubAssignmentNode) n);
        if(n instanceof VariableMulAssignmentNode) return visitVariableMulAssignmentNode((VariableMulAssignmentNode) n);
        if(n instanceof VariableDivAssignmentNode) return visitVariableDivAssignmentNode((VariableDivAssignmentNode) n);
        if(n instanceof VariablePowAssignmentNode) return visitVariablePowAssignmentNode((VariablePowAssignmentNode) n);
        if(n instanceof VariableIncreaseNode) return visitVariableIncreaseNode((VariableIncreaseNode) n);
        if(n instanceof VariableDecreaseNode) return visitVariableDecreaseNode((VariableDecreaseNode) n);
        if(n instanceof VariableAssignmentNode) return visitVariableAssignmentNode((VariableAssignmentNode) n);
        if(n instanceof VariableUsageNode) return visitVariableUsageNode((VariableUsageNode) n);
        if(n instanceof LogicalEqEqualsNode) return visitEqEqualsNode((LogicalEqEqualsNode) n);
        if(n instanceof LogicalBiggerEqualsNode) return visitBiggerEqualsNode((LogicalBiggerEqualsNode) n);
        if(n instanceof LogicalSmallerEqualsNode) return visitSmallerEqualsNode((LogicalSmallerEqualsNode) n);
        if(n instanceof LogicalBiggerNode) return visitBiggerNode((LogicalBiggerNode) n);
        if(n instanceof LogicalSmallerNode) return visitSmallerNode((LogicalSmallerNode) n);
        if(n instanceof LogicalAndNode) return visitLogicalAndNode((LogicalAndNode) n);
        if(n instanceof LogicalOrNode) return visitLogicalOrNode((LogicalOrNode) n);
        if(n instanceof WhileNode) return visitWhileNode((WhileNode) n);
        if(n instanceof DoWhileNode) return visitDoWhileNode((DoWhileNode) n);
        if(n instanceof ForNode) return visitForNode((ForNode) n);
        if(n instanceof IfNode) return visitIfNode((IfNode) n);
        if(n instanceof FunctionDeclarationNode) return visitFunctionDeclarationNode((FunctionDeclarationNode) n);
        if(n instanceof ClassConstructionNode) return visitClassConstruction((ClassConstructionNode) n);
        if(n instanceof FunctionCallNode) return visitFunctionCallNode((FunctionCallNode) n);
        if(n instanceof IdentifierNode) return visitIdentifierNode((IdentifierNode) n);
        if(n instanceof ClassDeclarationNode) return visitClassDeclarationNode((ClassDeclarationNode) n);
        if(n instanceof LogicalTrueNode) return visitLogicalTrueNode((LogicalTrueNode) n);
        if(n instanceof LogicalFalseNode) return visitLogicalFalseNode((LogicalFalseNode) n);
        throw new Error(String.format("It looks like that node is not implemented in the Interpreter: %s", n.getClass().toString()));

    }

    public abstract T visitTree(Tree t);
    public abstract T visitNumberNode(NumberNode n);
    public abstract T visitAddNode(AddNode n);
    public abstract T visitSubNode(SubNode n);
    public abstract T visitMulNode(MulNode n);
    public abstract T visitDivNode(DivNode n);
    public abstract T visitPowNode(PowNode n);
    public abstract T visitVariableDeclarationNode(VariableDeclarationNode n);
    public abstract T visitVariableAssignmentNode(VariableAssignmentNode n);
    public abstract T visitVariableAddAssignmentNode(VariableAddAssignmentNode n);
    public abstract T visitVariableSubAssignmentNode(VariableSubAssignmentNode n);
    public abstract T visitVariableMulAssignmentNode(VariableMulAssignmentNode n);
    public abstract T visitVariableDivAssignmentNode(VariableDivAssignmentNode n);
    public abstract T visitVariablePowAssignmentNode(VariablePowAssignmentNode n);
    public abstract T visitVariableIncreaseNode(VariableIncreaseNode n);
    public abstract T visitVariableDecreaseNode(VariableDecreaseNode n);
    public abstract T visitVariableUsageNode(VariableUsageNode n);
    public abstract T visitEqEqualsNode(LogicalEqEqualsNode n);
    public abstract T visitBiggerEqualsNode(LogicalBiggerEqualsNode n);
    public abstract T visitSmallerEqualsNode(LogicalSmallerEqualsNode n);
    public abstract T visitBiggerNode(LogicalBiggerNode n);
    public abstract T visitSmallerNode(LogicalSmallerNode n);
    public abstract T visitLogicalAndNode(LogicalAndNode n);
    public abstract T visitLogicalOrNode(LogicalOrNode n);
    public abstract T visitWhileNode(WhileNode n);
    public abstract T visitDoWhileNode(DoWhileNode n);
    public abstract T visitForNode(ForNode n);
    public abstract T visitIfNode(IfNode n);
    public abstract T visitFunctionDeclarationNode(FunctionDeclarationNode n);
    public abstract T visitClassDeclarationNode(ClassDeclarationNode n);
    public abstract T visitClassConstruction(ClassConstructionNode n);
    public abstract T visitFunctionCallNode(FunctionCallNode n);
    public abstract T visitIdentifierNode(IdentifierNode n);
    public abstract T visitLogicalTrueNode(LogicalTrueNode n);
    public abstract T visitLogicalFalseNode(LogicalFalseNode n);
}
