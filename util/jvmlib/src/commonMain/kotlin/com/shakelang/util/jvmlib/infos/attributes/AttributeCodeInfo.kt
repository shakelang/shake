package com.shakelang.util.jvmlib.infos.attributes

import com.shakelang.util.io.streaming.input.bytes.DataInputStream
import com.shakelang.util.io.streaming.input.bytes.InputStream
import com.shakelang.util.io.streaming.input.bytes.dataStream
import com.shakelang.util.jvmlib.infos.constants.ConstantInfo
import com.shakelang.util.jvmlib.infos.constants.ConstantPool
import com.shakelang.util.jvmlib.infos.constants.ConstantUtf8Info
import com.shakelang.util.primitives.bytes.*

@Suppress("MemberVisibilityCanBePrivate")
class AttributeCodeInfo(

    name: ConstantUtf8Info,
    val maxStack: UShort,
    val maxLocals: UShort,
    val code: ByteArray,
    val exceptionTable: Array<ExceptionTableEntry>,
    val attributes: Array<AttributeInfo>,

) : AttributeInfo(name) {

    override val uses: Array<ConstantInfo>
        get() = arrayOf(
            name,
            *attributes.flatMap { it.uses.toList() }.toTypedArray(),
        )

    override val bytes: ByteArray
        get() {
            val attributes = this.attributes.map { it.toBytes().toList() }.flatten().toByteArray()
            val b = ByteArray(12 + code.size + exceptionTable.size * 8 + attributes.size)
            b.setUShort(0, maxStack) // 2
            b.setUShort(2, maxLocals) // 2
            b.setInt(4, code.size) // 2
            b.setBytes(8, code) // code.size
            b.setUShort(8 + code.size, exceptionTable.size.toUShort()) // 2
            for (i in exceptionTable.indices) { // 8 * exception_table.size
                b.setBytes(10 + code.size + i * 8, exceptionTable[i].toBytes())
            }
            b.setUShort(10 + code.size + exceptionTable.size * 8, this.attributes.size.toUShort()) // 2
            b.setBytes(12 + code.size + exceptionTable.size * 8, attributes) // attributes.size
            return b
        }

    override fun toJson() = mapOf(
        "tag" to ATTRIBUTE_CODE,
        "max_stack" to maxStack,
        "max_locals" to maxLocals,
        "code" to code.toHexString(),
        "name_index" to nameIndex,
        "exception_table" to exceptionTable.map { it.toJson() },
        "attributes" to attributes.map { it.toJson() },
    )

    companion object {
        const val ATTRIBUTE_CODE = "Code"
        fun contentsFromStream(pool: ConstantPool, stream: DataInputStream, name: ConstantUtf8Info): AttributeCodeInfo {
            val maxStack = stream.readUnsignedShort()
            val maxLocals = stream.readUnsignedShort()
            val codeLength = stream.readInt()
            val code = stream.readNBytes(codeLength)
            val exceptionTableLength = stream.readUnsignedShort()
            val exceptionTable = Array(exceptionTableLength.toInt()) {
                ExceptionTableEntry.fromStream(stream)
            }
            val attributesCount = stream.readUnsignedShort()
            val attributes = Array(attributesCount.toInt()) {
                AttributeInfo.fromStream(pool, stream)
            }
            return AttributeCodeInfo(
                name,
                maxStack,
                maxLocals,
                code,
                exceptionTable,
                attributes,
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
        val startPc: UShort,
        val endPc: UShort,
        val handlerPc: UShort,
        val catchType: UShort,
    ) {
        val bytes: ByteArray
            get() {
                val b = ByteArray(8)
                b.setUShort(0, startPc)
                b.setUShort(2, endPc)
                b.setUShort(4, handlerPc)
                b.setUShort(6, catchType)
                return b
            }

        fun toBytes() = this.bytes
        fun toJson() = mapOf(
            "start_pc" to startPc,
            "end_pc" to endPc,
            "handler_pc" to handlerPc,
            "catch_type" to catchType,
        )

        companion object {
            fun fromBytes(b: ByteArray): ExceptionTableEntry {
                return ExceptionTableEntry(
                    b.getUnsignedShort(0),
                    b.getUnsignedShort(2),
                    b.getUnsignedShort(4),
                    b.getUnsignedShort(6),
                )
            }

            fun fromStream(stream: DataInputStream): ExceptionTableEntry {
                return fromBytes(stream.readNBytes(8))
            }
        }
    }
}
