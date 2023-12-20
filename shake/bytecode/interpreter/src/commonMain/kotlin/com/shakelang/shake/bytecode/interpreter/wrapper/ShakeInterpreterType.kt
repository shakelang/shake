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

        val BYTE = ShakeInterpreterType("B", Type.BYTE)
        val SHORT = ShakeInterpreterType("S", Type.SHORT)
        val INT = ShakeInterpreterType("I", Type.INT)
        val LONG = ShakeInterpreterType("J", Type.LONG)
        val UNSIGNED_BYTE = ShakeInterpreterType("b", Type.BYTE)
        val UNSIGNED_SHORT = ShakeInterpreterType("s", Type.SHORT)
        val UNSIGNED_INT = ShakeInterpreterType("i", Type.INT)
        val UNSIGNED_LONG = ShakeInterpreterType("j", Type.LONG)
        val FLOAT = ShakeInterpreterType("F", Type.FLOAT)
        val DOUBLE = ShakeInterpreterType("D", Type.DOUBLE)
        val CHAR = ShakeInterpreterType("C", Type.CHAR)
        val BOOLEAN = ShakeInterpreterType("Z", Type.BOOLEAN)
        val VOID = ShakeInterpreterType("V", Type.VOID)

        fun of(storage: TypeDescriptor, classpath: ShakeClasspath): ShakeInterpreterType {
            return when (storage) {
                is TypeDescriptor.ByteType -> BYTE
                is TypeDescriptor.ShortType -> SHORT
                is TypeDescriptor.IntType -> INT
                is TypeDescriptor.LongType -> LONG
                is TypeDescriptor.UnsignedByteType -> UNSIGNED_BYTE
                is TypeDescriptor.UnsignedShortType -> UNSIGNED_SHORT
                is TypeDescriptor.UnsignedIntType -> UNSIGNED_INT
                is TypeDescriptor.UnsignedLongType -> UNSIGNED_LONG
                is TypeDescriptor.FloatType -> FLOAT
                is TypeDescriptor.DoubleType -> DOUBLE
                is TypeDescriptor.CharType -> CHAR
                is TypeDescriptor.BooleanType -> BOOLEAN
                is TypeDescriptor.VoidType -> VOID
                is TypeDescriptor.ArrayType -> ArrayType(of(storage.type, classpath))
                is TypeDescriptor.ObjectType -> ObjectType(classpath.getClass(storage.className)!!)
                else -> throw IllegalArgumentException("Unknown type: ${storage::class.simpleName}")
            }
        }
    }
}
