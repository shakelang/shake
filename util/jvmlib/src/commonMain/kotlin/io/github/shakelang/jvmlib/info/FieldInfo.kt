package io.github.shakelang.jvmlib.info

import io.github.shakelang.shason.json

class FieldInfo(
    val access_flags: Int,
    val name_index: Int,
    val descriptor_index: Int,
    val attributes: Array<Int>
) {
    override fun toString() = json.stringify(toJson())

    fun toJson() = mapOf(
        "access_flags" to access_flags,
        "name_index" to name_index,
        "descriptor_index" to descriptor_index,
        "attributes" to attributes
    )
}