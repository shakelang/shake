package com.shakelang.shake.processor.program.creation.code.statements

import com.shakelang.shake.processor.program.types.code.statements.ShakeStatement

interface CreationShakeStatement : ShakeStatement {
    override fun toJson(): Map<String, Any?>
}
