package com.shakelang.shake.shakespeare.spec.code

import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.NamespaceSpec
import com.shakelang.util.testlib.FlatTestSpec
import com.shakelang.util.testlib.TestSpecContext
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class MixedTests : FlatTestSpec(
    {

        fun <T : AbstractAssignmentSpec.AbstractAssignmentSpecBuilder<T>> TestSpecContext.abstractAssignmentSpecBuilderTest(createBuilder: () -> T) {
            describe("builder.name() [AbstractAssignmentSpec]") {
                it("should set the name") {
                    val name = NamespaceSpec("variable")
                    val builder = VariableAssignmentSpec.builder()
                    builder.name(name)
                    builder.name shouldBe name
                }

                it("should set the name (String)") {
                    val name = NamespaceSpec("variable")
                    val builder = createBuilder()
                    builder.name("variable")
                    builder.name shouldBe name
                }
            }

            describe("builder.value() [AbstractAssignmentSpec]") {
                it("should set the value (String)") {
                    val value = ValueSpec.of("42")
                    val builder = createBuilder()
                    builder.value("42")
                    builder.value shouldBe value
                }

                it("should set the value (ValueSpec)") {
                    val value = IntLiteralSpec(42)
                    val builder = createBuilder()
                    builder.value(value)
                    builder.value shouldBe value
                }

                it("should set the value (NamespaceSpec)") {
                    val value = VariableReferenceSpec("variable")
                    val builder = createBuilder()
                    builder.value("variable")
                    builder.value shouldBe value
                }

                it("should set the value (Boolean)") {
                    val value = BooleanLiteralSpec(true)
                    val builder = createBuilder()
                    builder.value(true)
                    builder.value shouldBe value
                }

                it("should set the value (Byte)") {
                    val value = IntLiteralSpec(42)
                    val builder = createBuilder()
                    builder.value(42.toByte())
                    builder.value shouldBe value
                }

                it("should set the value (Short)") {
                    val value = IntLiteralSpec(42)
                    val builder = createBuilder()
                    builder.value(42.toShort())
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

        fun <T : AbstractModificationSpec.AbstractModificationSpecBuilder<T>> TestSpecContext.abstractModificationSpecBuilderTest(createBuilder: () -> T) {
            describe("builder.name() [AbstractModificationSpec]") {
                it("should set the name") {
                    val name = NamespaceSpec("variable")
                    val builder = VariableAssignmentSpec.builder()
                    builder.name(name)
                    builder.name shouldBe name
                }

                it("should set the name (String)") {
                    val name = NamespaceSpec("variable")
                    val builder = createBuilder()
                    builder.name("variable")
                    builder.name shouldBe name
                }
            }
        }

        describe("VariableAssignmentSpec") {

            describe("create") {
                it("should create from namespace and value") {
                    val name = NamespaceSpec("variable")
                    val value = ValueSpec.of("test")
                    val node = VariableAssignmentSpec(name, value)
                    node.name shouldBe name
                    node.value shouldBe value
                }
            }

            describe("generate()") {
                it("should generate the assignment") {
                    val name = NamespaceSpec("variable")
                    val value = ValueSpec.of("test")
                    val node = VariableAssignmentSpec(name, value)
                    val ctx = GenerationContext()
                    node.generate(ctx) shouldBe "variable = test"
                }
            }

            describe("equals()") {
                it("should be equal to same instance") {
                    val node = VariableAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
                    node shouldBe node
                }
                it("should be equal to another VariableAssignmentSpec with the same name and value") {
                    VariableAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldBe VariableAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
                }
                it("should not be equal to another VariableAssignmentSpec with a different name") {
                    VariableAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe VariableAssignmentSpec(NamespaceSpec("variable2"), ValueSpec.of("test"))
                }
                it("should not be equal to another VariableAssignmentSpec with a different value") {
                    VariableAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe VariableAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test2"))
                }
                it("should not be equal to another type") {
                    VariableAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe IntLiteralSpec(42)
                }
                it("should not be equal to null") {
                    VariableAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe null
                }
            }

            describe("builder()") {
                it("should create a builder") {
                    val builder = VariableAssignmentSpec.builder()
                    builder shouldNotBe null
                    builder.name shouldBe null
                    builder.value shouldBe null
                }

                abstractAssignmentSpecBuilderTest(VariableAssignmentSpec::builder)

                describe("builder.build()") {
                    it("should build the VariableAssignmentSpec") {
                        val name = NamespaceSpec("variable")
                        val value = ValueSpec.of("test")
                        val node = VariableAssignmentSpec.builder()
                            .name(name)
                            .value(value)
                            .build()
                        node.name shouldBe name
                        node.value shouldBe value
                    }

                    it("should throw an exception if the name is not set") {
                        val builder = VariableAssignmentSpec.builder()
                        builder.value(ValueSpec.of("test"))
                        shouldThrowWithMessage<IllegalStateException>("Name not set") {
                            builder.build()
                        }
                    }

                    it("should throw an exception if the value is not set") {
                        val builder = VariableAssignmentSpec.builder()
                        builder.name(NamespaceSpec("variable"))
                        shouldThrowWithMessage<IllegalStateException>("Value not set") {
                            builder.build()
                        }
                    }
                }
            }
        }

        describe("VariableAdditionAssignmentSpec") {
            describe("create") {
                it("should create from namespace and value") {
                    val name = NamespaceSpec("variable")
                    val value = ValueSpec.of("test")
                    val node = VariableAdditionAssignmentSpec(name, value)
                    node.name shouldBe name
                    node.value shouldBe value
                }
            }

            describe("generate()") {
                it("should generate the assignment") {
                    val name = NamespaceSpec("variable")
                    val value = ValueSpec.of("test")
                    val node = VariableAdditionAssignmentSpec(name, value)
                    val ctx = GenerationContext()
                    node.generate(ctx) shouldBe "variable += test"
                }
            }

            describe("equals()") {
                it("should be equal to same instance") {
                    val node = VariableAdditionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
                    node shouldBe node
                }
                it("should be equal to another VariableAdditionAssignmentSpec with the same name and value") {
                    VariableAdditionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldBe VariableAdditionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
                }
                it("should not be equal to another VariableAdditionAssignmentSpec with a different name") {
                    VariableAdditionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe VariableAdditionAssignmentSpec(NamespaceSpec("variable2"), ValueSpec.of("test"))
                }
                it("should not be equal to another VariableAdditionAssignmentSpec with a different value") {
                    VariableAdditionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe VariableAdditionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test2"))
                }
                it("should not be equal to another type") {
                    VariableAdditionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe IntLiteralSpec(42)
                }
                it("should not be equal to null") {
                    VariableAdditionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe null
                }
            }

            describe("builder()") {
                it("should create a builder") {
                    val builder = VariableAdditionAssignmentSpec.builder()
                    builder shouldNotBe null
                    builder.name shouldBe null
                    builder.value shouldBe null
                }

                abstractAssignmentSpecBuilderTest(VariableAdditionAssignmentSpec::builder)

                describe("builder.build()") {
                    it("should build the VariableAdditionAssignmentSpec") {
                        val name = NamespaceSpec("variable")
                        val value = ValueSpec.of("test")
                        val node = VariableAdditionAssignmentSpec.builder()
                            .name(name)
                            .value(value)
                            .build()
                        node.name shouldBe name
                        node.value shouldBe value
                    }

                    it("should throw an exception if the name is not set") {
                        val builder = VariableAdditionAssignmentSpec.builder()
                        builder.value(ValueSpec.of("test"))
                        shouldThrowWithMessage<IllegalStateException>("Name not set") {
                            builder.build()
                        }
                    }

                    it("should throw an exception if the value is not set") {
                        val builder = VariableAdditionAssignmentSpec.builder()
                        builder.name(NamespaceSpec("variable"))
                        shouldThrowWithMessage<IllegalStateException>("Value not set") {
                            builder.build()
                        }
                    }
                }
            }
        }

        describe("VariableSubtractionAssignmentSpec") {
            describe("create") {
                it("should create from namespace and value") {
                    val name = NamespaceSpec("variable")
                    val value = ValueSpec.of("test")
                    val node = VariableSubtractionAssignmentSpec(name, value)
                    node.name shouldBe name
                    node.value shouldBe value
                }
            }

            describe("generate()") {
                it("should generate the assignment") {
                    val name = NamespaceSpec("variable")
                    val value = ValueSpec.of("test")
                    val node = VariableSubtractionAssignmentSpec(name, value)
                    val ctx = GenerationContext()
                    node.generate(ctx) shouldBe "variable -= test"
                }
            }

            describe("equals()") {
                it("should be equal to same instance") {
                    val node = VariableSubtractionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
                    node shouldBe node
                }
                it("should be equal to another VariableSubtractionAssignmentSpec with the same name and value") {
                    VariableSubtractionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldBe VariableSubtractionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
                }
                it("should not be equal to another VariableSubtractionAssignmentSpec with a different name") {
                    VariableSubtractionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe VariableSubtractionAssignmentSpec(NamespaceSpec("variable2"), ValueSpec.of("test"))
                }
                it("should not be equal to another VariableSubtractionAssignmentSpec with a different value") {
                    VariableSubtractionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe VariableSubtractionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test2"))
                }
                it("should not be equal to another type") {
                    VariableSubtractionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe IntLiteralSpec(42)
                }
                it("should not be equal to null") {
                    VariableSubtractionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe null
                }
            }

            describe("builder()") {
                it("should create a builder") {
                    val builder = VariableSubtractionAssignmentSpec.builder()
                    builder shouldNotBe null
                    builder.name shouldBe null
                    builder.value shouldBe null
                }

                abstractAssignmentSpecBuilderTest(VariableSubtractionAssignmentSpec::builder)

                describe("builder.build()") {
                    it("should build the VariableSubtractionAssignmentSpec") {
                        val name = NamespaceSpec("variable")
                        val value = ValueSpec.of("test")
                        val node = VariableSubtractionAssignmentSpec.builder()
                            .name(name)
                            .value(value)
                            .build()
                        node.name shouldBe name
                        node.value shouldBe value
                    }

                    it("should throw an exception if the name is not set") {
                        val builder = VariableSubtractionAssignmentSpec.builder()
                        builder.value(ValueSpec.of("test"))
                        shouldThrowWithMessage<IllegalStateException>("Name not set") {
                            builder.build()
                        }
                    }

                    it("should throw an exception if the value is not set") {
                        val builder = VariableSubtractionAssignmentSpec.builder()
                        builder.name(NamespaceSpec("variable"))
                        shouldThrowWithMessage<IllegalStateException>("Value not set") {
                            builder.build()
                        }
                    }
                }
            }
        }

        describe("VariableMultiplicationAssignmentSpec") {
            describe("create") {
                it("should create from namespace and value") {
                    val name = NamespaceSpec("variable")
                    val value = ValueSpec.of("test")
                    val node = VariableMultiplicationAssignmentSpec(name, value)
                    node.name shouldBe name
                    node.value shouldBe value
                }
            }

            describe("generate()") {
                it("should generate the assignment") {
                    val name = NamespaceSpec("variable")
                    val value = ValueSpec.of("test")
                    val node = VariableMultiplicationAssignmentSpec(name, value)
                    val ctx = GenerationContext()
                    node.generate(ctx) shouldBe "variable *= test"
                }
            }

            describe("equals()") {
                it("should be equal to same instance") {
                    val node = VariableMultiplicationAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
                    node shouldBe node
                }
                it("should be equal to another VariableMultiplicationAssignmentSpec with the same name and value") {
                    VariableMultiplicationAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldBe VariableMultiplicationAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
                }
                it("should not be equal to another VariableMultiplicationAssignmentSpec with a different name") {
                    VariableMultiplicationAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe VariableMultiplicationAssignmentSpec(NamespaceSpec("variable2"), ValueSpec.of("test"))
                }
                it("should not be equal to another VariableMultiplicationAssignmentSpec with a different value") {
                    VariableMultiplicationAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe VariableMultiplicationAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test2"))
                }
                it("should not be equal to another type") {
                    VariableMultiplicationAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe IntLiteralSpec(42)
                }
                it("should not be equal to null") {
                    VariableMultiplicationAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe null
                }
            }

            describe("builder()") {
                it("should create a builder") {
                    val builder = VariableMultiplicationAssignmentSpec.builder()
                    builder shouldNotBe null
                    builder.name shouldBe null
                    builder.value shouldBe null
                }

                abstractAssignmentSpecBuilderTest(VariableMultiplicationAssignmentSpec::builder)

                describe("builder.build()") {
                    it("should build the VariableMultiplicationAssignmentSpec") {
                        val name = NamespaceSpec("variable")
                        val value = ValueSpec.of("test")
                        val node = VariableMultiplicationAssignmentSpec.builder()
                            .name(name)
                            .value(value)
                            .build()
                        node.name shouldBe name
                        node.value shouldBe value
                    }

                    it("should throw an exception if the name is not set") {
                        val builder = VariableMultiplicationAssignmentSpec.builder()
                        builder.value(ValueSpec.of("test"))
                        shouldThrowWithMessage<IllegalStateException>("Name not set") {
                            builder.build()
                        }
                    }

                    it("should throw an exception if the value is not set") {
                        val builder = VariableMultiplicationAssignmentSpec.builder()
                        builder.name(NamespaceSpec("variable"))
                        shouldThrowWithMessage<IllegalStateException>("Value not set") {
                            builder.build()
                        }
                    }
                }
            }
        }

        describe("VariableDivisionAssignmentSpec") {
            describe("create") {
                it("should create from namespace and value") {
                    val name = NamespaceSpec("variable")
                    val value = ValueSpec.of("test")
                    val node = VariableDivisionAssignmentSpec(name, value)
                    node.name shouldBe name
                    node.value shouldBe value
                }
            }

            describe("generate()") {
                it("should generate the assignment") {
                    val name = NamespaceSpec("variable")
                    val value = ValueSpec.of("test")
                    val node = VariableDivisionAssignmentSpec(name, value)
                    val ctx = GenerationContext()
                    node.generate(ctx) shouldBe "variable /= test"
                }
            }

            describe("equals()") {
                it("should be equal to same instance") {
                    val node = VariableDivisionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
                    node shouldBe node
                }
                it("should be equal to another VariableDivisionAssignmentSpec with the same name and value") {
                    VariableDivisionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldBe VariableDivisionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
                }
                it("should not be equal to another VariableDivisionAssignmentSpec with a different name") {
                    VariableDivisionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe VariableDivisionAssignmentSpec(NamespaceSpec("variable2"), ValueSpec.of("test"))
                }
                it("should not be equal to another VariableDivisionAssignmentSpec with a different value") {
                    VariableDivisionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe VariableDivisionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test2"))
                }
                it("should not be equal to another type") {
                    VariableDivisionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe IntLiteralSpec(42)
                }
                it("should not be equal to null") {
                    VariableDivisionAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe null
                }
            }

            describe("builder()") {
                it("should create a builder") {
                    val builder = VariableDivisionAssignmentSpec.builder()
                    builder shouldNotBe null
                    builder.name shouldBe null
                    builder.value shouldBe null
                }

                abstractAssignmentSpecBuilderTest(VariableDivisionAssignmentSpec::builder)

                describe("builder.build()") {
                    it("should build the VariableDivisionAssignmentSpec") {
                        val name = NamespaceSpec("variable")
                        val value = ValueSpec.of("test")
                        val node = VariableDivisionAssignmentSpec.builder()
                            .name(name)
                            .value(value)
                            .build()
                        node.name shouldBe name
                        node.value shouldBe value
                    }

                    it("should throw an exception if the name is not set") {
                        val builder = VariableDivisionAssignmentSpec.builder()
                        builder.value(ValueSpec.of("test"))
                        shouldThrowWithMessage<IllegalStateException>("Name not set") {
                            builder.build()
                        }
                    }

                    it("should throw an exception if the value is not set") {
                        val builder = VariableDivisionAssignmentSpec.builder()
                        builder.name(NamespaceSpec("variable"))
                        shouldThrowWithMessage<IllegalStateException>("Value not set") {
                            builder.build()
                        }
                    }
                }
            }
        }

        describe("VariableModuloAssignmentSpec") {
            describe("create") {
                it("should create from namespace and value") {
                    val name = NamespaceSpec("variable")
                    val value = ValueSpec.of("test")
                    val node = VariableModuloAssignmentSpec(name, value)
                    node.name shouldBe name
                    node.value shouldBe value
                }
            }

            describe("generate()") {
                it("should generate the assignment") {
                    val name = NamespaceSpec("variable")
                    val value = ValueSpec.of("test")
                    val node = VariableModuloAssignmentSpec(name, value)
                    val ctx = GenerationContext()
                    node.generate(ctx) shouldBe "variable %= test"
                }
            }

            describe("equals()") {
                it("should be equal to same instance") {
                    val node = VariableModuloAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
                    node shouldBe node
                }
                it("should be equal to another VariableModuloAssignmentSpec with the same name and value") {
                    VariableModuloAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldBe VariableModuloAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
                }
                it("should not be equal to another VariableModuloAssignmentSpec with a different name") {
                    VariableModuloAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe VariableModuloAssignmentSpec(NamespaceSpec("variable2"), ValueSpec.of("test"))
                }
                it("should not be equal to another VariableModuloAssignmentSpec with a different value") {
                    VariableModuloAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe VariableModuloAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test2"))
                }
                it("should not be equal to another type") {
                    VariableModuloAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe IntLiteralSpec(42)
                }
                it("should not be equal to null") {
                    VariableModuloAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe null
                }
            }

            describe("builder()") {
                it("should create a builder") {
                    val builder = VariableModuloAssignmentSpec.builder()
                    builder shouldNotBe null
                    builder.name shouldBe null
                    builder.value shouldBe null
                }

                abstractAssignmentSpecBuilderTest(VariableModuloAssignmentSpec::builder)

                describe("builder.build()") {
                    it("should build the VariableModuloAssignmentSpec") {
                        val name = NamespaceSpec("variable")
                        val value = ValueSpec.of("test")
                        val node = VariableModuloAssignmentSpec.builder()
                            .name(name)
                            .value(value)
                            .build()
                        node.name shouldBe name
                        node.value shouldBe value
                    }

                    it("should throw an exception if the name is not set") {
                        val builder = VariableModuloAssignmentSpec.builder()
                        builder.value(ValueSpec.of("test"))
                        shouldThrowWithMessage<IllegalStateException>("Name not set") {
                            builder.build()
                        }
                    }

                    it("should throw an exception if the value is not set") {
                        val builder = VariableModuloAssignmentSpec.builder()
                        builder.name(NamespaceSpec("variable"))
                        shouldThrowWithMessage<IllegalStateException>("Value not set") {
                            builder.build()
                        }
                    }
                }
            }
        }

        describe("VariablePowerAssignmentSpec") {
            describe("create") {
                it("should create from namespace and value") {
                    val name = NamespaceSpec("variable")
                    val value = ValueSpec.of("test")
                    val node = VariablePowerAssignmentSpec(name, value)
                    node.name shouldBe name
                    node.value shouldBe value
                }
            }

            describe("generate()") {
                it("should generate the assignment") {
                    val name = NamespaceSpec("variable")
                    val value = ValueSpec.of("test")
                    val node = VariablePowerAssignmentSpec(name, value)
                    val ctx = GenerationContext()
                    node.generate(ctx) shouldBe "variable **= test"
                }
            }

            describe("equals()") {
                it("should be equal to same instance") {
                    val node = VariablePowerAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
                    node shouldBe node
                }
                it("should be equal to another VariablePowerAssignmentSpec with the same name and value") {
                    VariablePowerAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldBe VariablePowerAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
                }
                it("should not be equal to another VariablePowerAssignmentSpec with a different name") {
                    VariablePowerAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe VariablePowerAssignmentSpec(NamespaceSpec("variable2"), ValueSpec.of("test"))
                }
                it("should not be equal to another VariablePowerAssignmentSpec with a different value") {
                    VariablePowerAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe VariablePowerAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test2"))
                }
                it("should not be equal to another type") {
                    VariablePowerAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe IntLiteralSpec(42)
                }
                it("should not be equal to null") {
                    VariablePowerAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe null
                }
            }

            describe("builder()") {
                it("should create a builder") {
                    val builder = VariablePowerAssignmentSpec.builder()
                    builder shouldNotBe null
                    builder.name shouldBe null
                    builder.value shouldBe null
                }

                abstractAssignmentSpecBuilderTest(VariablePowerAssignmentSpec::builder)

                describe("builder.build()") {
                    it("should build the VariablePowerAssignmentSpec") {
                        val name = NamespaceSpec("variable")
                        val value = ValueSpec.of("test")
                        val node = VariablePowerAssignmentSpec.builder()
                            .name(name)
                            .value(value)
                            .build()
                        node.name shouldBe name
                        node.value shouldBe value
                    }

                    it("should throw an exception if the name is not set") {
                        val builder = VariablePowerAssignmentSpec.builder()
                        builder.value(ValueSpec.of("test"))
                        shouldThrowWithMessage<IllegalStateException>("Name not set") {
                            builder.build()
                        }
                    }

                    it("should throw an exception if the value is not set") {
                        val builder = VariablePowerAssignmentSpec.builder()
                        builder.name(NamespaceSpec("variable"))
                        shouldThrowWithMessage<IllegalStateException>("Value not set") {
                            builder.build()
                        }
                    }
                }
            }
        }

        describe("VariableBitwiseAndAssignmentSpec") {
            describe("create") {
                it("should create from namespace and value") {
                    val name = NamespaceSpec("variable")
                    val value = ValueSpec.of("test")
                    val node = VariableBitwiseAndAssignmentSpec(name, value)
                    node.name shouldBe name
                    node.value shouldBe value
                }
            }

            describe("generate()") {
                it("should generate the assignment") {
                    val name = NamespaceSpec("variable")
                    val value = ValueSpec.of("test")
                    val node = VariableBitwiseAndAssignmentSpec(name, value)
                    val ctx = GenerationContext()
                    node.generate(ctx) shouldBe "variable &= test"
                }
            }

            describe("equals()") {
                it("should be equal to same instance") {
                    val node = VariableBitwiseAndAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
                    node shouldBe node
                }
                it("should be equal to another VariableBitwiseAndAssignmentSpec with the same name and value") {
                    VariableBitwiseAndAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldBe VariableBitwiseAndAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
                }
                it("should not be equal to another VariableBitwiseAndAssignmentSpec with a different name") {
                    VariableBitwiseAndAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe VariableBitwiseAndAssignmentSpec(NamespaceSpec("variable2"), ValueSpec.of("test"))
                }
                it("should not be equal to another VariableBitwiseAndAssignmentSpec with a different value") {
                    VariableBitwiseAndAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe VariableBitwiseAndAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test2"))
                }
                it("should not be equal to another type") {
                    VariableBitwiseAndAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe IntLiteralSpec(42)
                }
                it("should not be equal to null") {
                    VariableBitwiseAndAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe null
                }
            }

            describe("builder()") {
                it("should create a builder") {
                    val builder = VariableBitwiseAndAssignmentSpec.builder()
                    builder shouldNotBe null
                    builder.name shouldBe null
                    builder.value shouldBe null
                }

                abstractAssignmentSpecBuilderTest(VariableBitwiseAndAssignmentSpec::builder)

                describe("builder.build()") {
                    it("should build the VariableBitwiseAndAssignmentSpec") {
                        val name = NamespaceSpec("variable")
                        val value = ValueSpec.of("test")
                        val node = VariableBitwiseAndAssignmentSpec.builder()
                            .name(name)
                            .value(value)
                            .build()
                        node.name shouldBe name
                        node.value shouldBe value
                    }

                    it("should throw an exception if the name is not set") {
                        val builder = VariableBitwiseAndAssignmentSpec.builder()
                        builder.value(ValueSpec.of("test"))
                        shouldThrowWithMessage<IllegalStateException>("Name not set") {
                            builder.build()
                        }
                    }

                    it("should throw an exception if the value is not set") {
                        val builder = VariableBitwiseAndAssignmentSpec.builder()
                        builder.name(NamespaceSpec("variable"))
                        shouldThrowWithMessage<IllegalStateException>("Value not set") {
                            builder.build()
                        }
                    }
                }
            }
        }

        describe("VariableBitwiseOrAssignmentSpec") {
            describe("create") {
                it("should create from namespace and value") {
                    val name = NamespaceSpec("variable")
                    val value = ValueSpec.of("test")
                    val node = VariableBitwiseOrAssignmentSpec(name, value)
                    node.name shouldBe name
                    node.value shouldBe value
                }
            }

            describe("generate()") {
                it("should generate the assignment") {
                    val name = NamespaceSpec("variable")
                    val value = ValueSpec.of("test")
                    val node = VariableBitwiseOrAssignmentSpec(name, value)
                    val ctx = GenerationContext()
                    node.generate(ctx) shouldBe "variable |= test"
                }
            }

            describe("equals()") {
                it("should be equal to same instance") {
                    val node = VariableBitwiseOrAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
                    node shouldBe node
                }
                it("should be equal to another VariableBitwiseOrAssignmentSpec with the same name and value") {
                    VariableBitwiseOrAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldBe VariableBitwiseOrAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
                }
                it("should not be equal to another VariableBitwiseOrAssignmentSpec with a different name") {
                    VariableBitwiseOrAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe VariableBitwiseOrAssignmentSpec(NamespaceSpec("variable2"), ValueSpec.of("test"))
                }
                it("should not be equal to another VariableBitwiseOrAssignmentSpec with a different value") {
                    VariableBitwiseOrAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe VariableBitwiseOrAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test2"))
                }
                it("should not be equal to another type") {
                    VariableBitwiseOrAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe IntLiteralSpec(42)
                }
                it("should not be equal to null") {
                    VariableBitwiseOrAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe null
                }
            }

            describe("builder()") {
                it("should create a builder") {
                    val builder = VariableBitwiseOrAssignmentSpec.builder()
                    builder shouldNotBe null
                    builder.name shouldBe null
                    builder.value shouldBe null
                }

                abstractAssignmentSpecBuilderTest(VariableBitwiseOrAssignmentSpec::builder)

                describe("builder.build()") {
                    it("should build the VariableBitwiseOrAssignmentSpec") {
                        val name = NamespaceSpec("variable")
                        val value = ValueSpec.of("test")
                        val node = VariableBitwiseOrAssignmentSpec.builder()
                            .name(name)
                            .value(value)
                            .build()
                        node.name shouldBe name
                        node.value shouldBe value
                    }

                    it("should throw an exception if the name is not set") {
                        val builder = VariableBitwiseOrAssignmentSpec.builder()
                        builder.value(ValueSpec.of("test"))
                        shouldThrowWithMessage<IllegalStateException>("Name not set") {
                            builder.build()
                        }
                    }

                    it("should throw an exception if the value is not set") {
                        val builder = VariableBitwiseOrAssignmentSpec.builder()
                        builder.name(NamespaceSpec("variable"))
                        shouldThrowWithMessage<IllegalStateException>("Value not set") {
                            builder.build()
                        }
                    }
                }
            }
        }

        describe("VariableBitwiseXorAssignmentSpec") {
            describe("create") {
                it("should create from namespace and value") {
                    val name = NamespaceSpec("variable")
                    val value = ValueSpec.of("test")
                    val node = VariableBitwiseXorAssignmentSpec(name, value)
                    node.name shouldBe name
                    node.value shouldBe value
                }
            }

            describe("generate()") {
                it("should generate the assignment") {
                    val name = NamespaceSpec("variable")
                    val value = ValueSpec.of("test")
                    val node = VariableBitwiseXorAssignmentSpec(name, value)
                    val ctx = GenerationContext()
                    node.generate(ctx) shouldBe "variable ^= test"
                }
            }

            describe("equals()") {
                it("should be equal to same instance") {
                    val node = VariableBitwiseXorAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
                    node shouldBe node
                }
                it("should be equal to another VariableBitwiseXorAssignmentSpec with the same name and value") {
                    VariableBitwiseXorAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldBe VariableBitwiseXorAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test"))
                }
                it("should not be equal to another VariableBitwiseXorAssignmentSpec with a different name") {
                    VariableBitwiseXorAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe VariableBitwiseXorAssignmentSpec(NamespaceSpec("variable2"), ValueSpec.of("test"))
                }
                it("should not be equal to another VariableBitwiseXorAssignmentSpec with a different value") {
                    VariableBitwiseXorAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe VariableBitwiseXorAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test2"))
                }
                it("should not be equal to another type") {
                    VariableBitwiseXorAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe IntLiteralSpec(42)
                }
                it("should not be equal to null") {
                    VariableBitwiseXorAssignmentSpec(NamespaceSpec("variable"), ValueSpec.of("test")) shouldNotBe null
                }
            }

            describe("builder()") {
                it("should create a builder") {
                    val builder = VariableBitwiseXorAssignmentSpec.builder()
                    builder shouldNotBe null
                    builder.name shouldBe null
                    builder.value shouldBe null
                }

                abstractAssignmentSpecBuilderTest(VariableBitwiseXorAssignmentSpec::builder)

                describe("builder.build()") {
                    it("should build the VariableBitwiseXorAssignmentSpec") {
                        val name = NamespaceSpec("variable")
                        val value = ValueSpec.of("test")
                        val node = VariableBitwiseXorAssignmentSpec.builder()
                            .name(name)
                            .value(value)
                            .build()
                        node.name shouldBe name
                        node.value shouldBe value
                    }

                    it("should throw an exception if the name is not set") {
                        val builder = VariableBitwiseXorAssignmentSpec.builder()
                        builder.value(ValueSpec.of("test"))
                        shouldThrowWithMessage<IllegalStateException>("Name not set") {
                            builder.build()
                        }
                    }

                    it("should throw an exception if the value is not set") {
                        val builder = VariableBitwiseXorAssignmentSpec.builder()
                        builder.name(NamespaceSpec("variable"))
                        shouldThrowWithMessage<IllegalStateException>("Value not set") {
                            builder.build()
                        }
                    }
                }
            }
        }

        describe("VariableIncrementBeforeSpec") {
            describe("create") {
                it("should create from namespace") {
                    val name = NamespaceSpec("variable")
                    val node = VariableIncrementBeforeSpec(name)
                    node.name shouldBe name
                }
            }

            describe("generate()") {
                it("should generate the increment") {
                    val name = NamespaceSpec("variable")
                    val node = VariableIncrementBeforeSpec(name)
                    val ctx = GenerationContext()
                    node.generate(ctx) shouldBe "++variable"
                }
            }

            describe("equals()") {
                it("should be equal to same instance") {
                    val node = VariableIncrementBeforeSpec(NamespaceSpec("variable"))
                    node shouldBe node
                }
                it("should be equal to another VariableIncrementBeforeSpec with the same name") {
                    VariableIncrementBeforeSpec(NamespaceSpec("variable")) shouldBe VariableIncrementBeforeSpec(NamespaceSpec("variable"))
                }
                it("should not be equal to another VariableIncrementBeforeSpec with a different name") {
                    VariableIncrementBeforeSpec(NamespaceSpec("variable")) shouldNotBe VariableIncrementBeforeSpec(NamespaceSpec("variable2"))
                }
                it("should not be equal to another type") {
                    VariableIncrementBeforeSpec(NamespaceSpec("variable")) shouldNotBe IntLiteralSpec(42)
                }
                it("should not be equal to null") {
                    VariableIncrementBeforeSpec(NamespaceSpec("variable")) shouldNotBe null
                }
            }

            describe("builder()") {
                it("should create a builder") {
                    val builder = VariableIncrementBeforeSpec.builder()
                    builder shouldNotBe null
                    builder.name shouldBe null
                }

                abstractModificationSpecBuilderTest(VariableIncrementBeforeSpec::builder)

                describe("builder.build()") {
                    it("should build the VariableIncrementBeforeSpec") {
                        val name = NamespaceSpec("variable")
                        val node = VariableIncrementBeforeSpec.builder()
                            .name(name)
                            .build()
                        node.name shouldBe name
                    }

                    it("should throw an exception if the name is not set") {
                        val builder = VariableIncrementBeforeSpec.builder()
                        shouldThrowWithMessage<IllegalStateException>("Name not set") {
                            builder.build()
                        }
                    }
                }
            }
        }

        describe("VariableIncrementAfterSpec") {
            describe("create") {
                it("should create from namespace") {
                    val name = NamespaceSpec("variable")
                    val node = VariableIncrementAfterSpec(name)
                    node.name shouldBe name
                }
            }

            describe("generate()") {
                it("should generate the increment") {
                    val name = NamespaceSpec("variable")
                    val node = VariableIncrementAfterSpec(name)
                    val ctx = GenerationContext()
                    node.generate(ctx) shouldBe "variable++"
                }
            }

            describe("equals()") {
                it("should be equal to same instance") {
                    val node = VariableIncrementAfterSpec(NamespaceSpec("variable"))
                    node shouldBe node
                }
                it("should be equal to another VariableIncrementAfterSpec with the same name") {
                    VariableIncrementAfterSpec(NamespaceSpec("variable")) shouldBe VariableIncrementAfterSpec(NamespaceSpec("variable"))
                }
                it("should not be equal to another VariableIncrementAfterSpec with a different name") {
                    VariableIncrementAfterSpec(NamespaceSpec("variable")) shouldNotBe VariableIncrementAfterSpec(NamespaceSpec("variable2"))
                }
                it("should not be equal to another type") {
                    VariableIncrementAfterSpec(NamespaceSpec("variable")) shouldNotBe IntLiteralSpec(42)
                }
                it("should not be equal to null") {
                    VariableIncrementAfterSpec(NamespaceSpec("variable")) shouldNotBe null
                }
            }

            describe("builder()") {
                it("should create a builder") {
                    val builder = VariableIncrementAfterSpec.builder()
                    builder shouldNotBe null
                    builder.name shouldBe null
                }

                abstractModificationSpecBuilderTest(VariableIncrementAfterSpec::builder)

                describe("builder.build()") {
                    it("should build the VariableIncrementAfterSpec") {
                        val name = NamespaceSpec("variable")
                        val node = VariableIncrementAfterSpec.builder()
                            .name(name)
                            .build()
                        node.name shouldBe name
                    }

                    it("should throw an exception if the name is not set") {
                        val builder = VariableIncrementAfterSpec.builder()
                        shouldThrowWithMessage<IllegalStateException>("Name not set") {
                            builder.build()
                        }
                    }
                }
            }
        }

        describe("VariableDecrementBeforeSpec") {
            describe("create") {
                it("should create from namespace") {
                    val name = NamespaceSpec("variable")
                    val node = VariableDecrementBeforeSpec(name)
                    node.name shouldBe name
                }
            }

            describe("generate()") {
                it("should generate the decrement") {
                    val name = NamespaceSpec("variable")
                    val node = VariableDecrementBeforeSpec(name)
                    val ctx = GenerationContext()
                    node.generate(ctx) shouldBe "--variable"
                }
            }

            describe("equals()") {
                it("should be equal to same instance") {
                    val node = VariableDecrementBeforeSpec(NamespaceSpec("variable"))
                    node shouldBe node
                }
                it("should be equal to another VariableDecrementBeforeSpec with the same name") {
                    VariableDecrementBeforeSpec(NamespaceSpec("variable")) shouldBe VariableDecrementBeforeSpec(NamespaceSpec("variable"))
                }
                it("should not be equal to another VariableDecrementBeforeSpec with a different name") {
                    VariableDecrementBeforeSpec(NamespaceSpec("variable")) shouldNotBe VariableDecrementBeforeSpec(NamespaceSpec("variable2"))
                }
                it("should not be equal to another type") {
                    VariableDecrementBeforeSpec(NamespaceSpec("variable")) shouldNotBe IntLiteralSpec(42)
                }
                it("should not be equal to null") {
                    VariableDecrementBeforeSpec(NamespaceSpec("variable")) shouldNotBe null
                }
            }

            describe("builder()") {
                it("should create a builder") {
                    val builder = VariableDecrementBeforeSpec.builder()
                    builder shouldNotBe null
                    builder.name shouldBe null
                }

                abstractModificationSpecBuilderTest(VariableDecrementBeforeSpec::builder)

                describe("builder.build()") {
                    it("should build the VariableDecrementBeforeSpec") {
                        val name = NamespaceSpec("variable")
                        val node = VariableDecrementBeforeSpec.builder()
                            .name(name)
                            .build()
                        node.name shouldBe name
                    }

                    it("should throw an exception if the name is not set") {
                        val builder = VariableDecrementBeforeSpec.builder()
                        shouldThrowWithMessage<IllegalStateException>("Name not set") {
                            builder.build()
                        }
                    }
                }
            }
        }

        describe("VariableDecrementAfterSpec") {
            describe("create") {
                it("should create from namespace") {
                    val name = NamespaceSpec("variable")
                    val node = VariableDecrementAfterSpec(name)
                    node.name shouldBe name
                }
            }

            describe("generate()") {
                it("should generate the decrement") {
                    val name = NamespaceSpec("variable")
                    val node = VariableDecrementAfterSpec(name)
                    val ctx = GenerationContext()
                    node.generate(ctx) shouldBe "variable--"
                }
            }

            describe("equals()") {
                it("should be equal to same instance") {
                    val node = VariableDecrementAfterSpec(NamespaceSpec("variable"))
                    node shouldBe node
                }
                it("should be equal to another VariableDecrementAfterSpec with the same name") {
                    VariableDecrementAfterSpec(NamespaceSpec("variable")) shouldBe VariableDecrementAfterSpec(NamespaceSpec("variable"))
                }
                it("should not be equal to another VariableDecrementAfterSpec with a different name") {
                    VariableDecrementAfterSpec(NamespaceSpec("variable")) shouldNotBe VariableDecrementAfterSpec(NamespaceSpec("variable2"))
                }
                it("should not be equal to another type") {
                    VariableDecrementAfterSpec(NamespaceSpec("variable")) shouldNotBe IntLiteralSpec(42)
                }
                it("should not be equal to null") {
                    VariableDecrementAfterSpec(NamespaceSpec("variable")) shouldNotBe null
                }
            }

            describe("builder()") {
                it("should create a builder") {
                    val builder = VariableDecrementAfterSpec.builder()
                    builder shouldNotBe null
                    builder.name shouldBe null
                }

                abstractModificationSpecBuilderTest(VariableDecrementAfterSpec::builder)

                describe("builder.build()") {
                    it("should build the VariableDecrementAfterSpec") {
                        val name = NamespaceSpec("variable")
                        val node = VariableDecrementAfterSpec.builder()
                            .name(name)
                            .build()
                        node.name shouldBe name
                    }

                    it("should throw an exception if the name is not set") {
                        val builder = VariableDecrementAfterSpec.builder()
                        shouldThrowWithMessage<IllegalStateException>("Name not set") {
                            builder.build()
                        }
                    }
                }
            }
        }

        describe("FunctionCallSpec") {
            describe("create") {
                it("should create from name") {
                    val name = NamespaceSpec("function")
                    val arguments = emptyList<ValueSpec>()
                    val node = FunctionCallSpec(name, arguments)
                    node.name shouldBe name
                    node.arguments shouldBe emptyList()
                }
            }

            describe("generate()") {
                it("should generate the function call") {
                    val name = NamespaceSpec("function")
                    val arguments = listOf(ValueSpec.of("test"))
                    val node = FunctionCallSpec(name, arguments)
                    val ctx = GenerationContext()
                    node.generate(ctx) shouldBe "function(test)"
                }
            }

            describe("equals()") {
                it("should be equal to same instance") {
                    val node = FunctionCallSpec(NamespaceSpec("function"), emptyList())
                    node shouldBe node
                }
                it("should be equal to another FunctionCallSpec with the same name and arguments") {
                    FunctionCallSpec(NamespaceSpec("function"), emptyList()) shouldBe FunctionCallSpec(NamespaceSpec("function"), emptyList())
                }
                it("should not be equal to another FunctionCallSpec with a different name") {
                    FunctionCallSpec(NamespaceSpec("function"), emptyList()) shouldNotBe FunctionCallSpec(NamespaceSpec("function2"), emptyList())
                }
                it("should not be equal to another FunctionCallSpec with different arguments") {
                    FunctionCallSpec(NamespaceSpec("function"), emptyList()) shouldNotBe FunctionCallSpec(NamespaceSpec("function"), listOf(ValueSpec.of("test")))
                }
                it("should not be equal to another type") {
                    FunctionCallSpec(NamespaceSpec("function"), emptyList()) shouldNotBe IntLiteralSpec(42)
                }
                it("should not be equal to null") {
                    FunctionCallSpec(NamespaceSpec("function"), emptyList()) shouldNotBe null
                }
            }

            describe("builder()") {
                it("should create a builder") {
                    val builder = FunctionCallSpec.builder()
                    builder shouldNotBe null
                    builder.name shouldBe null
                    builder.arguments shouldBe emptyList()
                }

                describe("builder.name()") {
                    it("should set the name") {
                        val name = NamespaceSpec("function")
                        val builder = FunctionCallSpec.builder()
                        builder.name(name)
                        builder.name shouldBe name
                    }
                }

                describe("builder.arguments()") {
                    it("should set the arguments") {
                        val arguments = listOf(ValueSpec.of("test"))
                        val builder = FunctionCallSpec.builder()
                        builder.arguments(arguments)
                        builder.arguments shouldBe arguments
                    }

                    it("should append if called multiple times") {
                        val arguments = listOf(ValueSpec.of("test"))
                        val builder = FunctionCallSpec.builder()
                        builder.arguments(arguments)
                        builder.arguments(listOf(ValueSpec.of("test2")))
                        builder.arguments shouldBe listOf(ValueSpec.of("test"), ValueSpec.of("test2"))
                    }
                }

                describe("builder.argument()") {
                    it("should add an argument (ValueSpec)") {
                        val argument = ValueSpec.of("test")
                        val builder = FunctionCallSpec.builder()
                        builder.argument(argument)
                        builder.arguments shouldBe listOf(argument)
                    }

                    it("should add an argument (String)") {
                        val argument = "test"
                        val builder = FunctionCallSpec.builder()
                        builder.argument(argument)
                        builder.arguments shouldBe listOf(ValueSpec.of("test"))
                    }

                    it("should append if called multiple times") {
                        val argument = ValueSpec.of("test")
                        val builder = FunctionCallSpec.builder()
                        builder.argument(argument)
                        builder.argument(ValueSpec.of("test2"))
                        builder.arguments shouldBe listOf(ValueSpec.of("test"), ValueSpec.of("test2"))
                    }
                }

                describe("builder.build()") {
                    it("should build the FunctionCallSpec") {
                        val name = NamespaceSpec("function")
                        val arguments = listOf(ValueSpec.of("test"))
                        val node = FunctionCallSpec.builder()
                            .name(name)
                            .arguments(arguments)
                            .build()
                        node.name shouldBe name
                        node.arguments shouldBe arguments
                    }

                    it("should throw an exception if the name is not set") {
                        val builder = FunctionCallSpec.builder()
                        builder.arguments(emptyList())
                        shouldThrowWithMessage<IllegalStateException>("Name not set") {
                            builder.build()
                        }
                    }

                    it("should work if the arguments are not set") {
                        val name = NamespaceSpec("function")
                        val node = FunctionCallSpec.builder()
                            .name(name)
                            .build()
                        node.name shouldBe name
                        node.arguments shouldBe emptyList()
                    }
                }
            }
        }
    },
)
