@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.nodes.spec.code

import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.shake.parser.node.ShakeStatementNode
import com.shakelang.shake.parser.node.statements.ShakeBlockNode
import com.shakelang.shake.shakespeare.nodes.spec.AbstractNodeSpec
import com.shakelang.shake.shakespeare.nodes.spec.NodeContext
import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.code.CodeSpec

open class CodeNodeSpec(
    statements: List<StatementNodeSpec>,
) : CodeSpec(statements), AbstractNodeSpec {
    override val statements = statements as List<StatementNodeSpec>

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeBlockNode {
        val nodes = mutableListOf<ShakeStatementNode>()
        val lcurl = nctx.createToken(ShakeTokenType.LCURL)
        val indented = ctx.indent()
        for (statement in statements) {
            nctx.print("\n")
            for (i in 0 until indented.indentLevel) nctx.print(ctx.indentType)
            nodes.add(statement.dump(indented, nctx))
        }
        nctx.print("\n")
        for (i in 0 until ctx.indentLevel) nctx.print(ctx.indentType)
        val rcurl = nctx.createToken(ShakeTokenType.RCURL)
        return ShakeBlockNode(nctx.map, nodes.toTypedArray(), lcurl, rcurl)
    }
}
