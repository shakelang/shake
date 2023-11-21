package com.shakelang.shake

import kotlin.reflect.KClass

infix fun <T> Array<T>.shouldHaveSameContents(expected: Array<T>) {
    if (this.size != expected.size) throw AssertionError("Expected ${expected.size} elements but was ${this.size}")
    for (i in this.indices) {
        if (this[i] != expected[i]) throw AssertionError("Expected ${expected[i]} but was ${this[i]}")
    }
}

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
            val subList =
                this@allCombinations.subList(0, i) + this@allCombinations.subList(i + 1, this@allCombinations.size)
            if (subList.isEmpty()) {
                yield(listOf(this@allCombinations[i]))
                continue
            }
            for (combination in subList.allCombinations()) {
                yield(listOf(this@allCombinations[i]) + combination)
            }
        }
    }
}

infix fun <T> T.shouldBeOfType(expected: KClass<*>) {
    if (this == null) throw AssertionError("Expected $expected but was null")
    if (!expected.isInstance(this)) throw AssertionError("Expected $expected but was ${this!!::class}")
}
