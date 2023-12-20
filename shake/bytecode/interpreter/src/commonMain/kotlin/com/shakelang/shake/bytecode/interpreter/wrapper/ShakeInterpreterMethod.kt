package com.shakelang.shake.bytecode.interpreter.wrapper

import com.shakelang.shake.bytecode.interpreter.format.Method
import com.shakelang.shake.bytecode.interpreter.format.descriptor.MethodDescriptor

interface ShakeInterpreterMethod {
    val storage: Method
    val qualifiedName: String
    val simpleName: String
    val isStatic: Boolean
    val returnType: ShakeInterpreterType
    val parameters: List<ShakeInterpreterType>
    val code: ByteArray

    companion object {
        fun of(storage: Method, classpath: ShakeClasspath, parentPath: String): ShakeInterpreterMethod {

            val attributes = storage.attributes
            val code = attributes.find { it.name == "Code" }?.let { it as com.shakelang.shake.bytecode.interpreter.format.attribute.CodeAttribute }
            val qualifiedName = storage.qualifiedName
            val parsed = MethodDescriptor.parse(qualifiedName)

            return object : ShakeInterpreterMethod {

                override val storage: Method = storage
                override val simpleName: String = storage.name
                override val qualifiedName: String = "$parentPath$simpleName"
                override val isStatic: Boolean = storage.isStatic
                override val returnType: ShakeInterpreterType = ShakeInterpreterType.of(parsed.returnType, classpath)
                override val parameters: List<ShakeInterpreterType> = parsed.parameters.map {
                    ShakeInterpreterType.of(
                        it,
                        classpath
                    )
                }
                override val code: ByteArray get() = code?.code ?: throw NullPointerException("Method ${this.qualifiedName} has no code!")

            }
        }
    }
}