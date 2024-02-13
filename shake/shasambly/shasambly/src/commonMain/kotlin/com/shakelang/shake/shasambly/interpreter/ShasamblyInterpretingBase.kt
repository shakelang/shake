@file:Suppress("unused")

package com.shakelang.shake.shasambly.interpreter

import com.shakelang.shake.shasambly.interpreter.natives.Natives
import com.shakelang.util.parseutils.ElementLoopController
import com.shakelang.util.parseutils.IndexedElementLoopController
import com.shakelang.util.primitives.bytes.*
import kotlin.experimental.and

/**
 * The base of the shasambly interpreter.
 * It contains the core functions of the interpreter that store data and are used to manipulate the interprets data.
 * It does not contain any functions that are used to interpret the data or to execute opcodes.
 */
@Suppress("ktlint:standard:function-naming")
abstract class ShasamblyInterpretingBase(
    memorySize: Int,
    bytes: ByteArray,
    position: Int = 0,
) {

    val freeTable = FreeTableControllerObject()
    val stack = StackControllerObject()

    /**
     * The memory of the interpreter.
     *
     * Memory Arrangement:
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

    /**
     * @deprecation This function is deprecated.
     */
    var position
        set(v) {
            this.instructionPointer = v
        }
        get() = this.instructionPointer

    var exitCode: Int = 0 // TODO

    init {
        bytes.copyInto(memory, 32)
        instructionPointer = 32 + position

        freeTableStartPointer = -1
        freeTableEndPointer = -1
        stackPointer = memorySize - 1
        localStackPointer = -1
        globalsSize = 32 + bytes.size + 8

        memory.setBytes(
            32 + bytes.size,
            byteArrayOf(
                Opcodes.I_PUSH,
                *0.toBytes(),
                Opcodes.INVOKE_NATIVE,
                *Natives.exit.toBytes(),
            ),
        )

        // memory[bytes.size + 4] = Opcodes.JUMP_STATIC
        // memory.setInt(bytes.size + 5, 0)
    }

    fun increaseGlobals(chunkSize: Int): Int {
        if (chunkSize < 4) throw Error("Chunk size must not be smaller than 4 (but is $chunkSize)")
        val addr = globalsSize
        globalsSize += chunkSize
        return addr
    }

    fun declareGlobal(chunkSize: Int): Int {
        if (chunkSize < 4) throw Error("Chunk size must not be smaller than 4 (but is $chunkSize)")
        var addr = freeTable.getReusedAddress(chunkSize)
        if (addr == -1) addr = increaseGlobals(chunkSize)
        return addr
    }

    fun free(address: Int, csize: Int) {
        if (csize < 4) throw Error("Chunk size must be at least 4!")
        val table = freeTable.getWithSize(csize)
        val lastElement = memory.getInt(table + 8)
        if (lastElement == -1) {
            memory.setInt(table + 4, address)
            memory.setInt(table + 8, address)
        } else {
            memory.setInt(lastElement, address)
            memory.setInt(table + 8, address)
        }
        memory.setInt(address, -1)
    }

    fun byte() = memory[instructionPointer]
    fun short() = memory.getShort(instructionPointer)
    fun int() = memory.getInt(instructionPointer)
    fun long() = memory.getLong(instructionPointer)

    fun read_byte(): Byte {
        return memory[instructionPointer++]
    }

    fun read_short(): Short {
        val v = short()
        instructionPointer += 2
        return v
    }

    fun read_int(): Int {
        val v = int()
        instructionPointer += 4
        return v
    }

    fun read_long(): Long {
        val v = long()
        instructionPointer += 8
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
            this.push((v and 0x00ff).toByte())
            this.push((v.toInt() shr 8).toByte())
        }

        /**
         * Push an int on top of the stack
         */
        fun pushInt(v: Int) {
            this.push((v and 0xff).toByte())
            this.push((v shr 8 and 0xff).toByte())
            this.push((v shr 16 and 0xff).toByte())
            this.push((v shr 24 and 0xff).toByte())
        }

        /**
         * Push a long on top of the stack
         */
        fun pushLong(v: Long) {
            this.push((v and 0xff).toByte())
            this.push((v shr 8 and 0xff).toByte())
            this.push((v shr 16 and 0xff).toByte())
            this.push((v shr 24 and 0xff).toByte())
            this.push((v shr 32 and 0xff).toByte())
            this.push((v shr 40 and 0xff).toByte())
            this.push((v shr 48 and 0xff).toByte())
            this.push((v shr 56 and 0xff).toByte())
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
        fun pushBoolean(v: Boolean) = this.push(if (v) 0x1.toByte() else 0x0.toByte())

        /**
         * Pop the top byte from the stack
         */
        fun popByte(): Byte = this.pop()

        /**
         * Pop the top short from the stack
         */
        fun popShort(): Short {
            return shortOf(this.pop(), this.pop())
        }

        /**
         * Pop the top int from the stack
         */
        fun popInt(): Int {
            return intOf(this.pop(), this.pop(), this.pop(), this.pop())
        }

        /**
         * Pop the top long from the stack
         */
        fun popLong(): Long {
            return longOf(
                this.pop(),
                this.pop(),
                this.pop(),
                this.pop(),
                this.pop(),
                this.pop(),
                this.pop(),
                this.pop(),
            )
        }

        /**
         * Pop the top float from the stack
         */
        fun popFloat(): Float = Float.fromBits(this.popInt())

        /**
         * Pop the top double from the stack
         */
        fun popDouble(): Double = Double.fromBits(this.popLong())
    }

    /**
     * Free Table
     *
     * The free table is a list of addresses that are available for reuse.
     * The free table contains firstly a linked list of the different sizes of chunks that are available.
     * In this linked list the elements are sorted by chunk_size.
     * This linked list is structured as follows:
     *
     * [Size: 20 bytes]
     * u4 chunk_size: The size of the chunks
     * u4 first_chunk: The first chunk
     * u4 last_chunk: The last chunk
     * u4 next_table: The next table in the list
     * u4 prev_table: The previous table in the list
     *
     * The chunks are stored in a linked list then (The first chunk is stored in the chunk table)
     * The chunk just contains four bytes of data, the address of the next chunk.
     * The last chunk contains a zero address.
     * The rest of the bytes are just ignored. When the chunk is reused, these addresses will also reused
     * Caused by the link to the next element being 4 bytes in size it is not possible to add sectors that are
     * smaller than 4 bytes.
     */
    inner class FreeTableControllerObject : MutableList<FreeTableControllerObject.FreeTableEntry> {

        /**
         * Find a free-table with a given size
         * Returns -1 if no table is found
         * If you want to create a new table if it does not exist use [getWithSize]
         *
         * @param size The size of the table to find
         * @return The index of the table or -1 if it does not exist
         *
         * @see FreeTableControllerObject
         */
        fun findWithSize(size: Int): Int {
            var position = freeTableStartPointer
            while (position != -1) {
                val csize = memory.getInt(position)
                if (csize == size) return position
                position = memory.getInt(position + 12)
            }
            return -1
        }

        /**
         * Finds the free table below that is closest to the given size
         *
         * @param size The size to find the closest table to
         * @return The index of the table or -1 if there is are no matching tables
         *
         * @see FreeTableControllerObject
         */
        fun findClosestBelow(size: Int): Int {
            var position = freeTableEndPointer
            while (position != -1) {
                if (memory.getInt(position) <= size) return position
                position = memory.getInt(position + 16)
            }
            return -1
        }

        /**
         * Finds the free table above that is the best match
         * (So it has to be exactly the given size or the given size + 4 + n (smaller n will be better))
         * The Idea is that we need at least 4 bytes to store in the free table if we have data left.
         *
         * @param size The size to find the closest table to
         * @return The index of the table or -1 if there is are no matching tables
         *
         * @see FreeTableControllerObject
         */
        fun findBestAbove(size: Int): Int {
            var position = freeTableEndPointer
            var bestMatch = -1
            while (position != -1) {
                val csize = memory.getInt(position)
                if (csize < size) break
                if (csize == size) return position
                if (csize >= size + 4) bestMatch = position
                position = memory.getInt(position + 16)
            }
            return bestMatch
        }

        /**
         * Creates a new free table element with the given size
         * This method should normally not be used externally, use [getWithSize] instead
         *
         * @param size The size of the table to create
         * @return The index of the table or -1 if it could not be created
         *
         * @see FreeTableControllerObject
         */
        fun create(size: Int): Int {
            val closestFreeTable = findClosestBelow(size)
            if (closestFreeTable == -1) {
                if (freeTableStartPointer == -1) { // This is the first defined free table
                    val addr = increaseGlobals(20)
                    memory.setInt(addr, size)
                    memory.setInt(addr + 4, -1)
                    memory.setInt(addr + 8, -1)
                    memory.setInt(addr + 12, -1)
                    memory.setInt(addr + 16, -1)
                    freeTableStartPointer = addr
                    freeTableEndPointer = addr
                    return addr
                } else { // There is already a free table, but this is the free table with the smallest size
                    val addr = declareGlobal(20)
                    memory.setInt(addr, size)
                    memory.setInt(addr + 4, -1)
                    memory.setInt(addr + 8, -1)
                    memory.setInt(addr + 12, freeTableStartPointer)
                    memory.setInt(addr + 16, -1)
                    memory.setInt(freeTableStartPointer + 16, addr)
                    freeTableStartPointer = addr
                    return addr
                }
            } else { // There is a table with a smaller size than the one we want to create
                val addr = declareGlobal(20)
                val next = memory.getInt(closestFreeTable + 12)
                memory.setInt(addr, size)
                memory.setInt(addr + 4, -1)
                memory.setInt(addr + 8, -1)
                memory.setInt(addr + 12, next)
                memory.setInt(addr + 16, closestFreeTable)
                memory.setInt(closestFreeTable + 12, addr)
                if (next == -1) {
                    freeTableEndPointer = addr
                } else {
                    memory.setInt(addr - 19, addr)
                }
                return addr
            }
        }

        /**
         * Returns a free table with the given size
         * If there is no free table with the given size, a new one will be created
         *
         * @param size The size of the table to get
         * @return The index of the table
         *
         * @see FreeTableControllerObject
         */
        fun getWithSize(size: Int): Int {
            var addr = findWithSize(size)
            if (addr == -1) addr = create(size)
            return addr
        }

        /**
         * Returns a free table at the given address
         *
         * @param index The address of the table to get
         * @return The table
         *
         * @see FreeTableControllerObject
         */
        override operator fun get(index: Int): FreeTableEntry {
            return FreeTableEntry(index)
        }

        /**
         * Removes an empty entry from the free table
         *
         * @param address The address of the table to remove
         *
         * @see FreeTableControllerObject
         */
        fun removeEmptyFreeTable(address: Int) {
            val a = freeTableStartPointer == address
            val b = freeTableEndPointer == address
            if (a && b) {
                freeTableStartPointer = -1
                freeTableEndPointer = -1
            } else if (a && !b) {
                freeTableStartPointer = memory.getInt(address + 12)
                memory.setInt(freeTableStartPointer + 16, -1)
            } else if (b && !a) {
                freeTableEndPointer = memory.getInt(address + 16)
                memory.setInt(freeTableEndPointer + 12, -1)
            } else {
                val next = memory.getInt(address + 12)
                val before = memory.getInt(address + 16)
                memory.setInt(next + 16, before)
                memory.setInt(before + 12, next)
            }
            free(address, 20)
        }

        /**
         * Trys to reuse an address from the free table
         * If there is no matching address, it will return -1
         *
         * @param csize the expected chunk size of the reused element
         * @return the address of the reused element or -1 if there is no matching address
         *
         * @see FreeTableControllerObject
         */
        fun getReusedAddress(csize: Int): Int {
            var table = findBestAbove(csize)
            var size: Int
            var addr: Int
            while (true) {
                if (table == -1) return -1
                size = memory.getInt(table)
                addr = memory.getInt(table + 4)
                if (addr == -1) {
                    table = findBestAbove(size + 1)
                    continue
                } else {
                    break
                }
            }
            val next = memory.getInt(addr)
            if (next == -1) {
                memory.setInt(table + 4, -1)
                memory.setInt(table + 8, -1)
                removeEmptyFreeTable(table)
            } else {
                memory.setInt(table + 4, next)
            }
            val dif = size - csize
            if (dif > 0) free(addr - csize, dif)
            return addr
        }

        /**
         * Runs through a free table entry and checks if a given address is contained
         * If the address is contained, it will return the address of the start of the entry, otherwise it will return -1
         *
         * @param address The address to search
         * @param startAddress The start address of the free table entry
         * @param csize the chunk size of the entry
         * @return The address of the start of the entry or [-1, -1] if the address is not contained
         *
         * @see FreeTableControllerObject
         */
        fun findFreeEntry(address: Int, startAddress: Int, csize: Int): IntArray {
            var addr = startAddress
            var before = -1
            while (addr != -1) {
                if (address in addr until addr + csize) return intArrayOf(before, addr)
                before = addr
                addr = memory.getInt(addr)
            }
            return intArrayOf(-1, -1)
        }

        /**
         * Checks if a given address is contained in the free table
         * If the address is contained, it will return the address of the start of the entry, the size of the chunk,
         * the chained element before and the table-element, if not it will just return null
         *
         * @param address The address to search
         * @return A FreeElement object containing the information about the entry or null if the address is not freed
         *
         * @see FreeTableControllerObject
         */
        fun findFree(address: Int): FreeElement? {
            var table = freeTableStartPointer
            while (table != -1) {
                val csize = memory.getInt(table)
                val first = memory.getInt(table + 4)
                val data = findFreeEntry(address, first, csize)
                if (data[0] != -1) return FreeElement(address = data[0], size = csize, before = data[1], table = table)
                table = memory.getInt(table + 12)
            }
            return null
        }

        /**
         * Loops over the free table entries
         *
         * @param action The action to perform on each entry
         */
        fun forEach(action: (FreeTableEntry) -> Unit) {
            var address = freeTableStartPointer
            while (address != -1) {
                action(FreeTableEntry(address))
                address = memory.getInt(address + 12)
            }
        }

        /**
         * Loops over the free table entries
         *
         * @param action The action to perform on each entry
         */
        fun forEachControlled(action: (ElementLoopController<FreeTableEntry>) -> Unit) {
            var address = freeTableStartPointer
            while (address != -1) {
                val table = FreeTableEntry(address)
                val controller = ElementLoopController(table)
                action(controller)
                if (controller.isBreak) break
                address = memory.getInt(address + 12)
            }
        }

        /**
         * Loops over the free table entries
         *
         * @param action The action to perform on each entry
         */
        fun forEachIndexed(action: (IndexedElementLoopController<FreeTableEntry>) -> Unit) {
            var address = freeTableStartPointer
            var index = 0
            while (address != -1) {
                val table = FreeTableEntry(address)
                val controller = IndexedElementLoopController(table, index)
                action(controller)
                if (controller.isBreak) break
                address = memory.getInt(address + 12)
                index++
            }
        }

        /**
         * A Utility class to help you programmatically working with FreeTable entries
         *
         * @see FreeTableControllerObject for more information
         */
        open inner class FreeTableEntry(
            val address: Int,
        ) {

            /**
             * The size of the entry
             */
            var size
                get() = memory.getInt(address)
                set(value) {
                    memory.setInt(address, value)
                }

            /**
             * The address of the first child of the entry
             */
            var firstAddress
                get() = memory.getInt(address + 4)
                set(value) {
                    memory.setInt(address + 4, value)
                }

            /**
             * The address of the last child of the entry
             */
            var lastAddress
                get() = memory.getInt(address + 8)
                set(value) {
                    memory.setInt(address + 8, value)
                }

            /**
             * The address of the next entry in the free table
             */
            var nextAddress
                get() = memory.getInt(address + 12)
                set(value) {
                    memory.setInt(address + 12, value)
                }

            /**
             * The address of the previous entry in the free table
             */
            var beforeAddress
                get() = memory.getInt(address + 16)
                set(value) {
                    memory.setInt(address + 16, value)
                }

            /**
             * The next entry in the free table
             */
            var next: FreeTableEntry?
                get() = if (nextAddress == -1) null else FreeTableEntry(nextAddress)
                set(value) {
                    nextAddress = value?.address ?: -1
                }

            /**
             * The previous entry in the free table
             */
            var before: FreeTableEntry?
                get() = if (beforeAddress == -1) null else FreeTableEntry(beforeAddress)
                set(value) {
                    beforeAddress = value?.address ?: -1
                }

            /**
             * Loops over the chunks of this entry
             */
            fun forEach(action: (Int) -> Unit) {
                var addr = firstAddress
                while (addr != -1) {
                    action(addr)
                    addr = memory.getInt(addr)
                }
            }

            /**
             * Loops over the chunks of this entry
             */
            fun forEachControlled(action: (ElementLoopController<Int>) -> Unit) {
                var addr = firstAddress
                while (addr != -1) {
                    val controller = ElementLoopController(addr)
                    action(controller)
                    if (controller.isBreak) break
                    addr = memory.getInt(addr)
                }
            }

            /**
             * Loops over the chunks of this entry
             */
            fun forEachIndexed(action: (IndexedElementLoopController<Int>) -> Unit) {
                var addr = firstAddress
                var index = 0
                while (addr != -1) {
                    val controller = IndexedElementLoopController(addr, index)
                    action(controller)
                    if (controller.isBreak) break
                    addr = memory.getInt(addr)
                    index++
                }
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other !is FreeTableEntry) return false
                return address == other.address &&
                    size == other.size &&
                    firstAddress == other.firstAddress &&
                    lastAddress == other.lastAddress &&
                    nextAddress == other.nextAddress &&
                    beforeAddress == other.beforeAddress
            }

            override fun hashCode(): Int {
                var result = address
                result = 31 * result + size
                result = 31 * result + firstAddress
                result = 31 * result + lastAddress
                result = 31 * result + nextAddress
                result = 31 * result + beforeAddress
                return result
            }
        }

        /**
         * The amount of free table entries
         */
        override val size: Int
            get() {
                var address = freeTableStartPointer
                var size = 0
                while (address != -1) {
                    size++
                    address = memory.getInt(address + 12)
                }
                return size
            }

        override fun contains(element: FreeTableEntry): Boolean {
            var address = freeTableStartPointer
            while (address != -1) {
                if (address == element.address) return true
                address = memory.getInt(address + 12)
            }
            return false
        }

        override fun containsAll(elements: Collection<FreeTableEntry>): Boolean {
            for (element in elements) {
                if (!contains(element)) return false
            }
            return true
        }

        override fun indexOf(element: FreeTableEntry): Int {
            var address = freeTableStartPointer
            var index = 0
            while (address != -1) {
                if (address == element.address) return index
                address = memory.getInt(address + 12)
                index++
            }
            return -1
        }

        override fun isEmpty(): Boolean {
            return freeTableStartPointer == -1
        }

        override fun iterator(): MutableIterator<FreeTableEntry> {
            return object : MutableIterator<FreeTableEntry> {
                var address = freeTableStartPointer
                override fun hasNext(): Boolean {
                    return address != -1
                }

                override fun next(): FreeTableEntry {
                    val result = FreeTableEntry(address)
                    address = memory.getInt(address + 12)
                    return result
                }

                override fun remove() {
                    throw UnsupportedOperationException()
                }
            }
        }

        override fun lastIndexOf(element: FreeTableEntry): Int {
            return indexOf(element) // Every element is unique
        }

        override fun add(element: FreeTableEntry): Boolean {
            throw UnsupportedOperationException()
        }

        override fun add(index: Int, element: FreeTableEntry) {
            throw UnsupportedOperationException()
        }

        override fun addAll(index: Int, elements: Collection<FreeTableEntry>): Boolean {
            throw UnsupportedOperationException()
        }

        override fun addAll(elements: Collection<FreeTableEntry>): Boolean {
            throw UnsupportedOperationException()
        }

        override fun clear() {
            throw UnsupportedOperationException()
        }

        override fun listIterator(): MutableListIterator<FreeTableEntry> {
            return listIterator(0)
        }

        override fun listIterator(index: Int): MutableListIterator<FreeTableEntry> {
            return object : MutableListIterator<FreeTableEntry> {
                var address = freeTableStartPointer
                var currentIndex = 0
                override fun hasNext(): Boolean {
                    return address != -1
                }

                override fun next(): FreeTableEntry {
                    val result = FreeTableEntry(address)
                    address = memory.getInt(address + 12)
                    currentIndex++
                    return result
                }

                override fun hasPrevious(): Boolean {
                    return address != -1
                }

                override fun nextIndex(): Int {
                    return currentIndex
                }

                override fun previous(): FreeTableEntry {
                    throw UnsupportedOperationException()
                }

                override fun previousIndex(): Int {
                    throw UnsupportedOperationException()
                }

                override fun remove() {
                    throw UnsupportedOperationException()
                }

                override fun set(element: FreeTableEntry) {
                    throw UnsupportedOperationException()
                }

                override fun add(element: FreeTableEntry) {
                    throw UnsupportedOperationException()
                }
            }
        }

        override fun remove(element: FreeTableEntry): Boolean {
            if (!contains(element)) {
                return false
            }
            val before = element.before
            val next = element.next
            if (before != null && next != null) {
                before.next = next
                next.before = before
            } else if (before != null) {
                before.next = null
                freeTableEndPointer
            } else if (next != null) {
                next.before = null
                freeTableStartPointer = next.address
            } else {
                freeTableStartPointer = -1
                freeTableEndPointer = -1
            }
            return true
        }

        override fun removeAll(elements: Collection<FreeTableEntry>): Boolean {
            var result = false
            for (element in elements) {
                result = result || remove(element)
            }
            return result
        }

        override fun removeAt(index: Int): FreeTableEntry {
            get(index).let {
                remove(it)
                return it
            }
        }

        override fun retainAll(elements: Collection<FreeTableEntry>): Boolean {
            return elements.any {
                remove(it)
            }
        }

        override fun set(index: Int, element: FreeTableEntry): FreeTableEntry {
            throw UnsupportedOperationException()
        }

        override fun subList(fromIndex: Int, toIndex: Int): MutableList<FreeTableEntry> {
            throw UnsupportedOperationException()
        }

        inner class FreeElement(
            val address: Int,
            val size: Int,
            val before: Int,
            val table: Int,
        ) {
            var next: Int
                get() = memory.getInt(address)
                set(value) {
                    memory.setInt(address, value)
                }
        }
    }
}
