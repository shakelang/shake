package com.github.nsc.de.shake.interpreter;

import com.github.nsc.de.shake.interpreter.values.*;
import com.github.nsc.de.shake.interpreter.values.ClassValue;
import com.github.nsc.de.shake.parser.node.AccessDescriber;
import com.github.nsc.de.shake.parser.node.VariableType;
import com.github.nsc.de.shake.parser.node.functions.FunctionCallNode;
import com.github.nsc.de.shake.parser.node.objects.ClassConstructionNode;

import java.lang.Class;


/**
 * Variable class to keep all {@link InterpreterValue}s in variables
 * Type argument is the type of value that
 *
 * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
 */
public class Variable<V extends InterpreterValue> implements InterpreterValue {



    // *******************************
    // fields

    /**
     * The {@link Variable} identifier
     */
    private final String identifier;

    /**
     * The access of the variable
     */
    private final AccessDescriber access;

    /**
     * The type of the variable
     */
    private final Class<V> type;

    /**
     * The value of the variable
     */
    private V value;

    /**
     * This boolean is only true while processing the {@link #toString()} function.
     *
     * In some cases toString can be executed recursively, so this variable
     * is for preventing a {@link StackOverflowError}. When the {@link #toString()}
     * starts this boolean is set to true and if the function gets called again it
     * just returns the {@link #identifier} instead of all the children. At the end
     * of {@link #toString()} this boolean will be set to false again.
     */
    private boolean toString = false;



    // *******************************
    // Constructors

    /**
     * Constructor for {@link Variable}
     *
     * @param identifier the identifier of the variable
     * @param access the access type of the variable
     * @param value the value of the variable
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Variable(String identifier, AccessDescriber access, Class<V> type, V value) {
        // apply the given values
        this.identifier = identifier;
        this.access = access;
        this.value = value;
        this.type = type;
    }

    /**
     * Constructor for {@link Variable}
     *
     * @param identifier the identifier of the variable
     * @param value the value of the variable
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Variable(String identifier, Class<V> type, V value) {
        // call other constructor using default AccessDescriber: PACKAGE
        this(identifier, AccessDescriber.PACKAGE, type, value);
    }

    /**
     * Constructor for {@link Variable}
     *
     * @param identifier the identifier of the variable
     * @param access the access of the
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Variable(String identifier, AccessDescriber access, Class<V> type) {
        // call other constructor using default value: null
        this(identifier, access, type, null);
    }

    /**
     * Constructor for {@link Variable}
     *
     * @param identifier the identifier of the variable
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Variable(String identifier, Class<V> type) {
        // call other constructor using default AccessDescriber: PACKAGE
        this(identifier, AccessDescriber.PACKAGE, type);
    }



    // *******************************
    // getters

    /**
     * Getter for the variable-type ({@link #type})
     *
     * @return {@link #type} (the variable-type)
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Class<V> getType() {
        // return the variable type
        return this.type;
    }

    /**
     * Getter for {@link #identifier} (the variable identifier)
     *
     * @return the variable identifier (this.{@link #identifier})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public String getIdentifier() {
        // return the identifier field
        return identifier;
    }

    /**
     * Getter for {@link #value} (the variable value)
     *
     * @return the variable value (this.{@link #value})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public InterpreterValue getValue() {
        // if the value field is not null return it
        // in other case return NullValue.NULL
        return value != null ? value : NullValue.NULL;
    }

    /**
     * Getter for {@link #access} (the variable access)
     *
     * @return the variable access (this.{@link #access})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public AccessDescriber getAccess() {
        // return the access field
        return access;
    }



    // *******************************
    // setters

    /**
     * Setter for {@link #value} (the variable value)
     *
     * @param value the value to set (value for {@link #value}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public void setValue(V value) {
        // set the value of the value field to the given value variable
        this.value = value;
    }

    /**
     * Checks if the {@link Variable} has a value ({@link #value} != null)
     *
     * @return has the {@link Variable} a value?
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public boolean hasValue() {
        // check if the field value is not null
        return this.value != null;
    }



    // ****************************
    // implementations for extended InterpreterValue
    // number-operators

    /**
     * This function will be executed when the operator '+' is used on the value
     *
     * @param v The Value to add to this value
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue add(InterpreterValue v) {
        // redirect operator to the value
        return this.getValue().add(v);
    }

    /**
     * This function will be executed when the operator '-' is used on the value
     *
     * @param v The Value to sub from this value
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue sub(InterpreterValue v) {
        // redirect operator to the value
        return this.getValue().sub(v);
    }

    /**
     * This function will be executed when the operator '*' is used on the value
     *
     * @param v The Value to multiply with this value
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue mul(InterpreterValue v) {
        // redirect operator to the value
        return this.getValue().mul(v);
    }

    /**
     * This function will be executed when the operator '/' is used on the value
     *
     * @param v The divisor-value
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue div(InterpreterValue v) {
        // redirect operator to the value
        return this.getValue().div(v);
    }

    /**
     * This function will be executed when the operator '%' is used on the value
     *
     * @param v The divisor-value
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue mod(InterpreterValue v) {
        // redirect operator to the value
        return this.getValue().mod(v);
    }

    /**
     * This function will be executed when the operator '**' is used on the value
     *
     * @param v The exponent value
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue pow(InterpreterValue v) {
        // redirect operator to the value
        return this.getValue().pow(v);
    }



    // ****************************
    // implementations for extended InterpreterValue
    // boolean-operators

    /**
     * This function will be executed when the operator '||' is used on the value
     *
     * @param v The other value for the or operator
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue or(InterpreterValue v) {
        // redirect operator to the value
        return this.getValue().or(v);
    }

    /**
     * This function will be executed when the operator '&amp;&amp;' is used on the value
     *
     * @param v The other value for the and operator
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue and(InterpreterValue v) {
        // redirect operator to the value
        return this.getValue().and(v);
    }



    // ****************************
    // implementations for extended InterpreterValue
    // comparison

    /**
     * This function will be executed when the operator '==' is used on the value
     *
     * @param v The value that should be the same
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue equals(InterpreterValue v) {
        // redirect operator to the value
        return this.getValue().equals(v);
    }

    /**
     * This function will be executed when the operator '&gt;=' is used on the value
     *
     * @param v The value that should be smaller
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue bigger_equals(InterpreterValue v) {
        // redirect operator to the value
        return this.getValue().bigger_equals(v);
    }

    /**
     * This function will be executed when the operator '&lt;=' is used on the value
     *
     * @param v The value that should be bigger
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue smaller_equals(InterpreterValue v) {
        // redirect operator to the value
        return this.getValue().smaller_equals(v);
    }

    /**
     * This function will be executed when the operator '&gt;' is used on the value
     *
     * @param v The value that should be smaller or equal
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue bigger(InterpreterValue v) {
        // redirect operator to the value
        return this.getValue().bigger(v);
    }

    /**
     * This function will be executed when the operator '&lt;' is used on the value
     *
     * @param v The value that should be bigger or equal
     * @return The Calculation-Result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue smaller(InterpreterValue v) {
        // redirect operator to the value
        return this.getValue().smaller(v);
    }



    // ****************************
    // implementations for extended InterpreterValue
    // Children

    /**
     * This function will be executed when getting a child (variable.child)
     *
     * @param c the child to get
     * @return the child variable
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public Variable getChild(String c) {
        // redirect to the value
        return this.getValue().getChild(c);
    }

    /**
     * Invoke a value
     *
     * @param node the node that called the function
     * @param scope the scope the call was made in (to process the arguments)
     *
     * @return the function result
     */
    @Override
    public InterpreterValue invoke(FunctionCallNode node, Scope scope) {
        // redirect operator to the value
        return this.getValue().invoke(node, scope);
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
    @Override
    public InterpreterValue newInstance(ClassConstructionNode node, Scope scope) {
        return this.getValue().newInstance(node, scope);
    }

    // ****************************
    // implementations for extended InterpreterValue
    // get-name

    /**
     * Returns the name of the type of {@link InterpreterValue} (To identify the type of value)
     *
     * @return the name of the {@link InterpreterValue}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public String getName() {
        // redirect operator to the value
        return this.getValue().getName();
    }



    // ****************************
    // copy

    /**
     * Copies the variable
     *
     * @return the copied {@link Variable}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Variable<V> copy() {
        // return a new Variable using the same values
        return new Variable<V>(this.identifier, this.access, this.type, this.value);
    }



    // *******************************
    // Override toString()

    /**
     * Returns the string representation of the {@link Variable}
     *
     * @return the string representation of the {@link Variable}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public String toString() {

        // prevent infinite recursion
        if(!toString) {

            // set toString to true to prevent infinite recursion
            toString = true;

            // create a string from the variable
            String ret =  "Variable{" +
                    "identifier='" + identifier + '\'' +
                    ", type=" + getType().getSimpleName() +
                    ", access=" + access +
                    ", value=" + value +
                    '}';

            // set toString to false again to reset the toString function
            toString = false;

            // return the generated string
            return ret;

        }
        else {
            // return a simple version of the Variable#toString() result
            return "Variable{identifier='" + identifier + "', ...}";
        }
    }

    /**
     * Returns the same variable, but you can declare what {@link Scope} to use (for class declarations)
     *
     * @param scope the scope to use
     * @return the {@link Function} using the specified {@link Scope}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Variable<V> withScope(Scope scope) {
        return new Variable<V>(identifier, access, type, useScope(value, scope));
    }

    /**
     * A function that applies a scope to an {@link InterpreterValue}
     *
     * @param v the value to apply the scope to
     * @param scope the scope to use
     * @param <V> the type of {@link InterpreterValue} that is given as argument
     * @return the given {@link InterpreterValue}, but it now uses the given {@link Scope}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    private static <V extends InterpreterValue> V useScope(V v, Scope scope) {
        if(v instanceof Variable) return (V) ((Variable) v).withScope(scope);
        if(v instanceof VariableList) return (V) ((VariableList) v).withScope(scope);
        if(v instanceof Function) return (V) ((Function) v).withScope(scope);
        if(v instanceof ClassValue)
            return (V) ((ClassValue) v).withScope(scope);
        return v;
    }



    /**
     * This function just converts a {@link VariableType} into a {@link Variable}
     *
     * @param type the type to convert
     * @return the converted type
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public static Variable<?> valueOf(String name, VariableType type) {
        switch (type.getType()) {

            // Return for all number-value types without decimal places the IntegerValue class
            case BYTE:
            case SHORT:
            case INTEGER:
            case LONG:
                new Variable<IntegerValue>(name, IntegerValue.class);

            // Return for all number-value types with decimal places the DoubleValue class
            case FLOAT:
            case DOUBLE:
                return new Variable<DoubleValue>(name, DoubleValue.class);

            // For booleans just return the BooleanValue class
            case BOOLEAN:
                return new Variable<BooleanValue>(name, BooleanValue.class);

            // For Objects return the ObjectValue class
            case OBJECT:
                return new Variable<ObjectValue>(name, ObjectValue.class); // TODO object-subtypes

            // For Dynamic return the InterpreterValue class (as super-class of all
            // interpreter-values it can hold all of them)
            case DYNAMIC:
                return new Variable<>(name, InterpreterValue.class);

            // TODO Char & Array are not implemented at the moment
            case CHAR:
            case ARRAY:
                throw new Error("Not implemented yet");

        }
        throw new Error(String.format("Wrong input: %s", type.getType()));
    }

    public static final <T extends InterpreterValue> Variable<T> finalOf(String name, T v) {
        return new Variable<T>(name, AccessDescriber.PUBLIC, (Class<T>) v.getClass(), v);
    }
}
