package io.github.shakelang.shake.util.jvmlib.infos.constants

import io.github.shakelang.shake.util.io.streaming.input.DataInputStream
import io.github.shakelang.shake.util.io.streaming.input.InputStream
import io.github.shakelang.shake.util.io.streaming.input.dataStream
import io.github.shakelang.shake.util.io.streaming.output.DataOutputStream

class ConstantClassInfo(private val vi: UShort) : ConstantInfo(), ConstantUser {

    val className: String get() = value.value

    lateinit var value: ConstantUtf8Info
        private set
    val valueIndex: UShort get() = value.index

    override val uses: Array<ConstantInfo> get() = arrayOf(value)

    override val tag: Byte get() = ConstantClassInfo.tag
    override val tagName: String get() = name
    override fun toJson() = super.toJson().with("value", valueIndex.toInt())

    override fun init(pool: ConstantPool) {
        super.init(pool)
        value = pool.getUtf8(vi)
    }

    override fun dump(out: DataOutputStream) {
        out.writeByte(tag)
        out.writeUnsignedShort(valueIndex)
    }

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantClassInfo {
            val value = stream.readUnsignedShort()
            return ConstantClassInfo(value)
        }

        fun contentsFromStream(stream: InputStream) = contentsFromStream(stream.dataStream)

        fun fromStream(stream: DataInputStream) =
            if (stream.readByte() != tag)
                throw IllegalArgumentException("Invalid tag for ConstantClassInfo")
            else contentsFromStream(stream)

        fun fromStream(stream: InputStream) = fromStream(stream.dataStream)

        fun contentsFromBytes(bytes: ByteArray) = contentsFromStream(bytes.dataStream())

        fun fromBytes(bytes: ByteArray) = fromStream(bytes.dataStream())

        const val name = "constant_class_info"
        const val tag = ConstantTags.CONSTANT_CLASS
    }

}