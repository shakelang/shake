package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.streaming.DataInputStream

class ConstantLongInfo(val value: Long) : ConstantInfo() {

    override val tag: Byte get() = ConstantLongInfo.tag
    override val type: String get() = name

    override fun toJson() = super.toJson().with("value", value)

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantLongInfo {
            val value = stream.readLong()
            return ConstantLongInfo(value)
        }

        fun fromStream(stream: DataInputStream): ConstantLongInfo {
            if(stream.readByte() != ConstantClassInfo.tag)
                throw IllegalArgumentException("Invalid tag for ConstantLongInfo")
            return contentsFromStream(stream)
        }

        const val name = "ConstantLongInfo"
        const val tag = ConstantTags.CONSTANT_LONG
    }

}