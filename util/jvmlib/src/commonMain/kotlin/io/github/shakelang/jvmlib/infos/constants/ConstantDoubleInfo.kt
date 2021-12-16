package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.bytes.dataStream
import io.github.shakelang.parseutils.streaming.input.DataInputStream
import io.github.shakelang.parseutils.streaming.input.InputStream
import io.github.shakelang.parseutils.streaming.input.dataStream
import io.github.shakelang.parseutils.streaming.output.DataOutputStream

class ConstantDoubleInfo(val value: Double) : ConstantInfo() {

    override val tag: Byte get() = ConstantDoubleInfo.tag
    override val tagName: String get() = name
    override fun toJson() = super.toJson().with("value", value)

    override fun dump(out: DataOutputStream) {
        out.writeByte(tag)
        out.writeDouble(value)
    }

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantDoubleInfo {
            val value = stream.readDouble()
            return ConstantDoubleInfo(value)
        }

        fun contentsFromStream(stream: InputStream)
                = ConstantFieldrefInfo.contentsFromStream(stream.dataStream)

        fun fromStream(stream: DataInputStream) =
            if(stream.readByte() != ConstantDoubleInfo.tag)
                throw IllegalArgumentException("Invalid tag for ConstantDoubleInfo")
            else ConstantFieldrefInfo.contentsFromStream(stream)

        fun fromStream(stream: InputStream) = fromStream(stream.dataStream)

        fun contentsFromBytes(bytes: ByteArray) = ConstantFieldrefInfo.contentsFromStream(bytes.dataStream())

        fun fromBytes(bytes: ByteArray) = fromStream(bytes.dataStream())

        const val name = "constant_double_info"
        const val tag = ConstantTags.CONSTANT_DOUBLE
    }

}