package com.shakelang.shake.bytecode.interpreter.format

import com.shakelang.shake.util.io.streaming.input.InputStream
import com.shakelang.shake.util.io.streaming.input.byteStream

/**
 * A type (of a field, method, parameter, variable, etc.)
 * @since 0.1.0
 * @version 0.1.0
 */
interface Type {

    /**
     * Get the name of the type
     * @since 0.1.0
     * @version 0.1.0
     */
    val descriptor: String

    /**
     * Get the size for a primitive storage of this type
     * @since 0.1.0
     * @version 0.1.0
     */
    val byteSize: Int

    /**
     * A [ByteType] represents the primitive type `byte` in shake
     * It has the descriptor `B` and a size of 1
     * @since 0.1.0
     * @version 0.1.0
     */
    enum class ByteType : Type {

        /**
         * The [INSTANCE] of the [ByteType]
         * @since 0.1.0
         */
        INSTANCE
        ;

        /**
         * The descriptor of the [ByteType]
         * @since 0.1.0
         */
        override val descriptor: String get() = "B"

        /**
         * The size of the [ByteType]
         * @since 0.1.0
         */
        override val byteSize: Int get() = 1

        /**
         * Get the [String] representation of the [ByteType]
         * @since 0.1.0
         */
        override fun toString(): String = descriptor
    }

    /**
     * A [ShortType] represents the primitive type `short` in shake
     * It has the descriptor `S` and a size of 2
     * @since 0.1.0
     * @version 0.1.0
     */
    enum class ShortType : Type {

        /**
         * The [INSTANCE] of the [ShortType]
         * @since 0.1.0
         */
        INSTANCE
        ;

        /**
         * The descriptor of the [ShortType]
         * @since 0.1.0
         */
        override val descriptor: String get() = "S"

        /**
         * The size of the [ShortType]
         * @since 0.1.0
         */
        override val byteSize: Int get() = 2

        /**
         * Get the [String] representation of the [ShortType]
         * @since 0.1.0
         */
        override fun toString(): String = descriptor
    }

    /**
     * A [IntType] represents the primitive type `int` in shake
     * It has the descriptor `I` and a size of 4
     * @since 0.1.0
     * @version 0.1.0
     */
    enum class IntType : Type {

        /**
         * The [INSTANCE] of the [IntType]
         * @since 0.1.0
         * @version 0.1.0
         */
        INSTANCE
        ;

        /**
         * The descriptor of the [IntType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val descriptor: String get() = "I"

        /**
         * The size of the [IntType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val byteSize: Int get() = 4

        /**
         * Get the [String] representation of the [IntType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString(): String = descriptor
    }

    /**
     * A [LongType] represents the primitive type `long` in shake
     * It has the descriptor `J` and a size of 8
     * @since 0.1.0
     * @version 0.1.0
     */
    enum class LongType : Type {

        /**
         * The [INSTANCE] of the [LongType]
         * @since 0.1.0
         * @version 0.1.0
         */
        INSTANCE
        ;

        /**
         * The descriptor of the [LongType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val descriptor: String get() = "J"

        /**
         * The size of the [LongType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val byteSize: Int get() = 8

        /**
         * Get the [String] representation of the [LongType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString(): String = descriptor
    }

    /**
     * A [UnsignedByteType] represents the primitive type `ubyte` in shake
     * It has the descriptor `b` and a size of 1
     * @since 0.1.0
     * @version 0.1.0
     */
    enum class UnsignedByteType : Type {

        /**
         * The [INSTANCE] of the [UnsignedByteType]
         * @since 0.1.0
         * @version 0.1.0
         */
        INSTANCE
        ;

        /**
         * The descriptor of the [UnsignedByteType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val descriptor: String get() = "b"

        /**
         * The size of the [UnsignedByteType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val byteSize: Int get() = 1

        /**
         * Get the [String] representation of the [UnsignedByteType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString(): String = descriptor
    }

    /**
     * A [UnsignedShortType] represents the primitive type `ushort` in shake
     * It has the descriptor `s` and a size of 2
     * @since 0.1.0
     * @version 0.1.0
     */
    enum class UnsignedShortType : Type {

        /**
         * The [INSTANCE] of the [UnsignedShortType]
         * @since 0.1.0
         * @version 0.1.0
         */
        INSTANCE
        ;

        /**
         * The descriptor of the [UnsignedShortType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val descriptor: String get() = "s"

        /**
         * The size of the [UnsignedShortType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val byteSize: Int get() = 2

        /**
         * Get the [String] representation of the [UnsignedShortType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString(): String = descriptor
    }

    /**
     * A [UnsignedIntType] represents the primitive type `uint` in shake
     * It has the descriptor `i` and a size of 4
     * @since 0.1.0
     * @version 0.1.0
     */
    enum class UnsignedIntType : Type {

        /**
         * The [INSTANCE] of the [UnsignedIntType]
         * @since 0.1.0
         * @version 0.1.0
         */
        INSTANCE
        ;

        /**
         * The descriptor of the [UnsignedIntType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val descriptor: String get() = "i"

        /**
         * The size of the [UnsignedIntType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val byteSize: Int get() = 4

        /**
         * Get the [String] representation of the [UnsignedIntType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString(): String = descriptor
    }

    /**
     * A [UnsignedLongType] represents the primitive type `ulong` in shake
     * It has the descriptor `l` and a size of 8
     * @since 0.1.0
     * @version 0.1.0
     */
    enum class UnsignedLongType : Type {

        /**
         * The [INSTANCE] of the [UnsignedLongType]
         * @since 0.1.0
         * @version 0.1.0
         */
        INSTANCE
        ;

        /**
         * The descriptor of the [UnsignedLongType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val descriptor: String get() = "l"

        /**
         * The size of the [UnsignedLongType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val byteSize: Int get() = 8

        /**
         * Get the [String] representation of the [UnsignedLongType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString(): String = descriptor
    }

    /**
     * A [FloatType] represents the primitive type `float` in shake
     * It has the descriptor `F` and a size of 4
     * @since 0.1.0
     * @version 0.1.0
     */
    enum class FloatType : Type {

        /**
         * The [INSTANCE] of the [FloatType]
         * @since 0.1.0
         * @version 0.1.0
         */
        INSTANCE
        ;

        /**
         * The descriptor of the [FloatType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val descriptor: String get() = "F"

        /**
         * The size of the [FloatType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val byteSize: Int get() = 4

        /**
         * Get the [String] representation of the [FloatType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString(): String = descriptor
    }

    /**
     * A [DoubleType] represents the primitive type `double` in shake
     * It has the descriptor `D` and a size of 8
     * @since 0.1.0
     * @version 0.1.0
     */
    enum class DoubleType : Type {

        /**
         * The [INSTANCE] of the [DoubleType]
         * @since 0.1.0
         * @version 0.1.0
         */
        INSTANCE
        ;

        /**
         * The descriptor of the [DoubleType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val descriptor: String get() = "D"

        /**
         * The size of the [DoubleType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val byteSize: Int get() = 8

        /**
         * Get the [String] representation of the [DoubleType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString(): String = descriptor
    }

    /**
     * A [CharType] represents the primitive type `char` in shake
     * It has the descriptor `C` and a size of 2
     * @since 0.1.0
     * @version 0.1.0
     */
    enum class CharType : Type {

        /**
         * The [INSTANCE] of the [CharType]
         * @since 0.1.0
         * @version 0.1.0
         */
        INSTANCE
        ;

        /**
         * The descriptor of the [CharType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val descriptor: String get() = "C"

        /**
         * The size of the [CharType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val byteSize: Int get() = 2

        /**
         * Get the [String] representation of the [CharType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString(): String = descriptor
    }

    /**
     * A [BooleanType] represents the primitive type `boolean` in shake
     * It has the descriptor `Z` and a size of 1
     * @since 0.1.0
     * @version 0.1.0
     */
    enum class BooleanType : Type {

        /**
         * The [INSTANCE] of the [BooleanType]
         * @since 0.1.0
         * @version 0.1.0
         */
        INSTANCE
        ;

        /**
         * The descriptor of the [BooleanType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val descriptor: String get() = "Z"

        /**
         * The size of the [BooleanType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val byteSize: Int get() = 1

        /**
         * Get the [String] representation of the [BooleanType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString(): String = descriptor
    }

    /**
     * A [VoidType] represents the primitive type `void` in shake
     * It has the descriptor `V` and a size of 0
     * @since 0.1.0
     * @version 0.1.0
     */
    enum class VoidType : Type {

        /**
         * The [INSTANCE] of the [VoidType]
         * @since 0.1.0
         * @version 0.1.0
         */
        INSTANCE
        ;

        /**
         * The descriptor of the [VoidType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val descriptor: String get() = "V"

        /**
         * The size of the [VoidType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val byteSize: Int get() = 0

        /**
         * Get the [String] representation of the [VoidType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString(): String = descriptor
    }

    /**
     * A [ObjectType] represents a object type in shake
     * It has the descriptor `L` and a size of 8
     * @since 0.1.0
     * @version 0.1.0
     */
    class ObjectType(val className: String, val genericTypes: List<Type> = emptyList()) : Type {

        /**
         * The descriptor of the [ObjectType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val descriptor: String
            get() {
                if (genericTypes.isEmpty()) {
                    return "L$className;"
                } else {
                    return "L$className@${genericTypes.joinToString("+") { it.descriptor }};"
                }
            }

        /**
         * The size of the [ObjectType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val byteSize: Int get() = 8

        /**
         * Is the [ObjectType] a generic type
         */
        val isGeneric: Boolean get() = genericTypes.isNotEmpty()

        /**
         * Get the [String] representation of the [ObjectType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString() = descriptor

        /**
         * Does the [ObjectType] equals the [other] [Any]
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is ObjectType) return false

            if (className != other.className) return false
            if (genericTypes.size != other.genericTypes.size) return false
            for (i in genericTypes.indices) {
                if (genericTypes[i] != other.genericTypes[i]) return false
            }

            return true
        }

        /**
         * Get the [Int] hash of the [ObjectType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun hashCode(): Int {
            var result = className.hashCode()
            result = 31 * result + genericTypes.hashCode()
            return result
        }
    }

    /**
     * A [ArrayType] represents an array type in shake
     * It has the descriptor `[` and a size of 8
     * @since 0.1.0
     * @version 0.1.0
     */
    class ArrayType(val type: Type) : Type {

        /**
         * The descriptor of the [ArrayType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val descriptor: String get() = "[${type.descriptor}"

        /**
         * The size of the [ArrayType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val byteSize: Int get() = 8

        /**
         * Get the [String] representation of the [ArrayType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is ArrayType) return false

            if (type != other.type) return false

            return true
        }

        /**
         * Get the [Int] hash of the [ArrayType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun hashCode(): Int {
            return type.hashCode()
        }
    }

    companion object {

        /**
         * Parse a [Type] from an [InputStream]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun parse(name: InputStream): Type {
            val next = name.read().toChar()

            when (next) {
                'B' -> return ByteType.INSTANCE
                'S' -> return ShortType.INSTANCE
                'I' -> return IntType.INSTANCE
                'J' -> return LongType.INSTANCE
                'b' -> return UnsignedByteType.INSTANCE
                's' -> return UnsignedShortType.INSTANCE
                'i' -> return UnsignedIntType.INSTANCE
                'l' -> return UnsignedLongType.INSTANCE
                'F' -> return FloatType.INSTANCE
                'D' -> return DoubleType.INSTANCE
                'C' -> return CharType.INSTANCE
                'Z' -> return BooleanType.INSTANCE
                'V' -> return VoidType.INSTANCE
                '[' -> return ArrayType(parse(name))
                'L' -> {
                    val className = StringBuilder()
                    var next2 = name.read().toChar()
                    while (next2 != '@' && next2 != ';') {
                        className.append(next2)
                        next2 = name.read().toChar()
                    }
                    if (next2 == ';') return ObjectType(className.toString(), emptyList())

                    val genericTypes = mutableListOf<Type>()
                    while (next2 != ';') {
                        genericTypes.add(parse(name))
                        next2 = name.read().toChar()
                    }
                    return ObjectType(className.toString(), genericTypes)
                }

                else -> throw Exception("Unknown type: $next")
            }
        }

        /**
         * Parse a [Type] from a [String]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun parse(name: String): Type = parse(name.byteStream())
    }
}
