@file:Suppress("nothing_to_inline", "unused")
package io.github.shakelang.shake.shasambly.interpreter

import io.github.shakelang.parseutils.bytes.*
import io.github.shakelang.shake.shasambly.interpreter.natives.nativeFunctions
import kotlin.experimental.and
import kotlin.math.abs

object Opcodes {

    const val INCR_STACK: Byte = 0x01 // Syntax: INCR_STACK, u2 stack_size (Increase the variable stack, the new stack has stack_size bytes of space)
    const val DECR_STACK: Byte = 0x02 // Syntax: DECR_STACK ; Removes the top stack level
    const val JUMP_STATIC: Byte = 0x03 // Syntax: JUMP_STATIC, u4 position ; Jump to the given position
    const val JUMP_DYNAMIC: Byte = 0x04 // Syntax: JUMP_DYNAMIC ; Jump to the top u4 element on the stack
    const val JUMP_IF: Byte = 0x05 // Syntax: JUMP_IF, u4 position ; Jump if the top stack boolean is true
    const val INVOKE_NATIVE: Byte = 0x06 // Syntax: INVOKE_NATIVE, u2 native [bytes] ; Invoke native function with the given id
    const val GLOB_ADDR: Byte = 0x07 // Syntax: GLOB_ADDR, u2 position ; Puts the global address of a local variable on top of the stack

    const val B_GET_LOCAL: Byte = 0x10 // Syntax: B_GET_LOCAL, u2 position ; Load a local byte onto the stack
    const val S_GET_LOCAL: Byte = 0x11 // Syntax: S_GET_LOCAL, u2 position ; Load a local short onto the stack
    const val I_GET_LOCAL: Byte = 0x12 // Syntax: I_GET_LOCAL, u2 position ; Load a local integer onto the stack
    const val L_GET_LOCAL: Byte = 0x13 // Syntax: L_GET_LOCAL, u2 position ; Load a local long onto the stack

    const val B_STORE_LOCAL: Byte = 0x14 // Syntax: B_STORE_LOCAL, u2 position ; Store the top byte from the stack into local
    const val S_STORE_LOCAL: Byte = 0x15 // Syntax: S_STORE_LOCAL, u2 position ; Store the top short from the stack into local
    const val I_STORE_LOCAL: Byte = 0x16 // Syntax: I_STORE_LOCAL, u2 position ; Store the top integer from the stack into local
    const val L_STORE_LOCAL: Byte = 0x17 // Syntax: L_STORE_LOCAL, u2 position ; Store the top long from the stack into local

    const val B_PUSH: Byte = 0x18 // Syntax: B_PUSH u1 ; Pushes a byte onto the stack
    const val S_PUSH: Byte = 0x19 // Syntax: S_PUSH u2 ; Pushes a short onto the stack
    const val I_PUSH: Byte = 0x1a // Syntax: I_PUSH u4 ; Pushes an integer onto the stack
    const val L_PUSH: Byte = 0x1b // Syntax: L_PUSH u8 ; Pushes a long onto the stack

    const val B_ADD: Byte = 0x1c // Syntax: B_ADD ; Adds the top two bytes and leaves the result on the stack
    const val B_SUB: Byte = 0x1d // Syntax: B_SUB ; Subtracts the top two bytes and leaves the result on the stack
    const val B_MUL: Byte = 0x1e // Syntax: B_MUL ; Multiplies the top two bytes and leaves the result on the stack
    const val B_DIV: Byte = 0x1f // Syntax: B_DIV ; Divides the top two bytes and leaves the result on the stack
    const val B_MOD: Byte = 0x20 // Syntax: B_MOD ; Calculates the modulo of the top two bytes and leaves the result on the stack

    const val S_ADD: Byte = 0x21 // Syntax: S_ADD ; Adds the top two shorts and leaves the result on the stack
    const val S_SUB: Byte = 0x22 // Syntax: S_SUB ; Subtracts the top two shorts and leaves the result on the stack
    const val S_MUL: Byte = 0x23 // Syntax: S_MUL ; Multiplies the top two shorts and leaves the result on the stack
    const val S_DIV: Byte = 0x24 // Syntax: S_DIV ; Divides the top two shorts and leaves the result on the stack
    const val S_MOD: Byte = 0x25 // Syntax: S_MOD ; Calculates the modulo of the top two shorts and leaves the result on the stack

    const val I_ADD: Byte = 0x26 // Syntax: I_ADD ; Adds the top two integers and leaves the result on the stack
    const val I_SUB: Byte = 0x27 // Syntax: I_SUB ; Subtracts the top two integers and leaves the result on the stack
    const val I_MUL: Byte = 0x28 // Syntax: I_MUL ; Multiplies the top two integers and leaves the result on the stack
    const val I_DIV: Byte = 0x29 // Syntax: I_DIV ; Divides the top two integers and leaves the result on the stack
    const val I_MOD: Byte = 0x2a // Syntax: I_MOD ; Calculates the modulo of the top two integers and leaves the result on the stack

    const val L_ADD: Byte = 0x2b // Syntax: L_ADD ; Adds the top two longs and leaves the result on the stack
    const val L_SUB: Byte = 0x2c // Syntax: L_SUB ; Subtracts the top two longs and leaves the result on the stack
    const val L_MUL: Byte = 0x2d // Syntax: L_MUL ; Multiplies the top two longs and leaves the result on the stack
    const val L_DIV: Byte = 0x2e // Syntax: L_DIV ; Divides the top two longs and leaves the result on the stack
    const val L_MOD: Byte = 0x2f // Syntax: L_MOD ; Calculates the modulo of the top two longs and leaves the result on the stack

    const val F_ADD: Byte = 0x30 // Syntax: F_ADD ; Adds the top two floats and leaves the result on the stack
    const val F_SUB: Byte = 0x31 // Syntax: F_SUB ; Subtracts the top two floats and leaves the result on the stack
    const val F_MUL: Byte = 0x32 // Syntax: F_MUL ; Multiplies the top two floats and leaves the result on the stack
    const val F_DIV: Byte = 0x33 // Syntax: F_DIV ; Divides the top two floats and leaves the result on the stack
    const val F_MOD: Byte = 0x34 // Syntax: F_MOD ; Calculates the modulo of the top two floats and leaves the result on the stack

    const val D_ADD: Byte = 0x35 // Syntax: D_ADD ; Adds the top two doubles and leaves the result on the stack
    const val D_SUB: Byte = 0x36 // Syntax: D_SUB ; Subtracts the top two doubles and leaves the result on the stack
    const val D_MUL: Byte = 0x37 // Syntax: D_MUL ; Multiplies the top two doubles and leaves the result on the stack
    const val D_DIV: Byte = 0x38 // Syntax: D_DIV ; Divides the top two doubles and leaves the result on the stack
    const val D_MOD: Byte = 0x39 // Syntax: D_MOD ; Calculates the modulo of the top two doubles and leaves the result on the stack

    const val B_EQ: Byte = 0x03a // Syntax: B_EQ ; Calculates a boolean if the top two bytes are similar to each other
    const val S_EQ: Byte = 0x03b // Syntax: S_EQ ; Calculates a boolean if the top two shorts are similar to each other
    const val I_EQ: Byte = 0x03c // Syntax: I_EQ ; Calculates a boolean if the top two integers are similar to each other
    const val L_EQ: Byte = 0x03d // Syntax: L_EQ ; Calculates a boolean if the top two longs are similar to each other
    const val F_EQ: Byte = 0x03e // Syntax: F_EQ ; Calculates a boolean if the top two floats are similar to each other
    const val D_EQ: Byte = 0x03f // Syntax: D_EQ ; Calculates a boolean if the top two doubles are similar to each other

    const val B_BIGGER: Byte = 0x040 // Syntax: B_BIGGER ; Calculates a boolean if the second but top byte is bigger than the top byte
    const val S_BIGGER: Byte = 0x041 // Syntax: S_BIGGER ; Calculates a boolean if the second but top short is bigger than the top byte
    const val I_BIGGER: Byte = 0x042 // Syntax: I_BIGGER ; Calculates a boolean if the second but top integer is bigger than the top byte
    const val L_BIGGER: Byte = 0x043 // Syntax: L_BIGGER ; Calculates a boolean if the second but top long is bigger than the top byte
    const val F_BIGGER: Byte = 0x044 // Syntax: F_BIGGER ; Calculates a boolean if the second but top float is bigger than the top byte
    const val D_BIGGER: Byte = 0x045 // Syntax: D_BIGGER ; Calculates a boolean if the second but top double is bigger than the top byte

    const val B_SMALLER: Byte = 0x046 // Syntax: B_SMALLER ; Calculates a boolean if the second but top byte is smaller than the top byte
    const val S_SMALLER: Byte = 0x047 // Syntax: S_SMALLER ; Calculates a boolean if the second but top short is smaller than the top byte
    const val I_SMALLER: Byte = 0x048 // Syntax: I_SMALLER ; Calculates a boolean if the second but top integer is smaller than the top byte
    const val L_SMALLER: Byte = 0x049 // Syntax: L_SMALLER ; Calculates a boolean if the second but top long is smaller than the top byte
    const val F_SMALLER: Byte = 0x04a // Syntax: F_SMALLER ; Calculates a boolean if the second but top float is smaller than the top byte
    const val D_SMALLER: Byte = 0x04b // Syntax: D_SMALLER ; Calculates a boolean if the second but top double is smaller than the top byte

    const val B_BIGGER_EQ: Byte = 0x04c // Syntax: B_BIGGER_EQ ; Calculates a boolean if the second but top byte is bigger or equal than the top byte
    const val S_BIGGER_EQ: Byte = 0x04d // Syntax: S_BIGGER_EQ ; Calculates a boolean if the second but top short is bigger or equal than the top byte
    const val I_BIGGER_EQ: Byte = 0x04e // Syntax: I_BIGGER_EQ ; Calculates a boolean if the second but top integer is bigger or equal than the top byte
    const val L_BIGGER_EQ: Byte = 0x04f // Syntax: L_BIGGER_EQ ; Calculates a boolean if the second but top long is bigger or equal than the top byte
    const val F_BIGGER_EQ: Byte = 0x050 // Syntax: F_BIGGER_EQ ; Calculates a boolean if the second but top float is bigger or equal than the top byte
    const val D_BIGGER_EQ: Byte = 0x051 // Syntax: D_BIGGER_EQ ; Calculates a boolean if the second but top double is bigger or equal than the top byte

    const val B_SMALLER_EQ: Byte = 0x052 // Syntax: B_SMALLER_EQ ; Calculates a boolean if the second but top byte is smaller or equal than the top byte
    const val S_SMALLER_EQ: Byte = 0x053 // Syntax: S_SMALLER_EQ ; Calculates a boolean if the second but top short is smaller or equal than the top byte
    const val I_SMALLER_EQ: Byte = 0x054 // Syntax: I_SMALLER_EQ ; Calculates a boolean if the second but top integer is smaller or equal than the top byte
    const val L_SMALLER_EQ: Byte = 0x055 // Syntax: L_SMALLER_EQ ; Calculates a boolean if the second but top long is smaller or equal than the top byte
    const val F_SMALLER_EQ: Byte = 0x056 // Syntax: F_SMALLER_EQ ; Calculates a boolean if the second but top float is smaller or equal than the top byte
    const val D_SMALLER_EQ: Byte = 0x057 // Syntax: D_SMALLER_EQ ; Calculates a boolean if the second but top double is smaller or equal than the top byte

    const val BOOL_NOT: Byte = 0x058 // Syntax: BOOL_NOT ; Put the opposite of the top boolean onto the stack

    const val B_GET_GLOBAL: Byte = 0x059 // Syntax: B_GET_GLOBAL, u4 position ; Get a global byte at a given position
    const val S_GET_GLOBAL: Byte = 0x05a // Syntax: S_GET_GLOBAL, u4 position ; Get a global short at a given position
    const val I_GET_GLOBAL: Byte = 0x05b // Syntax: I_GET_GLOBAL, u4 position ; Get a global int at a given position
    const val L_GET_GLOBAL: Byte = 0x05c // Syntax: L_GET_GLOBAL, u4 position ; Get a global long at a given position

    const val B_GET_GLOBAL_DYNAMIC: Byte = 0x05d // Syntax: B_GET_GLOBAL ; Get a global byte at a given position (position is the top stack integer)
    const val S_GET_GLOBAL_DYNAMIC: Byte = 0x05e // Syntax: S_GET_GLOBAL ; Get a global short at a given position (position is the top stack integer)
    const val I_GET_GLOBAL_DYNAMIC: Byte = 0x05f // Syntax: I_GET_GLOBAL ; Get a global int at a given position (position is the top stack integer)
    const val L_GET_GLOBAL_DYNAMIC: Byte = 0x060 // Syntax: L_GET_GLOBAL ; Get a global long at a given position (position is the top stack integer)

    const val B_STORE_GLOBAL: Byte = 0x061 // Syntax: B_GET_GLOBAL, u4 position ; Get a global byte at a given position
    const val S_STORE_GLOBAL: Byte = 0x062 // Syntax: S_GET_GLOBAL, u4 position ; Get a global short at a given position
    const val I_STORE_GLOBAL: Byte = 0x063 // Syntax: I_GET_GLOBAL, u4 position ; Get a global int at a given position
    const val L_STORE_GLOBAL: Byte = 0x064 // Syntax: L_GET_GLOBAL, u4 position ; Get a global long at a given position

    const val B_STORE_GLOBAL_DYNAMIC: Byte = 0x065 // Syntax: B_GET_GLOBAL ; Get a global byte at a given position (position is the top stack integer)
    const val S_STORE_GLOBAL_DYNAMIC: Byte = 0x066 // Syntax: S_GET_GLOBAL ; Get a global short at a given position (position is the top stack integer)
    const val I_STORE_GLOBAL_DYNAMIC: Byte = 0x067 // Syntax: I_GET_GLOBAL ; Get a global int at a given position (position is the top stack integer)
    const val L_STORE_GLOBAL_DYNAMIC: Byte = 0x068 // Syntax: L_GET_GLOBAL ; Get a global long at a given position (position is the top stack integer)

    const val B_NEG: Byte = 0x069 // Syntax: B_NEG ; Negate the top byte
    const val S_NEG: Byte = 0x06a // Syntax: S_NEG ; Negate the top short
    const val I_NEG: Byte = 0x06b // Syntax: I_NEG ; Negate the top integer
    const val L_NEG: Byte = 0x06c // Syntax: L_NEG ; Negate the top long
    const val F_NEG: Byte = 0x06d // Syntax: F_NEG ; Negate the top float
    const val D_NEG: Byte = 0x06e // Syntax: D_NEG ; Negate the top double

    const val B_ABS: Byte = 0x06f // Syntax: B_ABS ; Absolute value of the top byte
    const val S_ABS: Byte = 0x070 // Syntax: S_ABS ; Absolute value of the top short
    const val I_ABS: Byte = 0x071 // Syntax: I_ABS ; Absolute value of the top integer
    const val L_ABS: Byte = 0x072 // Syntax: L_ABS ; Absolute value of the top long
    const val F_ABS: Byte = 0x073 // Syntax: F_ABS ; Absolute value of the top float
    const val D_ABS: Byte = 0x074 // Syntax: D_ABS ; Absolute value of the top double

}

class ShasamblyInterpreter(
    memorySize: Int,
    bytes: ByteArray,
    position: Int
) {

    val memory = ByteArray(memorySize)
    val memorySize get() = memory.size
    val variableStackSizes = mutableListOf<Int>()
    val stack = ByteArray(1024)
    var stackSize = 0
    private var variableStackSize: Int = 0
    var variableAddress = bytes.size + 9
    val byteMap = createByteMap()
    var globalAddress = 0
    var globalsSize = 4
    private var startPointer = -1
    private var endPointer = -1
    var position = position + 4

    val exitCode : Int get() = memory.getInt(0)

    init {
        bytes.copyInto(memory, 4)
        memory[bytes.size + 4] = Opcodes.JUMP_STATIC
        memory.setInt(bytes.size + 5, 0)
    }

    private fun increaseGlobals(chunkSize: Int): Int {
        val addr = memorySize - globalsSize
        globalsSize += chunkSize
        // Addresses : addr - chunkSize + 1 .. addr
        return addr
    }

    fun declareGlobal(chunkSize: Int): Int {
        if(chunkSize < 4) throw Error("Chunk size must not be smaller than 4 (but is $chunkSize)")
        var addr = getReusedAddress(chunkSize)
        if(addr == -1) addr = increaseGlobals(chunkSize)
        return addr
    }

    private fun findFreeTableWithSize(size: Int): Int {
        var position = startPointer
        while(position != -1) {
            val csize = memory.getInt(position - 3)
            if(csize == size) return position
            position = memory.getInt(position - 15)
        }
        return -1
    }

    private fun findClosestFreeBelowTable(size: Int): Int {
        var position = endPointer
        while(position != -1) {
            if(memory.getInt(position - 3) <= size) return position
            position = memory.getInt(position - 19)
        }
        return -1
    }

    private fun findClosestFreeAboveTable(size: Int): Int {
        var position = endPointer
        var bestMatch = -1
        while(position != -1) {
            val csize = memory.getInt(position - 3)
            if(csize < size) break
            if(csize == size) return position
            if(csize >= size + 4) bestMatch = position
            position = memory.getInt(position - 19)
        }
        return bestMatch
    }

    private fun createFreeTable(size: Int): Int {
        val closestFreeTable = findClosestFreeBelowTable(size)
        if(closestFreeTable == -1) {
            if(this.startPointer == -1) {
                val addr = this.increaseGlobals(20)
                this.memory.setInt(addr - 3, size)
                this.memory.setInt(addr - 7, -1)
                this.memory.setInt(addr - 11, -1)
                this.memory.setInt(addr - 15, -1)
                this.memory.setInt(addr - 19, -1)
                this.startPointer = addr
                this.endPointer = addr
                return addr
            }
            else {
                val addr = declareGlobal(20)
                this.memory.setInt(addr - 3, size)
                this.memory.setInt(addr - 7, -1)
                this.memory.setInt(addr - 11, -1)
                this.memory.setInt(addr - 15, this.startPointer)
                this.memory.setInt(addr - 19, -1)
                this.memory.setInt(this.startPointer - 19, addr)
                this.startPointer = addr
                return addr
            }
        }
        else {
            val addr = declareGlobal(20)
            val next = this.memory.getInt(closestFreeTable - 15)
            this.memory.setInt(addr - 3, size)
            this.memory.setInt(addr - 7, -1)
            this.memory.setInt(addr - 11, -1)
            this.memory.setInt(addr - 15, next)
            this.memory.setInt(addr - 19, closestFreeTable)
            this.memory.setInt(closestFreeTable - 15, addr)
            if(next == -1) this.endPointer = addr
            else this.memory.setInt(addr - 19, addr)
            return addr
        }
    }

    private fun getFreeTable(size: Int): Int {
        var addr = findFreeTableWithSize(size)
        if(addr == -1) addr = createFreeTable(size)
        return addr
    }


    fun free(address: Int, csize: Int) {
        if(csize < 4) throw Error("Chunk size must be at least 4!")
        val table = getFreeTable(csize)
        val lastElement = memory.getInt(table - 11)
        if(lastElement == -1) {
            memory.setInt(table - 7, address)
            memory.setInt(table - 11, address)
        }
        else {
            memory.setInt(lastElement - 3, address)
            memory.setInt(table - 11, address)
        }
        memory.setInt(address - 3, -1)
    }

    fun removeEmptyFreeTable(address: Int) {
        val a = startPointer == address
        val b = endPointer == address
        if(a && b) {
            startPointer = -1
            endPointer = -1
        }
        else if(a && !b) {
            startPointer = memory.getInt(address - 15)
            memory.setInt(startPointer - 19, -1)
        }
        else if(b && !a) {
            endPointer = memory.getInt(address - 19)
            memory.setInt(endPointer - 15, -1)
        }
        else {
            val next = memory.getInt(address - 15)
            val before = memory.getInt(address - 19)
            memory.setInt(next - 19, before)
            memory.setInt(before - 15, next)
        }
        free(address, 20)
    }

    fun getReusedAddress(csize: Int): Int {
        var table = findClosestFreeAboveTable(csize)
        var size: Int
        var addr: Int
        while (true) {
            if (table == -1) return -1
            size = memory.getInt(table - 3)
            addr = memory.getInt(table - 7)
            if (addr == -1) {
                table = findClosestFreeAboveTable(size + 1)
                continue
            }
            else break
        }
        val next = memory.getInt(addr - 3)
        if(next == -1) {
            memory.setInt(table - 7, -1)
            memory.setInt(table - 11, -1)
            removeEmptyFreeTable(table)
        }
        else {
            memory.setInt(table - 7, next)
        }
        val dif = size - csize
        if(dif > 0) free(addr - csize, dif)
        return addr
    }

    fun finished(): Boolean = this.position == 0

    fun tick() {
        val pos = position
        val next = memory[position++]
        //println("Executing byte at position 0x${(position-1).toBytes().toHexString()}")
        try {
            (byteMap[next.toUByte().toInt()] ?: throw NoSuchElementException("Wrong opcode")).invoke()
        } catch (e: Throwable) {
            throw Error("Could not execute byte 0x${next.toBytes().toHexString()} " +
                    "at position 0x${(pos - 5).toBytes().toHexString()} (${pos-5})", e)
        }
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
        //println("Variable stack size updated to $variableAddress")
    }

    fun decr_variable_stack() {
        variableAddress -= variableStackSizes.removeLast()
        //println("Variable stack size updated to $variableAddress")
    }

    fun jump(address: Int) {
        //println("Jumping to ${address.toBytes().toHexString()}")
        if(address < 0 || address > memory.size) throw Error("Address 0x${address.toBytes().toHexString()} out of range")
        position = address
    }

    fun jump_static() {
        jump(read_int())
    }

    fun jump_dynamic() {
        jump(removeLastInt())
    }

    fun jump_if() {
        val addr = read_int()
        if(removeLastByte() != 0x00.toByte()) jump(addr)
    }

    fun invoke_native() {
        val native = read_short().toUShort().toInt()
        (nativeFunctions[native]
            ?: throw Error("Unknown native function 0x${native.toBytes().toHexString()} at position 0x${position.toBytes().toHexString()}"))
            .execute.invoke(this)
    }

    fun glob_addr() {
        val variable = read_short().toUShort().toInt()
        if(variable >= variableStackSize) throw IllegalArgumentException("Could not get global address of local $variable (Local stack size is only $variableStackSize)")
        addInt(variableAddress - variable)
    }

    fun b_get_local() {
        val variable = read_short().toUShort().toInt()
        sadd(memory[variableAddress - variable])
    }

    fun s_get_local() {
        val variable = read_short().toUShort().toInt()
        val addr = variableAddress - variable
        sadd(memory[addr])
        sadd(memory[addr - 1])
    }

    fun i_get_local() {
        val variable = read_short().toUShort().toInt()
        val addr = variableAddress - variable
        sadd(memory[addr])
        sadd(memory[addr - 1])
        sadd(memory[addr - 2])
        sadd(memory[addr - 3])
    }

    fun l_get_local() {
        val variable = read_short().toUShort().toInt()
        val addr = variableAddress - variable
        sadd(memory[addr])
        sadd(memory[addr - 1])
        sadd(memory[addr - 2])
        sadd(memory[addr - 3])
        sadd(memory[addr - 4])
        sadd(memory[addr - 5])
        sadd(memory[addr - 6])
        sadd(memory[addr - 7])
    }

    fun b_store_local() {
        val variable = read_short().toUShort().toInt()
        val addr = variableAddress - variable
        memory[addr] = sRemoveLast()
    }

    fun s_store_local() {
        val variable = read_short().toUShort().toInt()
        val addr = variableAddress - variable
        memory[addr - 1] = sRemoveLast()
        memory[addr] = sRemoveLast()
    }

    fun i_store_local() {
        val variable = read_short().toUShort().toInt()
        val addr = variableAddress - variable
        memory[addr - 3] = sRemoveLast()
        memory[addr - 2] = sRemoveLast()
        memory[addr - 1] = sRemoveLast()
        memory[addr] = sRemoveLast()
    }

    fun l_store_local() {
        val variable = read_short().toUShort().toInt()
        val addr = variableAddress - variable
        memory[addr - 7] = sRemoveLast()
        memory[addr - 6] = sRemoveLast()
        memory[addr - 5] = sRemoveLast()
        memory[addr - 4] = sRemoveLast()
        memory[addr - 3] = sRemoveLast()
        memory[addr - 2] = sRemoveLast()
        memory[addr - 1] = sRemoveLast()
        memory[addr] = sRemoveLast()
    }

    fun bpush() {
        sadd(read_byte())
    }

    fun spush() {
        addShort(read_short())
    }

    fun ipush() {
        addInt(read_int())
    }

    fun lpush() {
        addLong(read_long())
    }

    fun badd() {
        val v1 = sRemoveLast()
        val v0 = sRemoveLast()
        sadd((v0 + v1).toByte())
    }

    fun bsub() {
        val v1 = sRemoveLast()
        val v0 = sRemoveLast()
        sadd((v0 - v1).toByte())
    }

    fun bmul() {
        val v1 = sRemoveLast()
        val v0 = sRemoveLast()
        sadd((v0 * v1).toByte())
    }

    fun bdiv() {
        val v1 = sRemoveLast()
        val v0 = sRemoveLast()
        sadd((v0 / v1).toByte())
    }

    fun bmod() {
        val v1 = sRemoveLast()
        val v0 = sRemoveLast()
        sadd((v0 % v1).toByte())
    }

    fun sadd() {
        val v1 = removeLastShort()
        val v0 = removeLastShort()
        addShort((v0 + v1).toShort())
    }

    fun ssub() {
        val v1 = removeLastShort()
        val v0 = removeLastShort()
        addShort((v0 - v1).toShort())
    }

    fun smul() {
        val v1 = removeLastShort()
        val v0 = removeLastShort()
        addShort((v0 * v1).toShort())
    }

    fun sdiv() {
        val v1 = removeLastShort()
        val v0 = removeLastShort()
        addShort((v0 / v1).toShort())
    }

    fun smod() {
        val v1 = removeLastShort()
        val v0 = removeLastShort()
        addShort((v0 % v1).toShort())
    }

    fun iadd() {
        val v1 = removeLastInt()
        val v0 = removeLastInt()
        addInt(v0 + v1)
    }

    fun isub() {
        val v1 = removeLastInt()
        val v0 = removeLastInt()
        addInt(v0 - v1)
    }

    fun imul() {
        val v1 = removeLastInt()
        val v0 = removeLastInt()
        addInt(v0 * v1)
    }

    fun idiv() {
        val v1 = removeLastInt()
        val v0 = removeLastInt()
        addInt(v0 / v1)
    }

    fun imod() {
        val v1 = removeLastInt()
        val v0 = removeLastInt()
        addInt(v0 % v1)
    }

    fun ladd() {
        val v1 = removeLastLong()
        val v0 = removeLastLong()
        addLong(v0 + v1)
    }

    fun lsub() {
        val v1 = removeLastLong()
        val v0 = removeLastLong()
        addLong(v0 - v1)
    }

    fun lmul() {
        val v1 = removeLastLong()
        val v0 = removeLastLong()
        addLong(v0 * v1)
    }

    fun ldiv() {
        val v1 = removeLastLong()
        val v0 = removeLastLong()
        addLong(v0 / v1)
    }

    fun lmod() {
        val v1 = removeLastLong()
        val v0 = removeLastLong()
        addLong(v0 % v1)
    }

    fun fadd() {
        val v1 = removeLastFloat()
        val v0 = removeLastFloat()
        addFloat(v0 + v1)
    }

    fun fsub() {
        val v1 = removeLastFloat()
        val v0 = removeLastFloat()
        addFloat(v0 - v1)
    }

    fun fmul() {
        val v1 = removeLastFloat()
        val v0 = removeLastFloat()
        addFloat(v0 * v1)
    }

    fun fdiv() {
        val v1 = removeLastFloat()
        val v0 = removeLastFloat()
        addFloat(v0 / v1)
    }

    fun fmod() {
        val v1 = removeLastFloat()
        val v0 = removeLastFloat()
        addFloat(v0 % v1)
    }

    fun dadd() {
        val v1 = removeLastDouble()
        val v0 = removeLastDouble()
        addDouble(v0 + v1)
    }

    fun dsub() {
        val v1 = removeLastDouble()
        val v0 = removeLastDouble()
        addDouble(v0 - v1)
    }

    fun dmul() {
        val v1 = removeLastDouble()
        val v0 = removeLastDouble()
        addDouble(v0 * v1)
    }

    fun ddiv() {
        val v1 = removeLastDouble()
        val v0 = removeLastDouble()
        addDouble(v0 / v1)
    }

    fun dmod() {
        val v1 = removeLastDouble()
        val v0 = removeLastDouble()
        addDouble(v0 % v1)
    }

    fun beq() {
        val v1 = removeLastByte()
        val v0 = removeLastByte()
        addBoolean(v0 == v1)
    }

    fun seq() {
        val v1 = removeLastShort()
        val v0 = removeLastShort()
        addBoolean(v0 == v1)
    }

    fun ieq() {
        val v1 = removeLastInt()
        val v0 = removeLastInt()
        addBoolean(v0 == v1)
    }

    fun leq() {
        val v1 = removeLastLong()
        val v0 = removeLastLong()
        addBoolean(v0 == v1)
    }

    fun feq() {
        val v1 = removeLastFloat()
        val v0 = removeLastFloat()
        addBoolean(v0 == v1)
    }

    fun deq() {
        val v1 = removeLastDouble()
        val v0 = removeLastDouble()
        addBoolean(v0 == v1)
    }

    fun bbigger() {
        val v1 = removeLastByte()
        val v0 = removeLastByte()
        addBoolean(v0 > v1)
    }

    fun sbigger() {
        val v1 = removeLastShort()
        val v0 = removeLastShort()
        addBoolean(v0 > v1)
    }

    fun ibigger() {
        val v1 = removeLastInt()
        val v0 = removeLastInt()
        addBoolean(v0 > v1)
    }

    fun lbigger() {
        val v1 = removeLastLong()
        val v0 = removeLastLong()
        addBoolean(v0 > v1)
    }

    fun fbigger() {
        val v1 = removeLastFloat()
        val v0 = removeLastFloat()
        addBoolean(v0 > v1)
    }

    fun dbigger() {
        val v1 = removeLastDouble()
        val v0 = removeLastDouble()
        addBoolean(v0 > v1)
    }

    fun bsmaller() {
        val v1 = removeLastByte()
        val v0 = removeLastByte()
        addBoolean(v0 < v1)
    }

    fun ssmaller() {
        val v1 = removeLastShort()
        val v0 = removeLastShort()
        addBoolean(v0 < v1)
    }

    fun ismaller() {
        val v1 = removeLastInt()
        val v0 = removeLastInt()
        addBoolean(v0 < v1)
    }

    fun lsmaller() {
        val v1 = removeLastLong()
        val v0 = removeLastLong()
        addBoolean(v0 < v1)
    }

    fun fsmaller() {
        val v1 = removeLastFloat()
        val v0 = removeLastFloat()
        addBoolean(v0 < v1)
    }

    fun dsmaller() {
        val v1 = removeLastDouble()
        val v0 = removeLastDouble()
        addBoolean(v0 < v1)
    }

    fun bbiggereq() {
        val v1 = removeLastByte()
        val v0 = removeLastByte()
        addBoolean(v0 >= v1)
    }

    fun sbiggereq() {
        val v1 = removeLastShort()
        val v0 = removeLastShort()
        addBoolean(v0 >= v1)
    }

    fun ibiggereq() {
        val v1 = removeLastInt()
        val v0 = removeLastInt()
        addBoolean(v0 >= v1)
    }

    fun lbiggereq() {
        val v1 = removeLastLong()
        val v0 = removeLastLong()
        addBoolean(v0 >= v1)
    }

    fun fbiggereq() {
        val v1 = removeLastFloat()
        val v0 = removeLastFloat()
        addBoolean(v0 >= v1)
    }

    fun dbiggereq() {
        val v1 = removeLastDouble()
        val v0 = removeLastDouble()
        addBoolean(v0 >= v1)
    }

    fun bsmallereq() {
        val v1 = removeLastByte()
        val v0 = removeLastByte()
        addBoolean(v0 <= v1)
    }

    fun ssmallereq() {
        val v1 = removeLastShort()
        val v0 = removeLastShort()
        addBoolean(v0 <= v1)
    }

    fun ismallereq() {
        val v1 = removeLastInt()
        val v0 = removeLastInt()
        addBoolean(v0 <= v1)
    }

    fun lsmallereq() {
        val v1 = removeLastLong()
        val v0 = removeLastLong()
        addBoolean(v0 <= v1)
    }

    fun fsmallereq() {
        val v1 = removeLastFloat()
        val v0 = removeLastFloat()
        addBoolean(v0 <= v1)
    }

    fun dsmallereq() {
        val v1 = removeLastDouble()
        val v0 = removeLastDouble()
        addBoolean(v0 <= v1)
    }

    fun bnot() {
        addBoolean(removeLastByte() == 0.toByte())
    }

    fun b_get_global() {
        val pos = read_int()
        addByte(memory[pos])
    }

    fun s_get_global() {
        val pos = read_int()
        addShort(memory.getShort(pos - 1))
    }

    fun i_get_global() {
        val pos = read_int()
        addInt(memory.getInt(pos - 3))
    }

    fun l_get_global() {
        val pos = read_int()
        addLong(memory.getLong(pos - 7))
    }

    fun b_get_global_dynamic() {
        val pos = removeLastInt()
        addByte(memory.getByte(pos))
    }

    fun s_get_global_dynamic() {
        val pos = removeLastInt()
        addShort(memory.getShort(pos - 1))
    }

    fun i_get_global_dynamic() {
        val pos = removeLastInt()
        addInt(memory.getInt(pos - 3))
    }

    fun l_get_global_dynamic() {
        val pos = removeLastInt()
        addLong(memory.getLong(pos - 7))
    }

    fun b_store_global() {
        val pos = read_int()
        val value = removeLastByte()
        memory[pos] = value
    }

    fun s_store_global() {
        val pos = read_int()
        val value = removeLastShort()
        memory.setShort(pos - 1, value)
    }

    fun i_store_global() {
        val pos = read_int()
        val value = removeLastInt()
        memory.setInt(pos - 3, value)
    }

    fun l_store_global() {
        val pos = read_int()
        val value = removeLastLong()
        memory.setLong(pos - 7, value)
    }

    fun b_store_global_dynamic() {
        val pos = removeLastInt()
        val value = removeLastByte()
        memory[pos] = value
    }

    fun s_store_global_dynamic() {
        val pos = removeLastInt()
        val value = removeLastShort()
        memory.setShort(pos - 1, value)
    }

    fun i_store_global_dynamic() {
        val pos = removeLastInt()
        val value = removeLastInt()
        memory.setInt(pos - 3, value)
    }

    fun l_store_global_dynamic() {
        val pos = removeLastInt()
        val value = removeLastLong()
        memory.setLong(pos - 7, value)
    }

    fun b_neg() {
        addByte((-removeLastByte()).toByte())
    }

    fun s_neg() {
        addShort((-removeLastShort()).toShort())
    }

    fun i_neg() {
        addInt(-removeLastInt())
    }

    fun l_neg() {
        addLong(-removeLastLong())
    }

    fun f_neg() {
        addFloat(-removeLastFloat())
    }

    fun d_neg() {
        addDouble(-removeLastDouble())
    }

    fun b_abs() {
        addByte(abs(removeLastByte().toInt()).toByte())
    }

    fun s_abs() {
        addShort(abs(removeLastShort().toInt()).toShort())
    }

    fun i_abs() {
        addInt(abs(removeLastInt()))
    }

    fun l_abs() {
        addLong(abs(removeLastLong()))
    }

    fun f_abs() {
        addFloat(abs(removeLastFloat()))
    }

    fun d_abs() {
        addDouble(abs(removeLastDouble()))
    }

    inline fun byte() = memory[position]
    inline fun short() = memory.getShort(position)
    inline fun int() = memory.getInt(position)
    inline fun long() = memory.getLong(position)
    inline fun read_byte(): Byte {
        return memory[position++]
    }
    inline fun read_short(): Short {
        val v = short()
        position += 2
        return v
    }
    inline fun read_int(): Int {
        val v = int()
        position += 4
        return v
    }
    inline fun read_long(): Long {
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
        map[Opcodes.GLOB_ADDR.toInt()] = { this.glob_addr() }

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

        map[Opcodes.BOOL_NOT.toInt()] = { this.bnot() }

        map[Opcodes.B_GET_GLOBAL.toInt()] = { this.b_get_global() }
        map[Opcodes.S_GET_GLOBAL.toInt()] = { this.s_get_global() }
        map[Opcodes.I_GET_GLOBAL.toInt()] = { this.i_get_global() }
        map[Opcodes.L_GET_GLOBAL.toInt()] = { this.l_get_global() }

        map[Opcodes.B_GET_GLOBAL_DYNAMIC.toInt()] = { this.b_get_global_dynamic() }
        map[Opcodes.S_GET_GLOBAL_DYNAMIC.toInt()] = { this.s_get_global_dynamic() }
        map[Opcodes.I_GET_GLOBAL_DYNAMIC.toInt()] = { this.i_get_global_dynamic() }
        map[Opcodes.L_GET_GLOBAL_DYNAMIC.toInt()] = { this.l_get_global_dynamic() }

        map[Opcodes.B_STORE_GLOBAL.toInt()] = { this.b_store_global() }
        map[Opcodes.S_STORE_GLOBAL.toInt()] = { this.s_store_global() }
        map[Opcodes.I_STORE_GLOBAL.toInt()] = { this.i_store_global() }
        map[Opcodes.L_STORE_GLOBAL.toInt()] = { this.l_store_global() }

        map[Opcodes.B_STORE_GLOBAL_DYNAMIC.toInt()] = { this.b_store_global_dynamic() }
        map[Opcodes.S_STORE_GLOBAL_DYNAMIC.toInt()] = { this.s_store_global_dynamic() }
        map[Opcodes.I_STORE_GLOBAL_DYNAMIC.toInt()] = { this.i_store_global_dynamic() }
        map[Opcodes.L_STORE_GLOBAL_DYNAMIC.toInt()] = { this.l_store_global_dynamic() }

        map[Opcodes.B_NEG.toInt()] = { this.b_neg() }
        map[Opcodes.S_NEG.toInt()] = { this.s_neg() }
        map[Opcodes.I_NEG.toInt()] = { this.i_neg() }
        map[Opcodes.L_NEG.toInt()] = { this.l_neg() }
        map[Opcodes.F_NEG.toInt()] = { this.f_neg() }
        map[Opcodes.D_NEG.toInt()] = { this.d_neg() }

        map[Opcodes.B_ABS.toInt()] = { this.b_abs() }
        map[Opcodes.S_ABS.toInt()] = { this.s_abs() }
        map[Opcodes.I_ABS.toInt()] = { this.i_abs() }
        map[Opcodes.L_ABS.toInt()] = { this.l_abs() }
        map[Opcodes.F_ABS.toInt()] = { this.f_abs() }
        map[Opcodes.D_ABS.toInt()] = { this.d_abs() }

        return map
    }

    override fun toString(): String {
        return "ShasablyInterpreter{" +
                "position=$position," +
                "memory=${memory.toHexString()}," +
                "stack=${stack.toHexString()}}"
    }


    fun ShasamblyInterpreter.sadd(b: Byte) {
        this.stack[stackSize++] = b
        // println("New stack size: $stackSize")
    }

    fun addByte(v: Byte) {
        this.sadd(v)
    }

    fun addShort(v: Short) {
        this.sadd((v.toInt() shr 8).toByte())
        this.sadd((v and 0x00ff).toByte())
    }

    fun addInt(v: Int) {
        this.sadd((v shr 24 and 0xff).toByte())
        this.sadd((v shr 16 and 0xff).toByte())
        this.sadd((v shr 8 and 0xff).toByte())
        this.sadd((v and 0xff).toByte())
    }

    fun addLong(v: Long) {
        this.sadd((v shr 56 and 0xff).toByte())
        this.sadd((v shr 48 and 0xff).toByte())
        this.sadd((v shr 40 and 0xff).toByte())
        this.sadd((v shr 32 and 0xff).toByte())
        this.sadd((v shr 24 and 0xff).toByte())
        this.sadd((v shr 16 and 0xff).toByte())
        this.sadd((v shr 8 and 0xff).toByte())
        this.sadd((v and 0xff).toByte())
    }

    fun addFloat(v: Float) = addInt(v.toBits())
    fun addDouble(v: Double) = addLong(v.toBits())

    fun addBoolean(v: Boolean) = this.sadd(if(v) 0x1.toByte() else 0x0.toByte())

    fun sRemoveLast(): Byte {
        //println("New stack size: ${stackSize - 1}")
        return this.stack[--stackSize]
    }

    inline fun removeLastByte(): Byte
            = this.sRemoveLast()

    inline fun removeLastShort(): Short {
        val v1 = this.sRemoveLast()
        val v0 = this.sRemoveLast()
        return shortOf(v0, v1)
    }

    inline fun removeLastInt(): Int {
        val v3 = this.sRemoveLast()
        val v2 = this.sRemoveLast()
        val v1 = this.sRemoveLast()
        val v0 = this.sRemoveLast()
        return intOf(v0, v1, v2, v3)
    }

    inline fun removeLastLong(): Long {
        val v7 = this.sRemoveLast()
        val v6 = this.sRemoveLast()
        val v5 = this.sRemoveLast()
        val v4 = this.sRemoveLast()
        val v3 = this.sRemoveLast()
        val v2 = this.sRemoveLast()
        val v1 = this.sRemoveLast()
        val v0 = this.sRemoveLast()
        return longOf(v0, v1, v2, v3, v4, v5, v6, v7)
    }

    fun removeLastFloat(): Float
            = Float.fromBits(this.removeLastInt())

    fun removeLastDouble(): Double
            = Double.fromBits(this.removeLastLong())

}

