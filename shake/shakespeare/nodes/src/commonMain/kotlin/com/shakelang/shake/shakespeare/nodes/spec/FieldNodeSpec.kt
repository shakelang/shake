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
import com.shakelang.shake.parser.node.misc.ShakeAccessDescriber
import com.shakelang.shake.parser.node.misc.ShakeVariableType
import com.shakelang.shake.parser.node.outer.ShakeFieldDeclarationNode
import com.shakelang.shake.shakespeare.nodes.spec.code.ValueNodeSpec
import com.shakelang.shake.shakespeare.spec.AccessModifier
import com.shakelang.shake.shakespeare.spec.FieldSpec
import com.shakelang.shake.shakespeare.spec.GenerationContext

class FieldNodeSpec(
    name: String,
    type: TypeNodeSpec?,
    isVal: Boolean = true,
    isStatic: Boolean = false,
    isFinal: Boolean = false,
    accessModifier: AccessModifier = AccessModifier.PUBLIC,
    isNative: Boolean = false,
    isConst: Boolean = false,
    isOverride: Boolean = false,
    isInline: Boolean = false,
    value: ValueNodeSpec? = null,
) : FieldSpec(
    name,
    type,
    isVal,
    isStatic,
    isFinal,
    accessModifier,
    isNative,
    isConst,
    isOverride,
    isInline,
    value,
),
    AbstractNodeSpec {

    override val type: TypeNodeSpec? get() = super.type as TypeNodeSpec?
    override val value: ValueNodeSpec? get() = super.value as ValueNodeSpec?

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeFieldDeclarationNode {
        val access = createAccessModifier(accessModifier, nctx)

        val nativeToken = createNativeFlagToken(nctx, isNative)
        val constToken = createConstFlagToken(nctx, isConst)
        val overrideToken = createOverrideFlagToken(nctx, isOverride)
        val staticToken = createStaticFlagToken(nctx, isStatic)
        val finalToken = createFinalFlagToken(nctx, isFinal)
        val inlineToken = createInlineFlagToken(nctx, isInline)

        val varToken = createValVarToken(nctx, !isVal)
        nctx.space()

        val nameToken = nctx.createToken(ShakeTokenType.IDENTIFIER, name)

        var colonToken: ShakeToken? = null
        var typeNode: ShakeVariableType? = null

        if (type != null) {
            colonToken = nctx.createToken(ShakeTokenType.COLON)
            typeNode = type!!.dump(ctx, nctx)
        }

        var assignmentToken: ShakeToken? = null
        var valueNode: ShakeValuedNode? = null

        if (value != null) {
            nctx.space()
            assignmentToken = nctx.createToken(ShakeTokenType.ASSIGN)
            valueNode = value!!.dump(ctx, nctx)
        }

        return ShakeFieldDeclarationNode(
            nctx.map,
            null,
            null,
            nameToken,
            typeNode,
            valueNode,
            access,
            varToken,
            staticToken,
            finalToken,
            nativeToken,
            constToken,
            overrideToken,
            inlineToken,
        )
    }
}

fun createAccessModifier(accessModifier: AccessModifier, nctx: NodeContext): ShakeAccessDescriber {
    val access = when (accessModifier) {
        AccessModifier.PUBLIC -> {
            ShakeAccessDescriber(nctx.createToken(ShakeTokenType.KEYWORD_PUBLIC))
        }

        AccessModifier.PRIVATE -> {
            ShakeAccessDescriber(nctx.createToken(ShakeTokenType.KEYWORD_PRIVATE))
        }

        AccessModifier.PROTECTED -> {
            ShakeAccessDescriber(nctx.createToken(ShakeTokenType.KEYWORD_PROTECTED))
        }

        AccessModifier.PACKAGE_PRIVATE -> {
            ShakeAccessDescriber.PACKAGE
        }
    }
    if (access != ShakeAccessDescriber.PACKAGE) nctx.space()
    return access
}

fun createFlagToken(
    nctx: NodeContext,
    b: Boolean,
    tt: ShakeTokenType,
): ShakeToken? {
    val token = if (b) nctx.createToken(tt) else null
    if (token != null) nctx.space()
    return token
}

fun createNativeFlagToken(
    nctx: NodeContext,
    b: Boolean,
): ShakeToken? {
    val token = if (b) nctx.createToken(ShakeTokenType.KEYWORD_NATIVE) else null
    if (token != null) nctx.space()
    return token
}

fun createSynchronizedFlagToken(
    nctx: NodeContext,
    b: Boolean,
): ShakeToken? {
    val token = if (b) nctx.createToken(ShakeTokenType.KEYWORD_SYNCHRONIZED) else null
    if (token != null) nctx.space()
    return token
}

fun createConstFlagToken(
    nctx: NodeContext,
    b: Boolean,
): ShakeToken? {
    val token = if (b) nctx.createToken(ShakeTokenType.KEYWORD_CONST) else null
    if (token != null) nctx.space()
    return token
}

fun createOverrideFlagToken(
    nctx: NodeContext,
    b: Boolean,
): ShakeToken? {
    val token = if (b) nctx.createToken(ShakeTokenType.KEYWORD_OVERRIDE) else null
    if (token != null) nctx.space()
    return token
}

fun createInlineFlagToken(
    nctx: NodeContext,
    b: Boolean,
): ShakeToken? {
    val token = if (b) nctx.createToken(ShakeTokenType.KEYWORD_INLINE) else null
    if (token != null) nctx.space()
    return token
}

fun createStaticFlagToken(
    nctx: NodeContext,
    b: Boolean,
): ShakeToken? {
    val token = if (b) nctx.createToken(ShakeTokenType.KEYWORD_STATIC) else null
    if (token != null) nctx.space()
    return token
}

fun createFinalFlagToken(
    nctx: NodeContext,
    b: Boolean,
): ShakeToken? {
    val token = if (b) nctx.createToken(ShakeTokenType.KEYWORD_FINAL) else null
    if (token != null) nctx.space()
    return token
}

fun createValVarToken(
    nctx: NodeContext,
    b: Boolean,
): ShakeToken {
    return if (b) nctx.createToken(ShakeTokenType.KEYWORD_VAL) else nctx.createToken(ShakeTokenType.KEYWORD_VAR)
}

fun createAbstractFlagToken(
    nctx: NodeContext,
    b: Boolean,
): ShakeToken? {
    val token = if (b) nctx.createToken(ShakeTokenType.KEYWORD_ABSTRACT) else null
    if (token != null) nctx.space()
    return token
}

fun createOperatorFlagToken(
    nctx: NodeContext,
    b: Boolean,
): ShakeToken? {
    val token = if (b) nctx.createToken(ShakeTokenType.KEYWORD_OPERATOR) else null
    if (token != null) nctx.space()
    return token
}
