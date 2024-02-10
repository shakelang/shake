package com.shakelang.shake.processor.program.types.code.values

import com.shakelang.shake.processor.program.types.ShakeProject
import com.shakelang.shake.processor.program.types.ShakeType

/**
 * Represents a value in the Shake language.
 * A value is a fundamental entity in programming that can be manipulated or evaluated.
 *
 * @since 0.1.0
 */
interface ShakeValue {
    /**
     * The project to which this value belongs.
     */
    val project: ShakeProject

    /**
     * The type of this value.
     */
    val type: ShakeType

    /**
     * Converts this value to a JSON representation.
     * Useful for serialization, debugging, or for interfacing with external systems.
     *
     * @return A map representing the JSON structure of this value.
     */
    fun toJson(): Map<String, Any?>
}
