package com.shakelang.shake.shakespeare.spec.code

import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.TypeSpec
import com.shakelang.util.testlib.FlatTestSpec
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class StatementTests : FlatTestSpec({

    describe("StatementSpec.of()") {

        describe("generate()") {
            it("should return the statement") {
                val ctx = GenerationContext()
                StatementSpec.of("statement").generate(ctx) shouldBe "statement"
            }
        }

        describe("equals()") {
            it("should return true if same instance") {
                val spec = StatementSpec.of("statement")
                spec shouldBe spec
            }
            it("should return true if the statements are the same") {
                StatementSpec.of("statement") shouldBe StatementSpec.of("statement")
            }
            it("should return false if the statements are different") {
                StatementSpec.of("statement") shouldNotBe StatementSpec.of("statement2")
            }
            it("should return false if the other object is not a StatementSpec") {
                StatementSpec.of("statement") shouldNotBe "statement"
            }
            it("should return false if other object is null") {
                StatementSpec.of("statement") shouldNotBe null
            }
        }
    }

    describe("VariableDeclarationSpec") {
        describe("create()") {
            it("should create object") {
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
        }

        describe("generate()") {
            it("should generate (var, no value, type)") {
                val spec = VariableDeclarationSpec(
                    "name",
                    TypeSpec.of("String"),
                    null,
                    false,
                )

                val ctx = GenerationContext()
                spec.generate(ctx) shouldBe "var name: String"
            }

            it("should generate (var, value, type)") {
                val spec = VariableDeclarationSpec(
                    "name",
                    TypeSpec.of("String"),
                    ValueSpec.of("value"),
                    false,
                )

                val ctx = GenerationContext()
                spec.generate(ctx) shouldBe "var name: String = value"
            }

            it("should generate (val, no value, type)") {
                val spec = VariableDeclarationSpec(
                    "name",
                    TypeSpec.of("String"),
                    null,
                    true,
                )

                val ctx = GenerationContext()
                spec.generate(ctx) shouldBe "val name: String"
            }

            it("should generate (val, value, type)") {
                val spec = VariableDeclarationSpec(
                    "name",
                    TypeSpec.of("String"),
                    ValueSpec.of("value"),
                    true,
                )

                val ctx = GenerationContext()
                spec.generate(ctx) shouldBe "val name: String = value"
            }

            it("should generate (var, no value, no type)") {
                val spec = VariableDeclarationSpec(
                    "name",
                    null,
                    null,
                    false,
                )

                val ctx = GenerationContext()
                spec.generate(ctx) shouldBe "var name"
            }

            it("should generate (var, value, no type)") {
                val spec = VariableDeclarationSpec(
                    "name",
                    null,
                    ValueSpec.of("value"),
                    false,
                )

                val ctx = GenerationContext()
                spec.generate(ctx) shouldBe "var name = value"
            }

            it("should generate (val, no value, no type)") {
                val spec = VariableDeclarationSpec(
                    "name",
                    null,
                    null,
                    true,
                )

                val ctx = GenerationContext()
                spec.generate(ctx) shouldBe "val name"
            }

            it("should generate (val, value, no type)") {
                val spec = VariableDeclarationSpec(
                    "name",
                    null,
                    ValueSpec.of("value"),
                    true,
                )

                val ctx = GenerationContext()
                spec.generate(ctx) shouldBe "val name = value"
            }
        }

        describe("equals()") {

            it("should return true if same instance") {
                val spec = VariableDeclarationSpec(
                    "name",
                    TypeSpec.of("String"),
                    ValueSpec.of("value"),
                    false,
                )
                spec shouldBe spec
            }

            it("should return true if the statements are the same") {
                VariableDeclarationSpec(
                    "name",
                    TypeSpec.of("String"),
                    ValueSpec.of("value"),
                    false,
                ) shouldBe VariableDeclarationSpec(
                    "name",
                    TypeSpec.of("String"),
                    ValueSpec.of("value"),
                    false,
                )
            }

            it("should return false if the statements are the same (val)") {
                VariableDeclarationSpec(
                    "name",
                    TypeSpec.of("String"),
                    ValueSpec.of("value"),
                    true,
                ) shouldNotBe VariableDeclarationSpec(
                    "name",
                    TypeSpec.of("String"),
                    ValueSpec.of("value"),
                    false,
                )
            }

            it("should return false if the names are different") {
                VariableDeclarationSpec(
                    "name",
                    TypeSpec.of("String"),
                    ValueSpec.of("value"),
                    false,
                ) shouldNotBe VariableDeclarationSpec(
                    "name2",
                    TypeSpec.of("String"),
                    ValueSpec.of("value"),
                    false,
                )
            }

            it("should return false if the types are different") {
                VariableDeclarationSpec(
                    "name",
                    TypeSpec.of("String"),
                    ValueSpec.of("value"),
                    false,
                ) shouldNotBe VariableDeclarationSpec(
                    "name",
                    TypeSpec.of("String2"),
                    ValueSpec.of("value"),
                    false,
                )
            }

            it("should return false if the values are different") {
                VariableDeclarationSpec(
                    "name",
                    TypeSpec.of("String"),
                    ValueSpec.of("value"),
                    false,
                ) shouldNotBe VariableDeclarationSpec(
                    "name",
                    TypeSpec.of("String"),
                    ValueSpec.of("value2"),
                    false,
                )
            }

            it("should return false if the other object is not a VariableDeclarationSpec") {
                VariableDeclarationSpec(
                    "name",
                    TypeSpec.of("String"),
                    ValueSpec.of("value"),
                    false,
                ) shouldNotBe "name"
            }

            it("should return false if the other object is null") {
                VariableDeclarationSpec(
                    "name",
                    TypeSpec.of("String"),
                    ValueSpec.of("value"),
                    false,
                ) shouldNotBe null
            }
        }

        describe("builder()") {

            it("should create builder") {
                val builder = VariableDeclarationSpec.builder()
                builder shouldNotBe null
                builder.type shouldBe null
                builder.value shouldBe null
                builder.name shouldBe null
                builder.isVal shouldBe false
            }

            describe("builder.type()") {
                it("should set type (String)") {
                    val builder = VariableDeclarationSpec.builder()
                    builder.type("String") shouldBe builder
                    builder.type shouldBe TypeSpec.of("String")
                }

                it("should set type (TypeSpec)") {
                    val builder = VariableDeclarationSpec.builder()
                    builder.type(TypeSpec.of("String")) shouldBe builder
                    builder.type shouldBe TypeSpec.of("String")
                }
            }

            describe("builder.value()") {
                it("should set value (String)") {
                    val builder = VariableDeclarationSpec.builder()
                    builder.value("value") shouldBe builder
                    builder.value shouldBe ValueSpec.of("value")
                }

                it("should set value (ValueSpec)") {
                    val builder = VariableDeclarationSpec.builder()
                    builder.value(ValueSpec.of("value")) shouldBe builder
                    builder.value shouldBe ValueSpec.of("value")
                }
            }

            describe("builder.name()") {
                it("should set name") {
                    val builder = VariableDeclarationSpec.builder()
                    builder.name("name") shouldBe builder
                    builder.name shouldBe "name"
                }
            }

            describe("builder.isVal()") {
                it("should make it a val (with no argument)") {
                    val builder = VariableDeclarationSpec.builder()
                    builder.isVal() shouldBe builder
                    builder.isVal shouldBe true
                }

                it("should make builder a val (with true argument)") {
                    val builder = VariableDeclarationSpec.builder()
                    builder.isVal(true) shouldBe builder
                    builder.isVal shouldBe true
                }

                it("should make builder a var (with false argument)") {
                    val builder = VariableDeclarationSpec.builder()
                    builder.isVal(false) shouldBe builder
                    builder.isVal shouldBe false
                }
            }

            describe("builder.build()") {
                it("should build") {
                    val builder = VariableDeclarationSpec.builder()
                    builder.type("String")
                    builder.value("value")
                    builder.name("name")
                    builder.isVal(true)
                    val spec = builder.build()

                    spec.type shouldBe TypeSpec.of("String")
                    spec.value shouldBe ValueSpec.of("value")
                    spec.name shouldBe "name"
                    spec.isVal shouldBe true
                }

                it("should throw exception if no name") {
                    val builder = VariableDeclarationSpec.builder()
                    builder.type("String")
                    builder.value("value")
                    builder.isVal(true)

                    shouldThrowWithMessage<IllegalStateException>("Name not set") {
                        builder.build()
                    }
                }

                it("should throw exception if no type") {
                    val builder = VariableDeclarationSpec.builder()
                    builder.value("value")
                    builder.name("name")
                    builder.isVal(true)

                    shouldThrowWithMessage<IllegalStateException>("Type not set") {
                        builder.build()
                    }
                }

                it("should throw exception if no value") {
                    val builder = VariableDeclarationSpec.builder()
                    builder.type("String")
                    builder.name("name")
                    builder.isVal(true)

                    shouldThrowWithMessage<IllegalStateException>("Value not set") {
                        builder.build()
                    }
                }
            }
        }
    }

    describe("WhileSpec") {
        describe("create()") {
            it("should create") {
                val spec = WhileSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.empty(),
                )

                spec.condition shouldBe ValueSpec.of("condition")
                spec.body shouldBe CodeSpec.empty()
            }
        }

        describe("generate()") {
            it("should generate") {
                val spec = WhileSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.empty(),
                )

                val ctx = GenerationContext()
                spec.generate(ctx) shouldBe "while (condition) {}"
            }
        }

        describe("equals()") {
            it("should return true if same instance") {
                val spec = WhileSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.empty(),
                )
                spec shouldBe spec
            }

            it("should return true if the statements are the same") {
                WhileSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.empty(),
                ) shouldBe WhileSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.empty(),
                )
            }

            it("should return false if conditions are different") {
                WhileSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.empty(),
                ) shouldNotBe WhileSpec(
                    ValueSpec.of("condition2"),
                    CodeSpec.empty(),
                )
            }

            it("should return false if the bodies are different") {
                WhileSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.empty(),
                ) shouldNotBe WhileSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.builder().addStatement(StatementSpec.of("statement")).build(),
                )
            }

            it("should return false if the other object is not a WhileSpec") {
                WhileSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.empty(),
                ) shouldNotBe "condition"
            }

            it("should return false if the other object is null") {
                WhileSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.empty(),
                ) shouldNotBe null
            }
        }

        describe("builder()") {

            it("should create builder") {
                val builder = WhileSpec.builder()
                builder shouldNotBe null
                builder.condition shouldBe null
                builder.body shouldBe null
            }

            describe("builder.condition()") {
                it("should set condition (String)") {
                    val builder = WhileSpec.builder()
                    builder.condition("condition") shouldBe builder
                    builder.condition shouldBe ValueSpec.of("condition")
                }

                it("should set condition (ValueSpec)") {
                    val builder = WhileSpec.builder()
                    builder.condition(ValueSpec.of("condition")) shouldBe builder
                    builder.condition shouldBe ValueSpec.of("condition")
                }
            }

            describe("builder.body()") {
                it("should set body") {
                    val builder = WhileSpec.builder()
                    builder.body(CodeSpec.empty()) shouldBe builder
                    builder.body shouldBe CodeSpec.empty()
                }
            }

            describe("builder.build()") {
                it("should build") {
                    val builder = WhileSpec.builder()
                    builder.condition("condition")
                    builder.body(CodeSpec.empty())
                    val spec = builder.build()

                    spec.condition shouldBe ValueSpec.of("condition")
                    spec.body shouldBe CodeSpec.empty()
                }

                it("should throw exception if no condition") {
                    val builder = WhileSpec.builder()
                    builder.body(CodeSpec.empty())

                    shouldThrowWithMessage<IllegalStateException>("Condition not set") {
                        builder.build()
                    }
                }

                it("should throw exception if no body") {
                    val builder = WhileSpec.builder()
                    builder.condition("condition")

                    shouldThrowWithMessage<IllegalStateException>("Body not set") {
                        builder.build()
                    }
                }
            }
        }
    }

    describe("DoWhileSpec") {

        describe("create()") {
            it("should create") {
                val spec = DoWhileSpec(
                    CodeSpec.empty(),
                    ValueSpec.of("condition"),
                )

                spec.body shouldBe CodeSpec.empty()
                spec.condition shouldBe ValueSpec.of("condition")
            }
        }

        describe("generate()") {
            it("should generate") {
                val spec = DoWhileSpec(
                    CodeSpec.empty(),
                    ValueSpec.of("condition"),
                )

                val ctx = GenerationContext()
                spec.generate(ctx) shouldBe "do {} while (condition)"
            }
        }

        describe("equals()") {
            it("should return true if same instance") {
                val spec = DoWhileSpec(
                    CodeSpec.empty(),
                    ValueSpec.of("condition"),
                )
                spec shouldBe spec
            }

            it("should return true if the statements are the same") {
                DoWhileSpec(
                    CodeSpec.empty(),
                    ValueSpec.of("condition"),
                ) shouldBe DoWhileSpec(
                    CodeSpec.empty(),
                    ValueSpec.of("condition"),
                )
            }

            it("should return false if the conditions are different") {
                DoWhileSpec(
                    CodeSpec.empty(),
                    ValueSpec.of("condition"),
                ) shouldNotBe DoWhileSpec(
                    CodeSpec.empty(),
                    ValueSpec.of("condition2"),
                )
            }

            it("should return false if the bodies are different") {
                DoWhileSpec(
                    CodeSpec.empty(),
                    ValueSpec.of("condition"),
                ) shouldNotBe DoWhileSpec(
                    CodeSpec.builder().addStatement(StatementSpec.of("statement")).build(),
                    ValueSpec.of("condition"),
                )
            }

            it("should return false if the other object is not a DoWhileSpec") {
                DoWhileSpec(
                    CodeSpec.empty(),
                    ValueSpec.of("condition"),
                ) shouldNotBe "condition"
            }

            it("should return false if the other object is null") {
                DoWhileSpec(
                    CodeSpec.empty(),
                    ValueSpec.of("condition"),
                ) shouldNotBe null
            }
        }

        describe("builder()") {
            it("should create builder") {
                val builder = DoWhileSpec.builder()
                builder shouldNotBe null
                builder.condition shouldBe null
                builder.body shouldBe null
            }

            describe("builder.condition()") {
                it("should set condition (String)") {
                    val builder = DoWhileSpec.builder()
                    builder.condition("condition") shouldBe builder
                    builder.condition shouldBe ValueSpec.of("condition")
                }

                it("should set condition (ValueSpec)") {
                    val builder = DoWhileSpec.builder()
                    builder.condition(ValueSpec.of("condition")) shouldBe builder
                    builder.condition shouldBe ValueSpec.of("condition")
                }
            }

            describe("builder.body()") {
                it("should set body") {
                    val builder = DoWhileSpec.builder()
                    builder.body(CodeSpec.empty()) shouldBe builder
                    builder.body shouldBe CodeSpec.empty()
                }
            }

            describe("builder.build()") {
                it("should build") {
                    val builder = DoWhileSpec.builder()
                    builder.condition("condition")
                    builder.body(CodeSpec.empty())
                    val spec = builder.build()

                    spec.condition shouldBe ValueSpec.of("condition")
                    spec.body shouldBe CodeSpec.empty()
                }

                it("should throw exception if no condition") {
                    val builder = DoWhileSpec.builder()
                    builder.body(CodeSpec.empty())

                    shouldThrowWithMessage<IllegalStateException>("Condition not set") {
                        builder.build()
                    }
                }

                it("should throw exception if no body") {
                    val builder = DoWhileSpec.builder()
                    builder.condition("condition")

                    shouldThrowWithMessage<IllegalStateException>("Body not set") {
                        builder.build()
                    }
                }
            }
        }
    }
    describe("ForSpec") {
        describe("create()") {
            it("should create") {
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
        }

        describe("generate()") {
            it("should generate") {
                val spec = ForSpec(
                    StatementSpec.of("init"),
                    ValueSpec.of("condition"),
                    StatementSpec.of("update"),
                    CodeSpec.empty(),
                )

                val ctx = GenerationContext()
                spec.generate(ctx) shouldBe "for (init; condition; update) {}"
            }
        }

        describe("equals()") {
            it("should return true if same instance") {
                val spec = ForSpec(
                    StatementSpec.of("init"),
                    ValueSpec.of("condition"),
                    StatementSpec.of("update"),
                    CodeSpec.empty(),
                )
                spec shouldBe spec
            }

            it("should return true if the statements are the same") {
                ForSpec(
                    StatementSpec.of("init"),
                    ValueSpec.of("condition"),
                    StatementSpec.of("update"),
                    CodeSpec.empty(),
                ) shouldBe ForSpec(
                    StatementSpec.of("init"),
                    ValueSpec.of("condition"),
                    StatementSpec.of("update"),
                    CodeSpec.empty(),
                )
            }

            it("should return false if the inits are different") {
                ForSpec(
                    StatementSpec.of("init"),
                    ValueSpec.of("condition"),
                    StatementSpec.of("update"),
                    CodeSpec.empty(),
                ) shouldNotBe ForSpec(
                    StatementSpec.of("init2"),
                    ValueSpec.of("condition"),
                    StatementSpec.of("update"),
                    CodeSpec.empty(),
                )
            }

            it("should return false if the conditions are different") {
                ForSpec(
                    StatementSpec.of("init"),
                    ValueSpec.of("condition"),
                    StatementSpec.of("update"),
                    CodeSpec.empty(),
                ) shouldNotBe ForSpec(
                    StatementSpec.of("init"),
                    ValueSpec.of("condition2"),
                    StatementSpec.of("update"),
                    CodeSpec.empty(),
                )
            }

            it("should return false if the updates are different") {
                ForSpec(
                    StatementSpec.of("init"),
                    ValueSpec.of("condition"),
                    StatementSpec.of("update"),
                    CodeSpec.empty(),
                ) shouldNotBe ForSpec(
                    StatementSpec.of("init"),
                    ValueSpec.of("condition"),
                    StatementSpec.of("update2"),
                    CodeSpec.empty(),
                )
            }

            it("should return false if the bodies are different") {
                ForSpec(
                    StatementSpec.of("init"),
                    ValueSpec.of("condition"),
                    StatementSpec.of("update"),
                    CodeSpec.empty(),
                ) shouldNotBe ForSpec(
                    StatementSpec.of("init"),
                    ValueSpec.of("condition"),
                    StatementSpec.of("update"),
                    CodeSpec.builder().addStatement(StatementSpec.of("statement")).build(),
                )
            }

            it("should return false if the other object is not a ForSpec") {
                ForSpec(
                    StatementSpec.of("init"),
                    ValueSpec.of("condition"),
                    StatementSpec.of("update"),
                    CodeSpec.empty(),
                ) shouldNotBe "condition"
            }

            it("should return false if the other object is null") {
                ForSpec(
                    StatementSpec.of("init"),
                    ValueSpec.of("condition"),
                    StatementSpec.of("update"),
                    CodeSpec.empty(),
                ) shouldNotBe null
            }
        }

        describe("builder()") {
            it("should create builder") {
                val builder = ForSpec.builder()
                builder shouldNotBe null
                builder.init shouldBe null
                builder.condition shouldBe null
                builder.update shouldBe null
                builder.body shouldBe null
            }

            describe("builder.init()") {
                it("should set init (String)") {
                    val builder = ForSpec.builder()
                    builder.init("init") shouldBe builder
                    builder.init shouldBe StatementSpec.of("init")
                }

                it("should set init (StatementSpec)") {
                    val builder = ForSpec.builder()
                    builder.init(StatementSpec.of("init")) shouldBe builder
                    builder.init shouldBe StatementSpec.of("init")
                }
            }

            describe("builder.condition()") {
                it("should set condition (String)") {
                    val builder = ForSpec.builder()
                    builder.condition("condition") shouldBe builder
                    builder.condition shouldBe ValueSpec.of("condition")
                }

                it("should set condition (ValueSpec)") {
                    val builder = ForSpec.builder()
                    builder.condition(ValueSpec.of("condition")) shouldBe builder
                    builder.condition shouldBe ValueSpec.of("condition")
                }
            }

            describe("builder.update()") {
                it("should set update (String)") {
                    val builder = ForSpec.builder()
                    builder.update("update") shouldBe builder
                    builder.update shouldBe StatementSpec.of("update")
                }

                it("should set update (StatementSpec)") {
                    val builder = ForSpec.builder()
                    builder.update(StatementSpec.of("update")) shouldBe builder
                    builder.update shouldBe StatementSpec.of("update")
                }
            }

            describe("builder.body()") {
                it("should set body") {
                    val builder = ForSpec.builder()
                    builder.body(CodeSpec.empty()) shouldBe builder
                    builder.body shouldBe CodeSpec.empty()
                }
            }

            describe("builder.build()") {
                it("should build") {
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

                it("should throw exception if no init") {
                    val builder = ForSpec.builder()
                    builder.condition("condition")
                    builder.update("update")
                    builder.body(CodeSpec.empty())

                    shouldThrowWithMessage<IllegalStateException>("Init not set") {
                        builder.build()
                    }
                }

                it("should throw exception if no condition") {
                    val builder = ForSpec.builder()
                    builder.init("init")
                    builder.update("update")
                    builder.body(CodeSpec.empty())

                    shouldThrowWithMessage<IllegalStateException>("Condition not set") {
                        builder.build()
                    }
                }

                it("should throw exception if no update") {
                    val builder = ForSpec.builder()
                    builder.init("init")
                    builder.condition("condition")
                    builder.body(CodeSpec.empty())

                    shouldThrowWithMessage<IllegalStateException>("Update not set") {
                        builder.build()
                    }
                }

                it("should throw exception if no body") {
                    val builder = ForSpec.builder()
                    builder.init("init")
                    builder.condition("condition")
                    builder.update("update")

                    shouldThrowWithMessage<IllegalStateException>("Body not set") {
                        builder.build()
                    }
                }
            }
        }
    }

    describe("IfSpec") {
        describe("create()") {
            it("should create (with else)") {
                val spec = IfSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.empty(),
                    CodeSpec.empty(),
                )

                spec.condition shouldBe ValueSpec.of("condition")
                spec.thenBody shouldBe CodeSpec.empty()
                spec.elseBody shouldBe CodeSpec.empty()
            }

            it("should create (no else)") {
                val spec = IfSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.empty(),
                    null,
                )

                spec.condition shouldBe ValueSpec.of("condition")
                spec.thenBody shouldBe CodeSpec.empty()
                spec.elseBody shouldBe null
            }
        }

        describe("generate()") {
            it("should generate") {
                val spec = IfSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.empty(),
                    CodeSpec.empty(),
                )

                val ctx = GenerationContext()
                spec.generate(ctx) shouldBe "if (condition) {} else {}"
            }

            it("should generate (no else)") {
                val spec = IfSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.empty(),
                    null,
                )

                val ctx = GenerationContext()
                spec.generate(ctx) shouldBe "if (condition) {}"
            }
        }

        describe("equals()") {
            it("should return true if same instance") {
                val spec = IfSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.empty(),
                    CodeSpec.empty(),
                )
                spec shouldBe spec
            }

            it("should return true if the statements are the same") {
                IfSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.empty(),
                    CodeSpec.empty(),
                ) shouldBe IfSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.empty(),
                    CodeSpec.empty(),
                )
            }

            it("should return false if the conditions are different") {
                IfSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.empty(),
                    CodeSpec.empty(),
                ) shouldNotBe IfSpec(
                    ValueSpec.of("condition2"),
                    CodeSpec.empty(),
                    CodeSpec.empty(),
                )
            }

            it("should return false if the bodies are different") {
                IfSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.empty(),
                    CodeSpec.empty(),
                ) shouldNotBe IfSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.builder().addStatement(StatementSpec.of("statement")).build(),
                    CodeSpec.empty(),
                )
            }

            it("should return false if the else bodies are different") {
                IfSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.empty(),
                    CodeSpec.empty(),
                ) shouldNotBe IfSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.empty(),
                    CodeSpec.builder().addStatement(StatementSpec.of("statement")).build(),
                )
            }

            it("should return false if the other object is not a IfSpec") {
                IfSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.empty(),
                    CodeSpec.empty(),
                ) shouldNotBe "condition"
            }

            it("should return false if the other object is null") {
                IfSpec(
                    ValueSpec.of("condition"),
                    CodeSpec.empty(),
                    CodeSpec.empty(),
                ) shouldNotBe null
            }
        }

        describe("builder()") {
            it("should create builder") {
                val builder = IfSpec.builder()
                builder shouldNotBe null
                builder.condition shouldBe null
                builder.body shouldBe null
                builder.elseBody shouldBe null
            }

            describe("builder.condition()") {
                it("should set condition (String)") {
                    val builder = IfSpec.builder()
                    builder.condition("condition") shouldBe builder
                    builder.condition shouldBe ValueSpec.of("condition")
                }

                it("should set condition (ValueSpec)") {
                    val builder = IfSpec.builder()
                    builder.condition(ValueSpec.of("condition")) shouldBe builder
                    builder.condition shouldBe ValueSpec.of("condition")
                }
            }

            describe("builder.body()") {
                it("should set body") {
                    val builder = IfSpec.builder()
                    builder.body(CodeSpec.empty()) shouldBe builder
                    builder.body shouldBe CodeSpec.empty()
                }
            }

            describe("builder.elseBody()") {
                it("should set elseBody") {
                    val builder = IfSpec.builder()
                    builder.elseBody(CodeSpec.empty()) shouldBe builder
                    builder.elseBody shouldBe CodeSpec.empty()
                }
            }

            describe("builder.build()") {
                it("should build") {
                    val builder = IfSpec.builder()
                    builder.condition("condition")
                    builder.body(CodeSpec.empty())
                    builder.elseBody(CodeSpec.empty())
                    val spec = builder.build()

                    spec.condition shouldBe ValueSpec.of("condition")
                    spec.thenBody shouldBe CodeSpec.empty()
                    spec.elseBody shouldBe CodeSpec.empty()
                }

                it("should throw exception if no condition") {
                    val builder = IfSpec.builder()
                    builder.body(CodeSpec.empty())
                    builder.elseBody(CodeSpec.empty())

                    shouldThrowWithMessage<IllegalStateException>("Condition not set") {
                        builder.build()
                    }
                }

                it("should throw exception if no body") {
                    val builder = IfSpec.builder()
                    builder.condition("condition")
                    builder.elseBody(CodeSpec.empty())

                    shouldThrowWithMessage<IllegalStateException>("Body not set") {
                        builder.build()
                    }
                }

                it("should work if no elseBody") {
                    val builder = IfSpec.builder()
                    builder.condition("condition")
                    builder.body(CodeSpec.empty())

                    val spec = builder.build()
                    spec.condition shouldBe ValueSpec.of("condition")
                    spec.thenBody shouldBe CodeSpec.empty()
                    spec.elseBody shouldBe null
                }
            }
        }
    }

    describe("ReturnSpec") {
        describe("create()") {
            it("should create (with value)") {
                val spec = ReturnSpec(
                    ValueSpec.of("value"),
                )

                spec.value shouldBe ValueSpec.of("value")
            }

            it("should create (no value)") {
                val spec = ReturnSpec()
                spec.value shouldBe null
            }
        }

        describe("generate()") {
            it("should generate") {
                val spec = ReturnSpec(
                    ValueSpec.of("value"),
                )

                val ctx = GenerationContext()
                spec.generate(ctx) shouldBe "return value"
            }

            it("should generate (no value)") {
                val spec = ReturnSpec(
                    null,
                )

                val ctx = GenerationContext()
                spec.generate(ctx) shouldBe "return"
            }
        }

        describe("equals()") {
            it("should return true if same instance") {
                val spec = ReturnSpec(
                    ValueSpec.of("value"),
                )
                spec shouldBe spec
            }

            it("should return true if the values are the same") {
                ReturnSpec(
                    ValueSpec.of("value"),
                ) shouldBe ReturnSpec(
                    ValueSpec.of("value"),
                )
            }

            it("should return false if the values are different") {
                ReturnSpec(
                    ValueSpec.of("value"),
                ) shouldNotBe ReturnSpec(
                    ValueSpec.of("value2"),
                )
            }

            it("should return false if the other object is not a ReturnSpec") {
                ReturnSpec(
                    ValueSpec.of("value"),
                ) shouldNotBe "value"
            }

            it("should return false if the other object is null") {
                ReturnSpec(
                    ValueSpec.of("value"),
                ) shouldNotBe null
            }
        }

        describe("builder()") {
            it("should create builder") {
                val builder = ReturnSpec.builder()
                builder shouldNotBe null
                builder.value shouldBe null
            }

            describe("builder.value()") {
                it("should set value (String)") {
                    val builder = ReturnSpec.builder()
                    builder.value("value") shouldBe builder
                    builder.value shouldBe ValueSpec.of("value")
                }

                it("should set value (ValueSpec)") {
                    val builder = ReturnSpec.builder()
                    builder.value(ValueSpec.of("value")) shouldBe builder
                    builder.value shouldBe ValueSpec.of("value")
                }
            }

            describe("builder.build()") {
                it("should build") {
                    val builder = ReturnSpec.builder()
                    builder.value("value")
                    val spec = builder.build()

                    spec.value shouldBe ValueSpec.of("value")
                }

                it("should work if no value") {
                    val builder = ReturnSpec.builder()
                    val spec = builder.build()

                    spec.value shouldBe null
                }
            }
        }
    }
})
