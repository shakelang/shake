@file:Suppress("nothing_to_inline", "unused")
package io.github.shakelang.shake.shasambly.interpreter

import io.github.shakelang.parseutils.bytes.*
import io.github.shakelang.shake.shasambly.interpreter.natives.Natives
import kotlin.experimental.and

abstract class ShasamblyInterpretingBase(
    memorySize: Int,
    bytes: ByteArray,
    position: Int
) {

    /**
     * The memory of the interpreter.
     */
    val memory = ByteArray(memorySize)

    /**
     * The size of the interpreters' memory.
     */
    val memorySize get() = memory.size
    var globalsSize = 16 + bytes.size + 8

    var running: Boolean = true
        private set

    // Memory:
    // 0x00 - 0x0F: Global variables
    // u4 - Free table start pointer
    // u4 - Free table end pointer
    // u4 - Stack pointer
    // u4 - Local variable stack pointer


    var freeTableStartPointer
        set(v) {
            memory.setInt(0, v)
        }
        get() = memory.getInt(0)

    var freeTableEndPointer
        set(v) {
            memory.setInt(4, v)
        }
        get() = memory.getInt(4)

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
        bytes.copyInto(memory, 16)
        memory.setBytes(16 + bytes.size, byteArrayOf(
            Opcodes.I_PUSH,
            *0.toBytes(),
            Opcodes.INVOKE_NATIVE,
            *Natives.exit.toBytes()
        ))

        freeTableStartPointer = -1
        freeTableEndPointer = -1
        stackPointer = memorySize - 1
        localStackPointer = -1

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
    // u4 next_element: The next element in the list
    // u4 prev_element: The previous element in the list
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


    fun sadd(b: Byte) {
        this.memory[stackPointer--] = b
    }

    fun spop(): Byte {
        return this.memory[++stackPointer]
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

    inline fun removeLastByte(): Byte
            = this.spop()

    inline fun removeLastShort(): Short {
        val v1 = this.spop()
        val v0 = this.spop()
        return shortOf(v0, v1)
    }

    inline fun removeLastInt(): Int {
        val v3 = this.spop()
        val v2 = this.spop()
        val v1 = this.spop()
        val v0 = this.spop()
        return intOf(v0, v1, v2, v3)
    }

    inline fun removeLastLong(): Long {
        val v7 = this.spop()
        val v6 = this.spop()
        val v5 = this.spop()
        val v4 = this.spop()
        val v3 = this.spop()
        val v2 = this.spop()
        val v1 = this.spop()
        val v0 = this.spop()
        return longOf(v0, v1, v2, v3, v4, v5, v6, v7)
    }

    fun removeLastFloat(): Float
            = Float.fromBits(this.removeLastInt())

    fun removeLastDouble(): Double
            = Double.fromBits(this.removeLastLong())

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
}