@file:JvmName("TestUtil")

package com.github.nsc.de.shake

import org.junit.jupiter.api.Assertions

fun assertType(expected: Class<*>?, actual: Any) {
    Assertions.assertSame(expected, actual.javaClass)
}