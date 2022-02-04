package io.github.shakelang.shake.shasambly.interpreter

import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class ShasamblyInterpretingBaseTests {

    class Instance(memorySize: Int, bytes: ByteArray) : ShasamblyInterpretingBase(memorySize, bytes, 0)

    @Ignore
    @Test
    fun basicTest() {
        val it = Instance(16, byteArrayOf(0x00, 0x01, 0x02, 0x03, 0x04, 0x05))
        assertEquals(listOf<Byte>(
            0, 0, 0, 0, 0, // Default return code
            1, 2, 3, 4, 5, // Argument bytes
            3, 0, 0, 0, 0,
            0, 0, 0, 0, // Free-Pointer
        0), it.memory.toList())
    }

    @Ignore
    @Test
    fun testIncreaseGlobals() {
        val it = Instance(16, byteArrayOf())
        assertEquals(8, it.increaseGlobals(4))
        assertEquals(listOf<Byte>(
            0, 0, 0, 0, // Default return code
            3,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        ), it.memory.toList())
        //assert
    }


}