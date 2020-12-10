package com.github.nsc.de.shake.interpreter.values;

import com.github.nsc.de.shake.interpreter.Interpreter;
import com.github.nsc.de.shake.interpreter.Scope;
import com.github.nsc.de.shake.interpreter.Variable;
import com.github.nsc.de.shake.parser.node.AccessDescriber;
import com.github.nsc.de.shake.parser.node.Tree;
import com.github.nsc.de.shake.parser.node.functions.FunctionArgumentNode;
import com.github.nsc.de.shake.parser.node.functions.FunctionCallNode;

import java.util.Arrays;



/**
 * An {@link InterpreterValue} for function-declarations
 */
public class Function implements InterpreterValue {



    // *******************************
    // fields

    /**
     * The function arguments
     */
    private final FunctionArgumentNode[] args;

    /**
     * The body of the function
     */
    private final Tree body;

    /**
     * The scope the function is declared in
     */
    private final Scope scope;

    /**
     * The interpreter the function is declared in
     */
    private final Interpreter interpreter;

    /**
     * The function access
     */
    private final AccessDescriber access;

    /**
     * Is this function final?
     */
    private final boolean isFinal;


    /**
     * Constructor for {@link Function}
     *
     * @param args the {@link #args} of the function
     * @param body the {@link #body} of the function
     * @param scope the {@link #scope} of the function
     * @param interpreter the {@link #interpreter} of the function
     * @param access the {@link #access} of the function
     * @param isFinal is this class {@link #isFinal}?
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Function(FunctionArgumentNode[] args, Tree body, Scope scope, Interpreter interpreter,
                    AccessDescriber access, boolean isFinal) {
        this.args = args;
        this.body = body;
        this.scope = scope;
        this.interpreter = interpreter;
        this.access = access;
        this.isFinal = isFinal;
    }

    /**
     * Returns the same function, but you can declare what {@link Scope} ({@link #scope}) to use (for class declarations)
     *
     * @param scope the scope to use
     * @return the {@link Function} using the specified {@link Scope} ({@link #scope})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Function withScope(Scope scope) {
        // Return a new Function with the same argument as this one, just replace the scope
        return new Function(args, body, scope, interpreter, access, isFinal);
    }



    // *******************************
    // getters

    /**
     * Getter for {@link #args} (the function arguments)
     *
     * @return the function arguments (this.{@link #args})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public FunctionArgumentNode[] getArgs() {
        // just return the function arguments
        return this.args;
    }

    /**
     * Getter for {@link #body} (the function body)
     *
     * @return the function body (this.{@link #body})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Tree getBody() {
        // just return the function body
        return this.body;
    }

    /**
     * Getter for {@link #interpreter} (the function interpreter)
     *
     * @return the function interpreter (this.{@link #interpreter})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Interpreter getInterpreter() {
        // just return the function access
        return this.interpreter;
    }

    /**
     * Getter for {@link #scope} (the scope the function is declared in (for accessing variables in the function))
     *
     * @return the scope the function is declared in (for accessing variables in the function) (this.{@link #scope})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Scope getScope() {
        // just return the function scope
        return this.scope;
    }

    /**
     * Getter for {@link #access} (the function access)
     *
     * @return the function access (this.{@link #access})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public AccessDescriber getAccess() {
        // just return the function access
        return this.access;
    }

    /**
     * Getter for {@link #isFinal} (is the function final?)
     *
     * @return is the function final? (this.{@link #isFinal})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public boolean isFinal() {
        // just return isFinal
        return this.isFinal;
    }

    /**
     * Function call method
     *
     * @param node the node that called the function
     * @param scope the scope the call was made in (to process the arguments)
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public void call(FunctionCallNode node, Scope scope) {
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
        Scope function_scope = new Scope(this.getScope());

        // loop over args (prepare function_scope)
        for(int i = 0; i < this.getArgs().length; i++) {

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
            function_scope.getScopeVariables().declare(new Variable<>(this.getArgs()[i].getName(), InterpreterValue.class));

            // Set the variable to the value that is given (we use the interpreter to visit the given argument)
            //
            // also we use the scope argument here as scope because we want to use variables at the location of
            // the function call and not at the location of the function declaration
            function_scope.getScopeVariables().get(this.getArgs()[i].getName()).setValue(interpreter.visit(node.getArgs()[i], scope));

        }

        // Visits the function body using the function scope so we can use all
        // the parameters that are given to the function
        this.getInterpreter().visit(this.getBody(), function_scope);
    }



    // *******************************
    // implementations for extended InterpreterValue
    // >> get-name


    /**
     * Returns the name of the type of {@link InterpreterValue} (To identify the type of value)
     * For {@link Function} it just always returns "function"
     *
     * @return "function"
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public String getName() {
        // just return "function"
        return "function";
    }



    // *******************************
    // Override toString()

    /**
     * Returns the string representation of the {@link Function}
     *
     * @return the string representation of the {@link Function}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public String toString() {
        // just return a string-representation of the function
        return String.format("Function{access=%s,isFinal=%s,args=%s,body=%s}", access, isFinal, Arrays.toString(args), body);
    }
}
