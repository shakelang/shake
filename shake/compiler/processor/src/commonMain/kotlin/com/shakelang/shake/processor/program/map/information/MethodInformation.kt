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
}
