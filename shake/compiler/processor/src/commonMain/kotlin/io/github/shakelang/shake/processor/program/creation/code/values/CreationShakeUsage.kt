package io.github.shakelang.shake.processor.program.creation.code.values

import io.github.shakelang.shake.processor.program.creation.CreationShakeClassField
import io.github.shakelang.shake.processor.program.creation.CreationShakeDeclaration
import io.github.shakelang.shake.processor.program.creation.CreationShakeField
import io.github.shakelang.shake.processor.program.creation.CreationShakeType
import io.github.shakelang.shake.processor.program.creation.CreationShakeScope
import io.github.shakelang.shake.processor.program.creation.code.statements.CreationShakeVariableDeclaration
import io.github.shakelang.shake.processor.program.types.ShakeType
import io.github.shakelang.shake.processor.program.types.code.values.*

abstract class CreationShakeUsage : CreationShakeValue, ShakeUsage {
    abstract override val scope: CreationShakeScope
    abstract override val type: ShakeType
    abstract override val declaration: CreationShakeDeclaration
}

open class CreationShakeClassFieldUsage(
    override val scope: CreationShakeScope,
    final override val declaration: CreationShakeClassField,
    override val receiver: CreationShakeValue? = null
) : CreationShakeUsage(), ShakeClassFieldUsage {

    override val name get() = declaration.name
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

open class CreationShakeStaticClassFieldUsage(
    override val scope: CreationShakeScope,
    final override val declaration: CreationShakeClassField
) : CreationShakeUsage(), ShakeStaticClassFieldUsage {

    override val name get() = declaration.name
    override val type get() = declaration.type

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "staticClassField",
            "name" to name,
            "type" to type.toJson()
        )
    }

}

open class CreationShakeFieldUsage(
    override val scope: CreationShakeScope,
    final override val declaration: CreationShakeField,
    override val receiver: CreationShakeValue? = null
) : CreationShakeUsage(), ShakeFieldUsage {

    override val name get() = declaration.name
    override val type get() = declaration.type

    init {
        if(declaration is CreationShakeClassField && !declaration.isStatic) {
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

        fun from(scope: CreationShakeScope, declaration: CreationShakeClassField, receiver: CreationShakeValue? = null): CreationShakeUsage {
            if(declaration.isStatic) {
                if(receiver != null) throw IllegalArgumentException("Static field $declaration cannot have a receiver")
                return CreationShakeStaticClassFieldUsage(scope, declaration)
            }
            if(receiver == null) throw IllegalArgumentException("Field $declaration is not static")
            return CreationShakeClassFieldUsage(scope, declaration, receiver)
        }

        fun from(scope: CreationShakeScope, declaration: CreationShakeField, receiver: CreationShakeValue? = null): CreationShakeUsage {
            return if(declaration is CreationShakeClassField) from(scope, declaration, receiver)
            else CreationShakeFieldUsage(scope, declaration, receiver)
        }

    }

}

open class CreationShakeVariableUsage(
    override val scope: CreationShakeScope,
    override val declaration: CreationShakeVariableDeclaration
) : CreationShakeUsage(), ShakeVariableUsage {
    override val type get() = declaration.type
    override val name get() = declaration.name

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "variable",
            "name" to name,
            "type" to type.toJson()
        )
    }
}