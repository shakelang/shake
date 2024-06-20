package com.shakelang.shake.bytecode.interpreter.wrapper

import com.shakelang.shake.conventions.descriptor.TypeDescriptor

interface ShakeInterpreterType {
    val name: String
    val type: Type
    val byteSize: Int

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
        OBJECT,
        ARRAY,
    }

    class ArrayType(val arrayType: ShakeInterpreterType) : ShakeInterpreterType {
        override val name: String = "[${arrayType.name}"
        override val type: Type = Type.ARRAY
        override val byteSize: Int = 8
    }

    class ObjectType(val objectType: ShakeInterpreterClass) : ShakeInterpreterType {
        override val name: String = "L${objectType.qualifiedName};"
        override val type: Type = Type.OBJECT
        override val byteSize: Int = 8
    }

    companion object {
        val BYTE = object : ShakeInterpreterType {
            override val name: String = "B"
            override val type: Type = Type.BYTE
            override val byteSize: Int = 1
        }
        val SHORT = object : ShakeInterpreterType {
            override val name: String = "S"
            override val type: Type = Type.SHORT
            override val byteSize: Int = 2
        }
        val INT = object : ShakeInterpreterType {
            override val name: String = "I"
            override val type: Type = Type.INT
            override val byteSize: Int = 4
        }
        val LONG = object : ShakeInterpreterType {
            override val name: String = "J"
            override val type: Type = Type.LONG
            override val byteSize: Int = 8
        }
        val UNSIGNED_BYTE = object : ShakeInterpreterType {
            override val name: String = "b"
            override val type: Type = Type.BYTE
            override val byteSize: Int = 1
        }
        val UNSIGNED_SHORT = object : ShakeInterpreterType {
            override val name: String = "s"
            override val type: Type = Type.SHORT
            override val byteSize: Int = 2
        }
        val UNSIGNED_INT = object : ShakeInterpreterType {
            override val name: String = "i"
            override val type: Type = Type.INT
            override val byteSize: Int = 4
        }
        val UNSIGNED_LONG = object : ShakeInterpreterType {
            override val name: String = "j"
            override val type: Type = Type.LONG
            override val byteSize: Int = 8
        }
        val FLOAT = object : ShakeInterpreterType {
            override val name: String = "F"
            override val type: Type = Type.FLOAT
            override val byteSize: Int = 4
        }
        val DOUBLE = object : ShakeInterpreterType {
            override val name: String = "D"
            override val type: Type = Type.DOUBLE
            override val byteSize: Int = 8
        }
        val CHAR = object : ShakeInterpreterType {
            override val name: String = "C"
            override val type: Type = Type.CHAR
            override val byteSize: Int = 2
        }
        val BOOLEAN = object : ShakeInterpreterType {
            override val name: String = "Z"
            override val type: Type = Type.BOOLEAN
            override val byteSize: Int = 1
        }
        val VOID = object : ShakeInterpreterType {
            override val name: String = "V"
            override val type: Type = Type.VOID
            override val byteSize: Int = 0
        }
        val NEW = object : ShakeInterpreterType {
            override val name: String = "N"
            override val type: Type = Type.OBJECT
            override val byteSize: Int = 8
        }

        fun of(storage: TypeDescriptor, classpath: ShakeInterpreterClasspath): ShakeInterpreterType = when (storage) {
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
            is TypeDescriptor.NewType -> NEW
            is TypeDescriptor.ArrayType -> ArrayType(of(storage.type, classpath))
            is TypeDescriptor.ObjectType -> ObjectType(classpath.getClass(storage.className)!!)
            else -> throw IllegalArgumentException("Unknown type: ${storage::class.simpleName}")
        }
    }
}
