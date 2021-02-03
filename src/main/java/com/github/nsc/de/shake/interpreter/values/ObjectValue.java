package com.github.nsc.de.shake.interpreter.values;

import com.github.nsc.de.shake.interpreter.Scope;
import com.github.nsc.de.shake.interpreter.Variable;
import com.github.nsc.de.shake.interpreter.VariableList;
import com.github.nsc.de.shake.parser.node.variables.VariableDeclarationNode;

import java.util.HashMap;


/**
 * {@link InterpreterValue} to store objects
 */
public class ObjectValue implements InterpreterValue {



    // *******************************
    // fields

    /**
     * The parent class of the object
     */
    private final ClassValue parent;

    /**
     * The scope inside of the class (for functions in the class)
     */
    private Scope scope;

    /**
     * The fields, functions and subclasses of the object
     */
    private VariableList this_object;



    // *******************************
    // Constructor

    /**
     * Constructor for {@link ObjectValue}
     *
     * @param parent the parent {@link ClassValue} of the {@link ObjectValue}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public ObjectValue(ClassValue parent) {

        // set the parent
        this.parent = parent;

        // Create this_object (extending the prototype from the parent)
        //
        // We use null as withScope parameter, this will be replaced later,
        // but we don't have the scope at this point of the code.
        this.this_object = new VariableList(new HashMap<>(), parent.getPrototype().withScope(null));

        // Create scope for inside of the class
        this.scope = new Scope(parent.getScope(), this_object, parent.getInterpreter());

        // replace the "withScope" scope
        // we do this here because the scope was not declared when we declared the this_object
        ((VariableList.ScopeVariableList) this_object.getParentList()).setScope(scope);

        // Declare this keyword inside of the this_object
        this.this_object.declare(new Variable<>("this", VariableList.class));

        // Set value of this keyword
        this.this_object.get("this").setValue(this_object);

        // TODO 2 Declarations with the same name
        // loop over the fields of the class
        for(VariableDeclarationNode node : parent.getFields()) {

            // declare the field inside of the this_object
            this.this_object.declare(Variable.create(node.getName(), node.getType(), node.isFinal(),
                    node.getAssignment() != null ? parent.getInterpreter().visit(
                            node.getAssignment().getValue(), // << the value that is assigned to the variable
                            scope // << The class-scope
                    ) : null));

        }
    }



    // *******************************
    // getters

    /**
     * Getter for {@link #parent} (the parent class)
     *
     * @return the parent class (this.{@link #parent})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public ClassValue getParent() {
        return parent;
    }

    /**
     * Getter for {@link #scope} (the scope inside of the class)
     *
     * @return the scope inside of the class (this.{@link #scope})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Scope getScope() {
        // just return the scope variable
        return scope;
    }

    /**
     * Getter for {@link #this_object} (all the fields, functions and subclasses of the object)
     *
     * @return all the fields, functions and subclasses of the object (this.{@link #this_object})
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public VariableList getThisObject() {
        // just return the this_object variable
        return this_object;
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
    public Variable<?> getChild(String c) {
        // it the required value does not exist throw an error
        // in other case return the required value
        if(getThisObject().get(c) == null || !getThisObject().get(c).hasValue()) throw new Error(String.format("Object has no property called \"%s\"", c));
        return getThisObject().get(c);
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
        // Just return the this-object keys
        return getThisObject().getChildren();
    }



    // *******************************
    // implementations for extended InterpreterValue
    // >> get-name

    /**
     * Returns the name of the type of {@link InterpreterValue} (To identify the type of value)
     * For {@link ObjectValue} it just always returns "object"
     *
     * @return "class"
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public String getName() {
        // just return "object"
        return "object";
    }



    // *******************************
    // Override toString()

    /**
     * Returns the string representation of the {@link ObjectValue}
     *
     * @return the string representation of the {@link ObjectValue}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public String toString() {
        // Just create a string out of all the children of the ObjectValue
        return "ObjectValue{" +
                "parent=" + parent +
                ", scope=" + scope +
                ", this_object=" + this_object +
                '}';
    }
}
