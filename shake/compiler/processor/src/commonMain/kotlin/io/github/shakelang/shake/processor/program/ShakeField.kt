package io.github.shakelang.shake.processor.program

import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode

open class ShakeField (
    val name: String,
    val isStatic: Boolean,
    val isFinal: Boolean,
    val isAbstract: Boolean,
    val isPrivate: Boolean,
    val isProtected: Boolean,
    val isPublic: Boolean,
) {
    lateinit var type: ShakeType
        private set

    fun lateinitType(): (ShakeType) -> ShakeType {
        return {
            type = it
            it
        }
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