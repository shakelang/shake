package com.shakelang.shake.processor.program.map.information

class ConstructorInformation(
    val signature: String,
    val flags: Short,
) {
    fun json(): Map<String, Any> = mapOf(
        "type" to "constructor",
        "signature" to signature,
        "flags" to flags,
    )

    companion object {
        fun fromJson(json: Map<String, Any>): ConstructorInformation {
            val signature = json["signature"] as String
            val flags = json["flags"] as Short
            return ConstructorInformation(signature, flags)
        }
    }
}
