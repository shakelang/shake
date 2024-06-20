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
}
