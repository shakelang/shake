package com.github.nsc.de.shake.interpreter

import com.github.nsc.de.shake.generation.ShakeGeneratorBase
import com.github.nsc.de.shake.interpreter.Variable.Companion.create
import com.github.nsc.de.shake.interpreter.Variable.Companion.finalOf
import com.github.nsc.de.shake.interpreter.values.*
import com.github.nsc.de.shake.interpreter.values.BooleanValue.Companion.from
import com.github.nsc.de.shake.interpreter.values.Function
import com.github.nsc.de.shake.lexer.Lexer
import com.github.nsc.de.shake.parser.Parser
import com.github.nsc.de.shake.parser.node.*
import com.github.nsc.de.shake.parser.node.expression.*
import com.github.nsc.de.shake.parser.node.factor.CharacterNode
import com.github.nsc.de.shake.parser.node.factor.DoubleNode
import com.github.nsc.de.shake.parser.node.factor.IntegerNode
import com.github.nsc.de.shake.parser.node.factor.StringNode
import com.github.nsc.de.shake.parser.node.functions.FunctionCallNode
import com.github.nsc.de.shake.parser.node.functions.FunctionDeclarationNode
import com.github.nsc.de.shake.parser.node.functions.ReturnNode
import com.github.nsc.de.shake.parser.node.logical.*
import com.github.nsc.de.shake.parser.node.loops.DoWhileNode
import com.github.nsc.de.shake.parser.node.loops.ForNode
import com.github.nsc.de.shake.parser.node.loops.WhileNode
import com.github.nsc.de.shake.parser.node.objects.ClassConstructionNode
import com.github.nsc.de.shake.parser.node.objects.ClassDeclarationNode
import com.github.nsc.de.shake.parser.node.variables.*
import com.github.nsc.de.shake.util.characterinput.characterinputstream.CharacterInputStream
import com.github.nsc.de.shake.util.characterinput.characterinputstream.SourceCharacterInputStream
import com.github.nsc.de.shake.util.characterinput.charactersource.CharacterSource.Companion.from
import java.io.IOException
import java.util.*
import kotlin.Error
import kotlin.String
import kotlin.Suppress
import kotlin.Throws
import kotlin.Unit

/**
 * [Interpreter] for executing the code directly
 */
@Suppress("unused")
class Interpreter : ShakeGeneratorBase {


    // *******************************
    // fields

    /**
     * The global [Scope] of the interpreter (if you call visit without giving a [Scope] as
     * parameter this [Scope] will be used
     */
    private val global: Scope


    // *******************************
    // constructors

    /**
     * Constructor for [Interpreter]
     *
     * @param global the global [Scope] ([.global])
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    constructor(global: Scope) {
        // set the global field
        this.global = global
    }

    /**
     * Constructor for [Interpreter]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    constructor() {
        // set the global scope to a new scope
        global = Scope(null, this)
        try {
            defaults
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @get:Throws(IOException::class)
    val defaults: Unit
        get() {
            global.getVariables().declare(Variable("java", Java::class.java, Java()))
            load("/shake/java/system.shake", "shake/system.shake")
            load("/shake/java/io.shake", "shake/io.shake")
        }

    @Throws(IOException::class)
    private fun load(src: String, src_name: String) {
        val source = from(
            javaClass.getResourceAsStream(src)!!, src_name
        )
        val inputStream: CharacterInputStream = SourceCharacterInputStream(source)
        val lexer = Lexer(inputStream)
        val tokens = lexer.makeTokens()
        val parser = Parser(tokens)
        val tree = parser.parse()
        visit(tree)
    }

    fun resetGlobals() {
        global.reset()
        try {
            defaults
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    /**
     * Visits the given [Node] using the specified [Scope]
     *
     * @param n the [Node] to visit
     * @param scope the [Scope] to use
     * @return the resulting [InterpreterValue] of the operation
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */


    // *******************************
    // visit function

    /**
     * Visit a [Node] (using the [.global] [Scope])
     *
     * @param n the [Node] to visit
     * @return the resulting [InterpreterValue] of the operation
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    @JvmOverloads
    fun visit(n: Node?, scope: Scope = global): InterpreterValue {
        // Check all the node-types and call the function to process it
        if (n is Tree) return visitTree(n, scope)
        if (n is DoubleNode) return visitDoubleNode(n)
        if (n is IntegerNode) return visitIntegerNode(n)
        if (n is StringNode) return visitStringNode(n)
        if (n is CharacterNode) return visitCharacterNode(n)
        if (n is AddNode) return visitAddNode(n, scope)
        if (n is SubNode) return visitSubNode(n, scope)
        if (n is MulNode) return visitMulNode(n, scope)
        if (n is DivNode) return visitDivNode(n, scope)
        if (n is ModNode) return visitModNode(n, scope)
        if (n is PowNode) return visitPowNode(n, scope)
        if (n is VariableDeclarationNode) return visitVariableDeclarationNode(n, scope)
        if (n is VariableAddAssignmentNode) return visitVariableAddAssignmentNode(n, scope)
        if (n is VariableSubAssignmentNode) return visitVariableSubAssignmentNode(n, scope)
        if (n is VariableMulAssignmentNode) return visitVariableMulAssignmentNode(n, scope)
        if (n is VariableDivAssignmentNode) return visitVariableDivAssignmentNode(n, scope)
        if (n is VariableModAssignmentNode) return visitVariableModAssignmentNode(n, scope)
        if (n is VariablePowAssignmentNode) return visitVariablePowAssignmentNode(n, scope)
        if (n is VariableIncreaseNode) return visitVariableIncreaseNode(n, scope)
        if (n is VariableDecreaseNode) return visitVariableDecreaseNode(n, scope)
        if (n is VariableAssignmentNode) return visitVariableAssignmentNode(n, scope)
        if (n is VariableUsageNode) return visitVariableUsageNode(n, scope)
        if (n is LogicalEqEqualsNode) return visitEqEqualsNode(n, scope)
        if (n is LogicalBiggerEqualsNode) return visitBiggerEqualsNode(n, scope)
        if (n is LogicalSmallerEqualsNode) return visitSmallerEqualsNode(n, scope)
        if (n is LogicalBiggerNode) return visitBiggerNode(n, scope)
        if (n is LogicalSmallerNode) return visitSmallerNode(n, scope)
        if (n is LogicalAndNode) return visitLogicalAndNode(n, scope)
        if (n is LogicalXOrNode) return visitLogicalXOrNode(n, scope)
        if (n is LogicalOrNode) return visitLogicalOrNode(n, scope)
        if (n is WhileNode) return visitWhileNode(n, scope)
        if (n is DoWhileNode) return visitDoWhileNode(n, scope)
        if (n is ForNode) return visitForNode(n, scope)
        if (n is IfNode) return visitIfNode(n, scope)
        if (n is FunctionDeclarationNode) return visitFunctionDeclarationNode(n, scope)
        if (n is FunctionCallNode) return visitFunctionCallNode(n, scope)
        if (n is ReturnNode) return visitReturnNode(n, scope)
        if (n is IdentifierNode) return visitIdentifier(n, scope)
        if (n is ClassConstructionNode) return visitClassConstruction(n, scope)
        if (n is ClassDeclarationNode) return visitClassDeclarationNode(n, scope)
        if (n is ImportNode) return visitImportNode(n, scope)
        if (n is CastNode) return visitCastNode(n, scope)

        // if the node a LogicalTrueNode return TRUE, if it is a LogicalFalseNode return false
        if (n is LogicalTrueNode) return BooleanValue.TRUE
        if (n is LogicalFalseNode) return BooleanValue.FALSE

        // if the node is null return NullValue.NULL
        if (n == null) return NullValue.NULL
        throw Error("It looks like that Node is not implemented in the Interpreter")
    }


    // *******************************
    // visit function

    /**
     * Visit a [Tree]
     *
     * @param t the [Tree] to visit
     * @param scope the scope to use for visiting the [Tree]
     * @return the latest [InterpreterValue] of the tree
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitTree(t: Tree, scope: Scope): InterpreterValue {

        // Visit all the children but the last one
        for (i in 0 until t.children.size - 1) {
            visit(t.children[i], scope)

            // When there was a return statement we exit this tree
            if (scope.returnValue != null) return NullValue.NULL
        }

        // Visit the last children (if the amount of children is bigger than 0)
        return if (t.children.isNotEmpty()) visit(t.children[t.children.size - 1], scope) else NullValue.NULL

        // This is just reached when the Tree has no children
        // If the number of children is 0 we just return NullValue.NULL
        // as there are no children to process
    }


    // *******************************
    // factor nodes

    fun visitCharacterNode(n: CharacterNode): InterpreterValue {
        return CharacterValue(n.value)
    }

    fun visitStringNode(n: StringNode): InterpreterValue {
        return StringValue(n.value)
    }

    /**
     * Visit an [IntegerNode]
     *
     * @param n the [IntegerNode] to process
     * @return the [IntegerValue] that is created
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitIntegerNode(n: IntegerNode): IntegerValue {
        // Just create a IntegerValue from the IntegerNode value and return it
        return IntegerValue(n.number)
    }

    /**
     * Visit a [DoubleNode]
     *
     * @param n the [DoubleNode] to process
     * @return the [DoubleValue] that is created
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitDoubleNode(n: DoubleNode): DoubleValue {
        // Just create a DoubleValue from the DoubleNode value and return it
        return DoubleValue(n.number)
    }


    // *******************************
    // calculations

    /**
     * Visit an [AddNode] (Addition)
     *
     * @param n the [AddNode] to visit
     * @param scope the [Scope] for visiting the [AddNode]
     * @return the addition-result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitAddNode(n: AddNode, scope: Scope): InterpreterValue {
        // visit both sides of the term
        // call the method add() on the result of the left InterpreterValue and
        // give the right result as argument.
        return try {
            visit(n.left, scope).add(visit(n.right, scope))
        } catch (error: UnformattedInterpreterError) {
            throw InterpreterError(error.message, n.map, n.operatorPosition, error)
        }
    }

    /**
     * Visit a [SubNode] (Subtraction)
     *
     * @param n the [SubNode] to visit
     * @param scope the [Scope] for visiting the [SubNode]
     * @return the subtraction-result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitSubNode(n: SubNode, scope: Scope): InterpreterValue {
        // visit both sides of the term
        // call the method sub() on the result of the left InterpreterValue and
        // give the right result as argument.
        return try {
            visit(n.left, scope).sub(visit(n.right, scope))
        } catch (error: UnformattedInterpreterError) {
            throw InterpreterError(error.message, n.map, n.operatorPosition, error)
        }
    }

    /**
     * Visit a [MulNode] (Multiplication)
     *
     * @param n the [MulNode] to visit
     * @param scope the [Scope] for visiting the [MulNode]
     * @return the multiplication-result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitMulNode(n: MulNode, scope: Scope): InterpreterValue {
        // visit both sides of the term
        // call the method mul() on the result of the left InterpreterValue and
        // give the right result as argument.
        return try {
            visit(n.left, scope).mul(visit(n.right, scope))
        } catch (error: UnformattedInterpreterError) {
            throw InterpreterError(error.message, n.map, n.operatorPosition, error)
        }
    }

    /**
     * Visit a [DivNode] (Division)
     *
     * @param n the [DivNode] to visit
     * @param scope the [Scope] for visiting the [DivNode]
     * @return the division-result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitDivNode(n: DivNode, scope: Scope): InterpreterValue {
        // visit both sides of the term
        // call the method div() on the result of the left InterpreterValue and
        // give the right result as argument.
        return try {
            visit(n.left, scope).div(visit(n.right, scope))
        } catch (error: UnformattedInterpreterError) {
            throw InterpreterError(error.message, n.map, n.operatorPosition, error)
        }
    }

    /**
     * Visit a [ModNode] (Modulo)
     *
     * @param n the [ModNode] to visit
     * @param scope the [Scope] for visiting the [ModNode]
     * @return the modulo-result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitModNode(n: ModNode, scope: Scope): InterpreterValue {
        // visit both sides of the term
        // call the method mod() on the result of the left InterpreterValue and
        // give the right result as argument.
        return try {
            visit(n.left, scope).mod(visit(n.right, scope))
        } catch (error: UnformattedInterpreterError) {
            throw InterpreterError(error.message, n.map, n.operatorPosition, error)
        }
    }

    /**
     * Visit a [PowNode] (Modulo)
     *
     * @param n the [PowNode] to visit
     * @param scope the [Scope] for visiting the [PowNode]
     * @return the power-result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitPowNode(n: PowNode, scope: Scope): InterpreterValue {
        // visit both sides of the term
        // call the method pow() on the result of the left InterpreterValue and
        // give the right result one as argument.
        return try {
            visit(n.left, scope).pow(visit(n.right, scope))
        } catch (error: UnformattedInterpreterError) {
            throw InterpreterError(
                error.message, n.map, n.operatorPosition,
                n.operatorPosition + 1, error
            )
        }
    }


    // *******************************
    // variables

    /**
     * Visit a [VariableDeclarationNode]
     *
     * @param n the [VariableDeclarationNode] to process
     * @param scope the [Scope] for visiting the [VariableDeclarationNode]
     * @return If the [Variable] gets a value assigned the value that is assigned, if not NULL
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitVariableDeclarationNode(n: VariableDeclarationNode, scope: Scope): InterpreterValue {
        val value = if (n.assignment != null) visit(n.assignment!!.value, scope) else null
        return if (!scope.scopeVariables.declare(
                create(
                    n.name,
                    n.type,
                    n.isFinal,
                    value
                )
            )
        ) throw Error("Variable is already defined") else NullValue.NULL
    }

    /**
     * Visit a [VariableAssignmentNode]
     *
     * @param n the [VariableAssignmentNode] to visit
     * @param scope the [Scope] for visiting the [VariableAssignmentNode]
     * @return The value that is assigned to the variable
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitVariableAssignmentNode(n: VariableAssignmentNode, scope: Scope): InterpreterValue {
        val variable = visit(n.variable, scope) as Variable
        val value = visit(n.value, scope)
        variable.value = value
        return value
    }

    /**
     * Visit a [VariableAddAssignmentNode]
     *
     * @param n the [VariableAddAssignmentNode] to visit
     * @param scope the [Scope] for visiting the [VariableAddAssignmentNode]
     * @return The value that is assigned to the variable (So the old variable value plus the value given.)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitVariableAddAssignmentNode(n: VariableAddAssignmentNode, scope: Scope): InterpreterValue {
        val variable = visit(n.variable, scope) as Variable
        val value = visit(n.value, scope)
        val newValue: InterpreterValue
        newValue = try {
            variable.value.add(value)
        } catch (error: UnformattedInterpreterError) {
            throw InterpreterError(
                error.message, n.map,
                n.operatorPosition, n.operatorPosition + 1, error
            )
        }
        variable.value = newValue
        return newValue
    }

    /**
     * Visit a [VariableSubAssignmentNode]
     *
     * @param n the [VariableSubAssignmentNode] to visit
     * @param scope the [Scope] for visiting the [VariableSubAssignmentNode]
     * @return The value that is assigned to the variable (So the old variable value minus the value given.)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitVariableSubAssignmentNode(n: VariableSubAssignmentNode, scope: Scope): InterpreterValue {
        val variable = visit(n.variable, scope) as Variable
        val value = visit(n.value, scope)
        val newValue: InterpreterValue
        newValue = try {
            variable.value.sub(value)
        } catch (error: UnformattedInterpreterError) {
            throw InterpreterError(
                error.message, n.map,
                n.operatorPosition, n.operatorPosition + 1, error
            )
        }
        variable.value = newValue
        return newValue
    }

    /**
     * Visit a [VariableMulAssignmentNode]
     *
     * @param n the [VariableMulAssignmentNode] to visit
     * @param scope the [Scope] for visiting the [VariableMulAssignmentNode]
     * @return The value that is assigned to the variable (So the old variable value times the value given.)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitVariableMulAssignmentNode(n: VariableMulAssignmentNode, scope: Scope): InterpreterValue {
        val variable = visit(n.variable, scope) as Variable
        val value = visit(n.value, scope)
        val newValue: InterpreterValue
        newValue = try {
            variable.value.mul(value)
        } catch (error: UnformattedInterpreterError) {
            throw InterpreterError(
                error.message, n.map,
                n.operatorPosition, n.operatorPosition + 1, error
            )
        }
        variable.value = newValue
        return newValue
    }

    /**
     * Visit a [VariableDivAssignmentNode]
     *
     * @param n the [VariableDivAssignmentNode] to visit
     * @param scope the [Scope] for visiting the [VariableDivAssignmentNode]
     * @return The value that is assigned to the variable (So the old variable value by the value given.)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitVariableDivAssignmentNode(n: VariableDivAssignmentNode, scope: Scope): InterpreterValue {
        val variable = visit(n.variable, scope) as Variable
        val value = visit(n.value, scope)
        val newValue: InterpreterValue
        newValue = try {
            variable.value.div(value)
        } catch (error: UnformattedInterpreterError) {
            throw InterpreterError(
                error.message, n.map,
                n.operatorPosition, n.operatorPosition + 1, error
            )
        }
        variable.value = newValue
        return newValue
    }

    /**
     * Visit a [VariableModAssignmentNode]
     *
     * @param n the [VariableModAssignmentNode] to visit
     * @param scope the [Scope] for visiting the [VariableModAssignmentNode]
     * @return The value that is assigned to the variable (So the old variable value modulo the value given.)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitVariableModAssignmentNode(n: VariableModAssignmentNode, scope: Scope): InterpreterValue {
        val variable = visit(n.variable, scope) as Variable
        val value = visit(n.value, scope)
        val newValue: InterpreterValue
        newValue = try {
            variable.value.mod(value)
        } catch (error: UnformattedInterpreterError) {
            throw InterpreterError(
                error.message, n.map,
                n.operatorPosition, n.operatorPosition + 1, error
            )
        }
        variable.value = newValue
        return newValue
    }

    /**
     * Visit a [VariablePowAssignmentNode]
     *
     * @param n the [VariablePowAssignmentNode] to visit
     * @param scope the [Scope] for visiting the [VariablePowAssignmentNode]
     * @return The value that is assigned to the variable (So the old variable value power the value given.)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitVariablePowAssignmentNode(n: VariablePowAssignmentNode, scope: Scope): InterpreterValue {
        val variable = visit(n.variable, scope) as Variable
        val value = visit(n.value, scope)
        val newValue: InterpreterValue
        newValue = try {
            variable.value.pow(value)
        } catch (error: UnformattedInterpreterError) {
            throw InterpreterError(
                error.message, n.map,
                n.operatorPosition, n.operatorPosition + 2, error
            )
        }
        variable.value = newValue
        return newValue
    }

    /**
     * Visit a [VariableIncreaseNode]
     *
     * @param n the [VariableIncreaseNode] to visit
     * @param scope the [Scope] for visiting the [VariableIncreaseNode]
     * @return The old value of the Variable
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitVariableIncreaseNode(n: VariableIncreaseNode, scope: Scope): InterpreterValue {
        val variable = visit(n.variable, scope) as Variable
        val v = variable.value
        variable.value = v.add(IntegerValue.ONE)
        return v
    }

    /**
     * Visit a [VariableDecreaseNode]
     *
     * @param n the [VariableDecreaseNode] to visit
     * @param scope the [Scope] for visiting the [VariableDecreaseNode]
     * @return The old value of the Variable
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitVariableDecreaseNode(n: VariableDecreaseNode, scope: Scope): InterpreterValue {
        val variable = visit(n.variable, scope) as Variable
        val v = variable.value
        variable.value = v.sub(IntegerValue.ONE)
        return v
    }

    /**
     * Visit a [VariableUsageNode]
     *
     * @param n the [VariableUsageNode] to visit
     * @param scope the [Scope] for visiting the [VariableUsageNode]
     * @return The value of the [Variable]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitVariableUsageNode(n: VariableUsageNode, scope: Scope): InterpreterValue {
        return visitIdentifier(n.variable, scope).value
    }


    // *******************************
    // variables

    /**
     * Visit a [LogicalEqEqualsNode]
     *
     * @param n the [LogicalEqEqualsNode] to visit
     * @param scope the [Scope] for visiting the [LogicalEqEqualsNode]
     * @return are the two sides of the [LogicalEqEqualsNode] the same?
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitEqEqualsNode(n: LogicalEqEqualsNode, scope: Scope): InterpreterValue {
        // visit both sides of the term
        // call the method equals() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.left, scope).equals(visit(n.right, scope))
    }

    /**
     * Visit a [LogicalBiggerEqualsNode]
     *
     * @param n the [LogicalBiggerEqualsNode] to visit
     * @param scope the [Scope] for visiting the [LogicalBiggerEqualsNode]
     * @return is the left side of the [LogicalBiggerEqualsNode] bigger or equal to the right side
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitBiggerEqualsNode(n: LogicalBiggerEqualsNode, scope: Scope): InterpreterValue {
        // visit both sides of the term
        // call the method bigger_equals() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.left, scope).biggerEquals(visit(n.right, scope))
    }

    /**
     * Visit a [LogicalSmallerEqualsNode]
     *
     * @param n the [LogicalSmallerEqualsNode] to visit
     * @param scope the [Scope] for visiting the [LogicalSmallerEqualsNode]
     * @return is the left side of the [LogicalSmallerEqualsNode] smaller or equal to the right side
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitSmallerEqualsNode(n: LogicalSmallerEqualsNode, scope: Scope): InterpreterValue {
        // visit both sides of the term
        // call the method smaller_equals() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.left, scope).smallerEquals(visit(n.right, scope))
    }

    /**
     * Visit a [LogicalBiggerEqualsNode]
     *
     * @param n the [LogicalBiggerEqualsNode] to visit
     * @param scope the [Scope] for visiting the [LogicalBiggerEqualsNode]
     * @return is the left side of the [LogicalBiggerEqualsNode] bigger than the right side
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitBiggerNode(n: LogicalBiggerNode, scope: Scope): InterpreterValue {
        // visit both sides of the term
        // call the method bigger() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.left, scope).bigger(visit(n.right, scope))
    }

    /**
     * Visit a [LogicalSmallerEqualsNode]
     *
     * @param n the [LogicalSmallerEqualsNode] to visit
     * @param scope the [Scope] for visiting the [LogicalSmallerEqualsNode]
     * @return is the left side of the [LogicalSmallerEqualsNode] bigger than the right side
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitSmallerNode(n: LogicalSmallerNode, scope: Scope): InterpreterValue {
        // visit both sides of the term
        // call the method smaller() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.left, scope).smaller(visit(n.right, scope))
    }
    // *******************************
    // logical concatenation
    /**
     * Visit a [LogicalAndNode]
     *
     * @param n the [LogicalAndNode] to visit
     * @param scope the [Scope] for visiting the [LogicalAndNode]
     * @return is at least one of the two sides true
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitLogicalAndNode(n: LogicalAndNode, scope: Scope): InterpreterValue {
        return visit(n.left, scope).and(visit(n.right, scope))
    }

    /**
     * Visit a [LogicalOrNode]
     *
     * @param n the [LogicalOrNode] to visit
     * @param scope the [Scope] for visiting the [LogicalOrNode]
     * @return are both sides true
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitLogicalOrNode(n: LogicalOrNode, scope: Scope): InterpreterValue {
        return visit(n.left, scope).or(visit(n.right, scope))
    }

    /**
     * Visit a [LogicalXOrNode]
     *
     * @param n the [LogicalXOrNode] to visit
     * @param scope the [Scope] for visiting the [LogicalOrNode]
     * @return are both sides true
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitLogicalXOrNode(n: LogicalXOrNode, scope: Scope): InterpreterValue {
        return visit(n.left, scope).xor(visit(n.right, scope))
    }


    // *******************************
    // loops

    /**
     * Visit a [WhileNode]
     *
     * @param n the [WhileNode] to visit
     * @param scope the [Scope] for visiting the [WhileNode]
     * @return NullValue.NULL (a while loop does not return a value)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitWhileNode(n: WhileNode, scope: Scope): InterpreterValue {

        // Visit the condition. As long as it is true we will execute this while-loop
        while (from(visit(n.condition, scope)).value) {

            // Copy the scope for inside the while loop
            // When we don't create a copy of the scope for
            // each round it would not be possible to declare
            // variables inside of the scope because there
            // would be an error thrown in the second run of
            // the loop
            val whileScope = scope.copy()

            // Visit the body using the copied scope
            visit(n.body, whileScope)
        }

        // Return NULL, because a while-loop has nothing to return
        return NullValue.NULL
    }

    /**
     * Visit a [DoWhileNode]
     *
     * @param n the [DoWhileNode] to visit
     * @param scope the [Scope] for visiting the [DoWhileNode]
     * @return NullValue.NULL (a do-while loop does not return a value)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitDoWhileNode(n: DoWhileNode, scope: Scope): InterpreterValue {

        // Visit the condition. As long as it is true we will execute this do-while-loop
        do {

            // Copy the scope for inside the while loop
            // When we don't create a copy of the scope for
            // each round it would not be possible to declare
            // variables inside of the scope because there
            // would be an error thrown in the second run of
            // the loop
            val doWhileScope = scope.copy()

            // Visit the body using the copied scope
            visit(n.body, doWhileScope)
        } while (from(visit(n.condition, scope)).value)

        // Return NULL, because a do-while-loop has nothing to return
        return NullValue.NULL
    }

    /**
     * Visit a [ForNode]
     *
     * @param n the [ForNode] to visit
     * @param scope the [Scope] for visiting the [ForNode]
     * @return NullValue.NULL (a for loop does not return a value)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitForNode(n: ForNode, scope: Scope): InterpreterValue {

        // copy the scope as outer scope, so the
        // counter-variable is deleted after the
        // for-loop-execution
        val forOuterScope = scope.copy()

        // visit the declaration (first statement) of the for-loop
        visit(n.declaration, forOuterScope)

        // Visit the condition. As long as it is true we will execute this for
        while (from(visit(n.condition, forOuterScope)).value) {


            // Copy the outer-scope for inside the while loop
            // When we don't create a copy of the scope for
            // each round it would not be possible to declare
            // variables inside of the scope because there
            // would be an error thrown in the second run of
            // the loop
            val forInnerScope = forOuterScope.copy()

            // Visit the body using the copied scope
            visit(n.body, forInnerScope)

            // Execute the round statement (the third statement inside the for-loop)
            // We are using the forOuterScope as scope argument here
            visit(n.round, forOuterScope)
        }

        // Return NULL, because a do-while-loop has nothing to return
        return NullValue.NULL
    }


    // *******************************
    // if-else

    /**
     * Visit a if-else statement ([IfNode])
     *
     * @param n the [IfNode] to visit
     * @param scope the [Scope] for visiting the [IfNode]
     * @return the last operation-result of the executed block
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitIfNode(n: IfNode, scope: Scope): InterpreterValue {

        // Create a copy of the scope for executing the if-block
        // so the variables declared inside of the if-block do
        // not exist outside of it
        val ifScope = scope.copy()

        // Visit the condition. If it is true then visit the body, if not visit the else-body
        if (from(visit(n.condition, ifScope)).value) return visit(
            n.body,
            ifScope
        ) else if (n.elseBody != null) return visit(n.elseBody, ifScope)

        // If we had nothing to return then we will just return NullValue.NULL
        return NullValue.NULL
    }


    // *******************************
    // functions

    /**
     * Visit a [FunctionDeclarationNode]
     *
     * @param node the [FunctionDeclarationNode] to visit
     * @param scope the [Scope] for visiting the [FunctionDeclarationNode]
     * @return the [Function]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitFunctionDeclarationNode(node: FunctionDeclarationNode, scope: Scope): Function {

        // Declare the variable that contains the function
        if (!scope.getVariables()
                .declare(Variable(node.name, Function::class.java))
        ) throw Error("'" + node.name + "' is already declared!")

        // Create the function
        val f = createFunctionDeclaration(node, scope)

        // Apply the function as value to the variable
        scope.getVariables()[node.name]!!.value = f

        // return the function
        return f
    }

    /**
     * Create a [FunctionDeclarationNode]
     *
     * @param node the [FunctionDeclarationNode] to create
     * @param scope the [Scope] to create the [FunctionDeclarationNode]
     * @return the created [Function]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun createFunctionDeclaration(node: FunctionDeclarationNode, scope: Scope?): Function {

        // return a new function from the node and the scope
        return Function(node.args, node.body, scope, this, node.access!!, node.isFinal)
    }

    /**
     * Visit a [FunctionCallNode]
     *
     * @param node the [FunctionCallNode] to visit
     * @param scope the [Scope] for visiting the [FunctionCallNode]
     * @return the return [InterpreterValue] of the function-call
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitFunctionCallNode(node: FunctionCallNode, scope: Scope): InterpreterValue {

        // get the function
        val v = visit(node.function, scope)

        // call the function & return it's result
        return v.invoke(node, scope)
    }

    fun visitReturnNode(node: ReturnNode, scope: Scope): InterpreterValue {
        scope.returnValue = visit(node.value, scope)
        return NullValue.NULL
    }
    // *******************************
    // classes
    /**
     * Visit a [ClassDeclarationNode]
     *
     * @param node the [ClassDeclarationNode] to visit
     * @param scope the [Scope] to visit the [ClassDeclarationNode]
     * @return the created [ClassValue]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitClassDeclarationNode(node: ClassDeclarationNode, scope: Scope): ClassValue {

        // Declare the variable that contains the class
        if (!scope.getVariables()
                .declare(Variable(node.name, ClassValue::class.java))
        ) throw Error("'" + node.name + "' is already declared!")

        // Create the class
        val c = createClassDeclaration(node, scope)

        // Set the variable value to the class
        scope.getVariables()[node.name]!!.value = c

        // return the class
        return c
    }

    /**
     * Create a class-declaration ([ClassDeclarationNode])
     *
     * @param n the [ClassDeclarationNode] to create
     * @param scope the [Scope] to create the [ClassDeclarationNode]
     * @return the created [ClassValue]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun createClassDeclaration(
        n: ClassDeclarationNode,
        scope: Scope
    ): ClassValue {


        // Create a list for the fields of the class
        // They will not be created in the declaration
        // So we save the VariableDeclarationNodes inside
        // of the class to create them when creating the
        // object
        // We take all the fields from the class-declaration
        // here and delete the static fields from it later
        val fields = mutableListOf(*n.fields)

        // Create VariableLists for the prototype
        // (containing functions and child-classes)
        val prototype = VariableList()
        val statics = VariableList()

        // TODO 2 Declarations with the same name

        // loop over all methods of the class
        for (node in n.methods) {

            // if the method is static
            if (n.isStatic) {
                // declare a new static variable for the function
                statics.declare(
                    Variable(
                        node.name,
                        Function::class.java
                    )
                )
                // create the function and apply it to the variable
                statics[node.name]!!.value = createFunctionDeclaration(node, scope)
            } else {
                // declare a new prototype-variable for the function
                prototype.declare(
                    Variable(
                        node.name,
                        Function::class.java
                    )
                )
                // create the function and apply it to the variable
                prototype[node.name]!!.value = createFunctionDeclaration(node, scope)
            }
        }

        // loop over all sub-classes of the class
        for (node in n.classes) {
            if (n.isStatic) {
                // declare a new static variable for the class
                statics.declare(
                    Variable(
                        node.name,
                        ClassValue::class.java
                    )
                )
                // create the class and apply it to the variable
                statics[node.name]!!.value = createClassDeclaration(node, scope)
            } else {
                // declare a new prototype-variable for the class
                prototype.declare(
                    Variable(
                        node.name,
                        ClassValue::class.java
                    )
                )
                // create the class and apply it to the variable
                prototype[node.name]!!.value = createClassDeclaration(node, scope)
            }
        }

        // loop over the fields
        var i = 0
        while (i < fields.size) {


            // get the VariableDeclarationNode from the fields list
            val node = fields[i]

            // if the field is static...
            if (node.isStatic) {
                // declare a new static field for it
                statics.declare(
                    create(
                        node.name,
                        node.type,
                        node.isFinal
                    )
                )

                // ...and apply the value (visit it's value)
                // TODO Use Class Scope
                statics[node.name]!!.value = visit(node.assignment!!.value, scope)

                // remove the field from the fields list
                //
                // (as we have one field less in the list
                // we have to decrease the counter-variable
                // by one)
                fields.removeAt(i--)
            }
            i++
        }

        // create a new class

        // return the class
        return ClassValue(
            n.name, statics, fields.toTypedArray(), scope,
            this, prototype, n.access, n.isFinal
        )
    }

    /**
     * Visit a [ClassConstructionNode]
     *
     * @param n the [ClassConstructionNode] to create
     * @param scope the [Scope] to create the [ClassDeclarationNode]
     * @return the created [ClassValue]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitClassConstruction(n: ClassConstructionNode, scope: Scope): InterpreterValue {

        // TODO Arguments for constructor and constructor in variable

        // Get the class
        val v = visit(n.type, scope)

        // create a new ObjectValue from the class
        return try {
            v.newInstance(n, scope)
        } catch (error: UnformattedInterpreterError) {
            throw InterpreterError(
                error.message, n.map, n.newKeywordPosition,
                n.newKeywordPosition + 2
            )
        }
    }


    // *******************************
    // identifiers

    /**
     * Visit an [IdentifierNode]
     *
     * @param node the [IdentifierNode] to visit
     * @param scope the [Scope] to visit the [IdentifierNode]
     * @return
     */
    fun visitIdentifier(node: IdentifierNode, scope: Scope): Variable {

        // if the IdentifierNode
        return if (node.parent != null) {

            // visit the parent
            val parent = visit(node.parent, scope)

            // get the child from the parent
            val v: Variable?
            v = try {
                parent.getChild(node.name)
            } catch (e: UnformattedInterpreterError) {
                throw InterpreterError(
                    e.message, node.map, node.position,
                    node.position + node.name.length - 1, e
                )
            }

            // if the variable not declared throw an error
            if (v == null) throw InterpreterError(
                String.format("Child \"%s\" is not defined", node.name),
                node.map, node.position, node.position + node.name.length - 1
            )

            // return the variable
            v
        } else {

            // get the variable from the scope
            val v = scope.getVariables()[node.name]
                ?: throw InterpreterError(
                    String.format("Variable with name \"%s\" is not declared", node.name),
                    node.map, node.position, node.position + node.name.length - 1
                )

            // if the variable is not declared throw an error

            // return the variable
            v
        }
    }

    fun visitImportNode(node: ImportNode, scope: Scope): InterpreterValue {
        var actual: InterpreterValue? = scope.getVariables()
        val imported = node.import
        val lastIndex = imported.size - 1
        for (i in imported.indices) {
            if (imported[i] == ImportNode.EVERYTHING) {
                val children = actual!!.children
                for (c in children.indices) scope.getVariables().declare(
                    finalOf(
                        children[c], actual.getChild(children[c])!!
                    )
                )
            } else {
                actual = actual!!.getChild(imported[i])
                if (i == lastIndex) scope.getVariables().declare(
                    finalOf(
                        imported[i], actual!!
                    )
                )
            }
        }
        return NullValue.NULL
    }

    fun visitCastNode(node: CastNode, scope: Scope): InterpreterValue {
        val v = visit(node.value, scope)
        return v.castTo(node.castTarget)
    }

    override fun getExtension(): String {
        return ""
    }

    override fun getName(): String {
        return "interpreter"
    }
}