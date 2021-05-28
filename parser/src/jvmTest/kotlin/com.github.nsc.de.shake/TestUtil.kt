@file:JvmName("TestUtil")

package com.github.nsc.de.shake

import kotlin.jvm.JvmName
import kotlin.reflect.KClass
import kotlin.test.assertSame
import kotlin.test.assertTrue

actual fun assertType(expected: KClass<*>, actual: Any) {
    assertSame(expected.java, actual::class.java)
}

actual fun assertArrayEquals(expected: Array<*>, actual: Array<*>) = assertTrue(expected.contentEquals(actual))