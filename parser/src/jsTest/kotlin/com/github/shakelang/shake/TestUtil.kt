
package com.github.shakelang.shake

import kotlin.reflect.KClass
import kotlin.test.assertSame
import kotlin.test.assertTrue

actual fun assertType(expected: KClass<*>, actual: Any) {
    assertSame(expected, actual::class)
}

actual fun assertArrayEquals(expected: Array<*>, actual: Array<*>) = assertTrue(expected.contentEquals(actual))