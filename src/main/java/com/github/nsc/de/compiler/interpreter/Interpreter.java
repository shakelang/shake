package com.github.nsc.de.compiler.interpreter;

import com.github.nsc.de.compiler.interpreter.values.*;
import com.github.nsc.de.compiler.interpreter.values.Class;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * {@link Interpreter} for executing the code directly
 */
public class Interpreter {



    // *******************************
    // fields

    /**
     * The global {@link Scope} of the interpreter (if you call visit without giving a {@link Scope} as
     * parameter this {@link Scope} will be used
     */
    private final Scope global;



    // *******************************
    // constructors

    /**
     * Constructor for {@link Interpreter}
     *
     * @param global the global {@link Scope} ({@link #global})
     *
     * @author Nicolas Schmidt
     */
    public Interpreter(Scope global) {
        // set the global field
        this.global = global;
    }

    /**
     * Constructor for {@link Interpreter}
     *
     * @author Nicolas Schmidt
     */
    public Interpreter() {
        // set the global scope to a new scope
        this.global = new Scope(null, DefaultFunctions.getFunctions(this));
    }



    // *******************************
    // visit function

    /**
     * Visit a {@link Node} (using the {@link #global} {@link Scope})
     *
     * @param n the {@link Node} to visit
     * @return the resulting {@link InterpreterValue} of the operation
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visit(Node n) {
        // return visit with global as scope argument
        return visit(n, this.global);
    }

    /**
     * Visits the given {@link Node} using the specified {@link Scope}
     *
     * @param n the {@link Node} to visit
     * @param scope the {@link Scope} to use
     * @return the resulting {@link InterpreterValue} of the operation
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visit(Node n, Scope scope) {

        // Check all the node-types and call the function to process it
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
        if(n instanceof VariableModAssignmentNode) return visitVariableModAssignmentNode((VariableModAssignmentNode) n, scope);
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
        if(n instanceof ClassDeclarationNode) return visitClassDeclarationNode((ClassDeclarationNode) n, scope);

        // if the node a LogicalTrueNode return TRUE, if it is a LogicalFalseNode return false
        if(n instanceof LogicalTrueNode) return BooleanValue.TRUE;
        if(n instanceof LogicalFalseNode) return BooleanValue.FALSE;

        // if the node is null return NullValue.NULL
        if(n == null) return NullValue.NULL;

        // Throw an error if the node could not be processed
        throw new Error("It looks like that Node is not implemented in the Interpreter");

    }



    // *******************************
    // visit function

    /**
     * Visit a {@link Tree}
     *
     * @param t the {@link Tree} to visit
     * @param scope the scope to use for visiting the {@link Tree}
     * @return the latest {@link InterpreterValue} of the tree
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitTree(Tree t, Scope scope) {

        // Visit all the children but the last one
        for (int i = 0; i < t.getChildren().length - 1; i++) visit(t.getChildren()[i], scope);

        // Visit the last children (if the amount of children is bigger than 0)
        if(t.getChildren().length > 0) return visit(t.getChildren()[t.getChildren().length-1], scope);

        // This is just reached when the Tree has no children
        // If the number of children is 0 we just return NullValue.NULL
        // as there are no children to process
        return NullValue.NULL;
    }



    // *******************************
    // number nodes

    /**
     * Visit an {@link IntegerNode}
     *
     * @param n the {@link IntegerNode} to process
     * @return the {@link IntegerValue} that is created
     *
     * @author Nicolas Schmidt
     */
    public IntegerValue visitIntegerNode(IntegerNode n) {
        // Just create a IntegerValue from the IntegerNode value and return it
        return new IntegerValue(n.getNumber());
    }

    /**
     * Visit a {@link DoubleNode}
     *
     * @param n the {@link DoubleNode} to process
     * @return the {@link DoubleValue} that is created
     *
     * @author Nicolas Schmidt
     */
    public DoubleValue visitDoubleNode(DoubleNode n) {
        // Just create a DoubleValue from the DoubleNode value and return it
        return new DoubleValue(n.getNumber());
    }



    // *******************************
    // calculations

    /**
     * Visit an {@link AddNode} (Addition)
     *
     * @param n the {@link AddNode} to visit
     * @param scope the {@link Scope} for visiting the {@link AddNode}
     * @return the addition-result
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitAddNode(AddNode n, Scope scope) {
        // visit both sides of the term
        // call the method add() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.getLeft(), scope).add(visit(n.getRight(), scope));
    }

    /**
     * Visit a {@link SubNode} (Subtraction)
     *
     * @param n the {@link SubNode} to visit
     * @param scope the {@link Scope} for visiting the {@link SubNode}
     * @return the subtraction-result
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitSubNode(SubNode n, Scope scope) {
        // visit both sides of the term
        // call the method sub() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.getLeft(), scope).sub(visit(n.getRight(), scope));
    }

    /**
     * Visit a {@link MulNode} (Multiplication)
     *
     * @param n the {@link MulNode} to visit
     * @param scope the {@link Scope} for visiting the {@link MulNode}
     * @return the multiplication-result
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitMulNode(MulNode n, Scope scope) {
        // visit both sides of the term
        // call the method mul() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.getLeft(), scope).mul(visit(n.getRight(), scope));
    }

    /**
     * Visit a {@link DivNode} (Division)
     *
     * @param n the {@link DivNode} to visit
     * @param scope the {@link Scope} for visiting the {@link DivNode}
     * @return the division-result
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitDivNode(DivNode n, Scope scope) {
        // visit both sides of the term
        // call the method div() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.getLeft(), scope).div(visit(n.getRight(), scope));
    }

    /**
     * Visit a {@link ModNode} (Modulo)
     *
     * @param n the {@link ModNode} to visit
     * @param scope the {@link Scope} for visiting the {@link ModNode}
     * @return the modulo-result
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitModNode(ModNode n, Scope scope) {
        // visit both sides of the term
        // call the method mod() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.getLeft(), scope).mod(visit(n.getRight(), scope));
    }

    /**
     * Visit a {@link PowNode} (Modulo)
     *
     * @param n the {@link PowNode} to visit
     * @param scope the {@link Scope} for visiting the {@link PowNode}
     * @return the power-result
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitPowNode(PowNode n, Scope scope) {
        // visit both sides of the term
        // call the method pow() on the result of the left InterpreterValue and
        // give the right result one as argument.
        return visit(n.getLeft(), scope).pow(visit(n.getRight(), scope));
    }



    // *******************************
    // variables

    /**
     * Visit a {@link VariableDeclarationNode}
     *
     * @param n the {@link VariableDeclarationNode} to process
     * @param scope the {@link Scope} for visiting the {@link VariableDeclarationNode}
     * @return If the {@link Variable} gets a value assigned the value that is assigned, if not NULL
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitVariableDeclarationNode(VariableDeclarationNode n, Scope scope) {
        if(!scope.getScopeVariables().declare(Variable.valueOf(n.getName(), n.getType()))) throw new Error("Variable is already defined");
        if(n.getAssignment() != null) return visitVariableAssignmentNode(n.getAssignment(), scope);
        else return NullValue.NULL;
    }

    /**
     * Visit a {@link VariableAssignmentNode}
     *
     * @param n the {@link VariableAssignmentNode} to visit
     * @param scope the {@link Scope} for visiting the {@link VariableAssignmentNode}
     * @return The value that is assigned to the variable
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitVariableAssignmentNode(VariableAssignmentNode n, Scope scope) {
        Variable variable = (Variable) visit(n.getVariable(), scope);
        InterpreterValue value = visit(n.getValue(), scope);
        variable.setValue(value);
        return value;
    }

    /**
     * Visit a {@link VariableAddAssignmentNode}
     *
     * @param n the {@link VariableAddAssignmentNode} to visit
     * @param scope the {@link Scope} for visiting the {@link VariableAddAssignmentNode}
     * @return The value that is assigned to the variable (So the old variable value plus the value given.)
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitVariableAddAssignmentNode(VariableAddAssignmentNode n, Scope scope) {
        Variable variable = (Variable) visit(n.getVariable(), scope);
        InterpreterValue value = visit(n.getValue(), scope);
        variable.setValue(variable.getValue().add(value));
        return variable.getValue();
    }

    /**
     * Visit a {@link VariableSubAssignmentNode}
     *
     * @param n the {@link VariableSubAssignmentNode} to visit
     * @param scope the {@link Scope} for visiting the {@link VariableSubAssignmentNode}
     * @return The value that is assigned to the variable (So the old variable value minus the value given.)
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitVariableSubAssignmentNode(VariableSubAssignmentNode n, Scope scope) {
        Variable variable = (Variable) visit(n.getVariable(), scope);
        InterpreterValue value = visit(n.getValue(), scope);
        variable.setValue(variable.getValue().sub(value));
        return variable.getValue();
    }

    /**
     * Visit a {@link VariableMulAssignmentNode}
     *
     * @param n the {@link VariableMulAssignmentNode} to visit
     * @param scope the {@link Scope} for visiting the {@link VariableMulAssignmentNode}
     * @return The value that is assigned to the variable (So the old variable value times the value given.)
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitVariableMulAssignmentNode(VariableMulAssignmentNode n, Scope scope) {
        Variable variable = (Variable) visit(n.getVariable(), scope);
        InterpreterValue value = visit(n.getValue(), scope);
        variable.setValue(variable.getValue().mul(value));
        return variable.getValue();
    }

    /**
     * Visit a {@link VariableDivAssignmentNode}
     *
     * @param n the {@link VariableDivAssignmentNode} to visit
     * @param scope the {@link Scope} for visiting the {@link VariableDivAssignmentNode}
     * @return The value that is assigned to the variable (So the old variable value by the value given.)
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitVariableDivAssignmentNode(VariableDivAssignmentNode n, Scope scope) {
        Variable variable = (Variable) visit(n.getVariable(), scope);
        InterpreterValue value = visit(n.getValue(), scope);
        variable.setValue(variable.getValue().div(value));
        return variable.getValue();
    }

    /**
     * Visit a {@link VariableModAssignmentNode}
     *
     * @param n the {@link VariableModAssignmentNode} to visit
     * @param scope the {@link Scope} for visiting the {@link VariableModAssignmentNode}
     * @return The value that is assigned to the variable (So the old variable value modulo the value given.)
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitVariableModAssignmentNode(VariableModAssignmentNode n, Scope scope) {
        Variable variable = (Variable) visit(n.getVariable(), scope);
        InterpreterValue value = visit(n.getValue(), scope);
        variable.setValue(variable.getValue().mod(value));
        return variable.getValue();
    }

    /**
     * Visit a {@link VariablePowAssignmentNode}
     *
     * @param n the {@link VariablePowAssignmentNode} to visit
     * @param scope the {@link Scope} for visiting the {@link VariablePowAssignmentNode}
     * @return The value that is assigned to the variable (So the old variable value power the value given.)
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitVariablePowAssignmentNode(VariablePowAssignmentNode n, Scope scope) {
        Variable variable = (Variable) visit(n.getVariable(), scope);
        InterpreterValue value = visit(n.getValue(), scope);
        variable.setValue(variable.getValue().pow(value));
        return variable.getValue();
    }

    /**
     * Visit a {@link VariableIncreaseNode}
     *
     * @param n the {@link VariableIncreaseNode} to visit
     * @param scope the {@link Scope} for visiting the {@link VariableIncreaseNode}
     * @return The old value of the Variable
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitVariableIncreaseNode(VariableIncreaseNode n, Scope scope) {
        Variable variable = (Variable) visit(n.getVariable(), scope);
        InterpreterValue v = variable.getValue();
        variable.setValue(v.add(IntegerValue.ONE));
        return v;
    }

    /**
     * Visit a {@link VariableDecreaseNode}
     *
     * @param n the {@link VariableDecreaseNode} to visit
     * @param scope the {@link Scope} for visiting the {@link VariableDecreaseNode}
     * @return The old value of the Variable
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitVariableDecreaseNode(VariableDecreaseNode n, Scope scope) {
        Variable variable = (Variable) visit(n.getVariable(), scope);
        InterpreterValue v = variable.getValue();
        variable.setValue(v.sub(IntegerValue.ONE));
        return v;
    }

    /**
     * Visit a {@link VariableUsageNode}
     *
     * @param n the {@link VariableUsageNode} to visit
     * @param scope the {@link Scope} for visiting the {@link VariableUsageNode}
     * @return The value of the {@link Variable}
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitVariableUsageNode(VariableUsageNode n, Scope scope) {
        return (visitIdentifier(n.getVariable(), scope)).getValue();
    }



    // *******************************
    // variables

    /**
     * Visit an {@link LogicalEqEqualsNode}
     *
     * @param n the {@link LogicalEqEqualsNode} to visit
     * @param scope the {@link Scope} for visiting the {@link LogicalEqEqualsNode}
     * @return are the two sides of the {@link LogicalEqEqualsNode} the same?
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitEqEqualsNode(LogicalEqEqualsNode n, Scope scope) {
        // visit both sides of the term
        // call the method equals() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.getLeft(), scope).equals(visit(n.getRight(), scope));
    }

    /**
     * Visit an {@link LogicalBiggerEqualsNode}
     *
     * @param n the {@link LogicalBiggerEqualsNode} to visit
     * @param scope the {@link Scope} for visiting the {@link LogicalBiggerEqualsNode}
     * @return is the left side of the {@link LogicalBiggerEqualsNode} bigger or equal to the right side
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitBiggerEqualsNode(LogicalBiggerEqualsNode n, Scope scope) {
        // visit both sides of the term
        // call the method bigger_equals() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.getLeft(), scope).bigger_equals(visit(n.getRight(), scope));
    }

    /**
     * Visit an {@link LogicalSmallerEqualsNode}
     *
     * @param n the {@link LogicalSmallerEqualsNode} to visit
     * @param scope the {@link Scope} for visiting the {@link LogicalSmallerEqualsNode}
     * @return is the left side of the {@link LogicalSmallerEqualsNode} smaller or equal to the right side
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitSmallerEqualsNode(LogicalSmallerEqualsNode n, Scope scope) {
        // visit both sides of the term
        // call the method smaller_equals() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.getLeft(), scope).smaller_equals(visit(n.getRight(), scope));
    }

    /**
     * Visit an {@link LogicalBiggerEqualsNode}
     *
     * @param n the {@link LogicalBiggerEqualsNode} to visit
     * @param scope the {@link Scope} for visiting the {@link LogicalBiggerEqualsNode}
     * @return is the left side of the {@link LogicalBiggerEqualsNode} bigger than the right side
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitBiggerNode(LogicalBiggerNode n, Scope scope) {
        // visit both sides of the term
        // call the method bigger() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.getLeft(), scope).bigger(visit(n.getRight(), scope));
    }

    /**
     * Visit an {@link LogicalSmallerEqualsNode}
     *
     * @param n the {@link LogicalSmallerEqualsNode} to visit
     * @param scope the {@link Scope} for visiting the {@link LogicalSmallerEqualsNode}
     * @return is the left side of the {@link LogicalSmallerEqualsNode} bigger than the right side
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitSmallerNode(LogicalSmallerNode n, Scope scope) {
        // visit both sides of the term
        // call the method smaller() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.getLeft(), scope).smaller(visit(n.getRight(), scope));
    }



    // *******************************
    // logical concatenation

    /**
     * Visit an {@link LogicalAndNode}
     *
     * @param n the {@link LogicalAndNode} to visit
     * @param scope the {@link Scope} for visiting the {@link LogicalAndNode}
     * @return is at least one of the two sides true
     *
     * @author Nicolas Schmidt
     */
    public InterpreterValue visitLogicalAndNode(LogicalAndNode n, Scope scope) {
        return visit(n.getLeft(), scope).and(visit(n.getRight(), scope));
    }

    /**
     * Visit an {@link LogicalOrNode}
     *
     * @param n the {@link LogicalOrNode} to visit
     * @param scope the {@link Scope} for visiting the {@link LogicalOrNode}
     * @return are both sides true
     *
     * @author Nicolas Schmidt
     */
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

        if(!scope.getVariables().declare(new Variable<Function>(node.getName())))
            throw new Error("'" + node.getName() + "' is already declared!");
        Function f = createFunctionDeclaration(node, scope);
        scope.getVariables().get(node.getName()).setValue(f);
        return f;

    }

    public Function createFunctionDeclaration(FunctionDeclarationNode node, Scope scope) {

        return new Function(node.getArgs(), node.getBody(), scope, this, node.getAccess(), node.isFinal());

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

            InterpreterValue parent = visit(node.getParent(), scope);
            return parent.getChild(node.getName());

        }
        else {

           Variable v = scope.getVariables().get(node.getName());
           if(v == null) throw new Error(String.format("Variable with name \"%s\" is not declared", node.getName()));
           return v;

        }

    }

    public Class visitClassDeclarationNode(ClassDeclarationNode node, Scope scope) {

        if(!scope.getVariables().declare(new Variable<Function>(node.getName())))
            throw new Error("'" + node.getName() + "' is already declared!");
        Class f = createClassDeclaration(node, scope);
        scope.getVariables().get(node.getName()).setValue(f);
        return f;

    }

    public Class createClassDeclaration(ClassDeclarationNode n, Scope scope) {

        List<VariableDeclarationNode> fields = new ArrayList<>(Arrays.asList(n.getFields()));
        VariableList prototype = new VariableList();
        VariableList statics = new VariableList();

        // TODO 2 Declarations with the same name
        for(FunctionDeclarationNode node : n.getMethods()) {
            if(n.isStatic()) {
                statics.declare(new Variable<Function>(node.getName()));
                statics.get(node.getName()).setValue(createFunctionDeclaration(node, scope));
            }
            else {
                prototype.declare(new Variable<Function>(node.getName()));
                prototype.get(node.getName()).setValue(createFunctionDeclaration(node, scope));
            }
        }

        for(ClassDeclarationNode node : n.getClasses()) {
            if(n.isStatic()) {
                statics.declare(new Variable<Class>(node.getName()));
                statics.get(node.getName()).setValue(createClassDeclaration(node, scope));
            } else {
                prototype.declare(new Variable<Class>(node.getName()));
                prototype.get(node.getName()).setValue(createClassDeclaration(node, scope));
            }
        }

        for(int i = 0; i < fields.size(); i++) {
            VariableDeclarationNode node = fields.get(i);
            if(node.isStatic()) {
                statics.declare(Variable.valueOf(node.getName(), node.getType()));
                statics.get(node.getName()).setValue(visit(node.getAssignment().getValue(), scope)); // TODO Use Class Scope
                fields.remove(i);
                i--;
            }
        }

        Class cls = new Class(n.getName(), statics, fields.toArray(new VariableDeclarationNode[] {}), scope,
                this, prototype, n.getAccess(), n.isFinal());

        scope.getVariables().declare(new Variable<Class>(n.getName()));
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
        return new ObjectValue(cls);

    }


    // TODO implement class usage
}
