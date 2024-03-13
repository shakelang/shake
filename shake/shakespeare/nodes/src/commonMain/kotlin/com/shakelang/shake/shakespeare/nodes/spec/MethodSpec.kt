@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.nodes.spec

import com.shakelang.shake.parser.node.outer.ShakeMethodDeclarationNode
import com.shakelang.shake.parser.node.outer.ShakeParameterNode
import com.shakelang.shake.shakespeare.nodes.spec.code.CodeNodeSpec
import com.shakelang.shake.shakespeare.spec.*

class ParameterNodeSpec(
    name: String,
    type: TypeNodeSpec,
) : ParameterSpec(name, type), AbstractNodeSpec {

    override val type get() = super.type as TypeNodeSpec

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeParameterNode {
    }
}

class MethodNodeSpec(
    name: NamespaceSpec,
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
),
    AbstractNodeSpec {
    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeMethodDeclarationNode {
    }
}
