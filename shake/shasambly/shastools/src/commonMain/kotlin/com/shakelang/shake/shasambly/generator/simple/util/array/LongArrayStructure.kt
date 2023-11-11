@file:Suppress("unused")

package com.shakelang.shake.shasambly.generator.simple.util.array

import com.shakelang.shake.shasambly.generator.simple.SimpleShasambly

/**
 * A local long array that does not store it's size. If you are using it you have to store
 * its size in some other way. It's advised to use arrays that store their size instead
 * e.g. [LocalLongArrayStructure]
 *
 * @see LocalLongArrayStructure
 * @see createStaticallySizedLocalLongArray
 */
class StaticallySizedLocalLongArrayStructure(val shasambly: SimpleShasambly, val address: Int, val size: Int = -1) {

    /**
     * Get an element of the array. The index is the first long from the stack
     */
    fun getElement() {
        shasambly {
            imul(8)
            i_get_local(address)
            isub()
            ineg()
            l_get_global_dynamic()
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
            if (index != 0) isub(index * 8)
            l_get_global_dynamic()
        }
    }

    /**
     * Store an element into the array. The index is the first integer on the stack.
     * The element value is the long below it
     */
    fun storeElement() {
        shasambly {
            imul(8)
            i_get_local(address)
            isub()
            ineg()
            l_store_global_dynamic()
        }
    }

    /**
     * Store an element into the array. The index is given as argument. The element value is
     * the top long on the stack.
     *
     * @param index the index of the array to store the element in
     */
    fun storeElement(index: Int) {
        shasambly {
            i_get_local(address)
            if (index != 0) isub(index * 8)
            l_store_global_dynamic()
        }
    }

    /**
     * Store an element into the array. Both index and value are given as arguments
     *
     * @param index the index of the array to store the element in
     * @param value the value to store into the index
     */
    fun storeElement(index: Int, value: Long) {
        shasambly {
            lpush(value)
            i_get_local(address)
            if (index != 0) isub(index * 8)
            l_store_global_dynamic()
        }
    }

    /**
     * Store an element into the array. Both index and value are given as arguments
     *
     * @param index the index of the array to store the element in
     * @param value the value to store into the index
     */
    fun storeElement(index: Int, value: Double) {
        this.storeElement(index, value.toBits())
    }

    /**
     * Free the array
     * If the array does not know it's size it takes the top int from the stack as size to free
     */
    fun free() {
        shasambly {
            i_get_local(address)
            if (size == -1) {
                imul(8)
                natives.freeGlobal()
            } else {
                natives.freeGlobal(size * 8)
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
            natives.freeGlobal(size * 8)
        }
    }
}

/**
 * Create a [StaticallySizedLocalLongArrayStructure]
 * A long array that does not store it's size. If you are using it you have to store
 * its size in some other way. It's advised to use arrays that store their size instead
 * e.g. [LocalLongArrayStructure]
 *
 * @param addr the local address to store the array in
 * @param size the size of the created array (must be at least 2 for this type of array!!!)
 */
fun SimpleShasambly.createStaticallySizedLocalLongArray(addr: Int, size: Int): StaticallySizedLocalLongArrayStructure {
    if (size < 1) throw IllegalArgumentException("Size must be at least 1 for this type of array")
    natives.declareGlobal(size * 8)
    i_store_local(addr)
    return StaticallySizedLocalLongArrayStructure(this.base, addr, size)
}

/**
 * Create a [StaticallySizedLocalLongArrayStructure]
 * A int array that does not store it's size. If you are using it you have to store
 * its size in some other way. It's advised to use arrays that store their size instead
 * e.g. [LocalLongArrayStructure]
 *
 * @param addr the local address to store the array in
 */
fun SimpleShasambly.createStaticallySizedLocalLongArray(addr: Int): StaticallySizedLocalLongArrayStructure {
    imul(8)
    natives.declareGlobal()
    i_store_local(addr)
    return StaticallySizedLocalLongArrayStructure(this.base, addr)
}

/**
 * A local long array. It stores the size at the start of it, so you can always get the size using [getSize]
 * This is the advised type of local long array over [StaticallySizedLocalLongArrayStructure]
 */
class LocalLongArrayStructure(val shasambly: SimpleShasambly, val address: Int) {

    /**
     * Put the size of the long array on top of the stack
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
            imul(8)
            i_get_local(address)
            isub()
            ineg()
            isub(4)
            i_get_global_dynamic()
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
            isub(index * 8 + 4)
            i_get_global_dynamic()
        }
    }

    /**
     * Store an element into the array at a given position
     * The position is defined by the top integer of the stack
     * The value is taken from the stack below the long
     */
    fun storeElement() {
        shasambly {
            imul(8)
            i_get_local(address)
            isub()
            ineg()
            isub(4)
            i_store_global_dynamic()
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
            isub(index * 8 + 4)
            i_store_global_dynamic()
        }
    }

    /**
     * Store an element into the array at a given position
     *
     * @param index the index to store the element in
     * @param value the value to store into the array
     */
    fun storeElement(index: Int, value: Long) {
        shasambly {
            lpush(value)
            i_get_local(address)
            isub(index * 8 + 4)
            i_store_global_dynamic()
        }
    }

    /**
     * Store an element into the array at a given position
     *
     * @param index the index to store the element in
     * @param value the value to store into the array
     */
    fun storeElement(index: Int, value: Double) {
        this.storeElement(index, value.toBits())
    }

    /**
     * Marks the storage of this array as free
     */
    fun free() {
        shasambly {
            getSize()
            imul(8)
            iadd(4)
            i_get_local(address)
            natives.freeGlobal()
        }
    }
}

/**
 * Create a [LocalLongArrayStructure]
 * A local long array. It stores the size at the start of it, so you can always get the size using [LocalLongArrayStructure.getSize]
 * This is the advised type of local long array over [StaticallySizedLocalLongArrayStructure]
 *
 * @param addr the local address to create the long array in
 * @param size the size of the long array
 */
fun SimpleShasambly.createLocalLongArray(addr: Int, size: Int): LocalLongArrayStructure {
    natives.declareGlobal(size * 8 + 4)
    i_store_local(addr)
    ipush(size)
    i_get_local(addr)
    i_store_global_dynamic()
    return LocalLongArrayStructure(this.base, addr)
}

/**
 * Create a [LocalLongArrayStructure]
 * A local long array. It stores the size at the start of it, so you can always get the size using [LocalLongArrayStructure.getSize]
 * This is the advised type of local long array over [StaticallySizedLocalLongArrayStructure]
 * The size is the first integer on the stack
 *
 * @param addr the local address to create the long array in
 */
fun SimpleShasambly.createLocalLongArray(addr: Int): LocalLongArrayStructure {
    natives.idup()
    imul(8)
    iadd(4)
    natives.declareGlobal()
    natives.idup()
    i_store_local(addr)
    natives.iswap(0u, 4u)
    i_store_global_dynamic()
    return LocalLongArrayStructure(this.base, addr)
}

typealias StaticallySizedLocalDoubleArrayStructure = StaticallySizedLocalLongArrayStructure

/**
 * Create a [StaticallySizedLocalLongArrayStructure]
 * A long array that does not store it's size. If you are using it you have to store
 * its size in some other way. It's advised to use arrays that store their size instead
 * e.g. [LocalLongArrayStructure]
 *
 * @param addr the local address to store the array in
 * @param size the size of the created array (must be at least 2 for this type of array!!!)
 */
fun SimpleShasambly.createStaticallySizedLocalDoubleArray(addr: Int, size: Int) =
    this.createStaticallySizedLocalLongArray(addr, size)

/**
 * Create a [StaticallySizedLocalLongArrayStructure]
 * A int array that does not store it's size. If you are using it you have to store
 * its size in some other way. It's advised to use arrays that store their size instead
 * e.g. [LocalLongArrayStructure]
 *
 * @param addr the local address to store the array in
 */
fun SimpleShasambly.createStaticallySizedLocalDoubleArray(addr: Int) = this.createStaticallySizedLocalLongArray(addr)

typealias LocalDoubleArrayStructure = LocalLongArrayStructure

/**
 * Create a [LocalLongArrayStructure]
 * A local long array. It stores the size at the start of it, so you can always get the size using [LocalLongArrayStructure.getSize]
 * This is the advised type of local long array over [StaticallySizedLocalLongArrayStructure]
 *
 * @param addr the local address to create the long array in
 * @param size the size of the long array
 */
fun SimpleShasambly.createLocalDoubleArray(addr: Int, size: Int) = this.createLocalLongArray(addr, size)

/**
 * Create a [LocalLongArrayStructure]
 * A local long array. It stores the size at the start of it, so you can always get the size using [LocalLongArrayStructure.getSize]
 * This is the advised type of local long array over [StaticallySizedLocalLongArrayStructure]
 * The size is the first integer on the stack
 *
 * @param addr the local address to create the long array in
 */
fun SimpleShasambly.createLocalDoubleArray(addr: Int) = this.createLocalLongArray(addr)
