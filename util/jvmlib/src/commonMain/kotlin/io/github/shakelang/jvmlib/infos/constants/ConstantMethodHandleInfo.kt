package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.streaming.DataInputStream

class ConstantMethodHandleInfo(val referenceKind: Byte, private val ri: UShort) : ConstantInfo(), ConstantUser {

    lateinit var reference: ConstantInfo
    val referenceIndex: UShort get() = constantPool.indexOf(reference).toUShort()

    override val uses get() = arrayOf(referenceIndex)

    override val tag: Byte get() = ConstantMethodHandleInfo.tag
    override val tagName: String get() = ConstantMethodHandleInfo.name
    override fun toJson() = super.toJson().with("type", referenceKind).with("index", referenceIndex)

    override fun init(pool: ConstantPool) {
        super.init(pool)
        reference = pool[ri]
    }


    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantMethodHandleInfo {
            val name = stream.readByte()
            val index = stream.readUnsignedShort()
            return ConstantMethodHandleInfo(name, index)
        }

        fun contentsFromStream(stream: DataInputStream, pool: ConstantPool): ConstantMethodHandleInfo {
            if(stream.readByte() != ConstantClassInfo.tag)
                throw IllegalArgumentException("Expected ConstantClassInfo")
            return contentsFromStream(stream)
        }

        const val name = "ConstantMethodHandleInfo"
        const val tag = ConstantTags.CONSTANT_METHOD_HANDLE
    }

}