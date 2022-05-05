package io.github.shakelang.shake.processor.program.creation.code

import io.github.shakelang.shake.parser.node.ShakeTree
import io.github.shakelang.shake.processor.program.creation.code.statements.CreationShakeStatement

open class CreationShakeCode(
    open val statements: List<CreationShakeStatement>
) {

    open class ShakeLateProcessCode (
        open val tree: ShakeTree
    ) : CreationShakeCode(emptyList()) {

        override lateinit var statements: List<CreationShakeStatement>

        fun process(scope: CreationShakeScope) {
            statements = tree.children.map {
                scope.processor.visitStatement(scope, it)
            }
        }
    }

    fun toJson(): Map<String, Any> {
        return mapOf(
            "statements" to statements.map { it.toJson() }
        )
    }

    companion object {

        //fun empty() = ShakeCode(emptyList())

        fun fromTree(tree: ShakeTree): CreationShakeCode {
            return ShakeLateProcessCode(tree)
        }
    }

}

