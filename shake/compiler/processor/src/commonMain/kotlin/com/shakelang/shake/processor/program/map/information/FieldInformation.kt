package com.shakelang.shake.processor.program.map.information

class FieldInformation(
    val name: String,
    val flags: Short,
    val type: String,
    val expanding: String,
) {
    fun json(): Map<String, Any> = mapOf(
        "type" to "field",
        "name" to name,
        "flags" to flags,
        "type" to type,
        "expanding" to expanding,
    )

    companion object {
        fun fromJson(json: Map<String, Any>): FieldInformation {
            val name = json["name"] as String
            val flags = json["flags"] as Short
            val type = json["type"] as String
            val expanding = json["expanding"] as String
            return FieldInformation(name, flags, type, expanding)
        }
    }
}
