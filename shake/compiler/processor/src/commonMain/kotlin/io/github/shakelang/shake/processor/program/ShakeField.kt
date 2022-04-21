package io.github.shakelang.shake.processor.program

import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import io.github.shakelang.shake.processor.program.code.values.ShakeFieldUsage
import io.github.shakelang.shake.processor.program.code.ShakeScope
import io.github.shakelang.shake.processor.program.code.ShakeValue
import io.github.shakelang.shake.processor.program.code.values.ShakeUsage

open class ShakeField (
    override val name: String,
    val isStatic: Boolean,
    val isFinal: Boolean,
    val isAbstract: Boolean,
    val isPrivate: Boolean,
    val isProtected: Boolean,
    val isPublic: Boolean,
): ShakeDeclaration, ShakeAssignable {

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

    fun lateinitType(): (ShakeType) -> ShakeType {
        return {
            type = it
            it
        }
    }

    override fun use(scope: ShakeScope): ShakeUsage {
        return ShakeFieldUsage(scope, this)
    }

    open fun processCode() {
        // TODO process code
    }

    companion object {
        fun from(baseProject: ShakeProject, node: ShakeVariableDeclarationNode): ShakeField {
            return ShakeField(
                node.name,
                node.isStatic,
                node.isFinal,
                false,
                node.access == ShakeAccessDescriber.PRIVATE,
                node.access == ShakeAccessDescriber.PROTECTED,
                node.access == ShakeAccessDescriber.PUBLIC
            ).let {
                it.lateinitType().let { run -> baseProject.getType(node.type) { t -> run(t) } }
                it
            }
        }
    }
}