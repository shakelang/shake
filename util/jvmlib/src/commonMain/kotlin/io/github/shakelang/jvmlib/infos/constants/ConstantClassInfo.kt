package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.streaming.input.DataInputStream
import io.github.shakelang.parseutils.streaming.output.DataOutputStream

class ConstantClassInfo(private val vi: UShort) : ConstantInfo(), ConstantUser {

    lateinit var value: ConstantUtf8Info
    val valueIndex: UShort get() = constantPool.indexOf(this).toUShort()

    override val uses: Array<ConstantInfo> get() = arrayOf(value)

    override val tag: Byte get() = ConstantClassInfo.tag
    override val tagName: String get() = name
    override fun toJson() = super.toJson().with("value", valueIndex)

    override fun init(pool: ConstantPool) {
        super.init(pool)
        value = pool.getUtf8(vi)
    }

    override fun dumpTo(out: DataOutputStream) {
        out.writeByte(tag)
        out.writeUnsignedShort(valueIndex)
    }

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantClassInfo {
            val value = stream.readUnsignedShort()
            return ConstantClassInfo(value)
        }
        fun fromStream(stream: DataInputStream): ConstantClassInfo {
            if(stream.readByte() != tag) throw IllegalArgumentException("Invalid tag for ConstantClassInfo")
            return contentsFromStream(stream)
        }

        const val name = "ConstantClassInfo"
        const val tag = ConstantTags.CONSTANT_CLASS
    }

}