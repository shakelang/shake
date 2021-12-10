package io.github.shakelang.jvmlib.infos

import io.github.shakelang.shason.json

class AttributeInfo(
    val attributeNameIndex: Int,
    val info: ByteArray
)
{
    override fun toString() = json.stringify(this.toJson())
    fun toJson() = mapOf(
        "attributeNameIndex" to attributeNameIndex,
        "info" to info.joinToString(separator = "") {
                b -> b.toUByte().toString(16).let { if (it.length == 1) "0$it" else it }
        }
    )
}