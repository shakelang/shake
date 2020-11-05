package com.github.nsc.de.compiler.generators.java;

import com.github.nsc.de.compiler.generators.Generator;
import com.github.nsc.de.compiler.generators.java.nodes.*;
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

public class JavaGenerator extends Generator<JavaNode> {
    @Override
    public JavaTree visitTree(Tree t) {

        JavaNode.JavaOperation[] children = new JavaNode.JavaOperation[t.getChildren().length];

        for(int i = 0; i < t.getChildren().length; i++) {
            children[i] = (JavaNode.JavaOperation) visit(t.getChildren()[i]);
            // FIXME Class declarations and function declarations will throw errors
        }

        return new JavaTree(children);
    }

    @Override
    public JavaValued.JavaDoublePart visitDoubleNode(DoubleNode n) {
        return new JavaValued.JavaDoublePart(n.getNumber());
    }

    @Override
    public JavaValued.JavaIntegerPart visitIntegerNode(IntegerNode n) {
        return new JavaValued.JavaIntegerPart(n.getNumber());
    }

    @Override
    public JavaValued.JavaExpression visitAddNode(AddNode n) {
        return new JavaValued.JavaExpression((JavaNode.JavaValuedOperation) visit(n.getLeft()), (JavaNode.JavaValuedOperation) visit(n.getRight()), "+");
    }

    @Override
    public JavaValued.JavaExpression visitSubNode(SubNode n) {
        return new JavaValued.JavaExpression((JavaNode.JavaValuedOperation) visit(n.getLeft()), (JavaNode.JavaValuedOperation) visit(n.getRight()), "-");
    }

    @Override
    public JavaValued.JavaExpression visitMulNode(MulNode n) {
        return new JavaValued.JavaExpression((JavaNode.JavaValuedOperation) visit(n.getLeft()), (JavaNode.JavaValuedOperation) visit(n.getRight()), "*");
    }

    @Override
    public JavaValued.JavaExpression visitDivNode(DivNode n) {
        return new JavaValued.JavaExpression((JavaNode.JavaValuedOperation) visit(n.getLeft()), (JavaNode.JavaValuedOperation) visit(n.getRight()), "/");
    }

    @Override
    public JavaValued.JavaFunctionCall visitPowNode(PowNode n) {
        return new JavaValued.JavaFunctionCall(
                new JavaIdentifier("pow", "Math"),
                new JavaNode.JavaValuedOperation[]{ (JavaNode.JavaValuedOperation) visit(n.getLeft()), (JavaNode.JavaValuedOperation) visit(n.getRight()) });
    }

    @Override
    public JavaVariableDeclaration visitVariableDeclarationNode(VariableDeclarationNode n) {
        JavaVariableType type = JavaVariableType.from(n.getType());
        // TODO Fields
        if(n.getAssignment() != null)
            return new JavaVariableDeclaration(type, n.getName(), (JavaNode.JavaValuedOperation) visit(n.getAssignment().getValue()));
        return new JavaVariableDeclaration(type, n.getName());
    }

    @Override
    public JavaValued.JavaVariableAssignment visitVariableAssignmentNode(VariableAssignmentNode n) {
        return new JavaValued.JavaVariableAssignment((JavaIdentifier) visit(n.getVariable()),
                (JavaNode.JavaValuedOperation) visit(n.getValue()));
    }

    @Override
    public JavaNode visitVariableAddAssignmentNode(VariableAddAssignmentNode n) {
        return new JavaValued.JavaVariableExpressionAssignment((JavaIdentifier) visit(n.getVariable()),
                (JavaNode.JavaValuedOperation) visit(n.getValue()), '+');
    }

    @Override
    public JavaNode visitVariableSubAssignmentNode(VariableSubAssignmentNode n) {
        return new JavaValued.JavaVariableExpressionAssignment((JavaIdentifier) visit(n.getVariable()),
                (JavaNode.JavaValuedOperation) visit(n.getValue()), '-');
    }

    @Override
    public JavaNode visitVariableMulAssignmentNode(VariableMulAssignmentNode n) {
        return new JavaValued.JavaVariableExpressionAssignment((JavaIdentifier) visit(n.getVariable()),
                (JavaNode.JavaValuedOperation) visit(n.getValue()), '*');
    }

    @Override
    public JavaNode visitVariableDivAssignmentNode(VariableDivAssignmentNode n) {
        return new JavaValued.JavaVariableExpressionAssignment((JavaIdentifier) visit(n.getVariable()),
                (JavaNode.JavaValuedOperation) visit(n.getValue()), '/');
    }

    @Override
    public JavaValued.JavaVariableAssignment visitVariablePowAssignmentNode(VariablePowAssignmentNode n) {
        JavaIdentifier left = (JavaIdentifier) visit(n.getVariable());
        return new JavaValued.JavaVariableAssignment(left,
                new JavaValued.JavaFunctionCall(
                        new JavaIdentifier("pow", "Math"),
                        new JavaNode.JavaValuedOperation[]{
                                (left),
                                (JavaNode.JavaValuedOperation) visit(n.getValue())
                        }));
    }

    @Override
    public JavaValued.JavaVariableIncr visitVariableIncreaseNode(VariableIncreaseNode n) {
        return new JavaValued.JavaVariableIncr((JavaIdentifier) visit(n.getVariable()));
    }

    @Override
    public JavaValued.JavaVariableDecr visitVariableDecreaseNode(VariableDecreaseNode n) {
        return new JavaValued.JavaVariableDecr((JavaIdentifier) visit(n.getVariable()));
    }

    @Override
    public JavaValued.JavaVariable visitVariableUsageNode(VariableUsageNode n) {
        return new JavaValued.JavaVariable(visitIdentifierNode(n.getVariable()));
    }

    @Override
    public JavaValued.JavaExpression visitEqEqualsNode(LogicalEqEqualsNode n) {
        return new JavaValued.JavaExpression(
                (JavaNode.JavaValuedOperation) visit(n.getLeft()),
                (JavaNode.JavaValuedOperation) visit(n.getRight()),
                "==");
    }

    @Override
    public JavaValued.JavaExpression visitBiggerEqualsNode(LogicalBiggerEqualsNode n) {
        return new JavaValued.JavaExpression(
                (JavaNode.JavaValuedOperation) visit(n.getLeft()),
                (JavaNode.JavaValuedOperation) visit(n.getRight()),
                ">=");
    }

    @Override
    public JavaValued.JavaExpression visitSmallerEqualsNode(LogicalSmallerEqualsNode n) {
        return new JavaValued.JavaExpression(
                (JavaNode.JavaValuedOperation) visit(n.getLeft()),
                (JavaNode.JavaValuedOperation) visit(n.getRight()),
                "<=");
    }

    @Override
    public JavaValued.JavaExpression visitBiggerNode(LogicalBiggerNode n) {
        return new JavaValued.JavaExpression(
                (JavaNode.JavaValuedOperation) visit(n.getLeft()),
                (JavaNode.JavaValuedOperation) visit(n.getRight()),
                ">");
    }

    @Override
    public JavaValued.JavaExpression visitSmallerNode(LogicalSmallerNode n) {
        return new JavaValued.JavaExpression(
                (JavaNode.JavaValuedOperation) visit(n.getLeft()),
                (JavaNode.JavaValuedOperation) visit(n.getRight()),
                "<");
    }

    @Override
    public JavaValued.JavaExpression visitLogicalAndNode(LogicalAndNode n) {
        return new JavaValued.JavaExpression(
                (JavaNode.JavaValuedOperation) visit(n.getLeft()),
                (JavaNode.JavaValuedOperation) visit(n.getRight()),
                "&&");
    }

    @Override
    public JavaValued.JavaExpression visitLogicalOrNode(LogicalOrNode n) {
        return new JavaValued.JavaExpression(
                (JavaNode.JavaValuedOperation) visit(n.getLeft()),
                (JavaNode.JavaValuedOperation) visit(n.getRight()),
                "||");
    }

    @Override
    public JavaWhileLoop visitWhileNode(WhileNode n) {
        return new JavaWhileLoop((JavaNode.JavaValuedOperation) visit(n.getCondition()), visitTree(n.getBody()));
    }

    @Override
    public JavaDoWhileLoop visitDoWhileNode(DoWhileNode n) {
        return new JavaDoWhileLoop((JavaNode.JavaValuedOperation) visit(n.getCondition()), visitTree(n.getBody()));
    }

    @Override
    public JavaForLoop visitForNode(ForNode n) {
        return new JavaForLoop(
                (JavaNode.JavaOperation) visit(n.getDeclaration()),
                (JavaNode.JavaValuedOperation) visit(n.getCondition()),
                (JavaNode.JavaOperation) visit(n.getRound()),
                visitTree(n.getBody()));
    }

    @Override
    public JavaIfCondition visitIfNode(IfNode n) {
        return n.getElseBody() != null ?
                new JavaIfCondition((JavaNode.JavaValuedOperation) visit(n.getCondition()), visitTree(n.getBody())) :
                new JavaIfCondition((JavaNode.JavaValuedOperation) visit(n.getCondition()), visitTree(n.getBody()),
                        visitTree(n.getElseBody()));
    }

    @Override
    public JavaNode visitFunctionDeclarationNode(FunctionDeclarationNode n) {
        throw new Error("Not implemented!");
    }

    @Override
    public JavaNode visitClassDeclarationNode(ClassDeclarationNode n) {
        throw new Error("Not implemented!");
    }

    @Override
    public JavaNode visitClassConstruction(ClassConstructionNode n) {
        throw new Error("Not implemented!");
    }

    @Override
    public JavaValued.JavaFunctionCall visitFunctionCallNode(FunctionCallNode n) {
        JavaNode.JavaValuedOperation[] values = new JavaNode.JavaValuedOperation[n.getArgs().length];
        for(int i = 0; i < values.length; i++) values[i] = (JavaNode.JavaValuedOperation) visit(n.getArgs()[i]);
        return new JavaValued.JavaFunctionCall((JavaIdentifier) visit(n.getFunction()), values);
    }

    @Override
    public JavaIdentifier visitIdentifierNode(IdentifierNode n) {
        return n.getParent() == null ? new JavaIdentifier(n.getName())
                : new JavaIdentifier(n.getName(), (JavaNode.JavaValuedOperation) visit(n.getParent()));
    }

    @Override
    public JavaNode visitLogicalTrueNode(LogicalTrueNode n) {
        return new JavaValued.JavaValue("true");
    }

    @Override
    public JavaNode visitLogicalFalseNode(LogicalFalseNode n) {
        return new JavaValued.JavaValue("false");
    }
}
