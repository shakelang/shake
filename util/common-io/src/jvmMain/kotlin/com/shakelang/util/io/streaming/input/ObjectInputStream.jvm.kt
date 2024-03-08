package com.shakelang.util.io.streaming.input

@Suppress("UNCHECKED_CAST")
actual fun <T> Array<T>.createNewArrayOfNulls(size: Int): Array<T?> {
    return java.lang.reflect.Array.newInstance(this::class.java.componentType, size) as Array<T?>
}
