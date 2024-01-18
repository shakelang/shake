package com.shakelang.shake.bytecode.interpreter.heap

import com.shakelang.shake.bytecode.interpreter.ByteStack
import com.shakelang.shake.bytecode.interpreter.ShakeInterpreter
import com.shakelang.util.primitives.bytes.toBytes
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class GarbageCollectorTests : FreeSpec({

    class TestEnvironment {
        val globalMemory = GlobalMemory()
        val malloc = Malloc(globalMemory)
        val interpreter = ShakeInterpreter()
        val garbageCollector = GarbageCollector(malloc, interpreter)
    }

    class ShakeCallStackElement(
        override val stack: ByteStack,
        override val locals: ByteArray,
    ) : com.shakelang.shake.bytecode.interpreter.ShakeCallStackElement {
        override val returnData: ByteArray = byteArrayOf()
        override val finished: Boolean = false

        constructor(
            stack: ByteArray,
            locals: ByteArray,
        ) : this(
            stack.run {
                val byteStack = ByteStack()
                stack.forEach { byteStack.push(it) }
                byteStack
            },
            locals,
        )

        override fun tick(times: Int): Int {
            TODO("Not yet implemented")
        }

        override fun tick() {
            TODO("Not yet implemented")
        }
    }

    fun test(test: TestEnvironment.() -> Unit) {
        test(TestEnvironment())
    }

    "marking using scanBlock(block)" {
        test {
            val pointer1 = malloc.malloc(8)
            val pointer2 = malloc.malloc(8)
            globalMemory.setLong(pointer1, pointer2)
            garbageCollector.scanBlock(pointer1 - malloc.headerSize)

            val header1 = malloc.readHeaderFor(pointer1)
            val header2 = malloc.readHeaderFor(pointer2)

            header1.isMarked shouldBe false
            header2.isMarked shouldBe true

            header1.isScanned shouldBe true
            header2.isScanned shouldBe false
        }
    }

    "marking using scanBlocks(blocks)" {
        test {
            val pointer2 = malloc.malloc(8)
            val pointer1 = malloc.malloc(8)

            var header1 = malloc.readHeaderFor(pointer1)
            header1.isMarked = true
            malloc.writeHeaderFor(pointer1, header1)
            globalMemory.setLong(pointer1, pointer2)

            garbageCollector.scanBlocks(
                malloc.usedStartPointer,
            )

            header1 = malloc.readHeaderFor(pointer1)
            val header2 = malloc.readHeaderFor(pointer2)

            header1.isMarked shouldBe true
            header2.isMarked shouldBe true
            header1.isScanned shouldBe true
            header2.isScanned shouldBe false
        }
    }

    "marking using mark(pointer)" {
        test {
            val pointer4 = malloc.malloc(8)
            val pointer3 = malloc.malloc(8)
            val pointer2 = malloc.malloc(8)
            val pointer1 = malloc.malloc(8)

            globalMemory.setLong(pointer1, pointer2)
            globalMemory.setLong(pointer2, pointer3)

            var header1 = malloc.readHeaderFor(pointer1)
            header1.isMarked = true
            malloc.writeHeaderFor(pointer1, header1)

            garbageCollector.mark(malloc.usedStartPointer)

            header1 = malloc.readHeaderFor(pointer1)
            val header2 = malloc.readHeaderFor(pointer2)
            val header3 = malloc.readHeaderFor(pointer3)
            val header4 = malloc.readHeaderFor(pointer4)

            header1.isMarked shouldBe true
            header2.isMarked shouldBe true
            header3.isMarked shouldBe true
            header4.isMarked shouldBe false
            header1.isScanned shouldBe true
            header2.isScanned shouldBe true
            header3.isScanned shouldBe true
            header4.isScanned shouldBe false
        }
    }

    "marking references in local variable table" {
        test {
            val pointer2 = malloc.malloc(8)
            val pointer1 = malloc.malloc(8)

            globalMemory.setLong(pointer1, pointer2)

            val locals = pointer1.toBytes()
            val stack = byteArrayOf()

            interpreter.pushStack(
                ShakeCallStackElement(
                    stack,
                    locals,
                ),
            )

            garbageCollector.scanLocalReferences()

            val header1 = malloc.readHeaderFor(pointer1)
            val header2 = malloc.readHeaderFor(pointer2)

            header1.isMarked shouldBe true
            header2.isMarked shouldBe false
            header1.isScanned shouldBe false
            header2.isScanned shouldBe false
        }
    }

    "marking references on stack" {
        test {
            val pointer2 = malloc.malloc(8)
            val pointer1 = malloc.malloc(8)

            globalMemory.setLong(pointer1, pointer2)

            val locals = byteArrayOf()
            val stack = pointer1.toBytes()

            interpreter.pushStack(
                ShakeCallStackElement(
                    stack,
                    locals,
                ),
            )

            garbageCollector.scanLocalReferences()

            val header1 = malloc.readHeaderFor(pointer1)
            val header2 = malloc.readHeaderFor(pointer2)

            header1.isMarked shouldBe true
            header2.isMarked shouldBe false
            header1.isScanned shouldBe false
            header2.isScanned shouldBe false
        }
    }
})
