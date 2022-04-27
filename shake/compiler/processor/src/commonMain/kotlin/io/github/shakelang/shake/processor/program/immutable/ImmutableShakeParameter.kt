package io.github.shakelang.shake.processor.program.immutable

import io.github.shakelang.shake.processor.program.types.ShakeParameter
import io.github.shakelang.shake.processor.program.types.ShakeType

class ImmutableShakeParameter (
    override val name: String,
) : ShakeParameter {
    constructor(name: String, type: ShakeType): this(name) {
        this.type = type
    }

    override lateinit var type: ShakeType
        private set

    fun lateinitType(): (ShakeType) -> ShakeType {
        return {
            type = it
            it
        }
    }

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "type" to type.toJson(),
        )
    }
}