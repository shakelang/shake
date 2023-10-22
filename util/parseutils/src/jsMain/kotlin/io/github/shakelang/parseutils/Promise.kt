package io.github.shakelang.parseutils

external fun setTimeout(exec: suspend () -> Unit, timout: Int)

@Suppress("ACTUAL_TYPE_ALIAS_TO_CLASS_WITH_DECLARATION_SITE_VARIANCE", "unused")
actual open class Promise<out T> {

    val promise: kotlin.js.Promise<T>

    actual constructor(executor: PromiseExecutor<T>) {
        this.promise = kotlin.js.Promise { resolve, reject ->
            executor(
                {
                    resolve(it) },
                {
                    reject(it) }
            )

        }
    }

    constructor(promise: kotlin.js.Promise<T>) {
        this.promise = promise
    }

    actual open fun <S> then(onFulfilled: ((T) -> S)?): Promise<S> {
        return Promise(this.promise.then { onFulfilled!!(it) })
    }

    actual open fun <S> then(onFulfilled: ((T) -> S)?, onRejected: ((Throwable) -> S)?): Promise<S> {
        return Promise(this.promise.then ({ onFulfilled!!(it) }, { onRejected!!(it) }))
    }

    actual open fun <S> catch(onRejected: (Throwable) -> S): Promise<S> {
        return Promise(this.promise.catch { onRejected(it) })
    }

    actual companion object {
        actual fun <T> resolve(v: T): Promise<T> = Promise(kotlin.js.Promise.resolve(v))
        actual fun <T> reject(e: Throwable): Promise<T> = Promise(kotlin.js.Promise.reject(e))
    }
}