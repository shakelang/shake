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
        val access = createAccessModifier(accessModifier, nctx)

        val nativeToken = if (isNative) nctx.createToken(ShakeTokenType.KEYWORD_NATIVE) else null
        if (nativeToken != null) nctx.space()

        val synchronizedToken = if (isSynchronized) nctx.createToken(ShakeTokenType.KEYWORD_SYNCHRONIZED) else null
        if (synchronizedToken != null) nctx.space()

        val constructorToken = nctx.createToken(ShakeTokenType.KEYWORD_CONSTRUCTOR)

        val name = name?.let {
            nctx.space()
            if (it.name.size != 1) throw Exception("Name of constructor must be a single identifier")
            nctx.createToken(ShakeTokenType.IDENTIFIER, it.name[0])
        }

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
        val access = createAccessModifier(accessModifier, nctx)

        val abstractToken = if (isAbstract) nctx.createToken(ShakeTokenType.KEYWORD_ABSTRACT) else null
        if (abstractToken != null) nctx.space()

        val finalToken = if (isFinal) nctx.createToken(ShakeTokenType.KEYWORD_FINAL) else null
        if (finalToken != null) nctx.space()

        val staticToken = if (isStatic) nctx.createToken(ShakeTokenType.KEYWORD_STATIC) else null
        if (staticToken != null) nctx.space()

        val nativeToken = if (isNative) nctx.createToken(ShakeTokenType.KEYWORD_NATIVE) else null
        if (nativeToken != null) nctx.space()

        val classToken = nctx.createToken(ShakeTokenType.KEYWORD_CLASS)
        nctx.space()

        val nameToken = nctx.createToken(ShakeTokenType.IDENTIFIER, name)
        nctx.space()

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

@Suppress("UNCHECKED_CAST")
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
    ClassLikeNodeSpec {
    override val methods: List<MethodNodeSpec> get() = super.methods as List<MethodNodeSpec>
    override val fields: List<FieldNodeSpec> get() = super.fields as List<FieldNodeSpec>
    override val classes: List<ClassNodeSpec> get() = super.classes as List<ClassNodeSpec>

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeClassDeclarationNode {
        val access = createAccessModifier(accessModifier, nctx)

        val interfaceToken = nctx.createToken(ShakeTokenType.KEYWORD_INTERFACE)
        nctx.space()

        val nameToken = nctx.createToken(ShakeTokenType.IDENTIFIER, name)
        nctx.space()

        val lc = nctx.createToken(ShakeTokenType.LCURL)

        val indented = ctx.indent()
        val fields = fields.map {
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
            interfaceToken,
            nameToken,
            emptyArray(),
            fields.toTypedArray(),
            methods.toTypedArray(),
            classes.toTypedArray(),
            emptyArray(),
            access,
            null,
            null,
            null,
            null,
        )
    }
}

@Suppress("UNCHECKED_CAST")
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
    ClassLikeNodeSpec {
    override val constants: List<NamespaceNodeSpec> get() = super.constants as List<NamespaceNodeSpec>
    override val methods: List<MethodNodeSpec> get() = super.methods as List<MethodNodeSpec>
    override val fields: List<FieldNodeSpec> get() = super.fields as List<FieldNodeSpec>
    override val classes: List<ClassNodeSpec> get() = super.classes as List<ClassNodeSpec>

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeClassDeclarationNode {
        TODO()
    }
}

@Suppress("UNCHECKED_CAST")
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
    ClassLikeNodeSpec {
    override val methods: List<MethodNodeSpec> get() = super.methods as List<MethodNodeSpec>
    override val fields: List<FieldNodeSpec> get() = super.fields as List<FieldNodeSpec>
    override val classes: List<ClassNodeSpec> get() = super.classes as List<ClassNodeSpec>

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeClassDeclarationNode {
        TODO()
    }
}
