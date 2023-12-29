package com.shakelang.shake.processor.program.types

interface ShakeParameter : ShakeAssignable {
    val name: String
    override val type: ShakeType
    fun toJson(): Map<String, Any?> = mapOf(
        "name" to name,
        "type" to type.toJson()
    )
}
