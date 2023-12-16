package com.shakelang.shake.bytecode.interpreter.format.attribute

import com.shakelang.shake.bytecode.interpreter.format.pool.ConstantPool
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.util.io.streaming.input.DataInputStream
import com.shakelang.shake.util.io.streaming.input.dataStream
import com.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream

open class CodeAttribute(
    override val pool: ConstantPool,
    override val nameConstant: Int,
    open val maxStack: Int,
    open val maxLocals: Int,
    open val code: ByteArray,
    open val exceptionTable: Array<ExceptionTableEntry>,
    open val attributes: Array<Attribute>
) : Attribute {

    init {
        checkAttributeName()
    }

    fun checkAttributeName() {
        if (name != "Code") throw IllegalArgumentException("Name must be 'Code' (is $name). Pointer: $nameConstant")
    }

    override fun equals(other: Any?): Boolean = compareHelper(other)
    override fun hashCode(): Int = hashCodeHelper()

    override val value: ByteArray
        get() {
            val stream = ByteArrayOutputStream()
            val dataStream = DataOutputStream(stream)
            dataStream.writeUnsignedShort(maxStack.toUShort())
            dataStream.writeUnsignedShort(maxLocals.toUShort())
            dataStream.writeInt(code.size)
            dataStream.write(code)
            dataStream.writeUnsignedShort(exceptionTable.size.toUShort())
            for (entry in exceptionTable) entry.dump(dataStream)
            dataStream.writeUnsignedShort(attributes.size.toUShort())
            for (attribute in attributes) attribute.dump(dataStream)
            return stream.toByteArray()
        }

    class ExceptionTableEntry(
        val startPc: Int,
        val endPc: Int,
        val handlerPc: Int,
        val catchType: Int
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is ExceptionTableEntry) return false

            if (startPc != other.startPc) return false
            if (endPc != other.endPc) return false
            if (handlerPc != other.handlerPc) return false
            if (catchType != other.catchType) return false

            return true
        }

        override fun hashCode(): Int {
            var result = startPc
            result = 31 * result + endPc
            result = 31 * result + handlerPc
            result = 31 * result + catchType
            return result
        }

        fun dump(stream: DataOutputStream) {
            stream.writeUnsignedShort(startPc.toUShort())
            stream.writeUnsignedShort(endPc.toUShort())
            stream.writeUnsignedShort(handlerPc.toUShort())
            stream.writeUnsignedShort(catchType.toUShort())
        }

        fun dump(): ByteArray {
            val stream = ByteArrayOutputStream()
            dump(DataOutputStream(stream))
            return stream.toByteArray()
        }
    }

    companion object {

        fun fromStream(pool: ConstantPool, stream: DataInputStream): CodeAttribute {
            val name = stream.readInt()
            return fromStream(pool, stream, name)
        }

        fun fromStream(pool: ConstantPool, stream: DataInputStream, name: Int): CodeAttribute {
            val maxStack = stream.readUnsignedShort().toInt()
            val maxLocals = stream.readUnsignedShort().toInt()
            val codeSize = stream.readInt()
            val code = ByteArray(codeSize) {
                stream.readByte()
            }
            val exceptionTableSize = stream.readUnsignedShort().toInt()
            val exceptionTable = Array(exceptionTableSize) {
                ExceptionTableEntry(
                    stream.readUnsignedShort().toInt(),
                    stream.readUnsignedShort().toInt(),
                    stream.readUnsignedShort().toInt(),
                    stream.readUnsignedShort().toInt()
                )
            }
            val attributesSize = stream.readUnsignedShort().toInt()
            val attributes = Array(attributesSize) {
                Attribute.fromStream(pool, stream)
            }
            return CodeAttribute(
                pool,
                name,
                maxStack,
                maxLocals,
                code,
                exceptionTable,
                attributes
            )
        }

        fun fromByteArray(pool: ConstantPool, array: ByteArray): CodeAttribute = fromStream(pool, array.dataStream())

        fun fromCodeAttribute(attribute: CodeAttribute): CodeAttribute = CodeAttribute(
            attribute.pool,
            attribute.nameConstant,
            attribute.maxStack,
            attribute.maxLocals,
            attribute.code,
            attribute.exceptionTable,
            attribute.attributes
        )
    }
}

class MutableCodeAttribute(
    pool: MutableConstantPool,
    override var nameConstant: Int,
    override var maxStack: Int,
    override var maxLocals: Int,
    override var code: ByteArray,
    override var exceptionTable: Array<ExceptionTableEntry>,
    override var attributes: Array<Attribute>
) : CodeAttribute(
    pool,
    nameConstant,
    maxStack,
    maxLocals,
    ByteArray(0),
    arrayOf(),
    arrayOf()
),
    MutableAttribute {

    override val pool: MutableConstantPool
        get() = super.pool as MutableConstantPool

    companion object {

        fun fromCodeAttribute(attribute: CodeAttribute): MutableCodeAttribute {
            return MutableCodeAttribute(
                attribute.pool as MutableConstantPool,
                attribute.nameConstant,
                attribute.maxStack,
                attribute.maxLocals,
                attribute.code,
                attribute.exceptionTable,
                attribute.attributes
            )
        }

        fun fromStream(pool: MutableConstantPool, stream: DataInputStream): MutableCodeAttribute {
            val name = stream.readInt()
            return fromStream(pool, stream, name)
        }

        fun fromStream(pool: MutableConstantPool, stream: DataInputStream, name: Int): MutableCodeAttribute {
            val size = stream.readInt()
            val maxStack = stream.readUnsignedShort().toInt()
            val maxLocals = stream.readUnsignedShort().toInt()
            val codeSize = stream.readInt()
            val code = ByteArray(codeSize) {
                stream.readByte()
            }
            val exceptionTableSize = stream.readUnsignedShort().toInt()
            val exceptionTable = Array(exceptionTableSize) {
                ExceptionTableEntry(
                    stream.readUnsignedShort().toInt(),
                    stream.readUnsignedShort().toInt(),
                    stream.readUnsignedShort().toInt(),
                    stream.readUnsignedShort().toInt()
                )
            }
            val attributesSize = stream.readUnsignedShort().toInt()
            val attributes = Array(attributesSize) {
                Attribute.fromStream(pool, stream)
            }
            return MutableCodeAttribute(
                pool,
                name,
                maxStack,
                maxLocals,
                code,
                exceptionTable,
                attributes
            )
        }

        fun fromByteArray(pool: MutableConstantPool, array: ByteArray): MutableCodeAttribute =
            fromStream(pool, array.dataStream())
    }
}
