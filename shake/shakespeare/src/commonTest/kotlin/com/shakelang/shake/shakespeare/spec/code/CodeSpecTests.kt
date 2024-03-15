package com.shakelang.shake.shakespeare.spec.code

import com.shakelang.shake.shakespeare.spec.GenerationContext
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class CodeSpecTests : FreeSpec({
    "create CodeSpec" {

        val codeSpec = CodeSpec(
            listOf(),
        )

        codeSpec.statements shouldBe listOf()
    }

    "create CodeSpec with statements" {

        val codeSpec = CodeSpec(
            listOf(
                StatementSpec.of("statement1"),
            ),
        )

        codeSpec.statements shouldBe listOf(
            StatementSpec.of("statement1"),
        )
    }

    "generate code" {
        val codeSpec = CodeSpec(
            listOf(
                StatementSpec.of("statement1"),
                StatementSpec.of("statement2"),
            ),
        )

        codeSpec.generate(GenerationContext()) shouldBe "{\n    statement1\n    statement2\n}"
    }

    "generate code with empty statements" {
        val codeSpec = CodeSpec(
            listOf(),
        )

        codeSpec.generate(GenerationContext()) shouldBe "{}"
    }

    "equals" {
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

    "build" {
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
