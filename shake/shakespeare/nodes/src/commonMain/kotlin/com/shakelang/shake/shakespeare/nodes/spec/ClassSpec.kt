@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.nodes.spec

import com.shakelang.shake.shakespeare.nodes.spec.code.CodeNodeSpec
import com.shakelang.shake.shakespeare.spec.*

open class ConstructorNodeSpec(
    parameters: List<ParameterNodeSpec>,
    body: CodeNodeSpec,
    name: Identifier? = null,
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
    AbstractNodeSpec

interface ClassLikeNodeSpec : AbstractNodeSpec, ClassLikeSpec

open class ClassNodeSpec(
    name: Identifier,
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
    name: Identifier,
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
    name: Identifier,
    constants: List<Identifier>,
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
    name: Identifier,
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
