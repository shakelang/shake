package io.github.shakelang.shake.processor.program.creation.code.statements

import io.github.shakelang.shake.processor.program.creation.code.CreationShakeCode
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeValue

class CreationShakeIf (

    val condition: CreationShakeValue,
    val body: CreationShakeCode,
    val elseBody: CreationShakeCode? = null

) : CreationShakeStatement {

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "if",
            "condition" to condition.toJson(),
            "body" to body.toJson(),
            "else" to elseBody?.toJson()
        )
    }

}