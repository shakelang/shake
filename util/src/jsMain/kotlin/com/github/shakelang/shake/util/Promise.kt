package com.github.shakelang.shake.util

external fun setTimeout(exec: suspend () -> Unit, timout: Int)

@Suppress("ACTUAL_TYPE_ALIAS_TO_CLASS_WITH_DECLARATION_SITE_VARIANCE")
actual typealias Promise<T> = kotlin.js.Promise<T>

actual fun timeout(millis: Int): Promise<Unit> =
    promisify {
        setTimeout({
            it.resolve(Unit)
        }, millis)
    }