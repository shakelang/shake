@file:Suppress("nothing_to_inline", "unused")
package io.github.shakelang.shake.shasambly.interpreter

import io.github.shakelang.parseutils.bytes.*
import kotlin.experimental.and

abstract class ShasamblyInterpretingBase(
    memorySize: Int,
    bytes: ByteArray,
    position: Int
) {

    val memory = ByteArray(memorySize)
    val memorySize get() = memory.size
    val variableStackSizes = mutableListOf<Int>()
    val stack = ByteArray(1024)
    var stackSize = 0
    protected var variableStackSize: Int = 0
    var variableAddress = bytes.size + 9
    var globalAddress = 0
    var globalsSize = 8
    var startPointer = -1
        set(v) {
            memory.setInt(memory.size - 4, v)
            field = v
        }
    var endPointer = -1
        set(v) {
            memory.setInt(memory.size - 8, v)
            field = v
        }
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