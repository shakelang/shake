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
            is ReturnSpec -> ReturnNodeSpec(spec.value.let { ValueNodeSpec.of(it) })
            is VariableDeclarationSpec -> VariableDeclarationNodeSpec.of(spec)
            is WhileSpec -> WhileNodeSpec.of(spec)
            is DoWhileSpec -> DoWhileNodeSpec.of(spec)
            is ForSpec -> ForNodeSpec.of(spec)
            else -> throw IllegalArgumentException("Unknown statement spec: $spec")
        }
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
    isVal: Boolean = true,
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

        nctx.space()

        var assignToken: ShakeToken? = null
        var value: ShakeValuedNode? = null

        if (this.value != null) {
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
        fun of(spec: VariableDeclarationSpec) = VariableDeclarationNodeSpec(
            spec.name,
            if (spec.type != null) TypeNodeSpec.of(spec.type!!) else null,
            if (spec.value != null) ValueNodeSpec.of(spec.value!!) else null,
            spec.isVal,
        )
    }
}

open class WhileNodeSpec(
    condition: ValueNodeSpec,
    body: CodeNodeSpec,
) : WhileSpec(condition, body), StatementNodeSpec {

    override val condition get() = super.condition as ValueNodeSpec
    override val body get() = super.body as CodeNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeWhileNode {
        val whileToken = nctx.createToken(ShakeTokenType.KEYWORD_WHILE)
        val lp = nctx.createToken(ShakeTokenType.LPAREN)
        val condition = condition.dump(ctx, nctx)
        val rp = nctx.createToken(ShakeTokenType.RPAREN)
        nctx.space()
        val body = body.dump(ctx, nctx)
        return ShakeWhileNode(nctx.map, body, condition, whileToken, lp, rp)
    }

    companion object {
        fun of(spec: WhileSpec) = WhileNodeSpec(
            ValueNodeSpec.of(spec.condition),
            CodeNodeSpec.of(spec.body),
        )
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
        val lp = nctx.createToken(ShakeTokenType.LPAREN)
        val condition = condition.dump(ctx, nctx)
        val rp = nctx.createToken(ShakeTokenType.RPAREN)
        return ShakeDoWhileNode(nctx.map, body, condition, doToken, whileToken, lp, rp)
    }

    companion object {
        fun of(spec: DoWhileSpec) = DoWhileNodeSpec(
            CodeNodeSpec.of(spec.body),
            ValueNodeSpec.of(spec.condition),
        )
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
        val lp = nctx.createToken(ShakeTokenType.LPAREN)
        val init = init.dump(ctx, nctx)
        val semicolon1 = nctx.createToken(ShakeTokenType.SEMICOLON)
        val condition = condition.dump(ctx, nctx)
        val semicolon2 = nctx.createToken(ShakeTokenType.SEMICOLON)
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
        fun of(spec: ForSpec) = ForNodeSpec(
            StatementNodeSpec.of(spec.init),
            ValueNodeSpec.of(spec.condition),
            StatementNodeSpec.of(spec.update),
            CodeNodeSpec.of(spec.body),
        )
    }
}

open class IfNodeSpec(
    condition: ValueNodeSpec,
    body: CodeNodeSpec,
    elseBody: CodeNodeSpec?,
) : IfSpec(condition, body, elseBody), StatementNodeSpec {

    override val condition get() = super.condition as ValueNodeSpec
    override val body get() = super.body as CodeNodeSpec
    override val elseBody get() = super.elseBody as CodeNodeSpec?

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeIfNode {
        val ifToken = nctx.createToken(ShakeTokenType.KEYWORD_IF)
        val lp = nctx.createToken(ShakeTokenType.LPAREN)
        val condition = condition.dump(ctx, nctx)
        val rp = nctx.createToken(ShakeTokenType.RPAREN)
        nctx.space()
        val body = body.dump(ctx, nctx)
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
        fun of(spec: IfSpec) = IfNodeSpec(
            ValueNodeSpec.of(spec.condition),
            CodeNodeSpec.of(spec.body),
            spec.elseBody?.let { CodeNodeSpec.of(it) },
        )
    }
}

open class ReturnNodeSpec(
    value: ValueNodeSpec,
) : ReturnSpec(value), StatementNodeSpec {

    override val value get() = super.value as ValueNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeReturnNode {
        val returnToken = nctx.createToken(ShakeTokenType.KEYWORD_RETURN)
        nctx.space()
        val value = value.dump(ctx, nctx)
        return ShakeReturnNode(nctx.map, value, returnToken)
    }
}
