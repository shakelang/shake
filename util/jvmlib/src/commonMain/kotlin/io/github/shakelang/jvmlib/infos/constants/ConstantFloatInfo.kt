package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.streaming.DataInputStream

class ConstantFloatInfo(val value: Float) : ConstantInfo() {

    override val tag: Byte get() = ConstantFloatInfo.tag
    override val tagName: String get() = name

    override fun toJson() = super.toJson().with("value", value)

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

        const val name = "ConstantFloatInfo"
        const val tag = ConstantTags.CONSTANT_FLOAT
    }

}