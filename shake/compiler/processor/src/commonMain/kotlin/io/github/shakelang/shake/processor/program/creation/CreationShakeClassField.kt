package io.github.shakelang.shake.processor.program.creation

import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeValue
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeFieldUsage
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeUsage
import io.github.shakelang.shake.processor.program.types.ShakeClassField

open class CreationShakeClassField (
    override val clazz: CreationShakeClass,
    parentScope: CreationShakeScope,
    name: String,
    isStatic: Boolean,
    isFinal: Boolean,
    isAbstract: Boolean,
    isPrivate: Boolean,
    isProtected: Boolean,
    isPublic: Boolean,
    initialValue: CreationShakeValue? = null
): CreationShakeField(
    clazz.prj,
    clazz.pkg,
    parentScope,
    name,
    isStatic,
    isFinal,
    isAbstract,
    isPrivate,
    isProtected,
    isPublic,
    initialValue
), ShakeClassField {
    override val qualifiedName: String
        get() = "${clazz.qualifiedName}.$name"


    override fun use(scope: CreationShakeScope): CreationShakeUsage {
        return CreationShakeFieldUsage(scope, this)
    }

    override fun access(scope: CreationShakeScope): CreationShakeValue {
        if(!isStatic) {
            throw IllegalStateException("Cannot access non-static field $qualifiedName in static context")
        }
        return CreationShakeFieldUsage(scope, this)
    }

    fun access(scope: CreationShakeScope, value: CreationShakeValue): CreationShakeValue {
        if(!isStatic) {
            throw IllegalStateException("Cannot access non-static field $qualifiedName in static context")
        }
        return CreationShakeFieldUsage(scope, this, value)
    }

    override fun processCode() {}

    companion object {
        fun from(
            clazz: CreationShakeClass,
            parentScope: CreationShakeScope,
            name: String,
            isStatic: Boolean,
            isFinal: Boolean,
            isAbstract: Boolean,
            isPrivate: Boolean,
            isProtected: Boolean,
            isPublic: Boolean,
            initialValue: CreationShakeValue? = null
        ): CreationShakeClassField {
            return CreationShakeClassField(
                clazz,
                parentScope,
                name,
                isStatic,
                isFinal,
                isAbstract,
                isPrivate,
                isProtected,
                isPublic,
                initialValue
            )
        }

        fun from(
            clazz: CreationShakeClass,
            parentScope: CreationShakeScope,
            node: ShakeVariableDeclarationNode
        ): CreationShakeClassField {
            return object : CreationShakeClassField(
                clazz,
                parentScope,
                node.name,
                node.isStatic,
                node.isFinal,
                false,
                node.access == ShakeAccessDescriber.PRIVATE,
                node.access == ShakeAccessDescriber.PROTECTED,
                node.access == ShakeAccessDescriber.PUBLIC
            ) {

                override var initialValue: CreationShakeValue? = null
                    private set

                override fun processCode() {
                    initialValue = node.value?.let { this.parentScope.processor.visitValue(parentScope, it) }
                }

            }
        }
    }
}