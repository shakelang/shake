package io.github.shakelang.jvmlib.infos.attributes

import io.github.shakelang.jvmlib.infos.constants.ConstantInfo
import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.jvmlib.infos.constants.ConstantUtf8Info
import io.github.shakelang.parseutils.bytes.toBytes
import io.github.shakelang.parseutils.bytes.toUnsignedShort
import io.github.shakelang.parseutils.streaming.input.DataInputStream

class AttributeConstantValue (

    attributeName: ConstantUtf8Info,
    val constantValue: ConstantInfo

) : AttributeInfo(attributeName) {


    override val uses: Array<ConstantInfo> get() = arrayOf(name, constantValue)
    val constantValueIndex: UShort get() = constantValue.index

    override val bytes: ByteArray
        get() = constantValueIndex.toBytes()

    override fun toJson() = mapOf(
        "tag" to TAG,
        "attribute_name_index" to nameIndex,
        "constant_value_index" to constantValueIndex
    )

    companion object {
        const val TAG = "ConstantValue"
        fun fromBytes(pool: ConstantPool, tagIndex: ConstantUtf8Info, bytes: ByteArray): AttributeConstantValue {
            val constantValueIndex = bytes.toUnsignedShort()
            val constantValue = pool[constantValueIndex]
            return AttributeConstantValue(tagIndex, constantValue)
        }

        fun contentsFromStream(pool: ConstantPool, stream: DataInputStream, name: ConstantUtf8Info): AttributeConstantValue {
            val constantValueIndex = stream.readUnsignedShort()
            val constantValue = pool[constantValueIndex]
            return AttributeConstantValue(name, constantValue)
        }
    }
}