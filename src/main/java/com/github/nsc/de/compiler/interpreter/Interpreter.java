package com.github.nsc.de.compiler.interpreter;

import com.github.nsc.de.compiler.interpreter.values.*;
import com.github.nsc.de.compiler.interpreter.values.Class;
import com.github.nsc.de.compiler.interpreter.values.VariableType;
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


public class Interpreter {

    private final Scope global;

    public Interpreter() {
        this.global = new Scope(null, DefaultFunctions.getFunctions(this));
    }

    public InterpreterValue visit(Node n) {
        return visit(n, this.global);
    }

    public InterpreterValue visit(Node n, Scope scope) {

        if(n instanceof Tree) return visitTree((Tree) n, scope);
        if(n instanceof DoubleNode) return visitDoubleNode((DoubleNode) n);
        if(n instanceof IntegerNode) return visitIntegerNode((IntegerNode) n);
        if(n instanceof AddNode) return visitAddNode((AddNode) n, scope);
        if(n instanceof SubNode) return visitSubNode((SubNode) n, scope);
        if(n instanceof MulNode) return visitMulNode((MulNode) n, scope);
        if(n instanceof DivNode) return visitDivNode((DivNode) n, scope);
        if(n instanceof ModNode) return visitModNode((ModNode) n, scope);
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
        if(n instanceof IdentifierNode) return visitIdentifier((IdentifierNode) n, scope);
        if(n instanceof ClassConstructionNode) return visitClassConstruction((ClassConstructionNode) n, scope);
        if(n instanceof LogicalTrueNode) return BooleanValue.TRUE;
        if(n instanceof LogicalFalseNode) return BooleanValue.FALSE;
        if(n instanceof ClassDeclarationNode) return visitClassDeclarationNode((ClassDeclarationNode) n, scope);
        if(n == null) return NullValue.NULL;
        throw new Error("It looks like that Node is not implemented in the Interpreter");

    }

    public InterpreterValue visitTree(Tree t, Scope scope) {
        for (int i = 0; i < t.getChildren().length - 1; i++) visit(t.getChildren()[i], scope);
        if(t.getChildren().length > 0) return visit(t.getChildren()[t.getChildren().length-1], scope);
        else return NullValue.NULL;
    }

    public IntegerValue visitIntegerNode(IntegerNode n) {
        return new IntegerValue(n.getNumber());
    }

    public DoubleValue visitDoubleNode(DoubleNode n) {
        return new DoubleValue(n.getNumber());
    }




    public InterpreterValue visitAddNode(AddNode n, Scope scope) {
        return visit(n.getLeft(), scope).add(visit(n.getRight(), scope));
    }

    public InterpreterValue visitSubNode(SubNode n, Scope scope) {
        return visit(n.getLeft(), scope).sub(visit(n.getRight(), scope));
    }

    public InterpreterValue visitMulNode(MulNode n, Scope scope) {
        return visit(n.getLeft(), scope).mul(visit(n.getRight(), scope));
    }

    public InterpreterValue visitDivNode(DivNode n, Scope scope) {
        return visit(n.getLeft(), scope).div(visit(n.getRight(), scope));
    }

    public InterpreterValue visitModNode(ModNode n, Scope scope) {
        return visit(n.getLeft(), scope).mod(visit(n.getRight(), scope));
    }

    public InterpreterValue visitPowNode(PowNode n, Scope scope) {
        return visit(n.getLeft(), scope).pow(visit(n.getRight(), scope));
    }




    public InterpreterValue visitVariableDeclarationNode(VariableDeclarationNode n, Scope scope) {
        if(!scope.getScopeVariables().declare(n.getName(), VariableType.valueOf(n.getType()))) throw new Error("Variable is already defined");
        if(n.getAssignment() != null) return visitVariableAssignmentNode(n.getAssignment(), scope);
        else return NullValue.NULL;
    }

    public InterpreterValue visitVariableAssignmentNode(VariableAssignmentNode n, Scope scope) {
        Variable variable = (Variable) visit(n.getVariable(), scope);
        InterpreterValue value = visit(n.getValue(), scope);
        variable.setValue(value);
        return value;
    }

    public InterpreterValue visitVariableAddAssignmentNode(VariableAddAssignmentNode n, Scope scope) {
        Variable variable = (Variable) visit(n.getVariable(), scope);
        InterpreterValue value = visit(n.getValue(), scope);
        variable.setValue(variable.getValue().add(value));
        return variable.getValue();
    }

    public InterpreterValue visitVariableSubAssignmentNode(VariableSubAssignmentNode n, Scope scope) {
        Variable variable = (Variable) visit(n.getVariable(), scope);
        InterpreterValue value = visit(n.getValue(), scope);
        variable.setValue(variable.getValue().sub(value));
        return variable.getValue();
    }

    public InterpreterValue visitVariableMulAssignmentNode(VariableMulAssignmentNode n, Scope scope) {
        Variable variable = (Variable) visit(n.getVariable(), scope);
        InterpreterValue value = visit(n.getValue(), scope);
        variable.setValue(variable.getValue().mul(value));
        return variable.getValue();
    }

    public InterpreterValue visitVariableDivAssignmentNode(VariableDivAssignmentNode n, Scope scope) {
        Variable variable = (Variable) visit(n.getVariable(), scope);
        InterpreterValue value = visit(n.getValue(), scope);
        variable.setValue(variable.getValue().div(value));
        return variable.getValue();
    }

    public InterpreterValue visitVariablePowAssignmentNode(VariablePowAssignmentNode n, Scope scope) {
        Variable variable = (Variable) visit(n.getVariable(), scope);
        InterpreterValue value = visit(n.getValue(), scope);
        variable.setValue(variable.getValue().pow(value));
        return variable.getValue();
    }

    public InterpreterValue visitVariableIncreaseNode(VariableIncreaseNode n, Scope scope) {
        Variable variable = (Variable) visit(n.getVariable(), scope);
        variable.setValue(variable.getValue().add(IntegerValue.ONE));
        return variable.getValue();
    }

    public InterpreterValue visitVariableDecreaseNode(VariableDecreaseNode n, Scope scope) {
        Variable variable = (Variable) visit(n.getVariable(), scope);
        variable.setValue(variable.getValue().sub(IntegerValue.ONE));
        return variable.getValue();
    }

    public InterpreterValue visitVariableUsageNode(VariableUsageNode n, Scope scope) {
        return (visitIdentifier(n.getVariable(), scope)).getValue();
    }

    public InterpreterValue visitEqEqualsNode(LogicalEqEqualsNode n, Scope scope) {
        return visit(n.getLeft(), scope).equals(visit(n.getRight(), scope));
    }

    public InterpreterValue visitBiggerEqualsNode(LogicalBiggerEqualsNode n, Scope scope) {
        return visit(n.getLeft(), scope).bigger_equals(visit(n.getRight(), scope));
    }

    public InterpreterValue visitSmallerEqualsNode(LogicalSmallerEqualsNode n, Scope scope) {
        return visit(n.getLeft(), scope).smaller_equals(visit(n.getRight(), scope));
    }

    public InterpreterValue visitBiggerNode(LogicalBiggerNode n, Scope scope) {
        return visit(n.getLeft(), scope).bigger(visit(n.getRight(), scope));
    }

    public InterpreterValue visitSmallerNode(LogicalSmallerNode n, Scope scope) {
        return visit(n.getLeft(), scope).smaller(visit(n.getRight(), scope));
    }

    public InterpreterValue visitLogicalAndNode(LogicalAndNode n, Scope scope) {
        return visit(n.getLeft(), scope).and(visit(n.getRight(), scope));
    }

    public InterpreterValue visitLogicalOrNode(LogicalOrNode n, Scope scope) {
        return visit(n.getLeft(), scope).or(visit(n.getRight(), scope));
    }

    public InterpreterValue visitWhileNode(WhileNode n, Scope scope) {

        while(BooleanValue.from(visit(n.getCondition(), scope)).getValue()) {

            Scope whileScope = scope.copy();
            visit(n.getBody(), whileScope);

        }
        return NullValue.NULL;
    }

    public InterpreterValue visitDoWhileNode(DoWhileNode n, Scope scope) {

        do {

            Scope doWhileScope = scope.copy();
            visit(n.getBody(), doWhileScope);

        } while(BooleanValue.from(visit(n.getCondition(), scope)).getValue());

        return NullValue.NULL;
    }

    public InterpreterValue visitForNode(ForNode n, Scope scope) {

        Scope forOuterScope = scope.copy();

        visit(n.getDeclaration(), forOuterScope);

        while(BooleanValue.from(visit(n.getCondition(), forOuterScope)).getValue()) {

            Scope forInnerScope = forOuterScope.copy();
            visit(n.getBody(), forInnerScope);
            visit(n.getRound(), forOuterScope);

        }

        return NullValue.NULL;
    }

    public InterpreterValue visitIfNode(IfNode n, Scope scope) {

        Scope ifScope = scope.copy();

        if(BooleanValue.from(visit(n.getCondition(), ifScope)).getValue()) return visit(n.getBody(), ifScope);
        else if(n.getElseBody() != null) return visit(n.getElseBody(), ifScope);

        return NullValue.NULL;

    }

    public Function visitFunctionDeclarationNode(FunctionDeclarationNode node, Scope scope) {

        if(!scope.getVariables().declare(node.getName(), Function.class))
            throw new Error("'" + node.getName() + "' is already declared!");
        Function f = createFunctionDeclaration(node, scope);
        scope.getVariables().get(node.getName()).setValue(f);
        return f;

    }

    public Function createFunctionDeclaration(FunctionDeclarationNode node, Scope scope) {

        return new Function(node.getArgs(), node.getBody(), scope, this, node.getAccess(),
                node.isInClass(), node.isStatic(), node.isFinal());

    }



    public InterpreterValue visitFunctionCallNode(FunctionCallNode node, Scope scope) {

        // TODO Type check (function)
        // TODO Return values

        Function f;
        InterpreterValue v = visit(node.getFunction());
        if(v instanceof Function) f = (Function) v;
        else if(v instanceof Variable) f = (Function) ((Variable) v).getValue();
        else throw new Error("Wrong function call");

        f.call(node, scope);
        return NullValue.NULL;

    }

    public Variable visitIdentifier(IdentifierNode node, Scope scope) {

        if(node.getParent() != null) {
            // TODO implement parents
            throw new Error("Not implemented yet!");
        }
        else {
           Variable v = scope.getVariables().get(node.getName());
           if(v == null) throw new Error(String.format("Variable with name \"%s\" is not declared", node.getName()));
           return v;
        }

    }

    public Class visitClassDeclarationNode(ClassDeclarationNode node, Scope scope) {

        if(!scope.getVariables().declare(node.getName(), Function.class))
            throw new Error("'" + node.getName() + "' is already declared!");
        Class f = createClassDeclaration(node, scope);
        scope.getVariables().get(node.getName()).setValue(f);
        return f;

    }

    public Class createClassDeclaration(ClassDeclarationNode n, Scope scope) {

        VariableList prototype = new VariableList();

        // TODO 2 Declarations with the same name
        for(FunctionDeclarationNode node : n.getMethods()) {
            prototype.declare(node.getName(), Function.class);
            prototype.get(node.getName()).setValue(createFunctionDeclaration(node, scope));
        }
        for(ClassDeclarationNode node : n.getClasses()) {
            prototype.declare(node.getName(), Class.class);
            prototype.get(node.getName()).setValue(createClassDeclaration(node, scope));
        }

        Class cls = new Class(n.getName(), n.getFields(), scope, this, prototype,
                n.getAccess(), n.isInClass(), n.isStatic(), n.isFinal());

        scope.getVariables().declare(n.getName(), Class.class);
        scope.getVariables().get(n.getName()).setValue(cls);

        return cls;

    }

    public ObjectValue visitClassConstruction(ClassConstructionNode n, Scope scope) {

        // TODO type check (is really a class?)
        // TODO Arguments for constructor
        InterpreterValue v = visit(n.getType(), scope);
        Class cls;
        if(v instanceof Class) cls = (Class) v;
        else if(v instanceof Variable) cls = (Class) ((Variable) v).getValue();
        else throw new Error("Seems to be not a class");
        return new ObjectValue(cls, scope);

    }


    // TODO implement class usage
}
