package com.shakelang.shake.bytecode.interpreter

import com.shakelang.shake.util.io.streaming.output.DataOutputStream

class ShakeInterpreterClass(
    val methods: List<ShakeInterpreterMethod>,
    val fields: List<ShakeInterpreterField>
)

class ShakeInterpreterMethod(
    val qualifiedName: String,
    val simpleName: String,
    val isStatic: Boolean,
    val returnType: ShakeInterpreterType,
    val parameters: List<ShakeInterpreterType>,
    val code: ByteArray
) {
    fun dump(stream: DataOutputStream) {
    }
}

class ShakeInterpreterField(
    val qualifiedName: String,
    val simpleName: String,
    val isStatic: Boolean,
    val type: ShakeInterpreterType
)

open class ShakeInterpreterType(
    val name: String,
    val type: Type,
    val subType: ShakeInterpreterType? = null,
    val classType: ShakeInterpreterClass? = null
) {

    enum class Type {
        INT,
        FLOAT,
        DOUBLE,
        LONG,
        SHORT,
        BYTE,
        CHAR,
        BOOLEAN,
        STRING,
        OBJECT,
        ARRAY,
        VOID
    }
}
