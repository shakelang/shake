package com.shakelang.util.jvmlib.infos.constants

import com.shakelang.util.io.streaming.input.bytes.DataInputStream
import com.shakelang.util.io.streaming.input.bytes.InputStream
import com.shakelang.util.io.streaming.input.bytes.dataStream
import com.shakelang.util.io.streaming.output.bytes.DataOutputStream

class ConstantClassInfo(private val vi: UShort) : ConstantInfo(), ConstantUser {

    val className: String get() = value.value

    lateinit var value: ConstantUtf8Info
        private set
    val valueIndex: UShort get() = value.index

    override val uses: Array<ConstantInfo> get() = arrayOf(value)

    override val tag: Byte get() = ConstantClassInfo.TAG
    override val tagName: String get() = NAME
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
            if (stream.readByte() != TAG) {
                throw IllegalArgumentException("Invalid tag for ConstantClassInfo")
            } else {
                contentsFromStream(stream)
            }

        fun fromStream(stream: InputStream) = fromStream(stream.dataStream)

        fun contentsFromBytes(bytes: ByteArray) = contentsFromStream(bytes.dataStream())

        fun fromBytes(bytes: ByteArray) = fromStream(bytes.dataStream())

        const val NAME = "constant_class_info"
        const val TAG = ConstantTags.CONSTANT_CLASS
    }
}
