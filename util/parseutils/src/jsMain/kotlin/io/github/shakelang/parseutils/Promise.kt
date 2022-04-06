package io.github.shakelang.parseutils

external fun setTimeout(exec: suspend () -> Unit, timout: Int)

@Suppress("ACTUAL_TYPE_ALIAS_TO_CLASS_WITH_DECLARATION_SITE_VARIANCE")
actual typealias Promise<T> = kotlin.js.Promise<T>