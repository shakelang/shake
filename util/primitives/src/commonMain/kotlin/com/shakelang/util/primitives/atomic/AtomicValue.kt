package com.shakelang.util.primitives.atomic

interface ListenableItem {
    fun addListener(listener: () -> Unit): () -> Unit
    fun removeListener(listener: () -> Unit)
}

abstract class ListenableItemImpl : ListenableItem {
    private val listeners: MutableList<() -> Unit> = mutableListOf()

    override fun addListener(listener: () -> Unit): () -> Unit {
        listeners.add(listener)
        return { listeners.remove(listener) }
    }

    override fun removeListener(listener: () -> Unit) {
        listeners.remove(listener)
    }

    protected open fun notifyListeners() {
        listeners.forEach { it() }
    }
}

interface AtomicValue : ListenableItem

class AtomicConnection1<T1 : AtomicValue, T : AtomicValue>(
    val source1: T1,
    val target: T,
) {
    lateinit var update: (AtomicConnection1<T1, T>) -> Unit
        private set

    constructor(source1: T1, target: T, update: (AtomicConnection1<T1, T>) -> Unit) : this(source1, target) {
        this.update = update
    }

    val listener: () -> Unit = source1.addListener {
        update(this)
    }

    fun destroy() {
        source1.removeListener(listener)
    }

    companion object {
        fun <T : AtomicValue, V : AtomicValue> init(source1: AtomicConnection1<T, V>, update: (AtomicConnection1<T, V>) -> Unit): AtomicConnection1<T, V> {
            source1.update = update
            return source1
        }
    }
}

class AtomicConnection2<T1 : AtomicValue, T2 : AtomicValue, T : AtomicValue>(
    val source1: T1,
    val source2: T2,
    val target: T,
) {
    lateinit var update: (AtomicConnection2<T1, T2, T>) -> Unit
        private set

    constructor(source1: T1, source2: T2, target: T, update: (AtomicConnection2<T1, T2, T>) -> Unit) : this(source1, source2, target) {
        this.update = update
    }

    val listener1: () -> Unit = source1.addListener {
        update(this)
    }

    val listener2: () -> Unit = source2.addListener {
        update(this)
    }

    fun destroy() {
        source1.removeListener(listener1)
        source2.removeListener(listener2)
    }

    companion object {
        fun <T : AtomicValue, V : AtomicValue, W : AtomicValue> init(source1: AtomicConnection2<T, V, W>, update: (AtomicConnection2<T, V, W>) -> Unit): AtomicConnection2<T, V, W> {
            source1.update = update
            return source1
        }
    }
}

class AtomicConnection3<T1 : AtomicValue, T2 : AtomicValue, T3 : AtomicValue, T : AtomicValue>(
    val source1: T1,
    val source2: T2,
    val source3: T3,
    val target: T,
) {

    lateinit var update: (AtomicConnection3<T1, T2, T3, T>) -> Unit
        private set

    constructor(source1: T1, source2: T2, source3: T3, target: T, update: (AtomicConnection3<T1, T2, T3, T>) -> Unit) : this(source1, source2, source3, target) {
        this.update = update
    }

    val listener1: () -> Unit = source1.addListener {
        update(this)
    }

    val listener2: () -> Unit = source2.addListener {
        update(this)
    }

    val listener3: () -> Unit = source3.addListener {
        update(this)
    }

    fun destroy() {
        source1.removeListener(listener1)
        source2.removeListener(listener2)
        source3.removeListener(listener3)
    }

    companion object {
        fun <T : AtomicValue, V : AtomicValue, W : AtomicValue, X : AtomicValue> init(source1: AtomicConnection3<T, V, W, X>, update: (AtomicConnection3<T, V, W, X>) -> Unit): AtomicConnection3<T, V, W, X> {
            source1.update = update
            return source1
        }
    }
}

fun <T : AtomicValue, V : AtomicValue> connection1(
    source1: T,
    target: V,
): AtomicConnection1<T, V> {
    return AtomicConnection1(source1, target)
}

fun <T : AtomicValue, V : AtomicValue> connection1(
    source1: T,
    target: V,
    update: (AtomicConnection1<T, V>) -> Unit,
): AtomicConnection1<T, V> {
    return AtomicConnection1(source1, target, update)
}

fun <T : AtomicValue, V : AtomicValue, W : AtomicValue> connection2(
    source1: T,
    source2: V,
    target: W,
): AtomicConnection2<T, V, W> {
    return AtomicConnection2(source1, source2, target)
}

fun <T : AtomicValue, V : AtomicValue, W : AtomicValue> connection2(
    source1: T,
    source2: V,
    target: W,
    update: (AtomicConnection2<T, V, W>) -> Unit,
): AtomicConnection2<T, V, W> {
    return AtomicConnection2(source1, source2, target, update)
}

fun <T : AtomicValue, V : AtomicValue, W : AtomicValue, X : AtomicValue> connection3(
    source1: T,
    source2: V,
    source3: W,
    target: X,
): AtomicConnection3<T, V, W, X> {
    return AtomicConnection3(source1, source2, source3, target)
}

fun <T : AtomicValue, V : AtomicValue, W : AtomicValue, X : AtomicValue> connection3(
    source1: T,
    source2: V,
    source3: W,
    target: X,
    update: (AtomicConnection3<T, V, W, X>) -> Unit,
): AtomicConnection3<T, V, W, X> {
    return AtomicConnection3(source1, source2, source3, target, update)
}
