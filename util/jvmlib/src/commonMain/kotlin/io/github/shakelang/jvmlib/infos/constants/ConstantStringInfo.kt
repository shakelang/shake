package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.io.streaming.input.DataInputStream
import io.github.shakelang.io.streaming.input.InputStream
import io.github.shakelang.io.streaming.input.dataStream
import io.github.shakelang.io.streaming.output.DataOutputStream

class ConstantStringInfo(private val si: UShort) : ConstantInfo(), ConstantUser {

    lateinit var string: ConstantUtf8Info
    val stringIndex: UShort get() = string.index

    override val uses: Array<ConstantInfo> get() = arrayOf(string)

    override val tag: Byte get() = ConstantStringInfo.tag
    override val tagName: String get() = name

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

        fun contentsFromStream(stream: InputStream)
                = contentsFromStream(stream.dataStream)

        fun fromStream(stream: DataInputStream) =
            if(stream.readByte() != ConstantMethodTypeInfo.tag)
                throw IllegalArgumentException("Invalid tag for ConstantStringInfo")
            else contentsFromStream(stream)

        fun fromStream(stream: InputStream) = fromStream(stream.dataStream)

        fun contentsFromBytes(bytes: ByteArray) = contentsFromStream(bytes.dataStream())

        fun fromBytes(bytes: ByteArray) = fromStream(bytes.dataStream())

        const val name = "constant_string_info"
        const val tag = ConstantTags.CONSTANT_STRING
    }

}