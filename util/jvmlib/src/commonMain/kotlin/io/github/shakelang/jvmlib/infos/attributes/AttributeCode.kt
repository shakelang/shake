package io.github.shakelang.jvmlib.infos.attributes

import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.parseutils.bytes.*
import io.github.shakelang.parseutils.streaming.DataInputStream

class AttributeCode(

    nameIndex: UShort,
    val max_stack: UShort,
    val max_locals: UShort,
    val code: ByteArray,
    val exception_table: Array<ExceptionTableEntry>,
    val attributes: Array<AttributeInfo>

): AttributeInfo(nameIndex, ATTRIBUTE_CODE) {

    override val bytes: ByteArray
        get() {
            val b = ByteArray(2 + 2 + 4 + code.size + 2 + exception_table.size * 8 + 2)
            b.setUnsignedShort(0, max_stack)
            b.setUnsignedShort(2, max_locals)
            b.setInt(4, code.size)
            b.setBytes(8, code)
            b.setUnsignedShort(8 + code.size, exception_table.size.toUShort())
            for (i in exception_table.indices) {
                b.setBytes(8 + code.size + 2 + i * 8, exception_table[i].toBytes())
            }
            b.setUnsignedShort(8 + code.size + 2 + exception_table.size * 8, attributes.size.toUShort())
            for (i in attributes.indices) {
                b.setBytes(8 + code.size + 2 + exception_table.size * 8 + 2 + i * 2, attributes[i].toBytes())
            }
            return b
        }

    override fun toJson() = mapOf(
        "tag" to ATTRIBUTE_CODE,
        "max_stack" to max_stack,
        "max_locals" to max_locals,
        "code" to code.toHexString(),
        "name_index" to nameIndex,
        "exception_table" to exception_table.map { it.toJson() },
        "attributes" to attributes.map { it.toJson() }
    )

    companion object {
        const val ATTRIBUTE_CODE = "Code"
        fun contentsFromStream(pool: ConstantPool, stream: DataInputStream, nameIndex: UShort): AttributeCode {
            val max_stack = stream.readUnsignedShort()
            val max_locals = stream.readUnsignedShort()
            val code_length = stream.readInt()
            val code = stream.readNBytes(code_length)
            val exception_table_length = stream.readUnsignedShort()
            val exception_table = Array(exception_table_length.toInt()) {
                ExceptionTableEntry.fromStream(stream)
            }
            val attributes_count = stream.readUnsignedShort()
            val attributes = Array(attributes_count.toInt()) {
                fromStream(pool, stream)
            }
            return AttributeCode(
                nameIndex,
                max_stack,
                max_locals,
                code,
                exception_table,
                attributes
            )
        }
    }


    class ExceptionTableEntry(
        val start_pc: UShort,
        val end_pc: UShort,
        val handler_pc: UShort,
        val catch_type: UShort
    ) {
        val bytes: ByteArray
            get() {
                val b = ByteArray(8)
                b.setUnsignedShort(0, start_pc)
                b.setUnsignedShort(2, end_pc)
                b.setUnsignedShort(4, handler_pc)
                b.setUnsignedShort(6, catch_type)
                return b
            }

        fun toBytes() = this.bytes
        fun toJson() = mapOf(
            "start_pc" to start_pc,
            "end_pc" to end_pc,
            "handler_pc" to handler_pc,
            "catch_type" to catch_type
        )

        companion object {
            fun fromBytes(b: ByteArray): ExceptionTableEntry {
                return ExceptionTableEntry(
                    b.getUnsignedShort(0),
                    b.getUnsignedShort(2),
                    b.getUnsignedShort(4),
                    b.getUnsignedShort(6)
                )
            }
            fun fromStream(stream: DataInputStream): ExceptionTableEntry {
                return fromBytes(stream.readNBytes(8))
            }
        }
    }

}