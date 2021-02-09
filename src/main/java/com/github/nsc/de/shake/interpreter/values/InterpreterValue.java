package com.github.nsc.de.shake.interpreter.values;


import com.github.nsc.de.shake.interpreter.InterpretationTools;
import com.github.nsc.de.shake.interpreter.Scope;
import com.github.nsc.de.shake.interpreter.UnformattedInterpreterError;
import com.github.nsc.de.shake.interpreter.Variable;
import com.github.nsc.de.shake.parser.node.functions.FunctionCallNode;
import com.github.nsc.de.shake.parser.node.objects.ClassConstructionNode;


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
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new UnformattedInterpreterError("Operator '+' is not defined for type " + getName());
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
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new UnformattedInterpreterError("Operator '-' is not defined for type " + getName());
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
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new UnformattedInterpreterError("Operator '*' is not defined for type " + getName());
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
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new UnformattedInterpreterError("Operator '/' is not defined for type " + getName());
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
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new UnformattedInterpreterError("Operator '%' is not defined for type " + getName());
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
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new UnformattedInterpreterError("Operator '**' is not defined for type " + getName());
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
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new UnformattedInterpreterError("Operator '||' is not defined for type " + getName());
    }

    /**
     * This function will be executed when the operator '^' is used on the value
     *
     * @param v The other value for the or operator
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    default InterpreterValue xor(InterpreterValue v) {
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new UnformattedInterpreterError("Operator '^' is not defined for type " + getName());
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
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new UnformattedInterpreterError("Operator '&&' is not defined for type " + getName());
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
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new UnformattedInterpreterError("Operator '==' is not defined for type " + getName());
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
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new UnformattedInterpreterError("Operator '>=' is not defined for type " + getName());
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
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new UnformattedInterpreterError("Operator '<=' is not defined for type " + getName());
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
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new UnformattedInterpreterError("Operator '>' is not defined for type " + getName());
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
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new UnformattedInterpreterError("Operator '<' is not defined for type " + getName());
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
    default Variable<?> getChild(String c) {
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new UnformattedInterpreterError("Can't get child values of type " + getName());
    }

    /**
     * This function will be executed when getting all child keys
     *
     * @return the keys of all children
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    default String[] getChildren() {
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new UnformattedInterpreterError("Can't get child values of type " + getName());
    }



    // ****************************
    // invoking

    /**
     * Invoke a value
     *
     * @param node the node that called the function
     * @param scope the scope the call was made in (to process the arguments)
     * @return the value of the function
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    default InterpreterValue invoke(FunctionCallNode node, Scope scope, InterpretationTools tools) {
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new UnformattedInterpreterError("Can't invoke type " + getName());
    }

    /**
     * Create a new instance of a class
     *
     * @param node the node that created the instance
     * @param scope the scope the creation was made in (to process the arguments)
     * @return the created {@link InterpreterValue}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    default InterpreterValue newInstance(ClassConstructionNode node, Scope scope, InterpretationTools tools) {
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw new UnformattedInterpreterError("Can't create a new instance of type " + getName());
    }



    // ****************************
    // converting & casting

    /**
     * Converts this value to another value
     *
     * @param type the type to convert to
     * @param <T> the type to convert to
     * @return the converted {@link InterpreterValue}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @SuppressWarnings("unchecked")
    default <T extends InterpreterValue> T to(Class<T> type) {
        if(type.isInstance(this)) return (T) this;
        throw new UnformattedInterpreterError("Can't convert " + getName() + " to type " + type.getName());
    }

    /**
     * Casts this value to another value
     *
     * @param type the type to cast to
     * @param <T> the type to cast to
     * @return the converted {@link InterpreterValue}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @SuppressWarnings("unchecked")
    default <T extends InterpreterValue> T castTo(Class<T> type) {
        if(type.isInstance(this)) return (T) this;
        throw new UnformattedInterpreterError("Can't convert " + getName() + " to type " + type.getName());
    }



    // ****************************
    // create a java-representation of the InterpreterValue

    /**
     * Get the java-representation of the {@link InterpreterValue}
     *
     * @return the java-representation of the {@link InterpreterValue}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    default Object toJava() {
        return getName();
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


    @SuppressWarnings({"unchecked", "rawtypes"})
    static InterpreterValue of(Object value) {

        if(value == null) return NullValue.NULL;
        if(value instanceof Byte) return new IntegerValue(((Byte) value));
        if(value instanceof Short) return new IntegerValue((((Short) value).byteValue()));
        if(value instanceof Integer) return new IntegerValue(((Integer) value));
        if(value instanceof Long) return new IntegerValue((((Long) value).intValue()));

        if(value instanceof Float) return new DoubleValue((((Float) value).doubleValue()));
        if(value instanceof Double) return new DoubleValue(((Double) value));

        if(value instanceof Boolean) return BooleanValue.from((Boolean) value);
        if(value instanceof Character) return new CharacterValue((Character) value);

        if(value instanceof Class) return new Java.JavaClass((Class<?>) value);
        if(value instanceof String) return new StringValue((String) value);

        return new Java.JavaValue(value.getClass(), value);

    }

}
