package io.github.shakelang.shake.processor.program.creation

import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeFieldUsage
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeUsage
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeValue
import io.github.shakelang.shake.processor.program.types.ShakeField
import io.github.shakelang.shake.processor.program.types.ShakeType
import io.github.shakelang.shake.processor.program.types.code.ShakeScope

open class CreationShakeField(
    override val project: CreationShakeProject,
    override val pkg: CreationShakePackage?,
    override val clazz: CreationShakeClass?,
    override val parentScope: CreationShakeScope,
    override val name: String,
    override val isStatic: Boolean,
    override val isFinal: Boolean,
    override val isAbstract: Boolean,
    override val isPrivate: Boolean,
    override val isProtected: Boolean,
    override val isPublic: Boolean,
    override val isNative: Boolean,
    override val initialValue: CreationShakeValue?
) : CreationShakeDeclaration, CreationShakeAssignable, ShakeField {

    override val qualifiedName: String
        get() = (pkg?.qualifiedName?.plus(".") ?: "") + name

    override val actualValue: CreationShakeValue?
        get() = TODO("Not yet implemented")

    override val actualType: CreationShakeType
        get() = TODO("Not yet implemented")

    final override lateinit var type: CreationShakeType
        private set

    final override var expanding: ShakeType? = null
        private set

    override fun assignType(other: ShakeType, scope: ShakeScope): ShakeType = type.assignType(other, scope) ?: other
    override fun additionAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.additionAssignType(other, scope) ?: type

    override fun subtractionAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.subtractionAssignType(other, scope) ?: type

    override fun multiplicationAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.multiplicationAssignType(other, scope) ?: type

    override fun divisionAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.divisionAssignType(other, scope) ?: type

    override fun modulusAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.modulusAssignType(other, scope) ?: type

    override fun powerAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.powerAssignType(other, scope) ?: type

    override fun incrementBeforeType(scope: ShakeScope): ShakeType = type.incrementBeforeType(scope) ?: type
    override fun incrementAfterType(scope: ShakeScope): ShakeType = type.incrementAfterType(scope) ?: type
    override fun decrementBeforeType(scope: ShakeScope): ShakeType? = type.decrementBeforeType(scope) ?: type
    override fun decrementAfterType(scope: ShakeScope): ShakeType? = type.decrementAfterType(scope) ?: type

    override fun access(scope: CreationShakeScope): CreationShakeValue {
        return CreationShakeFieldUsage(scope, this)
    }

    fun lateinitType(): (CreationShakeType) -> CreationShakeType {
        return {
            type = it
            it
        }
    }

    fun lateinitExpanding(): (ShakeType) -> CreationShakeType {
        return {
            expanding = it
            type
        }
    }

    override fun use(scope: CreationShakeScope): CreationShakeUsage {
        return CreationShakeFieldUsage(scope, this)
    }

    open fun processCode() {}

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "isStatic" to isStatic,
            "isFinal" to isFinal,
            "isAbstract" to isAbstract,
            "isPrivate" to isPrivate,
            "isProtected" to isProtected,
            "isPublic" to isPublic,
            "type" to type.toJson()
        )
    }

    companion object {
        fun from(
            baseProject: CreationShakeProject,
            pkg: CreationShakePackage?,
            parentScope: CreationShakeScope,
            node: ShakeVariableDeclarationNode
        ): CreationShakeField {
            return object : CreationShakeField(
                baseProject,
                pkg,
                null,
                parentScope,
                node.name,
                node.isStatic,
                node.isFinal,
                false,
                node.access == ShakeAccessDescriber.PRIVATE,
                node.access == ShakeAccessDescriber.PROTECTED,
                node.access == ShakeAccessDescriber.PUBLIC,
                node.isNative,
                null
            ) {

                override var initialValue: CreationShakeValue? = null
                    private set

                override fun processCode() {
                    initialValue = node.value?.let { this.parentScope.processor.visitValue(parentScope, it) }
                }
            }.let {
                it.lateinitType().let { run -> parentScope.getType(node.type) { t -> run(t) } }
                node.expandedType?.let { it1 ->
                    it.lateinitExpanding().let { run -> parentScope.getType(it1) { t -> run(t) } }
                }
                it
            }
        }

        fun from(
            clazz: CreationShakeClass,
            parentScope: CreationShakeScope,
            node: ShakeVariableDeclarationNode
        ): CreationShakeField {
            return object : CreationShakeField(
                clazz.prj,
                clazz.pkg,
                clazz,
                parentScope,
                node.name,
                node.isStatic,
                node.isFinal,
                false,
                node.access == ShakeAccessDescriber.PRIVATE,
                node.access == ShakeAccessDescriber.PROTECTED,
                node.access == ShakeAccessDescriber.PUBLIC,
                node.isNative,
                null
            ) {
                override var initialValue: CreationShakeValue? = null
                    private set

                override fun processCode() {
                    initialValue = node.value?.let { this.parentScope.processor.visitValue(parentScope, it) }
                }
            }.let {
                it.lateinitType().let { run -> clazz.instanceScope.getType(node.type) { t -> run(t) } }
                it
            }
        }
    }
}
