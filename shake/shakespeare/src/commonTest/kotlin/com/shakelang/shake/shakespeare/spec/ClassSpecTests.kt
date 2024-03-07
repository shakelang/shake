package com.shakelang.shake.shakespeare.spec

import com.shakelang.shake.shakespeare.spec.code.CodeSpec
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ConstructorSpecTests : FreeSpec(
    {

        "should create a constructor" {
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val constructor = ConstructorSpec(parameters, body)
            constructor.parameters shouldBe parameters
            constructor.body shouldBe body
        }

        "should create a named constructor" {
            val identifier = NamespaceSpec("name")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val constructor = ConstructorSpec(parameters, body, identifier)
            constructor.name shouldBe identifier
            constructor.parameters shouldBe parameters
            constructor.body shouldBe body
        }

        "should generate a constructor" {
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val constructor = ConstructorSpec(parameters, body)
            constructor.generate(GenerationContext()) shouldBe "public constructor() {}"
        }

        "should generate a named constructor" {
            val identifier = NamespaceSpec("name")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val constructor = ConstructorSpec(parameters, body, identifier)
            constructor.generate(GenerationContext()) shouldBe "public constructor name() {}"
        }

        "builder should create a constructor" {
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val constructor = ConstructorSpec.builder()
                .parameters(parameters)
                .body(body)
                .build()
            constructor.parameters shouldBe parameters
            constructor.body shouldBe body
            constructor.name shouldBe null
        }

        "builder should create a named constructor" {
            val identifier = NamespaceSpec("name")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val constructor = ConstructorSpec.builder()
                .name(identifier)
                .parameters(parameters)
                .body(body)
                .build()
            constructor.name shouldBe identifier
            constructor.parameters shouldBe parameters
            constructor.body shouldBe body
        }
    },
)
