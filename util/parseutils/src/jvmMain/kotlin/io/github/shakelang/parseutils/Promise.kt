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
     * The onFulfilled functions of the [Promise]
     */
    private var onFulfilled = mutableListOf<FulfilledFunction<T, *>>()

    /**
     * The onRejected functions of the [Promise]
     */
    private var onRejected = mutableListOf<RejectedFunction<*>>()

    /**
     * The onFinally functions of the [Promise]
     */
    private var onFinally = mutableListOf<FinallyFunction>()

    /**
     * The onFinally functions of the [Promise]
     */

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

    actual open fun finally(onFinally: FinallyFunction): Promise<T> {
        this.onFinally.add(onFinally)
        return this
    }

    private fun resolve(value: T) {

        this.value = value
        this.finished = true
        for(f in onFulfilled) f(value)
        for(f in onFinally) f()

    }

    private fun reject(error: Throwable) {

        this.error = error
        this.finished = true
        for(r in onRejected) r(error)
        for(f in onFinally) f()

    }

    private fun execute() {
        try {
            executor(this::resolve, this::reject)
        } catch (error: Throwable) {
            this.reject(error)
        }
    }

    actual companion object {
        actual fun <S> all(promise: Array<out Promise<S>>): Promise<Array<out S>> {
            return Promise { resolve, reject ->
                val results = mutableListOf<S>()
                var finished = 0
                for((i, p) in promise.withIndex()) {
                    p.then {
                        results.add(it)
                        finished++
                        if(finished == promise.size) resolve(results.toList())
                    }.catch {
                        reject(it)
                    }
                }
            }
        }

        actual fun <S> race(promise: Array<out Promise<S>>): Promise<S> {
            return Promise { resolve, reject ->
                for(p in promise) {
                    p.then {
                        resolve(it)
                    }.catch {
                        reject(it)
                    }
                }
            }
        }

        actual fun reject(e: Throwable): Promise<Nothing> {
            return Promise { _, reject ->
                reject(e)
            }
        }

        actual fun <S> resolve(e: S): Promise<S> {
            return Promise { resolve, _ ->
                resolve(e)
            }
        }

        actual fun <S> resolve(e: Promise<S>): Promise<S> {
            return Promise { resolve, reject ->
                e.then {
                    resolve(it)
                }.catch {
                    reject(it)
                }
            }
        }
    }
}