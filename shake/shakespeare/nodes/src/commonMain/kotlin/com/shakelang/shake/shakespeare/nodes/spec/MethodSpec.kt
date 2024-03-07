@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.nodes.spec

import com.shakelang.shake.shakespeare.nodes.spec.code.CodeNodeSpec
import com.shakelang.shake.shakespeare.spec.AccessModifier
import com.shakelang.shake.shakespeare.spec.MethodSpec
import com.shakelang.shake.shakespeare.spec.NamespaceSpec
import com.shakelang.shake.shakespeare.spec.ParameterSpec

class ParameterNodeSpec(
    name: NamespaceSpec,
    type: TypeNode,
) : ParameterSpec(name, type), AbstractNodeSpec

class MethodNodeSpec(
    name: NamespaceSpec,
    returnType: TypeNode,
    extending: TypeNode? = null,
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
    AbstractNodeSpec
