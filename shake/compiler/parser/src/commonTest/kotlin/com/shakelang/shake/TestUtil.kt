package com.shakelang.shake

import kotlin.reflect.KClass
import kotlin.test.Test

expect fun assertType(expected: KClass<*>, actual: Any)
expect fun assertArrayEquals(expected: Array<*>, actual: Array<*>)

/**
 * Returns a stream of all possible permutations of the given list.
 */
// a b c -> {a, b c}, {a, c b}, {b, a c}, {b, c a}, {c, a b}, {c, b a}
fun <T> allCombinations(list: List<T>): Sequence<List<T>> {
    return sequence {
        val indices = list.indices.toList()

        for (i in indices.indices) {
            // get sublist without i
            val subList = list.subList(0, i) + list.subList(i + 1, list.size)
            if (subList.isEmpty()) {
                yield(listOf(list[i]))
                continue
            }
            for(combination in allCombinations(subList)) {
                yield(listOf(list[i]) + combination)
            }
        }
    }
}