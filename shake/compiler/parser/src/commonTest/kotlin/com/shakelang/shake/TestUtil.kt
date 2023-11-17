package com.shakelang.shake

import kotlin.reflect.KClass

expect fun assertType(expected: KClass<*>, actual: Any)
expect fun assertArrayEquals(expected: Array<*>, actual: Array<*>)

/**
 * Returns a stream of all possible permutations of the given list.
 *
 * `a b c` -> `{a, b c}, {a, c b}, {b, a c}, {b, c a}, {c, a b}, {c, b a}`
 */

fun <T> List<T>.allCombinations(): Sequence<List<T>> {
    return sequence {
        val indices = this@allCombinations.indices.toList()

        for (i in indices.indices) {
            // get sublist without i
            val subList = this@allCombinations.subList(0, i) + this@allCombinations.subList(i + 1, this@allCombinations.size)
            if (subList.isEmpty()) {
                yield(listOf(this@allCombinations[i]))
                continue
            }
            for(combination in subList.allCombinations()) {
                yield(listOf(this@allCombinations[i]) + combination)
            }
        }
    }
}

infix fun <T> T.shouldBeOfType(expected: KClass<*>) {
    if(this == null) throw AssertionError("Expected $expected but was null")
    assertType(expected, this)
}