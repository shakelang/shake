package com.github.nsc.de.shake.generators.java;

import com.github.nsc.de.shake.generators.Generator;
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

public class SimpleJavaGenerator extends Generator<String> {


    @Override
    public String visitTree(Tree t) {
        // Generate all the children and join them
        StringBuilder out = new StringBuilder();
        for(Node n : t.getChildren()) {
            out.append(visit(n)).append(';');
        }
        return out.toString();

    }

    @Override
    public String visitDoubleNode(DoubleNode n) {
        // Just give back the number
        return String.valueOf(n.getNumber());
    }

    @Override
    public String visitIntegerNode(IntegerNode n) {
        // Just give back the number
        return String.valueOf(n.getNumber());
    }

    @Override
    public String visitAddNode(AddNode n) {
        // Generate primitive add
        return String.valueOf(n.getLeft()) + '+' + String.valueOf(n.getRight());
    }

    @Override
    public String visitSubNode(SubNode n) {
        // Generate primitive sub
        return String.valueOf(n.getLeft()) + '-' + n.getRight();
    }

    @Override
    public String visitMulNode(MulNode n) {
        // Generate primitive mul
        return String.valueOf(n.getLeft()) + '*' + n.getRight();
    }

    @Override
    public String visitDivNode(DivNode n) {
        // Generate primitive div
        return String.valueOf(n.getLeft()) + '/' + n.getRight();
    }

    @Override
    public String visitPowNode(PowNode n) {
        // Generate usage of pow function
        return "Math.pow(" + String.valueOf(n.getLeft()) + ',' + n.getRight() + ')';
    }

    @Override
    public String visitVariableDeclarationNode(VariableDeclarationNode n) {
        // Create variable and (if asked) put assignment
        return this.toName(n.getType()) + " " + n.getName() +
                (n.getAssignment() != null ? "=" + visit(n.getAssignment().getValue()) : "");
    }

    @Override
    public String visitVariableAssignmentNode(VariableAssignmentNode n) {
        return visit(n.getVariable()) + "=" + visit(n.getValue());
    }

    @Override
    public String visitVariableAddAssignmentNode(VariableAddAssignmentNode n) {
        return visit(n.getVariable()) + "+=" + visit(n.getValue());
    }

    @Override
    public String visitVariableSubAssignmentNode(VariableSubAssignmentNode n) {
        return visit(n.getVariable()) + "-=" + visit(n.getValue());
    }

    @Override
    public String visitVariableMulAssignmentNode(VariableMulAssignmentNode n) {
        return visit(n.getVariable()) + "*=" + visit(n.getValue());
    }

    @Override
    public String visitVariableDivAssignmentNode(VariableDivAssignmentNode n) {
        return visit(n.getVariable()) + "/=" + visit(n.getValue());
    }

    @Override
    public String visitVariablePowAssignmentNode(VariablePowAssignmentNode n) {
        String variable = visit(n.getVariable());
        return variable + "=" + "Math.pow(" + variable + ',' + visit(n.getValue()) + ')';
    }

    @Override
    public String visitVariableIncreaseNode(VariableIncreaseNode n) {
        return visit(n.getVariable()) + "++";
    }

    @Override
    public String visitVariableDecreaseNode(VariableDecreaseNode n) {
        return visit(n.getVariable()) + "--";
    }

    @Override
    public String visitVariableUsageNode(VariableUsageNode n) {
        return visit(n.getVariable());
    }

    @Override
    public String visitEqEqualsNode(LogicalEqEqualsNode n) {
        // TODO equals for objects / strings
        return visit(n.getLeft()) + "==" + visit(n.getRight());
    }

    @Override
    public String visitBiggerEqualsNode(LogicalBiggerEqualsNode n) {
        return visit(n.getLeft()) + ">=" + visit(n.getRight());
    }

    @Override
    public String visitSmallerEqualsNode(LogicalSmallerEqualsNode n) {
        return visit(n.getLeft()) + "<=" + visit(n.getRight());
    }

    @Override
    public String visitBiggerNode(LogicalBiggerNode n) {
        return visit(n.getLeft()) + ">" + visit(n.getRight());
    }

    @Override
    public String visitSmallerNode(LogicalSmallerNode n) {
        return visit(n.getLeft()) + "<" + visit(n.getRight());
    }

    @Override
    public String visitLogicalAndNode(LogicalAndNode n) {
        return visit(n.getLeft()) + "&&" + visit(n.getRight());
    }

    @Override
    public String visitLogicalOrNode(LogicalOrNode n) {
        return visit(n.getLeft()) + "||" + visit(n.getRight());
    }

    @Override
    public String visitWhileNode(WhileNode n) {
        return "while(" + visit(n.getCondition()) + "){" + visit(n.getBody()) + '}';
    }

    @Override
    public String visitDoWhileNode(DoWhileNode n) {
        return "do{" + visit(n.getBody()) + "}while(" + visit(n.getCondition()) + ')';
    }

    @Override
    public String visitForNode(ForNode n) {
        return "for(" + visit(n.getDeclaration()) + ';' + visit(n.getCondition()) + ';'
                + visit(n.getRound()) + "){" + visit(n.getBody()) + '}';
    }

    @Override
    public String visitIfNode(IfNode n) {
        return "if(" + visit(n.getCondition()) + "){" + visit(n.getBody()) + '}'
                + n.getElseBody() != null ? "else{" + visit(n.getElseBody()) + '}' : "";
    }

    @Override
    public String visitFunctionDeclarationNode(FunctionDeclarationNode n) {
        throw new Error("Not implemented yet!");
    }

    @Override
    public String visitClassDeclarationNode(ClassDeclarationNode n) {
        throw new Error("Not implemented yet!");
    }

    @Override
    public String visitClassConstruction(ClassConstructionNode n) {
        throw new Error("Not implemented yet!");
    }

    @Override
    public String visitFunctionCallNode(FunctionCallNode n) {
        throw new Error("Not implemented yet!");
    }

    @Override
    public String visitIdentifierNode(IdentifierNode n) {
        return (n.getParent() != null ? visit(n.getParent()) + '.' : "") + n.getName();
    }

    @Override
    public String visitLogicalTrueNode(LogicalTrueNode n) {
        return "true";
    }

    @Override
    public String visitLogicalFalseNode(LogicalFalseNode n) {
        return "false";
    }

    private String toName(VariableType type) {
        switch (type.getType()) {
            case BYTE: return "byte";
            case SHORT: return "short";
            case INTEGER: return "int";
            case LONG: return "long";
            case FLOAT: return "float";
            case DOUBLE: return "double";
            case BOOLEAN: return "boolean";
            case CHAR: return "char";
            case OBJECT: return type.getSubtype();
            case ARRAY: throw new Error("Not implemented yet!"); // TODO
            case DYNAMIC: return "Object";
        }
        throw new Error();
    }
}
