package com.shakelang.shake.bytecode.interpreter.generator.attributes

import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.bytecode.interpreter.generator.bytecode
import com.shakelang.shake.util.testlib.shouldContainExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CodeAttributeGenerationContextTests : FreeSpec({

    "code attribute generation (toAttribute)" {

        val ctx = CodeAttributeGenerationContext(MutableConstantPool())
        ctx.run {

            maxLocals = 10
            maxStack = 11

        }

        val pool = MutableConstantPool()
        val attribute = ctx.toAttribute(pool)

        attribute.maxLocals shouldBe 10
        attribute.maxStack shouldBe 11
        attribute.code.isEmpty() shouldBe true
        attribute.exceptionTable.isEmpty() shouldBe true
        attribute.attributes.isEmpty() shouldBe true

    }

    "code attribute generation with bytecode (toAttribute)" {
        val ctx = CodeAttributeGenerationContext(MutableConstantPool())
        ctx.run {

            maxLocals = 10
            maxStack = 11

            bytecode {
                bpush(10)
                bpush(11)
                badd()
            }

        }

        val pool = MutableConstantPool()
        val attribute = ctx.toAttribute(pool)

        attribute.maxLocals shouldBe 10
        attribute.maxStack shouldBe 11
        attribute.code shouldBe bytecode {
            bpush(10)
            bpush(11)
            badd()
        }
        attribute.exceptionTable.isEmpty() shouldBe true
        attribute.attributes.isEmpty() shouldBe true
    }

    "code attribute generation with exception table (toAttribute)" {
        val ctx = CodeAttributeGenerationContext(MutableConstantPool())
        ctx.run {

            maxLocals = 10
            maxStack = 11

            exceptionTableEntry {
                startPc = 0
                endPc = 4
                catchType = 10
                handlerPc = 3
            }

        }

        val pool = MutableConstantPool()
        val attribute = ctx.toAttribute(pool)

        attribute.maxLocals shouldBe 10
        attribute.maxStack shouldBe 11
        attribute.exceptionTable.size shouldBe 1

        val entry = attribute.exceptionTable[0]
        entry.startPc shouldBe 0
        entry.endPc shouldBe 4
        entry.catchType shouldBe 10
        entry.handlerPc shouldBe 3

    }

    "code attribute generation with attributes (toAttribute)" {
        val ctx = CodeAttributeGenerationContext(MutableConstantPool())
        ctx.run {

            maxLocals = 10
            maxStack = 11

            attribute {
                name = "test"
                data = byteArrayOf(1, 2, 3, 4, 5)
            }

        }

        val pool = MutableConstantPool()
        val attribute = ctx.toAttribute(pool)

        attribute.maxLocals shouldBe 10
        attribute.maxStack shouldBe 11
        attribute.attributes.size shouldBe 1

        val entry = attribute.attributes[0]
        entry.name shouldBe "test"
        entry.value shouldContainExactly byteArrayOf(1, 2, 3, 4, 5)

    }

    "code attribute generation with everything (toAttribute)" {
        val ctx = CodeAttributeGenerationContext(MutableConstantPool())
        ctx.run {

            maxLocals = 10
            maxStack = 11

            bytecode {
                bpush(10)
                bpush(11)
                badd()
            }

            exceptionTableEntry {
                startPc = 0
                endPc = 4
                catchType = 10
                handlerPc = 3
            }

            attribute {
                name = "test"
                data = byteArrayOf(1, 2, 3, 4, 5)
            }

        }

        val pool = MutableConstantPool()
        val attribute = ctx.toAttribute(pool)

        attribute.maxLocals shouldBe 10
        attribute.maxStack shouldBe 11
        attribute.code shouldBe bytecode {
            bpush(10)
            bpush(11)
            badd()
        }
        attribute.exceptionTable.size shouldBe 1

        val entry = attribute.exceptionTable[0]
        entry.startPc shouldBe 0
        entry.endPc shouldBe 4
        entry.catchType shouldBe 10
        entry.handlerPc shouldBe 3

        attribute.attributes.size shouldBe 1

        val entry2 = attribute.attributes[0]
        entry2.name shouldBe "test"
        entry2.value shouldContainExactly byteArrayOf(1, 2, 3, 4, 5)

    }

    "code attribute generation with everything (toMutableAttribute)" {
        val ctx = CodeAttributeGenerationContext(MutableConstantPool())
        ctx.run {

            maxLocals = 10
            maxStack = 11

            bytecode {
                bpush(10)
                bpush(11)
                badd()
            }

            exceptionTableEntry {
                startPc = 0
                endPc = 4
                catchType = 10
                handlerPc = 3
            }

            attribute {
                name = "test"
                data = byteArrayOf(1, 2, 3, 4, 5)
            }

        }

        val pool = MutableConstantPool()
        val attribute = ctx.toMutableAttribute(pool)

        attribute.maxLocals shouldBe 10
        attribute.maxStack shouldBe 11
        attribute.code shouldBe bytecode {
            bpush(10)
            bpush(11)
            badd()
        }
        attribute.exceptionTable.size shouldBe 1

        val entry = attribute.exceptionTable[0]
        entry.startPc shouldBe 0
        entry.endPc shouldBe 4
        entry.catchType shouldBe 10
        entry.handlerPc shouldBe 3

        attribute.attributes.size shouldBe 1

        val entry2 = attribute.attributes[0]
        entry2.name shouldBe "test"
        entry2.value shouldContainExactly byteArrayOf(1, 2, 3, 4, 5)

    }

    "code attribute generation with everything (toAttribute) (with pool)" {
        val ctx = CodeAttributeGenerationContext(MutableConstantPool())
        ctx.run {

            maxLocals = 10
            maxStack = 11

            bytecode {
                bpush(10)
                bpush(11)
                badd()
            }

            exceptionTableEntry {
                startPc = 0
                endPc = 4
                catchType = 10
                handlerPc = 3
            }

            attribute {
                name = "test"
                data = byteArrayOf(1, 2, 3, 4, 5)
            }

        }

        val pool = MutableConstantPool()
        val attribute = ctx.toAttribute(pool)

        attribute.maxLocals shouldBe 10
        attribute.maxStack shouldBe 11
        attribute.code shouldBe bytecode {
            bpush(10)
            bpush(11)
            badd()
        }
        attribute.exceptionTable.size shouldBe 1

        val entry = attribute.exceptionTable[0]
        entry.startPc shouldBe 0
        entry.endPc shouldBe 4
        entry.catchType shouldBe 10
        entry.handlerPc shouldBe 3

        attribute.attributes.size shouldBe 1

        val entry2 = attribute.attributes[0]
        entry2.name shouldBe "test"
        entry2.value shouldContainExactly byteArrayOf(1, 2, 3, 4, 5)

    }

    "code attribute generation with everything (toMutableAttribute) (with pool)" {
        val ctx = CodeAttributeGenerationContext(MutableConstantPool())
        ctx.run {

            maxLocals = 10
            maxStack = 11

            bytecode {
                bpush(10)
                bpush(11)
                badd()
            }

            exceptionTableEntry {
                startPc = 0
                endPc = 4
                catchType = 10
                handlerPc = 3
            }

            attribute {
                name = "test"
                data = byteArrayOf(1, 2, 3, 4, 5)
            }

        }

        val pool = MutableConstantPool()
        val attribute = ctx.toMutableAttribute(pool)

        attribute.maxLocals shouldBe 10
        attribute.maxStack shouldBe 11
        attribute.code shouldBe bytecode {
            bpush(10)
            bpush(11)
            badd()
        }
        attribute.exceptionTable.size shouldBe 1

        val entry = attribute.exceptionTable[0]
        entry.startPc shouldBe 0
        entry.endPc shouldBe 4
        entry.catchType shouldBe 10
        entry.handlerPc shouldBe 3

        attribute.attributes.size shouldBe 1

        val entry2 = attribute.attributes[0]
        entry2.name shouldBe "test"
        entry2.value shouldContainExactly byteArrayOf(1, 2, 3, 4, 5)

    }

    "code attribute generation with everything (toAttribute) (with pool and generation context)" {
        val ctx = CodeAttributeGenerationContext(MutableConstantPool())
        ctx.run {

            maxLocals = 10
            maxStack = 11

            bytecode {
                bpush(10)
                bpush(11)
                badd()
            }

            exceptionTableEntry {
                startPc = 0
                endPc = 4
                catchType = 10
                handlerPc = 3
            }

            attribute {
                name = "test"
                data = byteArrayOf(1, 2, 3, 4, 5)
            }

        }

        val pool = MutableConstantPool()
        val attribute = ctx.toAttribute(pool)

        attribute.maxLocals shouldBe 10
        attribute.maxStack shouldBe 11
        attribute.code shouldBe bytecode {
            bpush(10)
            bpush(11)
            badd()
        }
        attribute.exceptionTable.size shouldBe 1

        val entry = attribute.exceptionTable[0]
        entry.startPc shouldBe 0
        entry.endPc shouldBe 4
        entry.catchType shouldBe 10
        entry.handlerPc shouldBe 3

        attribute.attributes.size shouldBe 1

        val entry2 = attribute.attributes[0]
        entry2.name shouldBe "test"
        entry2.value shouldContainExactly byteArrayOf(1, 2, 3, 4, 5)

    }

    "code attribute generation with everything (toMutableAttribute) (with pool and generation context)" {
        val ctx = CodeAttributeGenerationContext(MutableConstantPool())
        ctx.run {

            maxLocals = 10
            maxStack = 11

            bytecode {
                bpush(10)
                bpush(11)
                badd()
            }

            exceptionTableEntry {
                startPc = 0
                endPc = 4
                catchType = 10
                handlerPc = 3
            }

            attribute {
                name = "test"
                data = byteArrayOf(1, 2, 3, 4, 5)
            }

        }

        val pool = MutableConstantPool()
        val attribute = ctx.toMutableAttribute(pool)

        attribute.maxLocals shouldBe 10
        attribute.maxStack shouldBe 11
        attribute.code shouldBe bytecode {
            bpush(10)
            bpush(11)
            badd()
        }
        attribute.exceptionTable.size shouldBe 1

        val entry = attribute.exceptionTable[0]
        entry.startPc shouldBe 0
        entry.endPc shouldBe 4
        entry.catchType shouldBe 10
        entry.handlerPc shouldBe 3

        attribute.attributes.size shouldBe 1

        val entry2 = attribute.attributes[0]
        entry2.name shouldBe "test"
        entry2.value shouldContainExactly byteArrayOf(1, 2, 3, 4, 5)

    }

})