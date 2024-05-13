@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.nodes.spec.code

import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.shake.shakespeare.nodes.spec.NodeContext
import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.TypeSpec
import com.shakelang.shake.shakespeare.spec.code.CodeSpec
import com.shakelang.shake.shakespeare.spec.code.VariableDeclarationSpec
import com.shakelang.util.testlib.FlatTestSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class CodeNodeSpecTests : FlatTestSpec({

    describe("of()") {
        it("should convert an empty CodeSpec") {
            val codeSpec = CodeSpec.empty()
            val codeNodeSpec = CodeNodeSpec.of(codeSpec)
            codeNodeSpec.statements.size shouldBe 0
        }

        it("should convert a CodeSpec with one statement") {
            val codeSpec = CodeSpec.builder()
                .addStatement(
                    VariableDeclarationSpec.builder()
                        .name("a")
                        .type(TypeSpec.int())
                        .build(),
                )
                .build()

            val codeNodeSpec = CodeNodeSpec.of(codeSpec)
            codeNodeSpec.statements.size shouldBe 1
            (codeNodeSpec.statements[0] is VariableDeclarationNodeSpec) shouldBe true
        }

        it("should convert a CodeSpec with multiple statements") {
            val codeSpec = CodeSpec.builder()
                .addStatement(
                    VariableDeclarationSpec.builder()
                        .name("a")
                        .type(TypeSpec.int())
                        .build(),
                )
                .addStatement(
                    VariableDeclarationSpec.builder()
                        .name("b")
                        .type(TypeSpec.int())
                        .build(),
                )
                .build()

            val codeNodeSpec = CodeNodeSpec.of(codeSpec)
            codeNodeSpec.statements.size shouldBe 2
            (codeNodeSpec.statements[0] is VariableDeclarationNodeSpec) shouldBe true
            (codeNodeSpec.statements[1] is VariableDeclarationNodeSpec) shouldBe true
        }
    }

    describe("empty()") {
        it("should create an empty CodeNodeSpec") {
            val codeNodeSpec = CodeNodeSpec.empty()
            codeNodeSpec.statements.size shouldBe 0
        }
    }

    describe("dump()") {
        it("should dump correct output for an empty CodeNodeSpec") {
            val codeNodeSpec = CodeNodeSpec.empty()
            val generationContext = GenerationContext()
            val nodeContext = NodeContext()
            codeNodeSpec.dump(generationContext, nodeContext)
            nodeContext.built.toString() shouldBe "{}"
        }

        it("should create correct nodes for an empty CodeNodeSpec") {
            val codeNodeSpec = CodeNodeSpec.empty()
            val generationContext = GenerationContext()
            val nodeContext = NodeContext()
            val blockNode = codeNodeSpec.dump(generationContext, nodeContext)
            blockNode.children.size shouldBe 0
            blockNode.lcurlyToken shouldNotBe null
            blockNode.lcurlyToken?.type shouldBe ShakeTokenType.LCURL
            blockNode.rcurlyToken shouldNotBe null
            blockNode.rcurlyToken shouldNotBe null
        }

        it("should dump correct output for a CodeNodeSpec with one statement") {
            val codeNodeSpec = CodeNodeSpec.of(
                CodeSpec.builder()
                    .addStatement(
                        VariableDeclarationSpec.builder()
                            .name("a")
                            .type(TypeSpec.int())
                            .build(),
                    )
                    .build(),
            )
            val generationContext = GenerationContext()
            val nodeContext = NodeContext()
            codeNodeSpec.dump(generationContext, nodeContext)
            nodeContext.built.toString() shouldBe "{\n    var a: int\n}"
        }

        it("should create correct nodes for a CodeNodeSpec with one statement") {
            val codeNodeSpec = CodeNodeSpec.of(
                CodeSpec.builder()
                    .addStatement(
                        VariableDeclarationSpec.builder()
                            .name("a")
                            .type(TypeSpec.int())
                            .build(),
                    )
                    .build(),
            )
            val generationContext = GenerationContext()
            val nodeContext = NodeContext()
            val blockNode = codeNodeSpec.dump(generationContext, nodeContext)
            blockNode.children.size shouldBe 1
            blockNode.lcurlyToken shouldNotBe null
            blockNode.lcurlyToken?.type shouldBe ShakeTokenType.LCURL
            blockNode.rcurlyToken shouldNotBe null
            blockNode.rcurlyToken shouldNotBe null
        }

        it("should dump correct output for a CodeNodeSpec with multiple statements") {
            val codeNodeSpec = CodeNodeSpec.of(
                CodeSpec.builder()
                    .addStatement(
                        VariableDeclarationSpec.builder()
                            .name("a")
                            .type(TypeSpec.int())
                            .build(),
                    )
                    .addStatement(
                        VariableDeclarationSpec.builder()
                            .name("b")
                            .type(TypeSpec.int())
                            .build(),
                    )
                    .build(),
            )
            val generationContext = GenerationContext()
            val nodeContext = NodeContext()
            codeNodeSpec.dump(generationContext, nodeContext)
            nodeContext.built.toString() shouldBe "{\n    var a: int\n    var b: int\n}"
        }

        it("should create correct nodes for a CodeNodeSpec with multiple statements") {
            val codeNodeSpec = CodeNodeSpec.of(
                CodeSpec.builder()
                    .addStatement(
                        VariableDeclarationSpec.builder()
                            .name("a")
                            .type(TypeSpec.int())
                            .build(),
                    )
                    .addStatement(
                        VariableDeclarationSpec.builder()
                            .name("b")
                            .type(TypeSpec.int())
                            .build(),
                    )
                    .build(),
            )
            val generationContext = GenerationContext()
            val nodeContext = NodeContext()
            val blockNode = codeNodeSpec.dump(generationContext, nodeContext)
            blockNode.children.size shouldBe 2
            blockNode.lcurlyToken shouldNotBe null
            blockNode.lcurlyToken?.type shouldBe ShakeTokenType.LCURL
            blockNode.rcurlyToken shouldNotBe null
            blockNode.rcurlyToken shouldNotBe null
        }
    }
})
