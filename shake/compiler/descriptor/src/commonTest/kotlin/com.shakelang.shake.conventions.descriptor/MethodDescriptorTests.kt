package com.shakelang.shake.conventions.descriptor

import com.shakelang.shake.bytecode.interpreter.format.descriptor.MethodDescriptor
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class MethodDescriptorTests :
    FreeSpec(
        {

            "empty method name" {
                val exception = shouldThrow<IllegalArgumentException> {
                    MethodDescriptor("", arrayOf(), TypeDescriptor.VOID)
                }
                exception.message shouldBe "Method name cannot be empty"
            }

            "test parameter count" {
                val descriptor = MethodDescriptor("test", arrayOf(TypeDescriptor.INT, TypeDescriptor.INT), TypeDescriptor.INT)
                descriptor.parameterCount shouldBe 2
            }

            "test descriptor" {
                val descriptor = MethodDescriptor("test", arrayOf(TypeDescriptor.INT, TypeDescriptor.INT), TypeDescriptor.INT)
                descriptor.descriptor shouldBe "test(I,I)I"
            }

            "test toString" {
                val descriptor = MethodDescriptor("test", arrayOf(TypeDescriptor.INT, TypeDescriptor.INT), TypeDescriptor.INT)
                descriptor.toString() shouldBe "test(I,I)I"
            }

            "test equals" {
                val descriptor1 = MethodDescriptor("test", arrayOf(TypeDescriptor.INT, TypeDescriptor.INT), TypeDescriptor.INT)
                val descriptor2 = MethodDescriptor("test", arrayOf(TypeDescriptor.INT, TypeDescriptor.INT), TypeDescriptor.INT)
                val descriptor3 = MethodDescriptor("test", arrayOf(TypeDescriptor.INT, TypeDescriptor.INT), TypeDescriptor.VOID)
                val descriptor4 = MethodDescriptor(
                    "test",
                    arrayOf(TypeDescriptor.INT, TypeDescriptor.INT, TypeDescriptor.INT),
                    TypeDescriptor.INT,
                )
                val descriptor5 = MethodDescriptor("test2", arrayOf(TypeDescriptor.INT, TypeDescriptor.INT), TypeDescriptor.INT)

                descriptor1 shouldBe descriptor2
                descriptor1 shouldNotBe descriptor3
                descriptor1 shouldNotBe descriptor4
                descriptor1 shouldNotBe descriptor5
            }

            "test hashCode" {
                val descriptor1 = MethodDescriptor("test", arrayOf(TypeDescriptor.INT, TypeDescriptor.INT), TypeDescriptor.INT)
                val descriptor2 = MethodDescriptor("test", arrayOf(TypeDescriptor.INT, TypeDescriptor.INT), TypeDescriptor.INT)
                val descriptor3 = MethodDescriptor("test", arrayOf(TypeDescriptor.INT, TypeDescriptor.INT), TypeDescriptor.VOID)
                val descriptor4 = MethodDescriptor(
                    "test",
                    arrayOf(TypeDescriptor.INT, TypeDescriptor.INT, TypeDescriptor.INT),
                    TypeDescriptor.INT,
                )
                val descriptor5 = MethodDescriptor("test2", arrayOf(TypeDescriptor.INT, TypeDescriptor.INT), TypeDescriptor.INT)

                descriptor1.hashCode() shouldBe descriptor2.hashCode()
                descriptor1.hashCode() shouldNotBe descriptor3.hashCode()
                descriptor1.hashCode() shouldNotBe descriptor4.hashCode()
                descriptor1.hashCode() shouldNotBe descriptor5.hashCode()
            }

            "test parse with no parameters" {
                val descriptor = MethodDescriptor.parse("test()V")
                descriptor.name shouldBe "test"
                descriptor.parameters.size shouldBe 0
                descriptor.returnType shouldBe TypeDescriptor.VOID
            }

            "test parse with one parameter" {
                val descriptor = MethodDescriptor.parse("test(I)V")
                descriptor.name shouldBe "test"
                descriptor.parameters.size shouldBe 1
                descriptor.parameters[0] shouldBe TypeDescriptor.INT
                descriptor.returnType shouldBe TypeDescriptor.VOID
            }

            "test parse with two parameters" {
                val descriptor = MethodDescriptor.parse("test(I,I)V")
                descriptor.name shouldBe "test"
                descriptor.parameters.size shouldBe 2
                descriptor.parameters[0] shouldBe TypeDescriptor.INT
                descriptor.parameters[1] shouldBe TypeDescriptor.INT
                descriptor.returnType shouldBe TypeDescriptor.VOID
            }
        },
    )
