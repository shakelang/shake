package com.shakelang.util.commander

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CommanderArgumentTests : FreeSpec(
    {
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

        "parse (required argument)" {
            val command = command {
                name = "test"
                description = "test description"

                argument {
                    name = "test2"
                    description = "test2 description"
                    required = true
                }
            }
            val result = command.execute(arrayOf("Hello World"))

            result.stack.size shouldBe 1
            result.getValueByName("test2")!!.first().value shouldBe "Hello World"
        }

        "parse (required argument not given)" {
            val command = command {
                name = "test"
                description = "test description"

                argument {
                    name = "test2"
                    description = "test2 description"
                    required = true
                }
            }

            shouldThrow<CliMissingArgumentException> {
                command.execute(arrayOf())
            }
        }

        "argument default value" {
            val command = command {
                name = "test"
                description = "test description"

                argument {
                    name = "test2"
                    description = "test2 description"
                    defaultValue = "Hello World"
                }
            }
            val result = command.execute(arrayOf())

            result.stack.size shouldBe 1
            result.getValueByName("test2")!!.first().value shouldBe "Hello World"
        }

        "argument default value (with value given)" {
            val command = command {
                name = "test"
                description = "test description"

                argument {
                    name = "test2"
                    description = "test2 description"
                    defaultValue = "Hello World"
                }
            }
            val result = command.execute(arrayOf("Hello World"))

            result.stack.size shouldBe 1
            result.getValueByName("test2")!!.first().value shouldBe "Hello World"
        }

        "required argument default value" {
            val command = command {
                name = "test"
                description = "test description"

                argument {
                    name = "test2"
                    description = "test2 description"
                    defaultValue = "Hello World"
                    required = true
                }
            }
            val result = command.execute(arrayOf())

            result.stack.size shouldBe 1
            result.getValueByName("test2")!!.first().value shouldBe "Hello World"
        }
    },
)
