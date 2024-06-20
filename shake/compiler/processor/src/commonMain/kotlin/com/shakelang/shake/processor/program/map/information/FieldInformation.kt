package com.shakelang.shake.processor.program.map.information

class FieldInformation(
    val name: String,
    val flags: Short,
    val type: String,
) {
    fun json(): Map<String, Any> = mapOf(
        "type" to "field",
        "name" to name,
        "flags" to flags,
        "type" to type,
    )
}
