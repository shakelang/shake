package io.github.shakelang.parseutils

typealias ResolveFunction<T> = (T) -> Unit
typealias RejectFunction = (Throwable) -> Unit
typealias FulfilledFunction<T, S> = ((T) -> S)
typealias FinallyFunction = (() -> Unit)
typealias RejectedFunction<T> = ((Throwable) -> T)
typealias PromiseExecutor<T> = (resolve: ResolveFunction<T>, reject: RejectFunction) -> Unit


/**
 * Argument for [promisify]'s executor
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
class PromiseExecutorArgument<T> (

    /**
     * Should resolve the [Promise]
     */
    private val resolve: ResolveFunction<T>,

    /**
     * Should reject the [Promise]
     */
    private val reject: RejectFunction

) {

    /**
     * Resolve the [Promise] with a value
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun resolve(value: T) = this.resolve.invoke(value)

    /**
     * Reject the [Promise] with a [Throwable]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun reject(value: Throwable) = this.reject.invoke(value)

}

/**
 * Simple way to create a [Promise]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun <T> promisify(executor: (PromiseExecutorArgument<T>) -> Unit) =
    Promise<T> { resolve, reject -> executor(PromiseExecutorArgument(resolve, reject))}

/**
 * The [Promise] object represents the eventual completion (or failure) of an asynchronous
 * operation and its resulting value.
 * [Promise]s are a native JavaScript feature. We also have a Java implementation right here
 * that should work the same
 * [Read the JavaScript documentation for Promises](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise)
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
expect open class Promise<out T>(

    /**
     * The executor for the [Promise].
     * This function will be executed instantly. It get's a resolve and a reject function as a parameter.
     * Call these with a parameter to resolve/reject the [Promise]
     */
     executor: PromiseExecutor<T>

) {

    /**
     * Chain a function to be executed after this [Promise] finishes
     *
     * @param onFulfilled the function to be executed after this promise
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open fun <S> then(onFulfilled: FulfilledFunction<T, S>?): Promise<S>

    /**
     * Chain a function to be executed after this [Promise] finishes and adds a
     * onRejected function to be executed when the [Promise] fails
     *
     * @param onFulfilled the function to be executed after this promise
     * @param onRejected the function to be executed when the promise fails
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open fun <S> then(onFulfilled: FulfilledFunction<T, S>?, onRejected: RejectedFunction<S>?): Promise<S>

    /**
     * Adds a onRejected function to be executed when the [Promise] fails
     *
     * @param onRejected the function to be executed when the promise fails
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open fun <S> catch(onRejected: RejectedFunction<S>): Promise<S>

    open fun finally(onFinally: FinallyFunction): Promise<T>

    companion object {
        fun <S> all(promise: Array<out Promise<S>>): Promise<Array<out S>>

        fun <S> race(promise: Array<out Promise<S>>): Promise<S>

        fun reject(e: Throwable): Promise<Nothing>

        fun <S> resolve(e: S): Promise<S>
        fun <S> resolve(e: Promise<S>): Promise<S>
    }
}


fun <T> promiseResolve(v: T) = Promise {
    resolve, _ -> resolve(v)
}

fun <T> promiseReject(e: Throwable) = Promise<T> {
    _, reject -> reject(e)
}