package com.shakelang.shake.bytecode.interpreter.wrapper

import com.shakelang.shake.bytecode.interpreter.format.Field
import com.shakelang.shake.bytecode.interpreter.format.descriptor.TypeDescriptor

interface ShakeInterpreterField {
    val storage: Field
    val qualifiedName: String
    val simpleName: String
    val isStatic: Boolean
    val type: ShakeInterpreterType

    companion object {
        fun of(storage: Field, classpath: ShakeClasspath, parentPath: String): ShakeInterpreterField {

                return object : ShakeInterpreterField {

                    override val storage: Field = storage
                    override val simpleName: String = storage.name
                    override val qualifiedName: String = "$parentPath$simpleName"
                    override val isStatic: Boolean = storage.isStatic
                    override val type: ShakeInterpreterType = ShakeInterpreterType.of(TypeDescriptor.parse(storage.type), classpath)

                }
        }
    }
}