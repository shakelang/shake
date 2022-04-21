package io.github.shakelang.shake.processor.program.code

import io.github.shakelang.shake.processor.program.ShakeConstructor
import io.github.shakelang.shake.processor.program.ShakeType

class ShakeNew (
    val reference: ShakeConstructor,
    val arguments: List<ShakeValue>,
    val parent: ShakeValue? = null
) : ShakeValue, ShakeStatement {

    override val type: ShakeType
        get() = reference.clazz.asType()
    val name get() = reference.name?.let { "${reference.clazz.name}.$it" } ?: reference.clazz.name

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "new",
            "name" to name,
            "arguments" to arguments.map { it.toJson() }
        )
    }

}