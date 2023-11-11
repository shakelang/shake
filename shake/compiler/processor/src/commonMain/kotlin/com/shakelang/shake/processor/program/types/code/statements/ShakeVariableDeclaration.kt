package com.shakelang.shake.processor.program.types.code.statements

import com.shakelang.shake.processor.program.types.ShakeAssignable
import com.shakelang.shake.processor.program.types.ShakeDeclaration
import com.shakelang.shake.processor.program.types.ShakeType
import com.shakelang.shake.processor.program.types.code.ShakeScope
import com.shakelang.shake.processor.program.types.code.values.ShakeValue

interface ShakeVariableDeclaration : ShakeDeclaration, ShakeAssignable, ShakeStatement {
    val scope: ShakeScope
    val initialValue: ShakeValue?
    val latestValue: ShakeValue?
    val latestType: ShakeType
    val isFinal: Boolean

    fun valueCompatible(value: ShakeValue): Boolean
    // override fun use(scope: ShakeScope): ShakeVariableUsage
}
