package com.github.nsc.de.shake.interpreter.values;

import com.github.nsc.de.shake.interpreter.*;
import com.github.nsc.de.shake.parser.node.AccessDescriber;
import com.github.nsc.de.shake.parser.node.objects.ClassConstructionNode;
import com.github.nsc.de.shake.parser.node.variables.VariableDeclarationNode;

import java.util.Arrays;



/**
 * An {@link InterpreterValue} to store class-declarations
 *
 * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
 */
public class ClassValue implements InterpreterValue {



    // *******************************
    // fields

    /**
     * The name of the class
     */
    private final String name;

    /**
     * The static fields, classes and functions of the class
     */
    private final VariableList statics;

    /**
     * The prototype of the class (contains subclasses and functions)
     */
    private final VariableList prototype;

    /**
     * The fields of the class (they get initialized when creating a new instance of the class)
     */
    private final VariableDeclarationNode[] fields;

    /**
     * The {@link Scope} the class is located in (this is used to access values of this scope in the class)
     */
    private final Scope scope;

    /**
     * The interpreter the class is created with
     */
    private final Interpreter interpreter;

    /**
     * The access type of the class
     */
    private final AccessDescriber access;

    /**
     * Is this class final?
     */
    private final boolean isFinal;



    // *******************************
    // Constructor

    /**
     * The Constructor for {@link ClassValue}
     *
     * @param name the {@link #name} of the class
     * @param statics the {@link #statics} of the class
     * @param fields the {@link #fields} of the class
     * @param scope the {@link #scope} of the class
     * @param interpreter the {@link #interpreter} of the class
     * @param prototype the {@link #prototype} of the class
     * @param access the {@link #access} of the class
     * @param isFinal is this class {@link #isFinal}?
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public ClassValue(String name, VariableList statics, VariableDeclarationNode[] fields, Scope scope, Interpreter interpreter,
                      VariableList prototype, AccessDescriber access, boolean isFinal) {
        // Set all the given values
        this.name = name;
        this.statics = statics;
        this.fields = fields;
        this.scope = scope;
        this.interpreter = interpreter;
        this.prototype = prototype;
        this.access = access;
        this.isFinal = isFinal;
    }

    /**
     * Returns the same class, but you can declare what {@link Scope} ({@link #scope}) to use (for class declarations)
     *
     * @param scope the scope to use
     * @return the {@link ClassValue} using the specified {@link Scope} ({@link #scope})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public ClassValue withScope(Scope scope) {
        // Return a new Function with the same argument as this one, just replace the scope
        return new ClassValue(name, statics, fields, scope, interpreter, prototype, access, isFinal);
    }



    // *******************************
    // getters

    /**
     * Getter for {@link #name} (the class name)
     *
     * @return the class name (this.{@link #name})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public String getClassName() {
        // just return the class-name
        return this.name;
    }

    /**
     * Getter for {@link #statics} (the statics of the class)
     *
     * @return the statics of the class (this.{@link #statics})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public VariableList getStatics() {
        // just return the statics
        return this.statics;
    }

    /**
     * Getter for {@link #prototype} (the prototype of the class)
     *
     * @return the prototype of the class (this.{@link #prototype})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public VariableList getPrototype() {
        // just return the prototype
        return this.prototype;
    }

    /**
     * Getter for {@link #fields} (the fields of the class)
     *
     * @return the fields of the class (this.{@link #fields})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public VariableDeclarationNode[] getFields() {
        // just return the fields
        return this.fields;
    }

    /**
     * Getter for {@link #interpreter} (the interpreter of the class)
     *
     * @return the interpreter of the class (this.{@link #interpreter})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Interpreter getInterpreter() {
        // just return the interpreter
        return this.interpreter;
    }

    /**
     * Getter for {@link #scope} (the scope the class is located in)
     *
     * @return the scope the class is located in (this.{@link #scope})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Scope getScope() {
        // just return the scope
        return this.scope;
    }

    /**
     * Getter for {@link #isFinal} (is this class final?)
     *
     * @return is this class final? (this.{@link #isFinal})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public boolean isFinal() {
        // just return isFinal
        return this.isFinal;
    }



    // ****************************
    // implementations for extended InterpreterValue
    // >> children

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
        // If the child does not exist throw an error
        // in other case return the child
        if(getStatics().get(c) == null || !getStatics().get(c).hasValue()) throw new UnformattedInterpreterError(String.format("Class \"%s\" has no property called %s", getClassName(), c));
        return getStatics().get(c);
    }

    /**
     * This function will be executed when getting all child keys
     *
     * @return the keys of all children
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public String[] getChildren() {
        // Just return the static keys
        return getStatics().getChildren();
    }

    // ****************************
    // implementations for extended InterpreterValue
    // >> invoking

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
    public InterpreterValue newInstance(ClassConstructionNode node, Scope scope, InterpretationTools tools) {
        return new ObjectValue(this, tools);
    }



    // *******************************
    // implementations for extended InterpreterValue
    // >> get-name


    /**
     * Returns the name of the type of {@link InterpreterValue} (To identify the type of value)
     * For {@link ClassValue} it just always returns "class"
     *
     * @return "class"
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public String getName() {
        // just return "class"
        return "class";
    }


    // *******************************
    // Override toString()

    /**
     * Returns the string representation of the {@link ClassValue}
     *
     * @return the string representation of the {@link ClassValue}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public String toString() {
        // Create a string representation of the class by logging out all the important parts
        return "Class{" +
                "name=" + name +
                ", access=" + access +
                ", isFinal=" + isFinal +
                ", prototype=" + prototype +
                ", fields=" + Arrays.toString(fields) +
                ", statics=" + statics +
                '}';
    }
}
