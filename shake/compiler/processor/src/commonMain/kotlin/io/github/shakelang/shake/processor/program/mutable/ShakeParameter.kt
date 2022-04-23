package io.github.shakelang.shake.processor.program.mutable

class ShakeParameter (
    val name: String,
) {
    constructor(name: String, type: ShakeType): this(name) {
        this.type = type
    }

    lateinit var type: ShakeType
        private set

    fun lateinitType(): (ShakeType) -> ShakeType {
        return {
            type = it
            it
        }
    }

    fun toJson(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "type" to type.toJson(),
        )
    }
}