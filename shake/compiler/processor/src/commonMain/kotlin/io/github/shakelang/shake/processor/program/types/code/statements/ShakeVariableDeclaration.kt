package io.github.shakelang.shake.processor.program.types.code.statements

import io.github.shakelang.shake.processor.program.types.ShakeAssignable
import io.github.shakelang.shake.processor.program.types.ShakeDeclaration
import io.github.shakelang.shake.processor.program.types.ShakeType
import io.github.shakelang.shake.processor.program.types.code.ShakeScope
import io.github.shakelang.shake.processor.program.types.code.values.ShakeValue

interface ShakeVariableDeclaration : ShakeDeclaration, ShakeAssignable, ShakeStatement {
    val scope: ShakeScope
    val initialValue: ShakeValue?
    var latestValue: ShakeValue?
    var latestType: ShakeType
    val isFinal: Boolean

    fun valueCompatible(value: ShakeValue): Boolean
    //override fun use(scope: ShakeScope): ShakeVariableUsage
}