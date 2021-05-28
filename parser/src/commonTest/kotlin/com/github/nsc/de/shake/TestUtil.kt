@file:JvmName("TestUtil")

package com.github.nsc.de.shake

import kotlin.jvm.JvmName
import kotlin.reflect.KClass

expect fun assertType(expected: KClass<*>, actual: Any)
expect fun assertArrayEquals(expected: Array<*>, actual: Array<*>)