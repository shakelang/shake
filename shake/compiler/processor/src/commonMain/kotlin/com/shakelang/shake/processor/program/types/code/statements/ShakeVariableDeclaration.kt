package com.shakelang.shake.processor.program.types.code.statements

import com.shakelang.shake.processor.program.types.ShakeAssignable
import com.shakelang.shake.processor.program.types.ShakeDeclaration
import com.shakelang.shake.processor.program.types.ShakeType
import com.shakelang.shake.processor.program.types.code.ShakeScope
import com.shakelang.shake.processor.program.types.code.values.ShakeValue

interface ShakeVariableDeclaration : ShakeDeclaration, ShakeAssignable, ShakeStatement {
    val scope: ShakeScope
    val initialValue: ShakeValue?
    override val actualValue: ShakeValue?
    override val actualType: ShakeType
    val isFinal: Boolean

    override val uniqueName: String
        get() = "$name@${scope.uniqueName}"

    fun valueCompatible(value: ShakeValue): Boolean
    // override fun use(scope: ShakeScope): ShakeVariableUsage
}
