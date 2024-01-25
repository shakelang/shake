package com.shakelang.shake.bytecode.interpreter.format.descriptor

import com.shakelang.util.io.streaming.input.InputStream
import com.shakelang.util.io.streaming.input.byteStream

/**
 * A type (of a field, method, parameter, variable, etc.)
 *
 * [Specification](https://spec.shakelang.com/bytecode/storage-format#type-descriptors)
 *
 * @since 0.1.0
 * @version 0.1.0
 */
@Suppress("unused")
interface TypeDescriptor {

    /**
     * Get the string representation of the [TypeDescriptor]
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#type-descriptors)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    val descriptor: String

    /**
     * Get the size for a primitive storage of this type
     * Objects and arrays are stored as pointers and therefore always
     * have a size of 8
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    val byteSize: Int

    /**
     * A [ByteType] represents the primitive type `byte` in shake
     *
     * It has the descriptor `B` and a size of 1
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#byte)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    object ByteType : TypeDescriptor {

        /**
         * The descriptor of the [ByteType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val descriptor: String get() = "B"

        /**
         * The size of the [ByteType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val byteSize: Int get() = 1

        /**
         * Get the [String] representation of the [ByteType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString(): String = descriptor
    }

    /**
     * A [ShortType] represents the primitive type `short` in shake
     * It has the descriptor `S` and a size of 2
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#short)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    object ShortType : TypeDescriptor {

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
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#int)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    object IntType : TypeDescriptor {

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
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#long)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    object LongType : TypeDescriptor {

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
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#ubyte)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    object UnsignedByteType : TypeDescriptor {

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
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#ushort)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    object UnsignedShortType : TypeDescriptor {

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
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#uint)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    object UnsignedIntType : TypeDescriptor {

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
     * It has the descriptor `j` and a size of 8
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#ulong)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    object UnsignedLongType : TypeDescriptor {

        /**
         * The descriptor of the [UnsignedLongType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val descriptor: String get() = "j"

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
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#float)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    object FloatType : TypeDescriptor {

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
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#double)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    object DoubleType : TypeDescriptor {

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
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#char)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    object CharType : TypeDescriptor {

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
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#boolean)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    object BooleanType : TypeDescriptor {

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
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#void)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    object VoidType : TypeDescriptor {

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
     * A [NewType] is a symbolic type. It is used to represent an anonymous pointer
     * to a new object created by a constructor call.
     */
    object NewType : TypeDescriptor {

        /**
         * The descriptor of the [NewType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val descriptor: String get() = "N"

        /**
         * The size of the [NewType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val byteSize: Int get() = 8

        /**
         * Get the [String] representation of the [NewType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString() = descriptor
    }

    /**
     * A [ObjectType] represents an object type in shake
     * It has the descriptor `L<class>[:genericTypes (joined using +)];` and a size of 8
     *
     * Objects can be generic, so they can have generic types
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#Objects)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    class ObjectType(val className: String, val genericTypes: List<TypeDescriptor> = emptyList()) : TypeDescriptor {

        /**
         * The descriptor of the [ObjectType]
         * @since 0.1.0
         * @version 0.1.0
         */
        override val descriptor: String
            get() =
                if (genericTypes.isEmpty()) {
                    "L$className;"
                } else {
                    "L$className@${genericTypes.joinToString("+") { it.descriptor }};"
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
     * An [ArrayType] represents an array type in shake
     * It has the descriptor `[<type>;` and a size of 8
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#Arrays)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    class ArrayType(val type: TypeDescriptor) : TypeDescriptor {

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

        // Shortcuts

        /**
         * A [ByteType] represents the primitive type `byte` in shake
         * It has the descriptor `B` and a size of 1
         *
         * This is a shortcut for [TypeDescriptor.ByteType]
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        val BYTE = ByteType

        /**
         * A [ShortType] represents the primitive type `short` in shake
         * It has the descriptor `S` and a size of 2
         *
         * This is a shortcut for [TypeDescriptor.ShortType]
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        val SHORT = ShortType

        /**
         * A [IntType] represents the primitive type `int` in shake
         * It has the descriptor `I` and a size of 4
         *
         * This is a shortcut for [TypeDescriptor.IntType]
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        val INT = IntType

        /**
         * A [LongType] represents the primitive type `long` in shake
         * It has the descriptor `J` and a size of 8
         *
         * This is a shortcut for [TypeDescriptor.LongType]
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        val LONG = LongType

        /**
         * A [UnsignedByteType] represents the primitive type `ubyte` in shake
         * It has the descriptor `b` and a size of 1
         *
         * This is a shortcut for [TypeDescriptor.UnsignedByteType]
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        val UNSIGNED_BYTE = UnsignedByteType

        /**
         * A [UnsignedShortType] represents the primitive type `ushort` in shake
         * It has the descriptor `s` and a size of 2
         *
         * This is a shortcut for [TypeDescriptor.UnsignedShortType]
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        val UNSIGNED_SHORT = UnsignedShortType

        /**
         * A [UnsignedIntType] represents the primitive type `uint` in shake
         * It has the descriptor `i` and a size of 4
         *
         * This is a shortcut for [TypeDescriptor.UnsignedIntType]
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        val UNSIGNED_INT = UnsignedIntType

        /**
         * A [UnsignedLongType] represents the primitive type `ulong` in shake
         * It has the descriptor `l` and a size of 8
         *
         * This is a shortcut for [TypeDescriptor.UnsignedLongType]
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        val UNSIGNED_LONG = UnsignedLongType

        /**
         * A [FloatType] represents the primitive type `float` in shake
         * It has the descriptor `F` and a size of 4
         *
         * This is a shortcut for [TypeDescriptor.FloatType]
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        val FLOAT = FloatType

        /**
         * A [DoubleType] represents the primitive type `double` in shake
         * It has the descriptor `D` and a size of 8
         *
         * This is a shortcut for [TypeDescriptor.DoubleType]
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        val DOUBLE = DoubleType

        /**
         * A [CharType] represents the primitive type `char` in shake
         * It has the descriptor `C` and a size of 2
         *
         * This is a shortcut for [TypeDescriptor.CharType]
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        val CHAR = CharType

        /**
         * A [BooleanType] represents the primitive type `boolean` in shake
         * It has the descriptor `Z` and a size of 1
         *
         * This is a shortcut for [TypeDescriptor.BooleanType]
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        val BOOLEAN = BooleanType

        /**
         * A [VoidType] represents the primitive type `void` in shake
         * It has the descriptor `V` and a size of 0
         *
         * This is a shortcut for [TypeDescriptor.VoidType]
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        val VOID = VoidType

        /**
         * A [NewType] is a symbolic type.
         * It is used to represent an anonymous pointer
         * to a new object created by a constructor call.
         *
         * This is a shortcut for [TypeDescriptor.NewType]
         */
        val NEW_TYPE = NewType

        /**
         * Parse a [TypeDescriptor] from an [InputStream]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun parse(name: InputStream): TypeDescriptor {
            when (val next = name.read().toChar()) {
                'B' -> return BYTE
                'S' -> return SHORT
                'I' -> return INT
                'J' -> return LONG
                'b' -> return UNSIGNED_BYTE
                's' -> return UNSIGNED_SHORT
                'i' -> return UNSIGNED_INT
                'l' -> return UNSIGNED_LONG
                'F' -> return FLOAT
                'D' -> return DOUBLE
                'C' -> return CHAR
                'Z' -> return BOOLEAN
                'V' -> return VOID
                'N' -> return NEW_TYPE
                '[' -> return ArrayType(parse(name))
                'L' -> {
                    val className = StringBuilder()
                    var next2 = name.read().toChar()
                    while (next2 != '@' && next2 != ';') {
                        className.append(next2)
                        if (name.available() == 0) throw Exception("Unexpected end of type descriptor: L$className...")
                        next2 = name.read().toChar()
                    }
                    if (next2 == ';') return ObjectType(className.toString(), emptyList())

                    val genericTypes = mutableListOf<TypeDescriptor>()
                    while (next2 != ';') {
                        genericTypes.add(parse(name))
                        if (name.available() == 0) throw Exception("Unexpected end of type descriptor: L$className@${genericTypes.joinToString("+") { it.descriptor }}...")
                        next2 = name.read().toChar()
                    }
                    return ObjectType(className.toString(), genericTypes)
                }

                else -> throw Exception("Unknown type: $next")
            }
        }

        /**
         * Parse a [TypeDescriptor] from a [String]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun parse(name: String): TypeDescriptor = parse(name.byteStream())
    }
}
