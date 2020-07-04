package com.github.nsc.de.compiler.interpreter;

import com.github.nsc.de.compiler.parser.node.*;
import com.github.nsc.de.compiler.parser.node.expression.*;
import com.github.nsc.de.compiler.parser.node.functions.FunctionCallNode;
import com.github.nsc.de.compiler.parser.node.functions.FunctionDeclarationNode;
import com.github.nsc.de.compiler.parser.node.logical.*;
import com.github.nsc.de.compiler.parser.node.loops.DoWhileNode;
import com.github.nsc.de.compiler.parser.node.loops.ForNode;
import com.github.nsc.de.compiler.parser.node.loops.WhileNode;
import com.github.nsc.de.compiler.parser.node.variables.*;


public class Interpreter {

    private final Scope global;

    public Interpreter() {
        this.global = new Scope(null, DefaultFunctions.getFunctions(this));
    }

    public InterpreterResult<Object> visit(Node n) {
        return visit(n, this.global);
    }

    public InterpreterResult<Object> visit(Node n, Scope scope) {

        if(n instanceof Tree) return visitTree((Tree) n, scope);
        if(n instanceof NumberNode) return visitNumberNode((NumberNode) n, scope);
        if(n instanceof AddNode) return visitAddNode((AddNode) n, scope);
        if(n instanceof SubNode) return visitSubNode((SubNode) n, scope);
        if(n instanceof MulNode) return visitMulNode((MulNode) n, scope);
        if(n instanceof DivNode) return visitDivNode((DivNode) n, scope);
        if(n instanceof PowNode) return visitPowNode((PowNode) n, scope);
        if(n instanceof VariableDeclarationNode) return visitVariableDeclarationNode((VariableDeclarationNode) n, scope);
        if(n instanceof VariableAddAssignmentNode) return visitVariableAddAssignmentNode((VariableAddAssignmentNode) n, scope);
        if(n instanceof VariableSubAssignmentNode) return visitVariableSubAssignmentNode((VariableSubAssignmentNode) n, scope);
        if(n instanceof VariableMulAssignmentNode) return visitVariableMulAssignmentNode((VariableMulAssignmentNode) n, scope);
        if(n instanceof VariableDivAssignmentNode) return visitVariableDivAssignmentNode((VariableDivAssignmentNode) n, scope);
        if(n instanceof VariablePowAssignmentNode) return visitVariablePowAssignmentNode((VariablePowAssignmentNode) n, scope);
        if(n instanceof VariableIncreaseNode) return visitVariableIncreaseNode((VariableIncreaseNode) n, scope);
        if(n instanceof VariableDecreaseNode) return visitVariableDecreaseNode((VariableDecreaseNode) n, scope);
        if(n instanceof VariableAssignmentNode) return visitVariableAssignmentNode((VariableAssignmentNode) n, scope);
        if(n instanceof VariableUsageNode) return visitVariableUsageNode((VariableUsageNode) n, scope);
        if(n instanceof LogicalEqEqualsNode) return visitEqEqualsNode((LogicalEqEqualsNode) n, scope);
        if(n instanceof LogicalBiggerEqualsNode) return visitBiggerEqualsNode((LogicalBiggerEqualsNode) n, scope);
        if(n instanceof LogicalSmallerEqualsNode) return visitSmallerEqualsNode((LogicalSmallerEqualsNode) n, scope);
        if(n instanceof LogicalBiggerNode) return visitBiggerNode((LogicalBiggerNode) n, scope);
        if(n instanceof LogicalSmallerNode) return visitSmallerNode((LogicalSmallerNode) n, scope);
        if(n instanceof LogicalAndNode) return visitLogicalAndNode((LogicalAndNode) n, scope);
        if(n instanceof LogicalOrNode) return visitLogicalOrNode((LogicalOrNode) n, scope);
        if(n instanceof WhileNode) return visitWhileNode((WhileNode) n, scope);
        if(n instanceof DoWhileNode) return visitDoWhileNode((DoWhileNode) n, scope);
        if(n instanceof ForNode) return visitForNode((ForNode) n, scope);
        if(n instanceof IfNode) return visitIfNode((IfNode) n, scope);
        if(n instanceof FunctionDeclarationNode) return visitFunctionDeclarationNode((FunctionDeclarationNode) n, scope);
        if(n instanceof FunctionCallNode) return visitFunctionCallNode((FunctionCallNode) n, scope);
        if(n instanceof LogicalTrueNode) return new InterpreterResult<>(true);
        if(n instanceof LogicalFalseNode) return new InterpreterResult<>(false);
        if(n == null) return new InterpreterResult<>(null);
        throw new Error("It looks like that Node is not implemented in the Interpreter");

    }

    public InterpreterResult<Object> visitTree(Tree t, Scope scope) {
        for (int i = 0; i < t.getChildren().length - 1; i++) visit(t.getChildren()[i], scope);
        if(t.getChildren().length > 0) return visit(t.getChildren()[t.getChildren().length-1], scope);
        else return new InterpreterResult<>(null);
    }

    public InterpreterResult<Object> visitNumberNode(NumberNode n, Scope scope) {
        return new InterpreterResult<>(n.getNumber());
    }

    public InterpreterResult<Object> visitAddNode(AddNode n, Scope scope) {
        return new InterpreterResult<>(((double) visit(n.getLeft(), scope).getValue()) + ((double)visit(n.getRight(), scope).getValue()));
    }

    public InterpreterResult<Object> visitSubNode(SubNode n, Scope scope) {
        return new InterpreterResult<>(((double)visit(n.getLeft(), scope).getValue()) - ((double)visit(n.getRight(), scope).getValue()));
    }

    public InterpreterResult<Object> visitMulNode(MulNode n, Scope scope) {
        return new InterpreterResult<>(((double)visit(n.getLeft(), scope).getValue()) * ((double)visit(n.getRight(), scope).getValue()));
    }

    public InterpreterResult<Object> visitDivNode(DivNode n, Scope scope) {
        return new InterpreterResult<>(((double)visit(n.getLeft(), scope).getValue()) - ((double)visit(n.getRight(), scope).getValue()));
    }

    public InterpreterResult<Object> visitPowNode(PowNode n, Scope scope) {
        return new InterpreterResult<>(Math.pow((double)visit(n.getLeft(), scope).getValue(), (double)visit(n.getRight(), scope).getValue()));
    }

    public InterpreterResult<Object> visitVariableDeclarationNode(VariableDeclarationNode n, Scope scope) {
        if(!scope.getScopeVariables().declare(n.getName(), VariableType.ANY)) throw new Error("Variable is already defined");
        if(n.getAssignment() != null) return visitVariableAssignmentNode(n.getAssignment(), scope);
        else return new InterpreterResult<>(null);
    }

    public InterpreterResult<Object> visitVariableAssignmentNode(VariableAssignmentNode n, Scope scope) {
        Variable variable = scope.getVariables().get(n.getName());
        InterpreterResult<Object> value = visit(n.getValue(), scope);
        if(variable == null) throw new Error("Variable is not declared");
        else variable.setValue(value.getValue());
        return value;
    }

    public InterpreterResult<Object> visitVariableAddAssignmentNode(VariableAddAssignmentNode n, Scope scope) {
        Variable variable = scope.getVariables().get(n.getName());
        InterpreterResult<Object> value = visit(n.getValue(), scope);
        if(variable == null) throw new Error("Variable is not declared");
        else variable.setValue((double) variable.getValue() + (double) value.getValue());
        return new InterpreterResult(variable.getValue());
    }

    public InterpreterResult<Object> visitVariableSubAssignmentNode(VariableSubAssignmentNode n, Scope scope) {
        Variable variable = scope.getVariables().get(n.getName());
        InterpreterResult<Object> value = visit(n.getValue(), scope);
        if(variable == null) throw new Error("Variable is not declared");
        else variable.setValue((double) variable.getValue() - (double) value.getValue());
        return new InterpreterResult(variable.getValue());
    }

    public InterpreterResult<Object> visitVariableMulAssignmentNode(VariableMulAssignmentNode n, Scope scope) {
        Variable variable = scope.getVariables().get(n.getName());
        InterpreterResult<Object> value = visit(n.getValue(), scope);
        if(variable == null) throw new Error("Variable is not declared");
        else variable.setValue((double) variable.getValue() * (double) value.getValue());
        return new InterpreterResult(variable.getValue());
    }

    public InterpreterResult<Object> visitVariableDivAssignmentNode(VariableDivAssignmentNode n, Scope scope) {
        Variable variable = scope.getVariables().get(n.getName());
        InterpreterResult<Object> value = visit(n.getValue(), scope);
        if(variable == null) throw new Error("Variable is not declared");
        else variable.setValue((double) variable.getValue() / (double) value.getValue());
        return new InterpreterResult(variable.getValue());
    }

    public InterpreterResult<Object> visitVariablePowAssignmentNode(VariablePowAssignmentNode n, Scope scope) {
        Variable variable = scope.getVariables().get(n.getName());
        InterpreterResult<Object> value = visit(n.getValue(), scope);
        if(variable == null) throw new Error("Variable is not declared");
        else variable.setValue(Math.pow((double) variable.getValue(), (double) value.getValue()));
        return new InterpreterResult(variable.getValue());
    }

    public InterpreterResult<Object> visitVariableIncreaseNode(VariableIncreaseNode n, Scope scope) {
        Variable variable = scope.getVariables().get(n.getName());
        if(variable == null) throw new Error("Variable is not declared");
        else variable.setValue((double) variable.getValue() + 1);
        return new InterpreterResult(variable.getValue());
    }

    public InterpreterResult<Object> visitVariableDecreaseNode(VariableDecreaseNode n, Scope scope) {
        Variable variable = scope.getVariables().get(n.getName());
        if(variable == null) throw new Error("Variable is not declared");
        else variable.setValue((double) variable.getValue() - 1);
        return new InterpreterResult(variable.getValue());
    }

    public InterpreterResult<Object> visitVariableUsageNode(VariableUsageNode n, Scope scope) {
        Variable variable = scope.getVariables().get(n.getName());
        if(variable == null) throw new Error("Variable is not declared");
        else return new InterpreterResult<>(variable.getValue());
    }

    public InterpreterResult<Object> visitEqEqualsNode(LogicalEqEqualsNode n, Scope scope) {
        Object left = visit(n.getLeft(), scope).getValue();
        Object right = visit(n.getRight(), scope).getValue();
        if(left instanceof Boolean) {
            if(right instanceof Boolean) return new InterpreterResult<>((boolean) left == (boolean) right);
            return new InterpreterResult<>((boolean) left == ((double) right == 0));
        }
        if(right instanceof Boolean) return new InterpreterResult<>(((double) left == 0) == ((boolean) right));
        return new InterpreterResult<>((double) visit(n.getLeft(), scope).getValue() == (double) visit(n.getRight(), scope).getValue());
    }

    public InterpreterResult<Object> visitBiggerEqualsNode(LogicalBiggerEqualsNode n, Scope scope) {
        return new InterpreterResult<>((double) visit(n.getLeft(), scope).getValue() >= (double) visit(n.getRight(), scope).getValue());
    }

    public InterpreterResult<Object> visitSmallerEqualsNode(LogicalSmallerEqualsNode n, Scope scope) {
        return new InterpreterResult<>((double) visit(n.getLeft(), scope).getValue() <= (double) visit(n.getRight(), scope).getValue());
    }

    public InterpreterResult<Object> visitBiggerNode(LogicalBiggerNode n, Scope scope) {
        return new InterpreterResult<>((double) visit(n.getLeft(), scope).getValue() > (double) visit(n.getRight(), scope).getValue());
    }

    public InterpreterResult<Object> visitSmallerNode(LogicalSmallerNode n, Scope scope) {
        return new InterpreterResult<>((double) visit(n.getLeft(), scope).getValue() < (double) visit(n.getRight(), scope).getValue());
    }

    public InterpreterResult<Object> visitLogicalAndNode(LogicalAndNode n, Scope scope) {
        return new InterpreterResult<>((boolean) visit(n.getLeft(), scope).getValue() && (boolean) visit(n.getRight(), scope).getValue());
    }

    public InterpreterResult<Object> visitLogicalOrNode(LogicalOrNode n, Scope scope) {
        return new InterpreterResult<>((boolean) visit(n.getLeft(), scope).getValue() || (boolean) visit(n.getRight(), scope).getValue());
    }

    public InterpreterResult<Object> visitWhileNode(WhileNode n, Scope scope) {

        while(this.toBoolean(visit(n.getCondition(), scope).getValue())) {

            Scope whileScope = scope.copy();
            visit(n.getBody(), whileScope);

        }
        return new InterpreterResult<>(null);
    }

    public InterpreterResult<Object> visitDoWhileNode(DoWhileNode n, Scope scope) {

        do {

            Scope doWhileScope = scope.copy();
            visit(n.getBody(), doWhileScope);

        } while(this.toBoolean(visit(n.getCondition(), scope)));

        return new InterpreterResult<>(null);
    }

    public InterpreterResult<Object> visitForNode(ForNode n, Scope scope) {

        Scope forOuterScope = scope.copy();

        visit(n.getDeclaration(), forOuterScope);
        while(toBoolean(visit(n.getCondition(), forOuterScope))) {

            Scope forInnerScope = forOuterScope.copy();
            visit(n.getBody(), forInnerScope);
            visit(n.getRound(), forOuterScope);

        }
        return new InterpreterResult<>(null);
    }

    public InterpreterResult<Object> visitIfNode(IfNode n, Scope scope) {

        Scope ifScope = scope.copy();

        boolean result = (boolean) visit(n.getCondition(), ifScope).getValue();
        if(result) visit(n.getBody(), ifScope);
        else if(n.getElseBody() != null) visit(n.getElseBody(), ifScope);
        return new InterpreterResult<>(null);
    }

    public InterpreterResult<Object> visitFunctionDeclarationNode(FunctionDeclarationNode node, Scope scope) {

        if(!scope.getVariables().declare(node.getName(), VariableType.FUNCTION)) throw new Error("'" + node.getName() + "' is already declared!");
        Function f = new Function(node.getArgs(), node.getBody(), scope, this);
        scope.getVariables().get(node.getName()).setValue(f);
        return new InterpreterResult<>(f);

    }

    public InterpreterResult<Object> visitFunctionCallNode(FunctionCallNode node, Scope scope) {
        Variable variable = scope.getVariables().get(node.getName());
        if(variable == null) throw new Error("Function '" + node.getName() + "' is not declared");
        else if(variable.getType() != VariableType.FUNCTION) throw new Error("Function '" + node.getName() + "' is not a function");
        else ((Function) variable.getValue()).call(node, scope);
        return new InterpreterResult<>(null);
    }

    private boolean toBoolean(Object o) {

        if(o instanceof InterpreterResult) return toBoolean(((InterpreterResult) o).getValue());
        if(o instanceof Boolean) return (boolean) o;
        if(o instanceof Integer) return ((int) o) != 0;
        if(o instanceof Float) return ((float) o) != 0.0f;
        if(o instanceof Double) return ((double) o) != 0.0;
        if(o instanceof Long) return ((long) o) != 0;
        if(o instanceof Byte) return ((byte) o) != 0;
        return o != null;

    }
}
