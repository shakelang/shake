package com.shakelang.util.pointer

/**
 * A pointer points to a value in memory. It can be used to pass a value by reference.
 * The advantage of a pointer is that it can be used to change the value of a variable
 * in a function.
 *
 * @param T The type of the value the pointer points to
 * @since 0.1.0
 * @version 0.1.0
 */
interface Pointer<out T> {

    /**
     * The value the pointer points to
     */
    val value: T

    /**
     * Transform the value of the pointer. Creates a new pointer which will
     * get the value on access, transform it and return it. So if you change
     * the value of the original pointer the value of the new pointer will
     * change too.
     *
     * WARNING: If you access the value of the transformed pointer multiple
     * times the transform function will be called multiple times too. So
     * for performance reasons you should get the value of the pointer and
     * store it in a variable if you need it multiple times (and don't need
     * the changes of the original pointer).
     *
     * @param transform The transform function
     * @return The new pointer
     * @since 0.1.0
     * @version 0.1.0
     */
    fun <A> transform(transform: (T) -> A): Pointer<A> {
        return object : Pointer<A> {
            override val value: A
                get() = transform(this@Pointer.value)
        }
    }

    /**
     * Chain the value of the pointer. Creates a new pointer which will
     * get the value on access, transform it and return it. So if you change
     * the value of the original pointer the value of the new pointer will
     * change too. Is the same as [transform] but the transform functions parameter
     * should return a value, this function parameter should return a pointer.
     *
     * WARNING: If you access the value of the transformed pointer multiple
     * times the transform function will be called multiple times too. So
     * for performance reasons you should get the value of the pointer and
     * store it in a variable if you need it multiple times (and don't need
     * the changes of the original pointer).
     *
     * @param transform The transform function
     * @return The new pointer
     * @since 0.1.0
     * @version 0.1.0
     */
    fun <A> chain(transform: (T) -> Pointer<A>): Pointer<A> {
        return object : Pointer<A> {
            override val value: A
                get() = transform(this@Pointer.value).value
        }
    }

    /**
     * Chain the value of the pointer. Creates a new pointer which will
     * get the value on access, transform it and return it. So if you change
     * the value of the original pointer the value of the new pointer will
     * change too. Is the same as [transform] but the transform functions parameter
     * should return a value, this function parameter should return a pointer.
     * This function allows null values.
     *
     * Additionally, in contrast to [chain], this function allows null values.
     *
     * WARNING: If you access the value of the transformed pointer multiple
     * times the transform function will be called multiple times too. So
     * for performance reasons you should get the value of the pointer and
     * store it in a variable if you need it multiple times (and don't need
     * the changes of the original pointer).
     *
     * @param transform The transform function
     * @return The new pointer
     * @since 0.1.0
     * @version 0.1.0
     */
    fun <A> chainAllowNull(transform: (T) -> Pointer<A?>?): Pointer<A?> {
        return object : Pointer<A?> {
            override val value: A?
                get() = transform(this@Pointer.value)?.value
        }
    }

    companion object {

        /**
         * Create a new pointer pointing to the given value
         *
         * @param value The value the pointer should point to
         * @return The new pointer
         * @since 0.1.0
         * @version 0.1.0
         */
        fun <T> of(value: T) = object : Pointer<T> {
            override val value: T = value
        }

        /**
         * Create a new mutable pointer pointing to the given value
         *
         * @param value The value the pointer should point to
         * @return The new pointer
         * @since 0.1.0
         * @version 0.1.0
         */
        fun <T> mutableOf(value: T) = object : MutablePointer<T> {
            override var value: T = value
        }

        /**
         * Create a new pointer which is not initialized yet and can
         * be initialized later
         *
         * @return The new pointer
         * @since 0.1.0
         * @version 0.1.0
         */
        fun <T> late() = object : LateInitPointer<T> {
            var realValue: T? = null

            override var isInitialized = false
                private set

            @Suppress("UNCHECKED_CAST")
            override val value: T
                get() = if (isInitialized) realValue as T else throw IllegalStateException("lateinit pointer is not initialized")

            override fun init(value: T) {
                if (isInitialized) throw IllegalStateException("late init pointer is already initialized")
                this.realValue = value
                isInitialized = true
            }
        }

        /**
         * Create a new mutable pointer which is not initialized yet
         * and can be initialized later
         *
         * @return The new pointer
         * @since 0.1.0
         * @version 0.1.0
         */
        fun <T> lateMutable() = object : LateInitMutablePointer<T> {
            var realValue: T? = null

            override var isInitialized = false
                private set

            @Suppress("UNCHECKED_CAST")
            override var value: T
                get() = if (isInitialized) realValue as T else throw IllegalStateException("late init pointer is not initialized")
                set(value) {
                    realValue = value
                    isInitialized = true
                }

            override fun init(value: T) {
                if (isInitialized) throw IllegalStateException("late init pointer is already initialized")
                this.realValue = value
                isInitialized = true
            }
        }

        /**
         * Create a new pointer which performs the given task on access
         * and returns the result of the task
         *
         * @param task The task to perform
         * @return The new pointer
         * @since 0.1.0
         * @version 0.1.0
         */
        fun <T> task(task: () -> T) = object : Pointer<T> {
            override val value: T get() = task()
        }
    }
}

/**
 * Create a new pointer which is not initialized yet and can
 * be initialized later
 * @return The new pointer
 * @since 0.1.0
 * @version 0.1.0
 */
fun <T> latePoint(): LateInitPointer<T> = Pointer.late()

/**
 * Create a new mutable pointer which is not initialized yet
 * and can be initialized later
 * @return The new pointer
 * @since 0.1.0
 * @version 0.1.0
 */
fun <T> lateMutablePoint(): LateInitMutablePointer<T> = Pointer.lateMutable()

/**
 * Create a new pointer which performs the given task on access
 * and returns the result of the task
 * @param task The task to perform
 * @return The new pointer
 * @since 0.1.0
 * @version 0.1.0
 */
fun <T> taskPoint(task: () -> T): Pointer<T> = Pointer.task(task)

/**
 * Create a new pointer pointing to the given value
 * @receiver The value the pointer should point to
 * @return The new pointer
 * @since 0.1.0
 * @version 0.1.0
 */
fun <T> T.point(): Pointer<T> = Pointer.of(this)

/**
 * Create a new mutable pointer pointing to the given value
 * @receiver The value the pointer should point to
 * @return The new pointer
 * @since 0.1.0
 * @version 0.1.0
 */
fun <T> T.mutablePoint(): MutablePointer<T> = Pointer.mutableOf(this)

/**
 * A mutable pointer points to a value in memory. It can be used to pass a value by reference.
 * The advantage of a pointer is that it can be used to change the value of a variable
 * in a function.
 *
 * @param T The type of the value the pointer points to
 * @since 0.1.0
 * @version 0.1.0
 * @see Pointer
 * @see LateInitPointer
 */
interface MutablePointer<T> : Pointer<T> {

    /**
     * The value the pointer points to
     */
    override var value: T
}

/**
 * A late init pointer points to a value in memory. It can be used to pass a value by reference.
 * The advantage of a pointer is that it can be used to change the value of a variable
 * in a function.
 *
 * The difference between a [LateInitPointer] and a [MutablePointer] is that a [LateInitPointer]
 * can be initialized later and a [LateInitPointer] can only be initialized once.
 *
 * @param T The type of the value the pointer points to
 * @since 0.1.0
 * @version 0.1.0
 * @see Pointer
 * @see MutablePointer
 * @see LateInitMutablePointer
 */
interface LateInitPointer<T> : Pointer<T> {

    /**
     * The value the pointer points to
     */
    override val value: T

    /**
     * Is the pointer initialized
     */
    val isInitialized: Boolean

    /**
     * Initialize the pointer
     *
     * @param value The value the pointer should point to
     * @since 0.1.0
     * @version 0.1.0
     */
    fun init(value: T)
}

/**
 * A mutable late init pointer points to a value in memory. It can be used to pass a value by reference.
 * The advantage of a pointer is that it can be used to change the value of a variable
 * in a function.
 *
 * A [LateInitMutablePointer] combines the features of a [MutablePointer] and a [LateInitPointer].
 *
 * @param T The type of the value the pointer points to
 * @since 0.1.0
 * @version 0.1.0
 * @see Pointer
 * @see MutablePointer
 * @see LateInitPointer
 * @see LateInitMutablePointer
 */
interface LateInitMutablePointer<T> : MutablePointer<T>, LateInitPointer<T> {
    /**
     * The value the pointer points to
     */
    override var value: T
}

/**
 * Make sure that the value of the pointer is not null
 * @param msg The message of the exception that will be thrown if the value is null
 * @return The pointer
 * @since 0.1.0
 * @version 0.1.0
 */
fun <T> Pointer<T?>.notNull(msg: String? = null): Pointer<T> {
    return this.transform { it ?: throw IllegalStateException(msg ?: "null value not allowed") }
}
