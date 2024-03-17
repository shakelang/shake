package com.shakelang.shake.shakespeare.spec.code

import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.NamespaceSpec
import com.shakelang.util.testlib.TestSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class MixedTests : TestSpec({

    describe("VariableAssignmentSpec") {
        it("create") {
            val spec = VariableAssignmentSpec(NamespaceSpec("test"), ValueSpec.of("test"))
            spec.name shouldBe NamespaceSpec("test")
            spec.value shouldBe ValueSpec.of("test")
        }

        it("generate") {
            val spec = VariableAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec.generate(GenerationContext()) shouldBe "variable = test"
        }

        it("equals") {
            val spec = VariableAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec shouldBe spec
            spec shouldBe VariableAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec shouldNotBe VariableAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test2"))
            spec shouldNotBe VariableAssignmentSpec(NamespaceSpec("variable2"), ValueSpec.of("test"))
        }
    }

    describe("VariableAdditionAssignmentSpec") {
        it("create") {
            val spec = VariableAdditionAssignmentSpec(NamespaceSpec("test"), ValueSpec.of("test"))
            spec.name shouldBe NamespaceSpec("test")
            spec.value shouldBe ValueSpec.of("test")
        }

        it("generate") {
            val spec = VariableAdditionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec.generate(GenerationContext()) shouldBe "variable += test"
        }

        it("equals") {
            val spec = VariableAdditionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec shouldBe spec
            spec shouldBe VariableAdditionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec shouldNotBe VariableAdditionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test2"))
            spec shouldNotBe VariableAdditionAssignmentSpec(NamespaceSpec("variable2"), ValueSpec.of("test"))
        }
    }

    describe("VariableSubtractionAssignmentSpec") {
        it("create") {
            val spec = VariableSubtractionAssignmentSpec(NamespaceSpec("test"), ValueSpec.of("test"))
            spec.name shouldBe NamespaceSpec("test")
            spec.value shouldBe ValueSpec.of("test")
        }

        it("generate") {
            val spec = VariableSubtractionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec.generate(GenerationContext()) shouldBe "variable -= test"
        }

        it("equals") {
            val spec = VariableSubtractionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec shouldBe spec
            spec shouldBe VariableSubtractionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec shouldNotBe VariableSubtractionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test2"))
            spec shouldNotBe VariableSubtractionAssignmentSpec(NamespaceSpec("variable2"), ValueSpec.of("test"))
        }
    }

    describe("VariableMultiplicationAssignmentSpec") {
        it("create") {
            val spec = VariableMultiplicationAssignmentSpec(NamespaceSpec("test"), ValueSpec.of("test"))
            spec.name shouldBe NamespaceSpec("test")
            spec.value shouldBe ValueSpec.of("test")
        }

        it("generate") {
            val spec = VariableMultiplicationAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec.generate(GenerationContext()) shouldBe "variable *= test"
        }

        it("equals") {
            val spec = VariableMultiplicationAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec shouldBe spec
            spec shouldBe VariableMultiplicationAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec shouldNotBe VariableMultiplicationAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test2"))
            spec shouldNotBe VariableMultiplicationAssignmentSpec(NamespaceSpec("variable2"), ValueSpec.of("test"))
        }
    }

    describe("VariableDivisionAssignmentSpec") {
        it("create") {
            val spec = VariableDivisionAssignmentSpec(NamespaceSpec("test"), ValueSpec.of("test"))
            spec.name shouldBe NamespaceSpec("test")
            spec.value shouldBe ValueSpec.of("test")
        }

        it("generate") {
            val spec = VariableDivisionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec.generate(GenerationContext()) shouldBe "variable /= test"
        }

        it("equals") {
            val spec = VariableDivisionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec shouldBe spec
            spec shouldBe VariableDivisionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec shouldNotBe VariableDivisionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test2"))
            spec shouldNotBe VariableDivisionAssignmentSpec(NamespaceSpec("variable2"), ValueSpec.of("test"))
        }
    }

    describe("VariableModuloAssignmentSpec") {
        it("create") {
            val spec = VariableModuloAssignmentSpec(NamespaceSpec("test"), ValueSpec.of("test"))
            spec.name shouldBe NamespaceSpec("test")
            spec.value shouldBe ValueSpec.of("test")
        }

        it("generate") {
            val spec = VariableModuloAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec.generate(GenerationContext()) shouldBe "variable %= test"
        }

        it("equals") {
            val spec = VariableModuloAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec shouldBe spec
            spec shouldBe VariableModuloAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec shouldNotBe VariableModuloAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test2"))
            spec shouldNotBe VariableModuloAssignmentSpec(NamespaceSpec("variable2"), ValueSpec.of("test"))
        }
    }

    describe("VariablePowerAssignmentSpec") {
        it("create") {
            val spec = VariablePowerAssignmentSpec(NamespaceSpec("test"), ValueSpec.of("test"))
            spec.name shouldBe NamespaceSpec("test")
            spec.value shouldBe ValueSpec.of("test")
        }

        it("generate") {
            val spec = VariablePowerAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec.generate(GenerationContext()) shouldBe "variable **= test"
        }

        it("equals") {
            val spec = VariablePowerAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec shouldBe spec
            spec shouldBe VariablePowerAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec shouldNotBe VariablePowerAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test2"))
            spec shouldNotBe VariablePowerAssignmentSpec(NamespaceSpec("variable2"), ValueSpec.of("test"))
        }
    }

    describe("VariableBitwiseAndAssignmentSpec") {
        it("create") {
            val spec = VariableBitwiseAndAssignmentSpec(NamespaceSpec("test"), ValueSpec.of("test"))
            spec.name shouldBe NamespaceSpec("test")
            spec.value shouldBe ValueSpec.of("test")
        }

        it("generate") {
            val spec = VariableBitwiseAndAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec.generate(GenerationContext()) shouldBe "variable &= test"
        }

        it("equals") {
            val spec = VariableBitwiseAndAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec shouldBe spec
            spec shouldBe VariableBitwiseAndAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec shouldNotBe VariableBitwiseAndAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test2"))
            spec shouldNotBe VariableBitwiseAndAssignmentSpec(NamespaceSpec("variable2"), ValueSpec.of("test"))
        }
    }

    describe("VariableBitwiseOrAssignmentSpec") {
        it("create") {
            val spec = VariableBitwiseOrAssignmentSpec(NamespaceSpec("test"), ValueSpec.of("test"))
            spec.name shouldBe NamespaceSpec("test")
            spec.value shouldBe ValueSpec.of("test")
        }

        it("generate") {
            val spec = VariableBitwiseOrAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec.generate(GenerationContext()) shouldBe "variable |= test"
        }

        it("equals") {
            val spec = VariableBitwiseOrAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec shouldBe spec
            spec shouldBe VariableBitwiseOrAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec shouldNotBe VariableBitwiseOrAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test2"))
            spec shouldNotBe VariableBitwiseOrAssignmentSpec(NamespaceSpec("variable2"), ValueSpec.of("test"))
        }
    }

    describe("VariableBitwiseXorAssignmentSpec") {
        it("create") {
            val spec = VariableBitwiseXorAssignmentSpec(NamespaceSpec("test"), ValueSpec.of("test"))
            spec.name shouldBe NamespaceSpec("test")
            spec.value shouldBe ValueSpec.of("test")
        }

        it("generate") {
            val spec = VariableBitwiseXorAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec.generate(GenerationContext()) shouldBe "variable ^= test"
        }

        it("equals") {
            val spec = VariableBitwiseXorAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec shouldBe spec
            spec shouldBe VariableBitwiseXorAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
            spec shouldNotBe VariableBitwiseXorAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test2"))
            spec shouldNotBe VariableBitwiseXorAssignmentSpec(NamespaceSpec("variable2"), ValueSpec.of("test"))
        }
    }

    describe("VariableIncrementBeforeSpec") {
        it("create") {
            val spec = VariableIncrementBeforeSpec(NamespaceSpec("test"))
            spec.name shouldBe NamespaceSpec("test")
        }

        it("generate") {
            val spec = VariableIncrementBeforeSpec(NamespaceSpec("variable"))
            spec.generate(GenerationContext()) shouldBe "++variable"
        }

        it("equals") {
            val spec = VariableIncrementBeforeSpec(NamespaceSpec("variable"))
            spec shouldBe spec
            spec shouldBe VariableIncrementBeforeSpec(NamespaceSpec("variable"))
            spec shouldNotBe VariableIncrementBeforeSpec(NamespaceSpec("variable2"))
        }
    }

    describe("VariableIncrementAfterSpec") {
        it("create") {
            val spec = VariableIncrementAfterSpec(NamespaceSpec("test"))
            spec.name shouldBe NamespaceSpec("test")
        }

        it("generate") {
            val spec = VariableIncrementAfterSpec(NamespaceSpec("variable"))
            spec.generate(GenerationContext()) shouldBe "variable++"
        }

        it("equals") {
            val spec = VariableIncrementAfterSpec(NamespaceSpec("variable"))
            spec shouldBe spec
            spec shouldBe VariableIncrementAfterSpec(NamespaceSpec("variable"))
            spec shouldNotBe VariableIncrementAfterSpec(NamespaceSpec("variable2"))
        }
    }

    describe("VariableDecrementBeforeSpec") {
        it("create") {
            val spec = VariableDecrementBeforeSpec(NamespaceSpec("test"))
            spec.name shouldBe NamespaceSpec("test")
        }

        it("generate") {
            val spec = VariableDecrementBeforeSpec(NamespaceSpec("variable"))
            spec.generate(GenerationContext()) shouldBe "--variable"
        }

        it("equals") {
            val spec = VariableDecrementBeforeSpec(NamespaceSpec("variable"))
            spec shouldBe spec
            spec shouldBe VariableDecrementBeforeSpec(NamespaceSpec("variable"))
            spec shouldNotBe VariableDecrementBeforeSpec(NamespaceSpec("variable2"))
        }
    }

    describe("VariableDecrementAfterSpec") {
        it("create") {
            val spec = VariableDecrementAfterSpec(NamespaceSpec("test"))
            spec.name shouldBe NamespaceSpec("test")
        }

        it("generate") {
            val spec = VariableDecrementAfterSpec(NamespaceSpec("variable"))
            spec.generate(GenerationContext()) shouldBe "variable--"
        }

        it("equals") {
            val spec = VariableDecrementAfterSpec(NamespaceSpec("variable"))
            spec shouldBe spec
            spec shouldBe VariableDecrementAfterSpec(NamespaceSpec("variable"))
            spec shouldNotBe VariableDecrementAfterSpec(NamespaceSpec("variable2"))
        }
    }

    describe("FunctionCallSpec") {
        it("create") {
            val spec = FunctionCallSpec(NamespaceSpec("test"), listOf())
            spec.name shouldBe NamespaceSpec("test")
        }

        it("generate") {
            val spec = FunctionCallSpec(NamespaceSpec("function"), listOf())
            spec.generate(GenerationContext()) shouldBe "function()"
        }

        it("equals") {
            val spec = FunctionCallSpec(NamespaceSpec("function"), listOf())
            spec shouldBe spec
            spec shouldBe FunctionCallSpec(NamespaceSpec("function"), listOf())
            spec shouldNotBe FunctionCallSpec(NamespaceSpec("function2"), listOf())
        }
    }
}, flatten = true)
