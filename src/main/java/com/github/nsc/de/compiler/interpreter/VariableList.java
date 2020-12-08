package com.github.nsc.de.compiler.interpreter;

import com.github.nsc.de.compiler.interpreter.values.InterpreterValue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



/**
 * A list that contains variables
 *
 * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
 */
public class VariableList implements InterpreterValue {

    /**
     * The variables
     */
    private final Map<String, Variable> variables;

    /**
     * The parent list (or null, if the list hast no parent list)
     */
    private final VariableList parentList;



    // *******************************
    // Constructors

    /**
     * Constructor for {@link VariableList}
     *
     * @param variables a map of the variables ({@link #variables})
     * @param parentList the parent list
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public VariableList(Map<String, Variable> variables, VariableList parentList) {
        // apply values to fields
        this.variables = variables;
        this.parentList = parentList;
    }

    /**
     * Constructor for {@link VariableList}
     *
     * @param parentList the parent list
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public VariableList(VariableList parentList) {
        // apply values to fields
        this.variables = new HashMap<>();
        this.parentList = parentList;
    }

    /**
     * Constructor for {@link VariableList}
     *
     * @param variables a map of the variables ({@link #variables})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public VariableList(HashMap<String, Variable> variables) {
        // apply given values to fields
        this.variables = variables;
        this.parentList = null;
    }

    /**
     * Constructor for {@link VariableList}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public VariableList() {
        // apply values to fields
        this.variables = new HashMap<>();
        this.parentList = null;
    }



    // *******************************
    // getters

    /**
     * Getter for {@link #variables} (the variable map)
     *
     * @return the variable map (this.{@link #variables})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Map<String, Variable> getVariables() {
        // just return the variables field
        return variables;
    }

    /**
     * Getter for {@link #parentList} (the parent-list)
     *
     * @return the parent-list (this.{@link #parentList})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public VariableList getParentList() {
        // just return the parentList field
        return parentList;
    }



    // *******************************
    // VariableList functionality

    /**
     * Declare a {@link Variable}
     *
     * @param v the variable
     * @return true, if the operation was successful, false if a with this name variable is already declared
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public boolean declare(Variable v) {
        // Check if the variable-map already contains a Variable with this name (if so return false).
        // In other case put the variable into the map using the identifier as key and return true
        if (this.variables.containsKey(v.getIdentifier())) return false;
        this.variables.put(v.getIdentifier(), v);
        return true;
    }

    /**
     * Get a variable from the {@link VariableList}
     *
     * @param name the name of the {@link Variable} to get
     * @return the {@link Variable} (or null if the {@link Variable} is not declared)
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Variable get(String name) {

        // If the variable map contains the variable then return it.
        if(variables.containsKey(name)) return variables.get(name);

        // In other case if the VariableList has a parent-list try to get the Variable from the parentList.s
        else if (this.getParentList() != null) return this.getParentList().get(name);

        // In other case just return null (the variable is not declared)
        return null;

    }

    /**
     * Puts together this {@link VariableList} with another given one (ignores the {@link #parentList} of the
     * {@link VariableList} that is given as argument)
     *
     * @param list the {@link VariableList} to put together with this one
     * @return the two merged {@link VariableList}s
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    VariableList concat(VariableList list) {

        // Create a new HashMap from the variables of the VariableList
        HashMap<String, Variable> variables = new HashMap<>(this.variables);

        // Loop over the given list and put the variables into the variables map
        list.getVariables().forEach(variables::put);

        // return the variable map
        return new VariableList(variables, list.getParentList());
    }

    /**
     * Copies the {@link VariableList}
     *
     * @return the copy of the {@link VariableList}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public VariableList copy() {

        // Create a new HashMap for the variables
        Map<String, Variable> vars = new HashMap<>();

        // Loop over the variables and put a copy of the variable into the vars Map
        this.variables.forEach((k, v) -> vars.put(k, v));

        // Return a new VariableList created using the created vars map
        return new VariableList(vars, this.parentList);

    }

    /**
     * Let's all the children of the {@link VariableList} use the given scope
     *
     * @param scope the scope to use
     * @return the {@link VariableList} using the scope
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public VariableList withScope(Scope scope) {
        // Create a new ScopeVariableList from the fields of this VariableList and the given Scope.
        return new ScopeVariableList(this.variables, this.parentList, scope);
    }



    // ****************************
    // implementations for extended InterpreterValue
    // Children

    /**
     * This function will be executed when getting a child (variable.child)
     *
     * @param c the child to get
     * @return the child variable
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public Variable getChild(String c) {
        // just get the child
        return this.get(c);
    }



    // ****************************
    // implementations for extended InterpreterValue
    // get-name

    /**
     * Returns the name of the type of {@link InterpreterValue} (To identify the type of value)
     * For {@link VariableList} it just always returns "variable_list"
     *
     * @return the name of the {@link InterpreterValue}
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public String getName() {
        // just return "variable_list"
        return "variable_list";
    }



    // *******************************
    // Override toString()

    /**
     * Returns the string representation of the {@link VariableList}
     *
     * @return the string representation of the {@link VariableList}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public String toString() {
        // just create a string out of the properties of the VariableList
        return String.format("{variables=%s,parentList=%s}", this.variables, this.parentList);
    }

    /**
     * This is a VariableList that forces all of its children to use a scope, it is required for the implementation
     * of {@link VariableList#withScope(Scope)}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public static class ScopeVariableList extends VariableList {

        /**
         * The scope to use for the children
         */
        private Scope scope;

        /**
         * Constructor for {@link ScopeVariableList}
         *
         * @param variables the variables
         * @param parentList the parent-list
         * @param scope the scope to use
         *
         * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
         *
         * @see VariableList#VariableList(Map, VariableList)
         */
        public ScopeVariableList(Map<String, Variable> variables, VariableList parentList, Scope scope) {

            // call super constructor with specified arguments
            super(variables, parentList);

            // set scope field
            this.scope = scope;

        }

        /**
         * Get a variable from the {@link VariableList}
         *
         * @param name the name of the {@link Variable} to get
         * @return the {@link Variable} (or null if the {@link Variable} is not declared)
         *
         * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
         */
        @Override
        public Variable get(String name) {
            // Get the Variable from the parent and call the withScope function on it
            return super.get(name).withScope(this.scope);
        }

        /**
         * Getter for {@link #variables} (the variable map)
         *
         * @return the variable map (this.{@link #variables})
         *
         * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
         */
        @Override
        public Map<String, Variable> getVariables() {
            // Create a new map
            Map<String, Variable> variableMap = new HashMap<>();

            // Copy all the variables to the variable-map, but always calling the #withScope function on them
            super.getVariables().forEach((String key, Variable variable) -> variableMap.put(key, variable.withScope(this.scope)));

            // return the variable-map
            return variableMap;
        }

        /**
         * Getter for {@link #scope} (the scope to use for all variables)
         *
         * @return the scope to use for all variables (this.{@link #scope})
         *
         * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
         */
        public Scope getScope() {
            // just return the scope field
            return scope;
        }

        /**
         * Setter for {@link #scope} (the scope to use for all variables)
         *
         * @param scope the scope to use for all variables (this.{@link #scope})
         *
         * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
         */
        public void setScope(Scope scope) {
            // just set the scope field
            this.scope = scope;
        }
    }
}
