package io.github.shakelang.shake.processor.program.creation.code.statements

import io.github.shakelang.shake.processor.program.creation.code.CreationShakeCode
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeValue

class CreationShakeWhile (
    val condition: CreationShakeValue,
    val body: CreationShakeCode
) : CreationShakeStatement {
    override fun toJson(): Map<String, Any> {
        return mapOf(
            "type" to "while",
            "condition" to condition.toJson(),
            "body" to body.toJson()
        )
    }
}

class CreationShakeDoWhile (
    val condition: CreationShakeValue,
    val body: CreationShakeCode
) : CreationShakeStatement {
    override fun toJson(): Map<String, Any> {
        return mapOf(
            "type" to "do-while",
            "condition" to condition.toJson(),
            "body" to body.toJson()
        )
    }
}

class CreationShakeFor(
    val init: CreationShakeStatement,
    val condition: CreationShakeValue,
    val update: CreationShakeStatement,
    val body: CreationShakeCode
) : CreationShakeStatement {
    override fun toJson(): Map<String, Any> {
        return mapOf(
            "type" to "for",
            "init" to init.toJson(),
            "condition" to condition.toJson(),
            "update" to update.toJson(),
            "body" to body.toJson()
        )
    }
}