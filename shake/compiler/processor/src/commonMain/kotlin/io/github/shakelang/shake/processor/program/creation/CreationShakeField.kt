package io.github.shakelang.shake.processor.program.creation

import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import io.github.shakelang.shake.processor.program.creation.code.CreationShakeScope
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeValue
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeFieldUsage
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeUsage

open class CreationShakeField (
    val project: CreationShakeProject,
    val pkg: CreationShakePackage?,
    val parentScope: CreationShakeScope,
    override val name: String,
    val isStatic: Boolean,
    val isFinal: Boolean,
    val isAbstract: Boolean,
    val isPrivate: Boolean,
    val isProtected: Boolean,
    val isPublic: Boolean,
    open val initialValue: CreationShakeValue? = null,
): CreationShakeDeclaration, CreationShakeAssignable {

    override val qualifiedName: String
        get() = (pkg?.qualifiedName?.plus(".") ?: "") + name

    override val actualValue: CreationShakeValue?
        get() = TODO("Not yet implemented")

    override val actualType: CreationShakeType
        get() = TODO("Not yet implemented")

    final override lateinit var type: CreationShakeType
        private set

    override fun assignType(other: CreationShakeType): CreationShakeType = type.assignType(other) ?: other
    override fun additionAssignType(other: CreationShakeType): CreationShakeType = type.additionAssignType(other) ?: type
    override fun subtractionAssignType(other: CreationShakeType): CreationShakeType = type.subtractionAssignType(other) ?: type
    override fun multiplicationAssignType(other: CreationShakeType): CreationShakeType = type.multiplicationAssignType(other) ?: type
    override fun divisionAssignType(other: CreationShakeType): CreationShakeType = type.divisionAssignType(other) ?: type
    override fun modulusAssignType(other: CreationShakeType): CreationShakeType = type.modulusAssignType(other) ?: type
    override fun powerAssignType(other: CreationShakeType): CreationShakeType = type.powerAssignType(other) ?: type
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
    }

}