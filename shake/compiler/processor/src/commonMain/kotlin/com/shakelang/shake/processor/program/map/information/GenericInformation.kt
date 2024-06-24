package com.shakelang.shake.processor.program.map.information

class GenericInformation(
    val name: String,
    val type: String,
) {
    fun json(): Map<String, Any> = mapOf(
        "type" to "generic",
        "name" to name,
        "type" to type,
    )

    companion object {
        fun fromJson(json: Map<String, Any>): GenericInformation {
            val name = json["name"] as String
            val type = json["type"] as String
            return GenericInformation(name, type)
        }
    }
}
