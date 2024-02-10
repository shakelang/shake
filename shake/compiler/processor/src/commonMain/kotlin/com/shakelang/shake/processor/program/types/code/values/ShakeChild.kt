package com.shakelang.shake.processor.program.types.code.values

import com.shakelang.shake.processor.program.types.ShakeAssignable
import com.shakelang.shake.processor.program.types.ShakeField
import com.shakelang.shake.processor.program.types.code.ShakeScope

/**
 * Represents a child element in the Shake language, which is assignable and linked to a parent value.
 *
 * @since 0.1.0
 */
interface ShakeChild : ShakeAssignable {
    /**
     * The scope in which this child element exists.
     */
    val scope: ShakeScope

    /**
     * The parent value to which this child is associated.
     */
    val parent: ShakeValue

    /**
     * The field representing this child element.
     */
    val field: ShakeField

    /**
     * The name of the child element.
     */
    val name: String

    /**
     * Accessor for the value of the child element.
     */
    val access: ShakeValue
}

/**
 * Represents the usage of a ShakeChild element within the Shake language.
 *
 * @since 0.1.0
 */
interface ShakeChildUsage : ShakeUsage {
    /**
     * The ShakeChild element that is being used.
     */
    val used: ShakeChild
}
