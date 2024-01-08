package com.shakelang.util.commander

import com.shakelang.util.testlib.CallCounter
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CommanderTests : FreeSpec(
    {

        "command generation" {
            val command = command {
                name = "test"
                description = "test description"
            }

            command.name shouldBe "test"
            command.description shouldBe "test description"
        }

        "subcommand generation" {
            val command = command {
                name = "test"
                description = "test description"

                subcommand {
                    name = "test2"
                    description = "test2 description"
                    action {
                        println("test")
                    }
                }
            }

            command.name shouldBe "test"
            command.description shouldBe "test description"
            command.commands.size shouldBe 1
            command.commands[0].name shouldBe "test2"
            command.commands[0].description shouldBe "test2 description"
        }

        "argument generation" {
            val command = command {
                name = "test"
                description = "test description"

                argument {
                    name = "test2"
                    description = "test2 description"
                    action {
                        println("test")
                    }
                }
            }

            command.name shouldBe "test"
            command.description shouldBe "test description"
            command.arguments.size shouldBe 1
            command.arguments[0].name shouldBe "test2"
            command.arguments[0].description shouldBe "test2 description"
        }

        "option generation" {
            val command = command {
                name = "test"
                description = "test description"

                option {
                    name = "test2"
                    description = "test2 description"
                    action {
                        println("test")
                    }
                }
            }

            command.name shouldBe "test"
            command.description shouldBe "test description"
            command.options.size shouldBe 1
            command.options[0].name shouldBe "test2"
            command.options[0].description shouldBe "test2 description"
        }

        "command alias" {
            val command = command {
                name = "test"
                description = "test description"
                alias("testalias")
            }

            command.name shouldBe "test"
            command.description shouldBe "test description"
            command.aliases.size shouldBe 1
            command.aliases[0] shouldBe "testalias"
        }

        "command action" {
            val counter = CallCounter()
            val command = command {
                name = "test"
                description = "test description"
                action {
                    counter.call()
                }
            }

            command.name shouldBe "test"
            command.description shouldBe "test description"
            command.action!!.invoke(command, ParseResult(mutableListOf()))
            counter shouldBeCalled 1
        }

        "parse (empty)" {
            val counter = CallCounter()
            val command = command {
                name = "test"
                description = "test description"
                action {
                    counter.call()
                }
            }
            val result = command.parse(arrayOf())

            result.stack.size shouldBe 1
        }

        "parse (with subcommand)" {
            val counter = CallCounter()
            val command = command {
                name = "test"
                description = "test description"

                subcommand {
                    name = "test2"
                    description = "test2 description"
                    action {
                        counter.call()
                    }
                }
            }
            val result = command.execute(arrayOf("test2"))

            result.stack.size shouldBe 2
            result.stack[1].name shouldBe "test2"
            counter shouldBeCalled 1
        }

        "parse (with subcommand using alias)" {
            val counter = CallCounter()
            val command = command {
                name = "test"
                description = "test description"

                subcommand {
                    name = "test2"
                    description = "test2 description"
                    alias("testalias")
                    action {
                        counter.call()
                    }
                }
            }
            val result = command.execute(arrayOf("testalias"))

            result.stack.size shouldBe 2
            result.stack[1].name shouldBe "test2"
            counter shouldBeCalled 1
        }

        "parse (with argument)" {
            val command = command {
                name = "test"
                description = "test description"

                argument {
                    name = "test2"
                    description = "test2 description"
                }
            }
            val result = command.execute(arrayOf("Hello World"))

            result.stack.size shouldBe 1
            result.getValueByName("test2")!!.first().value shouldBe "Hello World"
        }

        "parse (with valued option)" {
            val command = command {
                name = "test"
                description = "test description"

                option {
                    name = "test2"
                    description = "test2 description"
                    hasValue = true
                }
            }
            val result = command.execute(arrayOf("--test2", "Hello World"))

            result.stack.size shouldBe 1
            result.getValueByName("test2")!!.first().value shouldBe "Hello World"
        }

        "parse (with option)" {
            val command = command {
                name = "test"
                description = "test description"

                option {
                    name = "test2"
                    description = "test2 description"
                }
            }
            val result = command.execute(arrayOf("--test2"))

            result.stack.size shouldBe 1
            result.getValueByName("test2")!!.first().toBoolean() shouldBe true
        }
    },
)
