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
import com.shakelang.shake.parser.node.misc.ShakeAccessDescriber
import com.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
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

        if (access != ShakeAccessDescriber.PACKAGE) nctx.print(" ")

        val nativeToken = if (isNative) nctx.createToken(ShakeTokenType.KEYWORD_NATIVE) else null
        if (nativeToken != null) nctx.print(" ")

        val synchronizedToken = if (isSynchronized) nctx.createToken(ShakeTokenType.KEYWORD_SYNCHRONIZED) else null
        if (synchronizedToken != null) nctx.print(" ")

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

        return ShakeConstructorDeclarationNode(
            nctx.map,
            name,
            body,
            parameters.toTypedArray(),
            access,
            constructorToken,
            lp,
            rp,
            nativeToken,
            synchronizedToken,
            commaTokens.toTypedArray(),
        )
    }
}

interface ClassLikeNodeSpec : AbstractNodeSpec, ClassLikeSpec

@Suppress("UNCHECKED_CAST")
open class ClassNodeSpec(
    name: String,
    methods: List<MethodNodeSpec>,
    fields: List<FieldNodeSpec>,
    classes: List<ClassNodeSpec>,
    constructors: List<ConstructorNodeSpec>,
    isAbstract: Boolean = false,
    isFinal: Boolean = false,
    isStatic: Boolean = false,
    isNative: Boolean = false,
    accessModifier: AccessModifier = AccessModifier.PUBLIC,
) : ClassSpec(
    name,
    methods,
    fields,
    classes,
    constructors,
    isAbstract,
    isFinal,
    isStatic,
    isNative,
    accessModifier,
),
    ClassLikeNodeSpec {

    override val methods: List<MethodNodeSpec> get() = super.methods as List<MethodNodeSpec>
    override val fields: List<FieldNodeSpec> get() = super.fields as List<FieldNodeSpec>
    override val classes: List<ClassNodeSpec> get() = super.classes as List<ClassNodeSpec>
    override val constructors: List<ConstructorNodeSpec> get() = super.constructors as List<ConstructorNodeSpec>

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeClassDeclarationNode {
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
        if (access != ShakeAccessDescriber.PACKAGE) nctx.print(" ")

        val abstractToken = if (isAbstract) nctx.createToken(ShakeTokenType.KEYWORD_ABSTRACT) else null
        if (abstractToken != null) nctx.print(" ")

        val finalToken = if (isFinal) nctx.createToken(ShakeTokenType.KEYWORD_FINAL) else null
        if (finalToken != null) nctx.print(" ")

        val staticToken = if (isStatic) nctx.createToken(ShakeTokenType.KEYWORD_STATIC) else null
        if (staticToken != null) nctx.print(" ")

        val nativeToken = if (isNative) nctx.createToken(ShakeTokenType.KEYWORD_NATIVE) else null
        if (nativeToken != null) nctx.print(" ")

        val classToken = nctx.createToken(ShakeTokenType.KEYWORD_CLASS)
        nctx.print(" ")

        val nameToken = nctx.createToken(ShakeTokenType.IDENTIFIER, name)
        nctx.print(" ")

        val lc = nctx.createToken(ShakeTokenType.LCURL)

        val indented = ctx.indent()
        val fields = fields.map {
            nctx.print("\n")
            for (i in 0 until indented.indentLevel) nctx.print(indented.indentType)
            it.dump(ctx, nctx)
        }

        val constructors = constructors.map {
            nctx.print("\n")
            for (i in 0 until indented.indentLevel) nctx.print(indented.indentType)
            it.dump(ctx, nctx)
        }

        val methods = methods.map {
            nctx.print("\n")
            for (i in 0 until indented.indentLevel) nctx.print(indented.indentType)
            it.dump(ctx, nctx)
        }

        val classes = classes.map {
            nctx.print("\n")
            for (i in 0 until indented.indentLevel) nctx.print(indented.indentType)
            it.dump(ctx, nctx)
        }

        nctx.print("\n")

        val rc = nctx.createToken(ShakeTokenType.RCURL)

        return ShakeClassDeclarationNode(
            nctx.map,
            classToken,
            nameToken,
            emptyArray(),
            fields.toTypedArray(),
            methods.toTypedArray(),
            classes.toTypedArray(),
            constructors.toTypedArray(),
            access,
            staticToken,
            finalToken,
            abstractToken,
            nativeToken,
        )
    }
}

class InterfaceNodeSpec(
    name: String,
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
    name: String,
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
    name: String,
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
