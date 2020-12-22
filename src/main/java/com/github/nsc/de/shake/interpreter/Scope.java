package com.github.nsc.de.shake.interpreter;

import com.github.nsc.de.shake.interpreter.values.InterpreterValue;

/**
 * A scope to keep all the variables inside
 *
 * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
 *
 * @see VariableList
 */
public class Scope {



    // *******************************
    // fields

    /**
     * The variables of the {@link Scope}
     */
    private final VariableList variables;

    /**
     * The parent {@link Scope} (or null if the {@link Scope} has no parent)
     */
    private final Scope parent;

    /**
     * The interpreter of the {@link Scope}
     */
    private final Interpreter interpreter;

    private InterpreterValue returnValue = null;



    // *******************************
    // constructors

    /**
     * Constructor for {@link Scope}
     *
     * @param parent the parent of the scope (value for {@link #parent})
     * @param variables the variables of the scope (value for {@link #variables})
     *
     * @param interpreter
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Scope(Scope parent, VariableList variables, Interpreter interpreter) {
        // just set the fields
        this.parent = parent;
        this.variables = variables;
        this.interpreter = interpreter;
    }

    /**
     * Constructor for {@link Scope}
     *
     * @param parent the parent of the scope (value for {@link #parent})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Scope(Scope parent, Interpreter interpreter) {
        // Use other constructor with an empty VariableList
        this(parent, new VariableList(), interpreter);
    }



    // *******************************
    // getters

    /**
     * Getter for {@link #parent} (the parent-scope)
     *
     * @return the parent-scope (this.{@link #parent})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Scope getParent() {
        return parent;
    }

    /**
     * Getter for {@link #variables} (the scope's variables)
     *
     * @return the scope's variables (this.{@link #variables})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public VariableList getScopeVariables() {
        return variables;
    }

    /**
     * Getter for all scope variables (including all the variables of the parents)
     *
     * @return the scope's variables (this.{@link #variables})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public VariableList getVariables() {
        return this.parent != null ? this.parent.getVariables().concat(variables) : variables;
    }

    /**
     * Getter for the {@link Interpreter} of the {@link Scope}
     *
     * @return the {@link Interpreter} of the {@link Scope}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Interpreter getInterpreter() {
        return interpreter;
    }

    /**
     * Copies the scope
     *
     * @return the copy of the scope
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Scope copy() {
        return new Scope(this.parent, this.getScopeVariables().copy(), interpreter);
    }

    /**
     * Setter for the {@link #returnValue} field
     *
     * @param returnValue the {@link #returnValue}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public void setReturnValue(InterpreterValue returnValue) {
        // just set the returnValue field
        this.returnValue = returnValue;
    }

    /**
     * Getter for the {@link #returnValue} field
     *
     * @return the {@link #returnValue}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public InterpreterValue getReturnValue() {
        // just return the returnValue field
        return returnValue;
    }
}
