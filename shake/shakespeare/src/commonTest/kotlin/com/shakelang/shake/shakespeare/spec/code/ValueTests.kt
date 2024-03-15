package com.shakelang.shake.shakespeare.spec.code

import com.shakelang.shake.shakespeare.spec.GenerationContext
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ValueTests : FreeSpec({

    "create StringLiteralNodeSpec from string" {
        val value = "Hello, World!"
        val node = StringLiteralSpec(value)
        node.value shouldBe value
    }

    "generate StringLiteralNodeSpec" {
        val value = "Hello, World!"
        val node = StringLiteralSpec(value)
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe "\"$value\""
    }

    "equals StringLiteralNodeSpec" {
        val value = "Hello, World!"
        StringLiteralSpec(value) shouldBe StringLiteralSpec(value)
        StringLiteralSpec("Hello, World!") shouldBe StringLiteralSpec("Hello, World!")
        StringLiteralSpec("Hello, World!") shouldNotBe StringLiteralSpec("Hello, World")
    }

    "build StringLiteralNodeSpec from string" {
        val value = "Hello, World!"
        val node = StringLiteralSpec.builder()
            .value(value)
            .build()
        node.value shouldBe value
    }

    "create IntLiteralNodeSpec from int" {
        val value = 42
        val node = IntLiteralSpec(value)
        node.value shouldBe value
    }

    "generate IntLiteralNodeSpec" {
        val value = 42
        val node = IntLiteralSpec(value)
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe value.toString()
    }

    "equals IntLiteralNodeSpec" {
        IntLiteralSpec(42) shouldBe IntLiteralSpec(42)
        IntLiteralSpec(42) shouldNotBe IntLiteralSpec(43)
    }

    "build IntLiteralNodeSpec from int" {
        val value = 42
        val node = IntLiteralSpec.builder()
            .value(value)
            .build()
        node.value shouldBe value
    }

    "create FloatLiteralNodeSpec from float" {
        val value = 42.0
        val node = FloatLiteralSpec(value)
        node.value shouldBe value
    }

    "generate FloatLiteralNodeSpec" {
        val value = 42.0
        val node = FloatLiteralSpec(value)
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe value.toString()
    }

    "equals FloatLiteralNodeSpec" {
        FloatLiteralSpec(42.0) shouldBe FloatLiteralSpec(42.0)
        FloatLiteralSpec(42.0) shouldNotBe FloatLiteralSpec(43.0)
    }

    "build FloatLiteralNodeSpec from float" {
        val value = 42.0
        val node = FloatLiteralSpec.builder()
            .value(value)
            .build()
        node.value shouldBe value
    }

    "create BooleanLiteralNodeSpec from boolean (true)" {
        val value = true
        val node = BooleanLiteralSpec(value)
        node.value shouldBe value
    }

    "generate BooleanLiteralNodeSpec (true)" {
        val value = true
        val node = BooleanLiteralSpec(value)
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe "true"
    }

    "create BooleanLiteralNodeSpec from boolean (false)" {
        val value = false
        val node = BooleanLiteralSpec(value)
        node.value shouldBe value
    }

    "generate BooleanLiteralNodeSpec (false)" {
        val value = false
        val node = BooleanLiteralSpec(value)
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe "false"
    }

    "equals BooleanLiteralNodeSpec" {
        BooleanLiteralSpec(true) shouldBe BooleanLiteralSpec(true)
        BooleanLiteralSpec(true) shouldNotBe BooleanLiteralSpec(false)
    }

    "build BooleanLiteralNodeSpec from boolean (true)" {
        val value = true
        val node = BooleanLiteralSpec.builder()
            .value(value)
            .build()
        node.value shouldBe value
    }

    "build BooleanLiteralNodeSpec from boolean (false)" {
        val value = false
        val node = BooleanLiteralSpec.builder()
            .value(value)
            .build()
        node.value shouldBe value
    }

    "create NullLiteralNodeSpec" {
        val node = NullLiteralSpec.INSTANCE
        node shouldBe NullLiteralSpec.INSTANCE
    }

    "generate NullLiteralNodeSpec" {
        val node = NullLiteralSpec.INSTANCE
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe "null"
    }

    "equals NullLiteralNodeSpec" {
        NullLiteralSpec.INSTANCE shouldBe NullLiteralSpec.INSTANCE
        NullLiteralSpec.INSTANCE shouldNotBe StringLiteralSpec("null")
    }

    "create VariableNodeSpec" {
        val name = "variable"
        val node = VariableReferenceSpec(name)
        node.name.name shouldBe arrayOf(name)
    }

    "generate VariableNodeSpec" {
        val name = "variable"
        val node = VariableReferenceSpec(name)
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe name
    }

    "equals VariableNodeSpec" {
        VariableReferenceSpec("variable") shouldBe VariableReferenceSpec("variable")
        VariableReferenceSpec("variable") shouldNotBe VariableReferenceSpec("variable2")
    }

    "create VariableNodeSpec with multiple names" {
        val name = "variable"
        val node = VariableReferenceSpec(name, "name", "name2")
        node.name.name shouldBe arrayOf(name, "name", "name2")
    }

    "generate VariableNodeSpec with multiple names" {
        val node = VariableReferenceSpec("name", "name2")
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe "name.name2"
    }

    "build VariableNodeSpec" {
        val name = "variable"
        val node = VariableReferenceSpec.builder()
            .name(name)
            .build()
        node.name.name shouldBe arrayOf(name)
    }

    "build VariableNodeSpec with multiple names" {
        val name = "variable"
        val node = VariableReferenceSpec.builder()
            .name(name)
            .name("name")
            .name("name2")
            .build()
        node.name.name shouldBe arrayOf(name, "name", "name2")
    }

    "create AdditionSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = AdditionSpec(left, right)
        node.left shouldBe left
        node.right shouldBe right
    }

    "generate AdditionSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = AdditionSpec(left, right)
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe "42 + 43"
    }

    "equals AdditionSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        AdditionSpec(left, right) shouldBe AdditionSpec(left, right)
        AdditionSpec(left, right) shouldNotBe AdditionSpec(right, left)
    }

    "build AdditionSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = AdditionSpec.builder()
            .left(left)
            .right(right)
            .build()
        node.left shouldBe left
        node.right shouldBe right
    }

    "create SubtractionSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = SubtractionSpec(left, right)
        node.left shouldBe left
        node.right shouldBe right
    }

    "generate SubtractionSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = SubtractionSpec(left, right)
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe "42 - 43"
    }

    "equals SubtractionSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        SubtractionSpec(left, right) shouldBe SubtractionSpec(left, right)
        SubtractionSpec(left, right) shouldNotBe SubtractionSpec(right, left)
    }

    "build SubtractionSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = SubtractionSpec.builder()
            .left(left)
            .right(right)
            .build()
        node.left shouldBe left
        node.right shouldBe right
    }

    "create MultiplicationSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = MultiplicationSpec(left, right)
        node.left shouldBe left
        node.right shouldBe right
    }

    "generate MultiplicationSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = MultiplicationSpec(left, right)
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe "42 * 43"
    }

    "equals MultiplicationSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        MultiplicationSpec(left, right) shouldBe MultiplicationSpec(left, right)
        MultiplicationSpec(left, right) shouldNotBe MultiplicationSpec(right, left)
    }

    "build MultiplicationSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = MultiplicationSpec.builder()
            .left(left)
            .right(right)
            .build()
        node.left shouldBe left
        node.right shouldBe right
    }

    "create DivisionSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = DivisionSpec(left, right)
        node.left shouldBe left
        node.right shouldBe right
    }

    "generate DivisionSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = DivisionSpec(left, right)
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe "42 / 43"
    }

    "equals DivisionSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        DivisionSpec(left, right) shouldBe DivisionSpec(left, right)
        DivisionSpec(left, right) shouldNotBe DivisionSpec(right, left)
    }

    "build DivisionSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = DivisionSpec.builder()
            .left(left)
            .right(right)
            .build()
        node.left shouldBe left
        node.right shouldBe right
    }

    "create ModuloSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = ModuloSpec(left, right)
        node.left shouldBe left
        node.right shouldBe right
    }

    "generate ModuloSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = ModuloSpec(left, right)
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe "42 % 43"
    }

    "equals ModuloSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        ModuloSpec(left, right) shouldBe ModuloSpec(left, right)
        ModuloSpec(left, right) shouldNotBe ModuloSpec(right, left)
    }

    "build ModuloSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = ModuloSpec.builder()
            .left(left)
            .right(right)
            .build()
        node.left shouldBe left
        node.right shouldBe right
    }

    "create PowerSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = PowerSpec(left, right)
        node.left shouldBe left
        node.right shouldBe right
    }

    "generate PowerSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = PowerSpec(left, right)
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe "42 ** 43"
    }

    "equals PowerSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        PowerSpec(left, right) shouldBe PowerSpec(left, right)
        PowerSpec(left, right) shouldNotBe PowerSpec(right, left)
    }

    "build PowerSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = PowerSpec.builder()
            .left(left)
            .right(right)
            .build()
        node.left shouldBe left
        node.right shouldBe right
    }

    "create UnaryMinusSpec" {
        val value = IntLiteralSpec(42)
        val node = UnaryMinusSpec(value)
        node.value shouldBe value
    }

    "generate UnaryMinusSpec" {
        val value = IntLiteralSpec(42)
        val node = UnaryMinusSpec(value)
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe "-42"
    }

    "equals UnaryMinusSpec" {
        val value = IntLiteralSpec(42)
        UnaryMinusSpec(value) shouldBe UnaryMinusSpec(value)
        UnaryMinusSpec(value) shouldNotBe UnaryMinusSpec(IntLiteralSpec(43))
    }

    "build UnaryMinusSpec" {
        val value = IntLiteralSpec(42)
        val node = UnaryMinusSpec.builder()
            .value(value)
            .build()
        node.value shouldBe value
    }

    "create UnaryPlusSpec" {
        val value = IntLiteralSpec(42)
        val node = UnaryPlusSpec(value)
        node.value shouldBe value
    }

    "generate UnaryPlusSpec" {
        val value = IntLiteralSpec(42)
        val node = UnaryPlusSpec(value)
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe "+42"
    }

    "equals UnaryPlusSpec" {
        val value = IntLiteralSpec(42)
        UnaryPlusSpec(value) shouldBe UnaryPlusSpec(value)
        UnaryPlusSpec(value) shouldNotBe UnaryPlusSpec(IntLiteralSpec(43))
    }

    "build UnaryPlusSpec" {
        val value = IntLiteralSpec(42)
        val node = UnaryPlusSpec.builder()
            .value(value)
            .build()
        node.value shouldBe value
    }

    "create LogicalAndSpec" {
        val left = BooleanLiteralSpec(true)
        val right = BooleanLiteralSpec(false)
        val node = LogicalAndSpec(left, right)
        node.left shouldBe left
        node.right shouldBe right
    }

    "generate LogicalAndSpec" {
        val left = BooleanLiteralSpec(true)
        val right = BooleanLiteralSpec(false)
        val node = LogicalAndSpec(left, right)
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe "true && false"
    }

    "equals LogicalAndSpec" {
        val left = BooleanLiteralSpec(true)
        val right = BooleanLiteralSpec(false)
        LogicalAndSpec(left, right) shouldBe LogicalAndSpec(left, right)
        LogicalAndSpec(left, right) shouldNotBe LogicalAndSpec(right, left)
    }

    "build LogicalAndSpec" {
        val left = BooleanLiteralSpec(true)
        val right = BooleanLiteralSpec(false)
        val node = LogicalAndSpec.builder()
            .left(left)
            .right(right)
            .build()
        node.left shouldBe left
        node.right shouldBe right
    }

    "create LogicalOrSpec" {
        val left = BooleanLiteralSpec(true)
        val right = BooleanLiteralSpec(false)
        val node = LogicalOrSpec(left, right)
        node.left shouldBe left
        node.right shouldBe right
    }

    "generate LogicalOrSpec" {
        val left = BooleanLiteralSpec(true)
        val right = BooleanLiteralSpec(false)
        val node = LogicalOrSpec(left, right)
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe "true || false"
    }

    "equals LogicalOrSpec" {
        val left = BooleanLiteralSpec(true)
        val right = BooleanLiteralSpec(false)
        LogicalOrSpec(left, right) shouldBe LogicalOrSpec(left, right)
        LogicalOrSpec(left, right) shouldNotBe LogicalOrSpec(right, left)
    }

    "build LogicalOrSpec" {
        val left = BooleanLiteralSpec(true)
        val right = BooleanLiteralSpec(false)
        val node = LogicalOrSpec.builder()
            .left(left)
            .right(right)
            .build()
        node.left shouldBe left
        node.right shouldBe right
    }

    "create LogicalNotSpec" {
        val value = BooleanLiteralSpec(true)
        val node = LogicalNotSpec(value)
        node.value shouldBe value
    }

    "generate LogicalNotSpec" {
        val value = BooleanLiteralSpec(true)
        val node = LogicalNotSpec(value)
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe "!true"
    }

    "equals LogicalNotSpec" {
        val value = BooleanLiteralSpec(true)
        LogicalNotSpec(value) shouldBe LogicalNotSpec(value)
        LogicalNotSpec(value) shouldNotBe LogicalNotSpec(BooleanLiteralSpec(false))
    }

    "build LogicalNotSpec" {
        val value = BooleanLiteralSpec(true)
        val node = LogicalNotSpec.builder()
            .value(value)
            .build()
        node.value shouldBe value
    }

    "create EqualitySpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = EqualitySpec(left, right)
        node.left shouldBe left
        node.right shouldBe right
    }

    "generate EqualitySpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = EqualitySpec(left, right)
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe "42 == 43"
    }

    "equals EqualitySpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        EqualitySpec(left, right) shouldBe EqualitySpec(left, right)
        EqualitySpec(left, right) shouldNotBe EqualitySpec(right, left)
    }

    "build EqualitySpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = EqualitySpec.builder()
            .left(left)
            .right(right)
            .build()
        node.left shouldBe left
        node.right shouldBe right
    }

    "create InequalitySpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = InequalitySpec(left, right)
        node.left shouldBe left
        node.right shouldBe right
    }

    "generate InequalitySpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = InequalitySpec(left, right)
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe "42 != 43"
    }

    "equals InequalitySpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        InequalitySpec(left, right) shouldBe InequalitySpec(left, right)
        InequalitySpec(left, right) shouldNotBe InequalitySpec(right, left)
    }

    "build InequalitySpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = InequalitySpec.builder()
            .left(left)
            .right(right)
            .build()
        node.left shouldBe left
        node.right shouldBe right
    }

    "create LessThanSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = LessThanSpec(left, right)
        node.left shouldBe left
        node.right shouldBe right
    }

    "generate LessThanSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = LessThanSpec(left, right)
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe "42 < 43"
    }

    "equals LessThanSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        LessThanSpec(left, right) shouldBe LessThanSpec(left, right)
        LessThanSpec(left, right) shouldNotBe LessThanSpec(right, left)
    }

    "build LessThanSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = LessThanSpec.builder()
            .left(left)
            .right(right)
            .build()
        node.left shouldBe left
        node.right shouldBe right
    }

    "create LessThanOrEqualSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = LessThanOrEqualSpec(left, right)
        node.left shouldBe left
        node.right shouldBe right
    }

    "generate LessThanOrEqualSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = LessThanOrEqualSpec(left, right)
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe "42 <= 43"
    }

    "equals LessThanOrEqualSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        LessThanOrEqualSpec(left, right) shouldBe LessThanOrEqualSpec(left, right)
        LessThanOrEqualSpec(left, right) shouldNotBe LessThanOrEqualSpec(right, left)
    }

    "build LessThanOrEqualSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = LessThanOrEqualSpec.builder()
            .left(left)
            .right(right)
            .build()
        node.left shouldBe left
        node.right shouldBe right
    }

    "create GreaterThanSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = GreaterThanSpec(left, right)
        node.left shouldBe left
        node.right shouldBe right
    }

    "generate GreaterThanSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = GreaterThanSpec(left, right)
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe "42 > 43"
    }

    "equals GreaterThanSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        GreaterThanSpec(left, right) shouldBe GreaterThanSpec(left, right)
        GreaterThanSpec(left, right) shouldNotBe GreaterThanSpec(right, left)
    }

    "build GreaterThanSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = GreaterThanSpec.builder()
            .left(left)
            .right(right)
            .build()
        node.left shouldBe left
        node.right shouldBe right
    }

    "create GreaterThanOrEqualSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = GreaterThanOrEqualSpec(left, right)
        node.left shouldBe left
        node.right shouldBe right
    }

    "generate GreaterThanOrEqualSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = GreaterThanOrEqualSpec(left, right)
        val ctx = GenerationContext()
        node.generate(ctx) shouldBe "42 >= 43"
    }

    "equals GreaterThanOrEqualSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        GreaterThanOrEqualSpec(left, right) shouldBe GreaterThanOrEqualSpec(left, right)
        GreaterThanOrEqualSpec(left, right) shouldNotBe GreaterThanOrEqualSpec(right, left)
    }

    "build GreaterThanOrEqualSpec" {
        val left = IntLiteralSpec(42)
        val right = IntLiteralSpec(43)
        val node = GreaterThanOrEqualSpec.builder()
            .left(left)
            .right(right)
            .build()
        node.left shouldBe left
        node.right shouldBe right
    }
})
