package io.github.shakelang.shake.processor.program.code.values

import io.github.shakelang.shake.processor.program.ShakeClassField
import io.github.shakelang.shake.processor.program.ShakeDeclaration
import io.github.shakelang.shake.processor.program.ShakeField
import io.github.shakelang.shake.processor.program.ShakeType
import io.github.shakelang.shake.processor.program.code.ShakeScope
import io.github.shakelang.shake.processor.program.code.ShakeValue
import io.github.shakelang.shake.processor.program.code.statements.ShakeVariableDeclaration

abstract class ShakeUsage : ShakeValue {
    abstract val scope: ShakeScope
    abstract override val type: ShakeType
    abstract val declaration: ShakeDeclaration
}

open class ShakeClassFieldUsage(
    override val scope: ShakeScope,
    final override val declaration: ShakeClassField,
    val receiver: ShakeValue? = null
) : ShakeUsage() {

    val name get() = declaration.name
    override val type get() = declaration.type

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "classField",
            "name" to name,
            "receiver" to receiver?.toJson(),
            "type" to type.toJson()
        )
    }

}

open class ShakeStaticClassFieldUsage(
    override val scope: ShakeScope,
    final override val declaration: ShakeClassField
) : ShakeUsage() {

    val name get() = declaration.name
    override val type get() = declaration.type

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "staticClassField",
            "name" to name,
            "type" to type.toJson()
        )
    }

}

open class ShakeFieldUsage(
    override val scope: ShakeScope,
    final override val declaration: ShakeField,
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

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "field",
            "name" to name,
            "receiver" to receiver?.toJson(),
            "type" to type.toJson()
        )
    }

    companion object {

        fun from(scope: ShakeScope, declaration: ShakeClassField, receiver: ShakeValue? = null): ShakeUsage {
            if(declaration.isStatic) {
                if(receiver != null) throw IllegalArgumentException("Static field $declaration cannot have a receiver")
                return ShakeStaticClassFieldUsage(scope, declaration)
            }
            if(receiver == null) throw IllegalArgumentException("Field $declaration is not static")
            return ShakeClassFieldUsage(scope, declaration, receiver)
        }

        fun from(scope: ShakeScope, declaration: ShakeField, receiver: ShakeValue? = null): ShakeUsage {
            return if(declaration is ShakeClassField) from(scope, declaration, receiver)
            else ShakeFieldUsage(scope, declaration, receiver)
        }

    }

}

open class ShakeVariableUsage(
    override val scope: ShakeScope,
    override val declaration: ShakeVariableDeclaration
) : ShakeUsage() {
    override val type get() = declaration.type
    val name get() = declaration.name

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "variable",
            "name" to name,
            "type" to type.toJson()
        )
    }
}