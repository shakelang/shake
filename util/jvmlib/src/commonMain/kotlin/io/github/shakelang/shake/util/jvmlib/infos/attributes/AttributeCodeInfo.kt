package io.github.shakelang.shake.util.jvmlib.infos.attributes

import io.github.shakelang.shake.util.io.streaming.input.DataInputStream
import io.github.shakelang.shake.util.io.streaming.input.InputStream
import io.github.shakelang.shake.util.io.streaming.input.dataStream
import io.github.shakelang.shake.util.jvmlib.infos.constants.ConstantInfo
import io.github.shakelang.shake.util.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.shake.util.jvmlib.infos.constants.ConstantUtf8Info
import io.github.shakelang.shake.util.primitives.bytes.*

class AttributeCodeInfo (

    name: ConstantUtf8Info,
    val max_stack: UShort,
    val max_locals: UShort,
    val code: ByteArray,
    val exception_table: Array<ExceptionTableEntry>,
    val attributes: Array<AttributeInfo>

): AttributeInfo(name) {

    override val uses: Array<ConstantInfo> get() = arrayOf(name, *attributes.flatMap { it.uses.toList() }.toTypedArray())

    override val bytes: ByteArray
        get() {
            val attributes = this.attributes.map { it.toBytes().toList() }.flatten().toByteArray()
            val b = ByteArray(12 + code.size + exception_table.size * 8 + attributes.size)
            b.setUnsignedShort(0, max_stack) // 2
            b.setUnsignedShort(2, max_locals) // 2
            b.setInt(4, code.size) // 2
            b.setBytes(8, code) // code.size
            b.setUnsignedShort(8 + code.size, exception_table.size.toUShort()) // 2
            for (i in exception_table.indices) { // 8 * exception_table.size
                b.setBytes(10 + code.size + i * 8, exception_table[i].toBytes())
            }
            b.setUnsignedShort(10 + code.size + exception_table.size * 8, this.attributes.size.toUShort()) // 2
            b.setBytes(12 + code.size + exception_table.size * 8, attributes) // attributes.size
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
        fun contentsFromStream(pool: ConstantPool, stream: DataInputStream, name: ConstantUtf8Info): AttributeCodeInfo {
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
                AttributeInfo.fromStream(pool, stream)
            }
            return AttributeCodeInfo(
                name,
                max_stack,
                max_locals,
                code,
                exception_table,
                attributes
            )
        }
        fun fromStream(pool: ConstantPool, stream: DataInputStream): AttributeCodeInfo {
            val name = pool.getUtf8(stream.readUnsignedShort())
            return contentsFromStream(pool, stream, name)
        }
        fun contentsFromStream(pool: ConstantPool, stream: InputStream, name: ConstantUtf8Info): AttributeCodeInfo {
            return contentsFromStream(pool, stream.dataStream, name)
        }
        fun fromStream(pool: ConstantPool, stream: InputStream): AttributeCodeInfo {
            return fromStream(pool, stream.dataStream)
        }
        fun contentsFromBytes(pool: ConstantPool, bytes: ByteArray, name: ConstantUtf8Info): AttributeCodeInfo {
            return contentsFromStream(pool, bytes.dataStream(), name)
        }
        fun fromBytes(pool: ConstantPool, bytes: ByteArray): AttributeCodeInfo {
            return fromStream(pool, bytes.dataStream())
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