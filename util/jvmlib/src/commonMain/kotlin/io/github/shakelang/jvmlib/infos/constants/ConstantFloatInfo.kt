package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.streaming.input.DataInputStream
import io.github.shakelang.parseutils.streaming.output.DataOutputStream

class ConstantFloatInfo(val value: Float) : ConstantInfo() {

    override val tag: Byte get() = ConstantFloatInfo.tag
    override val tagName: String get() = name

    override fun toJson() = super.toJson().with("value", value)

    override fun dump(out: DataOutputStream) {
        out.writeByte(tag)
        out.writeFloat(value)
    }

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantFloatInfo {
            val value = stream.readFloat()
            return ConstantFloatInfo(value)
        }

        fun fromStream(stream: DataInputStream): ConstantFloatInfo {
            if(stream.readByte() != ConstantClassInfo.tag)
                throw IllegalArgumentException("Invalid tag for ConstantFloatInfo")
            return contentsFromStream(stream)
        }

        const val name = "constant_float_info"
        const val tag = ConstantTags.CONSTANT_FLOAT
    }

}