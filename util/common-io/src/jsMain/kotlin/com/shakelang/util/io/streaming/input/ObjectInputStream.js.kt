package com.shakelang.util.io.streaming.input

actual fun <T> Array<T>.createNewArrayOfNulls(size: Int): Array<T?> {
    return js("new Array(size)") as Array<T?>
}
