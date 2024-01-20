package com.shakelang.shake.bytecode.interpreter.wrapper

import com.shakelang.shake.bytecode.interpreter.ShakeInterpreter
import com.shakelang.shake.bytecode.interpreter.format.Field
import com.shakelang.shake.bytecode.interpreter.format.descriptor.TypeDescriptor
import com.shakelang.shake.bytecode.interpreter.format.pool.ConstantPool

interface ShakeInterpreterField {
    val storage: Field
    val qualifiedName: String
    val simpleName: String
    val isStatic: Boolean
    val type: ShakeInterpreterType
    val pkg: ShakeInterpreterPackage
    val constantPool: ConstantPool
    val interpreter: ShakeInterpreter

    companion object {
        fun of(
            storage: Field,
            classpath: ShakeClasspath,
            parentPath: String,
            constantPool: ConstantPool,
            pkg: ShakeInterpreterPackage,
        ): ShakeInterpreterField {
            return object : ShakeInterpreterField {
                override val interpreter: ShakeInterpreter
                    get() = classpath.interpreter
                override val storage: Field = storage
                override val simpleName: String = storage.name
                override val qualifiedName: String = "$parentPath$simpleName"
                override val isStatic: Boolean = storage.isStatic
                override val type: ShakeInterpreterType =
                    ShakeInterpreterType.of(TypeDescriptor.parse(storage.type), classpath)
                override val constantPool: ConstantPool = constantPool
                override val pkg: ShakeInterpreterPackage = pkg
            }
        }
    }
}
