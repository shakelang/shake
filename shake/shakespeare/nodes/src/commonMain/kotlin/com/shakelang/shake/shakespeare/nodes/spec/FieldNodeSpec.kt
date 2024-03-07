@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.nodes.spec

import com.shakelang.shake.shakespeare.spec.AccessModifier
import com.shakelang.shake.shakespeare.spec.FieldSpec
import com.shakelang.shake.shakespeare.spec.NamespaceSpec

class FieldNodeSpec(
    name: NamespaceSpec,
    type: TypeNode,
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
    AbstractNodeSpec
