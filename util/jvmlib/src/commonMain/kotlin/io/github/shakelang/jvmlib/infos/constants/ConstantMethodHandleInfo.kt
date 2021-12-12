package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.streaming.input.DataInputStream
import io.github.shakelang.parseutils.streaming.output.DataOutputStream

class ConstantMethodHandleInfo(val referenceKind: Byte, private val ri: UShort) : ConstantInfo(), ConstantUser {

    lateinit var reference: ConstantInfo
    val referenceIndex: UShort get() = constantPool.indexOf(reference).toUShort()

    override val uses get() = arrayOf(reference)

    override val tag: Byte get() = ConstantMethodHandleInfo.tag
    override val tagName: String get() = ConstantMethodHandleInfo.name
    override fun toJson() = super.toJson().with("type", referenceKind).with("index", referenceIndex)

    override fun init(pool: ConstantPool) {
        super.init(pool)
        reference = pool[ri]
    }

    override fun dumpTo(out: DataOutputStream) {
        out.write(tag)
        out.write(referenceKind)
        out.write(referenceIndex)
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