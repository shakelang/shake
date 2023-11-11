package com.shakelang.shake.processor.program.creation.code

import io.github.shakelang.shake.processor.program.creation.CreationShakeConstructor
import io.github.shakelang.shake.processor.program.creation.CreationShakeProject
import io.github.shakelang.shake.processor.program.creation.CreationShakeType
import io.github.shakelang.shake.processor.program.creation.code.statements.CreationShakeStatement
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeValue
import io.github.shakelang.shake.processor.program.types.code.ShakeNew

class CreationShakeNew(
    override val project: CreationShakeProject,
    override val reference: CreationShakeConstructor,
    override val arguments: List<CreationShakeValue>,
    override val parent: CreationShakeValue? = null
) : CreationShakeValue, CreationShakeStatement, ShakeNew {

    override val type: CreationShakeType
        get() = reference.clazz.asType()
    override val name get() = reference.name?.let { "${reference.clazz.name}.$it" } ?: reference.clazz.name

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "new",
            "name" to name,
            "arguments" to arguments.map { it.toJson() }
        )
    }
}
