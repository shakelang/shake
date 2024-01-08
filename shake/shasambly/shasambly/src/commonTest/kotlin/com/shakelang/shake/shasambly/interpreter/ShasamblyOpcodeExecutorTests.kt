package com.shakelang.shake.shasambly.interpreter

import com.shakelang.util.primitives.bytes.toBytes
import kotlin.test.Test
import kotlin.test.assertEquals

class ShasamblyOpcodeExecutorTests {

    class Instance(
        memorySize: Int,
        bytes: ByteArray,
        position: Int = 0,
    ) : ShasamblyOpcodeExecutor(memorySize, bytes, position)

    @Test
    fun testIncreaseLocalStack() {
        val it = Instance(64, 20.toShort().toBytes())

        println(it.memory.toList())

        it.incr_variable_stack()
        println(it.globalsSize)

        assertEquals(
            byteArrayOf(

                *(-1).toBytes(), // Free table start pointer
                *(-1).toBytes(), // Free table end pointer
                *63.toBytes(), // Stack pointer
                *(50).toBytes(), // Local variables stack pointer

                *(34).toBytes(), // Instruction pointer
                *(70).toBytes(), // Global stack pointer
                *(0).toBytes(), // Unused memory location
                *(0).toBytes(), // Unused memory location

                0, 20, // Short 20
                16, 0, 0, 0, 0, 4, // Default generated instruction to end the program
                0, 21, -1, -1, -1, -1, 0, 0, 0, 20,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,

                ).toList(),
            it.memory.toList(),
        )
    }
}
