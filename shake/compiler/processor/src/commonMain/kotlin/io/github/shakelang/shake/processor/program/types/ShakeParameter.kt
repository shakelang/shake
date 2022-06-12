package io.github.shakelang.shake.processor.program.types

interface ShakeParameter {
    val name: String
    val type: ShakeType
    fun toJson(): Map<String, Any?> = mapOf(
        "name" to name,
        "type" to type.toJson()
    )
}