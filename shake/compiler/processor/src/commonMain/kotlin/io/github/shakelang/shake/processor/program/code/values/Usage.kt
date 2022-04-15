package io.github.shakelang.shake.processor.program.code.values

import io.github.shakelang.shake.processor.program.ShakeClassField
import io.github.shakelang.shake.processor.program.ShakeDeclaration
import io.github.shakelang.shake.processor.program.ShakeField
import io.github.shakelang.shake.processor.program.ShakeType
import io.github.shakelang.shake.processor.program.code.ShakeScope
import io.github.shakelang.shake.processor.program.code.ShakeStatement
import io.github.shakelang.shake.processor.program.code.ShakeValue
import io.github.shakelang.shake.processor.program.code.ShakeVariableDeclaration

abstract class ShakeUsage : ShakeStatement {
    abstract val scope: ShakeScope
    abstract val type: ShakeType
    abstract val declaration: ShakeDeclaration
}

open class ShakeFieldUsage(
    override val scope: ShakeScope,
    override val declaration: ShakeField,
    val receiver: ShakeValue? = null
) : ShakeUsage() {

    val name get() = declaration.name
    override val type get() = declaration.type

    init {
        if(declaration is ShakeClassField && !declaration.isStatic) {
            if(receiver == null) {
                throw IllegalArgumentException("Field $name is not static")
            }
        }
    }

}

open class ShakeVariableUsage(
    override val scope: ShakeScope,
    override val declaration: ShakeVariableDeclaration
) : ShakeUsage() {
    override val type get() = declaration.type
    val name get() = declaration.name
}