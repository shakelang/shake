package com.shakelang.shake.processor.program.types.code

import com.shakelang.shake.processor.program.types.ShakeConstructor
import com.shakelang.shake.processor.program.types.code.values.ShakeValue

/**
 * Represents a 'new' expression in the Shake language.
 * This is used to create new instances of classes, invoking constructors with specified arguments.
 *
 * @since 0.1.0
 */
interface ShakeNew : ShakeValuedStatement {

    /**
     * The constructor reference which is to be invoked by this 'new' expression.
     */
    val reference: ShakeConstructor

    /**
     * The list of arguments to be passed to the constructor.
     */
    val arguments: List<ShakeValue>

    /**
     * The parent value, if any, of the new instance being created.
     * This can be used in cases where nested member initialization is involved.
     */
    val parent: ShakeValue?

    /**
     * The name of the class or entity being instantiated.
     */
    val name: String
}
