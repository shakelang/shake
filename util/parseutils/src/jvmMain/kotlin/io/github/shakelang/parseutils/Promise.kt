package io.github.shakelang.parseutils

@Suppress("ACTUAL_TYPE_ALIAS_TO_CLASS_WITH_DECLARATION_SITE_VARIANCE", "unused")
actual open class Promise<out T> actual constructor(
    private val executor: PromiseExecutor<T>
) {

    /**
     * If the [Promise] is finished
     */
    private var finished: Boolean = false

    /**
     * The value of the [Promise]
     */
    private var value: T? = null

    /**
     * The error of the [Promise]
     */
    private var error: Throwable? = null

    /**
     * The onFulfilled function of the [Promise]
     */
    private var onFulfilled = mutableListOf<FulfilledFunction<T, *>>()

    /**
     * The onRejected function of the [Promise]
     */
    private var onRejected = mutableListOf<RejectedFunction<*>>()

    init {
        execute()
    }

    actual open fun <S> then(onFulfilled: ((T) -> S)?): Promise<S> {

        var followResolve: ResolveFunction<S>? = null
        var followReject: RejectFunction? = null

        val promise = Promise<S> { rs, rj ->
            followResolve = rs
            followReject = rj
        }

        if(finished) {
            if(error != null) {
                followReject?.let { it(error!!) }
            } else {
                onFulfilled?.let { followResolve?.let { it2 -> it2(it(value!!)) } }
            }
        }

        else this.onFulfilled.add {
            try {
                val v = onFulfilled?.let { it2 -> it2(it) }
                followResolve?.let { it (v!!) }
            } catch(t: Throwable) {
                followReject?.let { it(t) }
            }
        }

        // TODO should this add the onRejected function to the promise?

        return promise

    }

    actual open fun <S> then(onFulfilled: ((T) -> S)?, onRejected: ((Throwable) -> S)?): Promise<S> {

        var followResolve: ResolveFunction<S>? = null
        var followReject: RejectFunction? = null

        val promise = Promise<S> { rs, rj ->
            followResolve = rs
            followReject = rj
        }

        val then = this.then(onFulfilled)

        then.then { followResolve?.let { it2 -> it2(it) } }
        then.catch { followReject?.let { it2 -> it2(it) } }

        if(onRejected == null) return promise

        val catch = this.catch(onRejected)

        catch.then { followResolve?.let { it2 -> it2(it) } }
        catch.catch { followReject?.let { it2 -> it2(it) } }

        return promise

    }

    actual open fun <S> catch(onRejected: (Throwable) -> S): Promise<S> {

        var followResolve: ResolveFunction<S>? = null
        var followReject: RejectFunction? = null

        val promise = Promise<S> { rs, rj ->
            followResolve = rs
            followReject = rj
        }

        if(finished) {
            if(error != null) {
                followReject?.let { it(error!!) }
            }

            // TODO should this add the onFulfilled function to the promise?
        }

        else this.onRejected.add {
            try {
                val v = onRejected(it)
                followResolve?.let { it(v) }
            } catch(t: Throwable) {
                followReject?.let { it(t) }
            }
        }

        return promise

    }

    private fun resolve(value: T) {

        this.value = value
        this.finished = true
        for(f in onFulfilled) f(value)

    }

    private fun reject(error: Throwable) {

        this.error = error
        this.finished = true
        for(r in onRejected) r(error)

    }

    private fun execute() {
        try {
            executor(this::resolve, this::reject)
        } catch (error: Throwable) {
            this.reject(error)
        }
    }

    actual companion object {
        actual fun <T> resolve(v: T): Promise<T> = Promise { rs, _ -> rs(v) }
        actual fun <T> reject(e: Throwable): Promise<T> = Promise { _, rj -> rj(e) }
    }
}