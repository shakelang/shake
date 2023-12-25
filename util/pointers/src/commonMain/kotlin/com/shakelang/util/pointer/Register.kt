package com.shakelang.util.pointer

/**
 * A register for pointers.
 * @param T The type of the value the pointers point to
 * @since 0.1.0
 * @version 0.1.0
 * @see Pointer
 * @see MutablePointer
 */
class PointerRegister<T> {

    /**
     * The list of pointers
     */
    private val pointers = mutableListOf<Pointer<T>?>()

    /**
     * The list of unused indices
     */
    private val unusedIndices = mutableListOf<Int>()

    init {
        instances.add(this)
    }

    /**
     * Register a pointer to the register (and return the index of the pointer)
     * @param pointer The pointer to register
     * @return The index of the pointer
     * @since 0.1.0
     * @version 0.1.0
     */
    fun register(pointer: Pointer<T>): Int {
        val index = unusedIndices.removeLastOrNull()
        if (index != null) {
            pointers[index] = pointer
        } else {
            pointers.add(pointer)
            return pointers.size - 1
        }
        return index
    }

    /**
     * Get the index of the given pointer
     * @param pointer The pointer
     * @return The index of the pointer or -1 if the pointer is not registered
     * @since 0.1.0
     * @version 0.1.0
     */
    fun indexOf(pointer: Pointer<Any?>): Int {
        this.pointers.forEachIndexed { index, p ->
            if (p == pointer) return index
        }
        return -1
    }

    /**
     * Unregister the pointer at the given index
     * @param index The index of the pointer
     * @since 0.1.0
     * @version 0.1.0
     */
    fun unregister(index: Int) {
        pointers[index] = null
        unusedIndices.add(index)
    }

    /**
     * Get the pointer at the given index
     * @throws IllegalStateException If the pointer is not registered
     * @param index The index of the pointer
     * @return The pointer
     * @since 0.1.0
     * @version 0.1.0
     */
    operator fun get(index: Int): Pointer<T> {
        return (
            if (index < pointers.size) {
                pointers[index]
            } else {
                null
            }
            ) ?: throw IllegalStateException("pointer at index $index is null")
    }

    /**
     * Get the pointer at the given index or null if the pointer is not registered
     * @param index The index of the pointer
     * @return The pointer or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getOrNull(index: Int): Pointer<T>? {
        return if (index < pointers.size) {
            pointers[index]
        } else {
            null
        }
    }

    /**
     * Create a late init pointer inside the register
     * @return The new pointer
     * @since 0.1.0
     * @version 0.1.0
     * @see LateInitPointer
     * @see Pointer.late
     */
    fun createPointer(): LateInitPointer<T> {
        val pointer = Pointer.late<T>()
        register(pointer)
        return pointer
    }

    /**
     * Create a late init pointer inside the register
     *
     * @param value The value the pointer should point to
     * @return The new pointer
     * @since 0.1.0
     * @version 0.1.0
     * @see LateInitPointer
     * @see Pointer.late
     */
    fun createPointer(value: T): Pointer<T> {
        val pointer = Pointer.of(value)
        register(pointer)
        return pointer
    }

    /**
     * Create a mutable pointer inside the register
     *
     * @return The new pointer
     * @since 0.1.0
     * @version 0.1.0
     * @see MutablePointer
     * @see Pointer.mutableOf
     */
    fun createMutablePointer(): LateInitMutablePointer<T> {
        val pointer = Pointer.lateMutable<T>()
        register(pointer)
        return pointer
    }

    /**
     * Create a mutable pointer inside the register
     *
     * @param value The value the pointer should point to
     * @return The new pointer
     * @since 0.1.0
     * @version 0.1.0
     * @see MutablePointer
     * @see Pointer.mutableOf
     */
    fun createMutablePointer(value: T): MutablePointer<T> {
        val pointer = Pointer.mutableOf(value)
        register(pointer)
        return pointer
    }

    /**
     * Destroy the register
     * @since 0.1.0
     * @version 0.1.0
     */
    fun destroy() {
        this.pointers.clear()
        instances.remove(this)
    }

    /**
     * Destroy all contained pointers
     * @since 0.1.0
     * @version 0.1.0
     */
    fun destroyAllContainedPointers() {
        pointers.forEach { it?.unregister() }
    }

    companion object {
        /**
         * The list of all instances of pointer registers
         */
        val instances = mutableListOf<PointerRegister<*>>()
    }
}

/**
 * Register the pointer to the given register
 * @param register The register
 * @return The index of the pointer
 * @since 0.1.0
 * @version 0.1.0
 */
fun <T> Pointer<T>.register(register: PointerRegister<T>): Int {
    return register.register(this)
}

/**
 * Unregister the pointer from the given register
 * @param register The register
 * @since 0.1.0
 * @version 0.1.0
 */
fun <T> Pointer<T>.unregister(register: PointerRegister<T>) {
    val index = register.indexOf(this)
    if (index == -1) throw IllegalStateException("pointer is not registered")
    register.unregister(index)
}

/**
 * Close the pointer (unregister it from all registers)
 * @since 0.1.0
 * @version 0.1.0
 */
fun Pointer<*>.unregister() {
    PointerRegister.instances.forEach {
        val index = it.indexOf(this)
        if (index != -1) it.unregister(index)
    }
}
