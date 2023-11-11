package com.shakelang.shake.shasambly.interpreter

import io.github.shakelang.shake.shasambly.interpreter.natives.Natives
import com.shakelang.shake.util.primitives.bytes.toBytes
import kotlin.test.Test
import kotlin.test.assertEquals

class ShasamblyInterpretingBaseTests {

    class Instance(memorySize: Int, bytes: ByteArray = byteArrayOf()) : ShasamblyInterpretingBase(memorySize, bytes, 0)

    @Test
    fun basicTest() {
        val it = Instance(64, byteArrayOf(0x00, 0x01, 0x02, 0x03, 0x04, 0x05))
        assertEquals(
            listOf(
                *(-1).toBytes().toTypedArray(), // Free table start pointer
                *(-1).toBytes().toTypedArray(), // Free table end pointer
                *63.toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(46).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                0, 1, 2, 3, 4, 5, // Given instruction bytes

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *Array(18) { 0 } // two unused bytes
            ),
            it.memory.toList()
        )
    }

    @Test
    fun testIncreaseGlobals() {
        val it = Instance(64, byteArrayOf())
        assertEquals(40, it.increaseGlobals(4))
        assertEquals(
            listOf(
                *(-1).toBytes().toTypedArray(), // Free table start pointer
                *(-1).toBytes().toTypedArray(), // Free table end pointer
                *(63).toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(44).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *Array(24) { 0 } // eight unused bytes
            ),
            it.memory.toList()
        )
    }

    @Test
    fun testIncreaseGlobals2() {
        val it = Instance(64, byteArrayOf())
        assertEquals(40, it.increaseGlobals(4))
        assertEquals(44, it.increaseGlobals(4))
        assertEquals(
            listOf(
                *(-1).toBytes().toTypedArray(), // Free table start pointer
                *(-1).toBytes().toTypedArray(), // Free table end pointer
                *63.toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(48).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *Array(24) { 0 } // eight unused bytes
            ),
            it.memory.toList()
        )
    }

    @Test
    fun testCreateFreeTable() {
        val it = Instance(64, byteArrayOf())
        assertEquals(
            listOf(
                *(-1).toBytes().toTypedArray(), // Free table start pointer
                *(-1).toBytes().toTypedArray(), // Free table end pointer
                *63.toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(40).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *Array(24) { 0 } // unused bytes
            ),
            it.memory.toList()
        )
        it.freeTable.create(10)

        assertEquals(
            listOf(
                *(40).toBytes().toTypedArray(), // Free table start pointer
                *(40).toBytes().toTypedArray(), // Free table end pointer
                *63.toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(60).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *Array(4) { 0 } // unused bytes
            ),
            it.memory.toList()
        )
    }

    @Test
    fun testCreateFreeTable2() {
        val it = Instance(96, byteArrayOf())
        assertEquals(
            listOf(
                *(-1).toBytes().toTypedArray(), // Free table start pointer
                *(-1).toBytes().toTypedArray(), // Free table end pointer
                *(95).toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(40).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *Array(56) { 0 } // unused bytes
            ),
            it.memory.toList()
        )

        it.freeTable.create(10)
        assertEquals(
            listOf(
                *(40).toBytes().toTypedArray(), // Free table start pointer
                *(40).toBytes().toTypedArray(), // Free table end pointer
                *(95).toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(60).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *Array(36) { 0 } // unused bytes

            ),
            it.memory.toList()
        )

        it.freeTable.create(12)
        assertEquals(
            listOf(
                *(40).toBytes().toTypedArray(), // Free table start pointer
                *(60).toBytes().toTypedArray(), // Free table end pointer
                *(95).toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(80).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(60).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *12.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(40).toBytes().toTypedArray(), // The previous free table entry

                *Array(16) { 0 } // unused bytes

            ),
            it.memory.toList()
        )
    }

    @Test
    fun testCreateFreeTable3() {
        val it = Instance(96, byteArrayOf())
        assertEquals(
            listOf(
                *(-1).toBytes().toTypedArray(), // Free table start pointer
                *(-1).toBytes().toTypedArray(), // Free table end pointer
                *(95).toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(40).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *Array(56) { 0 } // unused bytes
            ),
            it.memory.toList()
        )

        it.freeTable.create(10)
        assertEquals(
            listOf(
                *(40).toBytes().toTypedArray(), // Free table start pointer
                *(40).toBytes().toTypedArray(), // Free table end pointer
                *(95).toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(60).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *Array(36) { 0 } // unused bytes
            ),
            it.memory.toList()
        )

        it.freeTable.create(8)

        assertEquals(
            listOf(
                *(60).toBytes().toTypedArray(), // Free table start pointer
                *(40).toBytes().toTypedArray(), // Free table end pointer
                *(95).toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(80).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(60).toBytes().toTypedArray(), // The previous free table entry

                *8.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(40).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *Array(16) { 0 } // unused bytes

            ),
            it.memory.toList()
        )
    }

    @Test
    fun testCreateFreeTable4() {
        val it = Instance(128, byteArrayOf())
        assertEquals(
            listOf(
                *(-1).toBytes().toTypedArray(), // Free table start pointer
                *(-1).toBytes().toTypedArray(), // Free table end pointer
                *127.toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(40).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *Array(88) { 0 } // unused bytes
            ),
            it.memory.toList()
        )

        it.freeTable.create(10)
        assertEquals(
            listOf(
                *(40).toBytes().toTypedArray(), // Free table start pointer
                *(40).toBytes().toTypedArray(), // Free table end pointer
                *127.toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(60).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *Array(68) { 0 } // unused bytes
            ),
            it.memory.toList()
        )

        it.freeTable.create(8)
        assertEquals(
            listOf(
                *(60).toBytes().toTypedArray(), // Free table start pointer
                *(40).toBytes().toTypedArray(), // Free table end pointer
                *127.toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(80).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(60).toBytes().toTypedArray(), // The previous free table entry

                *8.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(40).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *Array(48) { 0 } // unused bytes

            ),
            it.memory.toList()
        )

        it.freeTable.create(12)
        assertEquals(
            listOf(
                *(60).toBytes().toTypedArray(), // Free table start pointer
                *(80).toBytes().toTypedArray(), // Free table end pointer
                *127.toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(100).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(80).toBytes().toTypedArray(), // The next free table entry
                *(60).toBytes().toTypedArray(), // The previous free table entry

                *8.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(40).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *12.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(40).toBytes().toTypedArray(), // The previous free table entry

                *Array(28) { 0 } // unused bytes

            ),
            it.memory.toList()
        )
    }

    @Test
    fun testFindFreeTableWithSize() {
        val it = Instance(96, byteArrayOf())
        assertEquals(
            listOf(
                *(-1).toBytes().toTypedArray(), // Free table start pointer
                *(-1).toBytes().toTypedArray(), // Free table end pointer
                *95.toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(40).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *Array(56) { 0 } // unused bytes
            ),
            it.memory.toList()
        )
        it.freeTable.create(10)

        assertEquals(
            listOf(
                *(40).toBytes().toTypedArray(), // Free table start pointer
                *(40).toBytes().toTypedArray(), // Free table end pointer
                *(95).toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(60).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *Array(36) { 0 } // unused bytes
            ),
            it.memory.toList()
        )

        assertEquals(40, it.freeTable.findWithSize(10))
    }

    @Test
    fun testFindFreeTableWithSize2() {
        val it = Instance(96, byteArrayOf())
        assertEquals(
            listOf(
                *(-1).toBytes().toTypedArray(), // Free table start pointer
                *(-1).toBytes().toTypedArray(), // Free table end pointer
                *(95).toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(40).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *Array(56) { 0 } // unused bytes
            ),
            it.memory.toList()
        )

        it.freeTable.create(10)
        assertEquals(
            listOf(
                *(40).toBytes().toTypedArray(), // Free table start pointer
                *(40).toBytes().toTypedArray(), // Free table end pointer
                *(95).toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(60).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *Array(36) { 0 } // unused bytes

            ),
            it.memory.toList()
        )

        it.freeTable.create(12)
        assertEquals(
            listOf(
                *(40).toBytes().toTypedArray(), // Free table start pointer
                *(60).toBytes().toTypedArray(), // Free table end pointer
                *(95).toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(80).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(60).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *12.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(40).toBytes().toTypedArray(), // The previous free table entry

                *Array(16) { 0 } // unused bytes

            ),
            it.memory.toList()
        )

        assertEquals(40, it.freeTable.findWithSize(10))
        assertEquals(60, it.freeTable.findWithSize(12))
    }

    @Test
    fun testFindFreeTableWithSize3() {
        val it = Instance(96, byteArrayOf())
        assertEquals(
            listOf(
                *(-1).toBytes().toTypedArray(), // Free table start pointer
                *(-1).toBytes().toTypedArray(), // Free table end pointer
                *(95).toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(40).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *Array(56) { 0 } // unused bytes
            ),
            it.memory.toList()
        )

        it.freeTable.create(10)
        assertEquals(
            listOf(
                *(40).toBytes().toTypedArray(), // Free table start pointer
                *(40).toBytes().toTypedArray(), // Free table end pointer
                *(95).toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(60).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *Array(36) { 0 } // unused bytes
            ),
            it.memory.toList()
        )

        it.freeTable.create(8)
        assertEquals(
            listOf(
                *(60).toBytes().toTypedArray(), // Free table start pointer
                *(40).toBytes().toTypedArray(), // Free table end pointer
                *(95).toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(80).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(60).toBytes().toTypedArray(), // The previous free table entry

                *8.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(40).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *Array(16) { 0 } // unused bytes

            ),
            it.memory.toList()
        )

        assertEquals(40, it.freeTable.findWithSize(10))
        assertEquals(60, it.freeTable.findWithSize(8))
    }

    @Test
    fun testFindFreeTableWithSize4() {
        val it = Instance(128, byteArrayOf())
        assertEquals(
            listOf(
                *(-1).toBytes().toTypedArray(), // Free table start pointer
                *(-1).toBytes().toTypedArray(), // Free table end pointer
                *127.toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(40).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *Array(88) { 0 } // unused bytes
            ),
            it.memory.toList()
        )

        it.freeTable.create(10)
        assertEquals(
            listOf(
                *(40).toBytes().toTypedArray(), // Free table start pointer
                *(40).toBytes().toTypedArray(), // Free table end pointer
                *(127).toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(60).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *Array(68) { 0 } // unused bytes
            ),
            it.memory.toList()
        )

        it.freeTable.create(8)
        assertEquals(
            listOf(
                *(60).toBytes().toTypedArray(), // Free table start pointer
                *(40).toBytes().toTypedArray(), // Free table end pointer
                *127.toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(80).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(60).toBytes().toTypedArray(), // The previous free table entry

                *8.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(40).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *Array(48) { 0 } // unused bytes

            ),
            it.memory.toList()
        )

        it.freeTable.create(12)
        assertEquals(
            listOf(
                *(60).toBytes().toTypedArray(), // Free table start pointer
                *(80).toBytes().toTypedArray(), // Free table end pointer
                *127.toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(100).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(80).toBytes().toTypedArray(), // The next free table entry
                *(60).toBytes().toTypedArray(), // The previous free table entry

                *8.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(40).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *12.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(40).toBytes().toTypedArray(), // The previous free table entry

                *Array(28) { 0 } // unused bytes

            ),
            it.memory.toList()
        )

        assertEquals(40, it.freeTable.findWithSize(10))
        assertEquals(60, it.freeTable.findWithSize(8))
        assertEquals(80, it.freeTable.findWithSize(12))
    }

    @Test
    fun testFindClosestFreeTableBelow() {
        val it = Instance(128, byteArrayOf())
        val p10 = it.freeTable.create(10) // Position: 24
        val p8 = it.freeTable.create(8) // Position: 44
        val p12 = it.freeTable.create(12) // Position: 64

        assertEquals(p10, it.freeTable.findClosestBelow(10))
        assertEquals(p8, it.freeTable.findClosestBelow(8))
        assertEquals(p12, it.freeTable.findClosestBelow(12))
        assertEquals(p12, it.freeTable.findClosestBelow(13))
        assertEquals(p12, it.freeTable.findClosestBelow(14))
        assertEquals(p10, it.freeTable.findClosestBelow(11))
        assertEquals(p8, it.freeTable.findClosestBelow(9))
        assertEquals(-1, it.freeTable.findClosestBelow(7))
    }

    @Test
    fun testFindBestAboveMatch() {
        val it = Instance(128, byteArrayOf())
        val p10 = it.freeTable.create(10) // Position: 24
        val p8 = it.freeTable.create(8) // Position: 44
        val p12 = it.freeTable.create(12) // Position: 64

        assertEquals(p10, it.freeTable.findBestAbove(10))
        assertEquals(p8, it.freeTable.findBestAbove(8))
        assertEquals(p12, it.freeTable.findBestAbove(12))
        assertEquals(-1, it.freeTable.findBestAbove(13))
        assertEquals(-1, it.freeTable.findBestAbove(14))
        assertEquals(-1, it.freeTable.findBestAbove(11))
        assertEquals(-1, it.freeTable.findBestAbove(9))
        assertEquals(p12, it.freeTable.findBestAbove(7))
        assertEquals(p10, it.freeTable.findBestAbove(6))
        assertEquals(p10, it.freeTable.findBestAbove(5))
        assertEquals(p8, it.freeTable.findBestAbove(4))
    }

    @Test
    fun testGetFreeTable() {
        val it = Instance(64, byteArrayOf())
        assertEquals(
            listOf(
                *(-1).toBytes().toTypedArray(), // Free table start pointer
                *(-1).toBytes().toTypedArray(), // Free table end pointer
                *63.toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(40).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *Array(24) { 0 } // unused bytes
            ),
            it.memory.toList()
        )
        assertEquals(40, it.freeTable.getWithSize(10))

        assertEquals(
            listOf(
                *(40).toBytes().toTypedArray(), // Free table start pointer
                *(40).toBytes().toTypedArray(), // Free table end pointer
                *63.toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(60).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *Array(4) { 0 } // unused bytes
            ),
            it.memory.toList()
        )
        assertEquals(40, it.freeTable.getWithSize(10))

        assertEquals(
            listOf(
                *(40).toBytes().toTypedArray(), // Free table start pointer
                *(40).toBytes().toTypedArray(), // Free table end pointer
                *63.toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(60).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *Array(4) { 0 } // unused bytes
            ),
            it.memory.toList()
        )
    }

    @Test
    fun testGetFreeTable2() {
        val it = Instance(96, byteArrayOf())
        assertEquals(
            listOf(
                *(-1).toBytes().toTypedArray(), // Free table start pointer
                *(-1).toBytes().toTypedArray(), // Free table end pointer
                *(95).toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(40).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *Array(56) { 0 } // unused bytes
            ),
            it.memory.toList()
        )

        assertEquals(40, it.freeTable.getWithSize(10))
        assertEquals(
            listOf(
                *(40).toBytes().toTypedArray(), // Free table start pointer
                *(40).toBytes().toTypedArray(), // Free table end pointer
                *(95).toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(60).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *Array(36) { 0 } // unused bytes

            ),
            it.memory.toList()
        )

        assertEquals(40, it.freeTable.getWithSize(10))
        assertEquals(60, it.freeTable.getWithSize(12))

        assertEquals(
            listOf(
                *(40).toBytes().toTypedArray(), // Free table start pointer
                *(60).toBytes().toTypedArray(), // Free table end pointer
                *(95).toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(80).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(60).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *12.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(40).toBytes().toTypedArray(), // The previous free table entry

                *Array(16) { 0 } // unused bytes

            ),
            it.memory.toList()
        )

        assertEquals(40, it.freeTable.getWithSize(10))
        assertEquals(60, it.freeTable.getWithSize(12))

        assertEquals(
            listOf(
                *(40).toBytes().toTypedArray(), // Free table start pointer
                *(60).toBytes().toTypedArray(), // Free table end pointer
                *(95).toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(80).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(60).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *12.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(40).toBytes().toTypedArray(), // The previous free table entry

                *Array(16) { 0 } // unused bytes

            ),
            it.memory.toList()
        )
    }

    @Test
    fun testGetFreeTable3() {
        val it = Instance(96, byteArrayOf())
        assertEquals(
            listOf(
                *(-1).toBytes().toTypedArray(), // Free table start pointer
                *(-1).toBytes().toTypedArray(), // Free table end pointer
                *(95).toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(40).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *Array(56) { 0 } // unused bytes
            ),
            it.memory.toList()
        )

        assertEquals(40, it.freeTable.getWithSize(10))

        assertEquals(
            listOf(
                *(40).toBytes().toTypedArray(), // Free table start pointer
                *(40).toBytes().toTypedArray(), // Free table end pointer
                *(95).toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(60).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *Array(36) { 0 } // unused bytes
            ),
            it.memory.toList()
        )

        assertEquals(40, it.freeTable.getWithSize(10))
        assertEquals(60, it.freeTable.getWithSize(8))

        assertEquals(
            listOf(
                *(60).toBytes().toTypedArray(), // Free table start pointer
                *(40).toBytes().toTypedArray(), // Free table end pointer
                *(95).toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(80).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(60).toBytes().toTypedArray(), // The previous free table entry

                *8.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(40).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *Array(16) { 0 } // unused bytes

            ),
            it.memory.toList()
        )

        assertEquals(40, it.freeTable.getWithSize(10))
        assertEquals(60, it.freeTable.getWithSize(8))
    }

    @Test
    fun testGetFreeTable4() {
        val it = Instance(128, byteArrayOf())
        assertEquals(
            listOf(
                *(-1).toBytes().toTypedArray(), // Free table start pointer
                *(-1).toBytes().toTypedArray(), // Free table end pointer
                *127.toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(40).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *Array(88) { 0 } // unused bytes
            ),
            it.memory.toList()
        )

        assertEquals(40, it.freeTable.getWithSize(10))

        assertEquals(
            listOf(
                *(40).toBytes().toTypedArray(), // Free table start pointer
                *(40).toBytes().toTypedArray(), // Free table end pointer
                *127.toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(60).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *Array(68) { 0 } // unused bytes
            ),
            it.memory.toList()
        )

        assertEquals(40, it.freeTable.getWithSize(10))
        assertEquals(60, it.freeTable.getWithSize(8))

        assertEquals(
            listOf(
                *(60).toBytes().toTypedArray(), // Free table start pointer
                *(40).toBytes().toTypedArray(), // Free table end pointer
                *127.toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(80).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(60).toBytes().toTypedArray(), // The previous free table entry

                *8.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(40).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *Array(48) { 0 } // unused bytes

            ),
            it.memory.toList()
        )

        assertEquals(40, it.freeTable.getWithSize(10))
        assertEquals(60, it.freeTable.getWithSize(8))
        assertEquals(80, it.freeTable.getWithSize(12))

        assertEquals(
            listOf(
                *(60).toBytes().toTypedArray(), // Free table start pointer
                *(80).toBytes().toTypedArray(), // Free table end pointer
                *127.toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(100).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(80).toBytes().toTypedArray(), // The next free table entry
                *(60).toBytes().toTypedArray(), // The previous free table entry

                *8.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(40).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *12.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(40).toBytes().toTypedArray(), // The previous free table entry

                *Array(28) { 0 } // unused bytes

            ),
            it.memory.toList()
        )

        assertEquals(40, it.freeTable.getWithSize(10))
        assertEquals(60, it.freeTable.getWithSize(8))
        assertEquals(80, it.freeTable.getWithSize(12))

        assertEquals(
            listOf(
                *(60).toBytes().toTypedArray(), // Free table start pointer
                *(80).toBytes().toTypedArray(), // Free table end pointer
                *127.toBytes().toTypedArray(), // Stack pointer
                *(-1).toBytes().toTypedArray(), // Local variables stack pointer
                *(32).toBytes().toTypedArray(), // Instruction pointer
                *(100).toBytes().toTypedArray(), // Global stack pointer
                *(0).toBytes().toTypedArray(), // Unused memory location
                *(0).toBytes().toTypedArray(), // Unused memory location

                Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
                Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

                *10.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(80).toBytes().toTypedArray(), // The next free table entry
                *(60).toBytes().toTypedArray(), // The previous free table entry

                *8.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(40).toBytes().toTypedArray(), // The next free table entry
                *(-1).toBytes().toTypedArray(), // The previous free table entry

                *12.toBytes().toTypedArray(), // Free table entry size
                *(-1).toBytes().toTypedArray(), // The first element of this free table entry
                *(-1).toBytes().toTypedArray(), // The last element of this free table entry
                *(-1).toBytes().toTypedArray(), // The next free table entry
                *(40).toBytes().toTypedArray(), // The previous free table entry

                *Array(28) { 0 } // unused bytes

            ),
            it.memory.toList()
        )
    }
}
