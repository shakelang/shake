package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.streaming.DataInputStream

class ConstantDoubleInfo(val value: Double) : ConstantInfo() {

    override val tag: Byte get() = ConstantDoubleInfo.tag
    override val type: String get() = name
    override fun toJson() = super.toJson().with("value", value)

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantDoubleInfo {
            val value = stream.readDouble()
            return ConstantDoubleInfo(value)
        }

        fun fromStream(stream: DataInputStream): ConstantDoubleInfo {
            if(stream.readByte() != ConstantClassInfo.tag)
                throw IllegalArgumentException("Invalid tag for ConstantDoubleInfo")
            return contentsFromStream(stream)
        }

        const val name = "ConstantDoubleInfo"
        const val tag = ConstantTags.CONSTANT_DOUBLE
    }

}