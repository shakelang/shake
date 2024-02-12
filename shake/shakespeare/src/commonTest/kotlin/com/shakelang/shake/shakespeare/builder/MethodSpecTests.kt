@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.builder

import com.shakelang.shake.shakespeare.spec.*
import com.shakelang.shake.shakespeare.spec.code.CodeSpec
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ParameterSpecTests : FreeSpec(
    {

        "should create a parameter" {
            val identifier = Identifier("name")
            val type = Type.of("int")
            val parameter = ParameterSpec(identifier, type)
            parameter.name shouldBe identifier
            parameter.type shouldBe type
        }

        "should generate a parameter" {
            val identifier = Identifier("name")
            val type = Type.of("int")
            val parameter = ParameterSpec(identifier, type)
            parameter.generate(GenerationContext()) shouldBe "int name"
        }

        "build should create a parameter" {
            val identifier = Identifier("name")
            val type = Type.of("int")
            val parameter = ParameterSpec.builder().name(identifier).type(type).build()
            parameter.name shouldBe identifier
            parameter.type shouldBe type
        }
    },
)

class MethodSpecTests : FreeSpec(
    {

        "should create a method" {
            val identifier = Identifier("name")
            val returnType = Type.of("int")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val isStatic = false
            val isAbstract = false
            val isFinal = false
            val isOverride = false
            val accessModifier = AccessModifier.PUBLIC
            val isSynchronized = false
            val isNative = false
            val method = MethodSpec(
                identifier,
                returnType,
                parameters,
                body,
                isStatic,
                isAbstract,
                isFinal,
                isOverride,
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
            val identifier = Identifier("name")
            val returnType = Type.of("int")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val isStatic = true
            val isAbstract = false
            val isFinal = false
            val isOverride = false
            val accessModifier = AccessModifier.PUBLIC
            val isSynchronized = false
            val isNative = false
            val method = MethodSpec(
                identifier,
                returnType,
                parameters,
                body,
                isStatic,
                isAbstract,
                isFinal,
                isOverride,
                accessModifier,
                isSynchronized,
                isNative,
            )
            method.isStatic shouldBe true
        }

        "create abstract method" {
            val identifier = Identifier("name")
            val returnType = Type.of("int")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val isStatic = false
            val isAbstract = true
            val isFinal = false
            val isOverride = false
            val accessModifier = AccessModifier.PUBLIC
            val isSynchronized = false
            val isNative = false
            val method = MethodSpec(
                identifier,
                returnType,
                parameters,
                body,
                isStatic,
                isAbstract,
                isFinal,
                isOverride,
                accessModifier,
                isSynchronized,
                isNative,
            )
            method.isAbstract shouldBe true
        }

        "create final method" {
            val identifier = Identifier("name")
            val returnType = Type.of("int")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val isStatic = false
            val isAbstract = false
            val isFinal = true
            val isOverride = false
            val accessModifier = AccessModifier.PUBLIC
            val isSynchronized = false
            val isNative = false
            val method = MethodSpec(
                identifier,
                returnType,
                parameters,
                body,
                isStatic,
                isAbstract,
                isFinal,
                isOverride,
                accessModifier,
                isSynchronized,
                isNative,
            )
            method.isFinal shouldBe true
        }

        "create override method" {
            val identifier = Identifier("name")
            val returnType = Type.of("int")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val isStatic = false
            val isAbstract = false
            val isFinal = false
            val isOverride = true
            val accessModifier = AccessModifier.PUBLIC
            val isSynchronized = false
            val isNative = false
            val method = MethodSpec(
                identifier,
                returnType,
                parameters,
                body,
                isStatic,
                isAbstract,
                isFinal,
                isOverride,
                accessModifier,
                isSynchronized,
                isNative,
            )
            method.isOverride shouldBe true
        }

        "create private method" {
            val identifier = Identifier("name")
            val returnType = Type.of("int")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val isStatic = false
            val isAbstract = false
            val isFinal = false
            val isOverride = false
            val accessModifier = AccessModifier.PRIVATE
            val isSynchronized = false
            val isNative = false
            val method = MethodSpec(
                identifier,
                returnType,
                parameters,
                body,
                isStatic,
                isAbstract,
                isFinal,
                isOverride,
                accessModifier,
                isSynchronized,
                isNative,
            )
            method.accessModifier shouldBe AccessModifier.PRIVATE
        }

        "create synchronized method" {
            val identifier = Identifier("name")
            val returnType = Type.of("int")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val isStatic = false
            val isAbstract = false
            val isFinal = false
            val isOverride = false
            val accessModifier = AccessModifier.PUBLIC
            val isSynchronized = true
            val isNative = false
            val method = MethodSpec(
                identifier,
                returnType,
                parameters,
                body,
                isStatic,
                isAbstract,
                isFinal,
                isOverride,
                accessModifier,
                isSynchronized,
                isNative,
            )
            method.isSynchronized shouldBe true
        }

        "create native method" {
            val identifier = Identifier("name")
            val returnType = Type.of("int")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val isStatic = false
            val isAbstract = false
            val isFinal = false
            val isOverride = false
            val accessModifier = AccessModifier.PUBLIC
            val isSynchronized = false
            val isNative = true
            val method = MethodSpec(
                identifier,
                returnType,
                parameters,
                body,
                isStatic,
                isAbstract,
                isFinal,
                isOverride,
                accessModifier,
                isSynchronized,
                isNative,
            )
            method.isNative shouldBe true
        }

        "should generate a method with parameters" {
            val identifier = Identifier("name")
            val returnType = Type.of("int")
            val parameters = listOf(
                ParameterSpec.builder().name(Identifier("param1")).type(Type.of("int")).build(),
                ParameterSpec.builder().name(Identifier("param2")).type(Type.of("int")).build(),
            )
            val body = CodeSpec.empty()
            val isStatic = false
            val isAbstract = false
            val isFinal = false
            val isOverride = false
            val accessModifier = AccessModifier.PUBLIC
            val isSynchronized = false
            val isNative = false
            val method = MethodSpec(
                identifier,
                returnType,
                parameters,
                body,
                isStatic,
                isAbstract,
                isFinal,
                isOverride,
                accessModifier,
                isSynchronized,
                isNative,
            )
            method.parameters shouldBe parameters
        }

        "should generate a method" {
            val identifier = Identifier("name")
            val returnType = Type.of("int")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val isStatic = false
            val isAbstract = false
            val isFinal = false
            val isOverride = false
            val accessModifier = AccessModifier.PUBLIC
            val isSynchronized = false
            val isNative = false
            val method = MethodSpec(
                identifier,
                returnType,
                parameters,
                body,
                isStatic,
                isAbstract,
                isFinal,
                isOverride,
                accessModifier,
                isSynchronized,
                isNative,
            )
            method.generate(GenerationContext()) shouldBe "public int name() {}"
        }
    },
)
