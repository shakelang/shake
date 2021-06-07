package com.github.shakelang.shake.util


typealias ResolveFunction<T> = (T) -> Unit
typealias RejectFunction = (Throwable) -> Unit
typealias FulfilledFunction<T> = ((T) -> Any?)
typealias RejectedFunction = ((Throwable) -> Any?)
typealias PromiseExecutor<T> = (resolve: ResolveFunction<T>, reject: RejectFunction) -> Unit

expect open class Promise<out T>(
     executor: PromiseExecutor<T>
) {
    open fun <S> then(onFulfilled: ((T) -> S)?): Promise<S>
    open fun <S> then(onFulfilled: ((T) -> S)?, onRejected: ((Throwable) -> S)?): Promise<S>
    open fun <S> catch(onRejected: (Throwable) -> S): Promise<S>
}