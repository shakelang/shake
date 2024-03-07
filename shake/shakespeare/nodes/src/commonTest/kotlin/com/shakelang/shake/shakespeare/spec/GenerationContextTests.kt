package com.shakelang.shake.shakespeare.spec

import com.shakelang.shake.shakespeare.nodes.spec.GenerationContext
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class GenerationContextTests : FreeSpec({
    "should be constructed correctly" {
        val context = GenerationContext()
        context.indentType shouldBe "    "
        context.newline shouldBe "\n"
        context.indentLevel shouldBe 0
    }

    "should be constructed with custom values" {
        val context = GenerationContext("  ", "\r\n", 2)
        context.indentType shouldBe "  "
        context.newline shouldBe "\r\n"
        context.indentLevel shouldBe 2
    }

    "should be indented correctly" {
        val context = GenerationContext().indent()
        context.indentLevel shouldBe 1
    }

    "should be dedented correctly" {
        val context = GenerationContext().indent().dedent()
        context.indentLevel shouldBe 0
    }

    "should generate indent correctly" {
        val context = GenerationContext("    ", "\r\n", 2)
        context.generateIndent() shouldBe "        "
    }

    "should generate indent correctly with custom indent type" {
        val context = GenerationContext("  ", "\r\n", 2)
        context.generateIndent() shouldBe "    "
    }
})
