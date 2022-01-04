@file:Suppress("unused")
package io.github.shakelang.shake.shasambly.interpreter.natives

import io.github.shakelang.shake.shasambly.interpreter.*

val nativeFunctions = arrayOfNulls<Pair<String, ShasamblyInterpreter.() -> Unit>>((UShort.MAX_VALUE).toInt() + 1)

private fun nativeFunction(name: String, id: Int, execute: ShasamblyInterpreter.() -> Unit): Short {
    nativeFunctions[id] = name to execute
    return id.toShort()
}

object Natives {
    val printByte = nativeFunction("print_byte", 0x01) {
        val v = stack.removeLastByte()
        print(v)
    }
    val printShort = nativeFunction("print_short", 0x02) {
        val v = stack.removeLastShort()
        print(v)
    }
    val printInt = nativeFunction("print_int", 0x03) {
        val v = stack.removeLastInt()
        print(v)
    }
    val printLong = nativeFunction("print_long", 0x04) {
        val v = stack.removeLastLong()
        print(v)
    }
    val printFloat = nativeFunction("print_float", 0x05) {
        val v = stack.removeLastFloat()
        print(v)
    }
    val printDouble = nativeFunction("print_double", 0x06) {
        val v = stack.removeLastDouble()
        print(v)
    }
    val printUtf8 = nativeFunction("print_utf8", 0x07) {
        val v = stack.removeLastByte().toInt().toChar()
        print(v)
    }
    val printLineEnding = nativeFunction("print_line_ending", 0x08) {
        println()
    }
    val declareGlobal = nativeFunction("declare_global", 0x09) {
        val size = read_int()
        val addr = declareGlobal(size)
        stack.addInt(addr)
    }
    val declareGlobalDynamic = nativeFunction("declare_global_dynamic", 0x0a) {
        val size = stack.removeLastInt()
        val addr = declareGlobal(size)
        stack.addInt(addr)
    }
    val freeGlobal = nativeFunction("free_global", 0x0b) {
        val addr = stack.removeLastInt()
        val size = read_int()
        free(addr, size)
    }
    val freeGlobalDynamic = nativeFunction("free_global_dynamic", 0x0c) {
        val addr = stack.removeLastInt()
        val size = stack.removeLastInt()
        free(addr, size)
    }
}