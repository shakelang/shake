package com.shakelang.util.jvmlib.infos.constants

import com.shakelang.util.io.streaming.input.bytes.DataInputStream
import com.shakelang.util.io.streaming.input.bytes.InputStream
import com.shakelang.util.io.streaming.input.bytes.dataStream
import com.shakelang.util.io.streaming.output.bytes.DataOutputStream

class ConstantNameAndTypeInfo(private val ti: UShort, private val ni: UShort) : ConstantInfo(), ConstantUser {

    lateinit var type: ConstantUtf8Info
    lateinit var name: ConstantUtf8Info

    val typeIndex: UShort get() = type.index
    val nameIndex: UShort get() = name.index

    override val uses: Array<ConstantInfo> get() = arrayOf(type, name)

    override val tag: Byte get() = ConstantNameAndTypeInfo.TAG
    override val tagName: String get() = ConstantNameAndTypeInfo.NAME

    override fun toJson() = super.toJson()
        .with("name", typeIndex.toInt())
        .with("type", nameIndex.toInt())

    override fun init(pool: ConstantPool) {
        super.init(pool)
        type = pool[ti] as ConstantUtf8Info
        name = pool[ni] as ConstantUtf8Info
    }

    override fun dump(out: DataOutputStream) {
        out.writeByte(tag)
        out.writeUnsignedShort(typeIndex)
        out.writeUnsignedShort(nameIndex)
    }

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantNameAndTypeInfo {
            val value = stream.readUnsignedShort()
            val name = stream.readUnsignedShort()
            return ConstantNameAndTypeInfo(value, name)
        }

        fun contentsFromStream(stream: InputStream) = contentsFromStream(stream.dataStream)

        fun fromStream(stream: DataInputStream) =
            if (stream.readByte() != TAG) {
                throw IllegalArgumentException("Invalid tag for ConstantNameAndTypeInfo")
            } else {
                contentsFromStream(stream)
            }

        fun fromStream(stream: InputStream) = fromStream(stream.dataStream)

        fun contentsFromBytes(bytes: ByteArray) = contentsFromStream(bytes.dataStream())

        fun fromBytes(bytes: ByteArray) = fromStream(bytes.dataStream())

        const val NAME = "constant_name_and_type_info"
        const val TAG = ConstantTags.CONSTANT_NAME_AND_TYPE
    }
}
