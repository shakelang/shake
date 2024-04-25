package com.shakelang.shake.shakespeare.nodes.spec.code

import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.shake.parser.node.misc.ShakeVariableType
import com.shakelang.shake.parser.node.mixed.ShakeVariableAddAssignmentNode
import com.shakelang.shake.parser.node.statements.ShakeLocalDeclarationNode
import com.shakelang.shake.parser.node.values.ShakeVariableUsageNode
import com.shakelang.shake.parser.node.values.factor.ShakeIntegerLiteralNode
import com.shakelang.shake.parser.node.values.factor.ShakeTrueLiteralNode
import com.shakelang.shake.shakespeare.nodes.spec.NamespaceNodeSpec
import com.shakelang.shake.shakespeare.nodes.spec.NodeContext
import com.shakelang.shake.shakespeare.nodes.spec.TypeNodeSpec
import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.TypeSpec
import com.shakelang.shake.shakespeare.spec.code.*
import com.shakelang.util.testlib.FlatTestSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe

class StatementTests : FlatTestSpec({

    describe("StatementNodeSpec") {
        describe("of()") {
            it("should convert VariableDeclarationSpec") {
                val node = VariableDeclarationSpec("a", TypeSpec.int(), ValueSpec.literal(10))
                val converted = StatementNodeSpec.of(node)
                converted.name shouldBe "a"
                converted.type shouldBe TypeNodeSpec.int()
                converted.value shouldBe IntLiteralNodeSpec(10)
            }
            it("should convert VariableDeclarationSpec (anonymous)") {
                val node = VariableDeclarationSpec("a", TypeSpec.int(), ValueSpec.literal(10))
                val converted = StatementNodeSpec.of(node as StatementSpec)
                (converted is VariableDeclarationNodeSpec).shouldBeTrue()
                (converted as VariableDeclarationNodeSpec).name shouldBe "a"
                converted.type shouldBe TypeNodeSpec.int()
                converted.value shouldBe IntLiteralNodeSpec(10)
            }
            it("should not convert VariableDeclarationNodeSpec") {
                val node = VariableDeclarationNodeSpec("a", TypeNodeSpec.int(), IntLiteralNodeSpec(10))
                (StatementNodeSpec.of(node) === node).shouldBeTrue()
            }
            it("should not convert VariableDeclarationNodeSpec (anonymous)") {
                val node = VariableDeclarationNodeSpec("a", TypeNodeSpec.int(), IntLiteralNodeSpec(10))
                (StatementNodeSpec.of(node as StatementSpec) === node).shouldBeTrue()
            }
            it("should convert WhileSpec") {
                val node = WhileSpec(BooleanLiteralSpec(true), CodeSpec.empty())
                val converted = StatementNodeSpec.of(node)
                converted.condition shouldBe BooleanLiteralNodeSpec(true)
                converted.body.statements.shouldBeEmpty()
            }
            it("should convert WhileSpec (anonymous)") {
                val node = WhileSpec(BooleanLiteralSpec(true), CodeSpec.empty())
                val converted = StatementNodeSpec.of(node as StatementSpec)
                (converted is WhileNodeSpec).shouldBeTrue()
                (converted as WhileNodeSpec).condition shouldBe BooleanLiteralNodeSpec(true)
                converted.body.statements.shouldBeEmpty()
            }
            it("should not convert WhileNodeSpec") {
                val node = WhileNodeSpec(BooleanLiteralNodeSpec(true), CodeNodeSpec.empty())
                (StatementNodeSpec.of(node) === node).shouldBeTrue()
            }
            it("should not convert WhileNodeSpec (anonymous)") {
                val node = WhileNodeSpec(BooleanLiteralNodeSpec(true), CodeNodeSpec.empty())
                (StatementNodeSpec.of(node as StatementSpec) === node).shouldBeTrue()
            }
            it("should convert DoWhileSpec") {
                val node = DoWhileSpec(CodeSpec.empty(), ValueSpec.literal(true))
                val converted = StatementNodeSpec.of(node)
                converted.condition shouldBe BooleanLiteralNodeSpec(true)
                converted.body.statements.shouldBeEmpty()
            }
            it("should convert DoWhileSpec (anonymous)") {
                val node = DoWhileSpec(CodeSpec.empty(), ValueSpec.literal(true))
                val converted = StatementNodeSpec.of(node as StatementSpec)
                (converted is DoWhileNodeSpec).shouldBeTrue()
                (converted as DoWhileNodeSpec).condition shouldBe BooleanLiteralNodeSpec(true)
                converted.body.statements.shouldBeEmpty()
            }
            it("should not convert DoWhileNodeSpec") {
                val node = DoWhileNodeSpec(CodeNodeSpec.empty(), BooleanLiteralNodeSpec(true))
                (StatementNodeSpec.of(node) === node).shouldBeTrue()
            }
            it("should not convert DoWhileNodeSpec (anonymous)") {
                val node = DoWhileNodeSpec(CodeNodeSpec.empty(), BooleanLiteralNodeSpec(true))
                (StatementNodeSpec.of(node as StatementSpec) === node).shouldBeTrue()
            }
            it("should convert ForSpec") {
                val node = ForSpec(
                    VariableDeclarationSpec("a", TypeSpec.int(), ValueSpec.literal(0)),
                    BooleanLiteralSpec(true),
                    VariableAdditionAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1)),
                    CodeSpec.empty(),
                )
                val converted = StatementNodeSpec.of(node)
                (converted).init shouldBe VariableDeclarationNodeSpec("a", TypeNodeSpec.int(), IntLiteralNodeSpec(0))
                converted.condition shouldBe BooleanLiteralNodeSpec(true)
                converted.update shouldBe VariableAdditionAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                converted.body.statements.shouldBeEmpty()
            }
            it("should convert ForSpec (anonymous)") {
                val node = ForSpec(
                    VariableDeclarationSpec("a", TypeSpec.int(), ValueSpec.literal(0)),
                    BooleanLiteralSpec(true),
                    VariableAdditionAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1)),
                    CodeSpec.empty(),
                )
                val converted = StatementNodeSpec.of(node as StatementSpec)
                (converted is ForNodeSpec).shouldBeTrue()
                (converted as ForNodeSpec).init shouldBe VariableDeclarationNodeSpec("a", TypeNodeSpec.int(), IntLiteralNodeSpec(0))
                converted.condition shouldBe BooleanLiteralNodeSpec(true)
                converted.update shouldBe VariableAdditionAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                converted.body.statements.shouldBeEmpty()
            }
            it("should not convert ForNodeSpec") {
                val node = ForNodeSpec(
                    VariableDeclarationNodeSpec("a", TypeNodeSpec.int(), IntLiteralNodeSpec(0)),
                    BooleanLiteralNodeSpec(true),
                    VariableAdditionAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1)),
                    CodeNodeSpec.empty(),
                )
                (StatementNodeSpec.of(node) === node).shouldBeTrue()
            }
            it("should not convert ForNodeSpec (anonymous)") {
                val node = ForNodeSpec(
                    VariableDeclarationNodeSpec("a", TypeNodeSpec.int(), IntLiteralNodeSpec(0)),
                    BooleanLiteralNodeSpec(true),
                    VariableAdditionAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1)),
                    CodeNodeSpec.empty(),
                )
                (StatementNodeSpec.of(node as StatementSpec) === node).shouldBeTrue()
            }
            it("should convert IfSpec") {
                val node = IfSpec(BooleanLiteralSpec(true), CodeSpec.empty(), CodeSpec.empty())
                val converted = StatementNodeSpec.of(node)
                converted.condition shouldBe BooleanLiteralNodeSpec(true)
                converted.thenBody.statements.shouldBeEmpty()
                converted.elseBody!!.statements.shouldBeEmpty()
            }
            it("should convert IfSpec (anonymous)") {
                val node = IfSpec(BooleanLiteralSpec(true), CodeSpec.empty(), CodeSpec.empty())
                val converted = StatementNodeSpec.of(node as StatementSpec)
                (converted is IfNodeSpec).shouldBeTrue()
                (converted as IfNodeSpec).condition shouldBe BooleanLiteralNodeSpec(true)
                converted.thenBody.statements.shouldBeEmpty()
                converted.elseBody!!.statements.shouldBeEmpty()
            }
            it("should not convert IfNodeSpec") {
                val node = IfNodeSpec(BooleanLiteralNodeSpec(true), CodeNodeSpec.empty(), CodeNodeSpec.empty())
                (StatementNodeSpec.of(node) === node).shouldBeTrue()
            }
            it("should not convert IfNodeSpec (anonymous)") {
                val node = IfNodeSpec(BooleanLiteralNodeSpec(true), CodeNodeSpec.empty(), CodeNodeSpec.empty())
                (StatementNodeSpec.of(node as StatementSpec) === node).shouldBeTrue()
            }
            it("should convert ReturnSpec") {
                val node = ReturnSpec(IntLiteralSpec(0))
                val converted = StatementNodeSpec.of(node)
                converted.value shouldBe IntLiteralNodeSpec(0)
            }
            it("should convert ReturnSpec (anonymous)") {
                val node = ReturnSpec(IntLiteralSpec(0))
                val converted = StatementNodeSpec.of(node as StatementSpec)
                (converted is ReturnNodeSpec).shouldBeTrue()
                (converted as ReturnNodeSpec).value shouldBe IntLiteralNodeSpec(0)
            }
            it("should not convert ReturnNodeSpec") {
                val node = ReturnNodeSpec(IntLiteralNodeSpec(0))
                (StatementNodeSpec.of(node) === node).shouldBeTrue()
            }
            it("should not convert ReturnNodeSpec (anonymous)") {
                val node = ReturnNodeSpec(IntLiteralNodeSpec(0))
                (StatementNodeSpec.of(node as StatementSpec) === node).shouldBeTrue()
            }
        }
    }

    describe("VariableDeclarationNodeSpec") {
        describe("dump") {
            it("should dump the correct output") {
                val value = VariableDeclarationNodeSpec("a", TypeNodeSpec.int(), IntLiteralNodeSpec(1), true)
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "val a: int = 1"
            }

            it("should output correct node") {
                val value = VariableDeclarationNodeSpec("a", TypeNodeSpec.int(), IntLiteralNodeSpec(1), true)
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                node.type!!.type shouldBe ShakeVariableType.Type.INTEGER
                node.type!!.name shouldBe "int"
                node.type!!.namespace.name shouldBe "int"
                node.type!!.namespace.nameToken.start shouldBe 7
                node.type!!.namespace.nameToken.end shouldBe 9
                node.type!!.namespace.nameToken.value shouldBe "int"
                node.type!!.namespace.parent shouldBe null
                node.type!!.namespace.dotToken shouldBe null
                node.colonToken!!.start shouldBe 5
                node.colonToken!!.end shouldBe 5
                node.colonToken!!.value shouldBe ":"
                node.isVal shouldBe true
                node.varToken.start shouldBe 0
                node.varToken.end shouldBe 2
                node.varToken.value shouldBe "val"
                node.varToken.type shouldBe ShakeTokenType.KEYWORD_VAL
                node.name shouldBe "a"
                node.nameToken.start shouldBe 4
                node.nameToken.end shouldBe 4
                node.nameToken.value shouldBe "a"
                node.assignmentToken!!.start shouldBe 11
                node.assignmentToken!!.end shouldBe 11
                node.assignmentToken!!.value shouldBe "="
                (node.value is ShakeIntegerLiteralNode) shouldBe true
                val valueNode = node.value as ShakeIntegerLiteralNode
                valueNode.value shouldBe 1
                valueNode.valueToken.start shouldBe 13
                valueNode.valueToken.end shouldBe 13
                valueNode.valueToken.value shouldBe "1"
            }

            it("should dump the correct output with anonymous type") {
                val value = VariableDeclarationNodeSpec("a", null, IntLiteralNodeSpec(1))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "var a = 1"
            }

            it("should output correct node with anonymous type") {
                val value = VariableDeclarationNodeSpec("a", null, IntLiteralNodeSpec(1))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                node.type shouldBe null
                node.colonToken shouldBe null
                node.isVal shouldBe false
                node.varToken.start shouldBe 0
                node.varToken.end shouldBe 2
                node.varToken.value shouldBe "var"
                node.name shouldBe "a"
                node.nameToken.start shouldBe 4
                node.nameToken.end shouldBe 4
                node.nameToken.value shouldBe "a"
                node.assignmentToken!!.start shouldBe 6
                node.assignmentToken!!.end shouldBe 6
                node.assignmentToken!!.value shouldBe "="
                (node.value is ShakeIntegerLiteralNode) shouldBe true
                val valueNode = node.value as ShakeIntegerLiteralNode
                valueNode.value shouldBe 1
                valueNode.valueToken.start shouldBe 8
                valueNode.valueToken.end shouldBe 8
                valueNode.valueToken.value shouldBe "1"
            }

            it("should dump the correct output without value") {
                val value = VariableDeclarationNodeSpec("a", TypeNodeSpec.int(), null)
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "var a: int"
            }

            it("should output correct node without value") {
                val value = VariableDeclarationNodeSpec("a", TypeNodeSpec.int(), null)
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                node.type!!.type shouldBe ShakeVariableType.Type.INTEGER
                node.type!!.name shouldBe "int"
                node.type!!.namespace.name shouldBe "int"
                node.type!!.namespace.nameToken.start shouldBe 7
                node.type!!.namespace.nameToken.end shouldBe 9
                node.type!!.namespace.nameToken.value shouldBe "int"
                node.type!!.namespace.parent shouldBe null
                node.type!!.namespace.dotToken shouldBe null
                node.colonToken!!.start shouldBe 5
                node.colonToken!!.end shouldBe 5
                node.colonToken!!.value shouldBe ":"
                node.isVal shouldBe false
                node.varToken.start shouldBe 0
                node.varToken.end shouldBe 2
                node.varToken.value shouldBe "var"
                node.name shouldBe "a"
                node.nameToken.start shouldBe 4
                node.nameToken.end shouldBe 4
                node.nameToken.value shouldBe "a"
                node.assignmentToken shouldBe null
                node.value shouldBe null
            }
        }
    }

    describe("WhileNodeSpec") {
        describe("dump") {
            it("should dump the correct output") {
                val value = WhileNodeSpec(BooleanLiteralNodeSpec(true), CodeNodeSpec.empty())
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "while (true) {}"
            }
            it("should output correct node") {
                val value = WhileNodeSpec(BooleanLiteralNodeSpec(true), CodeNodeSpec.empty())
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                node.whileToken.start shouldBe 0
                node.whileToken.end shouldBe 4
                node.whileToken.value shouldBe "while"
                node.lparenToken.start shouldBe 6
                node.lparenToken.end shouldBe 6
                node.lparenToken.value shouldBe "("
                (node.condition is ShakeTrueLiteralNode) shouldBe true
                val conditionNode = node.condition as ShakeTrueLiteralNode
                conditionNode.valueToken.start shouldBe 7
                conditionNode.valueToken.end shouldBe 10
                conditionNode.valueToken.value shouldBe "true"
                node.rparenToken.start shouldBe 11
                node.rparenToken.end shouldBe 11
                node.rparenToken.value shouldBe ")"
                node.body.lcurlyToken!!.start shouldBe 13
                node.body.lcurlyToken!!.end shouldBe 13
                node.body.lcurlyToken!!.value shouldBe "{"
                node.body.rcurlyToken!!.start shouldBe 14
                node.body.rcurlyToken!!.end shouldBe 14
                node.body.rcurlyToken!!.value shouldBe "}"
                node.body.children.shouldBeEmpty()
            }
        }
    }

    describe("DoWhileNodeSpec") {
        describe("dump") {
            it("should dump the correct output") {
                val value = DoWhileNodeSpec(CodeNodeSpec.empty(), BooleanLiteralNodeSpec(true))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "do {} while (true)"
            }
            it("should output correct node") {
                val value = DoWhileNodeSpec(CodeNodeSpec.empty(), BooleanLiteralNodeSpec(true))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                node.doToken.start shouldBe 0
                node.doToken.end shouldBe 1
                node.doToken.value shouldBe "do"
                node.body.lcurlyToken!!.start shouldBe 3
                node.body.lcurlyToken!!.end shouldBe 3
                node.body.lcurlyToken!!.value shouldBe "{"
                node.body.rcurlyToken!!.start shouldBe 4
                node.body.rcurlyToken!!.end shouldBe 4
                node.body.rcurlyToken!!.value shouldBe "}"
                node.body.children.shouldBeEmpty()
                node.whileToken.start shouldBe 6
                node.whileToken.end shouldBe 10
                node.whileToken.value shouldBe "while"
                node.lparenToken.start shouldBe 12
                node.lparenToken.end shouldBe 12
                node.lparenToken.value shouldBe "("
                (node.condition is ShakeTrueLiteralNode) shouldBe true
                val conditionNode = node.condition as ShakeTrueLiteralNode
                conditionNode.valueToken.start shouldBe 13
                conditionNode.valueToken.end shouldBe 16
                conditionNode.valueToken.value shouldBe "true"
                node.rparenToken.start shouldBe 17
                node.rparenToken.end shouldBe 17
                node.rparenToken.value shouldBe ")"
            }
        }
    }

    describe("ForNodeSpec") {
        describe("dump") {
            it("should dump the correct output") {
                val value = ForNodeSpec(
                    VariableDeclarationNodeSpec("a", TypeNodeSpec.int(), IntLiteralNodeSpec(0)),
                    BooleanLiteralNodeSpec(true),
                    VariableAdditionAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1)),
                    CodeNodeSpec.empty(),
                )
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "for (var a: int = 0; true; a += 1) {}"
            }
            it("should output correct node") {
                val value = ForNodeSpec(
                    VariableDeclarationNodeSpec("a", TypeNodeSpec.int(), IntLiteralNodeSpec(0)),
                    BooleanLiteralNodeSpec(true),
                    VariableAdditionAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1)),
                    CodeNodeSpec.empty(),
                )
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                node.forToken.start shouldBe 0
                node.forToken.end shouldBe 2
                node.forToken.value shouldBe "for"
                node.lparenToken.start shouldBe 4
                node.lparenToken.end shouldBe 4
                node.lparenToken.value shouldBe "("
                (node.init is ShakeLocalDeclarationNode) shouldBe true
                val initNode = node.init as ShakeLocalDeclarationNode
                initNode.isVal shouldBe false
                initNode.varToken.start shouldBe 5
                initNode.varToken.end shouldBe 7
                initNode.varToken.value shouldBe "var"
                initNode.name shouldBe "a"
                initNode.nameToken.start shouldBe 9
                initNode.nameToken.end shouldBe 9
                initNode.nameToken.value shouldBe "a"
                initNode.assignmentToken!!.start shouldBe 16
                initNode.assignmentToken!!.end shouldBe 16
                initNode.assignmentToken!!.value shouldBe "="
                (initNode.value is ShakeIntegerLiteralNode) shouldBe true
                val valueNode = initNode.value as ShakeIntegerLiteralNode
                valueNode.value shouldBe 0
                valueNode.valueToken.start shouldBe 18
                valueNode.valueToken.end shouldBe 18
                valueNode.valueToken.value shouldBe "0"
                node.semicolon1Token.start shouldBe 19
                node.semicolon1Token.end shouldBe 19
                node.semicolon1Token.value shouldBe ";"
                (node.condition is ShakeTrueLiteralNode) shouldBe true
                val conditionNode = node.condition as ShakeTrueLiteralNode
                conditionNode.valueToken.start shouldBe 21
                conditionNode.valueToken.end shouldBe 24
                conditionNode.valueToken.value shouldBe "true"
                node.semicolon2Token.start shouldBe 25
                node.semicolon2Token.end shouldBe 25
                node.semicolon2Token.value shouldBe ";"
                (node.update is ShakeVariableAddAssignmentNode) shouldBe true
                val updateNode = node.update as ShakeVariableAddAssignmentNode
                (updateNode.variable is ShakeVariableUsageNode) shouldBe true
                val variableNode = updateNode.variable as ShakeVariableUsageNode
                variableNode.identifier.name shouldBe "a"
                variableNode.identifier.nameToken.start shouldBe 27
                variableNode.identifier.nameToken.end shouldBe 27
                variableNode.identifier.nameToken.value shouldBe "a"
                node.rparenToken.start shouldBe 33
                node.rparenToken.end shouldBe 33
                node.rparenToken.value shouldBe ")"
                node.body.lcurlyToken!!.start shouldBe 35
                node.body.lcurlyToken!!.end shouldBe 35
                node.body.lcurlyToken!!.value shouldBe "{"
                node.body.rcurlyToken!!.start shouldBe 36
                node.body.rcurlyToken!!.end shouldBe 36
                node.body.rcurlyToken!!.value shouldBe "}"
                node.body.children.shouldBeEmpty()
            }
        }
    }

    describe("IfNodeSpec") {
        describe("dump") {
            it("should dump the correct output") {
                val value = IfNodeSpec(BooleanLiteralNodeSpec(true), CodeNodeSpec.empty(), CodeNodeSpec.empty())
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "if (true) {} else {}"
            }
            it("should output correct node") {
                val value = IfNodeSpec(BooleanLiteralNodeSpec(true), CodeNodeSpec.empty(), CodeNodeSpec.empty())
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                node.ifToken.start shouldBe 0
                node.ifToken.end shouldBe 1
                node.ifToken.value shouldBe "if"
                node.lparenToken.start shouldBe 3
                node.lparenToken.end shouldBe 3
                node.lparenToken.value shouldBe "("
                (node.condition is ShakeTrueLiteralNode) shouldBe true
                val conditionNode = node.condition as ShakeTrueLiteralNode
                conditionNode.valueToken.start shouldBe 4
                conditionNode.valueToken.end shouldBe 7
                conditionNode.valueToken.value shouldBe "true"
                node.rparenToken.start shouldBe 8
                node.rparenToken.end shouldBe 8
                node.rparenToken.value shouldBe ")"
                node.thenBody.lcurlyToken!!.start shouldBe 10
                node.thenBody.lcurlyToken!!.end shouldBe 10
                node.thenBody.lcurlyToken!!.value shouldBe "{"
                node.thenBody.rcurlyToken!!.start shouldBe 11
                node.thenBody.rcurlyToken!!.end shouldBe 11
                node.thenBody.rcurlyToken!!.value shouldBe "}"
                node.thenBody.children.shouldBeEmpty()
                node.elseToken!!.start shouldBe 13
                node.elseToken!!.end shouldBe 16
                node.elseToken!!.value shouldBe "else"
                node.elseBody!!.lcurlyToken!!.start shouldBe 18
                node.elseBody!!.lcurlyToken!!.end shouldBe 18
                node.elseBody!!.lcurlyToken!!.value shouldBe "{"
                node.elseBody!!.rcurlyToken!!.start shouldBe 19
                node.elseBody!!.rcurlyToken!!.end shouldBe 19
                node.elseBody!!.rcurlyToken!!.value shouldBe "}"
                node.elseBody!!.children.shouldBeEmpty()
            }
        }
    }

    describe("ReturnNodeSpec") {
        describe("dump") {
            it("should dump the correct output") {
                val value = ReturnNodeSpec(IntLiteralNodeSpec(0))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "return 0"
            }
            it("should output correct node") {
                val value = ReturnNodeSpec(IntLiteralNodeSpec(0))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                node.returnToken.start shouldBe 0
                node.returnToken.end shouldBe 5
                node.returnToken.value shouldBe "return"
                (node.value is ShakeIntegerLiteralNode) shouldBe true
                val valueNode = node.value as ShakeIntegerLiteralNode
                valueNode.value shouldBe 0
                valueNode.valueToken.start shouldBe 7
                valueNode.valueToken.end shouldBe 7
                valueNode.valueToken.value shouldBe "0"
            }
        }
    }
})
