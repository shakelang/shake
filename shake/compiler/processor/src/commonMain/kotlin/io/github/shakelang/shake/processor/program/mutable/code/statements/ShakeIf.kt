package io.github.shakelang.shake.processor.program.mutable.code.statements

import io.github.shakelang.shake.processor.program.mutable.code.ShakeCode
import io.github.shakelang.shake.processor.program.mutable.code.values.ShakeValue

class ShakeIf (

    val condition: ShakeValue,
    val body: ShakeCode,
    val elseBody: ShakeCode? = null

) : ShakeStatement {

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "if",
            "condition" to condition.toJson(),
            "body" to body.toJson(),
            "else" to elseBody?.toJson()
        )
    }

}