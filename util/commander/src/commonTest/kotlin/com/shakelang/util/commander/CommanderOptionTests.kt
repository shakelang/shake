package com.shakelang.util.commander

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CommanderOptionTests : FreeSpec(
    {
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

        "parse (with option using alias)" {
            val command = command {
                name = "test"
                description = "test description"

                option {
                    name = "test2"
                    description = "test2 description"
                    alias("testalias")
                }
            }
            val result = command.execute(arrayOf("--testalias"))

            result.stack.size shouldBe 1
            result.getValueByName("test2")!!.first().toBoolean() shouldBe true
        }

        "parse (with option using alias and value)" {
            val command = command {
                name = "test"
                description = "test description"

                option {
                    name = "test2"
                    description = "test2 description"
                    alias("testalias")
                    hasValue = true
                }
            }
            val result = command.execute(arrayOf("--testalias", "Hello World"))

            result.stack.size shouldBe 1
            result.getValueByName("test2")!!.first().value shouldBe "Hello World"
        }

        "parse (with option using short alias)" {
            val command = command {
                name = "test"
                description = "test description"

                option {
                    name = "test2"
                    description = "test2 description"
                    shortAlias("t")
                }
            }
            val result = command.execute(arrayOf("-t"))

            result.stack.size shouldBe 1
            result.getValueByName("test2")!!.first().toBoolean() shouldBe true
        }

        "parse (with option using short alias and value)" {
            val command = command {
                name = "test"
                description = "test description"

                option {
                    name = "test2"
                    description = "test2 description"
                    shortAlias("t")
                    hasValue = true
                }
            }
            val result = command.execute(arrayOf("-t", "Hello World"))

            result.stack.size shouldBe 1
            result.getValueByName("test2")!!.first().value shouldBe "Hello World"
        }

        "two values for an option" {
            val command = command {
                name = "test"
                description = "test description"

                option {
                    name = "test2"
                    description = "test2 description"
                    hasValue = true
                }
            }
            val result = command.execute(arrayOf("--test2", "Hello", "--test2", "World"))

            result.stack.size shouldBe 1
            result.getValueByName("test2")!!.size shouldBe 2
            result.getValueByName("test2")!![0].value shouldBe "Hello"
            result.getValueByName("test2")!![1].value shouldBe "World"
        }

        "two values for an option using alias" {
            val command = command {
                name = "test"
                description = "test description"

                option {
                    name = "test2"
                    description = "test2 description"
                    alias("testalias")
                    hasValue = true
                }
            }
            val result = command.execute(arrayOf("--testalias", "Hello", "--testalias", "World"))

            result.stack.size shouldBe 1
            result.getValueByName("test2")!!.size shouldBe 2
            result.getValueByName("test2")!![0].value shouldBe "Hello"
            result.getValueByName("test2")!![1].value shouldBe "World"
        }

        "required option" {
            val command = command {
                name = "test"
                description = "test description"

                option {
                    name = "test2"
                    required = true
                    description = "test2 description"
                    hasValue = true
                }
            }
            val result = command.execute(arrayOf("--test2", "Hello World"))

            result.stack.size shouldBe 1
            result.getValueByName("test2")!!.first().value shouldBe "Hello World"
        }

        "required option not given" {
            val command = command {
                name = "test"
                description = "test description"

                option {
                    name = "test2"
                    required = true
                    description = "test2 description"
                    hasValue = true
                }
            }

            shouldThrow<CommanderMissingOptionException> {
                command.execute(arrayOf())
            }
        }

        "default value for option" {
            val command = command {
                name = "test"
                description = "test description"

                option {
                    name = "test2"
                    required = true
                    description = "test2 description"
                    hasValue = true
                    defaultValue = "Hello World"
                }
            }
            val result = command.execute(arrayOf())

            result.stack.size shouldBe 1
            result.getValueByName("test2")!!.first().value shouldBe "Hello World"
        }

        "default value for option (with value given)" {
            val command = command {
                name = "test"
                description = "test description"

                option {
                    name = "test2"
                    required = true
                    description = "test2 description"
                    hasValue = true
                    defaultValue = "Hello World"
                }
            }
            val result = command.execute(arrayOf("--test2", "Hello World 2"))

            result.stack.size shouldBe 1
            result.getValueByName("test2")!!.first().value shouldBe "Hello World 2"
        }

        "default value for option (with value given using alias)" {
            val command = command {
                name = "test"
                description = "test description"

                option {
                    name = "test2"
                    required = true
                    description = "test2 description"
                    hasValue = true
                    defaultValue = "Hello World"
                    alias("testalias")
                }
            }
            val result = command.execute(arrayOf("--testalias", "Hello World 2"))

            result.stack.size shouldBe 1
            result.getValueByName("test2")!!.first().value shouldBe "Hello World 2"
        }

        "default value for required option" {
            val command = command {
                name = "test"
                description = "test description"

                option {
                    name = "test2"
                    required = true
                    description = "test2 description"
                    hasValue = true
                    defaultValue = "Hello World"
                }
            }
            val result = command.execute(arrayOf())

            result.stack.size shouldBe 1
            result.getValueByName("test2")!!.first().value shouldBe "Hello World"
        }
    },
)
