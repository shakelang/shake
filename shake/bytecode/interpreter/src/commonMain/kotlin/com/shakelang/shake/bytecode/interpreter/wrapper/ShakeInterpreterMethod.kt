package com.shakelang.shake.bytecode.interpreter.wrapper

import com.shakelang.shake.bytecode.interpreter.ShakeInterpreter
import com.shakelang.shake.bytecode.interpreter.format.Method
import com.shakelang.shake.bytecode.interpreter.format.attribute.CodeAttribute
import com.shakelang.shake.bytecode.interpreter.format.descriptor.MethodDescriptor
import com.shakelang.shake.bytecode.interpreter.format.pool.ConstantPool

interface ShakeInterpreterMethod {
    val interpreter: ShakeInterpreter
    val storage: Method
    val qualifiedName: String
    val simpleName: String
    val fullName: String
    val isPublic: Boolean
    val isPrivate: Boolean
    val isProtected: Boolean
    val isFinal: Boolean
    val isSynchronized: Boolean
    val isStatic: Boolean
    val isNative: Boolean
    val isConstructor: Boolean
    val returnType: ShakeInterpreterType
    val parameters: List<ShakeInterpreterType>
    val code: ByteArray
    val exceptionHandlers: List<CodeAttribute.ExceptionTableEntry>
    val maxStack: Int
    val maxLocals: Int
    val pkg: ShakeInterpreterPackage
    val clazz: ShakeInterpreterClass?
    val constantPool: ConstantPool

    companion object {

        fun of(
            storage: Method,
            classpath: ShakeInterpreterClasspath,
            parentPath: String,
            constantPool: ConstantPool,
            pkg: ShakeInterpreterPackage,
            clazz: ShakeInterpreterClass?,
        ): ShakeInterpreterMethod {
            val attributes = storage.attributes
            val code = attributes.find { it.name == "Code" }
                ?.let { it as CodeAttribute }
            val qualifiedName = storage.qualifiedName
            val parsed = MethodDescriptor.parse(qualifiedName)

            return object : ShakeInterpreterMethod {
                override val interpreter: ShakeInterpreter
                    get() = classpath.interpreter
                override val storage: Method = storage
                override val simpleName: String = storage.name
                override val qualifiedName: String = storage.qualifiedName
                override val fullName: String = "$parentPath$qualifiedName"
                override val isPublic: Boolean = storage.isPublic
                override val isPrivate: Boolean = storage.isPrivate
                override val isProtected: Boolean = storage.isProtected
                override val isFinal: Boolean = storage.isFinal
                override val isSynchronized: Boolean = storage.isSynchronized
                override val isStatic: Boolean = storage.isStatic
                override val isNative: Boolean = storage.isNative
                override val isConstructor: Boolean = storage.isConstructor
                override val returnType: ShakeInterpreterType = ShakeInterpreterType.of(parsed.returnType, classpath)
                override val parameters: List<ShakeInterpreterType> = parsed.parameters.map {
                    ShakeInterpreterType.of(
                        it,
                        classpath,
                    )
                }
                override val constantPool: ConstantPool = constantPool
                override val pkg: ShakeInterpreterPackage = pkg
                override val clazz: ShakeInterpreterClass? = clazz

                override val code: ByteArray
                    get() = code?.code ?: throw NullPointerException("Method ${this.qualifiedName} has no code!")
                override val exceptionHandlers: List<CodeAttribute.ExceptionTableEntry>
                    get() = code?.exceptionTable?.toList()
                        ?: throw NullPointerException("Method ${this.qualifiedName} has no code!")
                override val maxStack: Int
                    get() = code?.maxStack ?: throw NullPointerException("Method ${this.qualifiedName} has no code!")
                override val maxLocals: Int
                    get() = code?.maxLocals ?: throw NullPointerException("Method ${this.qualifiedName} has no code!")
            }
        }
    }
}
