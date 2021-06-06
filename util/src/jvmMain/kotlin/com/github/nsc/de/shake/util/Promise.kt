package com.github.nsc.de.shake.util

@Suppress("ACTUAL_TYPE_ALIAS_TO_CLASS_WITH_DECLARATION_SITE_VARIANCE")
actual open class Promise<out T> actual constructor(
    private val executor: PromiseExecutor<T>
) {
    private var finished: Boolean = false
    private var value: T? = null
    private var error: Throwable? = null
    private var onFulfilled: FulfilledFunction<T>? = null
    private var onRejected: RejectedFunction? = null
    private var followResolve: ResolveFunction<*>? = null
    private var followReject: RejectFunction? = null

    actual open fun <S> then(onFulfilled: ((T) -> S)?): Promise<S> {
        if (this.onFulfilled != null) throw Error("onFulfilled already specified")
        this.onFulfilled = onFulfilled
        if (this.finished && this.value != null) onFulfilled?.let { it(this.value!!) }
        return Promise { rs, rj ->
            run {
                this.followResolve = rs
                this.followReject = rj
            }
        }
    }

    actual open fun <S> then(onFulfilled: ((T) -> S)?, onRejected: ((Throwable) -> S)?): Promise<S> {
        if (this.onFulfilled != null) throw Error("onFulfilled already specified")
        if (this.onRejected != null) throw Error("onRejected already specified")
        this.onFulfilled = onFulfilled
        this.onRejected = onRejected
        if (this.finished) {
            if (this.value != null) onFulfilled?.let { it(this.value!!) }
            else onRejected?.let { it(this.error!!) }
        }
        return Promise { rs, rj ->
            run {
                this.followResolve = rs
                this.followReject = rj
            }
        }
    }

    actual open fun <S> catch(onRejected: (Throwable) -> S): Promise<S> {
        if (this.onFulfilled != null) throw Error("onFulfilled already specified")
        if (this.onRejected != null) throw Error("onRejected already specified")
        this.onFulfilled = onFulfilled
        this.onRejected = onRejected
        if (this.finished && this.value == null) onRejected(this.error!!)
        return Promise { rs, rj ->
            run {
                this.followResolve = rs
                this.followReject = rj
            }
        }
    }

    private fun resolve(value: T) {
        try {
            if (finished) throw Error("Throwable already finished")
            this.finished = true
            this.value = value
            val v = this.onFulfilled?.let { it(value) }
            followResolve?.let { it(v as Nothing) }
        } catch(t: Throwable) {
            followReject?.let { it(t) };
        }
    }

    private fun reject(error: Throwable) {
        try {
            if(finished) throw Error("Throwable already finished")
            this.finished = true
            this.error = error
            val v = this.onRejected?.let { it(error) }
            followResolve?.let { it(v as Nothing) }
        } catch(t: Throwable) {
            followReject?.let { it(t) };
        }
    }

    private fun execute() {
        try {
            executor(this::resolve, this::reject)
        } catch (error: Throwable) {
            this.reject(error)
        }
    }

    init {
    }
}