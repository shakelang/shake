package com.shakelang.shake.processor.program.creation.code.statements

import com.shakelang.shake.processor.program.creation.code.CreationShakeCode
import com.shakelang.shake.processor.program.creation.code.values.CreationShakeValue
import com.shakelang.shake.processor.program.types.code.statements.ShakeDoWhile
import com.shakelang.shake.processor.program.types.code.statements.ShakeFor
import com.shakelang.shake.processor.program.types.code.statements.ShakeWhile

class CreationShakeWhile(
    override val condition: CreationShakeValue,
    override val body: CreationShakeCode,
) : CreationShakeStatement, ShakeWhile {
    override fun toJson(): Map<String, Any> {
        return mapOf(
            "type" to "while",
            "condition" to condition.toJson(),
            "body" to body.toJson(),
        )
    }
}

class CreationShakeDoWhile(
    override val condition: CreationShakeValue,
    override val body: CreationShakeCode,
) : CreationShakeStatement, ShakeDoWhile {
    override fun toJson(): Map<String, Any> {
        return mapOf(
            "type" to "do-while",
            "condition" to condition.toJson(),
            "body" to body.toJson(),
        )
    }
}

class CreationShakeFor(
    override val init: CreationShakeStatement,
    override val condition: CreationShakeValue,
    override val update: CreationShakeStatement,
    override val body: CreationShakeCode,
) : CreationShakeStatement, ShakeFor {
    override fun toJson(): Map<String, Any> {
        return mapOf(
            "type" to "for",
            "init" to init.toJson(),
            "condition" to condition.toJson(),
            "update" to update.toJson(),
            "body" to body.toJson(),
        )
    }
}
