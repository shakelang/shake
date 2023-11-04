package io.github.shakelang.shake.processor.program.types.code.values

import io.github.shakelang.shake.processor.program.types.ShakeDeclaration
import io.github.shakelang.shake.processor.program.types.ShakeField
import io.github.shakelang.shake.processor.program.types.code.ShakeScope
import io.github.shakelang.shake.processor.program.types.code.statements.ShakeVariableDeclaration

interface ShakeUsage : ShakeValue {
    val scope: ShakeScope
    val declaration: ShakeDeclaration
}

interface ShakeClassFieldUsage {
    val scope: ShakeScope
    val receiver: ShakeValue?
    val name: String
    val declaration: ShakeField
}

interface ShakeStaticClassFieldUsage : ShakeUsage {
    val name: String
}

interface ShakeFieldUsage : ShakeUsage {
    val receiver: ShakeValue?
    val name get() = declaration.name
}

interface ShakeVariableUsage : ShakeUsage {
    override val declaration: ShakeVariableDeclaration
    val name: String
}