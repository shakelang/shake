@file:Suppress("unused")
package io.github.shakelang.shake.shasambly.interpreter.natives

import io.github.shakelang.shake.shasambly.interpreter.*

val nativeFunctions = arrayOfNulls<Pair<String, (ShasamblyInterpreter) -> Unit>>((UShort.MAX_VALUE).toInt() + 1)

private fun nativeFunction(name: String, id: Int, execute: (ShasamblyInterpreter) -> Unit): Short {
    nativeFunctions[id] = name to execute
    return id.toShort()
}

object Natives {
    val printByte = nativeFunction("print_byte", 0x01) {
        val v = it.stack.removeLastByte()
        print(v)
    }
    val printShort = nativeFunction("print_short", 0x02) {
        val v = it.stack.removeLastShort()
        print(v)
    }
    val printInt = nativeFunction("print_int", 0x03) {
        val v = it.stack.removeLastInt()
        print(v)
    }
    val printLong = nativeFunction("print_long", 0x04) {
        val v = it.stack.removeLastLong()
        print(v)
    }
    val printFloat = nativeFunction("print_float", 0x05) {
        val v = it.stack.removeLastFloat()
        print(v)
    }
    val printDouble = nativeFunction("print_double", 0x06) {
        val v = it.stack.removeLastDouble()
        print(v)
    }
    val printLineEnding = nativeFunction("print_line_ending", 0x07) {
        println()
    }
}