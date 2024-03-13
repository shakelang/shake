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
import com.shakelang.shake.parser.node.objects.ShakeConstructorDeclarationNode
import com.shakelang.shake.shakespeare.nodes.spec.code.CodeNodeSpec
import com.shakelang.shake.shakespeare.spec.*

open class ConstructorNodeSpec(
    parameters: List<ParameterNodeSpec>,
    body: CodeNodeSpec,
    name: NamespaceSpec? = null,
    accessModifier: AccessModifier = AccessModifier.PUBLIC,
    isSynchronized: Boolean = false,
    isNative: Boolean = false,
) : ConstructorSpec(
    parameters,
    body,
    name,
    accessModifier,
    isSynchronized,
    isNative,
),
    AbstractNodeSpec {

    @Suppress("UNCHECKED_CAST")
    override val parameters: List<ParameterNodeSpec> get() = super.parameters as List<ParameterNodeSpec>
    override val body: CodeNodeSpec get() = super.body as CodeNodeSpec
    override val name: NamespaceNodeSpec? get() = super.name as NamespaceNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeConstructorDeclarationNode {
        val constructorToken = nctx.createToken(ShakeTokenType.KEYWORD_CONSTRUCTOR)

        val name = name?.let {
            nctx.print(" ")
            if (it.name.size != 1) throw Exception("Name of constructor must be a single identifier")
            nctx.createToken(ShakeTokenType.IDENTIFIER, it.name[0])
        }

        val lp = nctx.createToken(ShakeTokenType.LPAREN)

        val commaTokens = mutableListOf<ShakeToken>()

        val parameters = parameters.map {
            val p = it.dump(ctx, nctx)
            if (it != parameters.last()) {
                commaTokens.add(nctx.createToken(ShakeTokenType.COMMA))
                nctx.print(" ")
            }
            p
        }

        val rp = nctx.createToken(ShakeTokenType.RPAREN)

        val body = body.dump(ctx, nctx)
    }
}

interface ClassLikeNodeSpec : AbstractNodeSpec, ClassLikeSpec

open class ClassNodeSpec(
    name: NamespaceSpec,
    methods: List<MethodNodeSpec>,
    fields: List<FieldNodeSpec>,
    classes: List<ClassNodeSpec>,
    constructors: List<ConstructorNodeSpec>,
    isAbstract: Boolean = false,
    isFinal: Boolean = false,
    accessModifier: AccessModifier = AccessModifier.PUBLIC,
) : ClassSpec(
    name,
    methods,
    fields,
    classes,
    constructors,
    isAbstract,
    isFinal,
    accessModifier,
),
    ClassLikeNodeSpec

class InterfaceNodeSpec(
    name: NamespaceSpec,
    methods: List<MethodNodeSpec>,
    fields: List<FieldNodeSpec>,
    classes: List<ClassNodeSpec>,
    accessModifier: AccessModifier = AccessModifier.PUBLIC,
) : InterfaceSpec(
    name,
    methods,
    fields,
    classes,
    accessModifier,
),
    ClassLikeNodeSpec

class EnumNodeSpec(
    name: NamespaceSpec,
    constants: List<NamespaceSpec>,
    methods: List<MethodNodeSpec>,
    fields: List<FieldNodeSpec>,
    classes: List<ClassNodeSpec>,
    accessModifier: AccessModifier = AccessModifier.PUBLIC,
) : EnumSpec(
    name,
    constants,
    methods,
    fields,
    classes,
    accessModifier,
),
    ClassLikeNodeSpec

class ObjectNodeSpec(
    name: NamespaceSpec,
    methods: List<MethodSpec>,
    fields: List<FieldNodeSpec>,
    classes: List<ClassNodeSpec>,
    accessModifier: AccessModifier = AccessModifier.PUBLIC,
) : ObjectSpec(
    name,
    methods,
    fields,
    classes,
    accessModifier,
),
    ClassLikeNodeSpec
