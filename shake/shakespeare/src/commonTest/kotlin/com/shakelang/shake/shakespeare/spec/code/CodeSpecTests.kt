package com.shakelang.shake.shakespeare.spec.code

import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.util.testlib.FlatTestSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class CodeSpecTests : FlatTestSpec({
    describe("create") {
        it("simple create") {

            val codeSpec = CodeSpec(
                listOf(),
            )

            codeSpec.statements shouldBe listOf()
        }

        it("with statements") {

            val codeSpec = CodeSpec(
                listOf(
                    StatementSpec.of("statement1"),
                ),
            )

            codeSpec.statements shouldBe listOf(
                StatementSpec.of("statement1"),
            )
        }
    }

    describe("generate") {
        it("code") {
            val codeSpec = CodeSpec(
                listOf(
                    StatementSpec.of("statement1"),
                    StatementSpec.of("statement2"),
                ),
            )

            codeSpec.generate(GenerationContext()) shouldBe "{\n    statement1\n    statement2\n}"
        }

        it(" with empty statements") {
            val codeSpec = CodeSpec(
                listOf(),
            )

            codeSpec.generate(GenerationContext()) shouldBe "{}"
        }
    }

    describe("equals") {

        it("should be equal to same") {
            val codeSpec = CodeSpec(
                listOf(
                    StatementSpec.of("statement1"),
                    StatementSpec.of("statement2"),
                ),
            )

            codeSpec shouldBe codeSpec
        }

        it("should not be equal to non-code-spec") {
            val codeSpec = CodeSpec(
                listOf(
                    StatementSpec.of("statement1"),
                    StatementSpec.of("statement2"),
                ),
            )

            codeSpec shouldNotBe "test"
        }

        it("should test equality") {
            val codeSpec = CodeSpec(
                listOf(
                    StatementSpec.of("statement1"),
                    StatementSpec.of("statement2"),
                ),
            )

            val codeSpec2 = CodeSpec(
                listOf(
                    StatementSpec.of("statement1"),
                    StatementSpec.of("statement2"),
                ),
            )

            codeSpec shouldBe codeSpec
            codeSpec shouldBe codeSpec2
            codeSpec shouldNotBe CodeSpec(
                listOf(
                    StatementSpec.of("statement1"),
                ),
            )

            codeSpec shouldNotBe CodeSpec(
                listOf(
                    StatementSpec.of("statement1"),
                    StatementSpec.of("statement3"),
                ),
            )
        }
    }

    it("build") {
        val codeSpec = CodeSpec.builder()
            .addStatement(StatementSpec.of("statement1"))
            .addStatement(StatementSpec.of("statement2"))
            .build()

        codeSpec shouldBe CodeSpec(
            listOf(
                StatementSpec.of("statement1"),
                StatementSpec.of("statement2"),
            ),
        )
    }
})
