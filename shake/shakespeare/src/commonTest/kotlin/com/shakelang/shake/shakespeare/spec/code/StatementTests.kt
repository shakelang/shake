package com.shakelang.shake.shakespeare.spec.code

import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.TypeSpec
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class StatementTests : FreeSpec({

    "create VariableDeclarationSpec" {
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

    "generate VariableDeclarationSpec (var, no value, type)" {
        val spec = VariableDeclarationSpec(
            "name",
            TypeSpec.of("String"),
            null,
            false,
        )

        val ctx = GenerationContext()
        spec.generate(ctx) shouldBe "var name: String"
    }

    "generate VariableDeclarationSpec (var, value, type)" {
        val spec = VariableDeclarationSpec(
            "name",
            TypeSpec.of("String"),
            ValueSpec.of("value"),
            false,
        )

        val ctx = GenerationContext()
        spec.generate(ctx) shouldBe "var name: String = value"
    }

    "generate VariableDeclarationSpec (val, no value, type)" {
        val spec = VariableDeclarationSpec(
            "name",
            TypeSpec.of("String"),
            null,
            true,
        )

        val ctx = GenerationContext()
        spec.generate(ctx) shouldBe "val name: String"
    }

    "generate VariableDeclarationSpec (val, value, type)" {
        val spec = VariableDeclarationSpec(
            "name",
            TypeSpec.of("String"),
            ValueSpec.of("value"),
            true,
        )

        val ctx = GenerationContext()
        spec.generate(ctx) shouldBe "val name: String = value"
    }

    "generate VariableDeclarationSpec (var, no value, no type)" {
        val spec = VariableDeclarationSpec(
            "name",
            null,
            null,
            false,
        )

        val ctx = GenerationContext()
        spec.generate(ctx) shouldBe "var name"
    }

    "generate VariableDeclarationSpec (var, value, no type)" {
        val spec = VariableDeclarationSpec(
            "name",
            null,
            ValueSpec.of("value"),
            false,
        )

        val ctx = GenerationContext()
        spec.generate(ctx) shouldBe "var name = value"
    }

    "generate VariableDeclarationSpec (val, no value, no type)" {
        val spec = VariableDeclarationSpec(
            "name",
            null,
            null,
            true,
        )

        val ctx = GenerationContext()
        spec.generate(ctx) shouldBe "val name"
    }

    "generate VariableDeclarationSpec (val, value, no type)" {
        val spec = VariableDeclarationSpec(
            "name",
            null,
            ValueSpec.of("value"),
            true,
        )

        val ctx = GenerationContext()
        spec.generate(ctx) shouldBe "val name = value"
    }

    "equals VariableDeclarationSpec" {

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

    "build VariableDeclarationSpec" {
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

    "build VariableDeclarationSpec (val)" {
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

    "create WhileSpec" {
        val spec = WhileSpec(
            ValueSpec.of("condition"),
            CodeSpec.empty(),
        )

        spec.condition shouldBe ValueSpec.of("condition")
        spec.body shouldBe CodeSpec.empty()
    }

    "generate WhileSpec" {
        val spec = WhileSpec(
            ValueSpec.of("condition"),
            CodeSpec.empty(),
        )

        val ctx = GenerationContext()
        spec.generate(ctx) shouldBe "while (condition) {}"
    }

    "build WhileSpec" {
        val builder = WhileSpec.builder()
        builder.condition("condition")
        builder.body(CodeSpec.empty())
        val spec = builder.build()

        spec.condition shouldBe ValueSpec.of("condition")
        spec.body shouldBe CodeSpec.empty()
    }

    "equals WhileSpec" {

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

    "create DoWhileSpec" {
        val spec = DoWhileSpec(
            CodeSpec.empty(),
            ValueSpec.of("condition"),
        )

        spec.condition shouldBe ValueSpec.of("condition")
        spec.body shouldBe CodeSpec.empty()
    }

    "generate DoWhileSpec" {
        val spec = DoWhileSpec(
            CodeSpec.empty(),
            ValueSpec.of("condition"),
        )

        val ctx = GenerationContext()
        spec.generate(ctx) shouldBe "do {} while (condition)"
    }

    "build DoWhileSpec" {
        val builder = DoWhileSpec.builder()
        builder.condition("condition")
        builder.body(CodeSpec.empty())
        val spec = builder.build()

        spec.condition shouldBe ValueSpec.of("condition")
        spec.body shouldBe CodeSpec.empty()
    }

    "equals DoWhileSpec" {

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

    "create ForSpec" {
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

    "generate ForSpec" {
        val spec = ForSpec(
            StatementSpec.of("init"),
            ValueSpec.of("condition"),
            StatementSpec.of("update"),
            CodeSpec.empty(),
        )

        val ctx = GenerationContext()
        spec.generate(ctx) shouldBe "for (init; condition; update) {}"
    }

    "build ForSpec" {
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

    "equals ForSpec" {

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

    "create IfSpec" {
        val spec = IfSpec(
            ValueSpec.of("condition"),
            CodeSpec.empty(),
            CodeSpec.empty(),
        )

        spec.condition shouldBe ValueSpec.of("condition")
        spec.body shouldBe CodeSpec.empty()
        spec.elseBody shouldBe CodeSpec.empty()
    }

    "generate IfSpec" {
        val spec = IfSpec(
            ValueSpec.of("condition"),
            CodeSpec.empty(),
            CodeSpec.empty(),
        )

        val ctx = GenerationContext()
        spec.generate(ctx) shouldBe "if (condition) {} else {}"
    }

    "generate IfSpec (no else)" {
        val spec = IfSpec(
            ValueSpec.of("condition"),
            CodeSpec.empty(),
            null,
        )

        val ctx = GenerationContext()
        spec.generate(ctx) shouldBe "if (condition) {}"
    }

    "build IfSpec" {
        val builder = IfSpec.builder()
        builder.condition("condition")
        builder.body(CodeSpec.empty())
        builder.elseBody(CodeSpec.empty())
        val spec = builder.build()

        spec.condition shouldBe ValueSpec.of("condition")
        spec.body shouldBe CodeSpec.empty()
        spec.elseBody shouldBe CodeSpec.empty()
    }

    "equals IfSpec" {

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

    "create ReturnSpec" {
        val spec = ReturnSpec(
            ValueSpec.of("value"),
        )

        spec.value shouldBe ValueSpec.of("value")
    }

    "generate ReturnSpec" {
        val spec = ReturnSpec(
            ValueSpec.of("value"),
        )

        val ctx = GenerationContext()
        spec.generate(ctx) shouldBe "return value"
    }

    "generate ReturnSpec (no value)" {
        val spec = ReturnSpec(
            null,
        )

        val ctx = GenerationContext()
        spec.generate(ctx) shouldBe "return"
    }

    "build ReturnSpec" {
        val builder = ReturnSpec.builder()
        builder.value("value")
        val spec = builder.build()

        spec.value shouldBe ValueSpec.of("value")
    }

    "equals ReturnSpec" {

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
})
