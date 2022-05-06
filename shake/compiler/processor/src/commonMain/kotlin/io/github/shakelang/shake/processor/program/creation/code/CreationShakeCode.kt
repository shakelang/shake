package io.github.shakelang.shake.processor.program.creation.code

import io.github.shakelang.shake.parser.node.ShakeTree
import io.github.shakelang.shake.processor.program.creation.CreationShakeScope
import io.github.shakelang.shake.processor.program.creation.code.statements.CreationShakeStatement
import io.github.shakelang.shake.processor.program.types.code.ShakeCode

open class CreationShakeCode(
    override val statements: List<CreationShakeStatement>
): ShakeCode {

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

    override fun toJson(): Map<String, Any> {
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

