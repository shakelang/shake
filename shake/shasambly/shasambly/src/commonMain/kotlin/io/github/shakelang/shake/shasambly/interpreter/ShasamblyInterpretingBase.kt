@file:Suppress("unused")
package io.github.shakelang.shake.shasambly.interpreter

import io.github.shakelang.parseutils.bytes.*
import io.github.shakelang.shake.shasambly.interpreter.natives.Natives
import kotlin.experimental.and

/**
 * The base of the shasambly interpreter.
 * It contains the core functions of the interpreter that store data and are used to manipulate the interprets data.
 * It does not contain any functions that are used to interpret the data or to execute opcodes.
 *
 * @author Nicolas Schmidt
 */
abstract class ShasamblyInterpretingBase(
    memorySize: Int,
    bytes: ByteArray,
    position: Int
) {

    val freeTable = FreeTableControllerObject()
    val stack = StackControllerObject()

    /**
     * The memory of the interpreter.
     *
     * Memory Arangement:
     *
     * 0x00 - 0x1f: Global variables:
     *
     * 0x00 - 0x03: u4 - Free table start pointer
     * 0x04 - 0x07: u4 - Free table end pointer
     * 0x08 - 0x0b: u4 - Stack pointer
     * 0x0c - 0x0f: u4 - Local variable stack pointer
     * 0x10 - 0x13: u4 - Instruction pointer
     * 0x14 - 0x17: u4 - Globals size
     * 0x18 - 0x1b: u4 - Unused
     * 0x1c - 0x1f: u4 - Unused
     * 0x20 - 0x20 + bytes.size: u1[] - Bytes
     * 0x20 + bytes.size: u8 - Instruction to terminate the program
     */
    val memory = ByteArray(memorySize)

    /**
     * The size of the interpreters' memory.
     */
    val memorySize get() = memory.size

    /**
     * The size of the global variable stack.
     */
    var globalsSize
        set(v) {
            memory.setInt(0x14, v)
        }
        get() = memory.getInt(0x14)


    var running: Boolean = true
        private set

    /**
     * The current instruction pointer.
     */
    var instructionPointer: Int
        set(v) {
            memory.setInt(0x10, v)
        }
        get() = memory.getInt(0x10)

    /**
     * The start pointer of the free table.
     */
    var freeTableStartPointer
        set(v) {
            memory.setInt(0, v)
        }
        get() = memory.getInt(0)

    /**
     * The end pointer of the free table.
     */
    var freeTableEndPointer
        set(v) {
            memory.setInt(4, v)
        }
        get() = memory.getInt(4)

    /**
     * The stack pointer.
     */
    var stackPointer
        set(v) {
            memory.setInt(8, v)
        }
        get() = memory.getInt(8)

    var localStackPointer
        set(v) {
            memory.setInt(12, v)
        }
        get() = memory.getInt(12)

    var variableStackSize
        set(v) {
            memory.setInt(localStackPointer - 4, v)
        }
        get() = memory.getInt(localStackPointer - 4)

    var position = position + 16
    var exitCode : Int = 0 // TODO

    init {
        bytes.copyInto(memory, 32)

        freeTableStartPointer = -1
        freeTableEndPointer = -1
        stackPointer = memorySize - 1
        localStackPointer = -1
        globalsSize = 32 + bytes.size + 8

        memory.setBytes(32 + bytes.size, byteArrayOf(
            Opcodes.I_PUSH,
            *0.toBytes(),
            Opcodes.INVOKE_NATIVE,
            *Natives.exit.toBytes()
        ))

        //memory[bytes.size + 4] = Opcodes.JUMP_STATIC
        //memory.setInt(bytes.size + 5, 0)
    }

    fun increaseGlobals(chunkSize: Int): Int {
        if(chunkSize < 4) throw Error("Chunk size must not be smaller than 4 (but is $chunkSize)")
        val addr = globalsSize
        globalsSize += chunkSize
        return addr
    }

    fun declareGlobal(chunkSize: Int): Int {
        if(chunkSize < 4) throw Error("Chunk size must not be smaller than 4 (but is $chunkSize)")
        var addr = getReusedAddress(chunkSize)
        if(addr == -1) addr = increaseGlobals(chunkSize)
        return addr
    }

    // Free Table
    //
    // The free table is a list of addresses that are available for reuse.
    // The free table contains firstly a linked list of the different sizes of chunks that are available.
    // In this linked list the elements are sorted by chunk_size.
    // These linked list is structured as follows:
    //
    // [Size: 20 bytes]
    // u4 chunk_size: The size of the chunks
    // u4 first_chunk: The first chunk
    // u4 last_chunk: The last chunk
    // u4 next_table: The next table in the list
    // u4 prev_table: The previous table in the list
    //
    // The chunks are stored in a linked list then (The first chunk is stored in the chunk table)
    // The chunk just contains four bytes of data, the address of the next chunk.
    // The last chunk contains a zero address.
    // The rest of the bytes are just ignored. When the chunk is reused, these addresses will also reused
    // Caused by the link to the next element being 4 bytes in size it is not possible to add sectors that are
    // smaller than 4 bytes.


    fun findFreeTableWithSize(size: Int): Int {
        var position = freeTableStartPointer
        while(position != -1) {
            val csize = memory.getInt(position)
            if(csize == size) return position
            position = memory.getInt(position + 12)
        }
        return -1
    }

    fun findClosestFreeTableBelow(size: Int): Int {
        var position = freeTableEndPointer
        while(position != -1) {
            if(memory.getInt(position) <= size) return position
            position = memory.getInt(position + 16)
        }
        return -1
    }

    fun findBestAboveMatch(size: Int): Int {
        var position = freeTableEndPointer
        var bestMatch = -1
        while(position != -1) {
            val csize = memory.getInt(position)
            if(csize < size) break
            if(csize == size) return position
            if(csize >= size + 4) bestMatch = position
            position = memory.getInt(position + 16)
        }
        return bestMatch
    }

    fun createFreeTable(size: Int): Int {
        val closestFreeTable = findClosestFreeTableBelow(size)
        if(closestFreeTable == -1) {
            if(this.freeTableStartPointer == -1) { // This is the first defined free table
                val addr = this.increaseGlobals(20)
                this.memory.setInt(addr, size)
                this.memory.setInt(addr + 4, -1)
                this.memory.setInt(addr + 8, -1)
                this.memory.setInt(addr + 12, -1)
                this.memory.setInt(addr + 16, -1)
                this.freeTableStartPointer = addr
                this.freeTableEndPointer = addr
                return addr
            }
            else { // There is already a free table, but this is the free table with the smallest size
                val addr = declareGlobal(20)
                this.memory.setInt(addr, size)
                this.memory.setInt(addr + 4, -1)
                this.memory.setInt(addr + 8, -1)
                this.memory.setInt(addr + 12, this.freeTableStartPointer)
                this.memory.setInt(addr + 16, -1)
                this.memory.setInt(this.freeTableStartPointer + 16, addr)
                this.freeTableStartPointer = addr
                return addr
            }
        }
        else { // There is a table with a smaller size than the one we want to create
            val addr = declareGlobal(20)
            val next = this.memory.getInt(closestFreeTable + 12)
            this.memory.setInt(addr, size)
            this.memory.setInt(addr + 4, -1)
            this.memory.setInt(addr + 8, -1)
            this.memory.setInt(addr + 12, next)
            this.memory.setInt(addr + 16, closestFreeTable)
            this.memory.setInt(closestFreeTable + 12, addr)
            if(next == -1) this.freeTableEndPointer = addr
            else this.memory.setInt(addr - 19, addr)
            return addr
        }
    }

    fun getFreeTable(size: Int): Int {
        var addr = findFreeTableWithSize(size)
        if(addr == -1) addr = createFreeTable(size)
        return addr
    }


    fun free(address: Int, csize: Int) {
        if(csize < 4) throw Error("Chunk size must be at least 4!")
        val table = getFreeTable(csize)
        val lastElement = memory.getInt(table + 8)
        if(lastElement == -1) {
            memory.setInt(table + 4, address)
            memory.setInt(table + 8, address)
        }
        else {
            memory.setInt(lastElement, address)
            memory.setInt(table + 8, address)
        }
        memory.setInt(address, -1)
    }

    fun removeEmptyFreeTable(address: Int) {
        val a = freeTableStartPointer == address
        val b = freeTableEndPointer == address
        if(a && b) {
            freeTableStartPointer = -1
            freeTableEndPointer = -1
        }
        else if(a && !b) {
            freeTableStartPointer = memory.getInt(address + 12)
            memory.setInt(freeTableStartPointer + 16, -1)
        }
        else if(b && !a) {
            freeTableEndPointer = memory.getInt(address + 16)
            memory.setInt(freeTableEndPointer + 12, -1)
        }
        else {
            val next = memory.getInt(address + 12)
            val before = memory.getInt(address + 16)
            memory.setInt(next + 16, before)
            memory.setInt(before + 12, next)
        }
        free(address, 20)
    }

    fun getReusedAddress(csize: Int): Int {
        var table = findBestAboveMatch(csize)
        var size: Int
        var addr: Int
        while (true) {
            if (table == -1) return -1
            size = memory.getInt(table)
            addr = memory.getInt(table + 4)
            if (addr == -1) {
                table = findBestAboveMatch(size + 1)
                continue
            }
            else break
        }
        val next = memory.getInt(addr)
        if(next == -1) {
            memory.setInt(table + 4, -1)
            memory.setInt(table + 8, -1)
            removeEmptyFreeTable(table)
        }
        else {
            memory.setInt(table + 4, next)
        }
        val dif = size - csize
        if(dif > 0) free(addr - csize, dif)
        return addr
    }

    fun findFreeEntry(address: Int, startAddress: Int, csize: Int): Int {
        var addr = startAddress
        while(addr != -1) {
            if(address in addr until addr +  csize) return addr
            addr = memory.getInt(addr)
        }
        return -1
    }

    fun findFree(address: Int): IntArray {
        var table = freeTableStartPointer
        while(table != -1) {
            val csize = memory.getInt(table)
            val first = memory.getInt(table + 4)
            val addr = findFreeEntry(address, first, csize)
            if(addr != -1) return intArrayOf(addr, csize, table)
            table = memory.getInt(table + 12)
        }
        return intArrayOf(-1, -1, -1)
    }

    fun byte() = memory[position]
    fun short() = memory.getShort(position)
    fun int() = memory.getInt(position)
    fun long() = memory.getLong(position)
    fun read_byte(): Byte {
        return memory[position++]
    }
    fun read_short(): Short {
        val v = short()
        position += 2
        return v
    }
    fun read_int(): Int {
        val v = int()
        position += 4
        return v
    }
    fun read_long(): Long {
        val v = long()
        position += 8
        return v
    }

    fun incrLocalStack(size: Int) {
        val old = localStackPointer
        val new = declareGlobal(size + 8)
        localStackPointer = new + 8
        memory.setInt(new, old)
        memory.setInt(new + 4, size)
    }

    fun decrLocalStack() {
        val size = variableStackSize
        val old = localStackPointer
        val new = memory.getInt(old - 8)
        localStackPointer = new
        free(old - 8, size + 8)
    }

    fun exitProcess(code: Int) {
        this.exitCode = code
        this.running = false
    }

    inner class StackControllerObject {

        /**
         * Push a byte on top of the stack
         */
        fun push(b: Byte) {
            this@ShasamblyInterpretingBase.memory[stackPointer--] = b
        }

        /**
         * Pop a byte from the stack
         */
        fun pop(): Byte {
            return this@ShasamblyInterpretingBase.memory[++stackPointer]
        }

        /**
         * Push a byte on top of the stack
         */
        fun pushByte(v: Byte) {
            this.push(v)
        }

        /**
         * Push a short on top of the stack
         */
        fun pushShort(v: Short) {
            this.push((v.toInt() shr 8).toByte())
            this.push((v and 0x00ff).toByte())
        }

        /**
         * Push an int on top of the stack
         */
        fun pushInt(v: Int) {
            this.push((v shr 24 and 0xff).toByte())
            this.push((v shr 16 and 0xff).toByte())
            this.push((v shr 8 and 0xff).toByte())
            this.push((v and 0xff).toByte())
        }

        /**
         * Push a long on top of the stack
         */
        fun pushLong(v: Long) {
            this.push((v shr 56 and 0xff).toByte())
            this.push((v shr 48 and 0xff).toByte())
            this.push((v shr 40 and 0xff).toByte())
            this.push((v shr 32 and 0xff).toByte())
            this.push((v shr 24 and 0xff).toByte())
            this.push((v shr 16 and 0xff).toByte())
            this.push((v shr 8 and 0xff).toByte())
            this.push((v and 0xff).toByte())
        }

        /**
         * Push a float on top of the stack
         */
        fun pushFloat(v: Float) = pushInt(v.toBits())

        /**
         * Push a double on top of the stack
         */
        fun pushDouble(v: Double) = pushLong(v.toBits())

        /**
         * Push a boolean on top of the stack
         */
        fun pushBoolean(v: Boolean) = this.push(if(v) 0x1.toByte() else 0x0.toByte())

        /**
         * Pop the top byte from the stack
         */
        fun popByte(): Byte
                = this.pop()

        /**
         * Pop the top short from the stack
         */
        fun popShort(): Short {
            val v1 = this.pop()
            val v0 = this.pop()
            return shortOf(v0, v1)
        }

        /**
         * Pop the top int from the stack
         */
        fun popInt(): Int {
            val v3 = this.pop()
            val v2 = this.pop()
            val v1 = this.pop()
            val v0 = this.pop()
            return intOf(v0, v1, v2, v3)
        }

        /**
         * Pop the top long from the stack
         */
        fun popLong(): Long {
            val v7 = this.pop()
            val v6 = this.pop()
            val v5 = this.pop()
            val v4 = this.pop()
            val v3 = this.pop()
            val v2 = this.pop()
            val v1 = this.pop()
            val v0 = this.pop()
            return longOf(v0, v1, v2, v3, v4, v5, v6, v7)
        }

        /**
         * Pop the top float from the stack
         */
        fun popFloat(): Float
                = Float.fromBits(this.popInt())

        /**
         * Pop the top double from the stack
         */
        fun popDouble(): Double
                = Double.fromBits(this.popLong())

    }

    inner class FreeTableControllerObject {

    }



}