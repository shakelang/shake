package io.github.shakelang.shake.processor.program

import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import io.github.shakelang.shake.processor.program.code.ShakeScope
import io.github.shakelang.shake.processor.program.code.values.ShakeValue
import io.github.shakelang.shake.processor.program.code.values.ShakeFieldUsage
import io.github.shakelang.shake.processor.program.code.values.ShakeUsage

open class ShakeClassField (
    val clazz: ShakeClass,
    parentScope: ShakeScope,
    name: String,
    isStatic: Boolean,
    isFinal: Boolean,
    isAbstract: Boolean,
    isPrivate: Boolean,
    isProtected: Boolean,
    isPublic: Boolean,
    initialValue: ShakeValue? = null
): ShakeField(
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
) {
    override val qualifiedName: String
        get() = "${clazz.qualifiedName}.$name"


    override fun use(scope: ShakeScope): ShakeUsage {
        return ShakeFieldUsage(scope, this)
    }

    override fun access(scope: ShakeScope): ShakeValue {
        if(!isStatic) {
            throw IllegalStateException("Cannot access non-static field $qualifiedName in static context")
        }
        return ShakeFieldUsage(scope, this)
    }

    fun access(scope: ShakeScope, value: ShakeValue): ShakeValue {
        if(!isStatic) {
            throw IllegalStateException("Cannot access non-static field $qualifiedName in static context")
        }
        return ShakeFieldUsage(scope, this, value)
    }

    override fun processCode() {}

    companion object {
        fun from(
            clazz: ShakeClass,
            parentScope: ShakeScope,
            name: String,
            isStatic: Boolean,
            isFinal: Boolean,
            isAbstract: Boolean,
            isPrivate: Boolean,
            isProtected: Boolean,
            isPublic: Boolean,
            initialValue: ShakeValue? = null
        ): ShakeClassField {
            return ShakeClassField(
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
            clazz: ShakeClass,
            parentScope: ShakeScope,
            node: ShakeVariableDeclarationNode
        ): ShakeClassField {
            return object : ShakeClassField(
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

                override var initialValue: ShakeValue? = null
                    private set

                override fun processCode() {
                    initialValue = node.value?.let { this.parentScope.processor.visitValue(parentScope, it) }
                }

            }
        }
    }
}