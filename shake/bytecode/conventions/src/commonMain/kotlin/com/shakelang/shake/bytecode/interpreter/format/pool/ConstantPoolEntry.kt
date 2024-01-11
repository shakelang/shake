package com.shakelang.shake.bytecode.interpreter.format.pool

import com.shakelang.util.io.streaming.input.DataInputStream
import com.shakelang.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.util.io.streaming.output.DataOutputStream

/**
 * A ConstantPoolEntry is an entry in the constant pool of a class file
 *
 * [Specification](https://spec.shakelang.com/bytecode/storage-format#constant-pool)
 *
 * @see ConstantPool
 * @since 0.1.0
 * @version 0.1.0
 */
sealed class ConstantPoolEntry {

    /**
     * Every ConstantPoolEntry should implement toString() to get
     * a string representation of the entry
     *
     * @return a string representation of the entry
     * @since 0.1.0
     * @version 0.1.0
     */
    abstract override fun toString(): String

    /**
     * Dump the entry to the given [stream]
     * @param stream the stream to dump the entry to
     * @since 0.1.0
     * @version 0.1.0
     */
    abstract fun dump(stream: DataOutputStream)

    /**
     * Every ConstantPoolEntry should implement equals() to check if
     * the entry is equal to the given [other] object
     *
     * @param other the object to check if it is equal to the entry
     * @return true if the entry is equal to the given [other] object
     * @since 0.1.0
     * @version 0.1.0
     */
    abstract override fun equals(other: Any?): Boolean

    /**
     * Every ConstantPoolEntry should implement hashCode() to get
     * the hash code of the entry
     *
     * @return the hash code of the entry
     * @since 0.1.0
     * @version 0.1.0
     */
    abstract override fun hashCode(): Int

    /**
     * Dump the entry into a byte array
     *
     * Implemented using [dump] and [ByteArrayOutputStream]
     *
     * @return a byte array representing the entry
     * @since 0.1.0
     * @version 0.1.0
     */
    fun dump(): ByteArray {
        val byteStream = ByteArrayOutputStream()
        val stream = DataOutputStream(byteStream)
        dump(stream)
        return byteStream.toByteArray()
    }

    /**
     * A Utf8Constant is a constant pool entry that contains a string
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#utf8-constant)
     *
     * @param value the string value of the entry
     * @see ConstantPoolEntry
     * @since 0.1.0
     * @version 0.1.0
     *
     * @constructor creates a new Utf8Constant
     * @param value the string value of the entry
     */
    class Utf8Constant(

        /**
         * The string value of the entry
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        val value: String,

    ) : ConstantPoolEntry() {

        /**
         * Get a string representation of the utf8 constant
         * @return a string representation of the utf8 constant
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString(): String {
            return "Utf8Constant(value='$value')"
        }

        /**
         * Dump the utf8 constant to the given [stream]
         * @param stream the stream to dump the utf8 constant to
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun dump(stream: DataOutputStream) {
            stream.writeByte(0x01)
            stream.writeUTF8(value)
        }

        /**
         * Check if the utf8 constant is equal to the given [other] object
         * @param other the object to check if it is equal to the utf8 constant
         * @return true if the utf8 constant is equal to the given [other] object
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Utf8Constant) return false

            if (value != other.value) return false

            return true
        }

        /**
         * Get the hash code of the utf8 constant
         * @return the hash code of the utf8 constant
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun hashCode(): Int {
            return value.hashCode()
        }

        companion object {

            /**
             * Create a new Utf8Constant from the given [stream] without
             * reading the identifier byte
             * @param stream the stream to create the utf8 constant from
             * @return the created utf8 constant
             * @since 0.1.0
             * @version 0.1.0
             */
            fun fromStreamIgnoreIdentifier(stream: DataInputStream): Utf8Constant {
                return Utf8Constant(stream.readUTF())
            }

            /**
             * Create a new Utf8Constant from the given [stream]
             * @param stream the stream to create the utf8 constant from
             * @return the created utf8 constant
             * @since 0.1.0
             * @version 0.1.0
             */
            fun fromStream(stream: DataInputStream): Utf8Constant {
                val identifier = stream.readByte()
                if (identifier != 0x01.toByte()) throw Exception("Invalid identifier for Utf8Constant: $identifier")
                return Utf8Constant(stream.readUTF())
            }
        }
    }

    /**
     * A ByteConstant is a constant pool entry that contains a byte
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#byte-constant)
     *
     * @param value the byte value of the entry
     * @see ConstantPoolEntry
     * @since 0.1.0
     * @version 0.1.0
     *
     * @constructor creates a new ByteConstant
     * @param value the byte value of the entry
     */
    class ByteConstant(

        /**
         * The byte value of the entry
         * @since 0.1.0
         * @version 0.1.0
         */
        val value: Byte,

    ) : ConstantPoolEntry() {

        /**
         * Get a string representation of the byte constant
         * @return a string representation of the byte constant
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString(): String {
            return "ByteConstant(value=$value)"
        }

        /**
         * Dump the byte constant to the given [stream]
         * @param stream the stream to dump the byte constant to
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun dump(stream: DataOutputStream) {
            stream.writeByte(0x02)
            stream.writeByte(value)
        }

        /**
         * Check if the byte constant is equal to the given [other] object
         * @param other the object to check if it is equal to the byte constant
         * @return true if the byte constant is equal to the given [other] object
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is ByteConstant) return false

            if (value != other.value) return false

            return true
        }

        /**
         * Get the hash code of the byte constant
         * @return the hash code of the byte constant
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun hashCode(): Int {
            return value.hashCode()
        }

        companion object {

            /**
             * Create a new ByteConstant from the given [stream] without
             * reading the identifier byte
             * @param stream the stream to create the byte constant from
             * @return the created byte constant
             * @since 0.1.0
             * @version 0.1.0
             */
            fun fromStreamIgnoreIdentifier(stream: DataInputStream): ByteConstant {
                return ByteConstant(stream.readByte())
            }

            /**
             * Create a new ByteConstant from the given [stream]
             * @param stream the stream to create the byte constant from
             * @return the created byte constant
             * @since 0.1.0
             * @version 0.1.0
             */
            fun fromStream(stream: DataInputStream): ByteConstant {
                val identifier = stream.readByte()
                if (identifier != 0x02.toByte()) throw Exception("Invalid identifier for ByteConstant: $identifier")
                return ByteConstant(stream.readByte())
            }
        }
    }

    /**
     * A ShortConstant is a constant pool entry that contains a short
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#short-constant)
     *
     * @param value the short value of the entry
     * @see ConstantPoolEntry
     * @since 0.1.0
     * @version 0.1.0
     *
     * @constructor creates a new ShortConstant
     * @param value the short value of the entry
     */
    class ShortConstant(

        /**
         * The short value of the entry
         * @since 0.1.0
         * @version 0.1.0
         */
        val value: Short,
    ) : ConstantPoolEntry() {

        /**
         * Get a string representation of the short constant
         * @return a string representation of the short constant
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString(): String {
            return "ShortConstant(value=$value)"
        }

        /**
         * Dump the short constant to the given [stream]
         * @param stream the stream to dump the short constant to
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun dump(stream: DataOutputStream) {
            stream.writeByte(0x03)
            stream.writeShort(value)
        }

        /**
         * Check if the short constant is equal to the given [other] object
         * @param other the object to check if it is equal to the short constant
         * @return true if the short constant is equal to the given [other] object
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is ShortConstant) return false

            if (value != other.value) return false

            return true
        }

        /**
         * Get the hash code of the short constant
         * @return the hash code of the short constant
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun hashCode(): Int {
            return value.hashCode()
        }

        companion object {

            /**
             * Create a new ShortConstant from the given [stream] without
             * reading the identifier byte
             * @param stream the stream to create the short constant from
             * @return the created short constant
             * @since 0.1.0
             * @version 0.1.0
             */
            fun fromStreamIgnoreIdentifier(stream: DataInputStream): ShortConstant {
                return ShortConstant(stream.readShort())
            }

            /**
             * Create a new ShortConstant from the given [stream]
             * @param stream the stream to create the short constant from
             * @return the created short constant
             * @since 0.1.0
             * @version 0.1.0
             */
            fun fromStream(stream: DataInputStream): ShortConstant {
                val identifier = stream.readByte()
                if (identifier != 0x03.toByte()) throw Exception("Invalid identifier for ShortConstant: $identifier")
                return ShortConstant(stream.readShort())
            }
        }
    }

    /**
     * An IntConstant is a constant pool entry that contains an int
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#int-constant)
     *
     * @param value the int value of the entry
     * @see ConstantPoolEntry
     * @since 0.1.0
     * @version 0.1.0
     *
     * @constructor creates a new IntConstant
     * @param value the int value of the entry
     */
    class IntConstant(

        /**
         * The int value of the entry
         * @since 0.1.0
         * @version 0.1.0
         */
        val value: Int,

    ) : ConstantPoolEntry() {

        /**
         * Get a string representation of the int constant
         * @return a string representation of the int constant
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString(): String {
            return "IntConstant(value=$value)"
        }

        /**
         * Dump the int constant to the given [stream]
         * @param stream the stream to dump the int constant to
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun dump(stream: DataOutputStream) {
            stream.writeByte(0x04)
            stream.writeInt(value)
        }

        /**
         * Check if the int constant is equal to the given [other] object
         * @param other the object to check if it is equal to the int constant
         * @return true if the int constant is equal to the given [other] object
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is IntConstant) return false

            if (value != other.value) return false

            return true
        }

        /**
         * Get the hash code of the int constant
         * @return the hash code of the int constant
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun hashCode(): Int {
            return value.hashCode()
        }

        companion object {

            /**
             * Create a new IntConstant from the given [stream] without
             * reading the identifier byte
             * @param stream the stream to create the int constant from
             * @return the created int constant
             * @since 0.1.0
             * @version 0.1.0
             */
            fun fromStreamIgnoreIdentifier(stream: DataInputStream): IntConstant {
                return IntConstant(stream.readInt())
            }

            /**
             * Create a new IntConstant from the given [stream]
             * @param stream the stream to create the int constant from
             * @return the created int constant
             * @since 0.1.0
             * @version 0.1.0
             */
            fun fromStream(stream: DataInputStream): IntConstant {
                val identifier = stream.readByte()
                if (identifier != 0x04.toByte()) throw Exception("Invalid identifier for IntConstant: $identifier")
                return IntConstant(stream.readInt())
            }
        }
    }

    /**
     * A LongConstant is a constant pool entry that contains a long
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#long-constant)
     *
     * @param value the long value of the entry
     * @see ConstantPoolEntry
     * @since 0.1.0
     * @version 0.1.0
     *
     * @constructor creates a new LongConstant
     * @param value the long value of the entry
     */
    class LongConstant(

        /**
         * The long value of the entry
         * @since 0.1.0
         * @version 0.1.0
         */
        val value: Long,

    ) : ConstantPoolEntry() {

        /**
         * Get a string representation of the long constant
         * @return a string representation of the long constant
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString(): String {
            return "LongConstant(value=$value)"
        }

        /**
         * Dump the long constant to the given [stream]
         * @param stream the stream to dump the long constant to
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun dump(stream: DataOutputStream) {
            stream.writeByte(0x05)
            stream.writeLong(value)
        }

        /**
         * Check if the long constant is equal to the given [other] object
         * @param other the object to check if it is equal to the long constant
         * @return true if the long constant is equal to the given [other] object
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is LongConstant) return false

            if (value != other.value) return false

            return true
        }

        /**
         * Get the hash code of the long constant
         * @return the hash code of the long constant
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun hashCode(): Int {
            return value.hashCode()
        }

        companion object {

            /**
             * Create a new LongConstant from the given [stream] without
             * reading the identifier byte
             * @param stream the stream to create the long constant from
             * @return the created long constant
             * @since 0.1.0
             * @version 0.1.0
             */
            fun fromStreamIgnoreIdentifier(stream: DataInputStream): LongConstant {
                return LongConstant(stream.readLong())
            }

            /**
             * Create a new LongConstant from the given [stream]
             * @param stream the stream to create the long constant from
             * @return the created long constant
             * @since 0.1.0
             * @version 0.1.0
             */
            fun fromStream(stream: DataInputStream): LongConstant {
                val identifier = stream.readByte()
                if (identifier != 0x05.toByte()) throw Exception("Invalid identifier for LongConstant: $identifier")
                return LongConstant(stream.readLong())
            }
        }
    }

    /**
     * A FloatConstant is a constant pool entry that contains a float
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#float-constant)
     *
     * @param value the float value of the entry
     * @see ConstantPoolEntry
     * @since 0.1.0
     * @version 0.1.0
     *
     * @constructor creates a new FloatConstant
     * @param value the float value of the entry
     */
    class FloatConstant(

        /**
         * The float value of the entry
         * @since 0.1.0
         * @version 0.1.0
         */
        val value: Float,

    ) : ConstantPoolEntry() {

        /**
         * Get a string representation of the float constant
         * @return a string representation of the float constant
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString(): String {
            return "FloatConstant(value=$value)"
        }

        /**
         * Dump the float constant to the given [stream]
         * @param stream the stream to dump the float constant to
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun dump(stream: DataOutputStream) {
            stream.writeByte(0x06)
            stream.writeFloat(value)
        }

        /**
         * Check if the float constant is equal to the given [other] object
         * @param other the object to check if it is equal to the float constant
         * @return true if the float constant is equal to the given [other] object
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is FloatConstant) return false

            if (value != other.value) return false

            return true
        }

        /**
         * Get the hash code of the float constant
         * @return the hash code of the float constant
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun hashCode(): Int {
            return value.hashCode()
        }

        companion object {

            /**
             * Create a new FloatConstant from the given [stream] without
             * reading the identifier byte
             * @param stream the stream to create the float constant from
             * @return the created float constant
             * @since 0.1.0
             * @version 0.1.0
             */
            fun fromStreamIgnoreIdentifier(stream: DataInputStream): FloatConstant {
                return FloatConstant(stream.readFloat())
            }

            /**
             * Create a new FloatConstant from the given [stream]
             * @param stream the stream to create the float constant from
             * @return the created float constant
             * @since 0.1.0
             * @version 0.1.0
             */
            fun fromStream(stream: DataInputStream): FloatConstant {
                val identifier = stream.readByte()
                if (identifier != 0x06.toByte()) throw Exception("Invalid identifier for FloatConstant: $identifier")
                return FloatConstant(stream.readFloat())
            }
        }
    }

    /**
     * A DoubleConstant is a constant pool entry that contains a double
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#double-constant)
     *
     * @param value the double value of the entry
     * @see ConstantPoolEntry
     * @since 0.1.0
     * @version 0.1.0
     *
     * @constructor creates a new DoubleConstant
     * @param value the double value of the entry
     */
    class DoubleConstant(

        /**
         * The double value of the entry
         * @since 0.1.0
         * @version 0.1.0
         */
        val value: Double,

    ) : ConstantPoolEntry() {

        /**
         * Get a string representation of the double constant
         * @return a string representation of the double constant
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString(): String {
            return "DoubleConstant(value=$value)"
        }

        /**
         * Dump the double constant to the given [stream]
         * @param stream the stream to dump the double constant to
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun dump(stream: DataOutputStream) {
            stream.writeByte(0x07)
            stream.writeDouble(value)
        }

        /**
         * Check if the double constant is equal to the given [other] object
         * @param other the object to check if it is equal to the double constant
         * @return true if the double constant is equal to the given [other] object
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is DoubleConstant) return false

            if (value != other.value) return false

            return true
        }

        /**
         * Get the hash code of the double constant
         * @return the hash code of the double constant
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun hashCode(): Int {
            return value.hashCode()
        }

        companion object {

            /**
             * Create a new DoubleConstant from the given [stream] without
             * reading the identifier byte
             * @param stream the stream to create the double constant from
             * @return the created double constant
             * @since 0.1.0
             * @version 0.1.0
             */
            fun fromStreamIgnoreIdentifier(stream: DataInputStream): DoubleConstant {
                return DoubleConstant(stream.readDouble())
            }

            /**
             * Create a new DoubleConstant from the given [stream]
             * @param stream the stream to create the double constant from
             * @return the created double constant
             * @since 0.1.0
             * @version 0.1.0
             */
            fun fromStream(stream: DataInputStream): DoubleConstant {
                val identifier = stream.readByte()
                if (identifier != 0x07.toByte()) throw Exception("Invalid identifier for DoubleConstant: $identifier")
                return DoubleConstant(stream.readDouble())
            }
        }
    }

    /**
     * A ClassConstant is a constant pool entry that contains a class identifier
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#class-constant)
     *
     * @param identifier the class identifier of the entry
     * @see ConstantPoolEntry
     * @since 0.1.0
     * @version 0.1.0
     *
     * @constructor creates a new ClassConstant
     * @param identifier the class identifier of the entry
     */
    class ClassConstant(

        /**
         * The class identifier of the entry
         * @since 0.1.0
         * @version 0.1.0
         */
        val identifier: Int,

    ) : ConstantPoolEntry() {

        /**
         * Get a string representation of the class constant
         * @return a string representation of the class constant
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString(): String {
            return "ClassConstant(identifier=$identifier)"
        }

        /**
         * Dump the class constant to the given [stream]
         * @param stream the stream to dump the class constant to
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun dump(stream: DataOutputStream) {
            stream.writeByte(0x08)
            stream.writeInt(identifier)
        }

        /**
         * Check if the class constant is equal to the given [other] object
         * @param other the object to check if it is equal to the class constant
         * @return true if the class constant is equal to the given [other] object
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is ClassConstant) return false

            if (identifier != other.identifier) return false

            return true
        }

        /**
         * Get the hash code of the class constant
         * @return the hash code of the class constant
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun hashCode(): Int {
            return identifier
        }

        companion object {

            /**
             * Create a new ClassConstant from the given [stream] without
             * reading the identifier byte
             * @param stream the stream to create the class constant from
             * @return the created class constant
             * @since 0.1.0
             * @version 0.1.0
             */
            fun fromStreamIgnoreIdentifier(stream: DataInputStream): ClassConstant {
                return ClassConstant(stream.readInt())
            }

            /**
             * Create a new ClassConstant from the given [stream]
             * @param stream the stream to create the class constant from
             * @return the created class constant
             * @since 0.1.0
             * @version 0.1.0
             */
            fun fromStream(stream: DataInputStream): ClassConstant {
                val identifier = stream.readByte()
                if (identifier != 0x08.toByte()) throw Exception("Invalid identifier for ClassConstant: $identifier")
                return ClassConstant(stream.readInt())
            }
        }
    }

    /**
     * A StringConstant is a constant pool entry that contains a string identifier
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format#string-constant)
     *
     * @param identifier the string identifier of the entry
     * @see ConstantPoolEntry
     * @since 0.1.0
     * @version 0.1.0
     *
     * @constructor creates a new StringConstant
     * @param identifier the string identifier of the entry
     */
    class StringConstant(

        /**
         * The string identifier of the entry
         * @since 0.1.0
         * @version 0.1.0
         */
        val identifier: Int,

    ) : ConstantPoolEntry() {

        /**
         * Get a string representation of the string constant
         * @return a string representation of the string constant
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun toString(): String {
            return "StringConstant(identifier=$identifier)"
        }

        /**
         * Dump the string constant to the given [stream]
         * @param stream the stream to dump the string constant to
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun dump(stream: DataOutputStream) {
            stream.writeByte(0x09)
            stream.writeInt(identifier)
        }

        /**
         * Check if the string constant is equal to the given [other] object
         * @param other the object to check if it is equal to the string constant
         * @return true if the string constant is equal to the given [other] object
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is StringConstant) return false

            if (identifier != other.identifier) return false

            return true
        }

        /**
         * Get the hash code of the string constant
         * @return the hash code of the string constant
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun hashCode(): Int {
            return identifier
        }

        companion object {

            /**
             * Create a new StringConstant from the given [stream] without
             * reading the identifier byte
             * @param stream the stream to create the string constant from
             * @return the created string constant
             * @since 0.1.0
             * @version 0.1.0
             */
            fun fromStreamIgnoreIdentifier(stream: DataInputStream): StringConstant {
                return StringConstant(stream.readInt())
            }

            /**
             * Create a new StringConstant from the given [stream]
             * @param stream the stream to create the string constant from
             * @return the created string constant
             * @since 0.1.0
             * @version 0.1.0
             */
            fun fromStream(stream: DataInputStream): StringConstant {
                val identifier = stream.readByte()
                if (identifier != 0x09.toByte()) throw Exception("Invalid identifier for StringConstant: $identifier")
                return StringConstant(stream.readInt())
            }
        }
    }

    companion object {

        /**
         * Create a new ConstantPoolEntry from the given [stream]
         * @param stream the stream to create the constant pool entry from
         * @return the created constant pool entry
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromStream(stream: DataInputStream): ConstantPoolEntry {
            val type = stream.readByte()
            return when (type) {
                0x01.toByte() -> Utf8Constant.fromStreamIgnoreIdentifier(stream)
                0x02.toByte() -> ByteConstant.fromStreamIgnoreIdentifier(stream)
                0x03.toByte() -> ShortConstant.fromStreamIgnoreIdentifier(stream)
                0x04.toByte() -> IntConstant.fromStreamIgnoreIdentifier(stream)
                0x05.toByte() -> LongConstant.fromStreamIgnoreIdentifier(stream)
                0x06.toByte() -> FloatConstant.fromStreamIgnoreIdentifier(stream)
                0x07.toByte() -> DoubleConstant.fromStreamIgnoreIdentifier(stream)
                0x08.toByte() -> ClassConstant.fromStreamIgnoreIdentifier(stream)
                0x09.toByte() -> StringConstant.fromStreamIgnoreIdentifier(stream)
                else -> throw Exception("Unknown constant pool entry type: $type")
            }
        }
    }
}
