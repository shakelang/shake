package io.github.shakelang.shake.processor.program.creation

class CreationShakeParameter (
    val name: String,
) {
    constructor(name: String, type: CreationShakeType): this(name) {
        this.type = type
    }

    lateinit var type: CreationShakeType
        private set

    fun lateinitType(): (CreationShakeType) -> CreationShakeType {
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