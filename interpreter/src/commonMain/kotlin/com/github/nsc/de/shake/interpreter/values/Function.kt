package com.github.nsc.de.shake.interpreter.values

import com.github.nsc.de.shake.interpreter.Interpreter
import com.github.nsc.de.shake.interpreter.Scope
import com.github.nsc.de.shake.interpreter.Variable
import com.github.nsc.de.shake.parser.node.AccessDescriber
import com.github.nsc.de.shake.parser.node.Tree
import com.github.nsc.de.shake.parser.node.functions.FunctionArgumentNode
import com.github.nsc.de.shake.parser.node.functions.FunctionCallNode

/**
 * An [InterpreterValue] for function-declarations
 */
class Function
/**
 * Constructor for [Function]
 *
 * @param args the [.args] of the function
 * @param body the [.body] of the function
 * @param scope the [.scope] of the function
 * @param interpreter the [.interpreter] of the function
 * @param access the [.access] of the function
 * @param isFinal is this class [.isFinal]?
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
(

    /**
     * The function arguments
     */
    val args: Array<FunctionArgumentNode>,

    /**
     * The body of the function
     */
    val body: Tree,

    /**
     * The scope the function is declared in
     */
    val scope: Scope?,

    /**
     * The interpreter the function is declared in
     */
    val interpreter: Interpreter,

    /**
     * The function access
     */
    val access: AccessDescriber,

    /**
     * Is this function final?
     */
    val isFinal: Boolean

) : InterpreterValue {

    /**
     * Returns the same function, but you can declare what [Scope] ([.scope]) to use (for class declarations)
     *
     * @param scope the scope to use
     * @return the [Function] using the specified [Scope] ([.scope])
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun withScope(scope: Scope?): Function {
        // Return a new Function with the same argument as this one, just replace the scope
        return Function(args, body, scope, interpreter, access, isFinal)
    }


    /**
     * Invoke a function
     *
     * @param node the node that called the function
     * @param scope the scope the call was made in (to process the arguments)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun invoke(node: FunctionCallNode, scope: Scope): InterpreterValue {
        // TODO return statements

        // Create function scope (for the function execution)
        // As parent we use this.getScope() so you can access values
        // that are declared before the function
        //
        // [Example]
        // >> var i = 0;
        // >> function f() {
        // >>   println(i); // >> 0
        // >> }
        //
        val functionScope = Scope(this.scope, scope.interpreter)

        // loop over args (prepare function_scope)
        for (i in args.indices) {

            // declare the variable name in the function scope
            // we set the value-type to InterpreterValue.class because at the moment we
            // do not allow variable types in function declarations
            //
            // [Example]
            // >> function( int i ) { ... } // will throw an error at the moment
            //
            // [Example]
            // >> function( i ) { ... } // will work
            //
            functionScope.scopeVariables.declare(Variable(args[i].name, InterpreterValue::class))

            // Set the variable to the value that is given (we use the interpreter to visit the given argument)
            //
            // also we use the scope argument here as scope because we want to use variables at the location of
            // the function call and not at the location of the function declaration
            functionScope.scopeVariables[args[i].name]!!.value = interpreter.visit(node.args[i], scope)
        }

        // Visits the function body using the function scope so we can use all
        // the parameters that are given to the function
        interpreter.visit(body, functionScope)
        val value = functionScope.returnValue
        return value ?: NullValue.NULL
    }


    // *******************************
    // implementations for extended InterpreterValue
    // >> get-name

    /**
     * Returns the name of the type of [InterpreterValue] (To identify the type of value)
     * For [Function] it just always returns "function"
     *
     * @return "function"
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override val name: String get() = "function"


    // *******************************
    // Override toString()

    /**
     * Returns the string representation of the [Function]
     *
     * @return the string representation of the [Function]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun toString(): String {
        // just return a string-representation of the function
        return "Function{access=$access,isFinal=$isFinal,args=${args.contentToString()},body=$body}"
    }
}