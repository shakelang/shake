package io.github.shakelang.shake.processor.program.creation

import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeFieldUsage
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeUsage
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeValue
import io.github.shakelang.shake.processor.program.types.ShakeField
import io.github.shakelang.shake.processor.program.types.ShakeType

open class CreationShakeField (
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
    override val initialValue: CreationShakeValue? = null,
): CreationShakeDeclaration, CreationShakeAssignable, ShakeField {

    override val qualifiedName: String
        get() = (pkg?.qualifiedName?.plus(".") ?: "") + name

    override val actualValue: CreationShakeValue?
        get() = TODO("Not yet implemented")

    override val actualType: CreationShakeType
        get() = TODO("Not yet implemented")

    final override lateinit var type: CreationShakeType
        private set

    override fun assignType(other: ShakeType): ShakeType = type.assignType(other) ?: other
    override fun additionAssignType(other: ShakeType): ShakeType = type.additionAssignType(other) ?: type
    override fun subtractionAssignType(other: ShakeType): ShakeType = type.subtractionAssignType(other) ?: type
    override fun multiplicationAssignType(other: ShakeType): ShakeType = type.multiplicationAssignType(other) ?: type
    override fun divisionAssignType(other: ShakeType): ShakeType = type.divisionAssignType(other) ?: type
    override fun modulusAssignType(other: ShakeType): ShakeType = type.modulusAssignType(other) ?: type
    override fun powerAssignType(other: ShakeType): ShakeType = type.powerAssignType(other) ?: type
    override fun incrementBeforeType(): CreationShakeType = type.incrementBeforeType() ?: type
    override fun incrementAfterType(): CreationShakeType = type.incrementAfterType() ?: type
    override fun decrementBeforeType(): CreationShakeType? = type.decrementBeforeType() ?: type
    override fun decrementAfterType(): CreationShakeType? = type.decrementAfterType() ?: type

    override fun access(scope: CreationShakeScope): CreationShakeValue {
        return CreationShakeFieldUsage(scope, this)
    }

    fun lateinitType(): (CreationShakeType) -> CreationShakeType {
        return {
            type = it
            it
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
        fun from(baseProject: CreationShakeProject, pkg: CreationShakePackage?, parentScope: CreationShakeScope, node: ShakeVariableDeclarationNode): CreationShakeField {
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
                node.access == ShakeAccessDescriber.PUBLIC
            ) {

                override var initialValue: CreationShakeValue? = null
                    private set

                override fun processCode() {
                    initialValue = node.value?.let { this.parentScope.processor.visitValue(parentScope, it) }
                }

            }.let {
                it.lateinitType().let { run -> baseProject.getType(node.type) { t -> run(t) } }
                it
            }
        }

        fun from(clazz: CreationShakeClass, parentScope: CreationShakeScope, node: ShakeVariableDeclarationNode): CreationShakeField {
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
                node.access == ShakeAccessDescriber.PUBLIC
            ) {

                override var initialValue: CreationShakeValue? = null
                    private set

                override fun processCode() {
                    initialValue = node.value?.let { this.parentScope.processor.visitValue(parentScope, it) }
                }

            }.let {
                it.lateinitType().let { run -> clazz.prj.getType(node.type) { t -> run(t) } }
                it
            }
        }
    }
}