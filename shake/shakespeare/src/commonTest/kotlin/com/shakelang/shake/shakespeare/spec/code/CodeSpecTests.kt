package com.shakelang.shake.shakespeare.spec.code

import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.util.testlib.FlatTestSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class CodeSpecTests : FlatTestSpec({
    it("create") {

        val codeSpec = CodeSpec(
            listOf(),
        )

        codeSpec.statements shouldBe listOf()
    }

    it("create with statements") {

        val codeSpec = CodeSpec(
            listOf(
                StatementSpec.of("statement1"),
            ),
        )

        codeSpec.statements shouldBe listOf(
            StatementSpec.of("statement1"),
        )
    }

    it("generate code") {
        val codeSpec = CodeSpec(
            listOf(
                StatementSpec.of("statement1"),
                StatementSpec.of("statement2"),
            ),
        )

        codeSpec.generate(GenerationContext()) shouldBe "{\n    statement1\n    statement2\n}"
    }

    it("generate with empty statements") {
        val codeSpec = CodeSpec(
            listOf(),
        )

        codeSpec.generate(GenerationContext()) shouldBe "{}"
    }

    it("equals") {
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
