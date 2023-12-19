package com.shakelang.shake.bytecode.interpreter.wrapper

import com.shakelang.shake.bytecode.interpreter.format.Field

interface ShakeInterpreterField {
    val storage: Field
    val qualifiedName: String
    val simpleName: String
    val isStatic: Boolean
    val type: ShakeInterpreterType

    companion object {
        fun of(storage: Field): ShakeInterpreterField {
            TODO()
        }
    }
}