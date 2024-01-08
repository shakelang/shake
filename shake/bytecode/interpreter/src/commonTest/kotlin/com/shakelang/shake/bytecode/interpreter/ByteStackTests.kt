@file:Suppress("unused")

package com.shakelang.shake.bytecode.interpreter

import com.shakelang.util.testlib.shouldContainExactly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ByteStackTests : FreeSpec(
    {

        "push/pop" {

            val stack = ByteStack()

            stack.push(1.toByte())
            stack.push(2.toByte())
            stack.push(3.toByte())

            stack.pop() shouldBe 3
            stack.pop() shouldBe 2
            stack.pop() shouldBe 1
        }

        "push short" {

            val stack = ByteStack()

            stack.push(1.toShort())
            stack.push(2.toShort())
            stack.push(3.toShort())

            stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 3.toByte()
            stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 2.toByte()
            stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 1.toByte()
        }

        "push int" {

            val stack = ByteStack()

            stack.push(1)
            stack.push(2)
            stack.push(3)

            for (i in 0 until 3) stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 3.toByte()
            for (i in 0 until 3) stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 2.toByte()
            for (i in 0 until 3) stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 1.toByte()
        }

        "push long" {

            val stack = ByteStack()

            stack.push(1L)
            stack.push(2L)
            stack.push(3L)

            for (i in 0 until 7) stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 3.toByte()
            for (i in 0 until 7) stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 2.toByte()
            for (i in 0 until 7) stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 1.toByte()
        }

        "push float" {

            val stack = ByteStack()

            stack.push(Float.fromBits(1))
            stack.push(Float.fromBits(2))
            stack.push(Float.fromBits(3))

            for (i in 0 until 3) stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 3.toByte()
            for (i in 0 until 3) stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 2.toByte()
            for (i in 0 until 3) stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 1.toByte()
        }

        "push double" {

            val stack = ByteStack()

            stack.push(Double.fromBits(1))
            stack.push(Double.fromBits(2))
            stack.push(Double.fromBits(3))

            for (i in 0 until 7) stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 3.toByte()
            for (i in 0 until 7) stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 2.toByte()
            for (i in 0 until 7) stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 1.toByte()
        }

        "push ubyte" {

            val stack = ByteStack()

            stack.push(1.toUByte())
            stack.push(2.toUByte())
            stack.push(3.toUByte())

            stack.pop() shouldBe 3.toByte()
            stack.pop() shouldBe 2.toByte()
            stack.pop() shouldBe 1.toByte()
        }

        "push ushort" {

            val stack = ByteStack()

            stack.push(1.toUShort())
            stack.push(2.toUShort())
            stack.push(3.toUShort())

            stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 3.toByte()
            stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 2.toByte()
            stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 1.toByte()
        }

        "push uint" {

            val stack = ByteStack()

            stack.push(1.toUInt())
            stack.push(2.toUInt())
            stack.push(3.toUInt())

            for (i in 0 until 3) stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 3.toByte()
            for (i in 0 until 3) stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 2.toByte()
            for (i in 0 until 3) stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 1.toByte()
        }

        "push ulong" {

            val stack = ByteStack()

            stack.push(1.toULong())
            stack.push(2.toULong())
            stack.push(3.toULong())

            for (i in 0 until 7) stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 3.toByte()
            for (i in 0 until 7) stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 2.toByte()
            for (i in 0 until 7) stack.pop() shouldBe 0.toByte()
            stack.pop() shouldBe 1.toByte()
        }

        "pop short" {

            val stack = ByteStack()

            stack.push(1.toByte())
            stack.push(0.toByte())
            stack.push(2.toByte())
            stack.push(0.toByte())
            stack.push(3.toByte())
            stack.push(0.toByte())

            stack.popShort() shouldBe 3.toShort()
            stack.popShort() shouldBe 2.toShort()
            stack.popShort() shouldBe 1.toShort()
        }

        "pop int" {

            val stack = ByteStack()

            stack.push(1.toByte())
            for (i in 0 until 3) stack.push(0.toByte())
            stack.push(2.toByte())
            for (i in 0 until 3) stack.push(0.toByte())
            stack.push(3.toByte())
            for (i in 0 until 3) stack.push(0.toByte())

            stack.popInt() shouldBe 3
            stack.popInt() shouldBe 2
            stack.popInt() shouldBe 1
        }

        "pop long" {

            val stack = ByteStack()

            stack.push(1.toByte())
            for (i in 0 until 7) stack.push(0.toByte())
            stack.push(2.toByte())
            for (i in 0 until 7) stack.push(0.toByte())
            stack.push(3.toByte())
            for (i in 0 until 7) stack.push(0.toByte())

            stack.popLong() shouldBe 3L
            stack.popLong() shouldBe 2L
            stack.popLong() shouldBe 1L
        }

        "pop float" {

            val stack = ByteStack()

            stack.push(1.toByte())
            for (i in 0 until 3) stack.push(0.toByte())
            stack.push(2.toByte())
            for (i in 0 until 3) stack.push(0.toByte())
            stack.push(3.toByte())
            for (i in 0 until 3) stack.push(0.toByte())

            stack.popFloat() shouldBe Float.fromBits(3)
            stack.popFloat() shouldBe Float.fromBits(2)
            stack.popFloat() shouldBe Float.fromBits(1)
        }

        "pop double" {

            val stack = ByteStack()

            stack.push(1.toByte())
            for (i in 0 until 7) stack.push(0.toByte())
            stack.push(2.toByte())
            for (i in 0 until 7) stack.push(0.toByte())
            stack.push(3.toByte())
            for (i in 0 until 7) stack.push(0.toByte())

            stack.popDouble() shouldBe Double.fromBits(3)
            stack.popDouble() shouldBe Double.fromBits(2)
            stack.popDouble() shouldBe Double.fromBits(1)
        }

        "pop ubyte" {

            val stack = ByteStack()

            stack.push(1.toByte())
            stack.push(2.toByte())
            stack.push(3.toByte())

            stack.popUByte() shouldBe 3.toUByte()
            stack.popUByte() shouldBe 2.toUByte()
            stack.popUByte() shouldBe 1.toUByte()
        }

        "pop ushort" {

            val stack = ByteStack()

            stack.push(1.toByte())
            stack.push(0.toByte())
            stack.push(2.toByte())
            stack.push(0.toByte())
            stack.push(3.toByte())
            stack.push(0.toByte())

            stack.popUShort() shouldBe 3.toUShort()
            stack.popUShort() shouldBe 2.toUShort()
            stack.popUShort() shouldBe 1.toUShort()
        }

        "pop uint" {

            val stack = ByteStack()

            stack.push(1.toByte())
            for (i in 0 until 3) stack.push(0.toByte())
            stack.push(2.toByte())
            for (i in 0 until 3) stack.push(0.toByte())
            stack.push(3.toByte())
            for (i in 0 until 3) stack.push(0.toByte())

            stack.popUInt() shouldBe 3.toUInt()
            stack.popUInt() shouldBe 2.toUInt()
            stack.popUInt() shouldBe 1.toUInt()
        }

        "pop ulong" {

            val stack = ByteStack()

            stack.push(1.toByte())
            for (i in 0 until 7) stack.push(0.toByte())
            stack.push(2.toByte())
            for (i in 0 until 7) stack.push(0.toByte())
            stack.push(3.toByte())
            for (i in 0 until 7) stack.push(0.toByte())

            stack.popULong() shouldBe 3.toULong()
            stack.popULong() shouldBe 2.toULong()
            stack.popULong() shouldBe 1.toULong()
        }

        "test stack overflow" {
            val stack = ByteStack(256)
            for (i in 0 until 256) stack.push(1.toByte())
            shouldThrow<StackOverflowException> {
                stack.push(1.toByte())
            }
        }

        "test stack underflow" {
            val stack = ByteStack()
            shouldThrow<StackUnderflowException> {
                stack.pop()
            }
        }

        "test to byte array" {
            val stack = ByteStack()
            stack.push(1.toByte())
            stack.push(2.toByte())
            stack.push(3.toByte())
            stack.toByteArray() shouldContainExactly byteArrayOf(3, 2, 1)
        }

        "test peek" {
            val stack = ByteStack()
            stack.push(1.toByte())
            stack.push(2.toByte())
            stack.push(3.toByte())
            stack.peek() shouldBe 3.toByte()
            stack.pop() shouldBe 3.toByte()
            stack.peek() shouldBe 2.toByte()
            stack.pop() shouldBe 2.toByte()
            stack.peek() shouldBe 1.toByte()
            stack.pop() shouldBe 1.toByte()
        }
    },
)
