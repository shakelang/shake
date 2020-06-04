package com.github.nsc.de.compiler.interpreter;

import com.github.nsc.de.compiler.parser.node.*;
import com.github.nsc.de.compiler.parser.node.expression.*;
import com.github.nsc.de.compiler.parser.node.logical.*;


public class Interpreter {

    private final VariableList variables = new VariableList();

    public InterpreterResult<Object> visit(Node n) {

        if(n instanceof Tree) return visitTree((Tree) n);
        if(n instanceof NumberNode) return visitNumberNode((NumberNode) n);
        if(n instanceof AddNode) return visitAddNode((AddNode) n);
        if(n instanceof SubNode) return visitSubNode((SubNode) n);
        if(n instanceof MulNode) return visitMulNode((MulNode) n);
        if(n instanceof DivNode) return visitDivNode((DivNode) n);
        if(n instanceof PowNode) return visitPowNode((PowNode) n);
        if(n instanceof VariableDeclarationNode) return visitVariableDeclarationNode((VariableDeclarationNode) n);
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
        if(n instanceof LogicalTrueNode) return new InterpreterResult<>(true);
        if(n instanceof LogicalFalseNode) return new InterpreterResult<>(false);
        if(n == null) return new InterpreterResult<>(null);
        throw new Error("It looks like that Node is not implemented in the Interpreter");

    }

    public InterpreterResult<Object> visitTree(Tree t) {
        for (int i = 0; i < t.getChildren().length - 1; i++) visit(t.getChildren()[i]);
        return visit(t.getChildren()[t.getChildren().length-1]);
    }

    public InterpreterResult<Object> visitNumberNode(NumberNode n) {
        return new InterpreterResult<>(n.getNumber());
    }

    public InterpreterResult<Object> visitAddNode(AddNode n) {
        return new InterpreterResult<>(((double) visit(n.getLeft()).getValue()) + ((double)visit(n.getRight()).getValue()));
    }

    public InterpreterResult<Object> visitSubNode(SubNode n) {
        return new InterpreterResult<>(((double)visit(n.getLeft()).getValue()) - ((double)visit(n.getRight()).getValue()));
    }

    public InterpreterResult<Object> visitMulNode(MulNode n) {
        return new InterpreterResult<>(((double)visit(n.getLeft()).getValue()) * ((double)visit(n.getRight()).getValue()));
    }

    public InterpreterResult<Object> visitDivNode(DivNode n) {
        return new InterpreterResult<>(((double)visit(n.getLeft()).getValue()) - ((double)visit(n.getRight()).getValue()));
    }

    public InterpreterResult<Object> visitPowNode(PowNode n) {
        return new InterpreterResult<>(Math.pow((double)visit(n.getLeft()).getValue(), (double)visit(n.getRight()).getValue()));
    }

    public InterpreterResult<Object> visitVariableDeclarationNode(VariableDeclarationNode n) {
        if(!this.variables.declare(n.getName(), VariableType.ANY)) throw new Error("Variable is already defined");
        if(n.getAssignment() != null) return visitVariableAssignmentNode(n.getAssignment());
        else return new InterpreterResult<>(null);
    }

    public InterpreterResult<Object> visitVariableAssignmentNode(VariableAssignmentNode n) {
        Variable variable = this.variables.get(n.getName());
        InterpreterResult<Object> value = visit(n.getValue());
        if(variable == null) throw new Error("Variable is not declared");
        else variable.setValue(value.getValue());
        return value;
    }

    public InterpreterResult<Object> visitVariableUsageNode(VariableUsageNode n) {
        Variable variable = this.variables.get(n.getName());
        if(variable == null) throw new Error("Variable is not declared");
        else return new InterpreterResult<>(variable.getValue());
    }

    public InterpreterResult<Object> visitEqEqualsNode(LogicalEqEqualsNode n) {
        Object left = visit(n.getLeft()).getValue();
        Object right = visit(n.getRight()).getValue();
        if(left instanceof Boolean) {
            if(right instanceof Boolean) return new InterpreterResult<>((boolean) left == (boolean) right);
            return new InterpreterResult<>((boolean) left == ((double) right == 0));
        }
        if(right instanceof Boolean) return new InterpreterResult<>(((double) left == 0) == ((boolean) right));
        return new InterpreterResult<>((double) visit(n.getLeft()).getValue() == (double) visit(n.getRight()).getValue());
    }

    public InterpreterResult<Object> visitBiggerEqualsNode(LogicalBiggerEqualsNode n) {
        return new InterpreterResult<>((double) visit(n.getLeft()).getValue() >= (double) visit(n.getRight()).getValue());
    }

    public InterpreterResult<Object> visitSmallerEqualsNode(LogicalSmallerEqualsNode n) {
        return new InterpreterResult<>((double) visit(n.getLeft()).getValue() <= (double) visit(n.getRight()).getValue());
    }

    public InterpreterResult<Object> visitBiggerNode(LogicalBiggerNode n) {
        return new InterpreterResult<>((double) visit(n.getLeft()).getValue() > (double) visit(n.getRight()).getValue());
    }

    public InterpreterResult<Object> visitSmallerNode(LogicalSmallerNode n) {
        return new InterpreterResult<>((double) visit(n.getLeft()).getValue() < (double) visit(n.getRight()).getValue());
    }

    public InterpreterResult<Object> visitLogicalAndNode(LogicalAndNode n) {
        return new InterpreterResult<>((boolean) visit(n.getLeft()).getValue() && (boolean) visit(n.getRight()).getValue());
    }

    public InterpreterResult<Object> visitLogicalOrNode(LogicalOrNode n) {
        return new InterpreterResult<>((boolean) visit(n.getLeft()).getValue() || (boolean) visit(n.getRight()).getValue());
    }

    public InterpreterResult<Object> visitWhileNode(WhileNode n) {
        while((boolean) visit(n.getCondition()).getValue()) {
            visit(n.getBody());
        }
        return new InterpreterResult<>(null);
    }
}
