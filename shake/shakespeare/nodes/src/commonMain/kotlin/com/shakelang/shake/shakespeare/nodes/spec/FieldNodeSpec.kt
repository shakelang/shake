@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.nodes.spec

import com.shakelang.shake.parser.node.outer.ShakeFieldDeclarationNode
import com.shakelang.shake.shakespeare.spec.AccessModifier
import com.shakelang.shake.shakespeare.spec.FieldSpec
import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.NamespaceSpec

class FieldNodeSpec(
    name: NamespaceSpec,
    type: TypeNodeSpec,
    isVal: Boolean = true,
    isStatic: Boolean = false,
    isFinal: Boolean = false,
    accessModifier: AccessModifier = AccessModifier.PUBLIC,
    isSynchronized: Boolean = false,
    isNative: Boolean = false,
) : FieldSpec(
    name,
    type,
    isVal,
    isStatic,
    isFinal,
    accessModifier,
    isSynchronized,
    isNative,
),
    AbstractNodeSpec {
    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeFieldDeclarationNode {
    }
}
