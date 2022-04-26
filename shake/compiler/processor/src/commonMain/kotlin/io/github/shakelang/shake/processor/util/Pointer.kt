package io.github.shakelang.shake.processor.util

interface Pointer<T> {
    val value: T

    companion object {
        fun <T> of(value: T) = object : Pointer<T> {
            override val value: T = value
        }
        fun <T> mutableOf(value: T) = object : MutablePointer<T> {
            override var value: T = value
        }
        fun <T> late() = object : LateInitPointer<T> {
            var realValue: T? = null

            override var isInitialized = false
                private set

            @Suppress("UNCHECKED_CAST")
            override val value: T
                get() = if(isInitialized) realValue as T else throw IllegalStateException("late init pointer is not initialized")

            override fun init(value: T) {
                if(isInitialized) throw IllegalStateException("late init pointer is already initialized")
                this.realValue = value
                isInitialized = true
            }
        }
        fun <T> lateMutable() = object : LateInitMutablePointer<T> {
            var realValue: T? = null

            override var isInitialized = false
                private set

            @Suppress("UNCHECKED_CAST")
            override var value: T
                get() = if(isInitialized) realValue as T else throw IllegalStateException("late init pointer is not initialized")
                set(value) {
                    realValue = value
                    isInitialized = true
                }

            override fun init(value: T) {
                if(isInitialized) throw IllegalStateException("late init pointer is already initialized")
                this.realValue = value
                isInitialized = true
            }
        }
    }
}

fun <T> point(value: T): Pointer<T> = Pointer.of(value)
fun <T> mutablePoint(value: T): MutablePointer<T> = Pointer.mutableOf(value)
fun <T> latePoint(): Pointer<T> = Pointer.late()
fun <T> lateMutablePoint(): MutablePointer<T> = Pointer.lateMutable()

interface MutablePointer<T> : Pointer<T> {
    override var value: T
}

interface LateInitPointer<T> : Pointer<T> {
    override val value: T
    val isInitialized: Boolean
    fun init(value: T)
}

interface LateInitMutablePointer<T> : MutablePointer<T>, LateInitPointer<T> {
    override var value: T
}