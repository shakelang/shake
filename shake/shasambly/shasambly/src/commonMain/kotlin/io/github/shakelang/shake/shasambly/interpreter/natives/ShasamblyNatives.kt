@file:Suppress("unused")
package io.github.shakelang.shake.shasambly.interpreter.natives

import io.github.shakelang.primitives.bytes.getByte
import io.github.shakelang.primitives.bytes.getInt
import io.github.shakelang.primitives.bytes.getLong
import io.github.shakelang.primitives.bytes.getShort
import io.github.shakelang.shake.shasambly.interpreter.ShasamblyOpcodeExecutor

val nativeFunctions = arrayOfNulls<ShasamblyNative>((UShort.MAX_VALUE).toInt() + 1)

private fun nativeFunction(name: String, id: Int, execute: ShasamblyOpcodeExecutor.() -> Unit): Short {
    nativeFunctions[id] = ShasamblyNative(name, 0, execute)
    return id.toShort()
}

private fun nativeFunction(name: String, id: Int, byteArgumentAmount: Int, execute: ShasamblyOpcodeExecutor.() -> Unit): Short {
    nativeFunctions[id] = ShasamblyNative(name, byteArgumentAmount, execute)
    return id.toShort()
}

class ShasamblyNative(
    val name: String,
    val byteArgumentAmount: Int = 0,
    val execute: ShasamblyOpcodeExecutor.() -> Unit
)

object Natives {
    val printByte = nativeFunction("print_byte", 0x01) {
        val v = stack.pop()
        print(v)
    }
    val printShort = nativeFunction("print_short", 0x02) {
        val v = stack.popShort()
        print(v)
    }
    val printInt = nativeFunction("print_int", 0x03) {
        val v = stack.popInt()
        print(v)
    }
    val printLong = nativeFunction("print_long", 0x04) {
        val v = stack.popLong()
        print(v)
    }
    val printFloat = nativeFunction("print_float", 0x05) {
        val v = stack.popFloat()
        print(v)
    }
    val printDouble = nativeFunction("print_double", 0x06) {
        val v = stack.popDouble()
        print(v)
    }
    val printUtf8 = nativeFunction("print_utf8", 0x07) {
        val v = stack.popByte().toInt().toChar()
        print(v)
    }
    val printLineEnding = nativeFunction("print_line_ending", 0x08) {
        println()
    }
    val declareGlobal = nativeFunction("declare_global", 0x09, 4) {
        val size = read_int()
        val addr = declareGlobal(size)
        stack.pushInt(addr)
    }
    val declareGlobalDynamic = nativeFunction("declare_global_dynamic", 0x0a) {
        val size = stack.popInt()
        val addr = declareGlobal(size)
        stack.pushInt(addr)
    }
    val freeGlobal = nativeFunction("free_global", 0x0b, 4) {
        val addr = stack.popInt()
        val size = read_int()
        free(addr, size)
    }
    val freeGlobalDynamic = nativeFunction("free_global_dynamic", 0x0c) {
        val addr = stack.popInt()
        val size = stack.popInt()
        free(addr, size)
    }
    val bSwap = nativeFunction("b_swap", 0x0d, 2) {
        val first = stackPointer - read_byte().toUByte().toInt()
        val second = stackPointer - read_byte().toUByte().toInt()
        val zw = memory[first]
        memory[first] = memory[second]
        memory[second] = zw
    }
    val sSwap = nativeFunction("s_swap", 0x0e, 2) {
        var first = stackPointer - read_byte().toUByte().toInt()
        var second = stackPointer - read_byte().toUByte().toInt()
        var zw = memory[first]
        memory[first] = memory[second]
        memory[second] = zw
        first++
        second++
        zw = memory[first]
        memory[first] = memory[second]
        memory[second] = zw
    }
    val iSwap = nativeFunction("i_swap", 0x0f, 2) {
        var first = stackPointer - read_byte().toUByte().toInt()
        var second = stackPointer - read_byte().toUByte().toInt()
        var zw = memory[first]
        memory[first] = memory[second]
        memory[second] = zw
        for(i in 0..2) {
            first++
            second++
            zw = memory[first]
            memory[first] = memory[second]
            memory[second] = zw
        }
    }
    val lSwap = nativeFunction("l_swap", 0x10, 2) {
        var first = stackPointer - read_byte().toUByte().toInt()
        var second = stackPointer - read_byte().toUByte().toInt()
        var zw = memory[first]
        memory[first] = memory[second]
        memory[second] = zw
        for(i in 0..6) {
            first++
            second++
            zw = memory[first]
            memory[first] = memory[second]
            memory[second] = zw
        }
    }
    val bDup = nativeFunction("b_dup", 0x11) {
        val byte = memory.getByte(stackPointer)
        stack.push(byte)
    }
    val sDup = nativeFunction("s_dup", 0x12) {
        val short = memory.getShort(stackPointer)
        stack.pushShort(short)
    }
    val iDup = nativeFunction("i_dup", 0x13) {
        val int = memory.getInt(stackPointer)
        stack.pushInt(int)
    }
    val lDup = nativeFunction("l_dup", 0x14) {
        val long = memory.getLong(stackPointer)
        stack.pushLong(long)
    }
    val exit = nativeFunction("exit", 0x15) {
        val code = stack.popInt()
        exitProcess(code)
    }
    fun initNativeFunctions() {
        // Do nothing
        // This function is just a placeholder called to initialize all
        // fields!
        // It is important to initialize them even if you are not using
        // its numerical values because the nativeFunctions table will
        // be empty if the fields are not initialized.
    }
}