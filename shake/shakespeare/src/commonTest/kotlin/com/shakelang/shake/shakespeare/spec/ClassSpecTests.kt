package com.shakelang.shake.shakespeare.spec

import com.shakelang.shake.shakespeare.spec.code.CodeSpec
import com.shakelang.util.testlib.FlatTestSpec
import io.kotest.matchers.shouldBe

class ConstructorSpecTests : FlatTestSpec(
    {

        it("should create a constructor") {
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val constructor = ConstructorSpec(parameters, body)
            constructor.parameters shouldBe parameters
            constructor.body shouldBe body
        }

        it("should create a named constructor") {
            val identifier = NamespaceSpec("name")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val constructor = ConstructorSpec(parameters, body, identifier)
            constructor.name shouldBe identifier
            constructor.parameters shouldBe parameters
            constructor.body shouldBe body
        }

        it("should generate a constructor") {
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val constructor = ConstructorSpec(parameters, body)
            constructor.generate(GenerationContext()) shouldBe "public constructor() {}"
        }

        it("should generate a named constructor") {
            val identifier = NamespaceSpec("name")
            val parameters = listOf<ParameterSpec>()
            val body = CodeSpec.empty()
            val constructor = ConstructorSpec(parameters, body, identifier)
            constructor.generate(GenerationContext()) shouldBe "public constructor name() {}"
        }

        it("builder should create a constructor") {
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

        it("builder should create a named constructor") {
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
