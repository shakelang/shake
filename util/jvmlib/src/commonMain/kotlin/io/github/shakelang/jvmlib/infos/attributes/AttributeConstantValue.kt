package io.github.shakelang.jvmlib.infos.attributes

import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.parseutils.bytes.toBytes
import io.github.shakelang.parseutils.bytes.toUnsignedShort
import io.github.shakelang.parseutils.streaming.DataInputStream

class AttributeConstantValue (

    attributeNameIndex: UShort,
    val constantValueIndex: UShort

) : AttributeInfo(attributeNameIndex, TAG) {

    override val bytes: ByteArray
        get() = constantValueIndex.toBytes()

    override fun toJson() = mapOf(
        "tag" to TAG,
        "attribute_name_index" to nameIndex,
        "constant_value_index" to constantValueIndex
    )

    companion object {
        const val TAG = "ConstantValue"
        fun fromBytes(tagIndex: UShort, bytes: ByteArray): AttributeConstantValue {
            val constantValueIndex = bytes.toUnsignedShort()
            return AttributeConstantValue(tagIndex, constantValueIndex)
        }

        fun contentsFromStream(pool: ConstantPool, stream: DataInputStream, nameIndex: UShort): AttributeConstantValue {
            val constantValueIndex = stream.readUnsignedShort()
            return AttributeConstantValue(nameIndex, constantValueIndex)
        }
    }
}