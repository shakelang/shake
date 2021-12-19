@file:JvmName("TestUtil")

package io.github.shakelang.shake

import kotlin.jvm.JvmName
import kotlin.reflect.KClass

expect fun assertType(expected: KClass<*>, actual: Any)
expect fun assertArrayEquals(expected: Array<*>, actual: Array<*>)