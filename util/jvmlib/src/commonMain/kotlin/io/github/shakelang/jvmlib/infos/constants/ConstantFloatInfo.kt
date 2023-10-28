package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.io.streaming.input.DataInputStream
import io.github.shakelang.io.streaming.input.InputStream
import io.github.shakelang.io.streaming.input.dataStream
import io.github.shakelang.io.streaming.output.DataOutputStream

class ConstantFloatInfo(val value: Float) : ConstantInfo() {

    override val tag: Byte get() = ConstantFloatInfo.tag
    override val tagName: String get() = name

    override fun toJson() = super.toJson().with("value", value.toDouble())

    override fun dump(out: DataOutputStream) {
        out.writeByte(tag)
        out.writeFloat(value)
    }

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantFloatInfo {
            val value = stream.readFloat()
            return ConstantFloatInfo(value)
        }

        fun contentsFromStream(stream: InputStream)
                = contentsFromStream(stream.dataStream)

        fun fromStream(stream: DataInputStream) =
            if(stream.readByte() != tag)
                throw IllegalArgumentException("Invalid tag for ConstantFloatInfo")
            else contentsFromStream(stream)

        fun fromStream(stream: InputStream) = fromStream(stream.dataStream)

        fun contentsFromBytes(bytes: ByteArray) = contentsFromStream(bytes.dataStream())

        fun fromBytes(bytes: ByteArray) = fromStream(bytes.dataStream())

        const val name = "constant_float_info"
        const val tag = ConstantTags.CONSTANT_FLOAT
    }

}