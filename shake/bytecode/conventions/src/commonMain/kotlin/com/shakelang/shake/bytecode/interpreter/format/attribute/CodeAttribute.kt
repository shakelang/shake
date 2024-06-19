package com.shakelang.shake.bytecode.interpreter.format.attribute

import com.shakelang.shake.bytecode.interpreter.format.pool.ConstantPool
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.util.io.streaming.input.bytes.DataInputStream
import com.shakelang.util.io.streaming.input.bytes.dataStream
import com.shakelang.util.io.streaming.output.bytes.ByteArrayOutputStream
import com.shakelang.util.io.streaming.output.bytes.DataOutputStream

/**
 * Attribute implementation for the code attribute
 * This attribute contains the code of a method
 *
 * [Specification](https://spec.shakelang.com/bytecode/map-format/#code-attribute)
 *
 * @property pool the [ConstantPool] of the attribute
 * @property nameConstant the name of the attribute
 * @property maxStack the maximum size of the stack
 * @property maxLocals the maximum size of the locals
 * @property code the code of the method
 * @property exceptionTable the exception table of the method
 * @property attributes the attributes of the method
 *
 * @constructor Creates a [CodeAttribute] with the given [pool], [nameConstant], [maxStack], [maxLocals], [code], [exceptionTable] and [attributes]
 *
 * @param pool the [ConstantPool] of the attribute
 * @param nameConstant the name of the attribute
 * @param maxStack the maximum size of the stack
 * @param maxLocals the maximum size of the locals
 * @param code the code of the method
 * @param exceptionTable the exception table of the method
 * @param attributes the attributes of the method
 *
 * @see Attribute
 * @see MutableCodeAttribute
 *
 * @since 0.1.0
 * @version 0.1.0
 */
open class CodeAttribute(

    /**
     * The [ConstantPool] of the attribute
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#constant-pool)
     *
     * @see ConstantPool
     * @see MutableConstantPool
     * @since 0.1.0
     * @version 0.1.0
     */
    override val pool: ConstantPool,

    /**
     * The name of the attribute
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#attribute-name-index)
     *
     * @see Attribute
     * @since 0.1.0
     * @version 0.1.0
     */
    override val nameConstant: Int,

    /**
     * The maximum size of the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#max-stack)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    open val maxStack: Int,

    /**
     * The maximum size of the locals
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#max-locals)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    open val maxLocals: Int,

    /**
     * The code of the method
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#code)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    open val code: ByteArray,

    /**
     * The exception table of the method
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#exception-table)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    open val exceptionTable: Array<ExceptionTableEntry>,

    /**
     * The attributes of the method
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#attributes)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    open val attributes: Array<Attribute>,

) : Attribute {

    /**
     * Check if this [CodeAttribute] is equal to [other]
     *
     * @param other the other [Any] to compare
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun equals(other: Any?): Boolean = compareHelper(other)

    /**
     * Get the hashCode of this [CodeAttribute]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun hashCode(): Int = hashCodeHelper()

    /**
     * The value of the attribute (Automatically generated from the [maxStack], [maxLocals], [code], [exceptionTable] and [attributes])
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#attribute-info)
     *
     * @see Attribute
     * @since 0.1.0
     * @version 0.1.0
     */
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

    /**
     * An entry of the exception table
     * This is used to handle exceptions
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#code-attribute-exception-table)
     *
     * @property startPc the start pc of the exception
     * @property endPc the end pc of the exception
     * @property handlerPc the handler pc of the exception
     * @property catchType the catch type of the exception
     *
     * @constructor Creates a [ExceptionTableEntry] with the given [startPc], [endPc], [handlerPc] and [catchType]
     *
     * @param startPc the start pc of the exception
     * @param endPc the end pc of the exception
     * @param handlerPc the handler pc of the exception
     * @param catchType the catch type of the exception
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    class ExceptionTableEntry(

        /**
         * The start pc of the exception
         *
         * [Specification](https://spec.shakelang.com/bytecode/map-format/#exception-start-pc)
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        val startPc: Int,

        /**
         * The end pc of the exception
         *
         * [Specification](https://spec.shakelang.com/bytecode/map-format/#exception-end-pc)
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        val endPc: Int,

        /**
         * The handler pc of the exception
         *
         * [Specification](https://spec.shakelang.com/bytecode/map-format/#exception-handler-pc)
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        val handlerPc: Int,

        /**
         * The catch type of the exception
         *
         * [Specification](https://spec.shakelang.com/bytecode/map-format/#exception-catch-type)
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        val catchType: Int,
    ) {

        /**
         * Check if this [ExceptionTableEntry] is equal to [other]
         *
         * @param other the other [Any] to compare
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is ExceptionTableEntry) return false

            if (startPc != other.startPc) return false
            if (endPc != other.endPc) return false
            if (handlerPc != other.handlerPc) return false
            if (catchType != other.catchType) return false

            return true
        }

        /**
         * Get the hashCode of this [ExceptionTableEntry]
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        override fun hashCode(): Int {
            var result = startPc
            result = 31 * result + endPc
            result = 31 * result + handlerPc
            result = 31 * result + catchType
            return result
        }

        /**
         * Dump the [ExceptionTableEntry] to the [stream]
         *
         * @param stream the [DataOutputStream] to dump the [ExceptionTableEntry] to
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun dump(stream: DataOutputStream) {
            stream.writeUnsignedShort(startPc.toUShort())
            stream.writeUnsignedShort(endPc.toUShort())
            stream.writeUnsignedShort(handlerPc.toUShort())
            stream.writeUnsignedShort(catchType.toUShort())
        }

        /**
         * Get the size of the [ExceptionTableEntry]
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun dump(): ByteArray {
            val stream = ByteArrayOutputStream()
            dump(DataOutputStream(stream))
            return stream.toByteArray()
        }
    }

    companion object {

        /**
         * Read a [CodeAttribute] from a [DataInputStream]
         *
         * @param pool the [ConstantPool] of the attribute
         * @param stream the [DataInputStream] to read from
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromStream(pool: ConstantPool, stream: DataInputStream): CodeAttribute {
            val name = stream.readInt()
            return fromStream(pool, stream, name)
        }

        /**
         * Read a [CodeAttribute] from a [DataInputStream]
         *
         * @param pool the [ConstantPool] of the attribute
         * @param stream the [DataInputStream] to read from
         * @param name the name of the attribute
         *
         * @since 0.1.0
         * @version 0.1.0
         */
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
                    stream.readUnsignedShort().toInt(),
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
                attributes,
            )
        }

        /**
         * Read a [CodeAttribute] from a [ByteArray]
         *
         * @param pool the [ConstantPool] of the attribute
         * @param array the [ByteArray] to read from
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromByteArray(pool: ConstantPool, array: ByteArray): CodeAttribute = fromStream(pool, array.dataStream())

        /**
         * Create a [CodeAttribute] from a [CodeAttribute] (copy)
         * @param attribute the [CodeAttribute] to copy
         * @return the new [CodeAttribute]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromCodeAttribute(attribute: CodeAttribute): CodeAttribute = CodeAttribute(
            attribute.pool,
            attribute.nameConstant,
            attribute.maxStack,
            attribute.maxLocals,
            attribute.code,
            attribute.exceptionTable,
            attribute.attributes,
        )
    }
}

/**
 * Mutable [CodeAttribute]
 *
 * @see CodeAttribute
 * @see MutableAttribute
 *
 * @constructor Create a new [MutableCodeAttribute]
 *
 * @param pool the [MutableConstantPool] of the attribute
 * @param nameConstant the name of the attribute
 * @param maxStack the maximum size of the stack
 * @param maxLocals the maximum size of the locals
 * @param code the code of the method
 * @param exceptionTable the exception table of the method
 * @param attributes the attributes of the method
 *
 * @since 0.1.0
 * @version 0.1.0
 */
class MutableCodeAttribute(
    pool: MutableConstantPool,

    /**
     * The name of the attribute
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#attribute-name-index)
     *
     * @see Attribute
     * @since 0.1.0
     * @version 0.1.0
     */
    override var nameConstant: Int,

    /**
     * The maximum size of the stack
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#max-stack)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    override var maxStack: Int,

    /**
     * The maximum size of the locals
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#max-locals)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    override var maxLocals: Int,

    /**
     * The code of the method
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#code)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    override var code: ByteArray,

    /**
     * The exception table of the method
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#exception-table)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    override var exceptionTable: Array<ExceptionTableEntry>,

    /**
     * The attributes of the method
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#attributes)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    override var attributes: Array<Attribute>,

) : CodeAttribute(
    pool,
    nameConstant,
    maxStack,
    maxLocals,
    ByteArray(0),
    exceptionTable,
    attributes,
),
    MutableAttribute {

    /**
     * The [MutableConstantPool] of the attribute
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#constant-pool)
     *
     * @see ConstantPool
     * @see MutableConstantPool
     * @since 0.1.0
     * @version 0.1.0
     */
    override val pool: MutableConstantPool
        get() = super.pool as MutableConstantPool

    companion object {

        /**
         * Create a [MutableCodeAttribute] from a [CodeAttribute] (copy)
         * @param attribute the [CodeAttribute] to copy
         * @return the new [MutableCodeAttribute]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromCodeAttribute(attribute: CodeAttribute): MutableCodeAttribute = MutableCodeAttribute(
            attribute.pool as MutableConstantPool,
            attribute.nameConstant,
            attribute.maxStack,
            attribute.maxLocals,
            attribute.code,
            attribute.exceptionTable,
            attribute.attributes,
        )

        /**
         * Read a [MutableCodeAttribute] from a [DataInputStream]
         *
         * @param pool the [MutableConstantPool] of the attribute
         * @param stream the [DataInputStream] to read from
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromStream(pool: MutableConstantPool, stream: DataInputStream): MutableCodeAttribute {
            val name = stream.readInt()
            return fromStream(pool, stream, name)
        }

        /**
         * Read a [MutableCodeAttribute] from a [DataInputStream]
         *
         * @param pool the [MutableConstantPool] of the attribute
         * @param stream the [DataInputStream] to read from
         * @param name the name of the attribute
         *
         * @since 0.1.0
         * @version 0.1.0
         */
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
                    stream.readUnsignedShort().toInt(),
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
                attributes,
            )
        }

        /**
         * Read a [MutableCodeAttribute] from a [ByteArray]
         *
         * @param pool the [MutableConstantPool] of the attribute
         * @param array the [ByteArray] to read from
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromByteArray(pool: MutableConstantPool, array: ByteArray): MutableCodeAttribute =
            fromStream(pool, array.dataStream())
    }
}
