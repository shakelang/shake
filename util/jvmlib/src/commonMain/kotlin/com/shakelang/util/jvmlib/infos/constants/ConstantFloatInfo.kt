package com.shakelang.util.jvmlib.infos.constants

import com.shakelang.util.io.streaming.input.bytes.DataInputStream
import com.shakelang.util.io.streaming.input.bytes.InputStream
import com.shakelang.util.io.streaming.input.bytes.dataStream
import com.shakelang.util.io.streaming.output.bytes.DataOutputStream

class ConstantFloatInfo(val value: Float) : ConstantInfo() {

    override val tag: Byte get() = ConstantFloatInfo.TAG
    override val tagName: String get() = NAME

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

        fun contentsFromStream(stream: InputStream) = contentsFromStream(stream.dataStream)

        fun fromStream(stream: DataInputStream) =
            if (stream.readByte() != TAG) {
                throw IllegalArgumentException("Invalid tag for ConstantFloatInfo")
            } else {
                contentsFromStream(stream)
            }

        fun fromStream(stream: InputStream) = fromStream(stream.dataStream)

        fun contentsFromBytes(bytes: ByteArray) = contentsFromStream(bytes.dataStream())

        fun fromBytes(bytes: ByteArray) = fromStream(bytes.dataStream())

        const val NAME = "constant_float_info"
        const val TAG = ConstantTags.CONSTANT_FLOAT
    }
}
