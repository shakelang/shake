package io.github.shakelang.shake.interpreter

import io.github.shakelang.shake.generation.ShakeGeneratorBase
import io.github.shakelang.shake.interpreter.Variable.Companion.create
import io.github.shakelang.shake.interpreter.Variable.Companion.finalOf
import io.github.shakelang.shake.interpreter.values.*
import io.github.shakelang.shake.interpreter.values.BooleanValue.Companion.from
import io.github.shakelang.shake.interpreter.values.Function
import io.github.shakelang.shake.parser.node.*
import io.github.shakelang.shake.parser.node.expression.*
import io.github.shakelang.shake.parser.node.factor.ShakeCharacterNode
import io.github.shakelang.shake.parser.node.factor.ShakeDoubleNode
import io.github.shakelang.shake.parser.node.factor.ShakeIntegerNode
import io.github.shakelang.shake.parser.node.factor.ShakeStringNode
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionCallNode
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import io.github.shakelang.shake.parser.node.functions.ShakeReturnNode
import io.github.shakelang.shake.parser.node.logical.*
import io.github.shakelang.shake.parser.node.loops.ShakeDoWhileNode
import io.github.shakelang.shake.parser.node.loops.ShakeForNode
import io.github.shakelang.shake.parser.node.loops.ShakeWhileNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassConstructionNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import io.github.shakelang.shake.parser.node.variables.*
import kotlin.Error
import kotlin.String
import kotlin.Suppress
import kotlin.jvm.JvmOverloads

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
    val global: Scope


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
            applyDefaults()
        } catch (e: Error) {
            e.printStackTrace()
        }
    }

    @Throws(Error::class)
    fun applyDefaults() = applyDefaults(this)
/*
    private fun load(src: String, src_name: String) {
        val s = javaClass.getResourceAsStream(src)!!
        val reader = BufferedReader(InputStreamReader(s))
        val chars = CharArray(s.available())
        for (i in chars.indices) chars[i] = reader.read().toChar()
        val source = from(chars, src_name)
        val inputStream: CharacterInputStream = SourceCharacterInputStream(source)
        val lexer = Lexer(inputStream)
        val tokens = lexer.makeTokens()
        val parser = Parser(tokens)
        val tree = parser.parse()
        visit(tree)
    }

 */

    fun resetGlobals() {
        global.reset()
        try {
            applyDefaults()
        } catch (e: Error) {
            e.printStackTrace()
        }
    }

    // *******************************
    // visit function

    /**
     * Visit a [ShakeNode] (using the [.global] [Scope])
     *
     * @param n the [ShakeNode] to visit
     * @return the resulting [InterpreterValue] of the operation
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    @JvmOverloads
    fun visit(n: ShakeNode?, scope: Scope = global): InterpreterValue {
        // Check all the node-types and call the function to process it
        if (n is ShakeBlockNode) return visitTree(n, scope)
        if (n is ShakeDoubleNode) return visitDoubleNode(n)
        if (n is ShakeIntegerNode) return visitIntegerNode(n)
        if (n is ShakeStringNode) return visitStringNode(n)
        if (n is ShakeCharacterNode) return visitCharacterNode(n)
        if (n is ShakeAddNode) return visitAddNode(n, scope)
        if (n is ShakeSubNode) return visitSubNode(n, scope)
        if (n is ShakeMulNode) return visitMulNode(n, scope)
        if (n is ShakeDivNode) return visitDivNode(n, scope)
        if (n is ShakeModNode) return visitModNode(n, scope)
        if (n is ShakePowNode) return visitPowNode(n, scope)
        if (n is ShakeVariableDeclarationNode) return visitVariableDeclarationNode(n, scope)
        if (n is ShakeVariableAddAssignmentNode) return visitVariableAddAssignmentNode(n, scope)
        if (n is ShakeVariableSubAssignmentNode) return visitVariableSubAssignmentNode(n, scope)
        if (n is ShakeVariableMulAssignmentNode) return visitVariableMulAssignmentNode(n, scope)
        if (n is ShakeVariableDivAssignmentNode) return visitVariableDivAssignmentNode(n, scope)
        if (n is ShakeVariableModAssignmentNode) return visitVariableModAssignmentNode(n, scope)
        if (n is ShakeVariablePowAssignmentNode) return visitVariablePowAssignmentNode(n, scope)
        if (n is ShakeVariableIncreaseNode) return visitVariableIncreaseNode(n, scope)
        if (n is ShakeVariableDecreaseNode) return visitVariableDecreaseNode(n, scope)
        if (n is ShakeValuedNode) return visitVariableAssignmentNode(n, scope)
        if (n is ShakeVariableUsageNode) return visitVariableUsageNode(n, scope)
        if (n is ShakeLogicalEqEqualsNode) return visitEqEqualsNode(n, scope)
        if (n is ShakeLogicalBiggerEqualsNode) return visitBiggerEqualsNode(n, scope)
        if (n is ShakeLogicalSmallerEqualsNode) return visitSmallerEqualsNode(n, scope)
        if (n is ShakeLogicalBiggerNode) return visitBiggerNode(n, scope)
        if (n is ShakeLogicalSmallerNode) return visitSmallerNode(n, scope)
        if (n is ShakeLogicalAndNode) return visitLogicalAndNode(n, scope)
        if (n is ShakeLogicalXOrNode) return visitLogicalXOrNode(n, scope)
        if (n is ShakeLogicalOrNode) return visitLogicalOrNode(n, scope)
        if (n is ShakeWhileNode) return visitWhileNode(n, scope)
        if (n is ShakeDoWhileNode) return visitDoWhileNode(n, scope)
        if (n is ShakeForNode) return visitForNode(n, scope)
        if (n is ShakeIfNode) return visitIfNode(n, scope)
        if (n is ShakeFunctionDeclarationNode) return visitFunctionDeclarationNode(n, scope)
        if (n is ShakeFunctionCallNode) return visitFunctionCallNode(n, scope)
        if (n is ShakeReturnNode) return visitReturnNode(n, scope)
        if (n is ShakeIdentifierNode) return visitIdentifier(n, scope)
        if (n is ShakeClassConstructionNode) return visitClassConstruction(n, scope)
        if (n is ShakeClassDeclarationNode) return visitClassDeclarationNode(n, scope)
        if (n is ShakeImportNode) return visitImportNode(n, scope)
        if (n is ShakeCastNode) return visitCastNode(n, scope)

        // if the node a LogicalTrueNode return TRUE, if it is a LogicalFalseNode return false
        if (n is ShakeLogicalTrueNode) return BooleanValue.TRUE
        if (n is ShakeLogicalFalseNode) return BooleanValue.FALSE

        // if the node is null return NullValue.NULL
        if (n == null) return NullValue.NULL
        throw Error("It looks like that Node is not implemented in the Interpreter")
    }


    // *******************************
    // visit function

    /**
     * Visit a [ShakeBlockNode]
     *
     * @param t the [ShakeBlockNode] to visit
     * @param scope the scope to use for visiting the [ShakeBlockNode]
     * @return the latest [InterpreterValue] of the tree
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitTree(t: ShakeBlockNode, scope: Scope): InterpreterValue {

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

    fun visitCharacterNode(n: ShakeCharacterNode): InterpreterValue {
        return CharacterValue(n.value)
    }

    fun visitStringNode(n: ShakeStringNode): InterpreterValue {
        return StringValue(n.value)
    }

    /**
     * Visit an [ShakeIntegerNode]
     *
     * @param n the [ShakeIntegerNode] to process
     * @return the [IntegerValue] that is created
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitIntegerNode(n: ShakeIntegerNode): IntegerValue {
        // Just create a IntegerValue from the IntegerNode value and return it
        return IntegerValue(n.number)
    }

    /**
     * Visit a [ShakeDoubleNode]
     *
     * @param n the [ShakeDoubleNode] to process
     * @return the [DoubleValue] that is created
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitDoubleNode(n: ShakeDoubleNode): DoubleValue {
        // Just create a DoubleValue from the DoubleNode value and return it
        return DoubleValue(n.number)
    }


    // *******************************
    // calculations

    /**
     * Visit an [ShakeAddNode] (Addition)
     *
     * @param n the [ShakeAddNode] to visit
     * @param scope the [Scope] for visiting the [ShakeAddNode]
     * @return the addition-result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitAddNode(n: ShakeAddNode, scope: Scope): InterpreterValue {
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
     * Visit a [ShakeSubNode] (Subtraction)
     *
     * @param n the [ShakeSubNode] to visit
     * @param scope the [Scope] for visiting the [ShakeSubNode]
     * @return the subtraction-result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitSubNode(n: ShakeSubNode, scope: Scope): InterpreterValue {
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
     * Visit a [ShakeMulNode] (Multiplication)
     *
     * @param n the [ShakeMulNode] to visit
     * @param scope the [Scope] for visiting the [ShakeMulNode]
     * @return the multiplication-result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitMulNode(n: ShakeMulNode, scope: Scope): InterpreterValue {
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
     * Visit a [ShakeDivNode] (Division)
     *
     * @param n the [ShakeDivNode] to visit
     * @param scope the [Scope] for visiting the [ShakeDivNode]
     * @return the division-result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitDivNode(n: ShakeDivNode, scope: Scope): InterpreterValue {
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
     * Visit a [ShakeModNode] (Modulo)
     *
     * @param n the [ShakeModNode] to visit
     * @param scope the [Scope] for visiting the [ShakeModNode]
     * @return the modulo-result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitModNode(n: ShakeModNode, scope: Scope): InterpreterValue {
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
     * Visit a [ShakePowNode] (Modulo)
     *
     * @param n the [ShakePowNode] to visit
     * @param scope the [Scope] for visiting the [ShakePowNode]
     * @return the power-result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitPowNode(n: ShakePowNode, scope: Scope): InterpreterValue {
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
     * Visit a [ShakeVariableDeclarationNode]
     *
     * @param n the [ShakeVariableDeclarationNode] to process
     * @param scope the [Scope] for visiting the [ShakeVariableDeclarationNode]
     * @return If the [Variable] gets a value assigned the value that is assigned, if not NULL
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitVariableDeclarationNode(n: ShakeVariableDeclarationNode, scope: Scope): InterpreterValue {
        val value = if (n.value != null) visit(n.value!!.value, scope) else null
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
     * Visit a [ShakeValuedNode]
     *
     * @param n the [ShakeValuedNode] to visit
     * @param scope the [Scope] for visiting the [ShakeValuedNode]
     * @return The value that is assigned to the variable
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitVariableAssignmentNode(n: ShakeValuedNode, scope: Scope): InterpreterValue {
        val variable = visit(n.variable, scope) as Variable
        val value = visit(n.value, scope)
        variable.value = value
        return value
    }

    /**
     * Visit a [ShakeVariableAddAssignmentNode]
     *
     * @param n the [ShakeVariableAddAssignmentNode] to visit
     * @param scope the [Scope] for visiting the [ShakeVariableAddAssignmentNode]
     * @return The value that is assigned to the variable (So the old variable value plus the value given.)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitVariableAddAssignmentNode(n: ShakeVariableAddAssignmentNode, scope: Scope): InterpreterValue {
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
     * Visit a [ShakeVariableSubAssignmentNode]
     *
     * @param n the [ShakeVariableSubAssignmentNode] to visit
     * @param scope the [Scope] for visiting the [ShakeVariableSubAssignmentNode]
     * @return The value that is assigned to the variable (So the old variable value minus the value given.)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitVariableSubAssignmentNode(n: ShakeVariableSubAssignmentNode, scope: Scope): InterpreterValue {
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
     * Visit a [ShakeVariableMulAssignmentNode]
     *
     * @param n the [ShakeVariableMulAssignmentNode] to visit
     * @param scope the [Scope] for visiting the [ShakeVariableMulAssignmentNode]
     * @return The value that is assigned to the variable (So the old variable value times the value given.)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitVariableMulAssignmentNode(n: ShakeVariableMulAssignmentNode, scope: Scope): InterpreterValue {
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
     * Visit a [ShakeVariableDivAssignmentNode]
     *
     * @param n the [ShakeVariableDivAssignmentNode] to visit
     * @param scope the [Scope] for visiting the [ShakeVariableDivAssignmentNode]
     * @return The value that is assigned to the variable (So the old variable value by the value given.)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitVariableDivAssignmentNode(n: ShakeVariableDivAssignmentNode, scope: Scope): InterpreterValue {
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
     * Visit a [ShakeVariableModAssignmentNode]
     *
     * @param n the [ShakeVariableModAssignmentNode] to visit
     * @param scope the [Scope] for visiting the [ShakeVariableModAssignmentNode]
     * @return The value that is assigned to the variable (So the old variable value modulo the value given.)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitVariableModAssignmentNode(n: ShakeVariableModAssignmentNode, scope: Scope): InterpreterValue {
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
     * Visit a [ShakeVariablePowAssignmentNode]
     *
     * @param n the [ShakeVariablePowAssignmentNode] to visit
     * @param scope the [Scope] for visiting the [ShakeVariablePowAssignmentNode]
     * @return The value that is assigned to the variable (So the old variable value power the value given.)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitVariablePowAssignmentNode(n: ShakeVariablePowAssignmentNode, scope: Scope): InterpreterValue {
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
     * Visit a [ShakeVariableIncreaseNode]
     *
     * @param n the [ShakeVariableIncreaseNode] to visit
     * @param scope the [Scope] for visiting the [ShakeVariableIncreaseNode]
     * @return The old value of the Variable
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitVariableIncreaseNode(n: ShakeVariableIncreaseNode, scope: Scope): InterpreterValue {
        val variable = visit(n.variable, scope) as Variable
        val v = variable.value
        variable.value = v.add(IntegerValue.ONE)
        return v
    }

    /**
     * Visit a [ShakeVariableDecreaseNode]
     *
     * @param n the [ShakeVariableDecreaseNode] to visit
     * @param scope the [Scope] for visiting the [ShakeVariableDecreaseNode]
     * @return The old value of the Variable
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitVariableDecreaseNode(n: ShakeVariableDecreaseNode, scope: Scope): InterpreterValue {
        val variable = visit(n.variable, scope) as Variable
        val v = variable.value
        variable.value = v.sub(IntegerValue.ONE)
        return v
    }

    /**
     * Visit a [ShakeVariableUsageNode]
     *
     * @param n the [ShakeVariableUsageNode] to visit
     * @param scope the [Scope] for visiting the [ShakeVariableUsageNode]
     * @return The value of the [Variable]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitVariableUsageNode(n: ShakeVariableUsageNode, scope: Scope): InterpreterValue {
        return visitIdentifier(n.variable, scope).value
    }


    // *******************************
    // variables

    /**
     * Visit a [ShakeLogicalEqEqualsNode]
     *
     * @param n the [ShakeLogicalEqEqualsNode] to visit
     * @param scope the [Scope] for visiting the [ShakeLogicalEqEqualsNode]
     * @return are the two sides of the [ShakeLogicalEqEqualsNode] the same?
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitEqEqualsNode(n: ShakeLogicalEqEqualsNode, scope: Scope): InterpreterValue {
        // visit both sides of the term
        // call the method equals() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.left, scope).equals(visit(n.right, scope))
    }

    /**
     * Visit a [ShakeLogicalBiggerEqualsNode]
     *
     * @param n the [ShakeLogicalBiggerEqualsNode] to visit
     * @param scope the [Scope] for visiting the [ShakeLogicalBiggerEqualsNode]
     * @return is the left side of the [ShakeLogicalBiggerEqualsNode] bigger or equal to the right side
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitBiggerEqualsNode(n: ShakeLogicalBiggerEqualsNode, scope: Scope): InterpreterValue {
        // visit both sides of the term
        // call the method bigger_equals() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.left, scope).biggerEquals(visit(n.right, scope))
    }

    /**
     * Visit a [ShakeLogicalSmallerEqualsNode]
     *
     * @param n the [ShakeLogicalSmallerEqualsNode] to visit
     * @param scope the [Scope] for visiting the [ShakeLogicalSmallerEqualsNode]
     * @return is the left side of the [ShakeLogicalSmallerEqualsNode] smaller or equal to the right side
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitSmallerEqualsNode(n: ShakeLogicalSmallerEqualsNode, scope: Scope): InterpreterValue {
        // visit both sides of the term
        // call the method smaller_equals() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.left, scope).smallerEquals(visit(n.right, scope))
    }

    /**
     * Visit a [ShakeLogicalBiggerEqualsNode]
     *
     * @param n the [ShakeLogicalBiggerEqualsNode] to visit
     * @param scope the [Scope] for visiting the [ShakeLogicalBiggerEqualsNode]
     * @return is the left side of the [ShakeLogicalBiggerEqualsNode] bigger than the right side
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitBiggerNode(n: ShakeLogicalBiggerNode, scope: Scope): InterpreterValue {
        // visit both sides of the term
        // call the method bigger() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.left, scope).bigger(visit(n.right, scope))
    }

    /**
     * Visit a [ShakeLogicalSmallerEqualsNode]
     *
     * @param n the [ShakeLogicalSmallerEqualsNode] to visit
     * @param scope the [Scope] for visiting the [ShakeLogicalSmallerEqualsNode]
     * @return is the left side of the [ShakeLogicalSmallerEqualsNode] bigger than the right side
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitSmallerNode(n: ShakeLogicalSmallerNode, scope: Scope): InterpreterValue {
        // visit both sides of the term
        // call the method smaller() on the result of the left InterpreterValue and
        // give the right result as argument.
        return visit(n.left, scope).smaller(visit(n.right, scope))
    }
    // *******************************
    // logical concatenation
    /**
     * Visit a [ShakeLogicalAndNode]
     *
     * @param n the [ShakeLogicalAndNode] to visit
     * @param scope the [Scope] for visiting the [ShakeLogicalAndNode]
     * @return is at least one of the two sides true
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitLogicalAndNode(n: ShakeLogicalAndNode, scope: Scope): InterpreterValue {
        return visit(n.left, scope).and(visit(n.right, scope))
    }

    /**
     * Visit a [ShakeLogicalOrNode]
     *
     * @param n the [ShakeLogicalOrNode] to visit
     * @param scope the [Scope] for visiting the [ShakeLogicalOrNode]
     * @return are both sides true
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitLogicalOrNode(n: ShakeLogicalOrNode, scope: Scope): InterpreterValue {
        return visit(n.left, scope).or(visit(n.right, scope))
    }

    /**
     * Visit a [ShakeLogicalXOrNode]
     *
     * @param n the [ShakeLogicalXOrNode] to visit
     * @param scope the [Scope] for visiting the [ShakeLogicalOrNode]
     * @return are both sides true
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitLogicalXOrNode(n: ShakeLogicalXOrNode, scope: Scope): InterpreterValue {
        return visit(n.left, scope).xor(visit(n.right, scope))
    }


    // *******************************
    // loops

    /**
     * Visit a [ShakeWhileNode]
     *
     * @param n the [ShakeWhileNode] to visit
     * @param scope the [Scope] for visiting the [ShakeWhileNode]
     * @return NullValue.NULL (a while loop does not return a value)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitWhileNode(n: ShakeWhileNode, scope: Scope): InterpreterValue {

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
     * Visit a [ShakeDoWhileNode]
     *
     * @param n the [ShakeDoWhileNode] to visit
     * @param scope the [Scope] for visiting the [ShakeDoWhileNode]
     * @return NullValue.NULL (a do-while loop does not return a value)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitDoWhileNode(n: ShakeDoWhileNode, scope: Scope): InterpreterValue {

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
     * Visit a [ShakeForNode]
     *
     * @param n the [ShakeForNode] to visit
     * @param scope the [Scope] for visiting the [ShakeForNode]
     * @return NullValue.NULL (a for loop does not return a value)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitForNode(n: ShakeForNode, scope: Scope): InterpreterValue {

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
     * Visit a if-else statement ([ShakeIfNode])
     *
     * @param n the [ShakeIfNode] to visit
     * @param scope the [Scope] for visiting the [ShakeIfNode]
     * @return the last operation-result of the executed block
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitIfNode(n: ShakeIfNode, scope: Scope): InterpreterValue {

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
     * Visit a [ShakeFunctionDeclarationNode]
     *
     * @param node the [ShakeFunctionDeclarationNode] to visit
     * @param scope the [Scope] for visiting the [ShakeFunctionDeclarationNode]
     * @return the [Function]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitFunctionDeclarationNode(node: ShakeFunctionDeclarationNode, scope: Scope): Function {

        // Declare the variable that contains the function
        if (!scope.getVariables()
                .declare(Variable(node.name, Function::class))
        ) throw Error("'" + node.name + "' is already declared!")

        // Create the function
        val f = createFunctionDeclaration(node, scope)

        // Apply the function as value to the variable
        scope.getVariables()[node.name]!!.value = f

        // return the function
        return f
    }

    /**
     * Create a [ShakeFunctionDeclarationNode]
     *
     * @param node the [ShakeFunctionDeclarationNode] to create
     * @param scope the [Scope] to create the [ShakeFunctionDeclarationNode]
     * @return the created [Function]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun createFunctionDeclaration(node: ShakeFunctionDeclarationNode, scope: Scope?): Function {

        // return a new function from the node and the scope
        return Function(node.args, node.body, scope, this, node.access!!, node.isFinal)
    }

    /**
     * Visit a [ShakeFunctionCallNode]
     *
     * @param node the [ShakeFunctionCallNode] to visit
     * @param scope the [Scope] for visiting the [ShakeFunctionCallNode]
     * @return the return [InterpreterValue] of the function-call
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitFunctionCallNode(node: ShakeFunctionCallNode, scope: Scope): InterpreterValue {

        // get the function
        val v = visit(node.function, scope)

        // call the function & return it's result
        return v.invoke(node, scope)
    }

    fun visitReturnNode(node: ShakeReturnNode, scope: Scope): InterpreterValue {
        scope.returnValue = visit(node.value, scope)
        return NullValue.NULL
    }
    // *******************************
    // classes
    /**
     * Visit a [ShakeClassDeclarationNode]
     *
     * @param node the [ShakeClassDeclarationNode] to visit
     * @param scope the [Scope] to visit the [ShakeClassDeclarationNode]
     * @return the created [ClassValue]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitClassDeclarationNode(node: ShakeClassDeclarationNode, scope: Scope): ClassValue {

        // Declare the variable that contains the class
        if (!scope.getVariables()
                .declare(Variable(node.name, ClassValue::class))
        ) throw Error("'" + node.name + "' is already declared!")

        // Create the class
        val c = createClassDeclaration(node, scope)

        // Set the variable value to the class
        scope.getVariables()[node.name]!!.value = c

        // return the class
        return c
    }

    /**
     * Create a class-declaration ([ShakeClassDeclarationNode])
     *
     * @param n the [ShakeClassDeclarationNode] to create
     * @param scope the [Scope] to create the [ShakeClassDeclarationNode]
     * @return the created [ClassValue]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun createClassDeclaration(
        n: ShakeClassDeclarationNode,
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
                        Function::class
                    )
                )
                // create the function and apply it to the variable
                statics[node.name]!!.value = createFunctionDeclaration(node, scope)
            } else {
                // declare a new prototype-variable for the function
                prototype.declare(
                    Variable(
                        node.name,
                        Function::class
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
                        ClassValue::class
                    )
                )
                // create the class and apply it to the variable
                statics[node.name]!!.value = createClassDeclaration(node, scope)
            } else {
                // declare a new prototype-variable for the class
                prototype.declare(
                    Variable(
                        node.name,
                        ClassValue::class
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
                statics[node.name]!!.value = visit(node.value!!.value, scope)

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
     * Visit a [ShakeClassConstructionNode]
     *
     * @param n the [ShakeClassConstructionNode] to create
     * @param scope the [Scope] to create the [ShakeClassDeclarationNode]
     * @return the created [ClassValue]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun visitClassConstruction(n: ShakeClassConstructionNode, scope: Scope): InterpreterValue {

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
     * Visit an [ShakeIdentifierNode]
     *
     * @param node the [ShakeIdentifierNode] to visit
     * @param scope the [Scope] to visit the [ShakeIdentifierNode]
     * @return
     */
    fun visitIdentifier(node: ShakeIdentifierNode, scope: Scope): Variable {

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
                "Child \"${node.name}\" is not defined",
                node.map, node.position, node.position + node.name.length - 1
            )

            // return the variable
            v
        } else {

            // get the variable from the scope
            val v = scope.getVariables()[node.name]
                ?: throw InterpreterError(
                    "Variable with name \"${node.name}\" is not declared",
                    node.map, node.position, node.position + node.name.length - 1
                )

            // if the variable is not declared throw an error

            // return the variable
            v
        }
    }

    fun visitImportNode(node: ShakeImportNode, scope: Scope): InterpreterValue {
        var actual: InterpreterValue? = scope.getVariables()
        val imported = node.import
        val lastIndex = imported.size - 1
        for (i in imported.indices) {
            if (imported[i] == ShakeImportNode.EVERYTHING) {
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

    fun visitCastNode(node: ShakeCastNode, scope: Scope): InterpreterValue {
        val v = visit(node.value, scope)
        return v.castTo(node.castTarget)
    }

    override val extension: String get() = ""
    override val name: String get() = "interpreter"
}


@Throws(Error::class)
expect fun applyDefaults(interpreter: Interpreter)