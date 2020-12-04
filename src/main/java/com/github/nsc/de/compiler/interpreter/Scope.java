package com.github.nsc.de.compiler.interpreter;

/**
 * A scope to keep all the variables inside
 *
 * @author Nicolas Schmidt
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



    // *******************************
    // constructors

    /**
     * Constructor for {@link Scope}
     *
     * @param parent the parent of the scope (value for {@link #parent})
     * @param variables the variables of the scope (value for {@link #variables})
     *
     * @author Nicolas Schmidt
     */
    public Scope(Scope parent, VariableList variables) {
        // just set the fields
        this.parent = parent;
        this.variables = variables;
    }

    /**
     * Constructor for {@link Scope}
     *
     * @param parent the parent of the scope (value for {@link #parent})
     *
     * @author Nicolas Schmidt
     */
    public Scope(Scope parent) {
        // Use other constructor with an empty VariableList
        this(parent, new VariableList());
    }



    // *******************************
    // getters

    /**
     * Getter for {@link #parent} (the parent-scope)
     *
     * @return the parent-scope (this.{@link #parent})
     *
     * @author Nicolas Schmidt
     */
    public Scope getParent() {
        return parent;
    }

    /**
     * Getter for {@link #variables} (the scope's variables)
     *
     * @return the scope's variables (this.{@link #variables})
     *
     * @author Nicolas Schmidt
     */
    public VariableList getScopeVariables() {
        return variables;
    }

    /**
     * Getter for all scope variables (including all the variables of the parents)
     *
     * @return the scope's variables (this.{@link #variables})
     *
     * @author Nicolas Schmidt
     */
    public VariableList getVariables() {
        return this.parent != null ? this.parent.getVariables().concat(variables) : variables;
    }

    /**
     * Copies the scope
     *
     * @return the copy of the scope
     *
     * @author Nicolas Schmidt
     */
    public Scope copy() {
        return new Scope(this.parent, this.getScopeVariables().copy());
    }
}
