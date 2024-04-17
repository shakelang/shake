package com.shakelang.shake.shakespeare.spec

import com.shakelang.shake.shakespeare.spec.code.CodeSpec
import com.shakelang.util.testlib.FlatTestSpec
import com.shakelang.util.testlib.describe
import io.kotest.matchers.shouldBe

class ConstructorSpecTests : FlatTestSpec(
    {

        describe("create") {
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
        }

        describe("generate") {

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
        }

        describe("builder") {
            it("should create a constructor") {
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

            it("should create a named constructor") {
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
        }
    },
)

class ClassSpecTests : FlatTestSpec({

    describe("create") {

        val it = ClassSpec(
            "ClassName",
            listOf(),
            listOf(),
            listOf(),
            listOf(),
            isAbstract = false,
            isFinal = false,
            isStatic = false,
            isNative = false,
            AccessModifier.PUBLIC,
        )

        it.name shouldBe "ClassName"
        it.methods shouldBe listOf()
        it.fields shouldBe listOf()
        it.constructors shouldBe listOf()
        it.classes shouldBe listOf()

        it.isAbstract shouldBe false
        it.isFinal shouldBe false
        it.isStatic shouldBe false
        it.isNative shouldBe false
        it.accessModifier shouldBe AccessModifier.PUBLIC
    }
})
