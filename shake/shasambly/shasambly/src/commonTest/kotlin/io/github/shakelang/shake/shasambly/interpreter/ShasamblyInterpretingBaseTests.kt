package io.github.shakelang.shake.shasambly.interpreter

import io.github.shakelang.parseutils.bytes.toBytes
import io.github.shakelang.shake.shasambly.interpreter.natives.Natives
import kotlin.test.Test
import kotlin.test.assertEquals

class ShasamblyInterpretingBaseTests {

    class Instance(memorySize: Int, bytes: ByteArray) : ShasamblyInterpretingBase(memorySize, bytes, 0)

    @Test
    fun basicTest() {
        val it = Instance(32, byteArrayOf(0x00, 0x01, 0x02, 0x03, 0x04, 0x05))
        assertEquals(listOf(
            *(-1).toBytes().toTypedArray(), // Free table start pointer
            *(-1).toBytes().toTypedArray(), // Free table end pointer
            *31.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            0, 1, 2, 3, 4, 5, // Given instruction bytes

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *Array(2) { 0 } // two unused bytes
        ), it.memory.toList())
    }

    @Test
    fun testIncreaseGlobals() {
        val it = Instance(32, byteArrayOf())
        assertEquals(24, it.increaseGlobals(4))
        assertEquals(listOf(
            *(-1).toBytes().toTypedArray(), // Free table start pointer
            *(-1).toBytes().toTypedArray(), // Free table end pointer
            *31.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *Array(8) { 0 } // eight unused bytes
        ), it.memory.toList())
    }

    @Test
    fun testIncreaseGlobals2() {
        val it = Instance(32, byteArrayOf())
        assertEquals(24, it.increaseGlobals(4))
        assertEquals(28, it.increaseGlobals(4))
        assertEquals(listOf(
            *(-1).toBytes().toTypedArray(), // Free table start pointer
            *(-1).toBytes().toTypedArray(), // Free table end pointer
            *31.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *Array(8) { 0 } // eight unused bytes
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

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *Array(40) { 0 } // unused bytes
        ), it.memory.toList())
        it.createFreeTable(10)

        assertEquals(listOf(
            *(24).toBytes().toTypedArray(), // Free table start pointer
            *(24).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

            *Array(20) { 0 } // unused bytes
        ), it.memory.toList())
    }

    @Test
    fun testCreateFreeTable2() {
        val it = Instance(64, byteArrayOf())
        assertEquals(listOf(
            *(-1).toBytes().toTypedArray(), // Free table start pointer
            *(-1).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *Array(40) { 0 } // unused bytes
        ), it.memory.toList())

        it.createFreeTable(10)
        assertEquals(listOf(
            *(24).toBytes().toTypedArray(), // Free table start pointer
            *(24).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

            *Array(20) { 0 } // unused bytes

        ), it.memory.toList())

        it.createFreeTable(12)
        assertEquals(listOf(
            *(24).toBytes().toTypedArray(), // Free table start pointer
            *(44).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(44).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

            *12.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(24).toBytes().toTypedArray(), // The previous free table entry

        ), it.memory.toList())


    }

    @Test
    fun testCreateFreeTable3() {
        val it = Instance(64, byteArrayOf())
        assertEquals(listOf(
            *(-1).toBytes().toTypedArray(), // Free table start pointer
            *(-1).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *Array(40) { 0 } // unused bytes
        ), it.memory.toList())

        it.createFreeTable(10)
        assertEquals(listOf(
            *(24).toBytes().toTypedArray(), // Free table start pointer
            *(24).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

            *Array(20) { 0 } // unused bytes
        ), it.memory.toList())

        it.createFreeTable(8)
        assertEquals(listOf(
            *(44).toBytes().toTypedArray(), // Free table start pointer
            *(24).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(44).toBytes().toTypedArray(), // The previous free table entry

            *8.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(24).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

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

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *Array(104) { 0 } // unused bytes
        ), it.memory.toList())

        it.createFreeTable(10)
        assertEquals(listOf(
            *(24).toBytes().toTypedArray(), // Free table start pointer
            *(24).toBytes().toTypedArray(), // Free table end pointer
            *127.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

            *Array(84) { 0 } // unused bytes
        ), it.memory.toList())

        it.createFreeTable(8)
        assertEquals(listOf(
            *(44).toBytes().toTypedArray(), // Free table start pointer
            *(24).toBytes().toTypedArray(), // Free table end pointer
            *127.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(44).toBytes().toTypedArray(), // The previous free table entry

            *8.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(24).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

            *Array(64) { 0 } // unused bytes

        ), it.memory.toList())

        it.createFreeTable(12)
        assertEquals(listOf(
            *(44).toBytes().toTypedArray(), // Free table start pointer
            *(64).toBytes().toTypedArray(), // Free table end pointer
            *127.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(64).toBytes().toTypedArray(), // The next free table entry
            *(44).toBytes().toTypedArray(), // The previous free table entry

            *8.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(24).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

            *12.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(24).toBytes().toTypedArray(), // The previous free table entry

            *Array(44) { 0 } // unused bytes

        ), it.memory.toList())
    }

    @Test
    fun testFindFreeTableWithSize() {
        val it = Instance(64, byteArrayOf())
        assertEquals(listOf(
            *(-1).toBytes().toTypedArray(), // Free table start pointer
            *(-1).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *Array(40) { 0 } // unused bytes
        ), it.memory.toList())
        it.createFreeTable(10)

        assertEquals(listOf(
            *(24).toBytes().toTypedArray(), // Free table start pointer
            *(24).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

            *Array(20) { 0 } // unused bytes
        ), it.memory.toList())

        assertEquals(24, it.findFreeTableWithSize(10))
    }

    @Test
    fun testFindFreeTableWithSize2() {
        val it = Instance(64, byteArrayOf())
        assertEquals(listOf(
            *(-1).toBytes().toTypedArray(), // Free table start pointer
            *(-1).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *Array(40) { 0 } // unused bytes
        ), it.memory.toList())

        it.createFreeTable(10)
        assertEquals(listOf(
            *(24).toBytes().toTypedArray(), // Free table start pointer
            *(24).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

            *Array(20) { 0 } // unused bytes

        ), it.memory.toList())

        it.createFreeTable(12)
        assertEquals(listOf(
            *(24).toBytes().toTypedArray(), // Free table start pointer
            *(44).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(44).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

            *12.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(24).toBytes().toTypedArray(), // The previous free table entry

        ), it.memory.toList())

        assertEquals(24, it.findFreeTableWithSize(10))
        assertEquals(44, it.findFreeTableWithSize(12))


    }

    @Test
    fun testFindFreeTableWithSize3() {
        val it = Instance(64, byteArrayOf())
        assertEquals(listOf(
            *(-1).toBytes().toTypedArray(), // Free table start pointer
            *(-1).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *Array(40) { 0 } // unused bytes
        ), it.memory.toList())

        it.createFreeTable(10)
        assertEquals(listOf(
            *(24).toBytes().toTypedArray(), // Free table start pointer
            *(24).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

            *Array(20) { 0 } // unused bytes
        ), it.memory.toList())

        it.createFreeTable(8)
        assertEquals(listOf(
            *(44).toBytes().toTypedArray(), // Free table start pointer
            *(24).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(44).toBytes().toTypedArray(), // The previous free table entry

            *8.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(24).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

        ), it.memory.toList())

        assertEquals(24, it.findFreeTableWithSize(10))
        assertEquals(44, it.findFreeTableWithSize(8))
    }

    @Test
    fun testFindFreeTableWithSize4() {
        val it = Instance(128, byteArrayOf())
        assertEquals(listOf(
            *(-1).toBytes().toTypedArray(), // Free table start pointer
            *(-1).toBytes().toTypedArray(), // Free table end pointer
            *127.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *Array(104) { 0 } // unused bytes
        ), it.memory.toList())

        it.createFreeTable(10)
        assertEquals(listOf(
            *(24).toBytes().toTypedArray(), // Free table start pointer
            *(24).toBytes().toTypedArray(), // Free table end pointer
            *127.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

            *Array(84) { 0 } // unused bytes
        ), it.memory.toList())

        it.createFreeTable(8)
        assertEquals(listOf(
            *(44).toBytes().toTypedArray(), // Free table start pointer
            *(24).toBytes().toTypedArray(), // Free table end pointer
            *127.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(44).toBytes().toTypedArray(), // The previous free table entry

            *8.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(24).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

            *Array(64) { 0 } // unused bytes

        ), it.memory.toList())

        it.createFreeTable(12)
        assertEquals(listOf(
            *(44).toBytes().toTypedArray(), // Free table start pointer
            *(64).toBytes().toTypedArray(), // Free table end pointer
            *127.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(64).toBytes().toTypedArray(), // The next free table entry
            *(44).toBytes().toTypedArray(), // The previous free table entry

            *8.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(24).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

            *12.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(24).toBytes().toTypedArray(), // The previous free table entry

            *Array(44) { 0 } // unused bytes

        ), it.memory.toList())

        assertEquals(24, it.findFreeTableWithSize(10))
        assertEquals(44, it.findFreeTableWithSize(8))
        assertEquals(64, it.findFreeTableWithSize(12))

    }

    @Test
    fun testGetFreeTable() {
        val it = Instance(64, byteArrayOf())
        assertEquals(listOf(
            *(-1).toBytes().toTypedArray(), // Free table start pointer
            *(-1).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *Array(40) { 0 } // unused bytes
        ), it.memory.toList())
        assertEquals(24, it.getFreeTable(10))

        assertEquals(listOf(
            *(24).toBytes().toTypedArray(), // Free table start pointer
            *(24).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

            *Array(20) { 0 } // unused bytes
        ), it.memory.toList())
        assertEquals(24, it.getFreeTable(10))

        assertEquals(listOf(
            *(24).toBytes().toTypedArray(), // Free table start pointer
            *(24).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

            *Array(20) { 0 } // unused bytes
        ), it.memory.toList())
    }

    @Test
    fun testGetFreeTable2() {
        val it = Instance(64, byteArrayOf())
        assertEquals(listOf(
            *(-1).toBytes().toTypedArray(), // Free table start pointer
            *(-1).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *Array(40) { 0 } // unused bytes
        ), it.memory.toList())

        assertEquals(24, it.getFreeTable(10))
        assertEquals(listOf(
            *(24).toBytes().toTypedArray(), // Free table start pointer
            *(24).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

            *Array(20) { 0 } // unused bytes

        ), it.memory.toList())

        assertEquals(44, it.getFreeTable(12))
        assertEquals(44, it.getFreeTable(12))
        assertEquals(listOf(
            *(24).toBytes().toTypedArray(), // Free table start pointer
            *(44).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(44).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

            *12.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(24).toBytes().toTypedArray(), // The previous free table entry

        ), it.memory.toList())


    }

    @Test
    fun testGetFreeTable3() {
        val it = Instance(64, byteArrayOf())
        assertEquals(listOf(
            *(-1).toBytes().toTypedArray(), // Free table start pointer
            *(-1).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *Array(40) { 0 } // unused bytes
        ), it.memory.toList())

        assertEquals(24, it.getFreeTable(10))
        assertEquals(24, it.getFreeTable(10))
        assertEquals(listOf(
            *(24).toBytes().toTypedArray(), // Free table start pointer
            *(24).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

            *Array(20) { 0 } // unused bytes
        ), it.memory.toList())

        assertEquals(44, it.getFreeTable(8))
        assertEquals(24, it.getFreeTable(10))
        assertEquals(44, it.getFreeTable(8))
        assertEquals(listOf(
            *(44).toBytes().toTypedArray(), // Free table start pointer
            *(24).toBytes().toTypedArray(), // Free table end pointer
            *63.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(44).toBytes().toTypedArray(), // The previous free table entry

            *8.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(24).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

        ), it.memory.toList())
    }

    @Test
    fun testGetFreeTable4() {
        val it = Instance(128, byteArrayOf())
        assertEquals(listOf(
            *(-1).toBytes().toTypedArray(), // Free table start pointer
            *(-1).toBytes().toTypedArray(), // Free table end pointer
            *127.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *Array(104) { 0 } // unused bytes
        ), it.memory.toList())

        assertEquals(24, it.getFreeTable(10))
        assertEquals(24, it.getFreeTable(10))
        assertEquals(listOf(
            *(24).toBytes().toTypedArray(), // Free table start pointer
            *(24).toBytes().toTypedArray(), // Free table end pointer
            *127.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

            *Array(84) { 0 } // unused bytes
        ), it.memory.toList())

        assertEquals(44, it.getFreeTable(8))
        assertEquals(44, it.getFreeTable(8))
        assertEquals(listOf(
            *(44).toBytes().toTypedArray(), // Free table start pointer
            *(24).toBytes().toTypedArray(), // Free table end pointer
            *127.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(44).toBytes().toTypedArray(), // The previous free table entry

            *8.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(24).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

            *Array(64) { 0 } // unused bytes

        ), it.memory.toList())

        assertEquals(64, it.getFreeTable(12))
        assertEquals(44, it.getFreeTable(8))
        assertEquals(64, it.getFreeTable(12))

        assertEquals(listOf(
            *(44).toBytes().toTypedArray(), // Free table start pointer
            *(64).toBytes().toTypedArray(), // Free table end pointer
            *127.toBytes().toTypedArray(), // Stack pointer
            *(-1).toBytes().toTypedArray(), // Local variables stack pointer

            Opcodes.I_PUSH, *(0).toBytes().toTypedArray(), // Push 0 to stack (the return code of the program)
            Opcodes.INVOKE_NATIVE, *Natives.exit.toBytes().toTypedArray(), // Invoke native exit

            *10.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(64).toBytes().toTypedArray(), // The next free table entry
            *(44).toBytes().toTypedArray(), // The previous free table entry

            *8.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(24).toBytes().toTypedArray(), // The next free table entry
            *(-1).toBytes().toTypedArray(), // The previous free table entry

            *12.toBytes().toTypedArray(), // Free table entry size
            *(-1).toBytes().toTypedArray(), // The first element of this free table entry
            *(-1).toBytes().toTypedArray(), // The last element of this free table entry
            *(-1).toBytes().toTypedArray(), // The next free table entry
            *(24).toBytes().toTypedArray(), // The previous free table entry

            *Array(44) { 0 } // unused bytes

        ), it.memory.toList())
    }
}