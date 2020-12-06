package com.github.nsc.de.compiler.interpreter.values;


import com.github.nsc.de.compiler.interpreter.Variable;



/**
 * A Value for the Interpreter, any type of value will implement this interface
 *
 * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
 */
public interface InterpreterValue {



    // ****************************
    // number-operators

    /**
     * This function will be executed when the operator '+' is used on the value
     *
     * @param v The Value to add to this value
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    default InterpreterValue add(InterpreterValue v) {
        // Throw an error when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new Error("Operator '+' is not defined for type " + getName());
    }

    /**
     * This function will be executed when the operator '-' is used on the value
     *
     * @param v The Value to sub from this value
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    default InterpreterValue sub(InterpreterValue v) {
        // Throw an error when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new Error("Operator '-' is not defined for type " + getName());
    }

    /**
     * This function will be executed when the operator '*' is used on the value
     *
     * @param v The Value to multiply with this value
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    default InterpreterValue mul(InterpreterValue v) {
        // Throw an error when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new Error("Operator '*' is not defined for type " + getName());
    }

    /**
     * This function will be executed when the operator '/' is used on the value
     *
     * @param v The divisor-value
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    default InterpreterValue div(InterpreterValue v) {
        // Throw an error when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new Error("Operator '/' is not defined for type " + getName());
    }

    /**
     * This function will be executed when the operator '%' is used on the value
     *
     * @param v The divisor-value
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    default InterpreterValue mod(InterpreterValue v) {
        // Throw an error when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new Error("Operator '%' is not defined for type " + getName());
    }

    /**
     * This function will be executed when the operator '**' is used on the value
     *
     * @param v The exponent value
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    default InterpreterValue pow(InterpreterValue v) {
        // Throw an error when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new Error("Operator '**' is not defined for type " + getName());
    }



    // ****************************
    // boolean-operators

    /**
     * This function will be executed when the operator '||' is used on the value
     *
     * @param v The other value for the or operator
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    default InterpreterValue or(InterpreterValue v) {
        // Throw an error when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new Error("Operator '||' is not defined for type " + getName());
    }

    /**
     * This function will be executed when the operator '&amp;&amp;' is used on the value
     *
     * @param v The other value for the and operator
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    default InterpreterValue and(InterpreterValue v) {
        // Throw an error when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new Error("Operator '&&' is not defined for type " + getName());
    }



    // ****************************
    // comparison

    /**
     * This function will be executed when the operator '==' is used on the value
     *
     * @param v The value that should be the same
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    default InterpreterValue equals(InterpreterValue v) {
        // Throw an error when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new Error("Operator '==' is not defined for type " + getName());
    }

    /**
     * This function will be executed when the operator '&gt;=' is used on the value
     *
     * @param v The value that should be smaller
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    default InterpreterValue bigger_equals(InterpreterValue v) {
        // Throw an error when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new Error("Operator '>=' is not defined for type " + getName());
    }

    /**
     * This function will be executed when the operator '&lt;=' is used on the value
     *
     * @param v The value that should be bigger
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    default InterpreterValue smaller_equals(InterpreterValue v) {
        // Throw an error when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new Error("Operator '<=' is not defined for type " + getName());
    }

    /**
     * This function will be executed when the operator '&gt;' is used on the value
     *
     * @param v The value that should be smaller or equal
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    default InterpreterValue bigger(InterpreterValue v) {
        // Throw an error when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new Error("Operator '>' is not defined for type " + getName());
    }

    /**
     * This function will be executed when the operator '&lt;' is used on the value
     *
     * @param v The value that should be bigger or equal
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    default InterpreterValue smaller(InterpreterValue v) {
        // Throw an error when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new Error("Operator '<' is not defined for type " + getName());
    }



    // ****************************
    // Children

    /**
     * This function will be executed when getting a child (variable.child)
     *
     * @param c the child to get
     * @return the child variable
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    default Variable getChild(String c) {
        // Throw an error when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new Error("Can't get child values of type " + getName());
    }



    // ****************************
    // getName

    /**
     * Returns the name of the type of {@link InterpreterValue} (To identify the type of value)
     *
     * @return the name of the {@link InterpreterValue}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    String getName();

}
