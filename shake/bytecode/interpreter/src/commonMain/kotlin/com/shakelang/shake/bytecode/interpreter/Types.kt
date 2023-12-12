package com.shakelang.shake.bytecode.interpreter

interface ShakeInterpreterClass {
    val methods: List<ShakeInterpreterMethod>
    val fields: List<ShakeInterpreterField>
}

interface ShakeInterpreterMethod {
    val qualifiedName: String
    val simpleName: String
    val isStatic: Boolean
    val returnType: ShakeInterpreterType
    val parameters: List<ShakeInterpreterType>
    val code: ByteArray
}

interface ShakeInterpreterField {
    val qualifiedName: String
    val simpleName: String
    val isStatic: Boolean
    val type: ShakeInterpreterType
}

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
