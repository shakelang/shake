package io.github.shakelang.jvmlib.info

class AttributeInfo(
    val attributeNameIndex: Int,
    val info: ByteArray
)
{
    override fun toString() = "AttributeInfo(attributeNameIndex=$attributeNameIndex, info=${info.size} bytes)"
    fun toJson() = mapOf(
        "attributeNameIndex" to attributeNameIndex,
        "info" to info
    )
}