package com.shakelang.shake.shakespeare.nodes.spec

import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.shake.parser.node.misc.ShakeNamespaceNode
import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.NamespaceSpec

class NamespaceNodeSpec(
    vararg name: String,
) : NamespaceSpec(*name), AbstractNodeSpec {
    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeNamespaceNode {
        if (name.isEmpty()) throw IllegalStateException("Namespace must have at least one part")
        nctx.print(name[0])

        var node = ShakeNamespaceNode(
            nctx.map,
            nctx.createToken(ShakeTokenType.IDENTIFIER, name[0]),
            null,
            null,
        )

        name.toList().subList(1, name.size).forEach {
            nctx.print(".")
            val dot = nctx.createToken(ShakeTokenType.DOT)
            nctx.print(it)
            node = ShakeNamespaceNode(
                nctx.map,
                dot,
                node,
                nctx.createToken(ShakeTokenType.IDENTIFIER, it),
            )
        }

        return node
    }
}
