package com.github.shakelang.shake.util.environment

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class EnvironmentTests {

    @Test
    fun testEnvironment() {

        assertTrue("Expect environment to be java!") { getRunningEnvironment().isJava }
        assertFalse("Expect environment not to be javascript!") { getRunningEnvironment().isJavaScript }

    }

}