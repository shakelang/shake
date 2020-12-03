package com.github.nsc.de.compiler.interpreter.values;

import com.github.nsc.de.compiler.interpreter.Scope;
import com.github.nsc.de.compiler.interpreter.Variable;
import com.github.nsc.de.compiler.interpreter.VariableList;
import com.github.nsc.de.compiler.parser.node.variables.VariableDeclarationNode;

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
    private final Class parent;

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
     * @param parent the parent {@link Class} of the {@link ObjectValue}
     *
     * @author Nicolas Schmidt
     */
    public ObjectValue(Class parent) {

        // set the parent
        this.parent = parent;

        // Create this_object (extending the prototype from the parent)
        //
        // We use null as withScope parameter, this will be replaced later,
        // but we don't have the scope at this point of the code.
        this.this_object = new VariableList(new HashMap<>(), parent.getPrototype().withScope(null));

        // Create scope for inside of the class
        this.scope = new Scope(parent.getScope(), this_object);

        // replace the "withScope" scope
        // we do this here because the scope was not declared when we declared the this_object
        ((VariableList.ScopeVariableList) this_object.getParentList()).setScope(scope);

        // Declare this keyword inside of the this_object
        this.this_object.declare("this", VariableList.class);

        // Set value of this keyword
        this.this_object.get("this").setValue(this_object);

        // TODO 2 Declarations with the same name
        // loop over the fields of the class
        for(VariableDeclarationNode node : parent.getFields()) {

            // declare the field inside of the this_object
            this.this_object.declare(node.getName(), VariableType.valueOf(node.getType()));

            // set the field value (if given)
            if(node.getAssignment() != null)
                this.this_object.get(node.getName())
                    .setValue(parent.getInterpreter().visit(
                            node.getAssignment().getValue(), // << the value that is assigned to the variable
                            scope // << The class-scope
                    ));

        }
    }



    // *******************************
    // getters

    /**
     * Getter for {@link #parent} (the parent class)
     *
     * @return the parent class (this.{@link #parent})
     *
     * @author Nicolas Schmidt
     */
    public Class getParent() {
        return parent;
    }

    /**
     * Getter for {@link #scope} (the scope inside of the class)
     *
     * @return the scope inside of the class (this.{@link #scope})
     *
     * @author Nicolas Schmidt
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
     * @author Nicolas Schmidt
     */
    public VariableList getThisObject() {
        // just return the this_object variable
        return this_object;
    }



    // ****************************
    // implementations for extended InterpreterValue
    // >> children

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
        // it the required value does not exist throw an error
        // in other case return the required value
        if(getThisObject().get(c) == null || !getThisObject().get(c).hasValue()) throw new Error(String.format("Object has no property called \"%s\"", c));
        return getThisObject().get(c);
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
     * @author Nicolas Schmidt
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
     * @author Nicolas Schmidt
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
