package io.github.shakelang.shake.processor.program.code

import io.github.shakelang.shake.parser.node.ShakeTree
import io.github.shakelang.shake.processor.program.*

interface ShakeStatement
interface ShakeValue {
    val type: ShakeType
}

open class ShakeCode(
    open val statements: List<ShakeStatement>
) {

    open class ShakeLateProcessCode (
        open val tree: ShakeTree
    ) : ShakeCode(emptyList()) {

        override lateinit var statements: List<ShakeStatement>

        fun process(scope: ShakeScope) {
            statements = tree.children.map {
                scope.processor.visitStatement(scope, it)
            }
        }
    }

    companion object {

        fun empty() = ShakeCode(emptyList())

        fun fromTree(tree: ShakeTree): ShakeCode {
            return ShakeLateProcessCode(tree)
        }
    }

}

