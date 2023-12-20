package com.shakelang.shake.bytecode.interpreter.wrapper

import com.shakelang.shake.bytecode.interpreter.format.descriptor.TypeDescriptor

open class ShakeInterpreterType(
    val name: String,
    val type: Type,
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
        VOID,
        STRING,
        OBJECT,
        ARRAY
    }

    class ArrayType(val arrayType: ShakeInterpreterType) : ShakeInterpreterType(
        "[${arrayType.name}",
        Type.ARRAY,
    )

    class ObjectType(val objectType: ShakeInterpreterClass) : ShakeInterpreterType(
        "L${objectType.qualifiedName};",
        Type.OBJECT,
    )



    companion object {

        val INT = ShakeInterpreterType("int", Type.INT)
        val FLOAT = ShakeInterpreterType("float", Type.FLOAT)
        val DOUBLE = ShakeInterpreterType("double", Type.DOUBLE)
        val LONG = ShakeInterpreterType("long", Type.LONG)
        val SHORT = ShakeInterpreterType("short", Type.SHORT)
        val BYTE = ShakeInterpreterType("byte", Type.BYTE)
        val CHAR = ShakeInterpreterType("char", Type.CHAR)
        val BOOLEAN = ShakeInterpreterType("boolean", Type.BOOLEAN)
        val VOID = ShakeInterpreterType("void", Type.VOID)

        fun of(storage: TypeDescriptor, classpath: ShakeClasspath): ShakeInterpreterType {
            return when (storage) {
                is TypeDescriptor.ByteType -> BYTE
                is TypeDescriptor.CharType -> CHAR
                is TypeDescriptor.DoubleType -> DOUBLE
                is TypeDescriptor.FloatType -> FLOAT
                is TypeDescriptor.IntType -> INT
                is TypeDescriptor.LongType -> LONG
                is TypeDescriptor.ShortType -> SHORT
                is TypeDescriptor.BooleanType -> BOOLEAN
                is TypeDescriptor.VoidType -> VOID
                is TypeDescriptor.ArrayType -> ArrayType(of(storage.type, classpath))
                is TypeDescriptor.ObjectType -> ObjectType(classpath.getClass(storage.className)!!)
                else -> throw IllegalArgumentException("Unknown type: ${storage::class.simpleName}")
            }
        }
    }
}
