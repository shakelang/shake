package com.shakelang.util.jvmlib.infos.constants

import com.shakelang.util.io.streaming.input.bytes.DataInputStream
import com.shakelang.util.io.streaming.input.bytes.InputStream
import com.shakelang.util.io.streaming.input.bytes.dataStream
import com.shakelang.util.io.streaming.output.bytes.DataOutputStream

class ConstantStringInfo(private val si: UShort) : ConstantInfo(), ConstantUser {

    lateinit var string: ConstantUtf8Info
    val stringIndex: UShort get() = string.index

    override val uses: Array<ConstantInfo> get() = arrayOf(string)

    override val tag: Byte get() = ConstantStringInfo.TAG
    override val tagName: String get() = NAME

    override fun toJson() = super.toJson()
        .with("value", stringIndex.toInt())

    override fun init(pool: ConstantPool) {
        super.init(pool)
        string = pool.getUtf8(si)
    }

    override fun dump(out: DataOutputStream) {
        out.writeByte(tag)
        out.writeUnsignedShort(stringIndex)
    }

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantStringInfo {
            val value = stream.readUnsignedShort()
            return ConstantStringInfo(value)
        }

        fun contentsFromStream(stream: InputStream) = contentsFromStream(stream.dataStream)

        fun fromStream(stream: DataInputStream) =
            if (stream.readByte() != ConstantMethodTypeInfo.TAG) {
                throw IllegalArgumentException("Invalid tag for ConstantStringInfo")
            } else {
                contentsFromStream(stream)
            }

        fun fromStream(stream: InputStream) = fromStream(stream.dataStream)

        fun contentsFromBytes(bytes: ByteArray) = contentsFromStream(bytes.dataStream())

        fun fromBytes(bytes: ByteArray) = fromStream(bytes.dataStream())

        const val NAME = "constant_string_info"
        const val TAG = ConstantTags.CONSTANT_STRING
    }
}
