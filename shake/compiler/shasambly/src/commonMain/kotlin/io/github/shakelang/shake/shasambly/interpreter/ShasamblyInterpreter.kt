@file:Suppress("nothing_to_inline")
package io.github.shakelang.shake.shasambly.interpreter

import io.github.shakelang.parseutils.bytes.*
import kotlin.experimental.and

object Bytes {

    val INCR_STACK: Byte = 0x01 // Syntax: INCR_STACK, u2 stack_size (Increase the variable stack, the new stack has stack_size bytes of space)
    val DECR_STACK: Byte = 0x02 // Syntax: DECR_STACK ; Removes the top stack level
    val JUMP_STATIC: Byte = 0x03 // Syntax: JUMP_STATIC, u4 position ; Jump to the given position
    val JUMP_DYNAMIC: Byte = 0x04 // Syntax: JUMP_DYNAMIC ; Jump to the top u4 element on the stack

    val B_GET_LOCAL: Byte = 0x05 // Syntax: B_GET_LOCAL, u2 position ; Load a local byte onto the stack
    val S_GET_LOCAL: Byte = 0x06 // Syntax: S_GET_LOCAL, u2 position ; Load a local short onto the stack
    val I_GET_LOCAL: Byte = 0x07 // Syntax: I_GET_LOCAL, u2 position ; Load a local integer onto the stack
    val L_GET_LOCAL: Byte = 0x08 // Syntax: L_GET_LOCAL, u2 position ; Load a local long onto the stack

    val B_STORE_LOCAL: Byte = 0x0b // Syntax: B_STORE_LOCAL, u2 position ; Store the top byte from the stack into local
    val S_STORE_LOCAL: Byte = 0x0c // Syntax: S_STORE_LOCAL, u2 position ; Store the top short from the stack into local
    val I_STORE_LOCAL: Byte = 0x0d // Syntax: I_STORE_LOCAL, u2 position ; Store the top integer from the stack into local
    val L_STORE_LOCAL: Byte = 0x0e // Syntax: L_STORE_LOCAL, u2 position ; Store the top long from the stack into local

    val B_ADD: Byte = 0x0f // Syntax: B_ADD ; Adds the top two bytes and leaves the result on the stack
    val B_SUB: Byte = 0x10 // Syntax: B_SUB ; Subtracts the top two bytes and leaves the result on the stack
    val B_MUL: Byte = 0x11 // Syntax: B_MUL ; Multiplies the top two bytes and leaves the result on the stack
    val B_DIV: Byte = 0x12 // Syntax: B_DIV ; Divides the top two bytes and leaves the result on the stack
    val B_MOD: Byte = 0x13 // Syntax: B_MOD ; Calculates the modulo of the top two bytes and leaves the result on the stack

    val S_ADD: Byte = 0x14 // Syntax: S_ADD ; Adds the top two shorts and leaves the result on the stack
    val S_SUB: Byte = 0x15 // Syntax: S_SUB ; Subtracts the top two shorts and leaves the result on the stack
    val S_MUL: Byte = 0x16 // Syntax: S_MUL ; Multiplies the top two shorts and leaves the result on the stack
    val S_DIV: Byte = 0x17 // Syntax: S_DIV ; Divides the top two shorts and leaves the result on the stack
    val S_MOD: Byte = 0x18 // Syntax: S_MOD ; Calculates the modulo of the top two shorts and leaves the result on the stack

    val I_ADD: Byte = 0x19 // Syntax: I_ADD ; Adds the top two integers and leaves the result on the stack
    val I_SUB: Byte = 0x1a // Syntax: I_SUB ; Subtracts the top two integers and leaves the result on the stack
    val I_MUL: Byte = 0x1b // Syntax: I_MUL ; Multiplies the top two integers and leaves the result on the stack
    val I_DIV: Byte = 0x1c // Syntax: I_DIV ; Divides the top two integers and leaves the result on the stack
    val I_MOD: Byte = 0x1d // Syntax: I_MOD ; Calculates the modulo of the top two integers and leaves the result on the stack

    val L_ADD: Byte = 0x1e // Syntax: L_ADD ; Adds the top two longs and leaves the result on the stack
    val L_SUB: Byte = 0x1f // Syntax: L_SUB ; Subtracts the top two longs and leaves the result on the stack
    val L_MUL: Byte = 0x20 // Syntax: L_MUL ; Multiplies the top two longs and leaves the result on the stack
    val L_DIV: Byte = 0x21 // Syntax: L_DIV ; Divides the top two longs and leaves the result on the stack
    val L_MOD: Byte = 0x22 // Syntax: L_MOD ; Calculates the modulo of the top two longs and leaves the result on the stack

    val F_ADD: Byte = 0x23 // Syntax: F_ADD ; Adds the top two floats and leaves the result on the stack
    val F_SUB: Byte = 0x24 // Syntax: F_SUB ; Subtracts the top two floats and leaves the result on the stack
    val F_MUL: Byte = 0x25 // Syntax: F_MUL ; Multiplies the top two floats and leaves the result on the stack
    val F_DIV: Byte = 0x26 // Syntax: F_DIV ; Divides the top two floats and leaves the result on the stack
    val F_MOD: Byte = 0x27 // Syntax: F_MOD ; Calculates the modulo of the top two floats and leaves the result on the stack

    val D_ADD: Byte = 0x28 // Syntax: D_ADD ; Adds the top two doubles and leaves the result on the stack
    val D_SUB: Byte = 0x29 // Syntax: D_SUB ; Subtracts the top two doubles and leaves the result on the stack
    val D_MUL: Byte = 0x2a // Syntax: D_MUL ; Multiplies the top two doubles and leaves the result on the stack
    val D_DIV: Byte = 0x2b // Syntax: D_DIV ; Divides the top two doubles and leaves the result on the stack
    val D_MOD: Byte = 0x2c // Syntax: D_MOD ; Calculates the modulo of the top two doubles and leaves the result on the stack

    val B_PUSH: Byte = 0x2f // Syntax: B_PUSH u1 ; Pushes a byte onto the stack
    val S_PUSH: Byte = 0x30 // Syntax: S_PUSH u2 ; Pushes a short onto the stack
    val I_PUSH: Byte = 0x31 // Syntax: I_PUSH u4 ; Pushes an integer onto the stack
    val L_PUSH: Byte = 0x32 // Syntax: L_PUSH u8 ; Pushes a long onto the stack

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
        if(address !in 0..bytes.size) throw Error("Address out of range")
        position = address
    }

    fun jump_static() {
        jump(read_int())
    }

    fun jump_dynamic() {
        jump(stack.removeLastInt())
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
        stack.add((v0 + v1).toShort())
    }

    fun ssub() {
        val v1 = stack.removeLastShort()
        val v0 = stack.removeLastShort()
        stack.add((v0 - v1).toShort())
    }

    fun smul() {
        val v1 = stack.removeLastShort()
        val v0 = stack.removeLastShort()
        stack.add((v0 * v1).toShort())
    }

    fun sdiv() {
        val v1 = stack.removeLastShort()
        val v0 = stack.removeLastShort()
        stack.add((v0 / v1).toShort())
    }

    fun smod() {
        val v1 = stack.removeLastShort()
        val v0 = stack.removeLastShort()
        stack.add((v0 % v1).toShort())
    }

    fun iadd() {
        val v1 = stack.removeLastInt()
        val v0 = stack.removeLastInt()
        stack.add(v0 + v1)
    }

    fun isub() {
        val v1 = stack.removeLastInt()
        val v0 = stack.removeLastInt()
        stack.add(v0 - v1)
    }

    fun imul() {
        val v1 = stack.removeLastInt()
        val v0 = stack.removeLastInt()
        stack.add(v0 * v1)
    }

    fun idiv() {
        val v1 = stack.removeLastInt()
        val v0 = stack.removeLastInt()
        stack.add(v0 / v1)
    }

    fun imod() {
        val v1 = stack.removeLastInt()
        val v0 = stack.removeLastInt()
        stack.add(v0 % v1)
    }

    fun ladd() {
        val v1 = stack.removeLastLong()
        val v0 = stack.removeLastLong()
        stack.add(v0 + v1)
    }

    fun lsub() {
        val v1 = stack.removeLastLong()
        val v0 = stack.removeLastLong()
        stack.add(v0 - v1)
    }

    fun lmul() {
        val v1 = stack.removeLastLong()
        val v0 = stack.removeLastLong()
        stack.add(v0 * v1)
    }

    fun ldiv() {
        val v1 = stack.removeLastLong()
        val v0 = stack.removeLastLong()
        stack.add(v0 / v1)
    }

    fun lmod() {
        val v1 = stack.removeLastLong()
        val v0 = stack.removeLastLong()
        stack.add(v0 % v1)
    }

    fun fadd() {
        val v1 = stack.removeLastFloat()
        val v0 = stack.removeLastFloat()
        stack.add(v0 + v1)
    }

    fun fsub() {
        val v1 = stack.removeLastFloat()
        val v0 = stack.removeLastFloat()
        stack.add(v0 - v1)
    }

    fun fmul() {
        val v1 = stack.removeLastFloat()
        val v0 = stack.removeLastFloat()
        stack.add(v0 * v1)
    }

    fun fdiv() {
        val v1 = stack.removeLastFloat()
        val v0 = stack.removeLastFloat()
        stack.add(v0 / v1)
    }

    fun fmod() {
        val v1 = stack.removeLastFloat()
        val v0 = stack.removeLastFloat()
        stack.add(v0 % v1)
    }

    fun dadd() {
        val v1 = stack.removeLastDouble()
        val v0 = stack.removeLastDouble()
        stack.add(v0 + v1)
    }

    fun dsub() {
        val v1 = stack.removeLastDouble()
        val v0 = stack.removeLastDouble()
        stack.add(v0 - v1)
    }

    fun dmul() {
        val v1 = stack.removeLastDouble()
        val v0 = stack.removeLastDouble()
        stack.add(v0 * v1)
    }

    fun ddiv() {
        val v1 = stack.removeLastDouble()
        val v0 = stack.removeLastDouble()
        stack.add(v0 / v1)
    }

    fun dmod() {
        val v1 = stack.removeLastDouble()
        val v0 = stack.removeLastDouble()
        stack.add(v0 % v1)
    }

    fun bpush() {
        stack.add(read_byte())
    }

    fun spush() {
        stack.add(read_short())
    }

    fun ipush() {
        stack.add(read_int())
    }

    fun lpush() {
        stack.add(read_long())
    }

    private inline fun MutableList<Byte>.removeLastShort(): Short {
        val v1 = this.removeLast()
        val v0 = this.removeLast()
        return shortOf(v0, v1)
    }

    private inline fun MutableList<Byte>.removeLastInt(): Int {
        val v3 = this.removeLast()
        val v2 = this.removeLast()
        val v1 = this.removeLast()
        val v0 = this.removeLast()
        return intOf(v0, v1, v2, v3)
    }

    private inline fun MutableList<Byte>.removeLastLong(): Long {
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

    private inline fun MutableList<Byte>.removeLastFloat(): Float
            = Float.fromBits(this.removeLastInt())

    private inline fun MutableList<Byte>.removeLastDouble(): Double
            = Double.fromBits(this.removeLastLong())

    private inline fun MutableList<Byte>.add(v: Short) {
        this.add((v.toInt() shr 8).toByte())
        this.add((v and 0x00ff).toByte())
    }

    private inline fun MutableList<Byte>.add(v: Int) {
        this.add((v shr 24 and 0xff).toByte())
        this.add((v shr 16 and 0xff).toByte())
        this.add((v shr 8 and 0xff).toByte())
        this.add((v and 0xff).toByte())
    }

    private inline fun MutableList<Byte>.add(v: Long) {
        this.add((v shr 56 and 0xff).toByte())
        this.add((v shr 48 and 0xff).toByte())
        this.add((v shr 40 and 0xff).toByte())
        this.add((v shr 32 and 0xff).toByte())
        this.add((v shr 24 and 0xff).toByte())
        this.add((v shr 16 and 0xff).toByte())
        this.add((v shr 8 and 0xff).toByte())
        this.add((v and 0xff).toByte())
    }

    private inline fun MutableList<Byte>.add(v: Float) = this.add(v.toBits())
    private inline fun MutableList<Byte>.add(v: Double) = this.add(v.toBits())


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
        map[Bytes.INCR_STACK.toInt()] = { this.incr_variable_stack() }
        map[Bytes.DECR_STACK.toInt()] = { this.decr_variable_stack() }
        map[Bytes.JUMP_STATIC.toInt()] = { this.jump_static() }
        map[Bytes.JUMP_DYNAMIC.toInt()] = { this.jump_dynamic() }

        map[Bytes.B_GET_LOCAL.toInt()] = { this.b_get_local() }
        map[Bytes.S_GET_LOCAL.toInt()] = { this.s_get_local() }
        map[Bytes.I_GET_LOCAL.toInt()] = { this.i_get_local() }
        map[Bytes.L_GET_LOCAL.toInt()] = { this.l_get_local() }

        map[Bytes.B_STORE_LOCAL.toInt()] = { this.b_store_local() }
        map[Bytes.S_STORE_LOCAL.toInt()] = { this.s_store_local() }
        map[Bytes.I_STORE_LOCAL.toInt()] = { this.i_store_local() }
        map[Bytes.L_STORE_LOCAL.toInt()] = { this.l_store_local() }

        map[Bytes.B_ADD.toInt()] = { this.badd() }
        map[Bytes.B_SUB.toInt()] = { this.bsub() }
        map[Bytes.B_MUL.toInt()] = { this.bmul() }
        map[Bytes.B_DIV.toInt()] = { this.bdiv() }
        map[Bytes.B_MOD.toInt()] = { this.bmod() }

        map[Bytes.S_ADD.toInt()] = { this.sadd() }
        map[Bytes.S_SUB.toInt()] = { this.ssub() }
        map[Bytes.S_MUL.toInt()] = { this.smul() }
        map[Bytes.S_DIV.toInt()] = { this.sdiv() }
        map[Bytes.S_MOD.toInt()] = { this.smod() }

        map[Bytes.I_ADD.toInt()] = { this.iadd() }
        map[Bytes.I_SUB.toInt()] = { this.isub() }
        map[Bytes.I_MUL.toInt()] = { this.imul() }
        map[Bytes.I_DIV.toInt()] = { this.idiv() }
        map[Bytes.I_MOD.toInt()] = { this.imod() }

        map[Bytes.L_ADD.toInt()] = { this.ladd() }
        map[Bytes.L_SUB.toInt()] = { this.lsub() }
        map[Bytes.L_MUL.toInt()] = { this.lmul() }
        map[Bytes.L_DIV.toInt()] = { this.ldiv() }
        map[Bytes.L_MOD.toInt()] = { this.lmod() }

        map[Bytes.F_ADD.toInt()] = { this.fadd() }
        map[Bytes.F_SUB.toInt()] = { this.fsub() }
        map[Bytes.F_MUL.toInt()] = { this.fmul() }
        map[Bytes.F_DIV.toInt()] = { this.fdiv() }
        map[Bytes.F_MOD.toInt()] = { this.fmod() }

        map[Bytes.D_ADD.toInt()] = { this.dadd() }
        map[Bytes.D_SUB.toInt()] = { this.dsub() }
        map[Bytes.D_MUL.toInt()] = { this.dmul() }
        map[Bytes.D_DIV.toInt()] = { this.ddiv() }
        map[Bytes.D_MOD.toInt()] = { this.dmod() }

        map[Bytes.B_PUSH.toInt()] = { this.bpush() }
        map[Bytes.S_PUSH.toInt()] = { this.spush() }
        map[Bytes.I_PUSH.toInt()] = { this.ipush() }
        map[Bytes.L_PUSH.toInt()] = { this.lpush() }

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

fun main() {
    val code = byteArrayOf(
        Bytes.INCR_STACK, *100.toShort().toBytes(),
        Bytes.I_PUSH, *0x10e2e3d9.toBytes(),
        Bytes.I_PUSH, *0x1e029832.toBytes(),
        Bytes.I_ADD,
        Bytes.I_STORE_LOCAL, *0.toShort().toBytes(),
        Bytes.I_PUSH, *0x88888888u.toBytes(),
        Bytes.I_STORE_LOCAL, *4.toShort().toBytes(),
        Bytes.I_GET_LOCAL, *0.toShort().toBytes(),
    )
    println(code.toHexString())
    val interpreter = ShasamblyInterpreter(
        1024, code, 0
    )
    interpreter.finish()
    println(interpreter)
}