package com.shakelang.shake.bytecode.interpreter.format.pool

import com.shakelang.shake.util.io.streaming.input.DataInputStream
import com.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream

sealed class ConstantPoolEntry {

    abstract override fun toString(): String
    abstract fun dump(stream: DataOutputStream)

    abstract override fun equals(other: Any?): Boolean
    abstract override fun hashCode(): Int

    fun dump(): ByteArray {
        val byteStream = ByteArrayOutputStream()
        val stream = DataOutputStream(byteStream)
        dump(stream)
        return byteStream.toByteArray()
    }

    class Utf8Constant(val value: String) : ConstantPoolEntry() {
        override fun toString(): String {
            return "Utf8Constant(value='$value')"
        }

        override fun dump(stream: DataOutputStream) {
            stream.writeByte(1)
            stream.writeUTF8(value)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Utf8Constant) return false

            if (value != other.value) return false

            return true
        }

        override fun hashCode(): Int {
            return value.hashCode()
        }

        companion object {
            fun fromStream(stream: DataInputStream): Utf8Constant {
                return Utf8Constant(stream.readUTF())
            }
        }
    }

    class ByteConstant(val value: Byte) : ConstantPoolEntry() {
        override fun toString(): String {
            return "ByteConstant(value=$value)"
        }

        override fun dump(stream: DataOutputStream) {
            stream.writeByte(2)
            stream.writeByte(value)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is ByteConstant) return false

            if (value != other.value) return false

            return true
        }

        override fun hashCode(): Int {
            return value.hashCode()
        }

        companion object {
            fun fromStream(stream: DataInputStream): ByteConstant {
                return ByteConstant(stream.readByte())
            }
        }
    }

    class ShortConstant(val value: Short) : ConstantPoolEntry() {
        override fun toString(): String {
            return "ShortConstant(value=$value)"
        }

        override fun dump(stream: DataOutputStream) {
            stream.writeByte(3)
            stream.writeShort(value)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is ShortConstant) return false

            if (value != other.value) return false

            return true
        }

        override fun hashCode(): Int {
            return value.hashCode()
        }

        companion object {
            fun fromStream(stream: DataInputStream): ShortConstant {
                return ShortConstant(stream.readShort())
            }
        }
    }

    class IntConstant(val value: Int) : ConstantPoolEntry() {
        override fun toString(): String {
            return "IntConstant(value=$value)"
        }

        override fun dump(stream: DataOutputStream) {
            stream.writeByte(4)
            stream.writeInt(value)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is IntConstant) return false

            if (value != other.value) return false

            return true
        }

        override fun hashCode(): Int {
            return value.hashCode()
        }

        companion object {
            fun fromStream(stream: DataInputStream): IntConstant {
                return IntConstant(stream.readInt())
            }
        }
    }

    class LongConstant(val value: Long) : ConstantPoolEntry() {
        override fun toString(): String {
            return "LongConstant(value=$value)"
        }

        override fun dump(stream: DataOutputStream) {
            stream.writeByte(5)
            stream.writeLong(value)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is LongConstant) return false

            if (value != other.value) return false

            return true
        }

        override fun hashCode(): Int {
            return value.hashCode()
        }

        companion object {
            fun fromStream(stream: DataInputStream): LongConstant {
                return LongConstant(stream.readLong())
            }
        }
    }

    class FloatConstant(val value: Float) : ConstantPoolEntry() {
        override fun toString(): String {
            return "FloatConstant(value=$value)"
        }

        override fun dump(stream: DataOutputStream) {
            stream.writeByte(6)
            stream.writeFloat(value)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is FloatConstant) return false

            if (value != other.value) return false

            return true
        }

        override fun hashCode(): Int {
            return value.hashCode()
        }

        companion object {
            fun fromStream(stream: DataInputStream): FloatConstant {
                return FloatConstant(stream.readFloat())
            }
        }
    }

    class DoubleConstant(val value: Double) : ConstantPoolEntry() {
        override fun toString(): String {
            return "DoubleConstant(value=$value)"
        }

        override fun dump(stream: DataOutputStream) {
            stream.writeByte(7)
            stream.writeDouble(value)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is DoubleConstant) return false

            if (value != other.value) return false

            return true
        }

        override fun hashCode(): Int {
            return value.hashCode()
        }

        companion object {
            fun fromStream(stream: DataInputStream): DoubleConstant {
                return DoubleConstant(stream.readDouble())
            }
        }
    }

    class ClassConstant(val identifier: Int) : ConstantPoolEntry() {
        override fun toString(): String {
            return "ClassConstant(identifier=$identifier)"
        }

        override fun dump(stream: DataOutputStream) {
            stream.writeByte(8)
            stream.writeInt(identifier)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is ClassConstant) return false

            if (identifier != other.identifier) return false

            return true
        }

        override fun hashCode(): Int {
            return identifier
        }

        companion object {
            fun fromStream(stream: DataInputStream): ClassConstant {
                return ClassConstant(stream.readInt())
            }
        }
    }

    companion object {
        fun fromStream(stream: DataInputStream): ConstantPoolEntry {
            val type = stream.readByte()
            return when (type) {
                1.toByte() -> Utf8Constant.fromStream(stream)
                2.toByte() -> ByteConstant.fromStream(stream)
                3.toByte() -> ShortConstant.fromStream(stream)
                4.toByte() -> IntConstant.fromStream(stream)
                5.toByte() -> LongConstant.fromStream(stream)
                6.toByte() -> FloatConstant.fromStream(stream)
                7.toByte() -> DoubleConstant.fromStream(stream)
                8.toByte() -> ClassConstant.fromStream(stream)
                else -> throw Exception("Unknown constant pool entry type: $type")
            }
        }
    }
}
