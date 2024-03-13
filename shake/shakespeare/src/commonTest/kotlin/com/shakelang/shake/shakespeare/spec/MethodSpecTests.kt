@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.spec

import com.shakelang.shake.shakespeare.spec.code.CodeSpec
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ParameterSpecTests : FreeSpec(
    {

        "should create a parameter" {
            val identifier = NamespaceSpec("name")
            val type = TypeSpec.of("int")
            val parameter = ParameterSpec(identifier, type)
            parameter.name shouldBe identifier
            parameter.type shouldBe type
        }

        "should generate a parameter" {
            val identifier = NamespaceSpec("name")
            val type = TypeSpec.of("int")
            val parameter = ParameterSpec(identifier, type)
            parameter.generate(GenerationContext()) shouldBe "name: int"
        }

        "build should create a parameter" {
            val identifier = NamespaceSpec("name")
            val type = TypeSpec.of("int")
            val parameter = ParameterSpec.builder().name(identifier).type(type).build()
            parameter.name shouldBe identifier
            parameter.type shouldBe type
        }
    },
)

class MethodSpecTests : FreeSpec(
    {

        "should create a method" {
            val identifier = NamespaceSpec("name")
            val returnType = TypeSpec.of("int")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val isStatic = false
            val isAbstract = false
            val isFinal = false
            val isOverride = false
            val accessModifier = AccessModifier.PUBLIC
            val isSynchronized = false
            val isNative = false
            val isOperator = false
            val method = MethodSpec(
                identifier,
                returnType,
                null,
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
            )
            method.name shouldBe identifier
            method.returnType shouldBe returnType
            method.parameters shouldBe parameters
            method.body shouldBe body
            method.isStatic shouldBe isStatic
            method.isAbstract shouldBe isAbstract
            method.isFinal shouldBe isFinal
            method.isOverride shouldBe isOverride
            method.accessModifier shouldBe accessModifier
            method.isSynchronized shouldBe isSynchronized
            method.isNative shouldBe isNative
        }

        "create static method" {
            val identifier = NamespaceSpec("name")
            val returnType = TypeSpec.of("int")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val isStatic = true
            val isAbstract = false
            val isFinal = false
            val isOverride = false
            val accessModifier = AccessModifier.PUBLIC
            val isSynchronized = false
            val isNative = false
            val isOperator = false
            val method = MethodSpec(
                identifier,
                returnType,
                null,
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
            )
            method.isStatic shouldBe true
        }

        "create abstract method" {
            val identifier = NamespaceSpec("name")
            val returnType = TypeSpec.of("int")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val isStatic = false
            val isAbstract = true
            val isFinal = false
            val isOverride = false
            val accessModifier = AccessModifier.PUBLIC
            val isSynchronized = false
            val isNative = false
            val isOperator = false
            val method = MethodSpec(
                identifier,
                returnType,
                null,
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
            )
            method.isAbstract shouldBe true
        }

        "create final method" {
            val identifier = NamespaceSpec("name")
            val returnType = TypeSpec.of("int")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val isStatic = false
            val isAbstract = false
            val isFinal = true
            val isOverride = false
            val accessModifier = AccessModifier.PUBLIC
            val isSynchronized = false
            val isNative = false
            val isOperator = false
            val method = MethodSpec(
                identifier,
                returnType,
                null,
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
            )
            method.isFinal shouldBe true
        }

        "create override method" {
            val identifier = NamespaceSpec("name")
            val returnType = TypeSpec.of("int")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val isStatic = false
            val isAbstract = false
            val isFinal = false
            val isOverride = true
            val accessModifier = AccessModifier.PUBLIC
            val isSynchronized = false
            val isNative = false
            val isOperator = false
            val method = MethodSpec(
                identifier,
                returnType,
                null,
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
            )
            method.isOverride shouldBe true
        }

        "create operator method" {
            val identifier = NamespaceSpec("name")
            val returnType = TypeSpec.of("int")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val isStatic = false
            val isAbstract = false
            val isFinal = false
            val isOverride = false
            val accessModifier = AccessModifier.PUBLIC
            val isSynchronized = false
            val isNative = false
            val isOperator = true
            val method = MethodSpec(
                identifier,
                returnType,
                null,
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
            )
            method.isOperator shouldBe true
        }

        "create private method" {
            val identifier = NamespaceSpec("name")
            val returnType = TypeSpec.of("int")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val isStatic = false
            val isAbstract = false
            val isFinal = false
            val isOverride = false
            val accessModifier = AccessModifier.PRIVATE
            val isSynchronized = false
            val isNative = false
            val isOperator = false
            val method = MethodSpec(
                identifier,
                returnType,
                null,
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
            )
            method.accessModifier shouldBe AccessModifier.PRIVATE
        }

        "create synchronized method" {
            val identifier = NamespaceSpec("name")
            val returnType = TypeSpec.of("int")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val isStatic = false
            val isAbstract = false
            val isFinal = false
            val isOverride = false
            val accessModifier = AccessModifier.PUBLIC
            val isSynchronized = true
            val isNative = false
            val isOperator = false
            val method = MethodSpec(
                identifier,
                returnType,
                null,
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
            )
            method.isSynchronized shouldBe true
        }

        "create native method" {
            val identifier = NamespaceSpec("name")
            val returnType = TypeSpec.of("int")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val isStatic = false
            val isAbstract = false
            val isFinal = false
            val isOverride = false
            val accessModifier = AccessModifier.PUBLIC
            val isSynchronized = false
            val isNative = true
            val isOperator = false
            val method = MethodSpec(
                identifier,
                returnType,
                null,
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
            )
            method.isNative shouldBe true
        }

        "should generate a method with parameters" {
            val identifier = NamespaceSpec("name")
            val returnType = TypeSpec.of("int")
            val parameters = listOf(
                ParameterSpec.builder().name(NamespaceSpec("param1")).type(TypeSpec.of("int")).build(),
                ParameterSpec.builder().name(NamespaceSpec("param2")).type(TypeSpec.of("int")).build(),
            )
            val body = CodeSpec.empty()
            val isStatic = false
            val isAbstract = false
            val isFinal = false
            val isOverride = false
            val accessModifier = AccessModifier.PUBLIC
            val isSynchronized = false
            val isNative = false
            val isOperator = false
            val method = MethodSpec(
                identifier,
                returnType,
                null,
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
            )
            method.parameters shouldBe parameters
        }

        "should generate a method" {
            val identifier = NamespaceSpec("name")
            val returnType = TypeSpec.of("int")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val isStatic = false
            val isAbstract = false
            val isFinal = false
            val isOverride = false
            val accessModifier = AccessModifier.PUBLIC
            val isSynchronized = false
            val isNative = false
            val isOperator = false
            val method = MethodSpec(
                identifier,
                returnType,
                null,
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
            )
            method.generate(GenerationContext()) shouldBe "public fun name(): int {}"
        }

        "builder should create a method" {
            val identifier = NamespaceSpec("name")
            val returnType = TypeSpec.of("int")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val isStatic = false
            val isAbstract = false
            val isFinal = false
            val isOverride = false
            val accessModifier = AccessModifier.PUBLIC
            val isSynchronized = false
            val isNative = false
            val method = MethodSpec.builder()
                .name(identifier)
                .returnType(returnType)
                .parameters(parameters)
                .body(body)
                .static(isStatic)
                .abstract(isAbstract)
                .final(isFinal)
                .override(isOverride)
                .accessModifier(accessModifier)
                .synchronized(isSynchronized)
                .native(isNative)
                .build()
            method.name shouldBe identifier
            method.returnType shouldBe returnType
            method.parameters shouldBe parameters
            method.body shouldBe body
            method.isStatic shouldBe isStatic
            method.isAbstract shouldBe isAbstract
            method.isFinal shouldBe isFinal
            method.isOverride shouldBe isOverride
            method.accessModifier shouldBe accessModifier
            method.isSynchronized shouldBe isSynchronized
            method.isNative shouldBe isNative
        }

        "builder should create a method with parameters" {
            val identifier = NamespaceSpec("name")
            val returnType = TypeSpec.of("int")
            val parameters = listOf(
                ParameterSpec.builder().name(NamespaceSpec("param1")).type(TypeSpec.of("int")).build(),
                ParameterSpec.builder().name(NamespaceSpec("param2")).type(TypeSpec.of("int")).build(),
            )
            val body = CodeSpec.empty()
            val isStatic = false
            val isAbstract = false
            val isFinal = false
            val isOverride = false
            val accessModifier = AccessModifier.PUBLIC
            val isSynchronized = false
            val isNative = false
            val method = MethodSpec.builder()
                .name(identifier)
                .returnType(returnType)
                .parameters(parameters)
                .body(body)
                .static(isStatic)
                .abstract(isAbstract)
                .final(isFinal)
                .override(isOverride)
                .accessModifier(accessModifier)
                .synchronized(isSynchronized)
                .native(isNative)
                .build()
            method.name shouldBe identifier
            method.returnType shouldBe returnType
            method.parameters shouldBe parameters
            method.body shouldBe body
            method.isStatic shouldBe isStatic
            method.isAbstract shouldBe isAbstract
            method.isFinal shouldBe isFinal
            method.isOverride shouldBe isOverride
            method.accessModifier shouldBe accessModifier
            method.isSynchronized shouldBe isSynchronized
            method.isNative shouldBe isNative
        }
    },
)
