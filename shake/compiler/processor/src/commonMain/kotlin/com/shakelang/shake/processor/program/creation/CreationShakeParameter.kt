package com.shakelang.shake.processor.program.creation

import io.github.shakelang.shake.processor.program.types.ShakeParameter

class CreationShakeParameter(
    override val name: String
) : ShakeParameter {
    constructor(name: String, type: CreationShakeType) : this(name) {
        this.type = type
    }

    override lateinit var type: CreationShakeType
        private set

    fun lateinitType(): (CreationShakeType) -> CreationShakeType {
        return {
            type = it
            it
        }
    }

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "type" to type.toJson()
        )
    }
}
