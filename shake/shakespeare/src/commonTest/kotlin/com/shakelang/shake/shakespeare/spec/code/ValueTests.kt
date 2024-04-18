package com.shakelang.shake.shakespeare.spec.code

import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.NamespaceSpec
import com.shakelang.util.testlib.FlatTestSpec
import com.shakelang.util.testlib.TestSpecContext
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ValueTests : FlatTestSpec({

    describe("Literals") {
        describe("StringLiteralSpec") {

            describe("create") {
                it("should create from string") {
                    val value = "Hello, World!"
                    val node = StringLiteralSpec(value)
                    node.value shouldBe value
                }
            }

            describe("generate()") {
                it("should generate the string") {
                    val value = "Hello, World!"
                    val node = StringLiteralSpec(value)
                    val ctx = GenerationContext()
                    node.generate(ctx) shouldBe "\"$value\""
                }
            }

            describe("equals()") {
                it("should be equal to same instance") {
                    val node = StringLiteralSpec("Hello, World!")
                    node shouldBe node
                }
                it("should be equal to another StringLiteralSpec with the same value") {
                    StringLiteralSpec("Hello, World!") shouldBe StringLiteralSpec("Hello, World!")
                }
                it("should not be equal to another StringLiteralSpec with a different value") {
                    StringLiteralSpec("Hello, World!") shouldNotBe StringLiteralSpec("Hello, World")
                }
                it("should not be equal to another type") {
                    StringLiteralSpec("Hello, World!") shouldNotBe IntLiteralSpec(42)
                }
                it("should not be equal to null") {
                    StringLiteralSpec("Hello, World!") shouldNotBe null
                }
            }

            describe("builder()") {
                it("should create a builder") {
                    val builder = StringLiteralSpec.builder()
                    builder shouldNotBe null
                    builder.value shouldBe null
                }

                describe("builder.value()") {
                    it("should set the value") {
                        val value = "Hello, World!"
                        val builder = StringLiteralSpec.builder()
                        builder.value(value)
                        builder.value shouldBe value
                    }
                }

                describe("builder.build()") {
                    it("should build the StringLiteralSpec") {
                        val value = "Hello, World!"
                        val node = StringLiteralSpec.builder()
                            .value(value)
                            .build()
                        node.value shouldBe value
                    }

                    it("should throw an exception if the value is not set") {
                        val builder = StringLiteralSpec.builder()
                        shouldThrowWithMessage<IllegalStateException>("Value not set") {
                            builder.build()
                        }
                    }
                }
            }
        }

        describe("IntLiteralSpec") {

            describe("create") {
                it("should create from int") {
                    val value = 42
                    val node = IntLiteralSpec(value)
                    node.value shouldBe value
                }
            }

            describe("generate()") {
                it("should generate the int") {
                    val value = 42
                    val node = IntLiteralSpec(value)
                    val ctx = GenerationContext()
                    node.generate(ctx) shouldBe value.toString()
                }
            }

            describe("equals()") {
                it("should be equal to same instance") {
                    val node = IntLiteralSpec(42)
                    node shouldBe node
                }
                it("should be equal to another IntLiteralSpec with the same value") {
                    IntLiteralSpec(42) shouldBe IntLiteralSpec(42)
                }
                it("should not be equal to another IntLiteralSpec with a different value") {
                    IntLiteralSpec(42) shouldNotBe IntLiteralSpec(43)
                }
                it("should not be equal to another type") {
                    IntLiteralSpec(42) shouldNotBe StringLiteralSpec("42")
                }
                it("should not be equal to null") {
                    IntLiteralSpec(42) shouldNotBe null
                }
            }

            describe("builder()") {
                it("should create a builder") {
                    val builder = IntLiteralSpec.builder()
                    builder shouldNotBe null
                    builder.value shouldBe null
                }

                describe("builder.value()") {
                    it("should set the value") {
                        val value = 42
                        val builder = IntLiteralSpec.builder()
                        builder.value(value)
                        builder.value shouldBe value
                    }
                }

                describe("builder.build()") {
                    it("should build the IntLiteralSpec") {
                        val value = 42
                        val node = IntLiteralSpec.builder()
                            .value(value)
                            .build()
                        node.value shouldBe value
                    }

                    it("should throw an exception if the value is not set") {
                        val builder = IntLiteralSpec.builder()
                        shouldThrowWithMessage<IllegalStateException>("Value not set") {
                            builder.build()
                        }
                    }
                }
            }
        }

        describe("FloatLiteralSpec") {

            describe("create") {
                it("should create from float") {
                    val value = 42.0f
                    val node = FloatLiteralSpec(value)
                    node.value shouldBe (value.toDouble()).plusOrMinus(0.0001)
                }

                it("should create from double") {
                    val value = 42.0
                    val node = FloatLiteralSpec(value)
                    node.value shouldBe (value).plusOrMinus(0.0001)
                }
            }

            describe("generate()") {
                it("should generate the float") {
                    val value = 42.0
                    val node = FloatLiteralSpec(value)
                    val ctx = GenerationContext()
                    node.generate(ctx) shouldBe "42.0"
                }
            }

            describe("equals()") {
                it("should be equal to same instance") {
                    val node = FloatLiteralSpec(42.0)
                    node shouldBe node
                }
                it("should be equal to another FloatLiteralSpec with the same value") {
                    FloatLiteralSpec(42.0) shouldBe FloatLiteralSpec(42.0)
                }
                it("should not be equal to another FloatLiteralSpec with a different value") {
                    FloatLiteralSpec(42.0) shouldNotBe FloatLiteralSpec(43.0)
                }
                it("should not be equal to another type") {
                    FloatLiteralSpec(42.0) shouldNotBe StringLiteralSpec("42.0")
                }
                it("should not be equal to null") {
                    FloatLiteralSpec(42.0) shouldNotBe null
                }
            }

            describe("builder()") {
                it("should create a builder") {
                    val builder = FloatLiteralSpec.builder()
                    builder shouldNotBe null
                    builder.value shouldBe null
                }

                describe("builder.value()") {
                    it("should set the value") {
                        val value = 42.0
                        val builder = FloatLiteralSpec.builder()
                        builder.value(value)
                        builder.value shouldBe value
                    }
                }

                describe("builder.build()") {
                    it("should build the FloatLiteralSpec") {
                        val value = 42.0
                        val node = FloatLiteralSpec.builder()
                            .value(value)
                            .build()
                        node.value shouldBe value
                    }

                    it("should throw an exception if the value is not set") {
                        val builder = FloatLiteralSpec.builder()
                        shouldThrowWithMessage<IllegalStateException>("Value not set") {
                            builder.build()
                        }
                    }
                }
            }
        }

        describe("BooleanLiteralSpec") {

            describe("create") {
                it("should create from boolean (true)") {
                    val value = true
                    val node = BooleanLiteralSpec(value)
                    node.value shouldBe value
                }

                it("should create from boolean (false)") {
                    val value = false
                    val node = BooleanLiteralSpec(value)
                    node.value shouldBe value
                }
            }

            describe("generate()") {
                it("should generate from boolean (true)") {
                    val value = true
                    val node = BooleanLiteralSpec(value)
                    val ctx = GenerationContext()
                    node.generate(ctx) shouldBe "true"
                }

                it("should generate from boolean (false)") {
                    val value = false
                    val node = BooleanLiteralSpec(value)
                    val ctx = GenerationContext()
                    node.generate(ctx) shouldBe "false"
                }
            }

            describe("equals()") {
                it("should be equal to same instance") {
                    val node = BooleanLiteralSpec(true)
                    node shouldBe node
                }
                it("should be equal to another BooleanLiteralSpec with the same value") {
                    BooleanLiteralSpec(true) shouldBe BooleanLiteralSpec(true)
                }
                it("should not be equal to another BooleanLiteralSpec with a different value") {
                    BooleanLiteralSpec(true) shouldNotBe BooleanLiteralSpec(false)
                }
                it("should not be equal to another type") {
                    BooleanLiteralSpec(true) shouldNotBe StringLiteralSpec("true")
                }
                it("should not be equal to null") {
                    BooleanLiteralSpec(true) shouldNotBe null
                }
            }

            describe("builder()") {
                it("should create a builder") {
                    val builder = BooleanLiteralSpec.builder()
                    builder shouldNotBe null
                    builder.value shouldBe null
                }

                describe("builder.value()") {
                    it("should set the value") {
                        val value = true
                        val builder = BooleanLiteralSpec.builder()
                        builder.value(value)
                        builder.value shouldBe value
                    }
                }

                describe("builder.build()") {
                    it("should build the BooleanLiteralSpec") {
                        val value = true
                        val node = BooleanLiteralSpec.builder()
                            .value(value)
                            .build()
                        node.value shouldBe value
                    }

                    it("should throw an exception if the value is not set") {
                        val builder = BooleanLiteralSpec.builder()
                        shouldThrowWithMessage<IllegalStateException>("Value not set") {
                            builder.build()
                        }
                    }
                }
            }
        }
        describe("NullLiteralSpec") {

            describe("create") {
                it("should create") {
                    val node = NullLiteralSpec.INSTANCE
                    node shouldBe NullLiteralSpec.INSTANCE
                }
            }

            describe("generate()") {
                it("should generate") {
                    val node = NullLiteralSpec.INSTANCE
                    val ctx = GenerationContext()
                    node.generate(ctx) shouldBe "null"
                }
            }

            describe("equals()") {
                it("should be equal to same instance") {
                    NullLiteralSpec.INSTANCE shouldBe NullLiteralSpec.INSTANCE
                }
                it("should not be equal to another type") {
                    NullLiteralSpec.INSTANCE shouldNotBe StringLiteralSpec("null")
                }
                it("should not be equal to another type") {
                    NullLiteralSpec.INSTANCE shouldNotBe StringLiteralSpec("null")
                }
            }
        }

        describe("VariableReferenceSpec") {

            describe("create") {
                it("should create") {
                    val name = "variable"
                    val node = VariableReferenceSpec(name)
                    node.name.name shouldBe arrayOf(name)
                }

                it("should create with multiple names") {
                    val name = "variable"
                    val node = VariableReferenceSpec(name, "name", "name2")
                    node.name.name shouldBe arrayOf(name, "name", "name2")
                }

                it("should create from namespace") {
                    val name = "variable"
                    val node = VariableReferenceSpec(NamespaceSpec(name))
                    node.name.name shouldBe arrayOf(name)
                }
            }

            describe("generate()") {
                it("should generate") {
                    val name = "variable"
                    val node = VariableReferenceSpec(name)
                    val ctx = GenerationContext()
                    node.generate(ctx) shouldBe name
                }

                it("should generate with multiple names") {
                    val name = "variable"
                    val node = VariableReferenceSpec(name, "name", "name2")
                    val ctx = GenerationContext()
                    node.generate(ctx) shouldBe "variable.name.name2"
                }
            }

            describe("equals()") {
                it("should be equal to same instance") {
                    val node = VariableReferenceSpec("variable")
                    node shouldBe node
                }
                it("should be equal to another VariableReferenceSpec with the same name") {
                    VariableReferenceSpec("variable") shouldBe VariableReferenceSpec("variable")
                }
                it("should not be equal to another VariableReferenceSpec with a different name") {
                    VariableReferenceSpec("variable") shouldNotBe VariableReferenceSpec("variable2")
                }
                it("should not be equal to another type") {
                    VariableReferenceSpec("variable") shouldNotBe StringLiteralSpec("variable")
                }
                it("should not be equal to null") {
                    VariableReferenceSpec("variable") shouldNotBe null
                }
            }

            describe("builder()") {
                it("should create a builder") {
                    val builder = VariableReferenceSpec.builder()
                    builder shouldNotBe null
                    builder.name shouldBe null
                }

                describe("builder.name()") {
                    it("should set the name") {
                        val name = "variable"
                        val builder = VariableReferenceSpec.builder()
                        builder.name(name) shouldBe builder
                        builder.name shouldBe NamespaceSpec(name)
                    }
                    it("should append the name") {
                        val name = "variable"
                        val builder = VariableReferenceSpec.builder()
                        builder.name(name) shouldBe builder
                        builder.name("name") shouldBe builder
                        builder.name shouldBe NamespaceSpec(name, "name")
                    }

                    it("should append the name multiple times") {
                        val name = "variable"
                        val builder = VariableReferenceSpec.builder()
                        builder.name(name) shouldBe builder
                        builder.name("name") shouldBe builder
                        builder.name("name2") shouldBe builder
                        builder.name shouldBe NamespaceSpec(name, "name", "name2")
                    }
                }

                describe("builder.build()") {
                    it("should build the VariableReferenceSpec") {
                        val name = "variable"
                        val node = VariableReferenceSpec.builder()
                            .name(name)
                            .build()
                        node.name.name shouldBe arrayOf(name)
                    }

                    it("should throw an exception if the name is not set") {
                        val builder = VariableReferenceSpec.builder()
                        shouldThrowWithMessage<IllegalStateException>("Name not set") {
                            builder.build()
                        }
                    }
                }
            }
        }
    }

    fun <T : AbstractDualOperatorSpec.AbstractDualOperatorSpecBuilder<T>> TestSpecContext.abstractDualOperatorSpecBuilderTests(createBuilder: () -> AbstractDualOperatorSpec.AbstractDualOperatorSpecBuilder<T>) {
        describe("builder.left()") {
            it("should set the left value (String)") {
                val left = ValueSpec.of("42")
                val builder = createBuilder()
                builder.left("42")
                builder.left shouldBe left
            }

            it("should set the left value (IntLiteralSpec)") {
                val left = IntLiteralSpec(42)
                val builder = createBuilder()
                builder.left(left)
                builder.left shouldBe left
            }

            it("should set the left value (Int)") {
                val left = IntLiteralSpec(42)
                val builder = createBuilder()
                builder.left(42)
                builder.left shouldBe left
            }

            it("should set the left value (Long)") {
                val left = IntLiteralSpec(42)
                val builder = createBuilder()
                builder.left(42L)
                builder.left shouldBe left
            }

            it("should set the left value (Float)") {
                val left = FloatLiteralSpec(42.0)
                val builder = createBuilder()
                builder.left(42.0f)
                builder.left shouldBe left
            }

            it("should set the left value (Double)") {
                val left = FloatLiteralSpec(42.0)
                val builder = createBuilder()
                builder.left(42.0)
                builder.left shouldBe left
            }
        }

        describe("builder.right()") {
            it("should set the right value") {
                val right = IntLiteralSpec(42)
                val builder = createBuilder()
                builder.right(right)
                builder.right shouldBe right
            }

            it("should set the right value (String)") {
                val right = IntLiteralSpec(42)
                val builder = createBuilder()
                builder.right("42")
                builder.right shouldBe right
            }

            it("should set the right value (IntLiteralSpec)") {
                val right = IntLiteralSpec(42)
                val builder = createBuilder()
                builder.right(right)
                builder.right shouldBe right
            }

            it("should set the right value (Int)") {
                val right = IntLiteralSpec(42)
                val builder = createBuilder()
                builder.right(42)
                builder.right shouldBe right
            }

            it("should set the right value (Long)") {
                val right = IntLiteralSpec(42)
                val builder = createBuilder()
                builder.right(42L)
                builder.right shouldBe right
            }

            it("should set the right value (Float)") {
                val right = FloatLiteralSpec(42.0)
                val builder = createBuilder()
                builder.right(42.0f)
                builder.right shouldBe right
            }

            it("should set the right value (Double)") {
                val right = FloatLiteralSpec(42.0)
                val builder = createBuilder()
                builder.right(42.0)
                builder.right shouldBe right
            }
        }
    }

    fun <T : AbstractUnaryOperatorSpec.AbstractUnaryOperatorSpecBuilder<T>> TestSpecContext.abstractUnaryOperatorSpecBuilderTests(createBuilder: () -> AbstractUnaryOperatorSpec.AbstractUnaryOperatorSpecBuilder<T>) {
        describe("builder.value()") {
            it("should set the value (String)") {
                val value = ValueSpec.of("42")
                val builder = createBuilder()
                builder.value("42")
                builder.value shouldBe value
            }

            it("should set the value (IntLiteralSpec)") {
                val value = IntLiteralSpec(42)
                val builder = createBuilder()
                builder.value(value)
                builder.value shouldBe value
            }

            it("should set the value (Int)") {
                val value = IntLiteralSpec(42)
                val builder = createBuilder()
                builder.value(42)
                builder.value shouldBe value
            }

            it("should set the value (Long)") {
                val value = IntLiteralSpec(42)
                val builder = createBuilder()
                builder.value(42L)
                builder.value shouldBe value
            }

            it("should set the value (Float)") {
                val value = FloatLiteralSpec(42.0)
                val builder = createBuilder()
                builder.value(42.0f)
                builder.value shouldBe value
            }

            it("should set the value (Double)") {
                val value = FloatLiteralSpec(42.0)
                val builder = createBuilder()
                builder.value(42.0)
                builder.value shouldBe value
            }
        }
    }

    describe("AdditionSpec") {

        describe("create") {
            it("should create") {
                val left = IntLiteralSpec(42)
                val right = IntLiteralSpec(43)
                val node = AdditionSpec(left, right)
                node.left shouldBe left
                node.right shouldBe right
            }
        }

        describe("generate()") {
            it("should generate") {
                val left = IntLiteralSpec(42)
                val right = IntLiteralSpec(43)
                val node = AdditionSpec(left, right)
                val ctx = GenerationContext()
                node.generate(ctx) shouldBe "42 + 43"
            }
        }

        describe("equals()") {
            it("should be equal to same instance") {
                val left = IntLiteralSpec(42)
                val right = IntLiteralSpec(43)
                val node = AdditionSpec(left, right)
                node shouldBe node
            }
            it("should be equal to another AdditionSpec with the same values") {
                AdditionSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldBe AdditionSpec(IntLiteralSpec(42), IntLiteralSpec(43))
            }
            it("should not be equal to another AdditionSpec with different values") {
                AdditionSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldNotBe AdditionSpec(IntLiteralSpec(43), IntLiteralSpec(42))
            }

            it("should not be equal to another type") {
                AdditionSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldNotBe IntLiteralSpec(42)
            }

            it("should not be equal to null") {
                AdditionSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldNotBe null
            }
        }

        describe("builder()") {
            it("should create a builder") {
                val builder = AdditionSpec.builder()
                builder shouldNotBe null
                builder.left shouldBe null
                builder.right shouldBe null
            }

            abstractDualOperatorSpecBuilderTests(AdditionSpec::builder)

            describe("builder.build()") {

                it("should build the AdditionSpec") {
                    val left = IntLiteralSpec(42)
                    val right = IntLiteralSpec(43)
                    val node = AdditionSpec.builder()
                        .left(left)
                        .right(right)
                        .build()
                    node.left shouldBe left
                    node.right shouldBe right
                }

                it("should throw an exception if the left value is not set") {
                    val builder = AdditionSpec.builder()
                    builder.right(IntLiteralSpec(42))
                    shouldThrowWithMessage<IllegalStateException>("Left not set") {
                        builder.build()
                    }
                }

                it("should throw an exception if the right value is not set") {
                    val builder = AdditionSpec.builder()
                    builder.left(IntLiteralSpec(42))
                    shouldThrowWithMessage<IllegalStateException>("Right not set") {
                        builder.build()
                    }
                }
            }
        }
    }

    describe("SubtractionSpec") {
        describe("create") {
            it("should create") {
                val left = IntLiteralSpec(42)
                val right = IntLiteralSpec(43)
                val node = SubtractionSpec(left, right)
                node.left shouldBe left
                node.right shouldBe right
            }
        }

        describe("generate()") {
            it("should generate") {
                val left = IntLiteralSpec(42)
                val right = IntLiteralSpec(43)
                val node = SubtractionSpec(left, right)
                val ctx = GenerationContext()
                node.generate(ctx) shouldBe "42 - 43"
            }
        }

        describe("equals()") {
            it("should be equal to same instance") {
                val left = IntLiteralSpec(42)
                val right = IntLiteralSpec(43)
                val node = SubtractionSpec(left, right)
                node shouldBe node
            }
            it("should be equal to another SubtractionSpec with the same values") {
                SubtractionSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldBe SubtractionSpec(IntLiteralSpec(42), IntLiteralSpec(43))
            }
            it("should not be equal to another SubtractionSpec with different values") {
                SubtractionSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldNotBe SubtractionSpec(IntLiteralSpec(43), IntLiteralSpec(42))
            }

            it("should not be equal to another type") {
                SubtractionSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldNotBe IntLiteralSpec(42)
            }

            it("should not be equal to null") {
                SubtractionSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldNotBe null
            }
        }

        describe("builder()") {
            it("should create a builder") {
                val builder = SubtractionSpec.builder()
                builder shouldNotBe null
                builder.left shouldBe null
                builder.right shouldBe null
            }

            abstractDualOperatorSpecBuilderTests(SubtractionSpec::builder)

            describe("builder.build()") {
                it("should build the SubtractionSpec") {
                    val left = IntLiteralSpec(42)
                    val right = IntLiteralSpec(43)
                    val node = SubtractionSpec.builder()
                        .left(left)
                        .right(right)
                        .build()
                    node.left shouldBe left
                    node.right shouldBe right
                }

                it("should throw an exception if the left value is not set") {
                    val builder = SubtractionSpec.builder()
                    builder.right(IntLiteralSpec(42))
                    shouldThrowWithMessage<IllegalStateException>("Left not set") {
                        builder.build()
                    }
                }

                it("should throw an exception if the right value is not set") {
                    val builder = SubtractionSpec.builder()
                    builder.left(IntLiteralSpec(42))
                    shouldThrowWithMessage<IllegalStateException>("Right not set") {
                        builder.build()
                    }
                }
            }
        }
    }

    describe("MultiplicationSpec") {
        describe("create") {
            it("should create") {
                val left = IntLiteralSpec(42)
                val right = IntLiteralSpec(43)
                val node = MultiplicationSpec(left, right)
                node.left shouldBe left
                node.right shouldBe right
            }
        }

        describe("generate()") {
            it("should generate") {
                val left = IntLiteralSpec(42)
                val right = IntLiteralSpec(43)
                val node = MultiplicationSpec(left, right)
                val ctx = GenerationContext()
                node.generate(ctx) shouldBe "42 * 43"
            }
        }

        describe("equals()") {
            it("should be equal to same instance") {
                val left = IntLiteralSpec(42)
                val right = IntLiteralSpec(43)
                val node = MultiplicationSpec(left, right)
                node shouldBe node
            }
            it("should be equal to another MultiplicationSpec with the same values") {
                MultiplicationSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldBe MultiplicationSpec(IntLiteralSpec(42), IntLiteralSpec(43))
            }
            it("should not be equal to another MultiplicationSpec with different values") {
                MultiplicationSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldNotBe MultiplicationSpec(IntLiteralSpec(43), IntLiteralSpec(42))
            }

            it("should not be equal to another type") {
                MultiplicationSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldNotBe IntLiteralSpec(42)
            }

            it("should not be equal to null") {
                MultiplicationSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldNotBe null
            }
        }

        describe("builder()") {
            it("should create a builder") {
                val builder = MultiplicationSpec.builder()
                builder shouldNotBe null
                builder.left shouldBe null
                builder.right shouldBe null
            }

            abstractDualOperatorSpecBuilderTests(MultiplicationSpec::builder)

            describe("builder.build()") {
                it("should build the MultiplicationSpec") {
                    val left = IntLiteralSpec(42)
                    val right = IntLiteralSpec(43)
                    val node = MultiplicationSpec.builder()
                        .left(left)
                        .right(right)
                        .build()
                    node.left shouldBe left
                    node.right shouldBe right
                }

                it("should throw an exception if the left value is not set") {
                    val builder = MultiplicationSpec.builder()
                    builder.right(IntLiteralSpec(42))
                    shouldThrowWithMessage<IllegalStateException>("Left not set") {
                        builder.build()
                    }
                }

                it("should throw an exception if the right value is not set") {
                    val builder = MultiplicationSpec.builder()
                    builder.left(IntLiteralSpec(42))
                    shouldThrowWithMessage<IllegalStateException>("Right not set") {
                        builder.build()
                    }
                }
            }
        }
    }

    describe("DivisionSpec") {
        describe("create") {
            it("should create") {
                val left = IntLiteralSpec(42)
                val right = IntLiteralSpec(43)
                val node = DivisionSpec(left, right)
                node.left shouldBe left
                node.right shouldBe right
            }
        }

        describe("generate()") {
            it("should generate") {
                val left = IntLiteralSpec(42)
                val right = IntLiteralSpec(43)
                val node = DivisionSpec(left, right)
                val ctx = GenerationContext()
                node.generate(ctx) shouldBe "42 / 43"
            }
        }

        describe("equals()") {
            it("should be equal to same instance") {
                val left = IntLiteralSpec(42)
                val right = IntLiteralSpec(43)
                val node = DivisionSpec(left, right)
                node shouldBe node
            }
            it("should be equal to another DivisionSpec with the same values") {
                DivisionSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldBe DivisionSpec(IntLiteralSpec(42), IntLiteralSpec(43))
            }
            it("should not be equal to another DivisionSpec with different values") {
                DivisionSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldNotBe DivisionSpec(IntLiteralSpec(43), IntLiteralSpec(42))
            }

            it("should not be equal to another type") {
                DivisionSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldNotBe IntLiteralSpec(42)
            }

            it("should not be equal to null") {
                DivisionSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldNotBe null
            }
        }

        describe("builder()") {
            it("should create a builder") {
                val builder = DivisionSpec.builder()
                builder shouldNotBe null
                builder.left shouldBe null
                builder.right shouldBe null
            }

            abstractDualOperatorSpecBuilderTests(DivisionSpec::builder)

            describe("builder.build()") {
                it("should build the DivisionSpec") {
                    val left = IntLiteralSpec(42)
                    val right = IntLiteralSpec(43)
                    val node = DivisionSpec.builder()
                        .left(left)
                        .right(right)
                        .build()
                    node.left shouldBe left
                    node.right shouldBe right
                }

                it("should throw an exception if the left value is not set") {
                    val builder = DivisionSpec.builder()
                    builder.right(IntLiteralSpec(42))
                    shouldThrowWithMessage<IllegalStateException>("Left not set") {
                        builder.build()
                    }
                }

                it("should throw an exception if the right value is not set") {
                    val builder = DivisionSpec.builder()
                    builder.left(IntLiteralSpec(42))
                    shouldThrowWithMessage<IllegalStateException>("Right not set") {
                        builder.build()
                    }
                }
            }
        }
    }

    describe("ModuloSpec") {
        describe("create") {
            it("should create") {
                val left = IntLiteralSpec(42)
                val right = IntLiteralSpec(43)
                val node = ModuloSpec(left, right)
                node.left shouldBe left
                node.right shouldBe right
            }
        }

        describe("generate()") {
            it("should generate") {
                val left = IntLiteralSpec(42)
                val right = IntLiteralSpec(43)
                val node = ModuloSpec(left, right)
                val ctx = GenerationContext()
                node.generate(ctx) shouldBe "42 % 43"
            }
        }

        describe("equals()") {
            it("should be equal to same instance") {
                val left = IntLiteralSpec(42)
                val right = IntLiteralSpec(43)
                val node = ModuloSpec(left, right)
                node shouldBe node
            }
            it("should be equal to another ModuloSpec with the same values") {
                ModuloSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldBe ModuloSpec(IntLiteralSpec(42), IntLiteralSpec(43))
            }
            it("should not be equal to another ModuloSpec with different values") {
                ModuloSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldNotBe ModuloSpec(IntLiteralSpec(43), IntLiteralSpec(42))
            }

            it("should not be equal to another type") {
                ModuloSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldNotBe IntLiteralSpec(42)
            }

            it("should not be equal to null") {
                ModuloSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldNotBe null
            }
        }

        describe("builder()") {
            it("should create a builder") {
                val builder = ModuloSpec.builder()
                builder shouldNotBe null
                builder.left shouldBe null
                builder.right shouldBe null
            }

            abstractDualOperatorSpecBuilderTests(ModuloSpec::builder)

            describe("builder.build()") {
                it("should build the ModuloSpec") {
                    val left = IntLiteralSpec(42)
                    val right = IntLiteralSpec(43)
                    val node = ModuloSpec.builder()
                        .left(left)
                        .right(right)
                        .build()
                    node.left shouldBe left
                    node.right shouldBe right
                }

                it("should throw an exception if the left value is not set") {
                    val builder = ModuloSpec.builder()
                    builder.right(IntLiteralSpec(42))
                    shouldThrowWithMessage<IllegalStateException>("Left not set") {
                        builder.build()
                    }
                }

                it("should throw an exception if the right value is not set") {
                    val builder = ModuloSpec.builder()
                    builder.left(IntLiteralSpec(42))
                    shouldThrowWithMessage<IllegalStateException>("Right not set") {
                        builder.build()
                    }
                }
            }
        }
    }

    describe("PowerSpec") {
        describe("create") {
            it("should create") {
                val left = IntLiteralSpec(42)
                val right = IntLiteralSpec(43)
                val node = PowerSpec(left, right)
                node.left shouldBe left
                node.right shouldBe right
            }
        }

        describe("generate()") {
            it("should generate") {
                val left = IntLiteralSpec(42)
                val right = IntLiteralSpec(43)
                val node = PowerSpec(left, right)
                val ctx = GenerationContext()
                node.generate(ctx) shouldBe "42 ** 43"
            }
        }

        describe("equals()") {
            it("should be equal to same instance") {
                val left = IntLiteralSpec(42)
                val right = IntLiteralSpec(43)
                val node = PowerSpec(left, right)
                node shouldBe node
            }
            it("should be equal to another PowerSpec with the same values") {
                PowerSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldBe PowerSpec(IntLiteralSpec(42), IntLiteralSpec(43))
            }
            it("should not be equal to another PowerSpec with different values") {
                PowerSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldNotBe PowerSpec(IntLiteralSpec(43), IntLiteralSpec(42))
            }

            it("should not be equal to another type") {
                PowerSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldNotBe IntLiteralSpec(42)
            }

            it("should not be equal to null") {
                PowerSpec(IntLiteralSpec(42), IntLiteralSpec(43)) shouldNotBe null
            }
        }

        describe("builder()") {
            it("should create a builder") {
                val builder = PowerSpec.builder()
                builder shouldNotBe null
                builder.left shouldBe null
                builder.right shouldBe null
            }

            abstractDualOperatorSpecBuilderTests(PowerSpec::builder)

            describe("builder.build()") {
                it("should build the PowerSpec") {
                    val left = IntLiteralSpec(42)
                    val right = IntLiteralSpec(43)
                    val node = PowerSpec.builder()
                        .left(left)
                        .right(right)
                        .build()
                    node.left shouldBe left
                    node.right shouldBe right
                }

                it("should throw an exception if the left value is not set") {
                    val builder = PowerSpec.builder()
                    builder.right(IntLiteralSpec(42))
                    shouldThrowWithMessage<IllegalStateException>("Left not set") {
                        builder.build()
                    }
                }

                it("should throw an exception if the right value is not set") {
                    val builder = PowerSpec.builder()
                    builder.left(IntLiteralSpec(42))
                    shouldThrowWithMessage<IllegalStateException>("Right not set") {
                        builder.build()
                    }
                }
            }
        }
    }

    describe("UnaryMinusSpec") {
        describe("create") {
            it("should create") {
                val value = IntLiteralSpec(42)
                val node = UnaryMinusSpec(value)
                node.value shouldBe value
            }
        }

        describe("generate") {
            it("should generate") {
                val value = IntLiteralSpec(42)
                val node = UnaryMinusSpec(value)
                val ctx = GenerationContext()
                node.generate(ctx) shouldBe "-42"
            }
        }

        describe("equals") {
            it("should be equal to same instance") {
                val value = IntLiteralSpec(42)
                val node = UnaryMinusSpec(value)
                node shouldBe node
            }
            it("should be equal to another UnaryMinusSpec with the same value") {
                UnaryMinusSpec(IntLiteralSpec(42)) shouldBe UnaryMinusSpec(IntLiteralSpec(42))
            }
            it("should not be equal to another UnaryMinusSpec with a different value") {
                UnaryMinusSpec(IntLiteralSpec(42)) shouldNotBe UnaryMinusSpec(IntLiteralSpec(43))
            }
            it("should not be equal to another type") {
                UnaryMinusSpec(IntLiteralSpec(42)) shouldNotBe IntLiteralSpec(42)
            }
            it("should not be equal to null") {
                UnaryMinusSpec(IntLiteralSpec(42)) shouldNotBe null
            }
        }

        describe("builder") {
            it("should create a builder") {
                val builder = UnaryMinusSpec.builder()
                builder shouldNotBe null
                builder.value shouldBe null
            }

            abstractUnaryOperatorSpecBuilderTests(UnaryMinusSpec::builder)

            describe("builder.build()") {
                it("should build the UnaryMinusSpec") {
                    val value = IntLiteralSpec(42)
                    val node = UnaryMinusSpec.builder()
                        .value(value)
                        .build()
                    node.value shouldBe value
                }

                it("should throw an exception if the value is not set") {
                    val builder = UnaryMinusSpec.builder()
                    shouldThrowWithMessage<IllegalStateException>("Value not set") {
                        builder.build()
                    }
                }
            }
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
