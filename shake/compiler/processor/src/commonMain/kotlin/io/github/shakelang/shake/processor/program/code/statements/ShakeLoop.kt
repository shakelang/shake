package io.github.shakelang.shake.processor.program.code.statements

import io.github.shakelang.shake.processor.program.code.ShakeCode
import io.github.shakelang.shake.processor.program.code.ShakeStatement
import io.github.shakelang.shake.processor.program.code.ShakeValue

class ShakeWhile (
    val condition: ShakeValue,
    val body: ShakeCode
) : ShakeStatement {
    override fun toJson(): Map<String, Any> {
        return mapOf(
            "type" to "while",
            "condition" to condition.toJson(),
            "body" to body.toJson()
        )
    }
}

class ShakeDoWhile (
    val condition: ShakeValue,
    val body: ShakeCode
) : ShakeStatement {
    override fun toJson(): Map<String, Any> {
        return mapOf(
            "type" to "do-while",
            "condition" to condition.toJson(),
            "body" to body.toJson()
        )
    }
}

class ShakeFor(
    val init: ShakeStatement,
    val condition: ShakeValue,
    val update: ShakeStatement,
    val body: ShakeCode
) : ShakeStatement {
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