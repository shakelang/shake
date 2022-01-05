@file:Suppress("unused")
package io.github.shakelang.shake.shasambly.generator.simple.util.array

import io.github.shakelang.shake.shasambly.generator.simple.SimpleShasambly

/**
 * A local byte array that does not store it's size. If you are using it you have to store
 * its size in some other way. It's advised to use arrays that store their size instead
 * e.g. [LocalByteArrayStructure]
 *
 * @see LocalByteArrayStructure
 * @see createStaticallySizedLocalByteArray
 */
class StaticallySizedLocalByteArrayStructure(val shasambly: SimpleShasambly, val address: Int, val size: Int = -1) {

    /**
     * Get an element of the array. The index is the first integer from the stack
     */
    fun getElement() {
        shasambly {
            i_get_local(address)
            isub()
            ineg()
            b_get_global_dynamic()
        }
    }

    /**
     * Get an element of the array. The index is given as argument
     *
     * @param index the index of the array to store the element in
     */
    fun getElement(index: Int) {
        shasambly {
            i_get_local(address)
            if(index != 0) {
                ipush(index)
                isub()
            }
            b_get_global_dynamic()
        }
    }

    /**
     * Store an element into the array. The index is the first integer on the stack.
     * The element value is the byte below it
     */
    fun storeElement() {
        shasambly {
            i_get_local(address)
            isub()
            ineg()
            b_store_global_dynamic()
        }
    }

    /**
     * Store an element into the array. The index is given as argument. The element value is
     * the top byte on the stack.
     *
     * @param index the index of the array to store the element in
     */
    fun storeElement(index: Int) {
        shasambly {
            i_get_local(address)
            if(index != 0) {
                ipush(index)
                isub()
            }
            b_store_global_dynamic()
        }
    }

    /**
     * Store an element into the array. Both index and value are given as arguments
     *
     * @param index the index of the array to store the element in
     * @param value the value to store into the index
     */
    fun storeElement(index: Int, value: Byte) {
        shasambly {
            bpush(value)
            i_get_local(address)
            if(index != 0) {
                ipush(index)
                isub()
            }
            b_store_global_dynamic()
        }
    }

    /**
     * Free the array
     * If the array does not know it's size it takes the top int from the stack as size to free
     */
    fun free() {
        shasambly {
            i_get_local(address)
            if(size == -1) natives.freeGlobal()
            else natives.freeGlobal(size)
        }
    }

    /**
     * Free the array with a given size
     *
     * @param size the size of the array
     */
    fun free(size: Int) {
        shasambly {
            i_get_local(address)
            natives.freeGlobal(size)
        }
    }
}

/**
 * Create a [StaticallySizedLocalByteArrayStructure]
 * A ByteArray that does not store it's size. If you are using it you have to store
 * its size in some other way. It's advised to use arrays that store their size instead
 * e.g. [LocalByteArrayStructure]
 *
 * @param addr the local address to store the array in
 * @param size the size of the created array (must be at least 4 for this type of array!!!)
 */
fun SimpleShasambly.createStaticallySizedLocalByteArray(addr: Int, size: Int): StaticallySizedLocalByteArrayStructure {
    if(size < 4) throw IllegalArgumentException("Size must be at least 4 for this type of array")
    natives.declareGlobal(size)
    i_store_local(addr)
    return StaticallySizedLocalByteArrayStructure(this.base, addr, size)
}

/**
 * Create a [StaticallySizedLocalByteArrayStructure]
 * A ByteArray that does not store it's size. If you are using it you have to store
 * its size in some other way. It's advised to use arrays that store their size instead
 * e.g. [LocalByteArrayStructure]
 *
 * @param addr the local address to store the array in
 */
fun SimpleShasambly.createStaticallySizedLocalByteArray(addr: Int): StaticallySizedLocalByteArrayStructure {
    natives.declareGlobal()
    i_store_local(addr)
    return StaticallySizedLocalByteArrayStructure(this.base, addr)
}

/**
 * A local byte array. It stores the size at the start of it, so you can always get the size using [getSize]
 * This is the advised type of local byte array over [StaticallySizedLocalByteArrayStructure]
 */
class LocalByteArrayStructure(val shasambly: SimpleShasambly, val address: Int) {

    /**
     * Put the size of the byte array on top of the stack
     */
    fun getSize() {
        shasambly {
            i_get_local(address)
            i_get_global_dynamic()
        }
    }

    /**
     * Put the element at a given position on top of the stack.
     * Takes the top integer of the stack as position.
     */
    fun getElement() {
        shasambly {
            i_get_local(address)
            isub()
            ineg()
            ipush(4)
            isub()
            b_get_global_dynamic()
        }
    }

    /**
     * Put the element at a given position on top of the stack.
     *
     * @param index the index of the element
     */
    fun getElement(index: Int) {
        shasambly {
            i_get_local(address)
            ipush(index + 4)
            isub()
            b_get_global_dynamic()
        }
    }

    /**
     * Store an element into the array at a given position
     * The position is defined by the top integer of the stack
     * The value is taken from the stack below the integer
     */
    fun storeElement() {
        shasambly {
            i_get_local(address)
            isub()
            ineg()
            ipush(4)
            isub()
            b_store_global_dynamic()
        }
    }

    /**
     * Store an element into the array at a given position
     * The value is given as argument, the value is taken from the top of the stack
     *
     * @param index the index to store the element in
     */
    fun storeElement(index: Int) {
        shasambly {
            i_get_local(address)
            ipush(index + 4)
            isub()
            b_store_global_dynamic()
        }
    }

    /**
     * Store an element into the array at a given position
     *
     * @param index the index to store the element in
     * @param value the value to store into the array
     */
    fun storeElement(index: Int, value: Byte) {
        shasambly {
            bpush(value)
            i_get_local(address)
            ipush(index + 4)
            isub()
            b_store_global_dynamic()
        }
    }

    /**
     * Marks the storage of this array as free
     */
    fun free() {
        shasambly {
            getSize()
            ipush(4)
            iadd()
            i_get_local(address)
            natives.freeGlobal()
        }
    }
}

/**
 * Create a [LocalByteArrayStructure]
 * A local byte array. It stores the size at the start of it, so you can always get the size using [LocalByteArrayStructure.getSize]
 * This is the advised type of local byte array over [StaticallySizedLocalByteArrayStructure]
 *
 * @param addr the local address to create the byte array in
 * @param size the size of the byte array
 */
fun SimpleShasambly.createSavedSizeLocalByteArray(addr: Int, size: Int): LocalByteArrayStructure {
    natives.declareGlobal(size + 4)
    i_store_local(addr)
    ipush(size)
    i_get_local(addr)
    i_store_global_dynamic()
    return LocalByteArrayStructure(this.base, addr)
}

/**
 * Create a [LocalByteArrayStructure]
 * A local byte array. It stores the size at the start of it, so you can always get the size using [LocalByteArrayStructure.getSize]
 * This is the advised type of local byte array over [StaticallySizedLocalByteArrayStructure]
 * The size is the first integer on the stack
 *
 * @param addr the local address to create the byte array in
 */
fun SimpleShasambly.createSavedSizeLocalByteArray(addr: Int): LocalByteArrayStructure {
    natives.idup()
    ipush(4)
    iadd()
    natives.declareGlobal()
    natives.idup()
    i_store_local(addr)
    natives.iswap(0u, 4u)
    i_store_global_dynamic()
    return LocalByteArrayStructure(this.base, addr)
}