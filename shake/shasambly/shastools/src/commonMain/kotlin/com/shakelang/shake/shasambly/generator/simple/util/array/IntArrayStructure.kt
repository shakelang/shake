@file:Suppress("unused")

package com.shakelang.shake.shasambly.generator.simple.util.array

import com.shakelang.shake.shasambly.generator.simple.SimpleShasambly

/**
 * A local int array that does not store it's size. If you are using it you have to store
 * its size in some other way. It's advised to use arrays that store their size instead
 * e.g. [LocalIntArrayStructure]
 *
 * @see LocalIntArrayStructure
 * @see createStaticallySizedLocalIntArray
 */
class StaticallySizedLocalIntArrayStructure(val shasambly: SimpleShasambly, val address: Int, val size: Int = -1) {

    /**
     * Get an element of the array. The index is the first integers from the stack
     */
    fun getElement() {
        shasambly {
            imul(4)
            i_get_local(address)
            isub()
            ineg()
            i_get_global_dynamic()
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
            if (index != 0) isub(index * 4)
            i_get_global_dynamic()
        }
    }

    /**
     * Store an element into the array. The index is the first integers on the stack.
     * The element value is the int below it
     */
    fun storeElement() {
        shasambly {
            imul(4)
            i_get_local(address)
            isub()
            ineg()
            i_store_global_dynamic()
        }
    }

    /**
     * Store an element into the array. The index is given as argument. The element value is
     * the top int on the stack.
     *
     * @param index the index of the array to store the element in
     */
    fun storeElement(index: Int) {
        shasambly {
            i_get_local(address)
            if (index != 0) isub(index * 4)
            i_store_global_dynamic()
        }
    }

    /**
     * Store an element into the array. Both index and value are given as arguments
     *
     * @param index the index of the array to store the element in
     * @param value the value to store into the index
     */
    fun storeElement(index: Int, value: Int) {
        shasambly {
            ipush(value)
            i_get_local(address)
            if (index != 0) isub(index * 4)
            i_store_global_dynamic()
        }
    }

    /**
     * Store an element into the array. Both index and value are given as arguments
     *
     * @param index the index of the array to store the element in
     * @param value the value to store into the index
     */
    fun storeElement(index: Int, value: Float) {
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
                imul(4)
                natives.freeGlobal()
            } else {
                natives.freeGlobal(size * 4)
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
            natives.freeGlobal(size * 4)
        }
    }
}

/**
 * Create a [StaticallySizedLocalIntArrayStructure]
 * A int array that does not store it's size. If you are using it you have to store
 * its size in some other way. It's advised to use arrays that store their size instead
 * e.g. [LocalIntArrayStructure]
 *
 * @param addr the local address to store the array in
 * @param size the size of the created array (must be at least 1 for this type of array!!!)
 */
fun SimpleShasambly.createStaticallySizedLocalIntArray(addr: Int, size: Int): StaticallySizedLocalIntArrayStructure {
    if (size < 1) throw IllegalArgumentException("Size must be at least 1 for this type of array")
    natives.declareGlobal(size * 4)
    i_store_local(addr)
    return StaticallySizedLocalIntArrayStructure(this.base, addr, size)
}

/**
 * Create a [StaticallySizedLocalIntArrayStructure]
 * A int array that does not store it's size. If you are using it you have to store
 * its size in some other way. It's advised to use arrays that store their size instead
 * e.g. [LocalIntArrayStructure]
 *
 * @param addr the local address to store the array in
 */
fun SimpleShasambly.createStaticallySizedLocalIntArray(addr: Int): StaticallySizedLocalIntArrayStructure {
    imul(4)
    natives.declareGlobal()
    i_store_local(addr)
    return StaticallySizedLocalIntArrayStructure(this.base, addr)
}

/**
 * A local int array. It stores the size at the start of it, so you can always get the size using [getSize]
 * This is the advised type of local int array over [StaticallySizedLocalIntArrayStructure]
 */
class LocalIntArrayStructure(val shasambly: SimpleShasambly, val address: Int) {

    /**
     * Put the size of the int array on top of the stack
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
            imul(4)
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
            isub(index * 4 + 4)
            i_get_global_dynamic()
        }
    }

    /**
     * Store an element into the array at a given position
     * The position is defined by the top integers of the stack
     * The value is taken from the stack below the integers
     */
    fun storeElement() {
        shasambly {
            imul(4)
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
            isub(index * 4 + 4)
            i_store_global_dynamic()
        }
    }

    /**
     * Store an element into the array at a given position
     *
     * @param index the index to store the element in
     * @param value the value to store into the array
     */
    fun storeElement(index: Int, value: Int) {
        shasambly {
            ipush(value)
            i_get_local(address)
            isub(index * 4 + 4)
            i_store_global_dynamic()
        }
    }

    /**
     * Store an element into the array at a given position
     *
     * @param index the index to store the element in
     * @param value the value to store into the array
     */
    fun storeElement(index: Int, value: Float) {
        this.storeElement(index, value.toBits())
    }

    /**
     * Marks the storage of this array as free
     */
    fun free() {
        shasambly {
            getSize()
            imul(4)
            iadd(4)
            i_get_local(address)
            natives.freeGlobal()
        }
    }
}

/**
 * Create a [LocalIntArrayStructure]
 * A local int array. It stores the size at the start of it, so you can always get the size using [LocalIntArrayStructure.getSize]
 * This is the advised type of local int array over [StaticallySizedLocalIntArrayStructure]
 *
 * @param addr the local address to create the int array in
 * @param size the size of the int array
 */
fun SimpleShasambly.createLocalIntArray(addr: Int, size: Int): LocalIntArrayStructure {
    natives.declareGlobal(size * 4 + 4)
    i_store_local(addr)
    ipush(size)
    i_get_local(addr)
    i_store_global_dynamic()
    return LocalIntArrayStructure(this.base, addr)
}

/**
 * Create a [LocalIntArrayStructure]
 * A local int array. It stores the size at the start of it, so you can always get the size using [LocalIntArrayStructure.getSize]
 * This is the advised type of local int array over [StaticallySizedLocalIntArrayStructure]
 * The size is the first integers on the stack
 *
 * @param addr the local address to create the int array in
 */
fun SimpleShasambly.createLocalIntArray(addr: Int): LocalIntArrayStructure {
    natives.idup()
    imul(4)
    iadd(4)
    natives.declareGlobal()
    natives.idup()
    i_store_local(addr)
    natives.iswap(0u, 4u)
    i_store_global_dynamic()
    return LocalIntArrayStructure(this.base, addr)
}

typealias StaticallySizedLocalIntegerArrayStructure = StaticallySizedLocalIntArrayStructure

/**
 * Create a [StaticallySizedLocalIntArrayStructure]
 * A int array that does not store it's size. If you are using it you have to store
 * its size in some other way. It's advised to use arrays that store their size instead
 * e.g. [LocalIntArrayStructure]
 *
 * @param addr the local address to store the array in
 * @param size the size of the created array (must be at least 1 for this type of array!!!)
 */
fun SimpleShasambly.createStaticallySizedLocalIntegerArray(addr: Int, size: Int) =
    this.createStaticallySizedLocalIntArray(addr, size)

/**
 * Create a [StaticallySizedLocalIntArrayStructure]
 * A int array that does not store it's size. If you are using it you have to store
 * its size in some other way. It's advised to use arrays that store their size instead
 * e.g. [LocalIntArrayStructure]
 *
 * @param addr the local address to store the array in
 */
fun SimpleShasambly.createStaticallySizedLocalIntegerArray(addr: Int) = this.createStaticallySizedLocalIntArray(addr)

typealias LocalIntegerArrayStructure = LocalIntArrayStructure

/**
 * Create a [LocalIntArrayStructure]
 * A local int array. It stores the size at the start of it, so you can always get the size using [LocalIntArrayStructure.getSize]
 * This is the advised type of local int array over [StaticallySizedLocalIntArrayStructure]
 *
 * @param addr the local address to create the int array in
 * @param size the size of the int array
 */
fun SimpleShasambly.createLocalIntegerArray(addr: Int, size: Int) = this.createLocalIntArray(addr, size)

/**
 * Create a [LocalIntArrayStructure]
 * A local int array. It stores the size at the start of it, so you can always get the size using [LocalIntArrayStructure.getSize]
 * This is the advised type of local int array over [StaticallySizedLocalIntArrayStructure]
 * The size is the first integers on the stack
 *
 * @param addr the local address to create the int array in
 */
fun SimpleShasambly.createLocalIntegerArray(addr: Int) = this.createLocalIntArray(addr)

typealias StaticallySizedLocalFloatArrayStructure = StaticallySizedLocalIntArrayStructure

/**
 * Create a [StaticallySizedLocalIntArrayStructure]
 * A int array that does not store it's size. If you are using it you have to store
 * its size in some other way. It's advised to use arrays that store their size instead
 * e.g. [LocalIntArrayStructure]
 *
 * @param addr the local address to store the array in
 * @param size the size of the created array (must be at least 1 for this type of array!!!)
 */
fun SimpleShasambly.createStaticallySizedLocalFloatArray(addr: Int, size: Int) =
    this.createStaticallySizedLocalIntArray(addr, size)

/**
 * Create a [StaticallySizedLocalIntArrayStructure]
 * A int array that does not store it's size. If you are using it you have to store
 * its size in some other way. It's advised to use arrays that store their size instead
 * e.g. [LocalIntArrayStructure]
 *
 * @param addr the local address to store the array in
 */
fun SimpleShasambly.createStaticallySizedLocalFloatArray(addr: Int) = this.createStaticallySizedLocalIntArray(addr)

typealias LocalFloatArrayStructure = LocalIntArrayStructure

/**
 * Create a [LocalIntArrayStructure]
 * A local int array. It stores the size at the start of it, so you can always get the size using [LocalIntArrayStructure.getSize]
 * This is the advised type of local int array over [StaticallySizedLocalIntArrayStructure]
 *
 * @param addr the local address to create the int/float array in
 * @param size the size of the int array
 */
fun SimpleShasambly.createLocalFloatArray(addr: Int, size: Int) = this.createLocalIntArray(addr, size)

/**
 * Create a [LocalIntArrayStructure]
 * A local int array. It stores the size at the start of it, so you can always get the size using [LocalIntArrayStructure.getSize]
 * This is the advised type of local int array over [StaticallySizedLocalIntArrayStructure]
 * The size is the first integers on the stack
 *
 * @param addr the local address to create the int/float array in
 */
fun SimpleShasambly.createLocalFloatArray(addr: Int) = this.createLocalIntArray(addr)
