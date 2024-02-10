package com.shakelang.shake.processor.program.types.code.values

import com.shakelang.shake.processor.program.types.ShakeDeclaration
import com.shakelang.shake.processor.program.types.ShakeField
import com.shakelang.shake.processor.program.types.code.ShakeScope

/**
 * Represents a general usage of a value in the Shake language.
 * This is a base interface for various specific types of usages.
 *
 * @since 0.1.0
 */
interface ShakeUsage : ShakeValue {
    /**
     * The scope in which the usage occurs.
     */
    val scope: ShakeScope

    /**
     * The declaration associated with this usage.
     */
    val declaration: ShakeDeclaration
}

/**
 * Represents the usage of a field in a class instance in the Shake language.
 *
 * @since 0.1.0
 */
interface ShakeClassFieldUsage {
    /**
     * The scope in which the field usage occurs.
     */
    val scope: ShakeScope

    /**
     * The receiver of the field. This is the instance of the class where the field is used.
     */
    val receiver: ShakeValue?

    /**
     * The name of the field being used.
     */
    val name: String

    /**
     * The declaration of the field being used.
     */
    val declaration: ShakeField
}

/**
 * Represents the usage of a static field in a class in the Shake language.
 *
 * @since 0.1.0
 */
interface ShakeStaticClassFieldUsage : ShakeUsage {
    /**
     * The name of the static field being used.
     */
    val name: String
}

/**
 * Represents the usage of a field in a class instance or an object in the Shake language.
 *
 * @since 0.1.0
 */
interface ShakeFieldUsage : ShakeUsage {
    /**
     * The receiver of the field. This is the instance of the class or object where the field is used.
     */
    val receiver: ShakeValue?

    /**
     * The name of the field being used. It is derived from the associated declaration.
     */
    val name get() = declaration.name
}

/**
 * Represents the usage of a variable in the Shake language.
 *
 * @since 0.1.0
 */
interface ShakeVariableUsage : ShakeUsage {
    /**
     * The declaration of the variable being used.
     */
    override val declaration: ShakeDeclaration

    /**
     * The name of the variable being used.
     */
    val name: String
}
