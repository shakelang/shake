package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.streaming.DataInputStream

class ConstantStringInfo(private val si: UShort) : ConstantInfo(), ConstantUser {

    lateinit var string: ConstantUtf8Info
    val stringIndex: UShort get() = constantPool.indexOf(string).toUShort()

    override val uses get() = arrayOf(stringIndex)

    override val tag: Byte get() = ConstantStringInfo.tag
    override val tagName: String get() = name

    override fun toJson() = super.toJson().with("value", stringIndex)
    fun getValue(pool: ConstantPool) = pool[stringIndex.toInt()] as ConstantUtf8Info

    override fun init(pool: ConstantPool) {
        super.init(pool)
        string = pool.getUtf8(si)
    }

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantStringInfo {
            val value = stream.readUnsignedShort()
            return ConstantStringInfo(value)
        }

        fun fromStream(stream: DataInputStream): ConstantStringInfo {
            if(stream.readByte() != ConstantClassInfo.tag)
                throw IllegalArgumentException("Invalid tag for ConstantStringInfo")
            return contentsFromStream(stream)
        }

        const val name = "ConstantStringInfo"
        const val tag = ConstantTags.CONSTANT_STRING
    }

}