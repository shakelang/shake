package io.github.shakelang.shake.processor.program.mutable.code

import io.github.shakelang.shake.processor.program.mutable.ShakeConstructor
import io.github.shakelang.shake.processor.program.mutable.ShakeType
import io.github.shakelang.shake.processor.program.mutable.code.statements.ShakeStatement
import io.github.shakelang.shake.processor.program.mutable.code.values.ShakeValue

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