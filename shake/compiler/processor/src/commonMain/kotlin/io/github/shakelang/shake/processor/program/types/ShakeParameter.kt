package io.github.shakelang.shake.processor.program.types

interface ShakeParameter {
    val name: String
    var type: ShakeType
    fun toJson(): Map<String, Any?>
}