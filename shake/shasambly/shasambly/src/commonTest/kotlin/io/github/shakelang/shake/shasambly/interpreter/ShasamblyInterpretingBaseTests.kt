package io.github.shakelang.shake.shasambly.interpreter

import io.github.shakelang.parseutils.bytes.toBytes
import io.github.shakelang.shake.shasambly.interpreter.natives.Natives
import kotlin.test.Test
import kotlin.test.assertEquals

class ShasamblyInterpretingBaseTests {

    class Instance(memorySize: Int, bytes: ByteArray = byteArrayOf()) : ShasamblyInterpretingBase(memorySize, bytes, 0)

    @Test
    fun basicTest() {
        val it = Instance(64, byteArrayOf(0x00, 0x01, 0x02, 0x03, 0x04, 0x05))
        assertEquals(listOf(
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
        ), it.memory.toList())
    }

    @Test
    fun testIncreaseGlobals() {
        val it = Instance(64, byteArrayOf())
        assertEquals(40, it.increaseGlobals(4))
        assertEquals(listOf(
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
        ), it.memory.toList())
    }

    @Test
    fun testIncreaseGlobals2() {
        val it = Instance(64, byteArrayOf())
        assertEquals(40, it.increaseGlobals(4))
        assertEquals(44, it.increaseGlobals(4))
        assertEquals(listOf(
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
        ), it.memory.toList())
    }

    @Test
    fun testCreateFreeTable() {
        val it = Instance(64, byteArrayOf())
        assertEquals(listOf(
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
        ), it.memory.toList())
        it.createFreeTable(10)

        assertEquals(listOf(
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
        ), it.memory.toList())
    }

    @Test
    fun testCreateFreeTable2() {
        val it = Instance(96, byteArrayOf())
        assertEquals(listOf(
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

            *Array( 56) { 0 } // unused bytes
        ), it.memory.toList())

        it.createFreeTable(10)
        assertEquals(listOf(
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

        ), it.memory.toList())

        it.createFreeTable(12)
        assertEquals(listOf(
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

        ), it.memory.toList())

    }

    @Test
    fun testCreateFreeTable3() {
        val it = Instance(96, byteArrayOf())
        assertEquals(listOf(
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
        ), it.memory.toList())

        it.createFreeTable(10)
        assertEquals(listOf(
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
        ), it.memory.toList())

        it.createFreeTable(8)

        assertEquals(listOf(
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

        ), it.memory.toList())
    }

    @Test
    fun testCreateFreeTable4() {
        val it = Instance(128, byteArrayOf())
        assertEquals(listOf(
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
        ), it.memory.toList())

        it.createFreeTable(10)
        assertEquals(listOf(
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
        ), it.memory.toList())

        it.createFreeTable(8)
        assertEquals(listOf(
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

        ), it.memory.toList())

        it.createFreeTable(12)
        assertEquals(listOf(
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

        ), it.memory.toList())
    }

    @Test
    fun testFindFreeTableWithSize() {
        val it = Instance(96, byteArrayOf())
        assertEquals(listOf(
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
        ), it.memory.toList())
        it.createFreeTable(10)

        assertEquals(listOf(
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
        ), it.memory.toList())

        assertEquals(40, it.findFreeTableWithSize(10))
    }

    @Test
    fun testFindFreeTableWithSize2() {
        val it = Instance(96, byteArrayOf())
        assertEquals(listOf(
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
        ), it.memory.toList())

        it.createFreeTable(10)
        assertEquals(listOf(
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

        ), it.memory.toList())

        it.createFreeTable(12)
        assertEquals(listOf(
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

        ), it.memory.toList())

        assertEquals(40, it.findFreeTableWithSize(10))
        assertEquals(60, it.findFreeTableWithSize(12))


    }

    @Test
    fun testFindFreeTableWithSize3() {
        val it = Instance(96, byteArrayOf())
        assertEquals(listOf(
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
        ), it.memory.toList())

        it.createFreeTable(10)
        assertEquals(listOf(
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
        ), it.memory.toList())

        it.createFreeTable(8)
        assertEquals(listOf(
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

        ), it.memory.toList())

        assertEquals(40, it.findFreeTableWithSize(10))
        assertEquals(60, it.findFreeTableWithSize(8))
    }

    @Test
    fun testFindFreeTableWithSize4() {
        val it = Instance(128, byteArrayOf())
        assertEquals(listOf(
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
        ), it.memory.toList())

        it.createFreeTable(10)
        assertEquals(listOf(
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
        ), it.memory.toList())

        it.createFreeTable(8)
        assertEquals(listOf(
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

        ), it.memory.toList())

        it.createFreeTable(12)
        assertEquals(listOf(
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

        ), it.memory.toList())

        assertEquals(40, it.findFreeTableWithSize(10))
        assertEquals(60, it.findFreeTableWithSize(8))
        assertEquals(80, it.findFreeTableWithSize(12))

    }

    @Test
    fun testFindClosestFreeTableBelow() {
        val it = Instance(128, byteArrayOf())
        val p10 = it.createFreeTable(10) // Position: 24
        val p8 = it.createFreeTable(8) // Position: 44
        val p12 = it.createFreeTable(12) // Position: 64

        assertEquals(p10, it.findClosestFreeTableBelow(10))
        assertEquals(p8, it.findClosestFreeTableBelow(8))
        assertEquals(p12, it.findClosestFreeTableBelow(12))
        assertEquals(p12, it.findClosestFreeTableBelow(13))
        assertEquals(p12, it.findClosestFreeTableBelow(14))
        assertEquals(p10, it.findClosestFreeTableBelow(11))
        assertEquals(p8, it.findClosestFreeTableBelow(9))
        assertEquals(-1, it.findClosestFreeTableBelow(7))
    }

    @Test
    fun testFindBestAboveMatch() {
        val it = Instance(128, byteArrayOf())
        val p10 = it.createFreeTable(10) // Position: 24
        val p8 = it.createFreeTable(8) // Position: 44
        val p12 = it.createFreeTable(12) // Position: 64

        assertEquals(p10, it.findBestAboveMatch(10))
        assertEquals(p8, it.findBestAboveMatch(8))
        assertEquals(p12, it.findBestAboveMatch(12))
        assertEquals(-1, it.findBestAboveMatch(13))
        assertEquals(-1, it.findBestAboveMatch(14))
        assertEquals(-1, it.findBestAboveMatch(11))
        assertEquals(-1, it.findBestAboveMatch(9))
        assertEquals(p12, it.findBestAboveMatch(7))
        assertEquals(p10, it.findBestAboveMatch(6))
        assertEquals(p10, it.findBestAboveMatch(5))
        assertEquals(p8, it.findBestAboveMatch(4))

    }

    @Test
    fun testGetFreeTable() {
        val it = Instance(64, byteArrayOf())
        assertEquals(listOf(
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
        ), it.memory.toList())
        assertEquals(40, it.getFreeTable(10))

        assertEquals(listOf(
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
        ), it.memory.toList())
        assertEquals(40, it.getFreeTable(10))

        assertEquals(listOf(
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
        ), it.memory.toList())
    }

    @Test
    fun testGetFreeTable2() {
        val it = Instance(96, byteArrayOf())
        assertEquals(listOf(
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

            *Array( 56) { 0 } // unused bytes
        ), it.memory.toList())

        assertEquals(40, it.getFreeTable(10))
        assertEquals(listOf(
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

        ), it.memory.toList())

        assertEquals(40, it.getFreeTable(10))
        assertEquals(60, it.getFreeTable(12))

        assertEquals(listOf(
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

        ), it.memory.toList())

        assertEquals(40, it.getFreeTable(10))
        assertEquals(60, it.getFreeTable(12))

        assertEquals(listOf(
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

        ), it.memory.toList())


    }

    @Test
    fun testGetFreeTable3() {
        val it = Instance(96, byteArrayOf())
        assertEquals(listOf(
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
        ), it.memory.toList())

        assertEquals(40, it.getFreeTable(10))

        assertEquals(listOf(
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
        ), it.memory.toList())

        assertEquals(40, it.getFreeTable(10))
        assertEquals(60, it.getFreeTable(8))

        assertEquals(listOf(
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

        ), it.memory.toList())

        assertEquals(40, it.getFreeTable(10))
        assertEquals(60, it.getFreeTable(8))
    }

    @Test
    fun testGetFreeTable4() {
        val it = Instance(128, byteArrayOf())
        assertEquals(listOf(
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
        ), it.memory.toList())

        assertEquals(40, it.getFreeTable(10))

        assertEquals(listOf(
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
        ), it.memory.toList())


        assertEquals(40, it.getFreeTable(10))
        assertEquals(60, it.getFreeTable(8))

        assertEquals(listOf(
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

        ), it.memory.toList())


        assertEquals(40, it.getFreeTable(10))
        assertEquals(60, it.getFreeTable(8))
        assertEquals(80, it.getFreeTable(12))

        assertEquals(listOf(
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

        ), it.memory.toList())


        assertEquals(40, it.getFreeTable(10))
        assertEquals(60, it.getFreeTable(8))
        assertEquals(80, it.getFreeTable(12))

        assertEquals(listOf(
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

        ), it.memory.toList())
    }
}