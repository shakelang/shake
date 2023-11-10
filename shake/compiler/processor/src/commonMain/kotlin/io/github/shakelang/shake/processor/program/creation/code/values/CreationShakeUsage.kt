package io.github.shakelang.shake.processor.program.creation.code.values

import io.github.shakelang.shake.processor.program.creation.CreationShakeDeclaration
import io.github.shakelang.shake.processor.program.creation.CreationShakeField
import io.github.shakelang.shake.processor.program.creation.CreationShakeScope
import io.github.shakelang.shake.processor.program.creation.code.statements.CreationShakeVariableDeclaration
import io.github.shakelang.shake.processor.program.types.ShakeProject
import io.github.shakelang.shake.processor.program.types.ShakeType
import io.github.shakelang.shake.processor.program.types.code.values.*

abstract class CreationShakeUsage : CreationShakeValue, ShakeUsage {
    abstract override val scope: CreationShakeScope
    abstract override val type: ShakeType
    abstract override val declaration: CreationShakeDeclaration
}

open class CreationShakeClassFieldUsage(
    override val scope: CreationShakeScope,
    final override val declaration: CreationShakeField,
    override val receiver: CreationShakeValue? = null
) : CreationShakeUsage(), ShakeClassFieldUsage {

    override val project: ShakeProject
        get() = scope.project

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
    final override val declaration: CreationShakeField
) : CreationShakeUsage(), ShakeStaticClassFieldUsage {

    override val project: ShakeProject
        get() = scope.project

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

class CreationShakeFieldUsage(
    override val scope: CreationShakeScope,
    override val declaration: CreationShakeField,
    override val receiver: CreationShakeValue? = null
) : CreationShakeUsage(), ShakeFieldUsage {

    override val project: ShakeProject
        get() = scope.project

    override val name get() = declaration.name
    override val type get() = declaration.type

    init {
        if (!declaration.isStatic && receiver == null) throw IllegalArgumentException("Field $name is not static")
        if (declaration.isStatic && receiver != null) throw IllegalArgumentException("Field $name is static")
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

        fun from(
            scope: CreationShakeScope,
            declaration: CreationShakeField,
            receiver: CreationShakeValue? = null
        ): CreationShakeUsage {
            return if (declaration.clazz != null) {
                if (declaration.isStatic) {
                    if (receiver != null) throw IllegalArgumentException("Static field $declaration cannot have a receiver")
                    CreationShakeStaticClassFieldUsage(scope, declaration)
                } else if (receiver == null) {
                    throw IllegalArgumentException("Field $declaration is not static")
                } else {
                    CreationShakeClassFieldUsage(scope, declaration, receiver)
                }
            } else {
                CreationShakeFieldUsage(scope, declaration, receiver)
            }
        }
    }
}

open class CreationShakeVariableUsage(
    override val scope: CreationShakeScope,
    override val declaration: CreationShakeVariableDeclaration
) : CreationShakeUsage(), ShakeVariableUsage {

    override val project: ShakeProject
        get() = scope.project

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
