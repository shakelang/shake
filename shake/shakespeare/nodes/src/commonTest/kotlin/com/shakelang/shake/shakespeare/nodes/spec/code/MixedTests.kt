package com.shakelang.shake.shakespeare.nodes.spec.code

import com.shakelang.shake.parser.node.values.ShakeVariableUsageNode
import com.shakelang.shake.parser.node.values.factor.ShakeIntegerLiteralNode
import com.shakelang.shake.shakespeare.nodes.spec.NamespaceNodeSpec
import com.shakelang.shake.shakespeare.nodes.spec.NodeContext
import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.code.*
import com.shakelang.util.testlib.FlatTestSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe

class MixedTests : FlatTestSpec(
    {

        describe("ValuedStatementNodeSpec") {
            describe("of()") {
                it("should not change ValuedStatementNodeSpec") {
                    val node = VariableAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    (ValuedStatementNodeSpec.of(node) === node).shouldBeTrue()
                }

                it("should convert assignment") {
                    val node = VariableAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = ValuedStatementNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
                it("should convert assignment (anonymous)") {
                    val node = VariableAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = ValuedStatementNodeSpec.of(node as ValuedStatementSpec)
                    (converted is VariableAssignmentNodeSpec).shouldBeTrue()
                    (converted as VariableAssignmentNodeSpec).name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
                it("should not change VariableAssignmentNodeSpec") {
                    val node = VariableAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    (ValuedStatementNodeSpec.of(node) === node).shouldBeTrue()
                }

                it("should convert addition-assignment") {
                    val node = VariableAdditionAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = ValuedStatementNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
                it("should convert addition-assignment (anonymous)") {
                    val node = VariableAdditionAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = ValuedStatementNodeSpec.of(node as ValuedStatementSpec)
                    (converted is VariableAdditionAssignmentNodeSpec).shouldBeTrue()
                    (converted as VariableAdditionAssignmentNodeSpec).name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
                it("should not change VariableAdditionAssignmentNodeSpec") {
                    val node = VariableAdditionAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    (ValuedStatementNodeSpec.of(node) === node).shouldBeTrue()
                }

                it("should convert subtraction-assignment") {
                    val node = VariableSubtractionAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = ValuedStatementNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
                it("should convert subtraction-assignment (anonymous)") {
                    val node = VariableSubtractionAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = ValuedStatementNodeSpec.of(node as ValuedStatementSpec)
                    (converted is VariableSubtractionAssignmentNodeSpec).shouldBeTrue()
                    (converted as VariableSubtractionAssignmentNodeSpec).name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
                it("should not change VariableSubtractionAssignmentNodeSpec") {
                    val node = VariableSubtractionAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    (ValuedStatementNodeSpec.of(node) === node).shouldBeTrue()
                }

                it("should convert multiplication-assignment") {
                    val node = VariableMultiplicationAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = ValuedStatementNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
                it("should convert multiplication-assignment (anonymous)") {
                    val node = VariableMultiplicationAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = ValuedStatementNodeSpec.of(node as ValuedStatementSpec)
                    (converted is VariableMultiplicationAssignmentNodeSpec).shouldBeTrue()
                    (converted as VariableMultiplicationAssignmentNodeSpec).name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
                it("should not change VariableMultiplicationAssignmentNodeSpec") {
                    val node = VariableMultiplicationAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    (ValuedStatementNodeSpec.of(node) === node).shouldBeTrue()
                }

                it("should convert division-assignment") {
                    val node = VariableDivisionAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = ValuedStatementNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
                it("should convert division-assignment (anonymous)") {
                    val node = VariableDivisionAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = ValuedStatementNodeSpec.of(node as ValuedStatementSpec)
                    (converted is VariableDivisionAssignmentNodeSpec).shouldBeTrue()
                    (converted as VariableDivisionAssignmentNodeSpec).name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
                it("should not change VariableDivisionAssignmentNodeSpec") {
                    val node = VariableDivisionAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    (ValuedStatementNodeSpec.of(node) === node).shouldBeTrue()
                }

                it("should convert modulo-assignment") {
                    val node = VariableModuloAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = ValuedStatementNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
                it("should convert modulo-assignment (anonymous)") {
                    val node = VariableModuloAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = ValuedStatementNodeSpec.of(node as ValuedStatementSpec)
                    (converted is VariableModuloAssignmentNodeSpec).shouldBeTrue()
                    (converted as VariableModuloAssignmentNodeSpec).name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
                it("should not change VariableModuloAssignmentNodeSpec") {
                    val node = VariableModuloAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    (ValuedStatementNodeSpec.of(node) === node).shouldBeTrue()
                }

                it("should convert power-assignment") {
                    val node = VariablePowerAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = ValuedStatementNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
                it("should convert power-assignment (anonymous)") {
                    val node = VariablePowerAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = ValuedStatementNodeSpec.of(node as ValuedStatementSpec)
                    (converted is VariablePowerAssignmentNodeSpec).shouldBeTrue()
                    (converted as VariablePowerAssignmentNodeSpec).name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
                it("should not change VariablePowerAssignmentNodeSpec") {
                    val node = VariablePowerAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    (ValuedStatementNodeSpec.of(node) === node).shouldBeTrue()
                }

                it("should convert increment-before") {
                    val node = VariableIncrementBeforeSpec(NamespaceNodeSpec("a"))
                    val converted = ValuedStatementNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                }
                it("should convert increment-before (anonymous)") {
                    val node = VariableIncrementBeforeSpec(NamespaceNodeSpec("a"))
                    val converted = ValuedStatementNodeSpec.of(node as ValuedStatementSpec)
                    (converted is VariableIncrementBeforeNodeSpec).shouldBeTrue()
                    (converted as VariableIncrementBeforeNodeSpec).name.name shouldBe arrayOf("a")
                }
                it("should not change VariableIncrementBeforeNodeSpec") {
                    val node = VariableIncrementBeforeNodeSpec(NamespaceNodeSpec("a"))
                    (ValuedStatementNodeSpec.of(node) === node).shouldBeTrue()
                }

                it("should convert increment-after") {
                    val node = VariableIncrementAfterSpec(NamespaceNodeSpec("a"))
                    val converted = ValuedStatementNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                }
                it("should convert increment-after (anonymous)") {
                    val node = VariableIncrementAfterSpec(NamespaceNodeSpec("a"))
                    val converted = ValuedStatementNodeSpec.of(node as ValuedStatementSpec)
                    (converted is VariableIncrementAfterNodeSpec).shouldBeTrue()
                    (converted as VariableIncrementAfterNodeSpec).name.name shouldBe arrayOf("a")
                }
                it("should not change VariableIncrementAfterNodeSpec") {
                    val node = VariableIncrementAfterNodeSpec(NamespaceNodeSpec("a"))
                    (ValuedStatementNodeSpec.of(node) === node).shouldBeTrue()
                }

                it("should convert decrement-before") {
                    val node = VariableDecrementBeforeSpec(NamespaceNodeSpec("a"))
                    val converted = ValuedStatementNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                }
                it("should convert decrement-before (anonymous)") {
                    val node = VariableDecrementBeforeSpec(NamespaceNodeSpec("a"))
                    val converted = ValuedStatementNodeSpec.of(node as ValuedStatementSpec)
                    (converted is VariableDecrementBeforeNodeSpec).shouldBeTrue()
                    (converted as VariableDecrementBeforeNodeSpec).name.name shouldBe arrayOf("a")
                }
                it("should not change VariableDecrementBeforeNodeSpec") {
                    val node = VariableDecrementBeforeNodeSpec(NamespaceNodeSpec("a"))
                    (ValuedStatementNodeSpec.of(node) === node).shouldBeTrue()
                }

                it("should convert decrement-after") {
                    val node = VariableDecrementAfterSpec(NamespaceNodeSpec("a"))
                    val converted = ValuedStatementNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                }
                it("should convert decrement-after (anonymous)") {
                    val node = VariableDecrementAfterSpec(NamespaceNodeSpec("a"))
                    val converted = ValuedStatementNodeSpec.of(node as ValuedStatementSpec)
                    (converted is VariableDecrementAfterNodeSpec).shouldBeTrue()
                    (converted as VariableDecrementAfterNodeSpec).name.name shouldBe arrayOf("a")
                }
                it("should not change VariableDecrementAfterNodeSpec") {
                    val node = VariableDecrementAfterNodeSpec(NamespaceNodeSpec("a"))
                    (ValuedStatementNodeSpec.of(node) === node).shouldBeTrue()
                }
                it("should convert function call") {
                    val node = FunctionCallSpec(NamespaceNodeSpec("a"), emptyList())
                    val converted = ValuedStatementNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.arguments.shouldBeEmpty()
                }
                it("should convert function call (anonymous)") {
                    val node = FunctionCallSpec(NamespaceNodeSpec("a"), emptyList())
                    val converted = ValuedStatementNodeSpec.of(node as ValuedStatementSpec)
                    (converted is FunctionCallNodeSpec).shouldBeTrue()
                    (converted as FunctionCallNodeSpec).name.name shouldBe arrayOf("a")
                    converted.arguments.shouldBeEmpty()
                }
            }
        }

        describe("VariableAssignmentNodeSpec") {

            describe("constructor") {
                it("should create a VariableAssignmentNodeSpec") {
                    val value = VariableAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    value.name.name shouldBe arrayOf("a")
                    value.value shouldBe IntLiteralNodeSpec(1)
                }
            }

            describe("of()") {
                it("should not change VariableAssignmentNodeSpec") {
                    val node = VariableAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    (VariableAssignmentNodeSpec.of(node) === node).shouldBeTrue()
                }

                it("should convert assignment") {
                    val node = VariableAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = VariableAssignmentNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
                it("should convert assignment (anonymous)") {
                    val node = VariableAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = VariableAssignmentNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
            }

            describe("dump") {
                it("should dump the correct output") {
                    val value = VariableAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val nodeContext = NodeContext()
                    value.dump(GenerationContext(), nodeContext)
                    nodeContext.built.toString() shouldBe "a = 1"
                }

                it("should output correct node") {
                    val value = VariableAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val nodeContext = NodeContext()
                    val node = value.dump(GenerationContext(), nodeContext)
                    (node.variable is ShakeVariableUsageNode) shouldBe true
                    val variable = node.variable as ShakeVariableUsageNode
                    variable.identifier.name shouldBe "a"
                    variable.identifier.nameToken.start shouldBe 0
                    variable.identifier.nameToken.end shouldBe 0
                    variable.identifier.nameToken.value shouldBe "a"
                    node.operatorToken.start shouldBe 2
                    node.operatorToken.end shouldBe 2
                    node.operatorToken.value shouldBe "="
                    (node.value is ShakeIntegerLiteralNode) shouldBe true
                    val valueNode = node.value as ShakeIntegerLiteralNode
                    valueNode.value shouldBe 1
                    valueNode.valueToken.start shouldBe 4
                    valueNode.valueToken.end shouldBe 4
                    valueNode.valueToken.value shouldBe "1"
                }
            }
        }

        describe("VariableAdditionAssignmentNodeSpec") {

            describe("constructor") {
                it("should create a VariableAdditionAssignmentNodeSpec") {
                    val value = VariableAdditionAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    value.name.name shouldBe arrayOf("a")
                    value.value shouldBe IntLiteralNodeSpec(1)
                }
            }

            describe("of()") {
                it("should not change VariableAdditionAssignmentNodeSpec") {
                    val node = VariableAdditionAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    (VariableAdditionAssignmentNodeSpec.of(node) === node).shouldBeTrue()
                }

                it("should convert addition-assignment") {
                    val node = VariableAdditionAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = VariableAdditionAssignmentNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
                it("should convert addition-assignment (anonymous)") {
                    val node = VariableAdditionAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = VariableAdditionAssignmentNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
            }

            describe("dump") {
                it("should dump the correct output") {
                    val value = VariableAdditionAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val nodeContext = NodeContext()
                    value.dump(GenerationContext(), nodeContext)
                    nodeContext.built.toString() shouldBe "a += 1"
                }

                it("should output correct node") {
                    val value = VariableAdditionAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val nodeContext = NodeContext()
                    val node = value.dump(GenerationContext(), nodeContext)
                    (node.variable is ShakeVariableUsageNode) shouldBe true
                    val variable = node.variable as ShakeVariableUsageNode
                    variable.identifier.name shouldBe "a"
                    variable.identifier.nameToken.start shouldBe 0
                    variable.identifier.nameToken.end shouldBe 0
                    variable.identifier.nameToken.value shouldBe "a"
                    node.operatorToken.start shouldBe 2
                    node.operatorToken.end shouldBe 3
                    node.operatorToken.value shouldBe "+="
                    (node.value is ShakeIntegerLiteralNode) shouldBe true
                    val valueNode = node.value as ShakeIntegerLiteralNode
                    valueNode.value shouldBe 1
                    valueNode.valueToken.start shouldBe 5
                    valueNode.valueToken.end shouldBe 5
                    valueNode.valueToken.value shouldBe "1"
                }
            }
        }

        describe("VariableSubtractionAssignmentNodeSpec") {

            describe("constructor") {
                it("should create a VariableSubtractionAssignmentNodeSpec") {
                    val value = VariableSubtractionAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    value.name.name shouldBe arrayOf("a")
                    value.value shouldBe IntLiteralNodeSpec(1)
                }
            }

            describe("of()") {
                it("should not change VariableSubtractionAssignmentNodeSpec") {
                    val node = VariableSubtractionAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    (VariableSubtractionAssignmentNodeSpec.of(node) === node).shouldBeTrue()
                }

                it("should convert subtraction-assignment") {
                    val node = VariableSubtractionAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = VariableSubtractionAssignmentNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
                it("should convert subtraction-assignment (anonymous)") {
                    val node = VariableSubtractionAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = VariableSubtractionAssignmentNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
            }

            describe("dump") {
                it("should dump the correct output") {
                    val value = VariableSubtractionAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val nodeContext = NodeContext()
                    value.dump(GenerationContext(), nodeContext)
                    nodeContext.built.toString() shouldBe "a -= 1"
                }

                it("should output correct node") {
                    val value = VariableSubtractionAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val nodeContext = NodeContext()
                    val node = value.dump(GenerationContext(), nodeContext)
                    (node.variable is ShakeVariableUsageNode) shouldBe true
                    val variable = node.variable as ShakeVariableUsageNode
                    variable.identifier.name shouldBe "a"
                    variable.identifier.nameToken.start shouldBe 0
                    variable.identifier.nameToken.end shouldBe 0
                    variable.identifier.nameToken.value shouldBe "a"
                    node.operatorToken.start shouldBe 2
                    node.operatorToken.end shouldBe 3
                    node.operatorToken.value shouldBe "-="
                    (node.value is ShakeIntegerLiteralNode) shouldBe true
                    val valueNode = node.value as ShakeIntegerLiteralNode
                    valueNode.value shouldBe 1
                    valueNode.valueToken.start shouldBe 5
                    valueNode.valueToken.end shouldBe 5
                    valueNode.valueToken.value shouldBe "1"
                }
            }
        }

        describe("VariableMultiplicationAssignmentNodeSpec") {

            describe("constructor") {
                it("should create a VariableMultiplicationAssignmentNodeSpec") {
                    val value = VariableMultiplicationAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    value.name.name shouldBe arrayOf("a")
                    value.value shouldBe IntLiteralNodeSpec(1)
                }
            }

            describe("of()") {
                it("should not change VariableMultiplicationAssignmentNodeSpec") {
                    val node = VariableMultiplicationAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    (VariableMultiplicationAssignmentNodeSpec.of(node) === node).shouldBeTrue()
                }

                it("should convert multiplication-assignment") {
                    val node = VariableMultiplicationAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = VariableMultiplicationAssignmentNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
                it("should convert multiplication-assignment (anonymous)") {
                    val node = VariableMultiplicationAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = VariableMultiplicationAssignmentNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
            }

            describe("dump") {
                it("should dump the correct output") {
                    val value = VariableMultiplicationAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val nodeContext = NodeContext()
                    value.dump(GenerationContext(), nodeContext)
                    nodeContext.built.toString() shouldBe "a *= 1"
                }

                it("should output correct node") {
                    val value = VariableMultiplicationAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val nodeContext = NodeContext()
                    val node = value.dump(GenerationContext(), nodeContext)
                    (node.variable is ShakeVariableUsageNode) shouldBe true
                    val variable = node.variable as ShakeVariableUsageNode
                    variable.identifier.name shouldBe "a"
                    variable.identifier.nameToken.start shouldBe 0
                    variable.identifier.nameToken.end shouldBe 0
                    variable.identifier.nameToken.value shouldBe "a"
                    node.operatorToken.start shouldBe 2
                    node.operatorToken.end shouldBe 3
                    node.operatorToken.value shouldBe "*="
                    (node.value is ShakeIntegerLiteralNode) shouldBe true
                    val valueNode = node.value as ShakeIntegerLiteralNode
                    valueNode.value shouldBe 1
                    valueNode.valueToken.start shouldBe 5
                    valueNode.valueToken.end shouldBe 5
                    valueNode.valueToken.value shouldBe "1"
                }
            }
        }

        describe("VariableDivisionAssignmentNodeSpec") {

            describe("constructor") {
                it("should create a VariableDivisionAssignmentNodeSpec") {
                    val value = VariableDivisionAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    value.name.name shouldBe arrayOf("a")
                    value.value shouldBe IntLiteralNodeSpec(1)
                }
            }

            describe("of()") {
                it("should not change VariableDivisionAssignmentNodeSpec") {
                    val node = VariableDivisionAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    (VariableDivisionAssignmentNodeSpec.of(node) === node).shouldBeTrue()
                }

                it("should convert division-assignment") {
                    val node = VariableDivisionAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = VariableDivisionAssignmentNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
                it("should convert division-assignment (anonymous)") {
                    val node = VariableDivisionAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = VariableDivisionAssignmentNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
            }

            describe("dump") {
                it("should dump the correct output") {
                    val value = VariableDivisionAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val nodeContext = NodeContext()
                    value.dump(GenerationContext(), nodeContext)
                    nodeContext.built.toString() shouldBe "a /= 1"
                }

                it("should output correct node") {
                    val value = VariableDivisionAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val nodeContext = NodeContext()
                    val node = value.dump(GenerationContext(), nodeContext)
                    (node.variable is ShakeVariableUsageNode) shouldBe true
                    val variable = node.variable as ShakeVariableUsageNode
                    variable.identifier.name shouldBe "a"
                    variable.identifier.nameToken.start shouldBe 0
                    variable.identifier.nameToken.end shouldBe 0
                    variable.identifier.nameToken.value shouldBe "a"
                    node.operatorToken.start shouldBe 2
                    node.operatorToken.end shouldBe 3
                    node.operatorToken.value shouldBe "/="
                    (node.value is ShakeIntegerLiteralNode) shouldBe true
                    val valueNode = node.value as ShakeIntegerLiteralNode
                    valueNode.value shouldBe 1
                    valueNode.valueToken.start shouldBe 5
                    valueNode.valueToken.end shouldBe 5
                    valueNode.valueToken.value shouldBe "1"
                }
            }
        }

        describe("VariableModuloAssignmentNodeSpec") {

            describe("constructor") {
                it("should create a VariableModuloAssignmentNodeSpec") {
                    val value = VariableModuloAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    value.name.name shouldBe arrayOf("a")
                    value.value shouldBe IntLiteralNodeSpec(1)
                }
            }

            describe("of()") {
                it("should not change VariableModuloAssignmentNodeSpec") {
                    val node = VariableModuloAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    (VariableModuloAssignmentNodeSpec.of(node) === node).shouldBeTrue()
                }

                it("should convert modulo-assignment") {
                    val node = VariableModuloAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = VariableModuloAssignmentNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
                it("should convert modulo-assignment (anonymous)") {
                    val node = VariableModuloAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = VariableModuloAssignmentNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
            }

            describe("dump") {
                it("should dump the correct output") {
                    val value = VariableModuloAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val nodeContext = NodeContext()
                    value.dump(GenerationContext(), nodeContext)
                    nodeContext.built.toString() shouldBe "a %= 1"
                }

                it("should output correct node") {
                    val value = VariableModuloAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val nodeContext = NodeContext()
                    val node = value.dump(GenerationContext(), nodeContext)
                    (node.variable is ShakeVariableUsageNode) shouldBe true
                    val variable = node.variable as ShakeVariableUsageNode
                    variable.identifier.name shouldBe "a"
                    variable.identifier.nameToken.start shouldBe 0
                    variable.identifier.nameToken.end shouldBe 0
                    variable.identifier.nameToken.value shouldBe "a"
                    node.operatorToken.start shouldBe 2
                    node.operatorToken.end shouldBe 3
                    node.operatorToken.value shouldBe "%="
                    (node.value is ShakeIntegerLiteralNode) shouldBe true
                    val valueNode = node.value as ShakeIntegerLiteralNode
                    valueNode.value shouldBe 1
                    valueNode.valueToken.start shouldBe 5
                    valueNode.valueToken.end shouldBe 5
                    valueNode.valueToken.value shouldBe "1"
                }
            }
        }

        describe("VariablePowerAssignmentNodeSpec") {

            describe("constructor") {
                it("should create a VariablePowerAssignmentNodeSpec") {
                    val value = VariablePowerAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    value.name.name shouldBe arrayOf("a")
                    value.value shouldBe IntLiteralNodeSpec(1)
                }
            }

            describe("of()") {
                it("should not change VariablePowerAssignmentNodeSpec") {
                    val node = VariablePowerAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    (VariablePowerAssignmentNodeSpec.of(node) === node).shouldBeTrue()
                }

                it("should convert power-assignment") {
                    val node = VariablePowerAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = VariablePowerAssignmentNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
                it("should convert power-assignment (anonymous)") {
                    val node = VariablePowerAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val converted = VariablePowerAssignmentNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.value shouldBe IntLiteralNodeSpec(1)
                }
            }

            describe("dump") {
                it("should dump the correct output") {
                    val value = VariablePowerAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val nodeContext = NodeContext()
                    value.dump(GenerationContext(), nodeContext)
                    nodeContext.built.toString() shouldBe "a **= 1"
                }

                it("should output correct node") {
                    val value = VariablePowerAssignmentNodeSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                    val nodeContext = NodeContext()
                    val node = value.dump(GenerationContext(), nodeContext)
                    (node.variable is ShakeVariableUsageNode) shouldBe true
                    val variable = node.variable as ShakeVariableUsageNode
                    variable.identifier.name shouldBe "a"
                    variable.identifier.nameToken.start shouldBe 0
                    variable.identifier.nameToken.end shouldBe 0
                    variable.identifier.nameToken.value shouldBe "a"
                    node.operatorToken.start shouldBe 2
                    node.operatorToken.end shouldBe 4
                    node.operatorToken.value shouldBe "**="
                    (node.value is ShakeIntegerLiteralNode)
                    val valueNode = node.value as ShakeIntegerLiteralNode
                    valueNode.value shouldBe 1
                    valueNode.valueToken.start shouldBe 6
                    valueNode.valueToken.end shouldBe 6
                    valueNode.valueToken.value shouldBe "1"
                }
            }
        }

        describe("VariableIncrementBeforeNodeSpec") {

            describe("constructor") {
                it("should create a VariableIncrementBeforeNodeSpec") {
                    val value = VariableIncrementBeforeNodeSpec(NamespaceNodeSpec("a"))
                    value.name.name shouldBe arrayOf("a")
                }
            }

            describe("of()") {
                it("should not change VariableIncrementBeforeNodeSpec") {
                    val node = VariableIncrementBeforeNodeSpec(NamespaceNodeSpec("a"))
                    (VariableIncrementBeforeNodeSpec.of(node) === node).shouldBeTrue()
                }

                it("should convert increment-before") {
                    val node = VariableIncrementBeforeSpec(NamespaceNodeSpec("a"))
                    val converted = VariableIncrementBeforeNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                }
                it("should convert increment-before (anonymous)") {
                    val node = VariableIncrementBeforeSpec(NamespaceNodeSpec("a"))
                    val converted = VariableIncrementBeforeNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                }
            }

            describe("dump") {
                it("should dump the correct output") {
                    val value = VariableIncrementBeforeNodeSpec(NamespaceNodeSpec("a"))
                    val nodeContext = NodeContext()
                    value.dump(GenerationContext(), nodeContext)
                    nodeContext.built.toString() shouldBe "++a"
                }

                it("should output correct node") {
                    val value = VariableIncrementBeforeNodeSpec(NamespaceNodeSpec("a"))
                    val nodeContext = NodeContext()
                    val node = value.dump(GenerationContext(), nodeContext)
                    node.operatorToken.start shouldBe 0
                    node.operatorToken.end shouldBe 1
                    node.operatorToken.value shouldBe "++"
                    (node.variable is ShakeVariableUsageNode) shouldBe true
                    val variable = node.variable as ShakeVariableUsageNode
                    variable.identifier.name shouldBe "a"
                    variable.identifier.nameToken.start shouldBe 2
                    variable.identifier.nameToken.end shouldBe 2
                    variable.identifier.nameToken.value shouldBe "a"
                }
            }
        }

        describe("VariableIncrementAfterNodeSpec") {

            describe("constructor") {
                it("should create a VariableIncrementAfterNodeSpec") {
                    val value = VariableIncrementAfterNodeSpec(NamespaceNodeSpec("a"))
                    value.name.name shouldBe arrayOf("a")
                }
            }

            describe("of()") {
                it("should not change VariableIncrementAfterNodeSpec") {
                    val node = VariableIncrementAfterNodeSpec(NamespaceNodeSpec("a"))
                    (VariableIncrementAfterNodeSpec.of(node) === node).shouldBeTrue()
                }

                it("should convert increment-after") {
                    val node = VariableIncrementAfterSpec(NamespaceNodeSpec("a"))
                    val converted = VariableIncrementAfterNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                }
                it("should convert increment-after (anonymous)") {
                    val node = VariableIncrementAfterSpec(NamespaceNodeSpec("a"))
                    val converted = VariableIncrementAfterNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                }
            }

            describe("dump") {
                it("should dump the correct output") {
                    val value = VariableIncrementAfterNodeSpec(NamespaceNodeSpec("a"))
                    val nodeContext = NodeContext()
                    value.dump(GenerationContext(), nodeContext)
                    nodeContext.built.toString() shouldBe "a++"
                }

                it("should output correct node") {
                    val value = VariableIncrementAfterNodeSpec(NamespaceNodeSpec("a"))
                    val nodeContext = NodeContext()
                    val node = value.dump(GenerationContext(), nodeContext)
                    (node.variable is ShakeVariableUsageNode) shouldBe true
                    val variable = node.variable as ShakeVariableUsageNode
                    variable.identifier.name shouldBe "a"
                    variable.identifier.nameToken.start shouldBe 0
                    variable.identifier.nameToken.end shouldBe 0
                    variable.identifier.nameToken.value shouldBe "a"
                    node.operatorToken.start shouldBe 1
                    node.operatorToken.end shouldBe 2
                    node.operatorToken.value shouldBe "++"
                }
            }
        }

        describe("VariableDecrementBeforeNodeSpec") {

            describe("constructor") {
                it("should create a VariableDecrementBeforeNodeSpec") {
                    val value = VariableDecrementBeforeNodeSpec(NamespaceNodeSpec("a"))
                    value.name.name shouldBe arrayOf("a")
                }
            }

            describe("of()") {
                it("should not change VariableDecrementBeforeNodeSpec") {
                    val node = VariableDecrementBeforeNodeSpec(NamespaceNodeSpec("a"))
                    (VariableDecrementBeforeNodeSpec.of(node) === node).shouldBeTrue()
                }

                it("should convert decrement-before") {
                    val node = VariableDecrementBeforeSpec(NamespaceNodeSpec("a"))
                    val converted = VariableDecrementBeforeNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                }
                it("should convert decrement-before (anonymous)") {
                    val node = VariableDecrementBeforeSpec(NamespaceNodeSpec("a"))
                    val converted = VariableDecrementBeforeNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                }
            }

            describe("dump") {
                it("should dump the correct output") {
                    val value = VariableDecrementBeforeNodeSpec(NamespaceNodeSpec("a"))
                    val nodeContext = NodeContext()
                    value.dump(GenerationContext(), nodeContext)
                    nodeContext.built.toString() shouldBe "--a"
                }

                it("should output correct node") {
                    val value = VariableDecrementBeforeNodeSpec(NamespaceNodeSpec("a"))
                    val nodeContext = NodeContext()
                    val node = value.dump(GenerationContext(), nodeContext)
                    node.operatorToken.start shouldBe 0
                    node.operatorToken.end shouldBe 1
                    node.operatorToken.value shouldBe "--"
                    (node.variable is ShakeVariableUsageNode) shouldBe true
                    val variable = node.variable as ShakeVariableUsageNode
                    variable.identifier.name shouldBe "a"
                    variable.identifier.nameToken.start shouldBe 2
                    variable.identifier.nameToken.end shouldBe 2
                    variable.identifier.nameToken.value shouldBe "a"
                }
            }
        }

        describe("VariableDecrementAfterNodeSpec") {

            describe("constructor") {
                it("should create a VariableDecrementAfterNodeSpec") {
                    val value = VariableDecrementAfterNodeSpec(NamespaceNodeSpec("a"))
                    value.name.name shouldBe arrayOf("a")
                }
            }

            describe("of()") {
                it("should not change VariableDecrementAfterNodeSpec") {
                    val node = VariableDecrementAfterNodeSpec(NamespaceNodeSpec("a"))
                    (VariableDecrementAfterNodeSpec.of(node) === node).shouldBeTrue()
                }

                it("should convert decrement-after") {
                    val node = VariableDecrementAfterSpec(NamespaceNodeSpec("a"))
                    val converted = VariableDecrementAfterNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                }
                it("should convert decrement-after (anonymous)") {
                    val node = VariableDecrementAfterSpec(NamespaceNodeSpec("a"))
                    val converted = VariableDecrementAfterNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                }
            }

            describe("dump") {
                it("should dump the correct output") {
                    val value = VariableDecrementAfterNodeSpec(NamespaceNodeSpec("a"))
                    val nodeContext = NodeContext()
                    value.dump(GenerationContext(), nodeContext)
                    nodeContext.built.toString() shouldBe "a--"
                }

                it("should output correct node") {
                    val value = VariableDecrementAfterNodeSpec(NamespaceNodeSpec("a"))
                    val nodeContext = NodeContext()
                    val node = value.dump(GenerationContext(), nodeContext)
                    (node.variable is ShakeVariableUsageNode) shouldBe true
                    val variable = node.variable as ShakeVariableUsageNode
                    variable.identifier.name shouldBe "a"
                    variable.identifier.nameToken.start shouldBe 0
                    variable.identifier.nameToken.end shouldBe 0
                    variable.identifier.nameToken.value shouldBe "a"
                    node.operatorToken.start shouldBe 1
                    node.operatorToken.end shouldBe 2
                    node.operatorToken.value shouldBe "--"
                }
            }
        }

        describe("FunctionCallNodeSpec") {

            describe("constructor") {
                it("should create a FunctionCallNodeSpec") {
                    val value = FunctionCallNodeSpec(NamespaceNodeSpec("a"), emptyList())
                    value.name.name shouldBe arrayOf("a")
                    value.arguments.shouldBeEmpty()
                }
            }

            describe("of()") {
                it("should not change FunctionCallNodeSpec") {
                    val node = FunctionCallNodeSpec(NamespaceNodeSpec("a"), emptyList())
                    (FunctionCallNodeSpec.of(node) === node).shouldBeTrue()
                }

                it("should convert function call") {
                    val node = FunctionCallSpec(NamespaceNodeSpec("a"), emptyList())
                    val converted = FunctionCallNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.arguments.shouldBeEmpty()
                }
                it("should convert function call (anonymous)") {
                    val node = FunctionCallSpec(NamespaceNodeSpec("a"), emptyList())
                    val converted = FunctionCallNodeSpec.of(node)
                    converted.name.name shouldBe arrayOf("a")
                    converted.arguments.shouldBeEmpty()
                }
            }

            describe("dump") {
                it("should dump the correct output") {
                    val value = FunctionCallNodeSpec(NamespaceNodeSpec("a"), emptyList())
                    val nodeContext = NodeContext()
                    value.dump(GenerationContext(), nodeContext)
                    nodeContext.built.toString() shouldBe "a()"
                }

                it("should output correct node") {
                    val value = FunctionCallNodeSpec(NamespaceNodeSpec("a"), emptyList())
                    val nodeContext = NodeContext()
                    val node = value.dump(GenerationContext(), nodeContext)
                    (node.function is ShakeVariableUsageNode) shouldBe true
                    val function = node.function as ShakeVariableUsageNode
                    function.identifier.name shouldBe "a"
                    function.identifier.nameToken.start shouldBe 0
                    function.identifier.nameToken.end shouldBe 0
                    function.identifier.nameToken.value shouldBe "a"
                    node.lparenToken.start shouldBe 1
                    node.lparenToken.end shouldBe 1
                    node.lparenToken.value shouldBe "("
                    node.rparenToken.start shouldBe 2
                    node.rparenToken.end shouldBe 2
                    node.rparenToken.value shouldBe ")"
                    node.args.shouldBeEmpty()
                }

                it("should dump the correct output with arguments") {
                    val value = FunctionCallNodeSpec(NamespaceNodeSpec("a"), listOf(IntLiteralNodeSpec(1)))
                    val nodeContext = NodeContext()
                    value.dump(GenerationContext(), nodeContext)
                    nodeContext.built.toString() shouldBe "a(1)"
                }

                it("should output correct node with arguments") {
                    val value = FunctionCallNodeSpec(NamespaceNodeSpec("a"), listOf(IntLiteralNodeSpec(1)))
                    val nodeContext = NodeContext()
                    val node = value.dump(GenerationContext(), nodeContext)
                    (node.function is ShakeVariableUsageNode) shouldBe true
                    val function = node.function as ShakeVariableUsageNode
                    function.identifier.name shouldBe "a"
                    function.identifier.nameToken.start shouldBe 0
                    function.identifier.nameToken.end shouldBe 0
                    function.identifier.nameToken.value shouldBe "a"
                    node.lparenToken.start shouldBe 1
                    node.lparenToken.end shouldBe 1
                    node.lparenToken.value shouldBe "("
                    (node.args.size == 1).shouldBeTrue()
                    (node.args[0] is ShakeIntegerLiteralNode) shouldBe true
                    val argument = node.args[0] as ShakeIntegerLiteralNode
                    argument.value shouldBe 1
                    argument.valueToken.start shouldBe 2
                    argument.valueToken.end shouldBe 2
                    argument.valueToken.value shouldBe "1"
                    node.rparenToken.start shouldBe 3
                    node.rparenToken.end shouldBe 3
                    node.rparenToken.value shouldBe ")"
                }
            }
        }

        describe("extensions") {
            it("ValuedStatementSpec#toNodeSpec()") {
                val node = VariableAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                val converted = (node as ValuedStatementSpec).toNodeSpec()
                (converted is VariableAssignmentNodeSpec) shouldBe true
                (converted as VariableAssignmentNodeSpec).name.name shouldBe arrayOf("a")
                converted.value shouldBe IntLiteralNodeSpec(1)
            }

            it("VariableAssignmentSpec#toNodeSpec()") {
                val node = VariableAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                val converted = node.toNodeSpec()
                converted.name.name shouldBe arrayOf("a")
                converted.value shouldBe IntLiteralNodeSpec(1)
            }

            it("VariableAdditionAssignmentSpec#toNodeSpec()") {
                val node = VariableAdditionAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                val converted = node.toNodeSpec()
                converted.name.name shouldBe arrayOf("a")
                converted.value shouldBe IntLiteralNodeSpec(1)
            }

            it("VariableSubtractionAssignmentSpec#toNodeSpec()") {
                val node = VariableSubtractionAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                val converted = node.toNodeSpec()
                converted.name.name shouldBe arrayOf("a")
                converted.value shouldBe IntLiteralNodeSpec(1)
            }

            it("VariableMultiplicationAssignmentSpec#toNodeSpec()") {
                val node = VariableMultiplicationAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                val converted = node.toNodeSpec()
                converted.name.name shouldBe arrayOf("a")
                converted.value shouldBe IntLiteralNodeSpec(1)
            }

            it("VariableDivisionAssignmentSpec#toNodeSpec()") {
                val node = VariableDivisionAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                val converted = node.toNodeSpec()
                converted.name.name shouldBe arrayOf("a")
                converted.value shouldBe IntLiteralNodeSpec(1)
            }

            it("VariableModuloAssignmentSpec#toNodeSpec()") {
                val node = VariableModuloAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                val converted = node.toNodeSpec()
                converted.name.name shouldBe arrayOf("a")
                converted.value shouldBe IntLiteralNodeSpec(1)
            }

            it("VariablePowerAssignmentSpec#toNodeSpec()") {
                val node = VariablePowerAssignmentSpec(NamespaceNodeSpec("a"), IntLiteralNodeSpec(1))
                val converted = node.toNodeSpec()
                converted.name.name shouldBe arrayOf("a")
                converted.value shouldBe IntLiteralNodeSpec(1)
            }

            it("VariableIncrementBeforeSpec#toNodeSpec()") {
                val node = VariableIncrementBeforeSpec(NamespaceNodeSpec("a"))
                val converted = node.toNodeSpec()
                converted.name.name shouldBe arrayOf("a")
            }

            it("VariableIncrementAfterSpec#toNodeSpec()") {
                val node = VariableIncrementAfterSpec(NamespaceNodeSpec("a"))
                val converted = node.toNodeSpec()
                converted.name.name shouldBe arrayOf("a")
            }

            it("VariableDecrementBeforeSpec#toNodeSpec()") {
                val node = VariableDecrementBeforeSpec(NamespaceNodeSpec("a"))
                val converted = node.toNodeSpec()
                converted.name.name shouldBe arrayOf("a")
            }

            it("VariableDecrementAfterSpec#toNodeSpec()") {
                val node = VariableDecrementAfterSpec(NamespaceNodeSpec("a"))
                val converted = node.toNodeSpec()
                converted.name.name shouldBe arrayOf("a")
            }

            it("FunctionCallSpec#toNodeSpec()") {
                val node = FunctionCallSpec(NamespaceNodeSpec("a"), emptyList())
                val converted = node.toNodeSpec()
                converted.name.name shouldBe arrayOf("a")
                converted.arguments.shouldBeEmpty()
            }
        }
    },
)
