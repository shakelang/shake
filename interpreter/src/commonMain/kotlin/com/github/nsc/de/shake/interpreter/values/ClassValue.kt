package com.github.nsc.de.shake.interpreter.values

import com.github.nsc.de.shake.interpreter.*
import com.github.nsc.de.shake.parser.node.AccessDescriber
import com.github.nsc.de.shake.parser.node.objects.ClassConstructionNode
import com.github.nsc.de.shake.parser.node.variables.VariableDeclarationNode

/**
 * An [InterpreterValue] to store class-declarations
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
class ClassValue


/**
 * The Constructor for [ClassValue]
 *
 * @param className the [ClassValue.className] of the class
 * @param statics the [ClassValue.statics] of the class
 * @param fields the [ClassValue.fields] of the class
 * @param scope the [ClassValue.scope] of the class
 * @param interpreter the [ClassValue.interpreter] of the class
 * @param prototype the [ClassValue.prototype] of the class
 * @param access the [ClassValue.access] of the class
 * @param isFinal is this class [ClassValue.isFinal]?
 *
 * @author [Nicolas Schmidt &lt;@nsc -de&gt;](https://github.com/nsc-de)
 */
(

/**
 * The name of the class
 *
 * @author [Nicolas Schmidt &lt;@nsc -de&gt;](https://github.com/nsc-de)
 */
val className: String,

/**
 * The static fields, classes and functions of the class
 *
 * @author [Nicolas Schmidt &lt;@nsc -de&gt;](https://github.com/nsc-de)
 */
val statics: VariableList,

/**
 * The fields of the class (they get initialized when creating a new instance of the class)
 *
 * @author [Nicolas Schmidt &lt;@nsc -de&gt;](https://github.com/nsc-de)
 */
val fields: Array<VariableDeclarationNode?>,

/**
 * The [Scope] the class is located in (this is used to access values of this scope in the class)
 *
 * @author [Nicolas Schmidt &lt;@nsc -de&gt;](https://github.com/nsc-de)
 */
val scope: Scope?,

/**
 * The interpreter the class is created with
 *
 * @author [Nicolas Schmidt &lt;@nsc -de&gt;](https://github.com/nsc-de)
 */
val interpreter: Interpreter,

/**
 * The prototype of the class (contains subclasses and functions)
 *
 * @author [Nicolas Schmidt &lt;@nsc -de&gt;](https://github.com/nsc-de)
 */
val prototype: VariableList,

/**
 * The access type of the class
 *
 * @author [Nicolas Schmidt &lt;@nsc -de&gt;](https://github.com/nsc-de)
 */
private val access: AccessDescriber,

/**
 * Is this class final?
 *
 * @author [Nicolas Schmidt &lt;@nsc -de&gt;](https://github.com/nsc-de)
 */
val isFinal: Boolean

) : InterpreterValue {

    /**
     * Returns the same class, but you can declare what [Scope] ([.scope]) to use (for class declarations)
     *
     * @param scope the scope to use
     * @return the [ClassValue] using the specified [Scope] ([.scope])
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun withScope(scope: Scope?): ClassValue {
        // Return a new Function with the same argument as this one, just replace the scope
        return ClassValue(this.className, statics, fields, scope, interpreter, prototype, access, isFinal)
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
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun getChild(c: String): Variable {
        // If the child does not exist throw an error
        // in other case return the child
        if (statics[c] == null || !statics[c]!!.hasValue())
            throw UnformattedInterpreterError("Class \"$className\" has no property called $c")
        return statics[c]!!
    }

    /**
     * This function will be executed when getting all child keys
     *
     * @return the keys of all children
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override val children: Array<String> get() = statics.children


    // ****************************
    // implementations for extended InterpreterValue
    // >> invoking

    /**
     * Create a new instance of a class
     *
     * @param node the node that created the instance
     * @param scope the scope the creation was made in (to process the arguments)
     * @return the created [InterpreterValue]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun newInstance(node: ClassConstructionNode, scope: Scope): InterpreterValue = ObjectValue(this)


    // *******************************
    // implementations for extended InterpreterValue
    // >> get-name

    /**
     * Returns the name of the type of [InterpreterValue] (To identify the type of value)
     * For [ClassValue] it just always returns "class"
     *
     * @return "class"
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override val name: String get() = "class"


    // *******************************
    // Override toString()

    /**
     * Returns the string representation of the [ClassValue]
     *
     * @return the string representation of the [ClassValue]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun toString(): String {
        // Create a string representation of the class by logging out all the important parts
        return "Class{" +
                "name=" + this.className +
                ", access=" + access +
                ", isFinal=" + isFinal +
                ", prototype=" + prototype +
                ", fields=" + fields.contentToString() +
                ", statics=" + statics +
                '}'
    }
}