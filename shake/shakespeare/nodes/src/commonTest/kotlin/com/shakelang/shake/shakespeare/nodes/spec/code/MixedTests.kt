package com.shakelang.shake.shakespeare.nodes.spec.code

import com.shakelang.shake.shakespeare.nodes.spec.NamespaceNodeSpec
import com.shakelang.shake.shakespeare.spec.code.*
import com.shakelang.util.testlib.FlatTestSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class MixedTests : FlatTestSpec({

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
        }
    }
})
