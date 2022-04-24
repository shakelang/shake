package io.github.shakelang.shake.processor.util

interface Pointer<T> {
    val value: T

    companion object {
        fun <T> of(value: T): Pointer<T> = object : Pointer<T> {
            override val value: T = value
        }
        fun <T> mutableOf(value: T): MutablePointer<T> = object : MutablePointer<T> {
            override var value: T = value
        }
        fun <T> late(): Pointer<T> = object : LateInitPointer<T> {
            var realValue: T? = null
            override val value: T
                get() = realValue!!
            override fun init(value: T) {
                this.realValue = value
            }
        }
        fun <T> lateMutable(): MutablePointer<T> = object : LateInitMutablePointer<T> {
            var realValue: T? = null
            override var value: T
                get() = realValue!!
                set(value) {
                    realValue = value
                }
            override fun init(value: T) {
                this.realValue = value
            }
        }
    }
}

interface MutablePointer<T> : Pointer<T> {
    override var value: T
}

interface LateInitPointer<T> : Pointer<T> {
    override val value: T
    fun init(value: T)
}

interface LateInitMutablePointer<T> : MutablePointer<T>, LateInitPointer<T> {
    override var value: T
}