package com.shakelang.shake.shasambly.interpreter

import kotlin.test.Ignore
import kotlin.test.Test

class ShasamblyInterpreterTests {

    @Ignore
    @Test
    fun testOperationIncrStack() {
        val interpreter = ShasamblyInterpreter(
            1024,
            byteArrayOf(
                Opcodes.INCR_STACK,
                0,
                0x64
            ),
            0
        )

        interpreter.finish()
    }

    @Ignore
    @Test
    fun testOperationDecrStack() {
        val interpreter = ShasamblyInterpreter(
            1024,
            byteArrayOf(
                Opcodes.INCR_STACK,
                0,
                0x64,
                Opcodes.DECR_STACK
            ),
            0
        )

        interpreter.finish()
    }
}
