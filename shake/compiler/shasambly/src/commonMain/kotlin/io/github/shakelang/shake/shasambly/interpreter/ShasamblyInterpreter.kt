@file:Suppress("nothing_to_inline", "unused")
package io.github.shakelang.shake.shasambly.interpreter

import io.github.shakelang.parseutils.bytes.*
import io.github.shakelang.shake.shasambly.interpreter.natives.nativeFunctions
import kotlin.experimental.and

object Opcodes {

    val INCR_STACK: Byte = 0x01 // Syntax: INCR_STACK, u2 stack_size (Increase the variable stack, the new stack has stack_size bytes of space)
    val DECR_STACK: Byte = 0x02 // Syntax: DECR_STACK ; Removes the top stack level
    val JUMP_STATIC: Byte = 0x03 // Syntax: JUMP_STATIC, u4 position ; Jump to the given position
    val JUMP_DYNAMIC: Byte = 0x04 // Syntax: JUMP_DYNAMIC ; Jump to the top u4 element on the stack
    val JUMP_IF: Byte = 0x05 // Syntax: JUMP_IF, u4 position ; Jump if the top stack boolean is true
    val INVOKE_NATIVE: Byte = 0x06 // Syntax: INVOKE_NATIVE, u4 native ; Invoke native function with the given id

    val B_GET_LOCAL: Byte = 0x10 // Syntax: B_GET_LOCAL, u2 position ; Load a local byte onto the stack
    val S_GET_LOCAL: Byte = 0x11 // Syntax: S_GET_LOCAL, u2 position ; Load a local short onto the stack
    val I_GET_LOCAL: Byte = 0x12 // Syntax: I_GET_LOCAL, u2 position ; Load a local integer onto the stack
    val L_GET_LOCAL: Byte = 0x13 // Syntax: L_GET_LOCAL, u2 position ; Load a local long onto the stack

    val B_STORE_LOCAL: Byte = 0x14 // Syntax: B_STORE_LOCAL, u2 position ; Store the top byte from the stack into local
    val S_STORE_LOCAL: Byte = 0x15 // Syntax: S_STORE_LOCAL, u2 position ; Store the top short from the stack into local
    val I_STORE_LOCAL: Byte = 0x16 // Syntax: I_STORE_LOCAL, u2 position ; Store the top integer from the stack into local
    val L_STORE_LOCAL: Byte = 0x17 // Syntax: L_STORE_LOCAL, u2 position ; Store the top long from the stack into local

    val B_PUSH: Byte = 0x18 // Syntax: B_PUSH u1 ; Pushes a byte onto the stack
    val S_PUSH: Byte = 0x19 // Syntax: S_PUSH u2 ; Pushes a short onto the stack
    val I_PUSH: Byte = 0x1a // Syntax: I_PUSH u4 ; Pushes an integer onto the stack
    val L_PUSH: Byte = 0x1b // Syntax: L_PUSH u8 ; Pushes a long onto the stack

    val B_ADD: Byte = 0x1b // Syntax: B_ADD ; Adds the top two bytes and leaves the result on the stack
    val B_SUB: Byte = 0x1c // Syntax: B_SUB ; Subtracts the top two bytes and leaves the result on the stack
    val B_MUL: Byte = 0x1d // Syntax: B_MUL ; Multiplies the top two bytes and leaves the result on the stack
    val B_DIV: Byte = 0x1e // Syntax: B_DIV ; Divides the top two bytes and leaves the result on the stack
    val B_MOD: Byte = 0x1f // Syntax: B_MOD ; Calculates the modulo of the top two bytes and leaves the result on the stack

    val S_ADD: Byte = 0x20 // Syntax: S_ADD ; Adds the top two shorts and leaves the result on the stack
    val S_SUB: Byte = 0x21 // Syntax: S_SUB ; Subtracts the top two shorts and leaves the result on the stack
    val S_MUL: Byte = 0x22 // Syntax: S_MUL ; Multiplies the top two shorts and leaves the result on the stack
    val S_DIV: Byte = 0x23 // Syntax: S_DIV ; Divides the top two shorts and leaves the result on the stack
    val S_MOD: Byte = 0x24 // Syntax: S_MOD ; Calculates the modulo of the top two shorts and leaves the result on the stack

    val I_ADD: Byte = 0x25 // Syntax: I_ADD ; Adds the top two integers and leaves the result on the stack
    val I_SUB: Byte = 0x26 // Syntax: I_SUB ; Subtracts the top two integers and leaves the result on the stack
    val I_MUL: Byte = 0x27 // Syntax: I_MUL ; Multiplies the top two integers and leaves the result on the stack
    val I_DIV: Byte = 0x28 // Syntax: I_DIV ; Divides the top two integers and leaves the result on the stack
    val I_MOD: Byte = 0x29 // Syntax: I_MOD ; Calculates the modulo of the top two integers and leaves the result on the stack

    val L_ADD: Byte = 0x2a // Syntax: L_ADD ; Adds the top two longs and leaves the result on the stack
    val L_SUB: Byte = 0x2b // Syntax: L_SUB ; Subtracts the top two longs and leaves the result on the stack
    val L_MUL: Byte = 0x2c // Syntax: L_MUL ; Multiplies the top two longs and leaves the result on the stack
    val L_DIV: Byte = 0x2d // Syntax: L_DIV ; Divides the top two longs and leaves the result on the stack
    val L_MOD: Byte = 0x2e // Syntax: L_MOD ; Calculates the modulo of the top two longs and leaves the result on the stack

    val F_ADD: Byte = 0x2f // Syntax: F_ADD ; Adds the top two floats and leaves the result on the stack
    val F_SUB: Byte = 0x30 // Syntax: F_SUB ; Subtracts the top two floats and leaves the result on the stack
    val F_MUL: Byte = 0x31 // Syntax: F_MUL ; Multiplies the top two floats and leaves the result on the stack
    val F_DIV: Byte = 0x32 // Syntax: F_DIV ; Divides the top two floats and leaves the result on the stack
    val F_MOD: Byte = 0x33 // Syntax: F_MOD ; Calculates the modulo of the top two floats and leaves the result on the stack

    val D_ADD: Byte = 0x34 // Syntax: D_ADD ; Adds the top two doubles and leaves the result on the stack
    val D_SUB: Byte = 0x35 // Syntax: D_SUB ; Subtracts the top two doubles and leaves the result on the stack
    val D_MUL: Byte = 0x36 // Syntax: D_MUL ; Multiplies the top two doubles and leaves the result on the stack
    val D_DIV: Byte = 0x37 // Syntax: D_DIV ; Divides the top two doubles and leaves the result on the stack
    val D_MOD: Byte = 0x38 // Syntax: D_MOD ; Calculates the modulo of the top two doubles and leaves the result on the stack

    val B_EQ: Byte = 0x039 // Syntax: B_EQ ; Calculates a boolean if the top two bytes are similar to each other
    val S_EQ: Byte = 0x03a // Syntax: S_EQ ; Calculates a boolean if the top two shorts are similar to each other
    val I_EQ: Byte = 0x03b // Syntax: I_EQ ; Calculates a boolean if the top two integers are similar to each other
    val L_EQ: Byte = 0x03c // Syntax: L_EQ ; Calculates a boolean if the top two longs are similar to each other
    val F_EQ: Byte = 0x03d // Syntax: F_EQ ; Calculates a boolean if the top two floats are similar to each other
    val D_EQ: Byte = 0x03e // Syntax: D_EQ ; Calculates a boolean if the top two doubles are similar to each other

    val B_BIGGER: Byte = 0x03f // Syntax: B_BIGGER ; Calculates a boolean if the second but top byte is bigger than the top byte
    val S_BIGGER: Byte = 0x040 // Syntax: S_BIGGER ; Calculates a boolean if the second but top short is bigger than the top byte
    val I_BIGGER: Byte = 0x041 // Syntax: I_BIGGER ; Calculates a boolean if the second but top integer is bigger than the top byte
    val L_BIGGER: Byte = 0x042 // Syntax: L_BIGGER ; Calculates a boolean if the second but top long is bigger than the top byte
    val F_BIGGER: Byte = 0x043 // Syntax: F_BIGGER ; Calculates a boolean if the second but top float is bigger than the top byte
    val D_BIGGER: Byte = 0x044 // Syntax: D_BIGGER ; Calculates a boolean if the second but top double is bigger than the top byte

    val B_SMALLER: Byte = 0x03f // Syntax: B_SMALLER ; Calculates a boolean if the second but top byte is smaller than the top byte
    val S_SMALLER: Byte = 0x040 // Syntax: S_SMALLER ; Calculates a boolean if the second but top short is smaller than the top byte
    val I_SMALLER: Byte = 0x041 // Syntax: I_SMALLER ; Calculates a boolean if the second but top integer is smaller than the top byte
    val L_SMALLER: Byte = 0x042 // Syntax: L_SMALLER ; Calculates a boolean if the second but top long is smaller than the top byte
    val F_SMALLER: Byte = 0x043 // Syntax: F_SMALLER ; Calculates a boolean if the second but top float is smaller than the top byte
    val D_SMALLER: Byte = 0x044 // Syntax: D_SMALLER ; Calculates a boolean if the second but top double is smaller than the top byte

    val B_BIGGER_EQ: Byte = 0x045 // Syntax: B_BIGGER_EQ ; Calculates a boolean if the second but top byte is bigger or equal than the top byte
    val S_BIGGER_EQ: Byte = 0x046 // Syntax: S_BIGGER_EQ ; Calculates a boolean if the second but top short is bigger or equal than the top byte
    val I_BIGGER_EQ: Byte = 0x047 // Syntax: I_BIGGER_EQ ; Calculates a boolean if the second but top integer is bigger or equal than the top byte
    val L_BIGGER_EQ: Byte = 0x048 // Syntax: L_BIGGER_EQ ; Calculates a boolean if the second but top long is bigger or equal than the top byte
    val F_BIGGER_EQ: Byte = 0x049 // Syntax: F_BIGGER_EQ ; Calculates a boolean if the second but top float is bigger or equal than the top byte
    val D_BIGGER_EQ: Byte = 0x04a // Syntax: D_BIGGER_EQ ; Calculates a boolean if the second but top double is bigger or equal than the top byte

    val B_SMALLER_EQ: Byte = 0x04b // Syntax: B_SMALLER_EQ ; Calculates a boolean if the second but top byte is smaller or equal than the top byte
    val S_SMALLER_EQ: Byte = 0x04c // Syntax: S_SMALLER_EQ ; Calculates a boolean if the second but top short is smaller or equal than the top byte
    val I_SMALLER_EQ: Byte = 0x04d // Syntax: I_SMALLER_EQ ; Calculates a boolean if the second but top integer is smaller or equal than the top byte
    val L_SMALLER_EQ: Byte = 0x04e // Syntax: L_SMALLER_EQ ; Calculates a boolean if the second but top long is smaller or equal than the top byte
    val F_SMALLER_EQ: Byte = 0x04f // Syntax: F_SMALLER_EQ ; Calculates a boolean if the second but top float is smaller or equal than the top byte
    val D_SMALLER_EQ: Byte = 0x050 // Syntax: D_SMALLER_EQ ; Calculates a boolean if the second but top double is smaller or equal than the top byte

}

class ShasamblyInterpreter(
    memorySize: Int,
    val bytes: ByteArray,
    var position: Int
) {

    val memory = ByteArray(memorySize)
    val memorySize get() = memory
    val variableStackSizes = mutableListOf<Int>()
    val stack = mutableListOf<Byte>()
    private var variableStackSize: Int = 0
    var variableAddress = 0
    val byteMap = createByteMap()

    fun finished(): Boolean = this.position >= this.bytes.size

    fun tick() {
        val next = bytes[position++]
        (byteMap[next.toUByte().toInt()]
            ?: throw Error("Could not execute byte 0x${next.toBytes().toHexString()} " +
                    "at position 0x${(position-1).toBytes().toHexString()} (${position-1})")).invoke()
    }

    fun tick(times: Int) {
        for (i in 1..times) {
            tick()
            if(finished()) break
        }
    }

    fun finish() {
        while(!finished()) tick()
    }

    fun incr_variable_stack() {
        variableStackSize = read_short().toUShort().toInt()
        variableAddress += variableStackSize
        variableStackSizes.add(variableStackSize)
    }

    fun decr_variable_stack() {
        variableAddress -= variableStackSizes.removeLast()
        variableStackSize = variableStackSizes.last()
    }

    fun jump(address: Int) {
        if(address !in bytes.indices) throw Error("Address out of range")
        position = address
    }

    fun jump_static() {
        jump(read_int())
    }

    fun jump_dynamic() {
        jump(stack.removeLastInt())
    }

    fun jump_if() {
        val addr = read_int()
        if(stack.removeLastByte() != 0x00.toByte()) jump(addr)
    }

    fun invoke_native() {
        val native = read_short().toUShort().toInt()
        (nativeFunctions[native]
            ?: throw Error("Unknown native function 0x${native.toBytes().toHexString()} at position 0x${position.toBytes().toHexString()}"))
            .second.invoke(this)
    }

    fun b_get_local() {
        val variable = read_short().toUShort().toInt()
        stack.add(memory[variableAddress - variable])
    }

    fun s_get_local() {
        val variable = read_short().toUShort().toInt()
        val addr = variableAddress - variable
        stack.add(memory[addr])
        stack.add(memory[addr - 1])
    }

    fun i_get_local() {
        val variable = read_short().toUShort().toInt()
        val addr = variableAddress - variable
        stack.add(memory[addr])
        stack.add(memory[addr - 1])
        stack.add(memory[addr - 2])
        stack.add(memory[addr - 3])
    }

    fun l_get_local() {
        val variable = read_short().toUShort().toInt()
        val addr = variableAddress - variable
        stack.add(memory[addr])
        stack.add(memory[addr - 1])
        stack.add(memory[addr - 2])
        stack.add(memory[addr - 3])
        stack.add(memory[addr - 4])
        stack.add(memory[addr - 5])
        stack.add(memory[addr - 6])
        stack.add(memory[addr - 7])
    }

    fun b_store_local() {
        val variable = read_short().toUShort().toInt()
        val addr = variableAddress - variable
        memory[addr] = stack.removeLast()
    }

    fun s_store_local() {
        val variable = read_short().toUShort().toInt()
        val addr = variableAddress - variable
        memory[addr - 1] = stack.removeLast()
        memory[addr] = stack.removeLast()
    }

    fun i_store_local() {
        val variable = read_short().toUShort().toInt()
        val addr = variableAddress - variable
        memory[addr - 3] = stack.removeLast()
        memory[addr - 2] = stack.removeLast()
        memory[addr - 1] = stack.removeLast()
        memory[addr] = stack.removeLast()
    }

    fun l_store_local() {
        val variable = read_short().toUShort().toInt()
        val addr = variableAddress - variable
        memory[addr - 7] = stack.removeLast()
        memory[addr - 6] = stack.removeLast()
        memory[addr - 5] = stack.removeLast()
        memory[addr - 4] = stack.removeLast()
        memory[addr - 3] = stack.removeLast()
        memory[addr - 2] = stack.removeLast()
        memory[addr - 1] = stack.removeLast()
        memory[addr] = stack.removeLast()
    }

    fun bpush() {
        stack.add(read_byte())
    }

    fun spush() {
        stack.addShort(read_short())
    }

    fun ipush() {
        stack.addInt(read_int())
    }

    fun lpush() {
        stack.addLong(read_long())
    }

    fun badd() {
        val v1 = stack.removeLast()
        val v0 = stack.removeLast()
        stack.add((v0 + v1).toByte())
    }

    fun bsub() {
        val v1 = stack.removeLast()
        val v0 = stack.removeLast()
        stack.add((v0 - v1).toByte())
    }

    fun bmul() {
        val v1 = stack.removeLast()
        val v0 = stack.removeLast()
        stack.add((v0 * v1).toByte())
    }

    fun bdiv() {
        val v1 = stack.removeLast()
        val v0 = stack.removeLast()
        stack.add((v0 / v1).toByte())
    }

    fun bmod() {
        val v1 = stack.removeLast()
        val v0 = stack.removeLast()
        stack.add((v0 % v1).toByte())
    }

    fun sadd() {
        val v1 = stack.removeLastShort()
        val v0 = stack.removeLastShort()
        stack.addShort((v0 + v1).toShort())
    }

    fun ssub() {
        val v1 = stack.removeLastShort()
        val v0 = stack.removeLastShort()
        stack.addShort((v0 - v1).toShort())
    }

    fun smul() {
        val v1 = stack.removeLastShort()
        val v0 = stack.removeLastShort()
        stack.addShort((v0 * v1).toShort())
    }

    fun sdiv() {
        val v1 = stack.removeLastShort()
        val v0 = stack.removeLastShort()
        stack.addShort((v0 / v1).toShort())
    }

    fun smod() {
        val v1 = stack.removeLastShort()
        val v0 = stack.removeLastShort()
        stack.addShort((v0 % v1).toShort())
    }

    fun iadd() {
        val v1 = stack.removeLastInt()
        val v0 = stack.removeLastInt()
        stack.addInt(v0 + v1)
    }

    fun isub() {
        val v1 = stack.removeLastInt()
        val v0 = stack.removeLastInt()
        stack.addInt(v0 - v1)
    }

    fun imul() {
        val v1 = stack.removeLastInt()
        val v0 = stack.removeLastInt()
        stack.addInt(v0 * v1)
    }

    fun idiv() {
        val v1 = stack.removeLastInt()
        val v0 = stack.removeLastInt()
        stack.addInt(v0 / v1)
    }

    fun imod() {
        val v1 = stack.removeLastInt()
        val v0 = stack.removeLastInt()
        stack.addInt(v0 % v1)
    }

    fun ladd() {
        val v1 = stack.removeLastLong()
        val v0 = stack.removeLastLong()
        stack.addLong(v0 + v1)
    }

    fun lsub() {
        val v1 = stack.removeLastLong()
        val v0 = stack.removeLastLong()
        stack.addLong(v0 - v1)
    }

    fun lmul() {
        val v1 = stack.removeLastLong()
        val v0 = stack.removeLastLong()
        stack.addLong(v0 * v1)
    }

    fun ldiv() {
        val v1 = stack.removeLastLong()
        val v0 = stack.removeLastLong()
        stack.addLong(v0 / v1)
    }

    fun lmod() {
        val v1 = stack.removeLastLong()
        val v0 = stack.removeLastLong()
        stack.addLong(v0 % v1)
    }

    fun fadd() {
        val v1 = stack.removeLastFloat()
        val v0 = stack.removeLastFloat()
        stack.addFloat(v0 + v1)
    }

    fun fsub() {
        val v1 = stack.removeLastFloat()
        val v0 = stack.removeLastFloat()
        stack.addFloat(v0 - v1)
    }

    fun fmul() {
        val v1 = stack.removeLastFloat()
        val v0 = stack.removeLastFloat()
        stack.addFloat(v0 * v1)
    }

    fun fdiv() {
        val v1 = stack.removeLastFloat()
        val v0 = stack.removeLastFloat()
        stack.addFloat(v0 / v1)
    }

    fun fmod() {
        val v1 = stack.removeLastFloat()
        val v0 = stack.removeLastFloat()
        stack.addFloat(v0 % v1)
    }

    fun dadd() {
        val v1 = stack.removeLastDouble()
        val v0 = stack.removeLastDouble()
        stack.addDouble(v0 + v1)
    }

    fun dsub() {
        val v1 = stack.removeLastDouble()
        val v0 = stack.removeLastDouble()
        stack.addDouble(v0 - v1)
    }

    fun dmul() {
        val v1 = stack.removeLastDouble()
        val v0 = stack.removeLastDouble()
        stack.addDouble(v0 * v1)
    }

    fun ddiv() {
        val v1 = stack.removeLastDouble()
        val v0 = stack.removeLastDouble()
        stack.addDouble(v0 / v1)
    }

    fun dmod() {
        val v1 = stack.removeLastDouble()
        val v0 = stack.removeLastDouble()
        stack.addDouble(v0 % v1)
    }

    fun beq() {
        val v1 = stack.removeLastByte()
        val v0 = stack.removeLastByte()
        stack.addBoolean(v0 == v1)
    }

    fun seq() {
        val v1 = stack.removeLastShort()
        val v0 = stack.removeLastShort()
        stack.addBoolean(v0 == v1)
    }

    fun ieq() {
        val v1 = stack.removeLastInt()
        val v0 = stack.removeLastInt()
        stack.addBoolean(v0 == v1)
    }

    fun leq() {
        val v1 = stack.removeLastLong()
        val v0 = stack.removeLastLong()
        stack.addBoolean(v0 == v1)
    }

    fun feq() {
        val v1 = stack.removeLastFloat()
        val v0 = stack.removeLastFloat()
        stack.addBoolean(v0 == v1)
    }

    fun deq() {
        val v1 = stack.removeLastDouble()
        val v0 = stack.removeLastDouble()
        stack.addBoolean(v0 == v1)
    }

    fun bbigger() {
        val v1 = stack.removeLastByte()
        val v0 = stack.removeLastByte()
        stack.addBoolean(v0 > v1)
    }

    fun sbigger() {
        val v1 = stack.removeLastShort()
        val v0 = stack.removeLastShort()
        stack.addBoolean(v0 > v1)
    }

    fun ibigger() {
        val v1 = stack.removeLastInt()
        val v0 = stack.removeLastInt()
        stack.addBoolean(v0 > v1)
    }

    fun lbigger() {
        val v1 = stack.removeLastLong()
        val v0 = stack.removeLastLong()
        stack.addBoolean(v0 > v1)
    }

    fun fbigger() {
        val v1 = stack.removeLastFloat()
        val v0 = stack.removeLastFloat()
        stack.addBoolean(v0 > v1)
    }

    fun dbigger() {
        val v1 = stack.removeLastDouble()
        val v0 = stack.removeLastDouble()
        stack.addBoolean(v0 > v1)
    }

    fun bsmaller() {
        val v1 = stack.removeLastByte()
        val v0 = stack.removeLastByte()
        stack.addBoolean(v0 < v1)
    }

    fun ssmaller() {
        val v1 = stack.removeLastShort()
        val v0 = stack.removeLastShort()
        stack.addBoolean(v0 < v1)
    }

    fun ismaller() {
        val v1 = stack.removeLastInt()
        val v0 = stack.removeLastInt()
        stack.addBoolean(v0 < v1)
    }

    fun lsmaller() {
        val v1 = stack.removeLastLong()
        val v0 = stack.removeLastLong()
        stack.addBoolean(v0 < v1)
    }

    fun fsmaller() {
        val v1 = stack.removeLastFloat()
        val v0 = stack.removeLastFloat()
        stack.addBoolean(v0 < v1)
    }

    fun dsmaller() {
        val v1 = stack.removeLastDouble()
        val v0 = stack.removeLastDouble()
        stack.addBoolean(v0 < v1)
    }

    fun bbiggereq() {
        val v1 = stack.removeLastByte()
        val v0 = stack.removeLastByte()
        stack.addBoolean(v0 >= v1)
    }

    fun sbiggereq() {
        val v1 = stack.removeLastShort()
        val v0 = stack.removeLastShort()
        stack.addBoolean(v0 >= v1)
    }

    fun ibiggereq() {
        val v1 = stack.removeLastInt()
        val v0 = stack.removeLastInt()
        stack.addBoolean(v0 >= v1)
    }

    fun lbiggereq() {
        val v1 = stack.removeLastLong()
        val v0 = stack.removeLastLong()
        stack.addBoolean(v0 >= v1)
    }

    fun fbiggereq() {
        val v1 = stack.removeLastFloat()
        val v0 = stack.removeLastFloat()
        stack.addBoolean(v0 >= v1)
    }

    fun dbiggereq() {
        val v1 = stack.removeLastDouble()
        val v0 = stack.removeLastDouble()
        stack.addBoolean(v0 >= v1)
    }

    fun bsmallereq() {
        val v1 = stack.removeLastByte()
        val v0 = stack.removeLastByte()
        stack.addBoolean(v0 <= v1)
    }

    fun ssmallereq() {
        val v1 = stack.removeLastShort()
        val v0 = stack.removeLastShort()
        stack.addBoolean(v0 <= v1)
    }

    fun ismallereq() {
        val v1 = stack.removeLastInt()
        val v0 = stack.removeLastInt()
        stack.addBoolean(v0 <= v1)
    }

    fun lsmallereq() {
        val v1 = stack.removeLastLong()
        val v0 = stack.removeLastLong()
        stack.addBoolean(v0 <= v1)
    }

    fun fsmallereq() {
        val v1 = stack.removeLastFloat()
        val v0 = stack.removeLastFloat()
        stack.addBoolean(v0 <= v1)
    }

    fun dsmallereq() {
        val v1 = stack.removeLastDouble()
        val v0 = stack.removeLastDouble()
        stack.addBoolean(v0 <= v1)
    }

    private inline fun byte() = bytes[position]
    private inline fun short() = bytes.getShort(position)
    private inline fun int() = bytes.getInt(position)
    private inline fun long() = bytes.getLong(position)
    private inline fun read_byte(): Byte {
        return bytes[position++]
    }
    private inline fun read_short(): Short {
        val v = short()
        position += 2
        return v
    }
    private inline fun read_int(): Int {
        val v = int()
        position += 4
        return v
    }
    private inline fun read_long(): Long {
        val v = long()
        position += 8
        return v
    }
    private fun createByteMap(): Array<(() -> Unit)?> {
        val map = arrayOfNulls<() -> Unit>(256)
        map[Opcodes.INCR_STACK.toInt()] = { this.incr_variable_stack() }
        map[Opcodes.DECR_STACK.toInt()] = { this.decr_variable_stack() }
        map[Opcodes.JUMP_STATIC.toInt()] = { this.jump_static() }
        map[Opcodes.JUMP_DYNAMIC.toInt()] = { this.jump_dynamic() }
        map[Opcodes.JUMP_IF.toInt()] = { this.jump_if() }
        map[Opcodes.INVOKE_NATIVE.toInt()] = { this.invoke_native() }

        map[Opcodes.B_GET_LOCAL.toInt()] = { this.b_get_local() }
        map[Opcodes.S_GET_LOCAL.toInt()] = { this.s_get_local() }
        map[Opcodes.I_GET_LOCAL.toInt()] = { this.i_get_local() }
        map[Opcodes.L_GET_LOCAL.toInt()] = { this.l_get_local() }

        map[Opcodes.B_STORE_LOCAL.toInt()] = { this.b_store_local() }
        map[Opcodes.S_STORE_LOCAL.toInt()] = { this.s_store_local() }
        map[Opcodes.I_STORE_LOCAL.toInt()] = { this.i_store_local() }
        map[Opcodes.L_STORE_LOCAL.toInt()] = { this.l_store_local() }

        map[Opcodes.B_ADD.toInt()] = { this.badd() }
        map[Opcodes.B_SUB.toInt()] = { this.bsub() }
        map[Opcodes.B_MUL.toInt()] = { this.bmul() }
        map[Opcodes.B_DIV.toInt()] = { this.bdiv() }
        map[Opcodes.B_MOD.toInt()] = { this.bmod() }

        map[Opcodes.S_ADD.toInt()] = { this.sadd() }
        map[Opcodes.S_SUB.toInt()] = { this.ssub() }
        map[Opcodes.S_MUL.toInt()] = { this.smul() }
        map[Opcodes.S_DIV.toInt()] = { this.sdiv() }
        map[Opcodes.S_MOD.toInt()] = { this.smod() }

        map[Opcodes.I_ADD.toInt()] = { this.iadd() }
        map[Opcodes.I_SUB.toInt()] = { this.isub() }
        map[Opcodes.I_MUL.toInt()] = { this.imul() }
        map[Opcodes.I_DIV.toInt()] = { this.idiv() }
        map[Opcodes.I_MOD.toInt()] = { this.imod() }

        map[Opcodes.L_ADD.toInt()] = { this.ladd() }
        map[Opcodes.L_SUB.toInt()] = { this.lsub() }
        map[Opcodes.L_MUL.toInt()] = { this.lmul() }
        map[Opcodes.L_DIV.toInt()] = { this.ldiv() }
        map[Opcodes.L_MOD.toInt()] = { this.lmod() }

        map[Opcodes.F_ADD.toInt()] = { this.fadd() }
        map[Opcodes.F_SUB.toInt()] = { this.fsub() }
        map[Opcodes.F_MUL.toInt()] = { this.fmul() }
        map[Opcodes.F_DIV.toInt()] = { this.fdiv() }
        map[Opcodes.F_MOD.toInt()] = { this.fmod() }

        map[Opcodes.D_ADD.toInt()] = { this.dadd() }
        map[Opcodes.D_SUB.toInt()] = { this.dsub() }
        map[Opcodes.D_MUL.toInt()] = { this.dmul() }
        map[Opcodes.D_DIV.toInt()] = { this.ddiv() }
        map[Opcodes.D_MOD.toInt()] = { this.dmod() }

        map[Opcodes.B_PUSH.toInt()] = { this.bpush() }
        map[Opcodes.S_PUSH.toInt()] = { this.spush() }
        map[Opcodes.I_PUSH.toInt()] = { this.ipush() }
        map[Opcodes.L_PUSH.toInt()] = { this.lpush() }

        map[Opcodes.B_EQ.toInt()] = { this.beq() }
        map[Opcodes.S_EQ.toInt()] = { this.seq() }
        map[Opcodes.I_EQ.toInt()] = { this.ieq() }
        map[Opcodes.L_EQ.toInt()] = { this.leq() }
        map[Opcodes.F_EQ.toInt()] = { this.feq() }
        map[Opcodes.D_EQ.toInt()] = { this.deq() }

        map[Opcodes.B_BIGGER.toInt()] = { this.bbigger() }
        map[Opcodes.S_BIGGER.toInt()] = { this.sbigger() }
        map[Opcodes.I_BIGGER.toInt()] = { this.ibigger() }
        map[Opcodes.L_BIGGER.toInt()] = { this.lbigger() }
        map[Opcodes.F_BIGGER.toInt()] = { this.fbigger() }
        map[Opcodes.D_BIGGER.toInt()] = { this.dbigger() }

        map[Opcodes.B_SMALLER.toInt()] = { this.bsmaller() }
        map[Opcodes.S_SMALLER.toInt()] = { this.ssmaller() }
        map[Opcodes.I_SMALLER.toInt()] = { this.ismaller() }
        map[Opcodes.L_SMALLER.toInt()] = { this.lsmaller() }
        map[Opcodes.F_SMALLER.toInt()] = { this.fsmaller() }
        map[Opcodes.D_SMALLER.toInt()] = { this.dsmaller() }

        map[Opcodes.B_BIGGER_EQ.toInt()] = { this.bbiggereq() }
        map[Opcodes.S_BIGGER_EQ.toInt()] = { this.sbiggereq() }
        map[Opcodes.I_BIGGER_EQ.toInt()] = { this.ibiggereq() }
        map[Opcodes.L_BIGGER_EQ.toInt()] = { this.lbiggereq() }
        map[Opcodes.F_BIGGER_EQ.toInt()] = { this.fbiggereq() }
        map[Opcodes.D_BIGGER_EQ.toInt()] = { this.dbiggereq() }

        map[Opcodes.B_SMALLER_EQ.toInt()] = { this.bsmallereq() }
        map[Opcodes.S_SMALLER_EQ.toInt()] = { this.ssmallereq() }
        map[Opcodes.I_SMALLER_EQ.toInt()] = { this.ismallereq() }
        map[Opcodes.L_SMALLER_EQ.toInt()] = { this.lsmallereq() }
        map[Opcodes.F_SMALLER_EQ.toInt()] = { this.fsmallereq() }
        map[Opcodes.D_SMALLER_EQ.toInt()] = { this.dsmallereq() }




        return map
    }

    override fun toString(): String {
        return "ShasablyInterpreter{" +
                "position=$position," +
                "code=${bytes.toHexString()}," +
                "memory=${memory.toHexString()}," +
                "stack=${stack.toByteArray().toHexString()}}"
    }
}

inline fun MutableList<Byte>.removeLastByte(): Byte
        = this.removeLast()

inline fun MutableList<Byte>.removeLastShort(): Short {
    val v1 = this.removeLast()
    val v0 = this.removeLast()
    return shortOf(v0, v1)
}

inline fun MutableList<Byte>.removeLastInt(): Int {
    val v3 = this.removeLast()
    val v2 = this.removeLast()
    val v1 = this.removeLast()
    val v0 = this.removeLast()
    return intOf(v0, v1, v2, v3)
}

inline fun MutableList<Byte>.removeLastLong(): Long {
    val v7 = this.removeLast()
    val v6 = this.removeLast()
    val v5 = this.removeLast()
    val v4 = this.removeLast()
    val v3 = this.removeLast()
    val v2 = this.removeLast()
    val v1 = this.removeLast()
    val v0 = this.removeLast()
    return longOf(v0, v1, v2, v3, v4, v5, v6, v7)
}

inline fun MutableList<Byte>.removeLastFloat(): Float
        = Float.fromBits(this.removeLastInt())

inline fun MutableList<Byte>.removeLastDouble(): Double
        = Double.fromBits(this.removeLastLong())

inline fun MutableList<Byte>.addByte(v: Byte) {
    this.add(v)
}

inline fun MutableList<Byte>.addShort(v: Short) {
    this.add((v.toInt() shr 8).toByte())
    this.add((v and 0x00ff).toByte())
}

inline fun MutableList<Byte>.addInt(v: Int) {
    this.add((v shr 24 and 0xff).toByte())
    this.add((v shr 16 and 0xff).toByte())
    this.add((v shr 8 and 0xff).toByte())
    this.add((v and 0xff).toByte())
}

inline fun MutableList<Byte>.addLong(v: Long) {
    this.add((v shr 56 and 0xff).toByte())
    this.add((v shr 48 and 0xff).toByte())
    this.add((v shr 40 and 0xff).toByte())
    this.add((v shr 32 and 0xff).toByte())
    this.add((v shr 24 and 0xff).toByte())
    this.add((v shr 16 and 0xff).toByte())
    this.add((v shr 8 and 0xff).toByte())
    this.add((v and 0xff).toByte())
}

inline fun MutableList<Byte>.addFloat(v: Float) = this.addInt(v.toBits())
inline fun MutableList<Byte>.addDouble(v: Double) = this.addLong(v.toBits())

private inline fun MutableList<Byte>.addBoolean(v: Boolean) = this.add(if(v) 0x1.toByte() else 0x0.toByte())
