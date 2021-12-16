package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.streaming.input.DataInputStream
import io.github.shakelang.parseutils.streaming.output.DataOutputStream

class ConstantUtf8Info(val value: String) : ConstantInfo() {

    override val tag: Byte get() = ConstantUtf8Info.tag
    override val tagName: String get() = name

    override fun toJson() = super.toJson().with("value", value)

    override fun dump(out: DataOutputStream) {
        out.writeByte(tag)
        out.writeStringUTF8(value)
    }

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantUtf8Info {
            val length = stream.readUnsignedShort()
            val bytes = stream.readNBytes(length.toInt())
            val value = bytes.joinToString("") { it.toInt().toChar().toString() }
            return ConstantUtf8Info(value)
        }

        fun fromStream(stream: DataInputStream): ConstantUtf8Info {
            if(stream.readByte() != tag)
                throw IllegalArgumentException("Invalid tag for ConstantUtf8Info")
            return contentsFromStream(stream)
        }

        const val name = "constant_utf8_info"
        const val tag = ConstantTags.CONSTANT_UTF8
    }

}