package com.shakelang.util.commander

import com.shakelang.util.testlib.CallCounter
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CommanderCommandTests : FreeSpec(
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
            command.action!!.invoke(command, CommanderParseResult(mutableListOf()))
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
    },
)
