package com.github.nsc.de.compiler.interpreter;

import com.github.nsc.de.compiler.interpreter.values.*;
import com.github.nsc.de.compiler.parser.node.AccessDescriber;
import com.github.nsc.de.compiler.parser.node.VariableType;

import java.lang.Class;
import java.lang.reflect.ParameterizedType;


/**
 * Variable class to keep all {@link InterpreterValue}s in variables
 * Type argument is the type of value that
 *
 * @author Nicolas Schmidt
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
     * @author Nicolas Schmidt
     */
    public Variable(String identifier, AccessDescriber access, V value) {
        // apply the given values
        this.identifier = identifier;
        this.access = access;
        this.value = value;
    }

    /**
     * Constructor for {@link Variable}
     *
     * @param identifier the identifier of the variable
     * @param value the value of the variable
     *
     * @author Nicolas Schmidt
     */
    public Variable(String identifier, V value) {
        // call other constructor using default AccessDescriber: PACKAGE
        this(identifier, AccessDescriber.PACKAGE, value);
    }

    /**
     * Constructor for {@link Variable}
     *
     * @param identifier the identifier of the variable
     * @param access the access of the
     *
     * @author Nicolas Schmidt
     */
    public Variable(String identifier, AccessDescriber access) {
        // call other constructor using default value: null
        this(identifier, access, null);
    }

    /**
     * Constructor for {@link Variable}
     *
     * @param identifier the identifier of the variable
     *
     * @author Nicolas Schmidt
     */
    public Variable(String identifier) {
        // call other constructor using default AccessDescriber: PACKAGE
        this(identifier, AccessDescriber.PACKAGE);
    }



    // *******************************
    // getters

    /**
     * Getter for the type-argument class (The variable-type)
     *
     * @return the type-argument class (The variable-type)
     *
     * @author Nicolas Schmidt
     */
    public Class<V> getType() {
        // return the class of the generic type V
        return (Class<V>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Getter for {@link #identifier} (the variable identifier)
     *
     * @return the variable identifier (this.{@link #identifier})
     *
     * @author Nicolas Schmidt
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
     * @author Nicolas Schmidt
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
     * @author Nicolas Schmidt
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
     * @author Nicolas Schmidt
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
     * @author Nicolas Schmidt
     */
    public boolean hasValue() {
        // check if the field value is not null
        return this.value != null;
    }



    // ****************************
    // implementations for extended InterpreterValue
    // number-operators

    /**
     * This function gets executed when the operator '+' is used on the value
     *
     * @param v The Value to add to this value
     * @return The Calculation-Result
     *
     * @author Nicolas Schmidt
     */
    @Override
    public InterpreterValue add(InterpreterValue v) {
        // redirect operator to the value
        return this.getValue().add(v);
    }

    /**
     * This function gets executed when the operator '-' is used on the value
     *
     * @param v The Value to sub from this value
     * @return The Calculation-Result
     *
     * @author Nicolas Schmidt
     */
    @Override
    public InterpreterValue sub(InterpreterValue v) {
        // redirect operator to the value
        return this.getValue().sub(v);
    }

    /**
     * This function gets executed when the operator '*' is used on the value
     *
     * @param v The Value to multiply with this value
     * @return The Calculation-Result
     *
     * @author Nicolas Schmidt
     */
    @Override
    public InterpreterValue mul(InterpreterValue v) {
        // redirect operator to the value
        return this.getValue().mul(v);
    }

    /**
     * This function gets executed when the operator '/' is used on the value
     *
     * @param v The divisor-value
     * @return The Calculation-Result
     *
     * @author Nicolas Schmidt
     */
    @Override
    public InterpreterValue div(InterpreterValue v) {
        // redirect operator to the value
        return this.getValue().div(v);
    }

    /**
     * This function gets executed when the operator '%' is used on the value
     *
     * @param v The divisor-value
     * @return The Calculation-Result
     *
     * @author Nicolas Schmidt
     */
    @Override
    public InterpreterValue mod(InterpreterValue v) {
        // redirect operator to the value
        return this.getValue().mod(v);
    }

    /**
     * This function gets executed when the operator '**' is used on the value
     *
     * @param v The exponent value
     * @return The Calculation-Result
     *
     * @author Nicolas Schmidt
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
     * This function gets executed when the operator '||' is used on the value
     *
     * @param v The other value for the or operator
     * @return The Calculation-Result
     *
     * @author Nicolas Schmidt
     */
    @Override
    public InterpreterValue or(InterpreterValue v) {
        // redirect operator to the value
        return this.getValue().or(v);
    }

    /**
     * This function gets executed when the operator '&amp;&amp;' is used on the value
     *
     * @param v The other value for the and operator
     * @return The Calculation-Result
     *
     * @author Nicolas Schmidt
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
     * This function gets executed when the operator '==' is used on the value
     *
     * @param v The value that should be the same
     * @return The Calculation-Result
     *
     * @author Nicolas Schmidt
     */
    @Override
    public InterpreterValue equals(InterpreterValue v) {
        // redirect operator to the value
        return this.getValue().equals(v);
    }

    /**
     * This function gets executed when the operator '&gt;=' is used on the value
     *
     * @param v The value that should be smaller
     * @return The Calculation-Result
     *
     * @author Nicolas Schmidt
     */
    @Override
    public InterpreterValue bigger_equals(InterpreterValue v) {
        // redirect operator to the value
        return this.getValue().bigger_equals(v);
    }

    /**
     * This function gets executed when the operator '&lt;=' is used on the value
     *
     * @param v The value that should be bigger
     * @return The Calculation-Result
     *
     * @author Nicolas Schmidt
     */
    @Override
    public InterpreterValue smaller_equals(InterpreterValue v) {
        // redirect operator to the value
        return this.getValue().smaller_equals(v);
    }

    /**
     * This function gets executed when the operator '&gt;' is used on the value
     *
     * @param v The value that should be smaller or equal
     * @return The Calculation-Result
     *
     * @author Nicolas Schmidt
     */
    @Override
    public InterpreterValue bigger(InterpreterValue v) {
        // redirect operator to the value
        return this.getValue().bigger(v);
    }

    /**
     * This function gets executed when the operator '&lt;' is used on the value
     *
     * @param v The value that should be bigger or equal
     * @return The Calculation-Result
     *
     * @author Nicolas Schmidt
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
     * This function gets executed when getting a child (variable.child)
     *
     * @param c the child to get
     * @return the child variable
     *
     * @author Nicolas Schmidt
     */
    @Override
    public Variable getChild(String c) {
        // redirect operator to the value
        return this.getValue().getChild(c);
    }



    // ****************************
    // implementations for extended InterpreterValue
    // get-name

    /**
     * Returns the name of the type of {@link InterpreterValue} (To identify the type of value)
     *
     * @return the name of the {@link InterpreterValue}
     *
     * @author Nicolas Schmidt
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
     * @author Nicolas Schmidt
     */
    public Variable<V> copy() {
        // return a new Variable using the same values
        return new Variable<V>(this.identifier, this.access, this.value);
    }



    // *******************************
    // Override toString()

    /**
     * Returns the string representation of the {@link Variable}
     *
     * @return the string representation of the {@link Variable}
     *
     * @author Nicolas Schmidt
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
     * @author Nicolas Schmidt
     */
    public Variable<V> withScope(Scope scope) {
        return new Variable<V>(identifier, access, useScope(value, scope));
    }

    /**
     * A function that applies a scope to an {@link InterpreterValue}
     *
     * @param v the value to apply the scope to
     * @param scope the scope to use
     * @param <V> the type of {@link InterpreterValue} that is given as argument
     * @return the given {@link InterpreterValue}, but it now uses the given {@link Scope}
     *
     * @author Nicolas Schmidt
     */
    private static <V extends InterpreterValue> V useScope(V v, Scope scope) {
        if(v instanceof Variable) return (V) ((Variable) v).withScope(scope);
        if(v instanceof VariableList) return (V) ((VariableList) v).withScope(scope);
        if(v instanceof Function) return (V) ((Function) v).withScope(scope);
        if(v instanceof com.github.nsc.de.compiler.interpreter.values.Class)
            return (V) ((com.github.nsc.de.compiler.interpreter.values.Class) v).withScope(scope);
        return v;
    }



    /**
     * This function just converts a {@link VariableType} into a {@link Variable}
     *
     * @param type the type to convert
     * @return the converted type
     *
     * @author Nicolas Schmidt
     */
    public static Variable<?> valueOf(String name, VariableType type) {
        switch (type.getType()) {

            // Return for all number-value types without decimal places the IntegerValue class
            case BYTE:
            case SHORT:
            case INTEGER:
            case LONG:
                new Variable<IntegerValue>(name);

            // Return for all number-value types with decimal places the DoubleValue class
            case FLOAT:
            case DOUBLE:
                return new Variable<DoubleValue>(name);

            // For booleans just return the BooleanValue class
            case BOOLEAN:
                return new Variable<BooleanValue>(name);

            // For Objects return the ObjectValue class
            case OBJECT:
                return new Variable<ObjectValue>(name); // TODO object-subtypes

            // For Dynamic return the InterpreterValue class (as super-class of all
            // interpreter-values it can hold all of them)
            case DYNAMIC:
                return new Variable<>(name);

            // TODO Char & Array are not implemented at the moment
            case CHAR:
            case ARRAY:
                throw new Error("Not implemented yet");

        }
        throw new Error(String.format("Wrong input: %s", type.getType()));
    }
}
