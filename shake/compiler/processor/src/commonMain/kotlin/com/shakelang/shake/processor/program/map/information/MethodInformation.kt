package com.shakelang.shake.processor.program.map.information

class MethodInformation(
    val signature: String,
    val flags: Short,
) {
    fun json(): Map<String, Any> = mapOf(
        "type" to "method",
        "signature" to signature,
        "flags" to flags,
    )

    companion object {
        fun fromJson(json: Map<String, Any>): MethodInformation {
            val signature = json["signature"] as String
            val flags = json["flags"] as Short
            return MethodInformation(signature, flags)
        }
    }
}
