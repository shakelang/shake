package io.github.shakelang.shake.interpreter.values

import io.github.shakelang.shake.interpreter.Scope
import io.github.shakelang.shake.interpreter.UnformattedInterpreterError
import io.github.shakelang.shake.interpreter.Variable
import io.github.shakelang.shake.interpreter.VariableList
import io.github.shakelang.shake.interpreter.VariableList.ScopeVariableList

/**
 * [InterpreterValue] to store objects
 */
class ObjectValue


/**
 * Constructor for [ObjectValue]
 *
 * @param parent the parent [ClassValue] of the [ObjectValue]
 */
(

    /**
     * The parent class of the object
     */
    val parent: ClassValue

) : InterpreterValue {

    /**
     * The scope inside of the class (for functions in the class)
     */
    val scope: Scope

    /**
     * The fields, functions and subclasses of the object
     */
    val thisObject: VariableList = VariableList(HashMap(), parent.prototype.withScope(null))


    // ****************************
    // implementations for extended InterpreterValue
    // >> children

    /**
     * This function will be executed when getting a child (variable.child)
     *
     * @param c the child to get
     * @return the child variable
     */
    override fun getChild(c: String): Variable {
        // it the required value does not exist throw an error
        // in other case return the required value
        if (thisObject[c] == null || !thisObject[c]!!.hasValue())
            throw UnformattedInterpreterError("Object has no property called \"$c\"")
        return thisObject[c]!!
    }

    /**
     * This function will be executed when getting all child keys
     *
     * @return the keys of all children
     */
    override val children: Array<String> get() = thisObject.children


    // *******************************
    // implementations for extended InterpreterValue
    // >> get-name

    /**
     * Returns the name of the type of [InterpreterValue] (To identify the type of value)
     * For [ObjectValue] it just always returns "object"
     *
     * @return "class"
     */
    override val name: String get() = "object"


    // *******************************
    // Override toString()

    /**
     * Returns the string representation of the [ObjectValue]
     *
     * @return the string representation of the [ObjectValue]
     */
    override fun toString(): String {
        // Just create a string out of all the children of the ObjectValue
        return "ObjectValue{" +
                "parent=" + parent +
                ", scope=" + scope +
                ", this_object=" + thisObject +
                '}'
    }

    init {

        // Create this_object (extending the prototype from the parent)
        //
        // We use null as withScope parameter, this will be replaced later,
        // but we don't have the scope at this point of the code.

        // Create scope for inside of the class
        scope = Scope(parent.scope, thisObject, parent.interpreter)

        // replace the "withScope" scope
        // we do this here because the scope was not declared when we declared the this_object
        (thisObject.parentList as ScopeVariableList).scope = scope

        // Declare this keyword inside of the this_object
        thisObject.declare(Variable("this", VariableList::class))

        // Set value of this keyword
        thisObject["this"]!!.value = thisObject

        // TODO 2 Declarations with the same name
        // loop over the fields of the class
        for (node in parent.fields) {

            // declare the field inside of the this_object
            thisObject.declare(
                Variable.create(
                    node!!.name, node.type, node.isFinal,
                    if (node.value != null) parent.interpreter.visit(
                        node.value!!.value,  // << the value that is assigned to the variable
                        scope // << The class-scope
                    ) else null
                )
            )
        }
    }
}