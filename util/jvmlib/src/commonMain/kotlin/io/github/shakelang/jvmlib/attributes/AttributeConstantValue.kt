package io.github.shakelang.jvmlib.attributes

import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.parseutils.bytes.toBytes
import io.github.shakelang.parseutils.bytes.toUnsignedShort
import io.github.shakelang.parseutils.streaming.DataInputStream

class AttributeConstantValue (

    attributeNameIndex: UShort,
    val constantValueIndex: UShort

) : Attribute(attributeNameIndex, TAG) {

    override val bytes: ByteArray
        get() = constantValueIndex.toBytes()

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