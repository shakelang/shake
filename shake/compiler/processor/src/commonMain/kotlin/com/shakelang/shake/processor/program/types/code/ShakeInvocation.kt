package com.shakelang.shake.processor.program.types.code

import com.shakelang.shake.processor.program.types.code.statements.ShakeStatement
import com.shakelang.shake.processor.program.types.code.values.ShakeValue

/**
 * Represents an invocation in the Shake language.
 * An invocation is a call to a callable entity (like a function or a method) with a set of arguments.
 *
 * @since 0.1.0
 */
interface ShakeInvocation : ShakeValue, ShakeStatement {

    /**
     * The callable entity that is being invoked.
     */
    val callable: ShakeInvokable

    /**
     * The list of arguments passed to the invocation.
     */
    val arguments: List<ShakeValue>

    /**
     * The parent value of this invocation, if any.
     * This is typically used in method calls where the parent refers to the object on which the method is called.
     */
    val parent: ShakeValue?

    /**
     * The name of the callable entity being invoked.
     */
    val name: String

    /**
     * Indicates if the invocation is anonymous.
     * An anonymous invocation does not have a directly associated name in the source code.
     */
    val isAnonymous: Boolean

    /**
     * Converts the invocation to a JSON representation.
     * Useful for serialization or debugging purposes.
     *
     * @return A map representing the JSON structure of this invocation.
     */
    override fun toJson(): Map<String, Any?>
}
