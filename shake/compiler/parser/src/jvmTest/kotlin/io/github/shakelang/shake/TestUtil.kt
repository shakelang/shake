@file:JvmName("TestUtil")

package io.github.shakelang.shake

import kotlin.reflect.KClass
import kotlin.test.assertSame
import kotlin.test.assertTrue

actual fun assertType(expected: KClass<*>, actual: Any) {
    assertSame(expected.java, actual::class.java)
}

actual fun assertArrayEquals(expected: Array<*>, actual: Array<*>) {
    try {
        assertTrue(expected.contentEquals(actual))
    } catch (e: AssertionError) {
        throw AssertionError(
            "Array mismatch: \n" +
                "expected: ${expected.contentToString()}, \n" +
                "actual: ${actual.contentToString()}",
            e
        )
    }
}
