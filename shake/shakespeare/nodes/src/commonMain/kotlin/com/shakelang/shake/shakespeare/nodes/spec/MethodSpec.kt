@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.nodes.spec

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.misc.ShakeVariableType
import com.shakelang.shake.parser.node.outer.ShakeMethodDeclarationNode
import com.shakelang.shake.parser.node.outer.ShakeParameterNode
import com.shakelang.shake.shakespeare.nodes.spec.code.CodeNodeSpec
import com.shakelang.shake.shakespeare.nodes.spec.code.ValueNodeSpec
import com.shakelang.shake.shakespeare.spec.*

class ParameterNodeSpec(
    name: String,
    type: TypeNodeSpec,
    defaultValue: ValueNodeSpec?,
) : ParameterSpec(name, type, defaultValue), AbstractNodeSpec {

    override val type get() = super.type as TypeNodeSpec
    override val defaultValue: ValueNodeSpec?
        get() = super.defaultValue as ValueNodeSpec?

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeParameterNode {
        val identifierToken = nctx.createToken(ShakeTokenType.IDENTIFIER, name)
        val colonToken = nctx.createToken(ShakeTokenType.COLON)
        val type = type.dump(ctx, nctx)

        var defaultValue: ShakeValuedNode? = null
        var assignToken: ShakeToken? = null

        if (this.defaultValue != null) {
            assignToken = nctx.createToken(ShakeTokenType.ASSIGN)
            defaultValue = this.defaultValue!!.dump(ctx, nctx)
        }

        return ShakeParameterNode(
            nctx.map,
            identifierToken,
            colonToken,
            type,
            defaultValue,
            assignToken,
        )
    }
}

class MethodNodeSpec(
    name: String,
    returnType: TypeNodeSpec,
    extending: TypeNodeSpec? = null,
    parameters: List<ParameterNodeSpec>,
    body: CodeNodeSpec?,
    isStatic: Boolean = false,
    isAbstract: Boolean = false,
    isFinal: Boolean = false,
    isOverride: Boolean = false,
    isOperator: Boolean = false,
    accessModifier: AccessModifier = AccessModifier.PUBLIC,
    isSynchronized: Boolean = false,
    isNative: Boolean = false,
    isInline: Boolean = false,
) : MethodSpec(
    name,
    returnType,
    extending,
    parameters,
    body,
    isStatic,
    isAbstract,
    isFinal,
    isOverride,
    isOperator,
    accessModifier,
    isSynchronized,
    isNative,
    isInline,
),
    AbstractNodeSpec {

    override val returnType get() = super.returnType as TypeNodeSpec
    override val extending: TypeNodeSpec?
        get() = super.extending as TypeNodeSpec?

    @Suppress("UNCHECKED_CAST")
    override val parameters: List<ParameterNodeSpec>
        get() = super.parameters as List<ParameterNodeSpec>

    override val body: CodeNodeSpec?
        get() = super.body as CodeNodeSpec?

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeMethodDeclarationNode {
        val access = createAccessModifier(accessModifier, nctx)

        val staticToken = createStaticFlagToken(nctx, isStatic)
        val abstractToken = createAbstractFlagToken(nctx, isAbstract)
        val finalToken = createFinalFlagToken(nctx, isFinal)
        val overrideToken = createOverrideFlagToken(nctx, isOverride)
        val operatorToken = createOperatorFlagToken(nctx, isOperator)
        val synchronizedToken = createSynchronizedFlagToken(nctx, isSynchronized)
        val nativeToken = createNativeFlagToken(nctx, isNative)
        val inlineToken = createInlineFlagToken(nctx, isInline)

        val funToken = nctx.createToken(ShakeTokenType.KEYWORD_FUN)

        var extending: ShakeVariableType? = null
        var dotToken: ShakeToken? = null

        if (this.extending != null) {
            extending = this.extending!!.dump(ctx, nctx)
            dotToken = nctx.createToken(ShakeTokenType.DOT)
        }

        val nameToken = nctx.createToken(ShakeTokenType.IDENTIFIER, name)

        val lp = nctx.createToken(ShakeTokenType.LPAREN)

        val commaTokens = mutableListOf<ShakeToken>()
        val parameters = parameters.map {
            val p = it.dump(ctx, nctx)
            if (it != parameters.last()) {
                commaTokens.add(nctx.createToken(ShakeTokenType.COMMA))
                nctx.space()
            }
            p
        }

        val rp = nctx.createToken(ShakeTokenType.RPAREN)

        val colonToken: ShakeToken = nctx.createToken(ShakeTokenType.COLON)
        val returnType: ShakeVariableType = this.returnType.dump(ctx, nctx)

        nctx.space()
        val body = body?.dump(ctx, nctx)

        return ShakeMethodDeclarationNode(
            nctx.map,
            null,
            nameToken,
            body,
            parameters.toTypedArray(),
            returnType,
            access,
            staticToken,
            finalToken,
            abstractToken,
            overrideToken,
            synchronizedToken,
            nativeToken,
            operatorToken,
            inlineToken,
            funToken,
            lp,
            rp,
            colonToken,
            commaTokens.toTypedArray(),
            null,
        )
    }
}
