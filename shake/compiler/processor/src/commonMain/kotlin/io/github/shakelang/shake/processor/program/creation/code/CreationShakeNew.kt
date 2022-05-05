package io.github.shakelang.shake.processor.program.creation.code

import io.github.shakelang.shake.processor.program.creation.CreationShakeConstructor
import io.github.shakelang.shake.processor.program.creation.CreationShakeType
import io.github.shakelang.shake.processor.program.creation.code.statements.CreationShakeStatement
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeValue

class CreationShakeNew (
    val reference: CreationShakeConstructor,
    val arguments: List<CreationShakeValue>,
    val parent: CreationShakeValue? = null
) : CreationShakeValue, CreationShakeStatement {

    override val type: CreationShakeType
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