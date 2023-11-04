package io.github.shakelang.shake.processor.program.creation.code.statements

import io.github.shakelang.shake.processor.program.creation.code.CreationShakeCode
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeValue
import io.github.shakelang.shake.processor.program.types.code.statements.ShakeIf

class CreationShakeIf(

    override val condition: CreationShakeValue,
    override val body: CreationShakeCode,
    override val elseBody: CreationShakeCode? = null

) : CreationShakeStatement, ShakeIf {

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "if",
            "condition" to condition.toJson(),
            "body" to body.toJson(),
            "else" to elseBody?.toJson()
        )
    }

}