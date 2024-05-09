@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.nodes.spec.code

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.shake.parser.node.ShakeStatementNode
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.misc.ShakeVariableType
import com.shakelang.shake.parser.node.statements.*
import com.shakelang.shake.shakespeare.nodes.spec.AbstractNodeSpec
import com.shakelang.shake.shakespeare.nodes.spec.NodeContext
import com.shakelang.shake.shakespeare.nodes.spec.TypeNodeSpec
import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.code.*

/**
 * A [StatementSpec] is a specification for a statement in the code
 * @since 0.1.0
 */
interface StatementNodeSpec : AbstractNodeSpec, StatementSpec {
    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeStatementNode

    companion object {
        fun of(spec: StatementSpec): StatementNodeSpec = when (spec) {
            is ValuedStatementSpec -> ValuedStatementNodeSpec.of(spec)
            is ReturnSpec -> ReturnNodeSpec.of(spec)
            is VariableDeclarationSpec -> VariableDeclarationNodeSpec.of(spec)
            is WhileSpec -> WhileNodeSpec.of(spec)
            is DoWhileSpec -> DoWhileNodeSpec.of(spec)
            is ForSpec -> ForNodeSpec.of(spec)
            is IfSpec -> IfNodeSpec.of(spec)
            else -> throw IllegalArgumentException("Unknown statement spec: $spec")
        }

        fun of(spec: VariableDeclarationSpec) = VariableDeclarationNodeSpec.of(spec)
        fun of(spec: WhileSpec) = WhileNodeSpec.of(spec)
        fun of(spec: DoWhileSpec) = DoWhileNodeSpec.of(spec)
        fun of(spec: ForSpec) = ForNodeSpec.of(spec)
        fun of(spec: IfSpec) = IfNodeSpec.of(spec)
        fun of(spec: ReturnSpec) = ReturnNodeSpec.of(spec)
    }
}

/**
 * A [VariableDeclarationSpec] is a specification for a variable declaration in the code
 * @param name The name of the variable
 * @param type The type of the variable
 * @param value The value of the variable
 * @since 0.1.0
 */
open class VariableDeclarationNodeSpec(
    name: String,
    type: TypeNodeSpec?,
    value: ValueNodeSpec?,
    isVal: Boolean = false,
) : VariableDeclarationSpec(name, type, value, isVal), StatementNodeSpec {

    override val type get() = super.type as TypeNodeSpec?
    override val value get() = super.value as ValueNodeSpec?

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeLocalDeclarationNode {
        val varToken = if (isVal) {
            nctx.createToken(ShakeTokenType.KEYWORD_VAL)
        } else {
            nctx.createToken(ShakeTokenType.KEYWORD_VAR)
        }

        nctx.space()

        val nameToken = nctx.createToken(ShakeTokenType.IDENTIFIER, name)

        var colonToken: ShakeToken? = null
        var type: ShakeVariableType? = null

        if (this.type != null) {
            colonToken = nctx.createToken(ShakeTokenType.COLON)
            nctx.space()
            type = this.type!!.dump(ctx, nctx)
        }

        var assignToken: ShakeToken? = null
        var value: ShakeValuedNode? = null

        if (this.value != null) {
            nctx.space()
            assignToken = nctx.createToken(ShakeTokenType.ASSIGN)
            nctx.space()
            value = this.value!!.dump(ctx, nctx)
        }

        return ShakeLocalDeclarationNode(
            nctx.map,
            nameToken,
            colonToken,
            type,
            assignToken,
            value,
            varToken,
        )
    }

    companion object {
        fun of(spec: VariableDeclarationSpec) = if (spec is VariableDeclarationNodeSpec) {
            spec
        } else {
            VariableDeclarationNodeSpec(
                spec.name,
                if (spec.type != null) TypeNodeSpec.of(spec.type!!) else null,
                if (spec.value != null) ValueNodeSpec.of(spec.value!!) else null,
                spec.isVal,
            )
        }
    }
}

private class Tuple4<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D) {
    operator fun component1() = first
    operator fun component2() = second
    operator fun component3() = third
    operator fun component4() = fourth
}

private fun statementBody(
    nctx: NodeContext,
    ctx: GenerationContext,
    bodySpec: CodeNodeSpec,
    conditionSpec: ValueNodeSpec,
): Tuple4<ShakeToken, ShakeValuedNode, ShakeToken, ShakeBlockNode> {
    nctx.space()
    val lp = nctx.createToken(ShakeTokenType.LPAREN)
    val condition = conditionSpec.dump(ctx, nctx)
    val rp = nctx.createToken(ShakeTokenType.RPAREN)
    nctx.space()
    val body = bodySpec.dump(ctx, nctx)
    return Tuple4(lp, condition, rp, body)
}

open class WhileNodeSpec(
    condition: ValueNodeSpec,
    body: CodeNodeSpec,
) : WhileSpec(condition, body), StatementNodeSpec {

    override val condition get() = super.condition as ValueNodeSpec
    override val body get() = super.body as CodeNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeWhileNode {
        val whileToken = nctx.createToken(ShakeTokenType.KEYWORD_WHILE)
        val (lp, condition, rp, body) = statementBody(nctx, ctx, body, condition)
        return ShakeWhileNode(nctx.map, body, condition, whileToken, lp, rp)
    }

    companion object {
        fun of(spec: WhileSpec) = if (spec is WhileNodeSpec) {
            spec
        } else {
            WhileNodeSpec(
                ValueNodeSpec.of(spec.condition),
                CodeNodeSpec.of(spec.body),
            )
        }
    }
}

open class DoWhileNodeSpec(
    body: CodeNodeSpec,
    condition: ValueNodeSpec,
) : DoWhileSpec(body, condition), StatementNodeSpec {

    override val condition get() = super.condition as ValueNodeSpec
    override val body get() = super.body as CodeNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeDoWhileNode {
        val doToken = nctx.createToken(ShakeTokenType.KEYWORD_DO, "do")
        nctx.space()
        val body = body.dump(ctx, nctx)
        nctx.space()
        val whileToken = nctx.createToken(ShakeTokenType.KEYWORD_WHILE, "while")
        nctx.space()
        val lp = nctx.createToken(ShakeTokenType.LPAREN)
        val condition = condition.dump(ctx, nctx)
        val rp = nctx.createToken(ShakeTokenType.RPAREN)
        return ShakeDoWhileNode(nctx.map, body, condition, doToken, whileToken, lp, rp)
    }

    companion object {
        fun of(spec: DoWhileSpec) = if (spec is DoWhileNodeSpec) {
            spec
        } else {
            DoWhileNodeSpec(
                CodeNodeSpec.of(spec.body),
                ValueNodeSpec.of(spec.condition),
            )
        }
    }
}

open class ForNodeSpec(
    init: StatementNodeSpec,
    condition: ValueNodeSpec,
    update: StatementNodeSpec,
    body: CodeNodeSpec,
) : ForSpec(init, condition, update, body), StatementNodeSpec {

    override val init get() = super.init as StatementNodeSpec
    override val condition get() = super.condition as ValueNodeSpec
    override val update get() = super.update as StatementNodeSpec
    override val body get() = super.body as CodeNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeForNode {
        val forToken = nctx.createToken(ShakeTokenType.KEYWORD_FOR)
        nctx.space()
        val lp = nctx.createToken(ShakeTokenType.LPAREN)
        val init = init.dump(ctx, nctx)
        val semicolon1 = nctx.createToken(ShakeTokenType.SEMICOLON)
        nctx.space()
        val condition = condition.dump(ctx, nctx)
        val semicolon2 = nctx.createToken(ShakeTokenType.SEMICOLON)
        nctx.space()
        val update = update.dump(ctx, nctx)
        val rp = nctx.createToken(ShakeTokenType.RPAREN)
        nctx.space()
        val body = body.dump(ctx, nctx)
        return ShakeForNode(
            nctx.map,
            body,
            init,
            condition,
            update,
            forToken,
            lp,
            semicolon1,
            semicolon2,
            rp,
        )
    }

    companion object {
        fun of(spec: ForSpec) = if (spec is ForNodeSpec) {
            spec
        } else {
            ForNodeSpec(
                StatementNodeSpec.of(spec.init),
                ValueNodeSpec.of(spec.condition),
                StatementNodeSpec.of(spec.update),
                CodeNodeSpec.of(spec.body),
            )
        }
    }
}

open class IfNodeSpec(
    condition: ValueNodeSpec,
    body: CodeNodeSpec,
    elseBody: CodeNodeSpec?,
) : IfSpec(condition, body, elseBody), StatementNodeSpec {

    override val condition get() = super.condition as ValueNodeSpec
    override val thenBody get() = super.thenBody as CodeNodeSpec
    override val elseBody get() = super.elseBody as CodeNodeSpec?

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeIfNode {
        val ifToken = nctx.createToken(ShakeTokenType.KEYWORD_IF)
        val (lp, condition, rp, body) = statementBody(nctx, ctx, thenBody, condition)
        if (elseBody != null) {
            nctx.space()
            val elseToken = nctx.createToken(ShakeTokenType.KEYWORD_ELSE)
            nctx.space()
            val elseBody = elseBody!!.dump(ctx, nctx)
            return ShakeIfNode(nctx.map, body, elseBody, condition, ifToken, lp, rp, elseToken)
        }
        return ShakeIfNode(nctx.map, body, null, condition, ifToken, lp, rp, null)
    }

    companion object {
        fun of(spec: IfSpec) = if (spec is IfNodeSpec) {
            spec
        } else {
            IfNodeSpec(
                ValueNodeSpec.of(spec.condition),
                CodeNodeSpec.of(spec.thenBody),
                spec.elseBody?.let { CodeNodeSpec.of(it) },
            )
        }
    }
}

open class ReturnNodeSpec(
    value: ValueNodeSpec?,
) : ReturnSpec(value), StatementNodeSpec {

    override val value get() = super.value as ValueNodeSpec?

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeReturnNode {
        val returnToken = nctx.createToken(ShakeTokenType.KEYWORD_RETURN)

        val value = if (value != null) {
            nctx.space()
            value!!.dump(ctx, nctx)
        } else {
            null
        }

        return ShakeReturnNode(nctx.map, value, returnToken)
    }

    companion object {
        fun of(spec: ReturnSpec) = if (spec is ReturnNodeSpec) {
            spec
        } else {
            ReturnNodeSpec(spec.value?.let { ValueNodeSpec.of(it) })
        }
    }
}

fun StatementSpec.toNodeSpec(): StatementNodeSpec = StatementNodeSpec.of(this)
fun VariableDeclarationSpec.toNodeSpec(): VariableDeclarationNodeSpec = VariableDeclarationNodeSpec.of(this)
fun WhileSpec.toNodeSpec(): WhileNodeSpec = WhileNodeSpec.of(this)
fun DoWhileSpec.toNodeSpec(): DoWhileNodeSpec = DoWhileNodeSpec.of(this)
fun ForSpec.toNodeSpec(): ForNodeSpec = ForNodeSpec.of(this)
fun IfSpec.toNodeSpec(): IfNodeSpec = IfNodeSpec.of(this)
fun ReturnSpec.toNodeSpec(): ReturnNodeSpec = ReturnNodeSpec.of(this)
