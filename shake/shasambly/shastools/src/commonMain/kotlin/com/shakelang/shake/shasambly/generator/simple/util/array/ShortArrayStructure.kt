@file:Suppress("unused")

package com.shakelang.shake.shasambly.generator.simple.util.array

import com.shakelang.shake.shasambly.generator.simple.SimpleShasambly

/**
 * A local shorts array that does not store it's size. If you are using it you have to store
 * its size in some other way. It's advised to use arrays that store their size instead
 * e.g. [LocalShortArrayStructure]
 *
 * @see LocalShortArrayStructure
 * @see createStaticallySizedLocalShortArray
 */
class StaticallySizedLocalShortArrayStructure(val shasambly: SimpleShasambly, val address: Int, val size: Int = -1) {

    /**
     * Get an element of the array. The index is the first integers from the stack
     */
    fun getElement() {
        shasambly {
            imul(2)
            i_get_local(address)
            isub()
            ineg()
            s_get_global_dynamic()
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
            if (index != 0) isub(index * 2)
            s_get_global_dynamic()
        }
    }

    /**
     * Store an element into the array. The index is the first integers on the stack.
     * The element value is the shorts below it
     */
    fun storeElement() {
        shasambly {
            imul(2)
            i_get_local(address)
            isub()
            ineg()
            s_store_global_dynamic()
        }
    }

    /**
     * Store an element into the array. The index is given as argument. The element value is
     * the top shorts on the stack.
     *
     * @param index the index of the array to store the element in
     */
    fun storeElement(index: Int) {
        shasambly {
            i_get_local(address)
            if (index != 0) isub(index * 2)
            s_store_global_dynamic()
        }
    }

    /**
     * Store an element into the array. Both index and value are given as arguments
     *
     * @param index the index of the array to store the element in
     * @param value the value to store into the index
     */
    fun storeElement(index: Int, value: Short) {
        shasambly {
            spush(value)
            i_get_local(address)
            if (index != 0) isub(index * 2)
            s_store_global_dynamic()
        }
    }

    /**
     * Free the array
     * If the array does not know it's size it takes the top int from the stack as size to free
     */
    fun free() {
        shasambly {
            i_get_local(address)
            if (size == -1) {
                imul(2)
                natives.freeGlobal()
            } else {
                natives.freeGlobal(size * 2)
            }
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
            natives.freeGlobal(size * 2)
        }
    }
}

/**
 * Create a [StaticallySizedLocalShortArrayStructure]
 * A shorts array that does not store it's size. If you are using it you have to store
 * its size in some other way. It's advised to use arrays that store their size instead
 * e.g. [LocalShortArrayStructure]
 *
 * @param addr the local address to store the array in
 * @param size the size of the created array (must be at least 2 for this type of array!!!)
 */
fun SimpleShasambly.createStaticallySizedLocalShortArray(
    addr: Int,
    size: Int,
): StaticallySizedLocalShortArrayStructure {
    if (size < 2) throw IllegalArgumentException("Size must be at least 2 for this type of array")
    natives.declareGlobal(size * 2)
    i_store_local(addr)
    return StaticallySizedLocalShortArrayStructure(this.base, addr, size)
}

/**
 * Create a [StaticallySizedLocalShortArrayStructure]
 * A shorts array that does not store it's size. If you are using it you have to store
 * its size in some other way. It's advised to use arrays that store their size instead
 * e.g. [LocalShortArrayStructure]
 *
 * @param addr the local address to store the array in
 */
fun SimpleShasambly.createStaticallySizedLocalShortArray(addr: Int): StaticallySizedLocalShortArrayStructure {
    imul(2)
    natives.declareGlobal()
    i_store_local(addr)
    return StaticallySizedLocalShortArrayStructure(this.base, addr)
}

/**
 * A local shorts array. It stores the size at the start of it, so you can always get the size using [getSize]
 * This is the advised type of local shorts array over [StaticallySizedLocalShortArrayStructure]
 */
class LocalShortArrayStructure(val shasambly: SimpleShasambly, val address: Int) {

    /**
     * Put the size of the shorts array on top of the stack
     */
    fun getSize() {
        shasambly {
            i_get_local(address)
            i_get_global_dynamic()
        }
    }

    /**
     * Put the element at a given position on top of the stack.
     * Takes the top integers of the stack as position.
     */
    fun getElement() {
        shasambly {
            imul(2)
            i_get_local(address)
            isub()
            ineg()
            isub(4)
            s_get_global_dynamic()
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
            isub(index * 2 + 4)
            s_get_global_dynamic()
        }
    }

    /**
     * Store an element into the array at a given position
     * The position is defined by the top integers of the stack
     * The value is taken from the stack below the integers
     */
    fun storeElement() {
        shasambly {
            imul(2)
            i_get_local(address)
            isub()
            ineg()
            isub(4)
            s_store_global_dynamic()
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
            isub(index * 2 + 4)
            s_store_global_dynamic()
        }
    }

    /**
     * Store an element into the array at a given position
     *
     * @param index the index to store the element in
     * @param value the value to store into the array
     */
    fun storeElement(index: Int, value: Short) {
        shasambly {
            spush(value)
            i_get_local(address)
            isub(index * 2 + 4)
            s_store_global_dynamic()
        }
    }

    /**
     * Marks the storage of this array as free
     */
    fun free() {
        shasambly {
            getSize()
            imul(2)
            iadd(4)
            i_get_local(address)
            natives.freeGlobal()
        }
    }
}

/**
 * Create a [LocalShortArrayStructure]
 * A local shorts array. It stores the size at the start of it, so you can always get the size using [LocalShortArrayStructure.getSize]
 * This is the advised type of local shorts array over [StaticallySizedLocalShortArrayStructure]
 *
 * @param addr the local address to create the shorts array in
 * @param size the size of the shorts array
 */
fun SimpleShasambly.createLocalShortArray(addr: Int, size: Int): LocalShortArrayStructure {
    natives.declareGlobal(size * 2 + 4)
    i_store_local(addr)
    ipush(size)
    i_get_local(addr)
    i_store_global_dynamic()
    return LocalShortArrayStructure(this.base, addr)
}

/**
 * Create a [LocalShortArrayStructure]
 * A local shorts array. It stores the size at the start of it, so you can always get the size using [LocalShortArrayStructure.getSize]
 * This is the advised type of local shorts array over [StaticallySizedLocalShortArrayStructure]
 * The size is the first integers on the stack
 *
 * @param addr the local address to create the shorts array in
 */
fun SimpleShasambly.createLocalShortArray(addr: Int): LocalShortArrayStructure {
    natives.idup()
    imul(2)
    iadd(4)
    natives.declareGlobal()
    natives.idup()
    i_store_local(addr)
    natives.iswap(0u, 4u)
    i_store_global_dynamic()
    return LocalShortArrayStructure(this.base, addr)
}
