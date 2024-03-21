package com.shakelang.shake.shakespeare.spec.code

import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.TypeSpec
import com.shakelang.util.testlib.FlatTestSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class StatementTests : FlatTestSpec({

    describe("VariableDeclarationSpec") {
        it("create") {
            val spec = VariableDeclarationSpec(
                "name",
                TypeSpec.of("String"),
                ValueSpec.of("value"),
                false,
            )

            spec.type shouldBe TypeSpec.of("String")
            spec.value shouldBe ValueSpec.of("value")
            spec.name shouldBe "name"
            spec.isVal shouldBe false
        }

        it("generate (var, no value, type)") {
            val spec = VariableDeclarationSpec(
                "name",
                TypeSpec.of("String"),
                null,
                false,
            )

            val ctx = GenerationContext()
            spec.generate(ctx) shouldBe "var name: String"
        }

        it("generate (var, value, type)") {
            val spec = VariableDeclarationSpec(
                "name",
                TypeSpec.of("String"),
                ValueSpec.of("value"),
                false,
            )

            val ctx = GenerationContext()
            spec.generate(ctx) shouldBe "var name: String = value"
        }

        it("generate (val, no value, type)") {
            val spec = VariableDeclarationSpec(
                "name",
                TypeSpec.of("String"),
                null,
                true,
            )

            val ctx = GenerationContext()
            spec.generate(ctx) shouldBe "val name: String"
        }

        it("generate (val, value, type)") {
            val spec = VariableDeclarationSpec(
                "name",
                TypeSpec.of("String"),
                ValueSpec.of("value"),
                true,
            )

            val ctx = GenerationContext()
            spec.generate(ctx) shouldBe "val name: String = value"
        }

        it("generate (var, no value, no type)") {
            val spec = VariableDeclarationSpec(
                "name",
                null,
                null,
                false,
            )

            val ctx = GenerationContext()
            spec.generate(ctx) shouldBe "var name"
        }

        it("generate (var, value, no type)") {
            val spec = VariableDeclarationSpec(
                "name",
                null,
                ValueSpec.of("value"),
                false,
            )

            val ctx = GenerationContext()
            spec.generate(ctx) shouldBe "var name = value"
        }

        it("generate (val, no value, no type)") {
            val spec = VariableDeclarationSpec(
                "name",
                null,
                null,
                true,
            )

            val ctx = GenerationContext()
            spec.generate(ctx) shouldBe "val name"
        }

        it("generate (val, value, no type)") {
            val spec = VariableDeclarationSpec(
                "name",
                null,
                ValueSpec.of("value"),
                true,
            )

            val ctx = GenerationContext()
            spec.generate(ctx) shouldBe "val name = value"
        }

        it("equals") {

            val spec = VariableDeclarationSpec(
                "name",
                TypeSpec.of("String"),
                ValueSpec.of("value"),
                false,
            )

            spec shouldBe spec

            spec shouldBe VariableDeclarationSpec(
                "name",
                TypeSpec.of("String"),
                ValueSpec.of("value"),
                false,
            )

            spec shouldNotBe VariableDeclarationSpec(
                "name",
                TypeSpec.of("String2"),
                ValueSpec.of("value"),
                false,
            )

            spec shouldNotBe VariableDeclarationSpec(
                "name2",
                TypeSpec.of("String"),
                ValueSpec.of("value"),
                false,
            )

            spec shouldNotBe "name"
        }

        it("build") {
            val builder = VariableDeclarationSpec.builder()
            builder.name("name")
            builder.type("String")
            builder.value("value")
            builder.isVal(false)
            val spec = builder.build()

            spec.type shouldBe TypeSpec.of("String")
            spec.value shouldBe ValueSpec.of("value")
            spec.name shouldBe "name"
            spec.isVal shouldBe false
        }

        it("build (val)") {
            val builder = VariableDeclarationSpec.builder()
            builder.name("name")
            builder.type(TypeSpec.of("String"))
            builder.value("value")
            builder.isVal()
            val spec = builder.build()

            spec.type shouldBe TypeSpec.of("String")
            spec.value shouldBe ValueSpec.of("value")
            spec.name shouldBe "name"
            spec.isVal shouldBe true
        }
    }

    describe("WhileSpec") {
        it("create") {
            val spec = WhileSpec(
                ValueSpec.of("condition"),
                CodeSpec.empty(),
            )

            spec.condition shouldBe ValueSpec.of("condition")
            spec.body shouldBe CodeSpec.empty()
        }

        it("generate") {
            val spec = WhileSpec(
                ValueSpec.of("condition"),
                CodeSpec.empty(),
            )

            val ctx = GenerationContext()
            spec.generate(ctx) shouldBe "while (condition) {}"
        }

        it("build") {
            val builder = WhileSpec.builder()
            builder.condition("condition")
            builder.body(CodeSpec.empty())
            val spec = builder.build()

            spec.condition shouldBe ValueSpec.of("condition")
            spec.body shouldBe CodeSpec.empty()
        }

        it("equals") {

            val spec = WhileSpec(
                ValueSpec.of("condition"),
                CodeSpec.empty(),
            )

            spec shouldBe spec

            spec shouldBe WhileSpec(
                ValueSpec.of("condition"),
                CodeSpec.empty(),
            )

            spec shouldNotBe WhileSpec(
                ValueSpec.of("condition2"),
                CodeSpec.empty(),
            )

            spec shouldNotBe WhileSpec(
                ValueSpec.of("condition"),
                CodeSpec.builder().addStatement(StatementSpec.of("statement")).build(),
            )

            spec shouldNotBe "condition"
        }
    }

    describe("DoWhileSpec") {
        it("create ") {
            val spec = DoWhileSpec(
                CodeSpec.empty(),
                ValueSpec.of("condition"),
            )

            spec.condition shouldBe ValueSpec.of("condition")
            spec.body shouldBe CodeSpec.empty()
        }

        it("generate") {
            val spec = DoWhileSpec(
                CodeSpec.empty(),
                ValueSpec.of("condition"),
            )

            val ctx = GenerationContext()
            spec.generate(ctx) shouldBe "do {} while (condition)"
        }

        it("build") {
            val builder = DoWhileSpec.builder()
            builder.condition("condition")
            builder.body(CodeSpec.empty())
            val spec = builder.build()

            spec.condition shouldBe ValueSpec.of("condition")
            spec.body shouldBe CodeSpec.empty()
        }

        it("equals") {

            val spec = DoWhileSpec(
                CodeSpec.empty(),
                ValueSpec.of("condition"),
            )

            spec shouldBe spec

            spec shouldBe DoWhileSpec(
                CodeSpec.empty(),
                ValueSpec.of("condition"),
            )

            spec shouldNotBe DoWhileSpec(
                CodeSpec.builder().addStatement(StatementSpec.of("statement")).build(),
                ValueSpec.of("condition"),
            )

            spec shouldNotBe DoWhileSpec(
                CodeSpec.empty(),
                ValueSpec.of("condition2"),
            )

            spec shouldNotBe "condition"
        }
    }
    describe("ForSpec") {
        it("create") {
            val spec = ForSpec(
                StatementSpec.of("init"),
                ValueSpec.of("condition"),
                StatementSpec.of("update"),
                CodeSpec.empty(),
            )

            spec.init shouldBe StatementSpec.of("init")
            spec.condition shouldBe ValueSpec.of("condition")
            spec.update shouldBe StatementSpec.of("update")
            spec.body shouldBe CodeSpec.empty()
        }

        it("generate") {
            val spec = ForSpec(
                StatementSpec.of("init"),
                ValueSpec.of("condition"),
                StatementSpec.of("update"),
                CodeSpec.empty(),
            )

            val ctx = GenerationContext()
            spec.generate(ctx) shouldBe "for (init; condition; update) {}"
        }

        it("build") {
            val builder = ForSpec.builder()
            builder.init("init")
            builder.condition("condition")
            builder.update("update")
            builder.body(CodeSpec.empty())
            val spec = builder.build()

            spec.init shouldBe StatementSpec.of("init")
            spec.condition shouldBe ValueSpec.of("condition")
            spec.update shouldBe StatementSpec.of("update")
            spec.body shouldBe CodeSpec.empty()
        }

        it("equals") {

            val spec = ForSpec(
                StatementSpec.of("init"),
                ValueSpec.of("condition"),
                StatementSpec.of("update"),
                CodeSpec.empty(),
            )

            spec shouldBe spec

            spec shouldBe ForSpec(
                StatementSpec.of("init"),
                ValueSpec.of("condition"),
                StatementSpec.of("update"),
                CodeSpec.empty(),
            )

            spec shouldNotBe ForSpec(
                StatementSpec.of("init2"),
                ValueSpec.of("condition"),
                StatementSpec.of("update"),
                CodeSpec.empty(),
            )

            spec shouldNotBe ForSpec(
                StatementSpec.of("init"),
                ValueSpec.of("condition2"),
                StatementSpec.of("update"),
                CodeSpec.empty(),
            )

            spec shouldNotBe ForSpec(
                StatementSpec.of("init"),
                ValueSpec.of("condition"),
                StatementSpec.of("update2"),
                CodeSpec.empty(),
            )

            spec shouldNotBe ForSpec(
                StatementSpec.of("init"),
                ValueSpec.of("condition"),
                StatementSpec.of("update"),
                CodeSpec.builder().addStatement(StatementSpec.of("statement")).build(),
            )

            spec shouldNotBe "condition"
        }
    }

    describe("IfSpec") {
        it("create") {
            val spec = IfSpec(
                ValueSpec.of("condition"),
                CodeSpec.empty(),
                CodeSpec.empty(),
            )

            spec.condition shouldBe ValueSpec.of("condition")
            spec.body shouldBe CodeSpec.empty()
            spec.elseBody shouldBe CodeSpec.empty()
        }

        it("generate") {
            val spec = IfSpec(
                ValueSpec.of("condition"),
                CodeSpec.empty(),
                CodeSpec.empty(),
            )

            val ctx = GenerationContext()
            spec.generate(ctx) shouldBe "if (condition) {} else {}"
        }

        it("generate (no else)") {
            val spec = IfSpec(
                ValueSpec.of("condition"),
                CodeSpec.empty(),
                null,
            )

            val ctx = GenerationContext()
            spec.generate(ctx) shouldBe "if (condition) {}"
        }

        it("build") {
            val builder = IfSpec.builder()
            builder.condition("condition")
            builder.body(CodeSpec.empty())
            builder.elseBody(CodeSpec.empty())
            val spec = builder.build()

            spec.condition shouldBe ValueSpec.of("condition")
            spec.body shouldBe CodeSpec.empty()
            spec.elseBody shouldBe CodeSpec.empty()
        }

        it("equals") {

            val spec = IfSpec(
                ValueSpec.of("condition"),
                CodeSpec.empty(),
                CodeSpec.empty(),
            )

            spec shouldBe spec

            spec shouldBe IfSpec(
                ValueSpec.of("condition"),
                CodeSpec.empty(),
                CodeSpec.empty(),
            )

            spec shouldNotBe IfSpec(
                ValueSpec.of("condition2"),
                CodeSpec.empty(),
                CodeSpec.empty(),
            )

            spec shouldNotBe IfSpec(
                ValueSpec.of("condition"),
                CodeSpec.builder().addStatement(StatementSpec.of("statement")).build(),
                CodeSpec.empty(),
            )

            spec shouldNotBe IfSpec(
                ValueSpec.of("condition"),
                CodeSpec.empty(),
                CodeSpec.builder().addStatement(StatementSpec.of("statement")).build(),
            )

            spec shouldNotBe "condition"
        }
    }
    describe("ReturnSpec") {
        it("create") {
            val spec = ReturnSpec(
                ValueSpec.of("value"),
            )

            spec.value shouldBe ValueSpec.of("value")
        }

        it("generate") {
            val spec = ReturnSpec(
                ValueSpec.of("value"),
            )

            val ctx = GenerationContext()
            spec.generate(ctx) shouldBe "return value"
        }

        it("generate (no value)") {
            val spec = ReturnSpec(
                null,
            )

            val ctx = GenerationContext()
            spec.generate(ctx) shouldBe "return"
        }

        it("build") {
            val builder = ReturnSpec.builder()
            builder.value("value")
            val spec = builder.build()

            spec.value shouldBe ValueSpec.of("value")
        }

        it("equals") {

            val spec = ReturnSpec(
                ValueSpec.of("value"),
            )

            spec shouldBe spec

            spec shouldBe ReturnSpec(
                ValueSpec.of("value"),
            )

            spec shouldNotBe ReturnSpec(
                ValueSpec.of("value2"),
            )

            spec shouldNotBe "value"
        }
    }
})
