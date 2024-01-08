package com.shakelang.shake.shasambly.interpreter

import com.shakelang.shake.shasambly.interpreter.natives.Natives
import com.shakelang.util.primitives.bytes.toBytes
import kotlin.test.Test
import kotlin.test.assertEquals

class ShasamblyInterpretingBaseStackTests {

    @Test
    fun testPush() {
        val it = ShasamblyInterpretingBaseTests.Instance(64)
        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(63).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(24) { 0 },
            ).toList(),
            it.memory.toList(),
        )

        it.stack.push(1)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(62).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(23) { 0 },
                1, // Bytes on the stack
            ).toList(),
            it.memory.toList(),
        )

        it.stack.push(5)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(61).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(22) { 0 },
                5, 1, // Bytes on the stack
            ).toList(),
            it.memory.toList(),
        )
    }

    @Test
    fun testPop() {
        val it = ShasamblyInterpretingBaseTests.Instance(64)

        it.stackPointer = 61
        it.memory[62] = 5
        it.memory[63] = 1

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(61).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(22) { 0 },
                5, 1, // Bytes on the stack
            ).toList(),
            it.memory.toList(),
        )

        assertEquals(5, it.stack.pop())

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(62).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(22) { 0 },
                5, // << This byte is not relevant anymore, the stack pointer ignores it, it is just left here and will be overwritten on the next push
                1, // Bytes on the stack
            ).toList(),
            it.memory.toList(),
        )

        assertEquals(1, it.stack.pop())

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(63).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(22) { 0 },
                5, 1, // leftover bytes on the stack
            ).toList(),
            it.memory.toList(),
        )
    }

    @Test
    fun stackPushPop() {
        val it = ShasamblyInterpretingBaseTests.Instance(64)
        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(63).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(24) { 0 },
            ).toList(),
            it.memory.toList(),
        )

        it.stack.push(1)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(62).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(23) { 0 },
                1, // Bytes on the stack
            ).toList(),
            it.memory.toList(),
        )

        it.stack.push(5)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(61).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(22) { 0 },
                5, 1, // Bytes on the stack
            ).toList(),
            it.memory.toList(),
        )

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(61).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(22) { 0 },
                5, 1, // Bytes on the stack
            ).toList(),
            it.memory.toList(),
        )

        assertEquals(5, it.stack.pop())

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(62).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(22) { 0 },
                5, // << This byte is not relevant anymore, the stack pointer ignores it, it is just left here and will be overwritten on the next push
                1, // Bytes on the stack
            ).toList(),
            it.memory.toList(),
        )

        assertEquals(1, it.stack.pop())

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(63).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(22) { 0 },
                5, 1, // leftover bytes on the stack
            ).toList(),
            it.memory.toList(),
        )

        it.stack.push(3)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(62).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(22) { 0 },
                5, 3, // Bytes on the stack
            ).toList(),
            it.memory.toList(),
        )

        it.stack.push(4)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(61).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(22) { 0 },
                4, 3, // Bytes on the stack
            ).toList(),
            it.memory.toList(),
        )
    }

    @Test
    fun testPushByte() {
        val it = ShasamblyInterpretingBaseTests.Instance(64)
        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(63).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(24) { 0 },
            ).toList(),
            it.memory.toList(),
        )

        it.stack.pushByte(1)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(62).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(23) { 0 },
                1, // Bytes on the stack
            ).toList(),
            it.memory.toList(),
        )

        it.stack.pushByte(5)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(61).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(22) { 0 },
                5, 1, // Bytes on the stack
            ).toList(),
            it.memory.toList(),
        )
    }

    @Test
    fun testPopByte() {
        val it = ShasamblyInterpretingBaseTests.Instance(64)

        it.stackPointer = 61
        it.memory[62] = 5
        it.memory[63] = 1

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(61).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(22) { 0 },
                5, 1, // Bytes on the stack
            ).toList(),
            it.memory.toList(),
        )

        assertEquals(5, it.stack.popByte())

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(62).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(22) { 0 },
                5, // << This byte is not relevant anymore, the stack pointer ignores it, it is just left here and will be overwritten on the next push
                1, // Bytes on the stack
            ).toList(),
            it.memory.toList(),
        )

        assertEquals(1, it.stack.popByte())

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(63).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(22) { 0 },
                5, 1, // leftover bytes on the stack
            ).toList(),
            it.memory.toList(),
        )
    }

    @Test
    fun testPushShort() {
        val it = ShasamblyInterpretingBaseTests.Instance(64)
        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(63).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(24) { 0 },
            ).toList(),
            it.memory.toList(),
        )

        it.stack.pushShort(1)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(61).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(23) { 0 },
                1, // Bytes on the stack
            ).toList(),
            it.memory.toList(),
        )

        it.stack.pushShort(5)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(59).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(20) { 0 },
                0, 5, 0, 1, // Bytes on the stack
            ).toList(),
            it.memory.toList(),
        )
    }

    @Test
    fun testPopShort() {
        val it = ShasamblyInterpretingBaseTests.Instance(64)

        it.stackPointer = 59
        it.memory[60] = 0
        it.memory[61] = 5
        it.memory[62] = 0
        it.memory[63] = 1

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(59).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(20) { 0 },
                0, 5, 0, 1, // Bytes on the stack
            ).toList(),
            it.memory.toList(),
        )

        assertEquals(5, it.stack.popShort())

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(61).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH,
                *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE,
                *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(20) { 0 },
                0,
                5, // << This byte is not relevant anymore, the stack pointer ignores it, it is just left here and will be overwritten on the next push
                0,
                1, // Bytes on the stack
            ).toList(),
            it.memory.toList(),
        )

        assertEquals(1, it.stack.popShort())

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(63).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(20) { 0 },
                0, 5, 0, 1, // leftover bytes on the stack
            ).toList(),
            it.memory.toList(),
        )
    }

    @Test
    fun testPushInt() {
        val it = ShasamblyInterpretingBaseTests.Instance(64)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(63).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(24) { 0 },
            ).toList(),
            it.memory.toList(),
        )

        it.stack.pushInt(1)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(59).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(20) { 0 },
                0, 0, 0, 1, // Bytes on the stack
            ).toList(),
            it.memory.toList(),
        )
    }

    @Test
    fun testPopInt() {
        val it = ShasamblyInterpretingBaseTests.Instance(64)

        it.stackPointer = 55
        it.memory[56] = 0x00
        it.memory[57] = 0x00
        it.memory[58] = 0x00
        it.memory[59] = 0x05
        it.memory[60] = 0x00
        it.memory[61] = 0x00
        it.memory[62] = 0x00
        it.memory[63] = 0x01

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(55).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(16) { 0 },
                0, 0, 0, 5,
                0, 0, 0, 1,
            ).toList(),
            it.memory.toList(),
        )

        assertEquals(5, it.stack.popInt())

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(59).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(16) { 0 },
                0, 0, 0, 5,
                0, 0, 0, 1,
            ).toList(),
            it.memory.toList(),
        )

        assertEquals(1, it.stack.popInt())

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(63).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(16) { 0 },
                0, 0, 0, 5,
                0, 0, 0, 1,
            ).toList(),
            it.memory.toList(),
        )
    }

    @Test
    fun testPushLong() {
        val it = ShasamblyInterpretingBaseTests.Instance(64)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(63).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(24) { 0 },
            ).toList(),
            it.memory.toList(),
        )

        it.stack.pushLong(1)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(55).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(16) { 0 },
                0, 0, 0, 0, 0, 0, 0, 1,
            ).toList(),
            it.memory.toList(),
        )

        it.stack.pushLong(5)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(47).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(8) { 0 },
                0, 0, 0, 0, 0, 0, 0, 5,
                0, 0, 0, 0, 0, 0, 0, 1,
            ).toList(),
            it.memory.toList(),
        )
    }

    @Test
    fun popLong() {
        val it = ShasamblyInterpretingBaseTests.Instance(64)

        it.stackPointer = 47
        it.memory[55] = 5
        it.memory[63] = 1

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(47).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(8) { 0 },
                *5L.toBytes(),
                *1L.toBytes(),
            ).toList(),
            it.memory.toList(),
        )

        assertEquals(5, it.stack.popLong())

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(55).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(8) { 0 },
                *5L.toBytes(),
                *1L.toBytes(),
            ).toList(),
            it.memory.toList(),
        )

        assertEquals(1, it.stack.popLong())

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(63).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(8) { 0 },
                *5L.toBytes(),
                *1L.toBytes(),
            ).toList(),
            it.memory.toList(),
        )
    }

    @Test
    fun testPushFloat() {
        val it = ShasamblyInterpretingBaseTests.Instance(64)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(63).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(24) { 0 },
            ).toList(),
            it.memory.toList(),
        )

        it.stack.pushFloat(1.0f)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(59).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(20) { 0 },
                *(1.0f).toBytes(),
            ).toList(),
            it.memory.toList(),
        )

        it.stack.pushFloat(5.0f)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(55).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(16) { 0 },
                *(5.0f).toBytes(),
                *(1.0f).toBytes(),
            ).toList(),
            it.memory.toList(),
        )
    }

    @Test
    fun popFloat() {
        val it = ShasamblyInterpretingBaseTests.Instance(64)

        it.stackPointer = 55
        5.0f.toBytes().copyInto(it.memory, 56)
        1.0f.toBytes().copyInto(it.memory, 60)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(55).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(16) { 0 },
                *5.0f.toBytes(),
                *1.0f.toBytes(),
            ).toList(),
            it.memory.toList(),
        )

        assertEquals(5.0f, it.stack.popFloat())

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(59).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(16) { 0 },
                *5.0f.toBytes(),
                *1.0f.toBytes(),
            ).toList(),
            it.memory.toList(),
        )

        assertEquals(1.0f, it.stack.popFloat())

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(63).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(16) { 0 },
                *5.0f.toBytes(),
                *1.0f.toBytes(),
            ).toList(),
            it.memory.toList(),
        )
    }

    @Test
    fun testPushDouble() {
        val it = ShasamblyInterpretingBaseTests.Instance(64)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(63).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(24) { 0 },
            ).toList(),
            it.memory.toList(),
        )

        it.stack.pushDouble(1.0)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(55).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(16) { 0 },
                *(1.0).toBytes(),
            ).toList(),
            it.memory.toList(),
        )

        it.stack.pushDouble(5.0)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(47).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(8) { 0 },
                *(5.0).toBytes(),
                *(1.0).toBytes(),
            ).toList(),
            it.memory.toList(),
        )
    }

    @Test
    fun testPushBoolean() {
        val it = ShasamblyInterpretingBaseTests.Instance(64)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(63).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(24) { 0 },
            ).toList(),
            it.memory.toList(),
        )

        it.stack.pushBoolean(false)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(62).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(23) { 0 },
                0,
            ).toList(),
            it.memory.toList(),
        )

        it.stack.pushBoolean(true)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(61).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(22) { 0 },
                1, 0,
            ).toList(),
            it.memory.toList(),
        )
    }

    @Test
    fun popDouble() {
        val it = ShasamblyInterpretingBaseTests.Instance(64)

        it.stackPointer = 47
        5.0.toBytes().copyInto(it.memory, 48)
        1.0.toBytes().copyInto(it.memory, 56)

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(47).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(8) { 0 },
                *5.0.toBytes(),
                *1.0.toBytes(),
            ).toList(),
            it.memory.toList(),
        )

        assertEquals(5.0, it.stack.popDouble())

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(55).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(8) { 0 },
                *5.0.toBytes(),
                *1.0.toBytes(),
            ).toList(),
            it.memory.toList(),
        )

        assertEquals(1.0, it.stack.popDouble())

        assertEquals(
            byteArrayOf(
                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *(63).toBytes(), // Stack pointer
                *(-1).toBytes(), // Local variables stack pointer
                *(32).toBytes(), // Instruction pointer
                *(40).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes(), // Invoke native exit

                *ByteArray(8) { 0 },
                *5.0.toBytes(),
                *1.0.toBytes(),
            ).toList(),
            it.memory.toList(),
        )
    }
}
