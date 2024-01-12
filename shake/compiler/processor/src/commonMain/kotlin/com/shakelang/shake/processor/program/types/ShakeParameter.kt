package com.shakelang.shake.processor.program.types

interface ShakeParameter : ShakeAssignable {
    val name: String

    // TODO uniqueName
    val uniqueName: String get() = "param_$name"
    override val type: ShakeType
    fun toJson(): Map<String, Any?> = mapOf(
        "name" to name,
        "type" to type.toJson(),
    )
}
