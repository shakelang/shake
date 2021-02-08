package com.github.nsc.de.shake.interpreter;

import com.github.nsc.de.shake.generators.ShakeGenerator;
import com.github.nsc.de.shake.interpreter.values.*;
import com.github.nsc.de.shake.interpreter.values.ClassValue;
import com.github.nsc.de.shake.lexer.Lexer;
import com.github.nsc.de.shake.lexer.characterinput.characterinputstream.CharacterInputStream;
import com.github.nsc.de.shake.lexer.characterinput.characterinputstream.SourceCharacterInputStream;
import com.github.nsc.de.shake.lexer.characterinput.charactersource.CharacterSource;
import com.github.nsc.de.shake.lexer.token.TokenInputStream;
import com.github.nsc.de.shake.parser.Parser;
import com.github.nsc.de.shake.parser.node.*;
import com.github.nsc.de.shake.parser.node.expression.*;
import com.github.nsc.de.shake.parser.node.factor.CharacterNode;
import com.github.nsc.de.shake.parser.node.factor.DoubleNode;
import com.github.nsc.de.shake.parser.node.factor.IntegerNode;
import com.github.nsc.de.shake.parser.node.factor.StringNode;
import com.github.nsc.de.shake.parser.node.functions.FunctionCallNode;
import com.github.nsc.de.shake.parser.node.functions.FunctionDeclarationNode;
import com.github.nsc.de.shake.parser.node.functions.ReturnNode;
import com.github.nsc.de.shake.parser.node.logical.*;
import com.github.nsc.de.shake.parser.node.loops.DoWhileNode;
import com.github.nsc.de.shake.parser.node.loops.ForNode;
import com.github.nsc.de.shake.parser.node.loops.WhileNode;

import com.github.nsc.de.shake.parser.node.objects.ClassConstructionNode;
import com.github.nsc.de.shake.parser.node.objects.ClassDeclarationNode;
import com.github.nsc.de.shake.parser.node.variables.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * {@link Interpreter} for executing the code directly
 */
public class Interpreter implements ShakeGenerator {



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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Interpreter(Scope global) {
        // set the global field
        this.global = global;
        try {
            getDefaults();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor for {@link Interpreter}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Interpreter() {
        // set the global scope to a new scope
        this.global = new Scope(null, this);
        global.getVariables().declare(new Variable<>("java", Java.class, new Java()));

        try {
            getDefaults();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getDefaults() throws IOException {
        CharacterSource source = CharacterSource.from(
                getClass().getResourceAsStream("/shake/java/system.shake"), "shake/system.shake");
        CharacterInputStream inputStream = new SourceCharacterInputStream(source);
        Lexer lexer = new Lexer(inputStream);
        TokenInputStream tokens = lexer.makeTokens();
        Parser parser = new Parser(tokens);
        Tree tree = parser.parse();
        this.visit(tree);
    }



    // *******************************
    // visit function

    /**
     * Visit a {@link Node} (using the {@link #global} {@link Scope})
     *
     * @param n the {@link Node} to visit
     * @return the resulting {@link InterpreterValue} of the operation
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public InterpreterValue visit(Node n, Scope scope) {
        // Check all the node-types and call the function to process it
        if(n instanceof Tree) return visitTree((Tree) n, scope);
        if(n instanceof DoubleNode) return visitDoubleNode((DoubleNode) n);
        if(n instanceof IntegerNode) return visitIntegerNode((IntegerNode) n);
        if(n instanceof StringNode) return visitStringNode((StringNode) n);
        if(n instanceof CharacterNode) return visitCharacterNode((CharacterNode) n);
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
        if(n instanceof LogicalXOrNode) return visitLogicalXOrNode((LogicalXOrNode) n, scope);
        if(n instanceof LogicalOrNode) return visitLogicalOrNode((LogicalOrNode) n, scope);
        if(n instanceof WhileNode) return visitWhileNode((WhileNode) n, scope);
        if(n instanceof DoWhileNode) return visitDoWhileNode((DoWhileNode) n, scope);
        if(n instanceof ForNode) return visitForNode((ForNode) n, scope);
        if(n instanceof IfNode) return visitIfNode((IfNode) n, scope);
        if(n instanceof FunctionDeclarationNode) return visitFunctionDeclarationNode((FunctionDeclarationNode) n, scope);
        if(n instanceof FunctionCallNode) return visitFunctionCallNode((FunctionCallNode) n, scope);
        if(n instanceof ReturnNode) return visitReturnNode((ReturnNode) n, scope);
        if(n instanceof IdentifierNode) return visitIdentifier((IdentifierNode) n, scope);
        if(n instanceof ClassConstructionNode) return visitClassConstruction((ClassConstructionNode) n, scope);
        if(n instanceof ClassDeclarationNode) return visitClassDeclarationNode((ClassDeclarationNode) n, scope);
        if(n instanceof ImportNode) return visitImportNode((ImportNode) n, scope);

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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public InterpreterValue visitTree(Tree t, Scope scope) {

        // Visit all the children but the last one
        for (int i = 0; i < t.getChildren().length - 1; i++) {
            visit(t.getChildren()[i], scope);

            // When there was a return statement we exit this tree
            if(scope.getReturnValue() != null) return NullValue.NULL;
        }

        // Visit the last children (if the amount of children is bigger than 0)
        if(t.getChildren().length > 0) return visit(t.getChildren()[t.getChildren().length-1], scope);

        // This is just reached when the Tree has no children
        // If the number of children is 0 we just return NullValue.NULL
        // as there are no children to process
        return NullValue.NULL;

    }



    // *******************************
    // factor nodes

    private InterpreterValue visitCharacterNode(CharacterNode n) {
        return new CharacterValue(n.getValue());
    }

    private InterpreterValue visitStringNode(StringNode n) {
        return new StringValue(n.getValue());
    }

    /**
     * Visit an {@link IntegerNode}
     *
     * @param n the {@link IntegerNode} to process
     * @return the {@link IntegerValue} that is created
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public InterpreterValue visitVariableDeclarationNode(VariableDeclarationNode n, Scope scope) {
        InterpreterValue value = n.getAssignment() != null ? visit(n.getAssignment().getValue(), scope) : null;
        if(!scope.getScopeVariables().declare(Variable.create(n.getName(), n.getType(), n.isFinal(), value))) throw new Error("Variable is already defined");
        else return NullValue.NULL;
}

    /**
     * Visit a {@link VariableAssignmentNode}
     *
     * @param n the {@link VariableAssignmentNode} to visit
     * @param scope the {@link Scope} for visiting the {@link VariableAssignmentNode}
     * @return The value that is assigned to the variable
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public InterpreterValue visitVariableUsageNode(VariableUsageNode n, Scope scope) {
        return (visitIdentifier(n.getVariable(), scope)).getValue();
    }



    // *******************************
    // variables

    /**
     * Visit a {@link LogicalEqEqualsNode}
     *
     * @param n the {@link LogicalEqEqualsNode} to visit
     * @param scope the {@link Scope} for visiting the {@link LogicalEqEqualsNode}
     * @return are the two sides of the {@link LogicalEqEqualsNode} the same?
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public InterpreterValue visitEqEqualsNode(LogicalEqEqualsNode n, Scope scope) {
        // visit both sides of the term
        // call the method equals() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.getLeft(), scope).equals(visit(n.getRight(), scope));
    }

    /**
     * Visit a {@link LogicalBiggerEqualsNode}
     *
     * @param n the {@link LogicalBiggerEqualsNode} to visit
     * @param scope the {@link Scope} for visiting the {@link LogicalBiggerEqualsNode}
     * @return is the left side of the {@link LogicalBiggerEqualsNode} bigger or equal to the right side
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public InterpreterValue visitBiggerEqualsNode(LogicalBiggerEqualsNode n, Scope scope) {
        // visit both sides of the term
        // call the method bigger_equals() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.getLeft(), scope).bigger_equals(visit(n.getRight(), scope));
    }

    /**
     * Visit a {@link LogicalSmallerEqualsNode}
     *
     * @param n the {@link LogicalSmallerEqualsNode} to visit
     * @param scope the {@link Scope} for visiting the {@link LogicalSmallerEqualsNode}
     * @return is the left side of the {@link LogicalSmallerEqualsNode} smaller or equal to the right side
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public InterpreterValue visitSmallerEqualsNode(LogicalSmallerEqualsNode n, Scope scope) {
        // visit both sides of the term
        // call the method smaller_equals() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.getLeft(), scope).smaller_equals(visit(n.getRight(), scope));
    }

    /**
     * Visit a {@link LogicalBiggerEqualsNode}
     *
     * @param n the {@link LogicalBiggerEqualsNode} to visit
     * @param scope the {@link Scope} for visiting the {@link LogicalBiggerEqualsNode}
     * @return is the left side of the {@link LogicalBiggerEqualsNode} bigger than the right side
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public InterpreterValue visitBiggerNode(LogicalBiggerNode n, Scope scope) {
        // visit both sides of the term
        // call the method bigger() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.getLeft(), scope).bigger(visit(n.getRight(), scope));
    }

    /**
     * Visit a {@link LogicalSmallerEqualsNode}
     *
     * @param n the {@link LogicalSmallerEqualsNode} to visit
     * @param scope the {@link Scope} for visiting the {@link LogicalSmallerEqualsNode}
     * @return is the left side of the {@link LogicalSmallerEqualsNode} bigger than the right side
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
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
     * Visit a {@link LogicalAndNode}
     *
     * @param n the {@link LogicalAndNode} to visit
     * @param scope the {@link Scope} for visiting the {@link LogicalAndNode}
     * @return is at least one of the two sides true
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public InterpreterValue visitLogicalAndNode(LogicalAndNode n, Scope scope) {
        return visit(n.getLeft(), scope).and(visit(n.getRight(), scope));
    }

    /**
     * Visit a {@link LogicalOrNode}
     *
     * @param n the {@link LogicalOrNode} to visit
     * @param scope the {@link Scope} for visiting the {@link LogicalOrNode}
     * @return are both sides true
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public InterpreterValue visitLogicalOrNode(LogicalOrNode n, Scope scope) {
        return visit(n.getLeft(), scope).or(visit(n.getRight(), scope));
    }

    /**
     * Visit a {@link LogicalXOrNode}
     *
     * @param n the {@link LogicalXOrNode} to visit
     * @param scope the {@link Scope} for visiting the {@link LogicalOrNode}
     * @return are both sides true
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public InterpreterValue visitLogicalXOrNode(LogicalXOrNode n, Scope scope) {
        return visit(n.getLeft(), scope).xor(visit(n.getRight(), scope));
    }



    // *******************************
    // loops

    /**
     * Visit a {@link WhileNode}
     *
     * @param n the {@link WhileNode} to visit
     * @param scope the {@link Scope} for visiting the {@link WhileNode}
     * @return NullValue.NULL (a while loop does not return a value)
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public InterpreterValue visitWhileNode(WhileNode n, Scope scope) {

        // Visit the condition. As long as it is true we will execute this while-loop
        while(BooleanValue.from(visit(n.getCondition(), scope)).getValue()) {

            // Copy the scope for inside the while loop
            // When we don't create a copy of the scope for
            // each round it would not be possible to declare
            // variables inside of the scope because there
            // would be an error thrown in the second run of
            // the loop
            Scope whileScope = scope.copy();

            // Visit the body using the copied scope
            visit(n.getBody(), whileScope);

        }

        // Return NULL, because a while-loop has nothing to return
        return NullValue.NULL;

    }

    /**
     * Visit a {@link DoWhileNode}
     *
     * @param n the {@link DoWhileNode} to visit
     * @param scope the {@link Scope} for visiting the {@link DoWhileNode}
     * @return NullValue.NULL (a do-while loop does not return a value)
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public InterpreterValue visitDoWhileNode(DoWhileNode n, Scope scope) {

        // Visit the condition. As long as it is true we will execute this do-while-loop
        do {

            // Copy the scope for inside the while loop
            // When we don't create a copy of the scope for
            // each round it would not be possible to declare
            // variables inside of the scope because there
            // would be an error thrown in the second run of
            // the loop
            Scope doWhileScope = scope.copy();

            // Visit the body using the copied scope
            visit(n.getBody(), doWhileScope);

        } while(BooleanValue.from(visit(n.getCondition(), scope)).getValue());

        // Return NULL, because a do-while-loop has nothing to return
        return NullValue.NULL;
    }

    /**
     * Visit a {@link ForNode}
     *
     * @param n the {@link ForNode} to visit
     * @param scope the {@link Scope} for visiting the {@link ForNode}
     * @return NullValue.NULL (a for loop does not return a value)
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public InterpreterValue visitForNode(ForNode n, Scope scope) {

        // copy the scope as outer scope, so the
        // counter-variable is deleted after the
        // for-loop-execution
        Scope forOuterScope = scope.copy();

        // visit the declaration (first statement) of the for-loop
        visit(n.getDeclaration(), forOuterScope);

        // Visit the condition. As long as it is true we will execute this for
        while(BooleanValue.from(visit(n.getCondition(), forOuterScope)).getValue()) {


            // Copy the outer-scope for inside the while loop
            // When we don't create a copy of the scope for
            // each round it would not be possible to declare
            // variables inside of the scope because there
            // would be an error thrown in the second run of
            // the loop
            Scope forInnerScope = forOuterScope.copy();

            // Visit the body using the copied scope
            visit(n.getBody(), forInnerScope);

            // Execute the round statement (the third statement inside the for-loop)
            // We are using the forOuterScope as scope argument here
            visit(n.getRound(), forOuterScope);

        }

        // Return NULL, because a do-while-loop has nothing to return
        return NullValue.NULL;
    }



    // *******************************
    // if-else

    /**
     * Visit a if-else statement ({@link IfNode})
     *
     * @param n the {@link IfNode} to visit
     * @param scope the {@link Scope} for visiting the {@link IfNode}
     * @return the last operation-result of the executed block
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public InterpreterValue visitIfNode(IfNode n, Scope scope) {

        // Create a copy of the scope for executing the if-block
        // so the variables declared inside of the if-block do
        // not exist outside of it
        Scope ifScope = scope.copy();

        // Visit the condition. If it is true then visit the body, if not visit the else-body
        if(BooleanValue.from(visit(n.getCondition(), ifScope)).getValue()) return visit(n.getBody(), ifScope);
        else if(n.getElseBody() != null) return visit(n.getElseBody(), ifScope);

        // If we had nothing to return then we will just return NullValue.NULL
        return NullValue.NULL;

    }



    // *******************************
    // functions

    /**
     * Visit a {@link FunctionDeclarationNode}
     *
     * @param node the {@link FunctionDeclarationNode} to visit
     * @param scope the {@link Scope} for visiting the {@link FunctionDeclarationNode}
     * @return the {@link Function}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Function visitFunctionDeclarationNode(FunctionDeclarationNode node, Scope scope) {

        // Declare the variable that contains the function
        if(!scope.getVariables().declare(new Variable<>(node.getName(), Function.class)))
            throw new Error("'" + node.getName() + "' is already declared!");

        // Create the function
        Function f = createFunctionDeclaration(node, scope);

        // Apply the function as value to the variable
        scope.getVariables().get(node.getName()).setValue(f);

        // return the function
        return f;

    }

    /**
     * Create a {@link FunctionDeclarationNode}
     *
     * @param node the {@link FunctionDeclarationNode} to create
     * @param scope the {@link Scope} to create the {@link FunctionDeclarationNode}
     * @return the created {@link Function}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Function createFunctionDeclaration(FunctionDeclarationNode node, Scope scope) {

        // return a new function from the node and the scope
        return new Function(node.getArgs(), node.getBody(), scope, this, node.getAccess(), node.isFinal());

    }

    /**
     * Visit a {@link FunctionCallNode}
     *
     * @param node the {@link FunctionCallNode} to visit
     * @param scope the {@link Scope} for visiting the {@link FunctionCallNode}
     * @return the return {@link InterpreterValue} of the function-call
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public InterpreterValue visitFunctionCallNode(FunctionCallNode node, Scope scope) {

        // get the function
        InterpreterValue v = visit(node.getFunction());

        // call the function & return it's result
        return v.invoke(node, scope);

    }

    public InterpreterValue visitReturnNode(ReturnNode node, Scope scope) {

        scope.setReturnValue(visit(node.getValue(), scope));
        return NullValue.NULL;

    }



    // *******************************
    // classes

    /**
     * Visit a {@link ClassDeclarationNode}
     *
     * @param node the {@link ClassDeclarationNode} to visit
     * @param scope the {@link Scope} to visit the {@link ClassDeclarationNode}
     * @return the created {@link ClassValue}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public ClassValue visitClassDeclarationNode(ClassDeclarationNode node, Scope scope) {

        // Declare the variable that contains the class
        if(!scope.getVariables().declare(new Variable<>(node.getName(), ClassValue.class)))
            throw new Error("'" + node.getName() + "' is already declared!");

        // Create the class
        ClassValue c = createClassDeclaration(node, scope);

        // Set the variable value to the class
        scope.getVariables().get(node.getName()).setValue(c);

        // return the class
        return c;

    }

    /**
     * Create a class-declaration ({@link ClassDeclarationNode})
     *
     * @param n the {@link ClassDeclarationNode} to create
     * @param scope the {@link Scope} to create the {@link ClassDeclarationNode}
     * @return the created {@link ClassValue}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public ClassValue createClassDeclaration(ClassDeclarationNode n, Scope scope) {


        // Create a list for the fields of the class
        // They will not be created in the declaration
        // So we save the VariableDeclarationNodes inside
        // of the class to create them when creating the
        // object
        // We take all the fields from the class-declaration
        // here and delete the static fields from it later
        List<VariableDeclarationNode> fields = new ArrayList<>(Arrays.asList(n.getFields()));

        // Create VariableLists for the prototype
        // (containing functions and child-classes)
        VariableList prototype = new VariableList();
        VariableList statics = new VariableList();

        // TODO 2 Declarations with the same name

        // loop over all methods of the class
        for(FunctionDeclarationNode node : n.getMethods()) {

            // if the method is static
            if(n.isStatic()) {
                // declare a new static variable for the function
                statics.declare(new Variable<>(node.getName(), Function.class));
                // create the function and apply it to the variable
                statics.get(node.getName()).setValue(createFunctionDeclaration(node, scope));
            }
            else {
                // declare a new prototype-variable for the function
                prototype.declare(new Variable<>(node.getName(), Function.class));
                // create the function and apply it to the variable
                prototype.get(node.getName()).setValue(createFunctionDeclaration(node, scope));
            }
        }

        // loop over all sub-classes of the class
        for(ClassDeclarationNode node : n.getClasses()) {
            if(n.isStatic()) {
                // declare a new static variable for the class
                statics.declare(new Variable<>(node.getName(), ClassValue.class));
                // create the class and apply it to the variable
                statics.get(node.getName()).setValue(createClassDeclaration(node, scope));
            } else {
                // declare a new prototype-variable for the class
                prototype.declare(new Variable<>(node.getName(), ClassValue.class));
                // create the class and apply it to the variable
                prototype.get(node.getName()).setValue(createClassDeclaration(node, scope));
            }
        }

        // loop over the fields
        for(int i = 0; i < fields.size(); i++) {

            // get the VariableDeclarationNode from the fields list
            VariableDeclarationNode node = fields.get(i);

            // if the field is static...
            if(node.isStatic()) {
                // declare a new static field for it
                statics.declare(Variable.create(node.getName(), node.getType(), node.isFinal()));

                // ...and apply the value (visit it's value)
                // TODO Use Class Scope
                statics.get(node.getName()).setValue(visit(node.getAssignment().getValue(), scope));

                // remove the field from the fields list
                //
                // (as we have one field less in the list
                // we have to decrease the counter-variable
                // by one)
                fields.remove(i--);
            }
        }

        // create a new class
        ClassValue cls = new ClassValue(n.getName(), statics, fields.toArray(new VariableDeclarationNode[] {}), scope,
                this, prototype, n.getAccess(), n.isFinal());

        // return the class
        return cls;

    }

    /**
     * Visit a {@link ClassConstructionNode}
     *
     * @param n the {@link ClassConstructionNode} to create
     * @param scope the {@link Scope} to create the {@link ClassDeclarationNode}
     * @return the created {@link ClassValue}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public InterpreterValue visitClassConstruction(ClassConstructionNode n, Scope scope) {

        // TODO Arguments for constructor and constructor in variable

        // Get the class
        InterpreterValue v = visit(n.getType(), scope);

        // create a new ObjectValue from the class
        return v.newInstance(n, scope);

    }



    // *******************************
    // identifiers

    /**
     * Visit an {@link IdentifierNode}
     *
     * @param node the {@link IdentifierNode} to visit
     * @param scope the {@link Scope} to visit the {@link IdentifierNode}
     * @return
     */
    public Variable visitIdentifier(IdentifierNode node, Scope scope) {

        // if the IdentifierNode
        if(node.getParent() != null) {

            // visit the parent
            InterpreterValue parent = visit(node.getParent(), scope);

            // get the child from the parent
            Variable v = parent.getChild(node.getName());

            // if the variable not declared throw an error
            if(v == null) throw new Error(String.format("Child \"%s\" is not defined", node.getName()));

            // return the variable
            return v;

        }
        else {

            // get the variable from the scope
            Variable v = scope.getVariables().get(node.getName());

            // if the variable is not declared throw an error
            if(v == null) throw new Error(String.format("Variable with name \"%s\" is not declared", node.getName()));

            // return the variable
            return v;

        }

    }

    public InterpreterValue visitImportNode(ImportNode node, Scope scope) {

        InterpreterValue actual = scope.getVariables();
        String[] imported = node.getImport();
        int lastIndex = imported.length - 1;

        for(int i = 0; i < imported.length; i++) {
            if(imported[i].equals(ImportNode.EVERYTHING)) {

                String[] children = actual.getChildren();

                for(int c = 0; c < children.length; c++)
                    scope.getVariables().declare(Variable.finalOf(children[c], actual.getChild(children[c])));

            }
            else {
                actual = actual.getChild(imported[i]);
                if(i == lastIndex) scope.getVariables().declare(Variable.finalOf(imported[i], actual));
            }
        }

        return NullValue.NULL;
    }

    @Override
    public String getExtension() {
        return "";
    }

    @Override
    public String getName() {
        return "interpreter";
    }
}
