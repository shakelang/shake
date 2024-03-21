package com.shakelang.shake.shakespeare.spec.code

import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.util.testlib.FlatTestSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ValueTests : FlatTestSpec({

    describe("StringLiteralSpec") {
        it("create from string") {
            val value = "Hello, World!"
            val node = StringLiteralSpec(value)
            node.value shouldBe value
        }

        it("generate") {
            val value = "Hello, World!"
            val node = StringLiteralSpec(value)
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe "\"$value\""
        }

        it("equals") {
            val value = "Hello, World!"
            StringLiteralSpec(value) shouldBe StringLiteralSpec(value)
            StringLiteralSpec("Hello, World!") shouldBe StringLiteralSpec("Hello, World!")
            StringLiteralSpec("Hello, World!") shouldNotBe StringLiteralSpec("Hello, World")
        }

        it("build from string") {
            val value = "Hello, World!"
            val node = StringLiteralSpec.builder()
                .value(value)
                .build()
            node.value shouldBe value
        }
    }

    describe("IntLiteralSpec") {

        it("create from int") {
            val value = 42
            val node = IntLiteralSpec(value)
            node.value shouldBe value
        }

        it("generate") {
            val value = 42
            val node = IntLiteralSpec(value)
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe value.toString()
        }

        it("equals") {
            IntLiteralSpec(42) shouldBe IntLiteralSpec(42)
            IntLiteralSpec(42) shouldNotBe IntLiteralSpec(43)
        }

        it("build from int") {
            val value = 42
            val node = IntLiteralSpec.builder()
                .value(value)
                .build()
            node.value shouldBe value
        }
    }

    describe("FloatLiteralSpec") {
        it("create from float") {
            val value = 42.0
            val node = FloatLiteralSpec(value)
            node.value shouldBe value
        }

        it("generate") {
            val value = 42.0
            val node = FloatLiteralSpec(value)
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe value.toString()
        }

        it("equals") {
            FloatLiteralSpec(42.0) shouldBe FloatLiteralSpec(42.0)
            FloatLiteralSpec(42.0) shouldNotBe FloatLiteralSpec(43.0)
        }

        it("build from float") {
            val value = 42.0
            val node = FloatLiteralSpec.builder()
                .value(value)
                .build()
            node.value shouldBe value
        }
    }

    describe("BooleanLiteralSpec") {
        it("create from boolean (true)") {
            val value = true
            val node = BooleanLiteralSpec(value)
            node.value shouldBe value
        }

        it("generate (true)") {
            val value = true
            val node = BooleanLiteralSpec(value)
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe "true"
        }

        it("create from boolean (false)") {
            val value = false
            val node = BooleanLiteralSpec(value)
            node.value shouldBe value
        }

        it("generate (false)") {
            val value = false
            val node = BooleanLiteralSpec(value)
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe "false"
        }

        it("equals") {
            BooleanLiteralSpec(true) shouldBe BooleanLiteralSpec(true)
            BooleanLiteralSpec(true) shouldNotBe BooleanLiteralSpec(false)
        }

        it("build from boolean (true)") {
            val value = true
            val node = BooleanLiteralSpec.builder()
                .value(value)
                .build()
            node.value shouldBe value
        }

        it("build from boolean (false)") {
            val value = false
            val node = BooleanLiteralSpec.builder()
                .value(value)
                .build()
            node.value shouldBe value
        }
    }
    describe("NullLiteralSpec") {
        it("create") {
            val node = NullLiteralSpec.INSTANCE
            node shouldBe NullLiteralSpec.INSTANCE
        }

        it("generate") {
            val node = NullLiteralSpec.INSTANCE
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe "null"
        }

        it("equals") {
            NullLiteralSpec.INSTANCE shouldBe NullLiteralSpec.INSTANCE
            NullLiteralSpec.INSTANCE shouldNotBe StringLiteralSpec("null")
        }
    }

    describe("VariableReferenceSpec") {
        it("create") {
            val name = "variable"
            val node = VariableReferenceSpec(name)
            node.name.name shouldBe arrayOf(name)
        }

        it("generate") {
            val name = "variable"
            val node = VariableReferenceSpec(name)
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe name
        }

        it("equals") {
            VariableReferenceSpec("variable") shouldBe VariableReferenceSpec("variable")
            VariableReferenceSpec("variable") shouldNotBe VariableReferenceSpec("variable2")
        }

        it("create with multiple names") {
            val name = "variable"
            val node = VariableReferenceSpec(name, "name", "name2")
            node.name.name shouldBe arrayOf(name, "name", "name2")
        }

        it("generate with multiple names") {
            val node = VariableReferenceSpec("name", "name2")
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe "name.name2"
        }

        it("build") {
            val name = "variable"
            val node = VariableReferenceSpec.builder()
                .name(name)
                .build()
            node.name.name shouldBe arrayOf(name)
        }

        it("build with multiple names") {
            val name = "variable"
            val node = VariableReferenceSpec.builder()
                .name(name)
                .name("name")
                .name("name2")
                .build()
            node.name.name shouldBe arrayOf(name, "name", "name2")
        }
    }

    describe("AdditionSpec") {
        it("create") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = AdditionSpec(left, right)
            node.left shouldBe left
            node.right shouldBe right
        }

        it("generate") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = AdditionSpec(left, right)
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe "42 + 43"
        }

        it("equals") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            AdditionSpec(left, right) shouldBe AdditionSpec(left, right)
            AdditionSpec(left, right) shouldNotBe AdditionSpec(right, left)
        }

        it("build") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = AdditionSpec.builder()
                .left(left)
                .right(right)
                .build()
            node.left shouldBe left
            node.right shouldBe right
        }
    }

    describe("SubtractionSpec") {
        it("create") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = SubtractionSpec(left, right)
            node.left shouldBe left
            node.right shouldBe right
        }

        it("generate") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = SubtractionSpec(left, right)
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe "42 - 43"
        }

        it("equals") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            SubtractionSpec(left, right) shouldBe SubtractionSpec(left, right)
            SubtractionSpec(left, right) shouldNotBe SubtractionSpec(right, left)
        }

        it("build") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = SubtractionSpec.builder()
                .left(left)
                .right(right)
                .build()
            node.left shouldBe left
            node.right shouldBe right
        }
    }

    describe("MultiplicationSpec") {
        it("create") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = MultiplicationSpec(left, right)
            node.left shouldBe left
            node.right shouldBe right
        }

        it("generate") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = MultiplicationSpec(left, right)
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe "42 * 43"
        }

        it("equals") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            MultiplicationSpec(left, right) shouldBe MultiplicationSpec(left, right)
            MultiplicationSpec(left, right) shouldNotBe MultiplicationSpec(right, left)
        }

        it("build") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = MultiplicationSpec.builder()
                .left(left)
                .right(right)
                .build()
            node.left shouldBe left
            node.right shouldBe right
        }
    }

    describe("DivisionSpec") {
        it("create") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = DivisionSpec(left, right)
            node.left shouldBe left
            node.right shouldBe right
        }

        it("generate") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = DivisionSpec(left, right)
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe "42 / 43"
        }

        it("equals") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            DivisionSpec(left, right) shouldBe DivisionSpec(left, right)
            DivisionSpec(left, right) shouldNotBe DivisionSpec(right, left)
        }

        it("build") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = DivisionSpec.builder()
                .left(left)
                .right(right)
                .build()
            node.left shouldBe left
            node.right shouldBe right
        }
    }

    describe("ModuloSpec") {
        it("create") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = ModuloSpec(left, right)
            node.left shouldBe left
            node.right shouldBe right
        }

        it("ModuloSpec") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = ModuloSpec(left, right)
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe "42 % 43"
        }

        it("ModuloSpec") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            ModuloSpec(left, right) shouldBe ModuloSpec(left, right)
            ModuloSpec(left, right) shouldNotBe ModuloSpec(right, left)
        }

        it("ModuloSpec") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = ModuloSpec.builder()
                .left(left)
                .right(right)
                .build()
            node.left shouldBe left
            node.right shouldBe right
        }
    }

    describe("PowerSpec") {
        it("create") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = PowerSpec(left, right)
            node.left shouldBe left
            node.right shouldBe right
        }

        it("generate") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = PowerSpec(left, right)
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe "42 ** 43"
        }

        it("equals") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            PowerSpec(left, right) shouldBe PowerSpec(left, right)
            PowerSpec(left, right) shouldNotBe PowerSpec(right, left)
        }

        it("build") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = PowerSpec.builder()
                .left(left)
                .right(right)
                .build()
            node.left shouldBe left
            node.right shouldBe right
        }
    }

    describe("UnaryMinusSpec") {
        it("create") {
            val value = IntLiteralSpec(42)
            val node = UnaryMinusSpec(value)
            node.value shouldBe value
        }

        it("generate") {
            val value = IntLiteralSpec(42)
            val node = UnaryMinusSpec(value)
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe "-42"
        }

        it("equals") {
            val value = IntLiteralSpec(42)
            UnaryMinusSpec(value) shouldBe UnaryMinusSpec(value)
            UnaryMinusSpec(value) shouldNotBe UnaryMinusSpec(IntLiteralSpec(43))
        }

        it("build") {
            val value = IntLiteralSpec(42)
            val node = UnaryMinusSpec.builder()
                .value(value)
                .build()
            node.value shouldBe value
        }
    }

    describe("UnaryPlusSpec") {
        it("create") {
            val value = IntLiteralSpec(42)
            val node = UnaryPlusSpec(value)
            node.value shouldBe value
        }

        it("generate") {
            val value = IntLiteralSpec(42)
            val node = UnaryPlusSpec(value)
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe "+42"
        }

        it("equals") {
            val value = IntLiteralSpec(42)
            UnaryPlusSpec(value) shouldBe UnaryPlusSpec(value)
            UnaryPlusSpec(value) shouldNotBe UnaryPlusSpec(IntLiteralSpec(43))
        }

        it("build") {
            val value = IntLiteralSpec(42)
            val node = UnaryPlusSpec.builder()
                .value(value)
                .build()
            node.value shouldBe value
        }
    }

    describe("LogicalAndSpec") {
        it("create") {
            val left = BooleanLiteralSpec(true)
            val right = BooleanLiteralSpec(false)
            val node = LogicalAndSpec(left, right)
            node.left shouldBe left
            node.right shouldBe right
        }

        it("generate") {
            val left = BooleanLiteralSpec(true)
            val right = BooleanLiteralSpec(false)
            val node = LogicalAndSpec(left, right)
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe "true && false"
        }

        it("equals") {
            val left = BooleanLiteralSpec(true)
            val right = BooleanLiteralSpec(false)
            LogicalAndSpec(left, right) shouldBe LogicalAndSpec(left, right)
            LogicalAndSpec(left, right) shouldNotBe LogicalAndSpec(right, left)
        }

        it("build") {
            val left = BooleanLiteralSpec(true)
            val right = BooleanLiteralSpec(false)
            val node = LogicalAndSpec.builder()
                .left(left)
                .right(right)
                .build()
            node.left shouldBe left
            node.right shouldBe right
        }
    }

    describe("LogicalOrSpec") {
        it("create") {
            val left = BooleanLiteralSpec(true)
            val right = BooleanLiteralSpec(false)
            val node = LogicalOrSpec(left, right)
            node.left shouldBe left
            node.right shouldBe right
        }

        it("generate") {
            val left = BooleanLiteralSpec(true)
            val right = BooleanLiteralSpec(false)
            val node = LogicalOrSpec(left, right)
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe "true || false"
        }

        it("equals") {
            val left = BooleanLiteralSpec(true)
            val right = BooleanLiteralSpec(false)
            LogicalOrSpec(left, right) shouldBe LogicalOrSpec(left, right)
            LogicalOrSpec(left, right) shouldNotBe LogicalOrSpec(right, left)
        }

        it("build") {
            val left = BooleanLiteralSpec(true)
            val right = BooleanLiteralSpec(false)
            val node = LogicalOrSpec.builder()
                .left(left)
                .right(right)
                .build()
            node.left shouldBe left
            node.right shouldBe right
        }
    }

    describe("LogicalNotSpec") {
        it("create") {
            val value = BooleanLiteralSpec(true)
            val node = LogicalNotSpec(value)
            node.value shouldBe value
        }

        it("generate") {
            val value = BooleanLiteralSpec(true)
            val node = LogicalNotSpec(value)
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe "!true"
        }

        it("equals") {
            val value = BooleanLiteralSpec(true)
            LogicalNotSpec(value) shouldBe LogicalNotSpec(value)
            LogicalNotSpec(value) shouldNotBe LogicalNotSpec(BooleanLiteralSpec(false))
        }

        it("build") {
            val value = BooleanLiteralSpec(true)
            val node = LogicalNotSpec.builder()
                .value(value)
                .build()
            node.value shouldBe value
        }
    }

    describe("EqualitySpec") {
        it("create") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = EqualitySpec(left, right)
            node.left shouldBe left
            node.right shouldBe right
        }

        it("generate") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = EqualitySpec(left, right)
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe "42 == 43"
        }

        it("equals") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            EqualitySpec(left, right) shouldBe EqualitySpec(left, right)
            EqualitySpec(left, right) shouldNotBe EqualitySpec(right, left)
        }

        it("build") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = EqualitySpec.builder()
                .left(left)
                .right(right)
                .build()
            node.left shouldBe left
            node.right shouldBe right
        }
    }

    describe("InequalitySpec") {
        it("create") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = InequalitySpec(left, right)
            node.left shouldBe left
            node.right shouldBe right
        }

        it("generate") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = InequalitySpec(left, right)
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe "42 != 43"
        }

        it("equals") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            InequalitySpec(left, right) shouldBe InequalitySpec(left, right)
            InequalitySpec(left, right) shouldNotBe InequalitySpec(right, left)
        }

        it("build") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = InequalitySpec.builder()
                .left(left)
                .right(right)
                .build()
            node.left shouldBe left
            node.right shouldBe right
        }
    }

    describe("LessThanSpec") {
        it("create") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = LessThanSpec(left, right)
            node.left shouldBe left
            node.right shouldBe right
        }

        it("generate") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = LessThanSpec(left, right)
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe "42 < 43"
        }

        it("equals") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            LessThanSpec(left, right) shouldBe LessThanSpec(left, right)
            LessThanSpec(left, right) shouldNotBe LessThanSpec(right, left)
        }

        it("build") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = LessThanSpec.builder()
                .left(left)
                .right(right)
                .build()
            node.left shouldBe left
            node.right shouldBe right
        }
    }

    describe("LessThanOrEqualSpec") {
        it("create") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = LessThanOrEqualSpec(left, right)
            node.left shouldBe left
            node.right shouldBe right
        }

        it("generate") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = LessThanOrEqualSpec(left, right)
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe "42 <= 43"
        }

        it("equals") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            LessThanOrEqualSpec(left, right) shouldBe LessThanOrEqualSpec(left, right)
            LessThanOrEqualSpec(left, right) shouldNotBe LessThanOrEqualSpec(right, left)
        }

        it("build") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = LessThanOrEqualSpec.builder()
                .left(left)
                .right(right)
                .build()
            node.left shouldBe left
            node.right shouldBe right
        }
    }

    describe("GreaterThanSpec") {
        it("create") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = GreaterThanSpec(left, right)
            node.left shouldBe left
            node.right shouldBe right
        }

        it("generate") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = GreaterThanSpec(left, right)
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe "42 > 43"
        }

        it("equals") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            GreaterThanSpec(left, right) shouldBe GreaterThanSpec(left, right)
            GreaterThanSpec(left, right) shouldNotBe GreaterThanSpec(right, left)
        }

        it("build") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = GreaterThanSpec.builder()
                .left(left)
                .right(right)
                .build()
            node.left shouldBe left
            node.right shouldBe right
        }
    }

    describe("GreaterThanOrEqualSpec") {
        it("create") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = GreaterThanOrEqualSpec(left, right)
            node.left shouldBe left
            node.right shouldBe right
        }

        it("generate") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = GreaterThanOrEqualSpec(left, right)
            val ctx = GenerationContext()
            node.generate(ctx) shouldBe "42 >= 43"
        }

        it("equals") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            GreaterThanOrEqualSpec(left, right) shouldBe GreaterThanOrEqualSpec(left, right)
            GreaterThanOrEqualSpec(left, right) shouldNotBe GreaterThanOrEqualSpec(right, left)
        }

        it("build") {
            val left = IntLiteralSpec(42)
            val right = IntLiteralSpec(43)
            val node = GreaterThanOrEqualSpec.builder()
                .left(left)
                .right(right)
                .build()
            node.left shouldBe left
            node.right shouldBe right
        }
    }
})
