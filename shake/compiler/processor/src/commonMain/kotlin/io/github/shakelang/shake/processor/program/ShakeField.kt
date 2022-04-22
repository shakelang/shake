package io.github.shakelang.shake.processor.program

import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import io.github.shakelang.shake.processor.program.code.ShakeScope
import io.github.shakelang.shake.processor.program.code.ShakeValue
import io.github.shakelang.shake.processor.program.code.values.ShakeFieldUsage
import io.github.shakelang.shake.processor.program.code.values.ShakeUsage

open class ShakeField (
    val project: ShakeProject,
    val pkg: ShakePackage?,
    val parentScope: ShakeScope,
    override val name: String,
    val isStatic: Boolean,
    val isFinal: Boolean,
    val isAbstract: Boolean,
    val isPrivate: Boolean,
    val isProtected: Boolean,
    val isPublic: Boolean,
    open val initialValue: ShakeValue? = null,
): ShakeDeclaration, ShakeAssignable {

    override val qualifiedName: String
        get() = (pkg?.qualifiedName?.plus(".") ?: "") + name

    override val actualValue: ShakeValue?
        get() = TODO("Not yet implemented")

    override val actualType: ShakeType
        get() = TODO("Not yet implemented")

    final override lateinit var type: ShakeType
        private set

    override fun assignType(other: ShakeType): ShakeType = type.assignType(other) ?: other
    override fun additionAssignType(other: ShakeType): ShakeType = type.additionAssignType(other) ?: type
    override fun subtractionAssignType(other: ShakeType): ShakeType = type.subtractionAssignType(other) ?: type
    override fun multiplicationAssignType(other: ShakeType): ShakeType = type.multiplicationAssignType(other) ?: type
    override fun divisionAssignType(other: ShakeType): ShakeType = type.divisionAssignType(other) ?: type
    override fun modulusAssignType(other: ShakeType): ShakeType = type.modulusAssignType(other) ?: type
    override fun powerAssignType(other: ShakeType): ShakeType = type.powerAssignType(other) ?: type
    override fun incrementBeforeType(): ShakeType = type.incrementBeforeType() ?: type
    override fun incrementAfterType(): ShakeType = type.incrementAfterType() ?: type
    override fun decrementBeforeType(): ShakeType? = type.decrementBeforeType() ?: type
    override fun decrementAfterType(): ShakeType? = type.decrementAfterType() ?: type

    override fun access(scope: ShakeScope): ShakeValue {
        return ShakeFieldUsage(scope, this)
    }

    fun lateinitType(): (ShakeType) -> ShakeType {
        return {
            type = it
            it
        }
    }

    override fun use(scope: ShakeScope): ShakeUsage {
        return ShakeFieldUsage(scope, this)
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
        fun from(baseProject: ShakeProject, pkg: ShakePackage?, parentScope: ShakeScope, node: ShakeVariableDeclarationNode): ShakeField {
            return object : ShakeField(
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

                override var initialValue: ShakeValue? = null
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